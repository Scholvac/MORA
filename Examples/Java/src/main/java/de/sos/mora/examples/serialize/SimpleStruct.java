package de.sos.mora.examples.serialize;

import de.sos.mora.Common;
import de.sos.mora.com.Communicator;
import de.sos.mora.examples.serialize.MyEnum;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.types.IMoraTypeEncoder;
import de.sos.mora.types.MoraEncoders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SimpleStruct {
	
	//-------------- Member ---------------------
	private boolean 	mBoolValue;
	private byte 	mByteValue;
	private short 	mShortValue;
	private int 	mIntValue;
	private long 	mLongValue;
	private float 	mFloatValue;
	private double 	mDoubleValue;
	private String 	mStringValue;
	private MyEnum 	mEnumValue;
	private SimpleStruct 	mStructValue;
	//-------------- Constructors ---------------
	/** Default constructor */
	public SimpleStruct() {
	}
	
	//-------------- Setter / Getter ----------------------
	public boolean getBoolValue(){
		return mBoolValue;
	}
	public void setBoolValue(boolean _newValue_) {
		mBoolValue = _newValue_;
	}
	public byte getByteValue(){
		return mByteValue;
	}
	public void setByteValue(byte _newValue_) {
		mByteValue = _newValue_;
	}
	public short getShortValue(){
		return mShortValue;
	}
	public void setShortValue(short _newValue_) {
		mShortValue = _newValue_;
	}
	public int getIntValue(){
		return mIntValue;
	}
	public void setIntValue(int _newValue_) {
		mIntValue = _newValue_;
	}
	public long getLongValue(){
		return mLongValue;
	}
	public void setLongValue(long _newValue_) {
		mLongValue = _newValue_;
	}
	public float getFloatValue(){
		return mFloatValue;
	}
	public void setFloatValue(float _newValue_) {
		mFloatValue = _newValue_;
	}
	public double getDoubleValue(){
		return mDoubleValue;
	}
	public void setDoubleValue(double _newValue_) {
		mDoubleValue = _newValue_;
	}
	public String getStringValue(){
		return mStringValue;
	}
	public void setStringValue(String _newValue_) {
		mStringValue = _newValue_;
	}
	public MyEnum getEnumValue(){
		return mEnumValue;
	}
	public void setEnumValue(MyEnum _newValue_) {
		mEnumValue = _newValue_;
	}
	public SimpleStruct getStructValue(){
		return mStructValue;
	}
	public void setStructValue(SimpleStruct _newValue_) {
		mStructValue = _newValue_;
	}
	
	
	//--------------- Compare ----------------------
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof SimpleStruct)
			return safe_compare(this, (SimpleStruct) obj) == 0;
		return false;
	}
	
	public static int safe_compare(final SimpleStruct s1, final SimpleStruct s2){
		if (s1 == null){
			if (s2 == null)
				return 0;
			else
				return 1;
		}else if (s2 == null){
			return -1;
		}
		return compare(s1, s2);
	}
	
	public static int compare(final SimpleStruct s1, final SimpleStruct s2){
		int result = 0;
		result = Boolean.compare(s1.mBoolValue, s2.mBoolValue);
		if (result != 0)
			return result;
		result = Byte.compare(s1.mByteValue, s2.mByteValue);
		if (result != 0)
			return result;
		result = Short.compare(s1.mShortValue, s2.mShortValue);
		if (result != 0)
			return result;
		result = Integer.compare(s1.mIntValue, s2.mIntValue);
		if (result != 0)
			return result;
		result = Long.compare(s1.mLongValue, s2.mLongValue);
		if (result != 0)
			return result;
		result = Float.compare(s1.mFloatValue, s2.mFloatValue);
		if (result != 0)
			return result;
		result = Double.compare(s1.mDoubleValue, s2.mDoubleValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare(s1.mStringValue, s2.mStringValue);
		if (result != 0)
			return result;
		result = MyEnum.safe_compare(s1.mEnumValue, s2.mEnumValue);
		if (result != 0)
			return result;
		result = SimpleStruct.safe_compare(s1.mStructValue, s2.mStructValue);
		if (result != 0)
			return result;
		return result;
	}
	
	public static int safe_list_compare(List<SimpleStruct> l1, List<SimpleStruct> l2) {
		int comp = Common.safe_compare_Lists(l1, l2);
		if (comp != 0)
			return comp;
		for (int i = 0; i < l1.size(); i++){
			comp = safe_compare(l1.get(i), l2.get(i));
			if (comp != 0)
				return comp;
		}
		return 0;
	};
	//--------------- Encoding / Decoding ----------------------
	
	public static void write(final List<SimpleStruct> in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		stream.writeInteger(in.size());
		for (int i = 0; i < in.size(); i++){
			write(in.get(i), stream, communicator);
		}
	}
	public static void write(final SimpleStruct in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		if (in == null){
			stream.write(Common.STRUCT_NULL); //value does not exists
			return ;
		}else{
			stream.write(Common.STRUCT_START); //value exists
		}
		stream.writeBoolean(in.mBoolValue);
		stream.writeByte(in.mByteValue);
		stream.writeShort(in.mShortValue);
		stream.writeInteger(in.mIntValue);
		stream.writeLong(in.mLongValue);
		stream.writeFloat(in.mFloatValue);
		stream.writeDouble(in.mDoubleValue);
		stream.writeString(in.mStringValue);
		MyEnum.write(in.mEnumValue, stream, communicator);
		SimpleStruct.write(in.mStructValue, stream, communicator);
		stream.write(Common.STRUCT_END);
	}
	
	public static List<SimpleStruct> readList(IMoraInputStream stream, Communicator communicator) throws IOException {
		ArrayList<SimpleStruct> out = new ArrayList<>();
		int count = stream.readInteger();
		for (int i = 0; i < count; i++){
			out.add(read(stream, communicator));
		}	
		return out;
	}
	public static SimpleStruct read(IMoraInputStream stream, Communicator communicator) throws IOException {
		byte _flag_ = stream.readByte();
		if (_flag_ == Common.STRUCT_NULL)
			return null;
		assert(_flag_ == Common.STRUCT_START);
		SimpleStruct out = new SimpleStruct();
		out.mBoolValue = stream.readBoolean();
		out.mByteValue = stream.readByte();
		out.mShortValue = stream.readShort();
		out.mIntValue = stream.readInteger();
		out.mLongValue = stream.readLong();
		out.mFloatValue = stream.readFloat();
		out.mDoubleValue = stream.readDouble();
		out.mStringValue = stream.readString();
		out.mEnumValue = MyEnum.read(stream, communicator);
		out.mStructValue = SimpleStruct.read(stream, communicator);
		byte _endFlag_ = stream.readByte();
		assert(_endFlag_ == Common.STRUCT_END);
		return out;	
	}
}
