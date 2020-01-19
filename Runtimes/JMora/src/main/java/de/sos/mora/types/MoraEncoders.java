package de.sos.mora.types;

import java.io.IOException;
import java.util.List;

import de.sos.mora.com.Communicator;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;

public class MoraEncoders {
	
	public static final IMoraTypeEncoder<Boolean> BOOL_ENCODER = new IMoraTypeEncoder<Boolean>() {		
		@Override
		public void encode(Boolean _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeBoolean(_in_.booleanValue());
		}
		
		@Override
		public Boolean decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readBoolean();
		}
	};
	
	
	public static final IMoraTypeEncoder<Byte> BYTE_ENCODER = new IMoraTypeEncoder<Byte>() {		
		@Override
		public void encode(Byte _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeByte(_in_.byteValue());
		}
		
		@Override
		public Byte decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readByte();
		}
	};
	public static final IMoraTypeEncoder<Short> SHORT_ENCODER = new IMoraTypeEncoder<Short>() {		
		@Override
		public void encode(Short _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeShort(_in_.shortValue());
		}
		
		@Override
		public Short decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readShort();
		}
	};
	public static final IMoraTypeEncoder<Integer> INTEGER_ENCODER = new IMoraTypeEncoder<Integer>() {		
		@Override
		public void encode(Integer _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeInteger(_in_.intValue());
		}
		
		@Override
		public Integer decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readInteger();
		}
	};
	public static final IMoraTypeEncoder<Long> LONG_ENCODER = new IMoraTypeEncoder<Long>() {		
		@Override
		public void encode(Long _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeLong(_in_.longValue());
		}
		
		@Override
		public Long decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readLong();
		}
	};
	public static final IMoraTypeEncoder<Float> FLOAT_ENCODER = new IMoraTypeEncoder<Float>() {		
		@Override
		public void encode(Float _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeFloat(_in_.floatValue());
		}
		
		@Override
		public Float decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readFloat();
		}
	};
	public static final IMoraTypeEncoder<Double> DOUBLE_ENCODER = new IMoraTypeEncoder<Double>() {		
		@Override
		public void encode(Double _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			_stream_.writeDouble(_in_.doubleValue());
		}
		
		@Override
		public Double decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			return _stream_.readDouble();
		}
	};
	
	
	public static final IMoraTypeEncoder<String> STRING_ENCODER = new IMoraTypeEncoder<String>() {
		@Override
		public void encode(String _in_, IMoraOutputStream _stream_, Communicator ctx) throws IOException {
			int l = _in_.length();
			_stream_.writeInteger(l);
			for (int i = 0; i < l; i++)
				_stream_.writeChar(_in_.charAt(i));
		}
		
		@Override
		public String decode(IMoraInputStream _stream_, Communicator ctx) throws IOException {
			int l = _stream_.readInteger();
			String out = "";
			for (int i = 0; i < l; i++) {
				out += _stream_.readChar();				
			}
			return out;
		}
	};
	public static final byte STRUCT_END = (byte) 0xFF;
	
	public static int safe_compare(String s1, String s2) {
		if (s1 == null){
			if (s2 == null)
				return 0;
			else
				return 1;
		}else if (s2 == null){
			return -1;
		}
		return s1.compareTo(s2);
	}

	public static int safe_compare_Lists(final List l1, final List l2) {
		if (l1 == null) {
			if (l2 == null)
				return 0;
			else
				return 1;
		}else if (l2 == null)
			return -1;
		int length1 = l1.size(), length2 = l2.size();
		int comp = Integer.compare(length1, length2);
		return comp;
	}
	public static int safe_compare_BooleanList(List<Boolean> l1, List<Boolean> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Boolean.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_ByteList(List<Byte> l1, List<Byte> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Byte.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_ShortList(List<Short> l1, List<Short> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Short.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_IntegerList(List<Integer> l1, List<Integer> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Integer.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_LongList(List<Long> l1, List<Long> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Long.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_FloatList(List<Float> l1, List<Float> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Float.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_DoubleList(List<Double> l1, List<Double> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = Double.compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	public static int safe_compare_StringList(List<String> l1, List<String> l2) {
		int comp = safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++) {
			comp = safe_compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	}
	
	

	
	
	
}
