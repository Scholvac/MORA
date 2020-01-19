
using System.IO;
using Mora.Com;
using System.Collections;
using System.Collections.Generic;
using Mora;
using Mora.Stream;

namespace De.Sos.MORA.Examples.Serialize {
	[System.Serializable]
	public class SimpleStruct {
		
		#region member
		public bool BoolValue;
		public byte ByteValue;
		public short ShortValue;
		public int IntValue;
		public long LongValue;
		public float FloatValue;
		public double DoubleValue;
		public string StringValue;
		public MyEnum EnumValue;
		public SimpleStruct StructValue;
		#endregion
		
		#region constructor
		public SimpleStruct() {
		}
		#endregion
		
		
		#region serialisation
		
		public static void Write(List<SimpleStruct> _in, IMoraOutputStream stream, Communicator communicator){
			stream.Write(_in.Count);
			for (int i = 0; i < _in.Count; i++)
				Write(_in[i], stream, communicator);
		}
		public static void Write(SimpleStruct _in, IMoraOutputStream stream, Communicator communicator){
			if (_in == null){
				stream.Write(Common.STRUCT_NULL); //value does not exists
				return ;
			}else{
				stream.Write(Common.STRUCT_START); //value exists
			}
			stream.Write(_in.BoolValue);
			stream.Write(_in.ByteValue);
			stream.Write(_in.ShortValue);
			stream.Write(_in.IntValue);
			stream.Write(_in.LongValue);
			stream.Write(_in.FloatValue);
			stream.Write(_in.DoubleValue);
			stream.Write(_in.StringValue);
			MyEnumUtil.Write(_in.EnumValue, stream, communicator);
			SimpleStruct.Write(_in.StructValue, stream, communicator);
			stream.Write(Common.STRUCT_END);
		}
		
		
		public static void Read(IMoraInputStream stream, Communicator communicator, out List<SimpleStruct> result){
			int count;
			stream.Read(out count);
			result = new List<SimpleStruct>(count);
			for (int i = 0; i < count; i++){
				SimpleStruct value;
				Read(stream, communicator, out value);
				result.Add(value);
			}
		}
		
		public static bool Read(IMoraInputStream stream, Communicator communicator, out SimpleStruct result){
			byte _flag_;
			stream.Read(out _flag_);					
			if (_flag_ == Common.STRUCT_NULL){
				result = null;
				return true;
			}
			if (_flag_ != Common.STRUCT_START)
				throw new System.Exception("Missing start flag");
			result = new SimpleStruct();
			stream.Read(out result.BoolValue);
			stream.Read(out result.ByteValue);
			stream.Read(out result.ShortValue);
			stream.Read(out result.IntValue);
			stream.Read(out result.LongValue);
			stream.Read(out result.FloatValue);
			stream.Read(out result.DoubleValue);
			stream.Read(out result.StringValue);
			MyEnumUtil.Read(stream, communicator, out result.EnumValue);
			SimpleStruct.Read(stream, communicator, out result.StructValue);
			byte _endFlag_;
			stream.Read(out _endFlag_);
			if (_endFlag_ != Common.STRUCT_END)
				throw new System.Exception("Missing end flag");
			return true;
		}
		#endregion
	}
}
