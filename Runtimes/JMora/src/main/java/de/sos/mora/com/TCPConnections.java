package de.sos.mora.com;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.sos.mora.RemoteObjects.RemoteCommunicator;
import de.sos.mora.com.ClientConnections.AbstractConnection;
import de.sos.mora.com.Communicator.Options;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.com.NetMessagees.InMessageCollection;
import de.sos.mora.com.NetMessagees.InMsg;
import de.sos.mora.com.NetMessagees.OutMsg;
import de.sos.mora.exceptions.UnreachableTargetException;

public class TCPConnections implements ISocketServer {

	public static class TCPConnection extends AbstractConnection {
		private final Socket				mSocket;
		private final RemoteCommunicator	mTarget;
		private final OutputStream			outStream;
		private final InputStream			inStream;
		private final Options				mOptions;
		private final byte[]				mAckBuffer = new byte[4];
		
		public TCPConnection(RemoteCommunicator target, int index, Options opt) throws IOException {
			super(index);
			mOptions = opt;
			mTarget = target;
			mSocket = new Socket(target.address, target.port);
			outStream = mSocket.getOutputStream();
			inStream = mSocket.getInputStream();
		}

		@Override
		protected boolean send(OutMsg outMsg) throws UnreachableTargetException {
			try {
				outMsg.requestAcknowledge(mOptions.requestAcknowledge);
				outStream.write(outMsg.buffer);
				outStream.flush();
					
				if (mOptions.requestAcknowledge) {
					int read = inStream.read(mAckBuffer);
					if (read >= 4)
						return outMsg.checkResponse(mAckBuffer);
					return false;
				}
				return true;
			}catch(IOException se) {
				throw new UnreachableTargetException(mTarget, se);
			}
		}
		
		@Override
		public void close() {
			try {
				mSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public int getMaximumMessageSize() {
			return 10*1024*1024;
		}
	}
	
	
	private class ServerRunnable implements Runnable{
		public int 				mUsedPort;
		public InetAddress		mUsedHost;
		private ServerSocket 	mSocketServer;
		
		public void run() {
			try {
				mSocketServer = new ServerSocket(mOptions.port < 0 ? 0 : mOptions.port, mOptions.backlog, mOptions.adr);
				
				
				mUsedHost = mSocketServer.getInetAddress();
				mUsedPort = mSocketServer.getLocalPort();
				mResponseAddress = new RemoteCommunicator(mUsedHost, mUsedPort, PROTOCOL.TCP);
				System.out.println("Start Server at: " + mResponseAddress);
				
				while(mAlive == true) {
					try {
						Socket socket = mSocketServer.accept();
						spawnWorker(socket, mUsedHost, mUsedPort);
					}catch(SocketException se) {
						if (mAlive)
							se.printStackTrace();
						mAlive = false;
					}catch(Throwable t) {
						if (mAlive)
							t.printStackTrace();
					}
				}
				System.out.println("Finish Server at: " + mResponseAddress);
				
				if (mSocketServer != null)
					mSocketServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}


	private class ServerConnection implements Runnable {
		private final Socket 	mSocket;
		private final short		mServerPort;
		
		private static final int NEW_MESSAGE = 0;
		private static final int CONTINUE_MESSAGE = 1;
		
		/** incompleted message collections, for the case that a message had to be splitted up into different messages, to fit size requirements*/
		private HashMap<Integer, InMessageCollection> 	mMessageCollections = new HashMap<>();
		
		public ServerConnection(Socket socket, int serverPort) {
			mSocket = socket;
			mServerPort = (short)serverPort;
		}
		public void run() {
			try {				
				final InputStream is = mSocket.getInputStream();
				byte[] buffer = new byte[1024];
				SocketAddress adr = mSocket.getRemoteSocketAddress();
				int state = NEW_MESSAGE;
				int bytesRead = 0;
				InMsg msg = null;
				boolean socketAlive = true;
				int reqRead = NetMessagees.HEADER_LENGTH;
				while(mAlive && socketAlive) {
					try {
						while(bytesRead <= reqRead) {
							try {
								int tmp = is.read(buffer, bytesRead, buffer.length-bytesRead);
								if (tmp < 0)
									throw new SocketException("read negative length");
								bytesRead += tmp;
							}catch(SocketException e) {
								if (mAlive)
									System.out.println("Connection has been reseted by remote host");
								socketAlive = false;
								mSocket.close();
								InetSocketAddress isa = (InetSocketAddress)adr;
								mCommunicator.closeConnections(new RemoteCommunicator(isa.getAddress(), mServerPort, PROTOCOL.TCP));
								break;
							}
						}
						if (bytesRead > 0) {
							if (bytesRead > (2*buffer.length)){
								System.out.println("Misread some values");
								break;
							}
							switch(state) {
							case NEW_MESSAGE:{
								msg = new InMsg(buffer, bytesRead);
								bytesRead = 0;
								break;
							}
							case CONTINUE_MESSAGE:
							{
								assert(msg != null);
								byte[] remaining = msg.append(buffer, bytesRead);
								if (remaining != null) {
									System.arraycopy(remaining, 0, buffer, 0, remaining.length);
									bytesRead = remaining.length;
								}else
									bytesRead = 0;
								break;
							}
							default:
								break;
							}
							
							if (msg.complete()) {
								handleNetMessage(msg);
								msg = null;
								reqRead = NetMessagees.HEADER_LENGTH;
								state = NEW_MESSAGE;
							}else{
								state = CONTINUE_MESSAGE;
								reqRead = 0;
							}
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		private void handleNetMessage(InMsg msg) throws IOException {
			Integer key = Integer.valueOf(msg.messageMagic);
			InMessageCollection mc;
			if (mMessageCollections.containsKey(key)) {
				mc = mMessageCollections.get(key);
				mc.append(msg);
			}else {
				mMessageCollections.put(key, mc = new InMessageCollection(msg, mSocket.getInetAddress()));
			}
			
			if ((msg.flags & NetMessagees.REQUEST_ACKNOWLEDGE) == NetMessagees.REQUEST_ACKNOWLEDGE){
				NetMessagees.acknowledgeMessage(mSocket, msg.messageMagic, msg.messageNumber);
			}
			
			
			if (mc.isComplete()) {
				mc = mMessageCollections.remove(key);
				mc.complete();
				//This call will handle the message (may in another thread)
				mCommunicator.handleIncomingMessage(mc);
			}
		}
	}
	
	private final Options					mOptions;
	
	private boolean 						mAlive = true;
	private Map<ServerConnection, Thread>	mWorkerMap = new HashMap<>();
	private ServerRunnable					mServerRunnable = null;
	private Thread							mServerThread = null;
	private RemoteCommunicator				mResponseAddress;
	private Communicator 					mCommunicator;
	
	
	public TCPConnections(final Options opt) {
		mOptions = opt;
	}
	
	public void start(Communicator communicator) {
		mCommunicator = communicator;
		String srn = mOptions.name + "_" + "SR";
		mServerThread = new Thread(mServerRunnable = new ServerRunnable(), srn);
		mServerThread.start();
	}
	
	private void spawnWorker(Socket socket, InetAddress iNetAdr, int serverPort) {
		ServerConnection sc = new ServerConnection(socket, serverPort);
		Thread t = new Thread(sc, "Incoming-TCP-Connection-"+iNetAdr.toString()+":"+serverPort);
		mWorkerMap.put(sc, t);
		t.start();
	}

	public void waitForTermination() throws InterruptedException {
		if (mServerThread == null)
			return ;
		mServerThread.interrupt();
		mServerThread.join();
	}
	@Override
	public void stop() throws InterruptedException {
		mAlive = false;
		try {
			if (mServerRunnable != null && mServerRunnable.mSocketServer != null)
				mServerRunnable.mSocketServer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Entry<ServerConnection, Thread> e : mWorkerMap.entrySet()) {
			try {
				e.getKey().mSocket.close();
				e.getValue().join();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		waitForTermination();
	}


	public RemoteCommunicator getResponseAddress() {
		return mResponseAddress;
	}

	
	
	
}
