package de.sos.mora.stream;

import java.io.IOException;

import de.sos.mora.com.Encoding;
import de.sos.mora.list.BoolArrayList;
import de.sos.mora.list.ByteArrayList;
import de.sos.mora.list.CharArrayList;
import de.sos.mora.list.DoubleArrayList;
import de.sos.mora.list.FloatArrayList;
import de.sos.mora.list.IntArrayList;
import de.sos.mora.list.LongArrayList;
import de.sos.mora.list.ShortArrayList;
import de.sos.mora.list.StringArrayList;

public interface IMoraInputStream {

	int available() throws IOException;
	int read() throws IOException;
	
		
	default int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int c = read();
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = read();
                b[off + i] = (byte)c;
            }
        } catch (IOException ee) {
        }
        return i;
    }
	
	default boolean readBoolean() throws IOException {
		return read() == 1;
	}
	default byte readByte() throws IOException {
		return (byte)read();
	}
	default char readChar() throws IOException {
		byte[] data = new byte[2];
		read(data, 0, 2);
		return Encoding.decodeChar(data);
	}
	default short readShort() throws IOException {
		byte[] data = new byte[2];
		read(data, 0, 2);
		return Encoding.decodeShort(data);
	}
	default int readInteger() throws IOException {
		byte[] data = new byte[4];
		read(data, 0, 4);
		return Encoding.decodeInt(data);
	}
	default long readLong() throws IOException {
		byte[] data = new byte[8];
		read(data, 0, 8);
		return Encoding.decodeLong(data);
	}
	default float readFloat() throws IOException {
		byte[] data = new byte[4];
		read(data, 0, 4);
		return Encoding.decodeFloat(data);
	}
	default double readDouble() throws IOException {
		byte[] data = new byte[8];
		read(data, 0, 8);
		return Encoding.decodeDouble(data);
	}
	default String readString() throws IOException {
		int l = readInteger();
		String out = "";
		for (int i = 0; i < l; i++)
			out += readChar();
		return out;
	}
	
	
	
	default BoolArrayList readBooleanList() throws IOException { return new BoolArrayList(readBoolArray()); }
	default ByteArrayList readByteList() throws IOException { return new ByteArrayList(readByteArray()); }
	default CharArrayList readCharList() throws IOException { return new CharArrayList(readCharArray()); }
	default ShortArrayList readShortList() throws IOException { return new ShortArrayList(readShortArray()); }
	default IntArrayList readIntegerList() throws IOException { return new IntArrayList(readIntArray()); }
	default LongArrayList readLongList() throws IOException { return new LongArrayList(readLongArray()); }
	default FloatArrayList readFloatList() throws IOException { return new FloatArrayList(readFloatArray()); }
	default DoubleArrayList readDoubleList() throws IOException { return new DoubleArrayList(readDoubleArray()); }
	default StringArrayList readStringList() throws IOException { return new StringArrayList(readStringArray()); }
	
	default boolean[] readBoolArray() throws IOException {
		int size = readInteger();
		boolean[] out = new boolean[size];
		for (int i = 0; i < size; i++)
			out[i] = readBoolean();
		return out;
	}
	default byte[] readByteArray() throws IOException {
		int size = readInteger();
		byte[] out = new byte[size];
		for (int i = 0; i < size; i++)
			out[i] = readByte();
		return out;
	}
	default short[] readShortArray() throws IOException {
		int size = readInteger();
		short[] out = new short[size];
		for (int i = 0; i < size; i++)
			out[i] = readShort();
		return out;
	}
	default char[] readCharArray() throws IOException {
		int size = readInteger();
		char[] out = new char[size];
		for (int i = 0; i < size; i++)
			out[i] = readChar();
		return out;
	}
	default int[] readIntArray() throws IOException {
		int size = readInteger();
		int[] out = new int[size];
		for (int i = 0; i < size; i++)
			out[i] = readInteger();
		return out;
	}
	default long[] readLongArray() throws IOException {
		int size = readInteger();
		long[] out = new long[size];
		for (int i = 0; i < size; i++)
			out[i] = readLong();
		return out;
	}
	default float[] readFloatArray() throws IOException {
		int size = readInteger();
		float[] out = new float[size];
		for (int i = 0; i < size; i++)
			out[i] = readFloat();
		return out;
	}
	
	default double[] readDoubleArray() throws IOException {
		int size = readInteger();
		double[] out = new double[size];
		for (int i = 0; i < size; i++)
			out[i] = readDouble();
		return out;
	}
	
	default String[] readStringArray() throws IOException {
		int size = readInteger();
		String[] out = new String[size];
		for (int i = 0; i < size; i++)
			out[i] = readString();
		return out;
	}	

}