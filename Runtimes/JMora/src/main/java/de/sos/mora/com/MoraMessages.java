package de.sos.mora.com;

import java.io.IOException;

import de.sos.mora.RemoteObjects.RemoteCommunicator;
import de.sos.mora.RemoteObjects.RemoteMethod;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.com.NetMessagees.InMessageCollection;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.stream.MoraOutputStream;

public class MoraMessages {

	public static final int				RESPONSE_STATUS__EXCEPTION = 1;


	public static final byte 			MESSAGE_TYPE__METHOD_CALL = 1;
	public static final byte 			MESSAGE_TYPE__RESPONSE = 2;
	public static final byte 			MESSAGE_TYPE__TOPIC = 4;
	

	
	
	public static abstract class MoraMessage {
		public final short				mMagic;
		public final byte				mMessageType;
		
		public MoraMessage(final short magic, final byte type) {
			mMagic = magic;
			mMessageType = type;
		}
		
		public short getMagic() {
			return mMagic;
		}
	}
	
	public static abstract class InMessage extends MoraMessage {
		private final InMessageCollection	mNetworkMessage;
		
		
		public InMessage(short magic, byte type, final InMessageCollection mc) throws IOException {
			super(magic, type);
			
			mNetworkMessage = mc;
		}
		
		public IMoraInputStream getStream() { return mNetworkMessage.getStream(); }
	}
	
	public static abstract class OutMessage extends MoraMessage {
		private final RemoteCommunicator	mTargetCommunicator;
		public final short					mMagic;
		
		public final MoraOutputStream		stream;

		public OutMessage(short magic, byte type, final RemoteCommunicator targetCom) throws IOException {
			super(magic, type);		
			
			mMagic = magic;
			mTargetCommunicator = targetCom;
			
			stream = new MoraOutputStream();
			stream.writeByte(mMessageType);
			stream.writeShort(mMagic);
		}
		
		public IMoraOutputStream getStream() { return stream; }
		
		public short getMagic() { return mMagic; }

		public RemoteCommunicator getTarget() { return mTargetCommunicator; }
		public byte[] toByteArray() {
			return stream.toByteArray();
		}

		public int size() {
			return stream.size();
		}
	}
	
	
	
	
	public static class InMethodCall extends InMessage {
		public final String					objectIdentifier;
		public final String					methodSignature;
		
		public final RemoteCommunicator 	responseCommunicator;
		
		public InMethodCall(short magic, byte type, InMessageCollection mc) throws IOException {
			super(magic, type, mc);
						
			//messageType and magic has been read already
			final byte respProt = mc.stream.readByte();
			final int respPort = mc.stream.readInteger();
			//now we can build the response communicator
			responseCommunicator = new RemoteCommunicator(mc.getResponseAddress(), respPort, PROTOCOL.valueOf(respProt));
			
			final String identifier = mc.stream.readString();
			int idx = identifier.lastIndexOf(':');
			objectIdentifier = identifier.substring(0, idx);
			methodSignature = identifier.substring(idx+1);
		}
		
		public RemoteCommunicator getResponseAddress() { return responseCommunicator; }
	}
	
	public static class OutMethodCall extends OutMessage {

		private final RemoteCommunicator	mResponseCommunicator;
		public final RemoteMethod			mRemoteMethod;
		
		public OutMethodCall(final RemoteMethod method, short magic, final RemoteCommunicator respCom) throws IOException {
			super(magic, MESSAGE_TYPE__METHOD_CALL, method.instance.communicator);
			
			mRemoteMethod = method;
			mResponseCommunicator = respCom;
			
			stream.writeByte(PROTOCOL.byteValue(respCom.protocol));
			stream.writeInteger(respCom.port);
			
			stream.write(mRemoteMethod.getStreamContent());
				
		}		
	}
	
	
	
	public static class OutMethodRespond extends OutMessage {
		public byte					statusFlag;
		public MoraException		exception;
		
		public OutMethodRespond(InMethodCall call) throws IOException {
			this(call, null);
		}
		
		public OutMethodRespond(InMethodCall call, final Throwable t) throws IOException {
			super(call.mMagic, MESSAGE_TYPE__RESPONSE, call.responseCommunicator);
			if (t != null) {
				stream.writeByte((byte) RESPONSE_STATUS__EXCEPTION);
				if (t instanceof MoraException)
					stream.writeString(((MoraException)t).getQualifiedException());
				else
					stream.writeString(new MoraException(t).getQualifiedException());
			}else
				stream.writeByte((byte)0); //status
		}
	}
	public static class InMethodRespond extends InMessage {
		public final byte				statusFlag;
		public final MoraException		exception;
		
		public InMethodRespond(short magic, byte type, InMessageCollection mc) throws IOException {
			super(magic, type, mc);
			
			statusFlag = mc.stream.readByte();
			if ((statusFlag & RESPONSE_STATUS__EXCEPTION) == RESPONSE_STATUS__EXCEPTION) {
				final String exMsg = mc.stream.readString();
				this.exception = new MoraException(exMsg);
			}else
				this.exception = null;
//				int idx = exMsg.indexOf(":");
//				if (idx < 0)
//					throw new IOException("Could not parse exception message");
//				final String expClassName = exMsg.substring(0, idx);
//				final String msg = exMsg.substring(idx+1);
//				MoraException newExept = null;
//				try {
//					newExept = (MoraException) MoraException.class.forName(expClassName).newInstance();
//					newExept.setMessage(msg);
//				}catch(Exception e) {
//					newExept = new MoraException(msg);
//				}
//				this.exception = newExept;
//			}else
//				this.exception = null;
		}

		public boolean isError() {
			return (statusFlag & RESPONSE_STATUS__EXCEPTION) == RESPONSE_STATUS__EXCEPTION;
		}
	}
	
	

	
	
	
	
	
	
	
}
