package de.sos.mora.examples.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import de.sos.mora.examples.serialize.EchoManagerRPC.IFace;
import de.sos.mora.list.BoolArrayList;
import de.sos.mora.list.ByteArrayList;
import de.sos.mora.list.DoubleArrayList;
import de.sos.mora.list.FloatArrayList;
import de.sos.mora.list.IntArrayList;
import de.sos.mora.list.LongArrayList;
import de.sos.mora.list.ShortArrayList;
import de.sos.mora.list.StringArrayList;
import de.sos.mora.examples.serialize.ListStruct;
import de.sos.mora.examples.serialize.MyEnum;
import de.sos.mora.examples.serialize.SimpleStruct;
import de.sos.mora.exceptions.MoraException;

public class EchoManager implements IFace {

	public static final Random RNG = new Random(System.currentTimeMillis());
	private static final int LIST_SIZE = 500;

	public static SimpleStruct createRandomSimpleStruct() {
		SimpleStruct ss = new SimpleStruct();
		ss.setBoolValue(RNG.nextBoolean());
		ss.setByteValue((byte) (RNG.nextInt() % 256));
		ss.setShortValue((short) RNG.nextInt());
		ss.setIntValue(RNG.nextInt());
		ss.setLongValue(RNG.nextLong());
		ss.setFloatValue(RNG.nextFloat());
		ss.setDoubleValue(RNG.nextDouble());
		ss.setStringValue(UUID.randomUUID().toString());
		ss.setEnumValue(RNG.nextBoolean() ? MyEnum.KEY : MyEnum.VALUE);
		return ss;
	}

	public static <T> List<T> createRandomList(Class<T> clazz) {
		List<T> out = new ArrayList<T>();
		int num = RNG.nextInt(LIST_SIZE);
		for (int i = 0; i < num; i++) {
			double n = RNG.nextDouble() * 1000;
			try {
				if (clazz == Boolean.class)
					out.add((T) Boolean.valueOf(RNG.nextBoolean()));
				else if (clazz == Byte.class)
					out.add((T) Byte.valueOf((byte) RNG.nextInt()));
				else if (clazz == Short.class)
					out.add((T) Short.valueOf((short) RNG.nextInt()));
				else if (clazz == Integer.class)
					out.add((T) Integer.valueOf(RNG.nextInt()));
				else if (clazz == Long.class)
					out.add((T) Long.valueOf(RNG.nextLong()));
				else if (clazz == Float.class)
					out.add((T) Float.valueOf(RNG.nextFloat()));
				else if (clazz == Double.class)
					out.add((T) Double.valueOf(RNG.nextDouble()));
				else if (clazz == String.class)
					out.add((T) UUID.randomUUID().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return out;
	}

	public static ListStruct createRandomListStruct() {
		ListStruct ls = new ListStruct();
		boolean[] boolListValue = new boolean[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < boolListValue.length; i++) boolListValue[i] = RNG.nextBoolean();
		byte[] byteListValue = new byte[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < byteListValue.length; i++) byteListValue[i] = (byte)RNG.nextInt();
		short[] shortListValue = new short[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < shortListValue.length; i++) shortListValue[i] = (short)RNG.nextInt();
		int[] intListValue = new int[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < intListValue.length; i++) intListValue[i] = RNG.nextInt();
		long[] longListValue = new long[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < longListValue.length; i++) longListValue[i] = RNG.nextInt();
		float[] floatListValue = new float[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < floatListValue.length; i++) floatListValue[i] = RNG.nextFloat();
		double[] doubleListValue = new double[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < doubleListValue.length; i++) doubleListValue[i] = RNG.nextDouble();
		String[] stringListValue = new String[RNG.nextInt(LIST_SIZE)];
		for (int i = 0; i < stringListValue.length; i++) stringListValue[i] = UUID.randomUUID().toString();
		
		
		ls.setBoolListValue(new BoolArrayList(boolListValue));
		ls.setByteListValue(new ByteArrayList(byteListValue));
		ls.setShortListValue(new ShortArrayList(shortListValue));
		ls.setIntListValue(new IntArrayList(intListValue));
		ls.setLongListValue(new LongArrayList(longListValue));
		ls.setFloatListValue(new FloatArrayList(floatListValue));
		ls.setDoubleListValue(new DoubleArrayList(doubleListValue));
		ls.setStringListValue(new StringArrayList(stringListValue));
		List<MyEnum> enL = new ArrayList<>();
		int num = RNG.nextInt(500);
		for (int i = 0; i < num; i++) {
			enL.add(MyEnum.valueOf(RNG.nextInt(1)));
		}
		ls.setEnumListValue(enL);
		List<SimpleStruct> stL = new ArrayList<>();
		num = RNG.nextInt(500);
		for (int i = 0; i < num; i++)
			stL.add(createRandomSimpleStruct());
		ls.setStructListValue(stL);
		return ls;
	}

	private de.sos.mora.examples.serialize.CallbackRPC.IFace mCallback;

	@Override
	public boolean echo(boolean _value_) {
		return _value_;
	}

	@Override
	public byte echo(byte _value_) {
		return _value_;
	}

	@Override
	public short echo(short _value_) {
		return _value_;
	}

	@Override
	public int echo(int _value_) {
		return _value_;
	}

	@Override
	public long echo(long _value_) {
		return _value_;
	}

	@Override
	public float echo(float _value_) {
		return _value_;
	}

	@Override
	public double echo(double _value_) {
		return _value_;
	}

	@Override
	public String echo(String _value_) {
		return _value_;
	}

	@Override
	public MyEnum echo(MyEnum _value_) {
		return _value_;
	}

	@Override
	public SimpleStruct echo(SimpleStruct _value_) {
		return _value_;
	}

	@Override
	public ListStruct echo(ListStruct _value_) {
		return _value_;
	}

	

	@Override
	public List<MyEnum> echo9(List<MyEnum> _value_) {
		return _value_;
	}

	@Override
	public List<SimpleStruct> echo10(List<SimpleStruct> _value_) {
		return _value_;
	}

	@Override
	public List<ListStruct> echo11(List<ListStruct> _value_) {
		return _value_;
	}

	@Override
	public BoolArrayList echo1(BoolArrayList _value_) {
		return _value_;
	}

	@Override
	public ByteArrayList echo2(ByteArrayList _value_) {
		return _value_;
	}

	@Override
	public ShortArrayList echo3(ShortArrayList _value_) {
		return _value_;
	}

	@Override
	public IntArrayList echo4(IntArrayList _value_) {
		return _value_;
	}

	@Override
	public LongArrayList echo5(LongArrayList _value_) {
		return _value_;
	}

	@Override
	public FloatArrayList echo6(FloatArrayList _value_) {
		return _value_;
	}

	@Override
	public DoubleArrayList echo7(DoubleArrayList _value_) {
		return _value_;
	}

	@Override
	public StringArrayList echo8(StringArrayList _value_) {
		return _value_;
	}

	@Override
	public void setCallback(de.sos.mora.examples.serialize.CallbackRPC.IFace _cb_, float _firstValue_) {
		mCallback = _cb_;
		_cb_.onEcho(_firstValue_);		
	}

	@Override
	public de.sos.mora.examples.serialize.CallbackRPC.IFace getCallback() {
		return mCallback;
	}


	@Override
	public void throwUnknownException() {
		throw new RuntimeException("Some Runtime Exception caught somewhere...:");
	}

}