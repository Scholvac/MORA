package de.sos.mora.stream;


import java.io.IOException;
import java.util.List;

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

public interface IMoraOutputStream {

	void write(int b) throws IOException;
	void write(byte[] b) throws IOException;
	int available() throws IOException;
	
	
	
	default void writeBoolean(boolean b) throws IOException {
		write((byte)(b?1:0));
	}
	default void writeByte(byte b) throws IOException{
		write(b);
	}
	default void writeChar(char s) throws IOException {
		write(Encoding.encodeChar(s));
	}
	default void writeShort(short s) throws IOException {
		write(Encoding.encodeShort(s));
	}
	default void writeInteger(int i) throws IOException {
		write(Encoding.encodeInt(i));
	}
	default void writeLong(long l) throws IOException {
		write(Encoding.encodeLong(l));
	}
	default void writeFloat(float f) throws IOException {
		write(Encoding.encodeFloat(f));
	}
	default void writeDouble(double d) throws IOException {
		write(Encoding.encodeDouble(d));
	}
	default void writeString(String s) throws IOException {
		final int l = s.length();
		char[] data = new char[l];
		s.getChars(0, l, data, 0);
		writeChar(data, l);		
	}
	
	
	
	
	default void writeBoolean(List<Boolean> b) throws IOException {
		int l = b != null ? b.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeBoolean(b.get(i));
	}
	default void writeByte(List<Byte> b) throws IOException{
		int l = b != null ? b.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeByte(b.get(i));
	}
	default void writeChar(List<Character> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeChar(list.get(i));
	}
	default void writeShort(List<Short> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeShort(list.get(i));
	}
	default void writeInteger(List<Integer> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeInteger(list.get(i));
	}
	default void writeLong(List<Long> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeLong(list.get(i));
	}
	default void writeFloat(List<Float> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeFloat(list.get(i));
	}
	default void writeDouble(List<Double> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeDouble(list.get(i));
	}
	default void writeString(List<String> list) throws IOException {
		int l = list != null ? list.size() : 0;
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeString(list.get(i));
	}
	
	
	
	default void writeBoolean(BoolArrayList b) throws IOException { writeBoolean(b._getBuffer(), b.size()); }
	default void writeByte(ByteArrayList b) throws IOException{ writeByte(b._getBuffer(), b.size()); }
	default void writeChar(CharArrayList s) throws IOException { writeChar(s._getBuffer(), s.size()); }
	default void writeShort(ShortArrayList s) throws IOException { writeShort(s._getBuffer(), s.size()); }
	default void writeInteger(IntArrayList i) throws IOException { writeInteger(i._getBuffer(), i.size()); }
	default void writeLong(LongArrayList l) throws IOException { writeLong(l._getBuffer(), l.size()); }
	default void writeFloat(FloatArrayList f) throws IOException { writeFloat(f._getBuffer(), f.size()); }
	default void writeDouble(DoubleArrayList d) throws IOException { writeDouble(d._getBuffer(), d.size()); }
	default void writeString(StringArrayList s) throws IOException { writeString(s._getBuffer(), s.size()); }
	
	
	default void writeBoolean(boolean[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeBoolean(list[i]);
	}
	default void writeByte(byte[] list, int l) throws IOException{
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeByte(list[i]);
	}
	default void writeChar(char[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeChar(list[i]);
	}
	default void writeShort(short[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeShort(list[i]);
	}
	default void writeInteger(int[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeInteger(list[i]);
	}
	default void writeLong(long[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeLong(list[i]);
	}
	default void writeFloat(float[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeFloat(list[i]);
	}
	default void writeDouble(double[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeDouble(list[i]);
	}
	default void writeString(String[] list, int l) throws IOException {
		writeInteger(l);
		for (int i = 0; i < l; i++)
			writeString(list[i]);
	}
}