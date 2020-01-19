package de.sos.mora.com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import de.sos.mora.RemoteObjects.RemoteCommunicator;
import de.sos.mora.com.ClientConnections.AbstractConnection;
import de.sos.mora.com.Communicator.Options;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.com.NetMessagees.InMessageCollection;
import de.sos.mora.com.NetMessagees.InMsg;
import de.sos.mora.com.NetMessagees.OutMsg;

public class UDPConnections implements ISocketServer{

	public static class UDPConnection extends AbstractConnection {
		
		
		private RemoteCommunicator mTarget;
		private DatagramSocket mServerSocket;
		private DatagramPacket mPacket;

		public UDPConnection(RemoteCommunicator target, int index) throws IOException {
			super(index);
			mTarget = target;
			mServerSocket = new DatagramSocket();
			mPacket = new DatagramPacket(new byte[] {0}, 1, mTarget.address, mTarget.port);
		}

		
		@Override
		public int getMaximumMessageSize() {
			return 64000;
		}
		@Override
		protected boolean send(OutMsg outMsg) {
			try {				
				mPacket.setData(outMsg.getBuffer());
				mServerSocket.send(mPacket);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@Override
		public void close() {
			try {
				mServerSocket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private class ServerRunnable implements Runnable{
		public int 			mUsedPort;
		public InetAddress	mUsedHost;
		
		private static final int NEW_MESSAGE = 0;
		private static final int CONTINUE_MESSAGE = 1;
		
		
		DatagramSocket		mSocketServer;
		private boolean mAlive;
		public void run() {
			try {
				mSocketServer = new DatagramSocket(mOptions.port < 0 ? 0 : mOptions.port, mOptions.adr);
								
				
				mUsedHost = mSocketServer.getLocalAddress();
				mUsedPort = mSocketServer.getLocalPort();
				mResponseAddress = new RemoteCommunicator(mUsedHost, mUsedPort, PROTOCOL.UDP);
				System.out.println("Start UDP Server at: " + mResponseAddress);

				mAlive = true;
				byte[] receiveData = new byte[64000]; //64000 == theoretical maximum size for an udp socket package				
				while(mAlive = true) {
					int bytesRead = 0;
					int state = NEW_MESSAGE;
					InMsg msg = null;
					InetAddress senderIP = null;
					while(bytesRead <= NetMessagees.HEADER_LENGTH) {
						DatagramPacket receivedPacket = new DatagramPacket(receiveData, bytesRead, receiveData.length);
						mSocketServer.receive(receivedPacket);
						
						bytesRead += receivedPacket.getLength();
						senderIP = receivedPacket.getAddress();
					}
					if (bytesRead > 0) {
						switch(state) {
						case NEW_MESSAGE: {
							msg = new InMsg(receiveData, bytesRead);
							bytesRead = 0;
							break;
						}
						case CONTINUE_MESSAGE:{
							byte[] remaining = msg.append(receiveData, bytesRead);
							if (remaining != null) {
								System.arraycopy(remaining, 0, receiveData, 0, remaining.length);
								bytesRead = remaining.length;
							}else
								bytesRead = 0;
							break;
						}
						default:
							break;
						}
						
						if (msg.complete()) {
							handleNetMessage(msg, senderIP);
							state = NEW_MESSAGE;
						}else {
							state = CONTINUE_MESSAGE;
						}
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		/** incompleted message collections, for the case that a message had to be splitted up into different messages, to fit size requirements*/
		private HashMap<Integer, InMessageCollection> 	mMessageCollections = new HashMap<>();
		
		private void handleNetMessage(InMsg msg, InetAddress senderIP) throws IOException {
			Integer key = Integer.valueOf(msg.messageMagic);
			InMessageCollection mc;
			if (mMessageCollections.containsKey(key)) {
				mc = mMessageCollections.get(key);
				mc.append(msg);
			}else {
				mMessageCollections.put(key, mc = new InMessageCollection(msg, senderIP));
			}
			
			if ((msg.flags & NetMessagees.REQUEST_ACKNOWLEDGE) == NetMessagees.REQUEST_ACKNOWLEDGE){
				//NetMessagees.acknowledgeMessage(mSocket, msg.messageMagic, msg.messageNumber);
				System.out.println("TODO: SEND ACKNOWLEDGE");
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
	private ServerRunnable					mServerRunnable = null;
	private Thread							mServerThread = null;
	private RemoteCommunicator				mResponseAddress;
	private Communicator 					mCommunicator;
	
	
	public UDPConnections(final Options opt) {
		mOptions = opt;
	}
	
	public void start(Communicator communicator) {
		mCommunicator = communicator;
		mServerThread = new Thread(mServerRunnable = new ServerRunnable());
		mServerThread.start();
	}
	
	public void waitForTermination() throws InterruptedException {
		if (mServerThread == null)
			return ;
		mServerThread.join();
	}


	public RemoteCommunicator getResponseAddress() {
		return mResponseAddress;
	}

	@Override
	public void stop() throws InterruptedException {
		mServerRunnable.mAlive = false;
		waitForTermination();
	}
	
}
