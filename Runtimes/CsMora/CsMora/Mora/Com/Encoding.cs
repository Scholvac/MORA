using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;

namespace Mora.Com
{
    public class Encoding
    {
        public static readonly int BYTE_LENGTH = 1;
        public static readonly int SHORT_LENGTH = 2;
        public static readonly int INT_LENGTH = 4;

        
        public static byte decodeByte(byte[] buffer, int offset) {
            return buffer[offset];
        }
        public static short decodeShort(byte[] buffer, int offset)
        {
            return (short)(
                 ((buffer[offset + 0] & 0xFF) << 8) +
                 ((buffer[offset + 1] & 0xFF) << 0)
             );
        }
        public static int decodeInt(byte[] buffer, int offset)
        {
            return (int)(
                 ((int)((buffer[offset + 0] & 0xFF) << 24)) +
                 ((int)((buffer[offset + 1] & 0xFF) << 16)) +
                 ((int)((buffer[offset + 2] & 0xFF) << 8)) +
                 ((int)((buffer[offset + 3] & 0xFF) << 0))
                );
        }

        internal static void Write(short value, byte[] buffer, int offset)
        {
            buffer[offset + 0] = (byte)((value >> 8) & 0xFF);
            buffer[offset + 1] = (byte)((value >> 0) & 0xFF);
        }
        internal static void Write(char value, byte[] buffer, int offset)
        {
            buffer[offset + 0] = (byte)((value >> 8) & 0xFF);
            buffer[offset + 1] = (byte)((value >> 0) & 0xFF);
        }
        public static void Write(int value, byte[] buffer, int offset)
        {
            buffer[offset + 0] = (byte)((value >> 24) & 0xFF);
            buffer[offset + 1] = (byte)((value >> 16) & 0xFF);
            buffer[offset + 2] = (byte)((value >> 8) & 0xFF);
            buffer[offset + 3] = (byte)((value >> 0) & 0xFF);
        }
        internal static void Write(string value, out byte[] result)
        {
            int l = value.Length;
            result = new byte[l * 2 + 4];
            Write(l, result, 0);
            int idx = 4;
            for (int i = 0; i < l; i++, idx += 2)
            {
                Write(value[i], result, idx);
            }
        }
    }
}
