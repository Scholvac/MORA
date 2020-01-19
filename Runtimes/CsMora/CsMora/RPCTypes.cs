using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

namespace RPCLibrary
{

    public interface IRPCTypeEncoder
    {
        void Encode(object _in, BinaryWriter writer, RPCCommunicator communicator);
        object Decode( BinaryReader reader, RPCCommunicator communicator);

        void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator);
        IList DecodeList(BinaryReader reader, RPCCommunicator communicator);
    }

    public class BoolEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeBool((bool)_in, w);
        }
        public static void EncodeBool(bool _in, BinaryWriter writer)
        {
            writer.Write(_in);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeBool(r);
        }

        public static bool DecodeBool(BinaryReader r)
        {
            return r.ReadBoolean();
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
            IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeBool((bool)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            return DecodeBoolList(reader);
        }

        public static List<bool> DecodeBoolList(BinaryReader reader)
        {
            var result = new List<bool>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeBool(reader));
            return result;
        }
    }

    public class ByteEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeByte((byte)_in, w);
        }
        public static void EncodeByte(byte _in, BinaryWriter writer)
        {
            writer.Write(_in);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeByte(r);
        }

        public static byte DecodeByte(BinaryReader r)
        {
            return r.ReadByte();
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
            IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeByte((byte)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<byte>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeByte(reader));
            return result;
        }
    }
    public class ShortEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeShort((short)_in, w);
        }
        public static void EncodeShort(short _in, BinaryWriter writer)
        {
            byte[] data = BitConverter.GetBytes(_in);
            Array.Reverse(data);
            writer.Write(data);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeShort(r);
        }

        public static short DecodeShort(BinaryReader r)
        {
            byte[] d = r.ReadBytes(2);
            Array.Reverse(d);
            return BitConverter.ToInt16(d, 0);
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
                        IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeShort((short)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<short>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeShort(reader));
            return result;
        }
    }
    public class IntEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeInt((int)_in, w);
        }
        public static void EncodeInt(int _in, BinaryWriter writer)
        {
            byte[] data = BitConverter.GetBytes(_in);
            Array.Reverse(data);
            writer.Write(data);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeInt(r);
        }

        public static int DecodeInt(BinaryReader r)
        {
            byte[] d = r.ReadBytes(4);
            Array.Reverse(d);
            return BitConverter.ToInt32(d, 0);
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
            IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeInt((int)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<int>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeInt(reader));
            return result;
        }
    }
    public class LongEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeLong((long)_in, w);
        }
        public static void EncodeLong(long _in, BinaryWriter writer)
        {
            byte[] data = BitConverter.GetBytes(_in);
            Array.Reverse(data);
            writer.Write(data);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeLong(r);
        }

        public static long DecodeLong(BinaryReader r)
        {
            byte[] d = r.ReadBytes(8);
            Array.Reverse(d);
            return BitConverter.ToInt64(d, 0);
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
                        IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeLong((long)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<long>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeLong(reader));
            return result;
        }
    }
    public class FloatEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeFloat((float)_in, w);
        }
        public static void EncodeFloat(float _in, BinaryWriter writer)
        {
            byte[] data = BitConverter.GetBytes(_in);
            Array.Reverse(data);
            writer.Write(data);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeFloat(r);
        }

        public static float DecodeFloat(BinaryReader r)
        {
            byte[] d = r.ReadBytes(4);
            Array.Reverse(d);
            return BitConverter.ToSingle(d, 0);
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
                        IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeFloat((float)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<float>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeFloat(reader));
            return result;
        }
    }
    public class DoubleEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeDouble((double)_in, w);
        }
        public static void EncodeDouble(double _in, BinaryWriter writer)
        {
            byte[] data = BitConverter.GetBytes(_in);
            Array.Reverse(data);
            writer.Write(data);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeDouble(r);
        }

        public static double DecodeDouble(BinaryReader r)
        {
            byte[] d = r.ReadBytes(8);
            Array.Reverse(d);
            return BitConverter.ToDouble(d, 0);
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
            IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeDouble((double)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            IList result = new List<double>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
                result.Add(DecodeDouble(reader));
            return result;
        }
    }



    public class StringEncoder : IRPCTypeEncoder
    {
        public void Encode(object _in, BinaryWriter w, RPCCommunicator communicator)
        {
            EncodeString((string)_in, w);
        }

        public object Decode(BinaryReader r, RPCCommunicator communicator)
        {
            return DecodeString(r);
        }

        public static string DecodeString(BinaryReader r)
        {
            int l = IntEncoder.DecodeInt(r);
            string res = "";
            for (int i = 0; i < l; i++)
            {
                char c = r.ReadChar();
                res += c;
            }
            return res;
        }
        public static void EncodeString(string s, BinaryWriter w)
        {
            int l = s.Length;
            IntEncoder.EncodeInt(l, w);
            for (int i = 0; i < l; i++)
            {
                w.Write((char)s[i]);
            }
        }

        public void EncodeList(IList _in, BinaryWriter writer, RPCCommunicator communicator)
        {
            IntEncoder.EncodeInt(_in.Count, writer);
            for (int i = 0; i < _in.Count; i++)
                EncodeString((string)_in[i], writer);
        }

        public IList DecodeList(BinaryReader reader, RPCCommunicator communicator)
        {
            List<string> result = new List<string>();
            int c = IntEncoder.DecodeInt(reader);
            for (int i = 0; i < c; i++)
            	result.Add(DecodeString(reader));
            return result;
        }
    }

    public class RPCTypes
    {
        public const byte STRUCT_END = (byte)0xFF;

        public static BoolEncoder BOOL_ENCODER = new BoolEncoder();
        public static ByteEncoder BYTE_ENCODER = new ByteEncoder();
        public static ShortEncoder SHORT_ENCODER = new ShortEncoder();
        public static IntEncoder INT_ENCODER = new IntEncoder();
        public static LongEncoder LONG_ENCODER = new LongEncoder();
        public static FloatEncoder FLOAT_ENCODER = new FloatEncoder();
        public static DoubleEncoder DOUBLE_ENCODER = new DoubleEncoder();
        public static StringEncoder STRING_ENCODER = new StringEncoder();



        
    }
}
