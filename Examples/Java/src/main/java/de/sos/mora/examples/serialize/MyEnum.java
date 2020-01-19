package de.sos.mora.examples.serialize;
import de.sos.mora.Common;
import de.sos.mora.com.Communicator;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.types.IMoraTypeEncoder;
import de.sos.mora.types.MoraEncoders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public enum MyEnum {
	KEY, VALUE;
	
	public static int valueOf(final MyEnum v){
		switch(v) {
			case KEY: return 0;
			case VALUE: return 1;
		}
		throw new UnsupportedOperationException();
	}
	public static MyEnum valueOf(final int v){
		switch(v){
			case 0: return KEY;
			case 1: return VALUE;
		}
		throw new UnsupportedOperationException();
	}
					
	public static int safe_compare(final MyEnum s1, final MyEnum s2){
		if (s1 == null){
			if (s2 == null)
				return 0;
			else
				return 1;
		}else if (s2 == null){
			return -1;
		}
		return Integer.compare(valueOf(s1), valueOf(s2));
	}
	public static int safe_list_compare(List<MyEnum> l1, List<MyEnum> l2) {
		int comp = MoraEncoders.safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++){
			comp = safe_compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	};
	
	public static void write(final List<MyEnum> in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		stream.writeInteger(in.size());
		for (int i = 0; i < in.size(); i++){
			write(in.get(i), stream, communicator);
		}
	}
	public static void write(final MyEnum value, IMoraOutputStream stream, Communicator communicator) throws IOException {
		stream.writeInteger(valueOf(value));
	}
	public static List<MyEnum> readList(IMoraInputStream stream, Communicator communicator) throws IOException {
		ArrayList<MyEnum> out = new ArrayList<>();
		int count = stream.readInteger();
		for (int i = 0; i < count; i++){
			out.add(read(stream, communicator));
		}
		return out;
	}
	public static MyEnum read(IMoraInputStream stream, Communicator communicator) throws IOException {
		return valueOf(stream.readInteger());
	}
}
