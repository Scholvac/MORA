package de.sos.mora.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class MoraOutputStream extends OutputStream implements IMoraOutputStream{

	private static final int 			MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	byte[]								mBytes = new byte[64];
	int									mCount = 0;
	

	public MoraOutputStream() {
		
	}
	
	
	@Override
	public void write(int b) throws IOException {
		ensureCapacity(mCount + 1);
		mBytes[mCount] = (byte) b;
		mCount += 1;
	}
	 /**
     * Writes <code>len</code> bytes from the specified byte array
     * starting at offset <code>off</code> to this byte array output stream.
     *
     * @param   b     the data.
     * @param   off   the start offset in the data.
     * @param   len   the number of bytes to write.
     */
    public synchronized void write(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(mCount + len);
        System.arraycopy(b, off, mBytes, mCount, len);
        mCount += len;
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
	public void write(byte[] data) throws IOException {
		write(data, 0, data.length);
	}
	@Override
	public int available() throws IOException {
		return 0;
	}

	public byte[] toByteArray() {
		return mBytes;
	}
	public int size() { return mCount;}
	
	
	
	public void writeBoolean(boolean b) throws IOException {
		write((byte)(b?1:0));
	}
	public void writeByte(byte b) throws IOException{
		ensureCapacity(mCount + 1);
		mBytes[mCount] = b;
		mCount += 1;
	}
	public void writeChar(char s) throws IOException {
		ensureCapacity(mCount+2);
		mBytes[mCount++] = (byte)((s >>> 8) & 0xFF);
		mBytes[mCount++] = (byte)((s >>> 0) & 0xFF);
	}
	public void writeShort(short s) throws IOException {
		ensureCapacity(mCount+2);
		mBytes[mCount++] = (byte)((s >>> 8) & 0xFF);
		mBytes[mCount++] = (byte)((s >>> 0) & 0xFF);
	}
	public void writeInteger(int i) throws IOException {
		ensureCapacity(mCount+4);
		mBytes[mCount++] = (byte)((i >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((i >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((i >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((i >>> 0 ) & 0xFF);
	}
	public void writeLong(long l) throws IOException {
		ensureCapacity(mCount+8);
		mBytes[mCount++] = (byte)((l >>> 56) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 48) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 40) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 32) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
	}
	public void writeFloat(float f) throws IOException {
		writeInteger(Float.floatToIntBits(f));
	}
	public void writeDouble(double d) throws IOException {
		writeLong(Double.doubleToLongBits(d));
	}
	
	
	
	
	
	
	public void writeBoolean(List<Boolean> b) throws IOException {
		int l = b.size();
		ensureCapacity(mCount+l+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = (byte) (b.get(i) ? 1 : 0);
		}
	}
	public void writeByte(List<Byte> b) throws IOException{
		int l = b.size();
		ensureCapacity(mCount+l+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = b.get(i);
		}
	}
	public void writeChar(List<Character> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*2+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			char v = list.get(i);
			mBytes[mCount++] = (byte)((v >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0) & 0xFF);
		}
	}
	public void writeShort(List<Short> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*2+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			short v = list.get(i);
			mBytes[mCount++] = (byte)((v >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0) & 0xFF);
		}
	}
	public void writeInteger(List<Integer> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*4+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			int v = list.get(i);
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeLong(List<Long> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*8+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			long v = list.get(i);
			mBytes[mCount++] = (byte)((v >>> 56) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 48) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 40) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 32) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeFloat(List<Float> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*4+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			int v = Float.floatToIntBits(list.get(i));				
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeDouble(List<Double> list) throws IOException {
		int l = list.size();
		ensureCapacity(mCount+l*8+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			long v = Double.doubleToLongBits(list.get(i));
			mBytes[mCount++] = (byte)((v >>> 56) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 48) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 40) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 32) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeString(List<String> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeString(list.get(i));
	}
	
	
	
	
	public void writeBoolean(boolean[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+4+l);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		for (int i = 0; i < l; i++)
			mBytes[mCount++] = (byte) (list[i] ? 1 : 0);
	}
	public void writeByte(byte[] list) throws IOException{
		int l = list.length;
		ensureCapacity(mCount+4+l);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		for (int i = 0; i < l; i++)
			mBytes[mCount++] = (byte) (list[i]);
	}
	public void writeChar(char[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+4+l*2);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = (byte)((list[i] >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 0) & 0xFF);
		}
	}
	public void writeShort(short[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+4+l*2);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = (byte)((list[i] >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 0) & 0xFF);
		}
	}
	public void writeInteger(int[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+4+l*4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = (byte)((list[i] >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 0) & 0xFF);
		}
	}
	public void writeLong(long[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+4+l*8);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			mBytes[mCount++] = (byte)((list[i] >>> 56) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 48) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 40) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 32) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 8) & 0xFF);
			mBytes[mCount++] = (byte)((list[i] >>> 0) & 0xFF);
		}
	}
	public void writeFloat(float[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+l*4+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			int v = Float.floatToIntBits(list[i]);				
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeDouble(double[] list) throws IOException {
		int l = list.length;
		ensureCapacity(mCount+l*8+4);
		mBytes[mCount++] = (byte)((l >>> 24) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 16) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 8 ) & 0xFF);
		mBytes[mCount++] = (byte)((l >>> 0 ) & 0xFF);
		
		for (int i = 0; i < l; i++) {
			long v = Double.doubleToLongBits(list[i]);
			mBytes[mCount++] = (byte)((v >>> 56) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 48) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 40) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 32) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 24) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 16) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 8 ) & 0xFF);
			mBytes[mCount++] = (byte)((v >>> 0 ) & 0xFF);
		}
	}
	public void writeString(String[] list) throws IOException {
		int l = list.length;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeString(list[i]);
	}
}
