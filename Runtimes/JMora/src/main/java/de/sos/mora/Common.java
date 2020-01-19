package de.sos.mora;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Common {

	public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	public static final byte STRUCT_START = (byte) 0xAF;
	public static final byte STRUCT_END = (byte) 0xFF;
	public static final byte STRUCT_NULL = 0x00;
	
	
	private static AtomicInteger		sMagicGen = new AtomicInteger(1);


	
	
	public static short generateShortMagic() {
		return (short)sMagicGen.incrementAndGet();
	}


	public static byte generateByteMagic() {
		return (byte)sMagicGen.incrementAndGet();
	}


	public static int safe_compare_Lists(List<?> l1, List<?> l2) {
		if (l1 == null)
			return -1;
		if (l2 == null)
			return 1;
		return l1.size()-l2.size();
	}
	
}
