package de.sos.mora.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.sos.mora.com.Communicator;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;

public interface IMoraTypeEncoder<T> {
	void encode(final T in, IMoraOutputStream stream, final Communicator ctx) throws IOException;
	T decode(final IMoraInputStream stream, final Communicator ctx) throws IOException;
	
	default List<T> decodeList(IMoraInputStream stream, final Communicator ctx) throws IOException{
		ArrayList<T> out = new ArrayList<>();
		final int count = stream.readInteger();
		for (int i = 0; i < count; i++) {
			out.add(decode(stream, ctx));
		}
		return out;
	}
	default void encodeList(final List<T> in, IMoraOutputStream stream, final Communicator ctx) throws IOException {
		stream.writeInteger(in.size());
		for (int i = 0; i < in.size(); i++) {
			encode(in.get(i), stream, ctx);
		}
	}
}
