package de.sos.mora.com;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import de.sos.mora.RemoteObjects.RemoteCommunicator;
import de.sos.mora.com.Communicator.Options;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.com.MoraMessages.OutMessage;
import de.sos.mora.com.NetMessagees.OutMsg;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;
import de.sos.mora.exceptions.UnreachableTargetException;

public class ClientConnections {

	public static abstract class AbstractConnection {

		private boolean used = false;
		
		private final int 	mIndex;
		
		public AbstractConnection(int index) {
			mIndex = index;
		}
		
		public void release() {
			used = false;
		}

		public abstract int getMaximumMessageSize();

		protected abstract boolean send(OutMsg outMsg) throws UnreachableTargetException;

		public abstract void close();

	}
	
	
	public static class Stack {
		private final RemoteCommunicator	mTarget;
		private final Options				mOptions;
		private final AbstractConnection[]	mConnections;
		
		
		public Stack(RemoteCommunicator com, final Options opt) {
			mTarget = com;
			mOptions = opt;
			mConnections = new AbstractConnection[opt.maxConnectionsPerClient];
		}
		
		public AbstractConnection get() throws MoraException {
			synchronized (mConnections) {
				for (int i = 0; i < mConnections.length; i++) {
					AbstractConnection con = mConnections[i];
					if (con == null)
						mConnections[i] = con = ClientConnections.createConnection(mTarget, i, mOptions);
					if (con.used == false) {
						con.used = true;
						return con;
					}
				}	
			}
			return null; //all available connections are in use - wait until a connection has been released
		}

		public void close() {
			for (int i = 0; i < mConnections.length; i++)
				if (mConnections[i] != null)
					mConnections[i].close();
		}
		
	}

	public static AbstractConnection createConnection(RemoteCommunicator target, int index, final Options opt) throws MoraException {
		try {
			switch(target.protocol) {
			case TCP:
				return new TCPConnections.TCPConnection(target, index, opt);
			case UDP:
				return new UDPConnections.UDPConnection(target, index);
			}
		}catch(Exception e) {
			throw new MoraInvalidAddressException(e);
		}
		return null;
	}
	
	
	
	
	public static String createIdentifier(final PROTOCOL protocol, final InetAddress adr, int port) {
		return protocol.name() + "://" + adr.getHostAddress() + ":" + port;
	}




	public int mMaxSendAttempts = 3;
	
	
	
	class ClientWorker implements Runnable{
		private final OutMessage 		mMessage;
		
		public ClientWorker(final OutMessage msg) {
			mMessage = msg;
		}
		public void run() {
			Stack connectionStack = getConnectionStack(mMessage.getTarget());
			try {
				if (!send(connectionStack, mMessage, mMaxSendAttempts))
					System.err.println("Failed to send message: " + mMessage);
			} catch (MoraException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//TODO: change to real log
		}
	}
	
	private final Options			mOptions;
	private final ExecutorService	mExecutorService;
	private Map<String, Stack>		mConnectionStacks = new HashMap<>();
	
	public ClientConnections(final Options opt) {
		mOptions = opt;
		mExecutorService = Executors.newCachedThreadPool(new ThreadFactory() {			
			int idx = 0;
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "ClientWorker_" + idx++);
			}
		});
	}
	
	public Stack getConnectionStack(final RemoteCommunicator com) {
		Stack stack = mConnectionStacks.get(com.getIdentifier());
		if (stack == null) {
			mConnectionStacks.put(com.getIdentifier(), stack = new Stack(com, mOptions));
		}
		return stack;
	}




	public void send(OutMessage call) {
		mExecutorService.submit(new ClientWorker(call));
	}

	
	public AbstractConnection getConnection(final RemoteCommunicator target) throws MoraException {
		final Stack stack = getConnectionStack(target);
		for (int i = 0; i < mOptions.maxConnectionsPerClient; i++) {
			AbstractConnection out = stack.get();
			if (out != null)
				return out;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	

	private static boolean send(Stack connectionStack, OutMessage msg, int maxAttempts) throws MoraException {
		AbstractConnection con = connectionStack.get();
		int attempts = 0;
		while(con == null && attempts < maxAttempts) { //for the case, that all connections are in use
			attempts++;
			try { Thread.sleep(5); }catch (Exception e) {}
			con = connectionStack.get();
		}
		try {
			List<OutMsg> messages = NetMessagees.encode(msg.toByteArray(), msg.size(), con.getMaximumMessageSize());
			for (int i = 0; i<  messages.size(); i++) {
				attempts = maxAttempts ;
				while(attempts-- > 0) {
					if (con.send(messages.get(i)))
						break;
				}
				if (attempts <= 0)
					return false;
			}
			return true;
		}finally {
			con.release();
		}
	}
	
	public boolean send_sync(OutMessage message) throws MoraException {
		Stack stack = getConnectionStack(message.getTarget());
		assert(stack != null);
		return send(stack, message, mMaxSendAttempts);
	}




	public void closeStack(String ip) {
		ArrayList<String> toRemove = new ArrayList<>();
		for (String key : mConnectionStacks.keySet()) {
			if (key.contains(ip))
				toRemove.add(key);
		}
		for (int i = 0; i < toRemove.size(); i++) {
			String k = toRemove.get(i);
			Stack stack = mConnectionStacks.remove(k);
			stack.close();
		}
	}

	public void closeAll() {
		for (Entry<String, Stack> e : mConnectionStacks.entrySet()) {
			e.getValue().close();
		}
		mConnectionStacks.clear();
	}
	
	
	
}
