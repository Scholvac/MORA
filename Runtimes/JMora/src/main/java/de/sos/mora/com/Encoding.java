package de.sos.mora.com;

public class Encoding {

	public static final int BYTE_LENGTH = 1;
	public static final int SHORT_LENGTH = 2;
	public static final int INT_LENGTH = 4;
	public static final int LONG_LENGTH = 8;
	public static final int FLOAT_LENGTH = 4;
	public static final int DOUBLE_LENGTH = 8;
	
	
	public static void main(String[] args) {
		boolean bov = true;
		byte bv = (byte) 142;
		short sv = 4242;
		int iv = 4242;
		long lv = 4242;
		float fv = 42.42f;
		double dv = 42.42;
		
		
		
		byte[] byte_bov = print(bov, encodeBoolean(bov), decodeBoolean(encodeBoolean(bov)));
		byte[] byte_bv = print(bv, encodeByte(bv), decodeByte(encodeByte(bv)));
		byte[] byte_sv = print(sv, encodeShort(sv), decodeShort(encodeShort(sv)));
		byte[] byte_iv = print(iv, encodeInt(iv), decodeInt(encodeInt(sv)));
		byte[] byte_lv = print(lv, encodeLong(lv), decodeLong(encodeLong(lv)));
		byte[] byte_fv = print(fv, encodeFloat(fv), decodeFloat(encodeFloat(fv)));
		byte[] byte_dv = print(dv, encodeDouble(dv), decodeDouble(encodeDouble(dv)));
		
	}
	
	
	public static boolean decodeBoolean(final byte[] b) {
		return decodeBoolean(b, 0); 
	}
	public static boolean decodeBoolean(final byte[] b, final int offset) {
		return b[offset+0] == 1;
	}
	public static byte decodeByte(final byte[] b) {
		return decodeByte(b, 0);
	}
	public static byte decodeByte(final byte[] b, final int offset) {
		return b[offset+0];
	}
	public static char decodeChar(final byte[] b) {
		return decodeChar(b, 0);
	}
	public static char decodeChar(final byte[] b, final int offset) {
		int ch1 = b[offset+0] & 0xFF;
		int ch2 = b[offset+1] & 0xFF;
		return (char)((ch1 << 8) + (ch2 << 0));
	}
	public static short decodeShort(final byte[] b) {
		return decodeShort(b, 0);
	}
	public static short decodeShort(final byte[] b, final int offset) {
        return (short)(
        		((b[offset+0] & 0xFF) << 8) + 
        		((b[offset+1] & 0xFF) << 0)
        	);
	}
	public static int decodeInt(final byte[] b) {
		return decodeInt(b, 0);
	}
	public static int decodeInt(final byte[] b, final int offset) {
		return (int) (
				((int)((b[offset+0] & 0xFF) << 24)) + 
				((int)((b[offset+1] & 0xFF) << 16)) +
				((int)((b[offset+2] & 0xFF) << 8)) +
				((int)((b[offset+3] & 0xFF) << 0))
				);
	}
	public static long decodeLong(final byte[] b) {
		return decodeLong(b, 0);
	}
	public static long decodeLong(final byte[] b, final int offset) {
		return (((long)b[offset+0] << 56) +
                ((long)(b[offset+1] & 255) << 48) +
                ((long)(b[offset+2] & 255) << 40) +
                ((long)(b[offset+3] & 255) << 32) +
                ((long)(b[offset+4] & 255) << 24) +
                ((b[offset+5] & 255) << 16) +
                ((b[offset+6] & 255) <<  8) +
                ((b[offset+7] & 255) <<  0));
	}
	
	public static float decodeFloat(final byte[] b) {
		return decodeFloat(b, 0);
	}
	public static float decodeFloat(final byte[] b, final int offset) {
		return Float.intBitsToFloat(decodeInt(b, offset));
	}
	public static double decodeDouble(final byte[] b) {
		return decodeDouble(b, 0);
	}
	public static double decodeDouble(final byte[] b, final int offset) {
		return Double.longBitsToDouble(decodeLong(b, offset));
	}


	public static byte[] encodeByte(byte b, byte[] out, int offset) {
		out[offset] = b;
		return out;
	}
	public static byte[] encodeByte(byte b) {
		return new byte[] {b};
	}
	public static byte[] encodeBoolean(boolean b) {
		return encodeByte((byte) (b ? 1 : 0));
	}
	public static byte[] encodeBoolean(boolean b, byte[] out, int offset) {
		out[offset] = (byte) (b ? 1 : 0);
		return out;
	}
	public static byte[] encodeChar(char s) {
		return encodeChar(s, new byte[2], 0);
	}
	public static byte[] encodeChar(char s, byte[] out, int offset) {
		out[offset+0] = (byte)((s >>> 8) & 0xFF);
		out[offset+1] = (byte)((s >>> 0) & 0xFF);
		return out;
	}
	public static byte[] encodeShort(short s) {
		return encodeShort(s, new byte[2], 0);
	}
	public static byte[] encodeShort(short s, byte[] out, int offset) {
		out[offset+0] = (byte)((s >>> 8) & 0xFF);
		out[offset+1] = (byte)((s >>> 0) & 0xFF);
		return out;
	}
	public static byte[] encodeInt(int i) {
		return encodeInt(i, new byte[4], 0);
	}
	public static byte[] encodeInt(int i, byte[] out, int offset) {
		out[offset+0] = (byte)((i >>> 24) & 0xFF);
		out[offset+1] = (byte)((i >>> 16) & 0xFF);
		out[offset+2] = (byte)((i >>> 8) & 0xFF);
		out[offset+3] = (byte)((i >>> 0) & 0xFF);
		return out;		
	}
	public static byte[] encodeLong(long l) {
		return encodeLong(l, new byte[8], 0);
	}
	public static byte[] encodeLong(long l, byte[] out, int offset) {
		out[offset+0] = (byte)(l >>> 56);
		out[offset+1] = (byte)(l >>> 48);
		out[offset+2] = (byte)(l >>> 40);
		out[offset+3] = (byte)(l >>> 32);
		out[offset+4] = (byte)(l >>> 24);
		out[offset+5] = (byte)(l >>> 16);
		out[offset+6] = (byte)(l >>> 8);
		out[offset+7] = (byte)(l >>> 0);
		return out;
	}
	public static byte[] encodeFloat(float f) {
		return encodeFloat(f, new byte[4], 0);
	}
	public static byte[] encodeFloat(float f, byte[] out, int offset) {
		final int intBits = Float.floatToIntBits(f);
		return encodeInt(intBits, out, offset);
	}
	public static byte[] encodeDouble(double d) {
		return encodeDouble(d, new byte[8], 0);
	}
	public static byte[] encodeDouble(double d, byte[] out, int offset) {
		final long longBits = Double.doubleToLongBits(d);
		return encodeLong(longBits, out, offset);
	}

	
	public static byte[] encodeString(String s) {
		final int l = s.length();
		final byte[] out = new byte[l*2+4];
		encodeInt(l, out, 0);
		byte[] tmp = new byte[2];
		int idx = 4;
		for (int i = 0; i < l; i++, idx+=2) {
			encodeChar(s.charAt(i), tmp, 0);
			out[idx+0] = tmp[0];
			out[idx+1] = tmp[1];
		}
		return out;
	}
	
	public static String decodeString(byte[] data) {
		int l = decodeInt(data);
		String out = "";
		int idx = 4;
		for (int i = 0; i < l; i++, idx+=2) {
			out += decodeChar(data, idx);
		}
		return out;
	}
	
	
	
	
	
	
	
	
	
	
	
	
    private static byte[] print(Object v, byte[] data, Object b) {
    	String bstr = "[";
    	for (int i = 0; i < data.length; i++)
    		bstr += data[i] + ", ";
    	bstr = bstr.substring(0, bstr.length()-2) + "]";
    	System.out.println(v.getClass().getSimpleName() + ": " + v + " = " + bstr + " = " + b);
    	return data;
	}


	




//	public static byte short1(short x) { return (byte)(x >> 8); }
//    public static byte short0(short x) { return (byte)(x     ); }
//    
//	
//    public static byte[] write(final byte b) {
//    	return new byte[] {b};
//    }
//	public static byte[] write(short s) {
//		return new byte[] {
//				short1(s), 
//				short0(s) 
//			};
//	}
//	public static byte[] write(int v) {
//		return new byte[] {
//		  (byte) ((v >>> 24) & 0xFF),
//	      (byte) ((v >>> 16) & 0xFF),
//	      (byte) ((v >>>  8) & 0xFF),
//	      (byte) ((v >>>  0) & 0xFF)
//		};
//	}
//	
//	
//	
//	public static byte readByte(byte[] data, int offset) {
//		return data[offset];
//	}
//	public static short readShort(byte[] data, int offset) {
//		return (short) ( 
//				((data[offset+0] & 0xff) <<  8) |
//                ((data[offset+1] & 0xff)      )
//                );
//	}
//	public static int readInt(byte[] data, int offset) {
//		final int ch1 = data[offset+0] & 0xff;
//		final int ch2 = data[offset+1] & 0xff;
//		final int ch3 = data[offset+2] & 0xff;
//		final int ch4 = data[offset+3] & 0xff;
//		return toInt(ch1, ch2, ch3, ch4);
//	}
//	public static short toShort(int ch1, int ch2) {
//		return (short) ((ch1 << 8) + (ch2 << 0));
//	}
//	public static int toInt(int ch1, int ch2, final int ch3, final int ch4) {
//		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
//	}
//	public static char toChar(final int ch1) {
//		return (char)ch1;
//	}
//	public static long toLong(int ch1, int ch2, int ch3, int ch4, int ch5, int ch6, int ch7, int ch8) {
//		return ((ch1 << 56) +
//				(ch2 << 48) + 
//				(ch3 << 40) +
//				(ch4 << 32) + 
//				(ch5 << 24) + 
//				(ch6 << 16) + 
//				(ch7 << 8) +
//				(ch8 << 0));
//	}
//	public static byte[] write(long l) {
//		return new byte[] {
//				  (byte) ((l >>> 52) & 0xFF),
//			      (byte) ((l >>> 48) & 0xFF),
//			      (byte) ((l >>> 40) & 0xFF),
//			      (byte) ((l >>> 32) & 0xFF),
//				  (byte) ((l >>> 24) & 0xFF),
//			      (byte) ((l >>> 16) & 0xFF),
//			      (byte) ((l >>>  8) & 0xFF),
//			      (byte) ((l >>>  0) & 0xFF)
//				};
//	}
//	public static byte[] write(float floatValue) {
//		return write(Float.floatToIntBits(floatValue));
//	}
//	public static byte[] write(double doubleValue) {
//		return write(Double.doubleToLongBits(doubleValue));
//	}

}
