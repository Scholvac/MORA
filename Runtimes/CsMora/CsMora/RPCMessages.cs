using NetMQ.Sockets;
using NetMQ;
using System;
using System.Collections.Generic;
using System.IO;
using RPCLibrary;

namespace RPCLibrary
{

    public class RPCConstants
    {
        public const byte REQUEST_MESSAGE = 24;
        public const byte RESPONSE_MESSAGE = 42;

        public const byte RESPONSE_TYPE_OK = 1;
        public const byte RESPONSE_TYPE_ERROR = 2;


        private static int counter = 0;
        public static int generateMagic()
        {
            return counter++;
        }
    }

    public class RPCProxyRequest
    {
        public String Identifier;
        public String TypeName;
        public RPCProxyRequest(String identifier, String typeName)
        {
            this.Identifier = identifier;
            this.TypeName = typeName;
        }
    }


    public class RPCMethodCallRequest
    {
        public String ClientIdentifier;
        public String MethodName;
        public String ClientAddress;
        public int Magic;
        public BinaryWriter Writer;
        public MemoryStream byteArray;

        public RPCMethodCallRequest(String clientId, String mn, String clientAdr)
        {
            ClientIdentifier = clientId;
            MethodName = mn;
            ClientAddress = clientAdr;

            byteArray = new MemoryStream();
            Writer = new BinaryWriter(byteArray);
            Writer.Write(RPCConstants.REQUEST_MESSAGE);
            IntEncoder.EncodeInt(Magic = RPCConstants.generateMagic(), Writer);
            StringEncoder.EncodeString(ClientIdentifier, Writer);
            StringEncoder.EncodeString(MethodName, Writer);
            StringEncoder.EncodeString(ClientAddress, Writer);
        }
    }
    public class RPCMethodCallResponse
    {
        public int Magic;
        public byte Type;
        public MemoryStream byteArray;
        public BinaryWriter writer;

        public RPCMethodCallResponse(int m, byte t)
        {
            Magic = m;
            Type = t;
            writer = new BinaryWriter(byteArray = new MemoryStream());

            ByteEncoder.EncodeByte(RPCConstants.RESPONSE_MESSAGE, writer);
            IntEncoder.EncodeInt(Magic, writer);
            ByteEncoder.EncodeByte(t, writer);
        }
    }

    public class RPCIncommingMethodResponse
    {
        public int Magic;
        public byte Type;
        public Stream byteArray;
        public BinaryReader reader;

        public RPCIncommingMethodResponse(byte[] data)
        {
            byteArray = new MemoryStream(data);
            reader = new BinaryReader(byteArray);
            byte c = reader.ReadByte();
            if (c != RPCConstants.RESPONSE_MESSAGE)
                throw new ArgumentException("Expected RESPONSE_MESSAGE flag");

            Magic = IntEncoder.DecodeInt(reader);
            Type = ByteEncoder.DecodeByte(reader);
        }
    }


    public class RPCIncommingMethodCall
    {
        public Stream byteArray;
        public BinaryReader reader;
        public String TargetIdentifier;
        public String MethodName;
        public String SourceIdentifier;
        public int Magic;


        public RPCIncommingMethodCall(byte[] data)
        {
            byteArray = new MemoryStream(data);
            reader = new BinaryReader(byteArray);

            byte req = reader.ReadByte();
            if (req != RPCConstants.REQUEST_MESSAGE)
                throw new RPCAdapterException("Expected request message but got: " + req);

            Magic = IntEncoder.DecodeInt(reader);
            TargetIdentifier = StringEncoder.DecodeString(reader);
            MethodName = StringEncoder.DecodeString(reader);
            SourceIdentifier = StringEncoder.DecodeString(reader);
        }
    }

    public class RPCInternalMethodResult
    {
        public object Value;
        public IRPCTypeEncoder Encoder;
        public Exception Throwable;
        private object p1;
        private object p2;
        private Exception t;

        public RPCInternalMethodResult(object v, IRPCTypeEncoder e, Exception t)
        {
            Value = v;
            Encoder = e;
            Throwable = t;
        }

        public RPCInternalMethodResult(object p1, object p2, Exception t)
        {
            this.p1 = p1;
            this.p2 = p2;
            this.t = t;
        }
    }
}
