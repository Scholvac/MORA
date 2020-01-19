
using System.IO;
using Mora.Com;
using System.Collections;
using System.Collections.Generic;
using Mora;
using Mora.Stream;
using System;

namespace De.Sos.MORA.Examples.Serialize {
	public enum MyEnum {
		KEY,
		VALUE
	}
	public static class MyEnumUtil {
		public static int valueOf(MyEnum v){
			switch(v) {
				case MyEnum.KEY: return 0;
				case MyEnum.VALUE: return 1;
			}
			throw new ArgumentException();
		}
		public static MyEnum valueOf(int v){
			switch(v){
				case 0: return MyEnum.KEY;
				case 1: return MyEnum.VALUE;
			}
			throw new ArgumentException();
		}
		
		#region serialisation
		
		public static void Write(List<MyEnum> _in, IMoraOutputStream stream, Communicator communicator) {
			stream.Write(_in.Count);
			for (int i = 0; i < _in.Count; i++)
				Write(_in[i], stream, communicator);
		}
		public static void Write(MyEnum _in, IMoraOutputStream stream, Communicator communicator) {
			stream.Write(valueOf(_in));
		}
		
		public static void Read(IMoraInputStream stream, Communicator communicator, out List<MyEnum> result)
        {
            List<int> intValues;
            stream.Read(out intValues);
            result = new List<MyEnum>(intValues.Count);
            for (int i = 0; i < intValues.Count; i++)
            {
                result.Add(valueOf(intValues[i]));
            }
        }
        public static void Read(IMoraInputStream stream, Communicator communicator, out MyEnum result)
        {
        	int intValue;
        	stream.Read(out intValue);
            result = valueOf(intValue);
        }
		#endregion
	}
}
