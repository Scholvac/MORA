package de.sos.mora;

import java.net.InetAddress;

import de.sos.mora.com.Encoding;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.exceptions.MoraInvalidAddressException;

public class RemoteObjects {
	
	public static class RemoteCommunicator {

		public final InetAddress		address;
		public final int				port;
		public final PROTOCOL			protocol;
		
		private String					mIdentifier;
		
		public RemoteCommunicator(InetAddress address, int port, PROTOCOL protocol) {
			super();
			this.address = address;
			this.port = port;
			this.protocol = protocol;
		}
		
		public String getIdentifier() {
			if (mIdentifier == null)
				mIdentifier = protocol.name().toLowerCase() + "://" + address.getHostAddress() + ":" + port;
			return mIdentifier;
		}
		
		
		@Override
		public String toString() {
			return getIdentifier();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof RemoteCommunicator)
				return getIdentifier().equals(((RemoteCommunicator)obj).getIdentifier());
			return false;
		}
		@Override
		public int hashCode() {
			return getIdentifier().hashCode();
		}
		
		public static RemoteCommunicator createRemoteCommunicator(final String adr) throws MoraInvalidAddressException {
			int idx = adr.indexOf("//");
			if (idx < 0) 
				return null;
			int idx2 = adr.indexOf(':', idx);
			if (idx2 < 0)
				return null;
			final String prot = adr.substring(0, idx-1);
			final String host = adr.substring(idx+2, idx2);
			final String port = adr.substring(idx2+1);
			try {
				return new RemoteCommunicator(InetAddress.getByName(host), Integer.parseInt(port), PROTOCOL.valueOf(prot.toUpperCase()));
			}catch(Exception e) {
				throw new MoraInvalidAddressException("Failed to parse remote communicator from: " + adr, e);
			}
		}
	}

	public static class RemoteObject {
		public final RemoteCommunicator		communicator;
		public final String					objectIdentifier;
		
		private String						mIdentifier;
		
		public RemoteObject(InetAddress address, int port, PROTOCOL protocol, final String objId) {
			this(new RemoteCommunicator(address, port, protocol), objId);
		}

		public RemoteObject(final RemoteCommunicator rc, final String objId) {
			communicator = rc;
			objectIdentifier = objId;
		}
		
		public final String getIdentifier() {
			if (mIdentifier == null)
				mIdentifier = communicator.getIdentifier() + "/" + objectIdentifier;
			return mIdentifier;
		}
		
		@Override
		public String toString() {
			return getIdentifier();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof RemoteCommunicator)
				return getIdentifier().equals(((RemoteCommunicator)obj).getIdentifier());
			return false;
		}
		@Override
		public int hashCode() {
			return getIdentifier().hashCode();
		}
		
		public static RemoteObject createRemoteObject(final String adr) throws MoraInvalidAddressException {
			int idx = adr.lastIndexOf('/');
			if (idx < 0)
				return null;
			final RemoteCommunicator rc = RemoteCommunicator.createRemoteCommunicator(adr.substring(0, idx));
			return new RemoteObject(rc, adr.substring(idx+1));
		}
	}

	public static class RemoteMethod {
		public final RemoteObject			instance;
		public final String					methodSignature;
		
		private String						mIdentifier;
		private byte[]						mStreamContent;
		
		public RemoteMethod(final RemoteObject inst, final String sig) {
			instance = inst;
			methodSignature = sig;
		}
		public RemoteMethod(InetAddress address, int port, PROTOCOL protocol, final String objId, final String sig) {
			this(new RemoteObject(address, port, protocol, objId), sig);
		}
		
		public String getIdentifier() {
			if (mIdentifier == null)
				mIdentifier = instance.getIdentifier() + ":" + methodSignature;
			return mIdentifier;
		}
		
		@Override
		public String toString() {
			return getIdentifier();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof RemoteCommunicator)
				return getIdentifier().equals(((RemoteCommunicator)obj).getIdentifier());
			return false;
		}
		@Override
		public int hashCode() {
			return getIdentifier().hashCode();
		}
		public byte[] getStreamContent() {
			if (mStreamContent == null) {
				mStreamContent = Encoding.encodeString(instance.objectIdentifier + ":" + methodSignature);
			}
			return mStreamContent;
		}
	}
	
	public static class TopicMethod {
		public final String			topic;
		public final String			methodSignature;
		
		private byte[]				mStreamContent;
		
		public TopicMethod(final String _topic, final String _methodSignature) {
			super();
			topic = _topic;
			methodSignature = _methodSignature;
		}

		public byte[] getStreamContent() {
			if (mStreamContent == null) {
				mStreamContent = Encoding.encodeString(topic+ ":" + methodSignature);
			}
			return mStreamContent;
		}
		
		
	}

}
