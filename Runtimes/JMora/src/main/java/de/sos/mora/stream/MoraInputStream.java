package de.sos.mora.stream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import de.sos.mora.list.BoolArrayList;
import de.sos.mora.list.ByteArrayList;
import de.sos.mora.list.CharArrayList;
import de.sos.mora.list.DoubleArrayList;
import de.sos.mora.list.FloatArrayList;
import de.sos.mora.list.IntArrayList;
import de.sos.mora.list.LongArrayList;
import de.sos.mora.list.ShortArrayList;

public class MoraInputStream extends InputStream implements IMoraInputStream {
	private static final int 			MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	byte[] 	mBytes = null;
	int		mInputCount = 0;
	
	int 	mReadCounter = 0;
	
	public MoraInputStream(byte[] in) {
		mBytes = in;
		mInputCount = in.length;
	}
	
	public synchronized void append(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(mInputCount + len);
        System.arraycopy(b, off, mBytes, mInputCount, len);
        mInputCount += len;
    }
    private void ensureCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - mBytes.length > 0)
            grow(minCapacity);
    }
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = mBytes.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        mBytes = Arrays.copyOf(mBytes, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
	
	@Override
	public int read() throws IOException {
		return mBytes[mReadCounter++] & 0xff;
	}


	
	
	
	
	
	
	
	
	
	
	public boolean readBoolean() throws IOException {
		return mBytes[mReadCounter++] == 1;
	}
	public byte readByte() throws IOException {
		return mBytes[mReadCounter++];
	}
	public char readChar() throws IOException {
		int ch1 = mBytes[mReadCounter++] & 0xFF;
		int ch2 = mBytes[mReadCounter++] & 0xFF;
		return (char)((ch1 << 8) + (ch2 << 0));
	}
	public short readShort() throws IOException {
		int ch1 = mBytes[mReadCounter++] & 0xFF;
		int ch2 = mBytes[mReadCounter++] & 0xFF;
		return (short)((ch1 << 8) + (ch2 << 0));
	}
	public int readInteger() throws IOException {
		return (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
	}
	public long readLong() throws IOException {
		return (((long)mBytes[mReadCounter++] << 56) +
                ((long)(mBytes[mReadCounter++] & 255) << 48) +
                ((long)(mBytes[mReadCounter++] & 255) << 40) +
                ((long)(mBytes[mReadCounter++] & 255) << 32) +
                ((long)(mBytes[mReadCounter++] & 255) << 24) +
                ((mBytes[mReadCounter++] & 255) << 16) +
                ((mBytes[mReadCounter++] & 255) <<  8) +
                ((mBytes[mReadCounter++] & 255) <<  0));
	}
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInteger());
	}
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}
	public String readString() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		if (l == 0)
			return null;
		char[] chars = new char[l];
		for (int i = 0; i < l; i++) {
			int ch1 = mBytes[mReadCounter++] & 0xFF;
			int ch2 = mBytes[mReadCounter++] & 0xFF;
			chars[i] = (char)((ch1 << 8) + (ch2 << 0));
		}
		return new String(chars);
	}
	

	
	
	
	
	
	
	
	public BoolArrayList readBooleanList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		boolean[] array = new boolean[l];
		for (int i = 0; i < l; i++)
			array[i] = mBytes[mReadCounter++] == 1;
		return new BoolArrayList(array);
	}
	public ByteArrayList readByteList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		byte[] array = new byte[l];
		for (int i = 0; i < l; i++)
			array[i] = mBytes[mReadCounter++];
		return new ByteArrayList(array);
	}
	public CharArrayList readCharList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		char[] array = new char[l];
		for (int i = 0; i < l; i++) {
			int ch1 = mBytes[mReadCounter++] & 0xFF;
			int ch2 = mBytes[mReadCounter++] & 0xFF;
			array[i] = (char)((ch1 << 8) + (ch2 << 0));
		}
		return new CharArrayList(array);
	}
	public ShortArrayList readShortList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		short[] array = new short[l];
		for (int i = 0; i < l; i++) {
			int ch1 = mBytes[mReadCounter++] & 0xFF;
			int ch2 = mBytes[mReadCounter++] & 0xFF;
			array[i] = (short)((ch1 << 8) + (ch2 << 0));
		}
		return new ShortArrayList(array);
	}
	public IntArrayList readIntegerList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		int[] array = new int[l];
		for (int i = 0; i < l; i++) {
			array[i] = (int) (
					((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
					((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
					((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
					((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
					);
		}
		return new IntArrayList(array);
	}
	public LongArrayList readLongList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		long[] array = new long[l];
		for (int i = 0; i < l; i++) {
			array[i] = (((long)mBytes[mReadCounter++] << 56) +
	                ((long)(mBytes[mReadCounter++] & 255) << 48) +
	                ((long)(mBytes[mReadCounter++] & 255) << 40) +
	                ((long)(mBytes[mReadCounter++] & 255) << 32) +
	                ((long)(mBytes[mReadCounter++] & 255) << 24) +
	                ((mBytes[mReadCounter++] & 255) << 16) +
	                ((mBytes[mReadCounter++] & 255) <<  8) +
	                ((mBytes[mReadCounter++] & 255) <<  0));
		}
		return new LongArrayList(array);
	}
	public FloatArrayList readFloatList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		float[] array = new float[l];
		for (int i = 0; i < l; i++) {
			array[i] = Float.intBitsToFloat((int) (
					((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
					((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
					((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
					((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
					));
		}
		return new FloatArrayList(array);
	}
	public DoubleArrayList readDoubleList() throws IOException {
		int l = (int) (
				((int)((mBytes[mReadCounter++] & 0xFF) << 24)) + 
				((int)((mBytes[mReadCounter++] & 0xFF) << 16)) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 8 )) +
				((int)((mBytes[mReadCounter++] & 0xFF) << 0 ))
				);
		double[] array = new double[l];
		for (int i = 0; i < l; i++) {
			array[i] = Double.longBitsToDouble((((long)mBytes[mReadCounter++] << 56) +
	                ((long)(mBytes[mReadCounter++] & 255) << 48) +
	                ((long)(mBytes[mReadCounter++] & 255) << 40) +
	                ((long)(mBytes[mReadCounter++] & 255) << 32) +
	                ((long)(mBytes[mReadCounter++] & 255) << 24) +
	                ((mBytes[mReadCounter++] & 255) << 16) +
	                ((mBytes[mReadCounter++] & 255) <<  8) +
	                ((mBytes[mReadCounter++] & 255) <<  0)));
		}
		return new DoubleArrayList(array);
	}

}
