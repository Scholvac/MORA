
using System.IO;
using Mora.Com;
using System.Collections;
using System.Collections.Generic;
using Mora;
using Mora.Stream;

namespace De.Sos.MORA.Examples.Serialize {
	[System.Serializable]
	public class ListStruct {
		
		#region member
		public List<bool> BoolListValue;
		public List<byte> ByteListValue;
		public List<short> ShortListValue;
		public List<int> IntListValue;
		public List<long> LongListValue;
		public List<float> FloatListValue;
		public List<double> DoubleListValue;
		public List<string> StringListValue;
		public List<MyEnum> EnumListValue;
		public List<SimpleStruct> StructListValue;
		#endregion
		
		#region constructor
		public ListStruct() {
		}
		#endregion
		
		
		#region serialisation
		
		public static void Write(List<ListStruct> _in, IMoraOutputStream stream, Communicator communicator){
			stream.Write(_in.Count);
			for (int i = 0; i < _in.Count; i++)
				Write(_in[i], stream, communicator);
		}
		public static void Write(ListStruct _in, IMoraOutputStream stream, Communicator communicator){
			if (_in == null){
				stream.Write(Common.STRUCT_NULL); //value does not exists
				return ;
			}else{
				stream.Write(Common.STRUCT_START); //value exists
			}
			stream.Write(_in.BoolListValue);
			stream.Write(_in.ByteListValue);
			stream.Write(_in.ShortListValue);
			stream.Write(_in.IntListValue);
			stream.Write(_in.LongListValue);
			stream.Write(_in.FloatListValue);
			stream.Write(_in.DoubleListValue);
			stream.Write(_in.StringListValue);
			MyEnumUtil.Write(_in.EnumListValue, stream, communicator);
			SimpleStruct.Write(_in.StructListValue, stream, communicator);
			stream.Write(Common.STRUCT_END);
		}
		
		
		public static void Read(IMoraInputStream stream, Communicator communicator, out List<ListStruct> result){
			int count;
			stream.Read(out count);
			result = new List<ListStruct>(count);
			for (int i = 0; i < count; i++){
				ListStruct value;
				Read(stream, communicator, out value);
				result.Add(value);
			}
		}
		
		public static bool Read(IMoraInputStream stream, Communicator communicator, out ListStruct result){
			byte _flag_;
			stream.Read(out _flag_);					
			if (_flag_ == Common.STRUCT_NULL){
				result = null;
				return true;
			}
			if (_flag_ != Common.STRUCT_START)
				throw new System.Exception("Missing start flag");
			result = new ListStruct();
			stream.Read(out result.BoolListValue);
			stream.Read(out result.ByteListValue);
			stream.Read(out result.ShortListValue);
			stream.Read(out result.IntListValue);
			stream.Read(out result.LongListValue);
			stream.Read(out result.FloatListValue);
			stream.Read(out result.DoubleListValue);
			stream.Read(out result.StringListValue);
			MyEnumUtil.Read(stream, communicator, out result.EnumListValue);
			SimpleStruct.Read(stream, communicator, out result.StructListValue);
			byte _endFlag_;
			stream.Read(out _endFlag_);
			if (_endFlag_ != Common.STRUCT_END)
				throw new System.Exception("Missing end flag");
			return true;
		}
		#endregion
	}
}
