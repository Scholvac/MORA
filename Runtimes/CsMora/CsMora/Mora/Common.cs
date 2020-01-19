using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora
{
    public class Common
    {
        public const byte STRUCT_START = (byte)0xAF;
        public const byte STRUCT_END = (byte)0xFF;
        public const byte STRUCT_NULL = 0x00;

        internal static readonly int NET_MESSAGES__HEADER_LENGTH = 10;
        internal static readonly byte NET_MESSAGES__ACKNOWLEDGE_MESSAGE = 0xA;
        internal static readonly byte NET_MESSAGES__REQUEST_ACKNOWLEDGE = 1;


        internal static readonly byte MESSAGE_TYPE__METHOD_CALL = 1;

        private static int mMagics = 0;

        internal static string ProtocolToString(PROTOCOL protocol)
        {
            switch (protocol)
            {
                case PROTOCOL.TCP: return "tcp";
                case PROTOCOL.UDP: return "udp";
            }
            throw new Exception("Unsupported Protocol: " + protocol);
        }

        internal static short generateShortMagic()
        {
            return unchecked((short)mMagics++);
        }
        internal static byte generateByteMagic()
        {
            return unchecked((byte)mMagics++);
        }

        internal static readonly byte MESSAGE_TYPE__RESPONSE = 2;

        internal static readonly byte RESPONSE_STATUS__EXCEPTION = 1;

        internal static PROTOCOL ByteToProtocol(byte protocol)
        {
            switch (protocol)
            {
                case 0: return PROTOCOL.TCP;
                case 1: return PROTOCOL.UDP;
            }
            throw new Exception("Unknown protocol: " + protocol);
        }

        internal static PROTOCOL StringToProtocol(string prot)
        {
            if (prot.ToLower().Equals("tcp"))
                return PROTOCOL.TCP;
            else if (prot.ToLower().Equals("udp"))
                return PROTOCOL.UDP;
            throw new Exception("Unknown protocol:" + prot);
        }

        internal static byte ProtocolToByte(PROTOCOL protocol)
        {
            switch (protocol)
            {
                case PROTOCOL.TCP: return 0;
                case PROTOCOL.UDP: return 1;
            }
            throw new Exception("Unknown protocol: " + protocol);
        }
    }
}
