package de.sos.mora.examples.serialize;

import de.sos.mora.Common;
import de.sos.mora.com.Communicator;
import de.sos.mora.examples.serialize.MyEnum;
import de.sos.mora.examples.serialize.SimpleStruct;
import de.sos.mora.list.BoolArrayList;
import de.sos.mora.list.ByteArrayList;
import de.sos.mora.list.DoubleArrayList;
import de.sos.mora.list.FloatArrayList;
import de.sos.mora.list.IntArrayList;
import de.sos.mora.list.LongArrayList;
import de.sos.mora.list.ShortArrayList;
import de.sos.mora.list.StringArrayList;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.types.IMoraTypeEncoder;
import de.sos.mora.types.MoraEncoders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ListStruct {
	
	//-------------- Member ---------------------
	private BoolArrayList 	mBoolListValue;
	private ByteArrayList 	mByteListValue;
	private ShortArrayList 	mShortListValue;
	private IntArrayList 	mIntListValue;
	private LongArrayList 	mLongListValue;
	private FloatArrayList 	mFloatListValue;
	private DoubleArrayList 	mDoubleListValue;
	private StringArrayList 	mStringListValue;
	private List<MyEnum> 	mEnumListValue;
	private List<SimpleStruct> 	mStructListValue;
	//-------------- Constructors ---------------
	/** Default constructor */
	public ListStruct() {
	}
	
	//-------------- Setter / Getter ----------------------
	public BoolArrayList getBoolListValue(){
		return mBoolListValue;
	}
	public void setBoolListValue(BoolArrayList _newValue_) {
		mBoolListValue = _newValue_;
	}
	public ByteArrayList getByteListValue(){
		return mByteListValue;
	}
	public void setByteListValue(ByteArrayList _newValue_) {
		mByteListValue = _newValue_;
	}
	public ShortArrayList getShortListValue(){
		return mShortListValue;
	}
	public void setShortListValue(ShortArrayList _newValue_) {
		mShortListValue = _newValue_;
	}
	public IntArrayList getIntListValue(){
		return mIntListValue;
	}
	public void setIntListValue(IntArrayList _newValue_) {
		mIntListValue = _newValue_;
	}
	public LongArrayList getLongListValue(){
		return mLongListValue;
	}
	public void setLongListValue(LongArrayList _newValue_) {
		mLongListValue = _newValue_;
	}
	public FloatArrayList getFloatListValue(){
		return mFloatListValue;
	}
	public void setFloatListValue(FloatArrayList _newValue_) {
		mFloatListValue = _newValue_;
	}
	public DoubleArrayList getDoubleListValue(){
		return mDoubleListValue;
	}
	public void setDoubleListValue(DoubleArrayList _newValue_) {
		mDoubleListValue = _newValue_;
	}
	public StringArrayList getStringListValue(){
		return mStringListValue;
	}
	public void setStringListValue(StringArrayList _newValue_) {
		mStringListValue = _newValue_;
	}
	public List<MyEnum> getEnumListValue(){
		return mEnumListValue;
	}
	public void setEnumListValue(List<MyEnum> _newValue_) {
		mEnumListValue = _newValue_;
	}
	public List<SimpleStruct> getStructListValue(){
		return mStructListValue;
	}
	public void setStructListValue(List<SimpleStruct> _newValue_) {
		mStructListValue = _newValue_;
	}
	
	
	//--------------- Compare ----------------------
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ListStruct)
			return safe_compare(this, (ListStruct) obj) == 0;
		return false;
	}
	
	public static int safe_compare(final ListStruct s1, final ListStruct s2){
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
	
	public static int compare(final ListStruct s1, final ListStruct s2){
		int result = 0;
		result = MoraEncoders.safe_compare_BooleanList(s1.mBoolListValue, s2.mBoolListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_ByteList(s1.mByteListValue, s2.mByteListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_ShortList(s1.mShortListValue, s2.mShortListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_IntegerList(s1.mIntListValue, s2.mIntListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_LongList(s1.mLongListValue, s2.mLongListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_FloatList(s1.mFloatListValue, s2.mFloatListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_DoubleList(s1.mDoubleListValue, s2.mDoubleListValue);
		if (result != 0)
			return result;
		result = MoraEncoders.safe_compare_StringList(s1.mStringListValue, s2.mStringListValue);
		if (result != 0)
			return result;
		result = MyEnum.safe_list_compare(s1.mEnumListValue, s2.mEnumListValue);
		if (result != 0)
			return result;
		result = SimpleStruct.safe_list_compare(s1.mStructListValue, s2.mStructListValue);
		if (result != 0)
			return result;
		return result;
	}
	
	public static int safe_list_compare(List<ListStruct> l1, List<ListStruct> l2) {
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
	
	public static void write(final List<ListStruct> in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		stream.writeInteger(in.size());
		for (int i = 0; i < in.size(); i++){
			write(in.get(i), stream, communicator);
		}
	}
	public static void write(final ListStruct in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		if (in == null){
			stream.write(Common.STRUCT_NULL); //value does not exists
			return ;
		}else{
			stream.write(Common.STRUCT_START); //value exists
		}
		stream.writeBoolean(in.mBoolListValue);
		stream.writeByte(in.mByteListValue);
		stream.writeShort(in.mShortListValue);
		stream.writeInteger(in.mIntListValue);
		stream.writeLong(in.mLongListValue);
		stream.writeFloat(in.mFloatListValue);
		stream.writeDouble(in.mDoubleListValue);
		stream.writeString(in.mStringListValue);
		MyEnum.write(in.mEnumListValue, stream, communicator);
		SimpleStruct.write(in.mStructListValue, stream, communicator);
		stream.write(Common.STRUCT_END);
	}
	
	public static List<ListStruct> readList(IMoraInputStream stream, Communicator communicator) throws IOException {
		ArrayList<ListStruct> out = new ArrayList<>();
		int count = stream.readInteger();
		for (int i = 0; i < count; i++){
			out.add(read(stream, communicator));
		}	
		return out;
	}
	public static ListStruct read(IMoraInputStream stream, Communicator communicator) throws IOException {
		byte _flag_ = stream.readByte();
		if (_flag_ == Common.STRUCT_NULL)
			return null;
		assert(_flag_ == Common.STRUCT_START);
		ListStruct out = new ListStruct();
		out.mBoolListValue = stream.readBooleanList();
		out.mByteListValue = stream.readByteList();
		out.mShortListValue = stream.readShortList();
		out.mIntListValue = stream.readIntegerList();
		out.mLongListValue = stream.readLongList();
		out.mFloatListValue = stream.readFloatList();
		out.mDoubleListValue = stream.readDoubleList();
		out.mStringListValue = stream.readStringList();
		out.mEnumListValue = MyEnum.readList(stream, communicator);
		out.mStructListValue = SimpleStruct.readList(stream, communicator);
		byte _endFlag_ = stream.readByte();
		assert(_endFlag_ == Common.STRUCT_END);
		return out;	
	}
}
