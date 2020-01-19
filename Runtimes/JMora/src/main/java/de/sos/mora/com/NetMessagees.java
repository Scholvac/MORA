package de.sos.mora.com;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;
import de.sos.mora.com.MoraMessages.OutMessage;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.MoraInputStream;

public class NetMessagees {
	

	
	public static final int HEADER_LENGTH = 10;
	
	public static final int REQUEST_ACKNOWLEDGE = 1;

	public static final byte ACKNOWLEDGE_MESSAGE = 0xA;

	
	public static class OutMsg {
		public byte[] buffer;
		
		OutMsg(short numberOfMessages, short messageNumber, byte messageMagic, final byte[] data, int length) {
			super();
			byte flags = 0;
			
			buffer = new byte[length + NetMessagees.HEADER_LENGTH];
			Encoding.encodeByte(messageMagic, buffer, 0);
			Encoding.encodeInt(length, buffer, 1);
			Encoding.encodeByte(flags, buffer, 5);
			Encoding.encodeShort(numberOfMessages, buffer, 6);
			Encoding.encodeShort(messageNumber, buffer, 8);
			
			System.arraycopy(data, 0, buffer, NetMessagees.HEADER_LENGTH, length);
		}
		public byte[] getBuffer() { return buffer;}
		
		public void requestAcknowledge(boolean requestAcknowledge) {
			if (requestAcknowledge)
				buffer[5] |= NetMessagees.REQUEST_ACKNOWLEDGE;
		}

		public void writeToStream(OutputStream stream) throws IOException {
			stream.write(buffer, 0, buffer.length);
		}
		public boolean checkResponse(byte[] mAckBuffer) {
			if (	mAckBuffer[0] != NetMessagees.ACKNOWLEDGE_MESSAGE ||  
					mAckBuffer[1] != buffer[0] ||
					mAckBuffer[2] != buffer[8] ||
					mAckBuffer[3] != buffer[9]
				) 
			{
				return false;
			}
			return true;
		}

		
	}

	public static class InMsg {
		
		public byte					flags = 0;
		
		public final short			numberOfMessages;
		public final short			messageNumber;
		public final byte			messageMagic;
		public final int			length;
		public final byte[]			data;
		
		private int					pos = -1;
		
		InMsg(final byte[] buffer, final int dataLength) {
			super();
			int offset = 0;
			assert(buffer.length > HEADER_LENGTH);
			messageMagic = Encoding.decodeByte(buffer, offset); 
			offset += Encoding.BYTE_LENGTH;
			length = Encoding.decodeInt(buffer, offset);
			offset += Encoding.INT_LENGTH;
			flags = Encoding.decodeByte(buffer, offset);
			offset += Encoding.BYTE_LENGTH;
			numberOfMessages = Encoding.decodeShort(buffer, offset);
			offset += Encoding.SHORT_LENGTH;
			messageNumber = Encoding.decodeShort(buffer, offset);
			offset += Encoding.SHORT_LENGTH;
			this.data = new byte[length];
			pos = dataLength-offset;
			System.arraycopy(buffer, offset, this.data, 0, pos);			
		}
		
		public byte[] append(final byte[] buffer, final int bufferLength) { //returns the remaining bytes or null if no bytes remain
			int remainig = length - pos;
			if (bufferLength > remainig) {
				System.arraycopy(buffer, 0, data, pos, remainig);
				pos += remainig;
				return Arrays.copyOfRange(buffer, remainig, bufferLength);
			}else {
				System.arraycopy(buffer, 0, data, pos, bufferLength);
				pos += bufferLength;
				return null;
			}
		}

		public boolean complete() {
			return pos == data.length;
		}
	}
	
	
	
	public static class InMessageCollection implements IMoraInputStream {
		private final InetAddress	mSourceAddress;
		private final int 			mMagic;
		private final InMsg[] 		mMessages;
		private int 				mMessageCounter = 0;
		
		private int					mOverallLength;
		private int					mOverallPos;
		
		private int					mMsgPos = 0;
		private int					mInMsgPos = 0;
		
		public MoraInputStream stream;
		
		public InMessageCollection(final InMsg msg, final InetAddress srcAdr) {
			mMagic = msg.messageMagic;
			mSourceAddress = srcAdr;
			mMessages = new InMsg[msg.numberOfMessages];
			mMessages[msg.messageNumber] = msg;
			mMessageCounter++;
			mOverallLength = msg.length;
		}
		
		public int getMagic() { return mMagic; }
		
		public void append(final InMsg msg) throws IOException {
			if (msg.messageMagic != mMagic)
				throw new IOException("Message Magic does not match");
			if (mMessages[msg.messageNumber] == null) {
				mMessages[msg.messageNumber] = msg;
				mMessageCounter++;
				mOverallLength+=msg.length;
			}			
		}
		
		public boolean isComplete() {
			return mMessageCounter == mMessages.length;
		}
		
		@Override
		public int read() throws IOException {
			final InMsg msg = mMessages[mMsgPos];
			int val = msg.data[mInMsgPos++];
			if (mInMsgPos >= msg.length) {
				mMsgPos++;
				mInMsgPos = 0;
			}
			mOverallPos++;
			
			return val;
		}
		
		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			if (len < 16)
				return IMoraInputStream.super.read(b, off, len);
			int lengthToRead = Math.min(available(), len);
			int outOffset = off;
			int read = 0;
			for (; mMsgPos < mMessages.length; ) {
				final InMsg msg = mMessages[mMsgPos];
				int toReadInMsg = Math.min(lengthToRead, msg.data.length - mInMsgPos);
				System.arraycopy(msg.data, mInMsgPos, b, outOffset, toReadInMsg);
				mInMsgPos += toReadInMsg;
				mOverallPos += toReadInMsg;
				outOffset += toReadInMsg;
				read += toReadInMsg;
				if (mInMsgPos >= msg.length) {
					mInMsgPos = 0;
					mMsgPos++;
				}
			}
			return read;
		}
		
		@Override
		public int available() throws IOException {
			return mOverallLength - mOverallPos;
		}

		public InetAddress getResponseAddress() {
			return mSourceAddress;
		}

		public InMsg getMessage(int i) {
			return mMessages[i];
		}

		public void complete() {
			MoraInputStream is = new MoraInputStream(mMessages[0].data);
			for (int i = 1; i < mMessages.length; i++)
				is.append(mMessages[i].data, 0, mMessages[i].data.length);
			stream = is;
		}
		
		public IMoraInputStream getStream() { return stream;}
	}
	
	public static List<OutMsg> encode(final OutMessage data, final int maxMsgSize) {
		return encode(data.toByteArray(), data.size(), maxMsgSize);
	}
	public static List<OutMsg> encode(final byte[] data, final int length, final int maxMsgSize) {
		final byte magic = Common.generateByteMagic();
		if (maxMsgSize - HEADER_LENGTH > length) {
			OutMsg msg = new OutMsg((short)1, (short)0, magic, data, length);
			return Arrays.asList(msg);
		}else {
			int mms = maxMsgSize-HEADER_LENGTH;
			int numMsg = (length / mms)+1;			
//			int remaining = length;
			List<OutMsg> res = new ArrayList<NetMessagees.OutMsg>();
			for (int i = 0; i < numMsg; i++) {
				int from = i*mms;
				int to = from+mms;
				if (to > length)
					to = length;
				byte[] copy = Arrays.copyOfRange(data, from, to);
//				to = from+Math.min(remaining, mms);
				res.add(new OutMsg((short)numMsg, (short)i, magic, copy, to-from));
//				remaining -= mms;
			}
			return res;
		}
	}

	public static void acknowledgeMessage(Socket socket, byte messageMagic, short messageNumber) throws IOException {
		byte[] mn = Encoding.encodeShort(messageNumber);
		socket.getOutputStream().write(new byte[] {ACKNOWLEDGE_MESSAGE, messageMagic, mn[0], mn[1]});
	}
}
