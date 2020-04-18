package de.sos.mora.com;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import de.sos.mora.IMoraAdapter;
import de.sos.mora.IMoraProxy;
import de.sos.mora.RemoteMethodCall;
import de.sos.mora.RemoteObjects.RemoteCommunicator;
import de.sos.mora.RemoteObjects.RemoteMethod;
import de.sos.mora.com.MoraMessages.InMethodCall;
import de.sos.mora.com.MoraMessages.InMethodRespond;
import de.sos.mora.com.MoraMessages.OutMethodCall;
import de.sos.mora.com.MoraMessages.OutMethodRespond;
import de.sos.mora.com.NetMessagees.InMessageCollection;
import de.sos.mora.exceptions.MoraException;

public class Communicator {

	public enum PROTOCOL {
		TCP, UDP;
		
		@Override
		public String toString() { 
			switch(this) {
			case UDP: return "udp";
			case TCP: return "tcp";
			}
			return null;
		}

		public static PROTOCOL valueOf(byte b) {
			switch(b) {
			case 0: return TCP;
			case 1: return UDP;
			}
			return null;
		}
		public static byte byteValue(final PROTOCOL p) {
			switch (p) {
			case TCP: return 0;
			case UDP: return 1;
			default:
				break;
			}
			return -1;
		}
	}
	
	public static class Options {
		public PROTOCOL 	protocol;
		public int 			port;
		public InetAddress	adr;
		/**
		 * requested maximum length of the queue of incoming
		 * connections.
		 */
		public int 			backlog = -1;
		public int			workerThreads = 5;
		public int 			maxConnectionsPerClient = 5;
		
		public long			timeout = 30;
		public TimeUnit		timeoutUnit = TimeUnit.SECONDS;
		
		public boolean		requestAcknowledge = true;
		public String 		name = "";
		
		
		public Options() {
			this(PROTOCOL.TCP, "localhost", 9242);
		}
		public Options(PROTOCOL protocol, String host, int port) {
			this.protocol = protocol;
			try {
				this.adr = InetAddress.getByName(host);
			} catch (UnknownHostException e) {
				throw new NullPointerException("Could not get ip address of host: " + host);
			}
			this.port = port;
		}
	}
	
	private final Options 					mOptions;
	
	private ISocketServer					mServer;
	private ClientConnections				mClients;
	
	private ExecutorService					mIncomingWorkerService;
	private RemoteCommunicator 				mResponseAddress;
	
	private Map<String, IMoraAdapter<?>>	mAdapters = new HashMap<>();
	private Map<String, IMoraProxy<?>>		mProxies = new HashMap<>();
	
	private List<IConnectionLostHandler>	mConnectionLostHandler = new ArrayList<>();
	

	public Communicator(PROTOCOL protocol) {
		this(protocol, "localhost", -1);
	}
	public Communicator(PROTOCOL protocol, String host, int port) {
		this(new Options(protocol, host, port));
	}
	public Communicator(final Options opt) {
		mOptions = opt;
		mClients = new ClientConnections(opt);
	}
	
	public long getTimeout() { return mOptions.timeout; }
	public TimeUnit getTimeoutUnit() { return mOptions.timeoutUnit;}


	public void start() throws MoraException {
		if (mServer != null)
			throw new MoraException("Server is already started");
		mIncomingWorkerService = Executors.newFixedThreadPool(mOptions.workerThreads, new ThreadFactory() {
			int idx = 0;
			@Override
			public Thread newThread(Runnable r) { 
				return new Thread(r, mOptions.name + "_" +"IncomingWorker_"+idx++);
			}
		});
		switch(mOptions.protocol) {
		case TCP:
			mServer = new TCPConnections(mOptions);
			break;
		case UDP:
			mServer = new UDPConnections(mOptions);
			break;
		default:
			throw new MoraException("Unsupported protocol");
		}
		assert(mServer != null);
		mServer.start(this);
	}
	
	public void stop() {
		try {
			mServer.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mClients.closeAll();
	}
	
	public void waitForTermination() throws InterruptedException {
		mServer.waitForTermination();
	}
	
	
	public RemoteCommunicator getResponseAddress() {
		if (mResponseAddress == null && mServer != null)
			mResponseAddress = mServer.getResponseAddress();
		return mResponseAddress;
	}

	/**
	 * Delegates the message to an worker (thread) that will do the invocation and response handling. 
	 * If a method with return value is invoked, the worker will send the return message to the server that has been encoded in the 
	 * incoming message.
	 * 
	 * @param inMoraMsg
	 */	
	public void handleIncomingMessage(InMessageCollection mc) {
		try {
			byte flags = mc.stream.readByte();
			short magic = mc.stream.readShort();
			if ((flags & MoraMessages.MESSAGE_TYPE__RESPONSE) == MoraMessages.MESSAGE_TYPE__RESPONSE) {
				InMethodRespond msg = new InMethodRespond(magic, flags, mc);
				mIncomingWorkerService.submit(() -> handleResponse(msg));
			}else if ((flags & MoraMessages.MESSAGE_TYPE__METHOD_CALL) == MoraMessages.MESSAGE_TYPE__METHOD_CALL) {
				InMethodCall msg = new InMethodCall(magic, flags, mc);
				mIncomingWorkerService.submit(new MessageHandler(this, msg));
			}else {
				throw new IOException("Don't know how to handle flag: " + flags);
			}
		}catch(IOException exp) {
			exp.printStackTrace();
		}
		
	}

	public <T extends IMoraAdapter<?>> T registerAdapter(T adapter) {
		if (adapter == null)
			return null;
		final String identifier = adapter.getIdentifier();
		if (hasAdapter(identifier) == true)
			return null;
		mAdapters.put(identifier, adapter);
		return adapter;
	}
	@SuppressWarnings("unchecked")
	public <T extends IMoraAdapter<?>> T getAdapter(String targetIdentifier) {
		try {
			return (T)mAdapters.get(targetIdentifier);
		}catch(ClassCastException cce) {
			return null;	
		}		
	}
	public boolean hasAdapter(String identifier) {
		return getAdapter(identifier) != null;
	}

	public <T extends IMoraProxy<?>> T getProxy(String quid) {
		IMoraProxy<?> p = mProxies.get(quid);
		if (p != null) {
			try {
				return (T)p;
			}catch(ClassCastException cc) {
				return null;
			}
		}
		return null;
	}


	public <T extends IMoraProxy<?>> void registerProxy(T proxy) {
		mProxies.put(proxy.getQualifiedIdentifier(), proxy);
	}
	
	
	
	
	public OutMethodCall createCall(final RemoteMethod method, final short magic) throws MoraException {
		try {
			return new OutMethodCall(method, magic, getResponseAddress());
		} catch (IOException e) {
			e.printStackTrace();
			throw new MoraException(e);
		}
	}


	
	
	
	
	private Map<Short, RemoteMethodCall<?>>		mOpenRemoteCalls = new HashMap<>();
	
	public void call(RemoteMethodCall<?> call) throws MoraException {
		short magic = call.getMagic();
		mOpenRemoteCalls.put(magic, call); //remember the call to handle the response
		mClients.send_sync(call.getMessage());
	}
	
	public void sendResponse(OutMethodRespond resp) throws MoraException {
		mClients.send_sync(resp);
	}

	private void handleResponse(InMethodRespond msg) {
		short magic = msg.getMagic();
		RemoteMethodCall resp = mOpenRemoteCalls.remove(magic);
		resp.handleResponse(msg);
	}
	
	
	public void registerConnectionLostHandler(IConnectionLostHandler handler) {
		mConnectionLostHandler.add(handler);
	}
	public boolean removeConnectionLostHandler(IConnectionLostHandler handler) {
		return mConnectionLostHandler.remove(handler);
	}
	public void closeConnections(RemoteCommunicator remoteCommunicator) {
		mClients.closeStack(remoteCommunicator.address.toString());
		if (mConnectionLostHandler != null && mConnectionLostHandler.isEmpty() == false)
			for (int i = 0; i < mConnectionLostHandler.size(); i++) {
				try {
					mConnectionLostHandler.get(i).onConnectionLost(remoteCommunicator);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	}

	
	





	
	
}
