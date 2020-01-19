using Mora.Stream;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;

namespace Mora.Com
{
    public abstract class MoraMessage
    {
        public short Magic { get;  }
        public byte Type { get; }
        protected MoraMessage(short magic, byte type)
        {
            Magic = magic;
            Type = type;
        }
    }
    public abstract class InMessage : MoraMessage 
    {
        public InMessageCollection NetMessage { get; }
        public IMoraInputStream Stream { get { return NetMessage.Stream; } }
        protected InMessage(short magic, byte type, InMessageCollection mc)
            : base(magic, type)
        {
            NetMessage = mc;
        }
    }

    public class InMethodCall : InMessage
    {
        public string ObjectIdentifier { get; }
        public string MethodSignature { get; }

        public RemoteCommunicator ResponseCommunicator { get; }

        public InMethodCall(short magic, byte type, InMessageCollection mc)
           : base(magic, type, mc)
        {
            byte respProt;
            Stream.Read(out respProt);
            int respPort; 
            Stream.Read(out respPort);
            System.Net.EndPoint ep = mc.SourceEP;
            ResponseCommunicator = new RemoteCommunicator((mc.SourceEP as IPEndPoint).Address, respPort, Common.ByteToProtocol(respProt));

            string id;
            Stream.Read(out id);
            int idx = id.LastIndexOf(':');
            ObjectIdentifier = id.Substring(0, idx);
            MethodSignature = id.Substring(idx + 1);
        }
    }
    public class InMethodRespond : InMessage
    {
        public byte StatusFlag { get; }
        public Exception Exception { get; }

        public InMethodRespond(short magic, byte type, InMessageCollection mc)
            : base(magic, type, mc)
        {
            byte flag;
            Stream.Read(out flag);
            StatusFlag = flag;

            if ((StatusFlag & Common.RESPONSE_STATUS__EXCEPTION) == Common.RESPONSE_STATUS__EXCEPTION)
            {
                string exMsg;
                Stream.Read(out exMsg);
                int idx = exMsg.IndexOf(":");
                if (idx < 0)
                    throw new Exception("Could not parse exception message");
                string expClassName = exMsg.Substring(0, idx);
                string msg = exMsg.Substring(idx + 1);

                Exception = new Exception(exMsg);
            }
            else
            {
                Exception = null;
            }
        }
    }


    public abstract class OutMessage : MoraMessage
    {
        public IMoraOutputStream Stream { get; protected set; }

        public RemoteCommunicator Target { get; }
        
        protected OutMessage(short magic, byte type, RemoteCommunicator dst)
        : base(magic, type)
        {
            Stream = new MoraByteArrayOutputStream();
            Target = dst;

            Stream.Write(type);
            Stream.Write(magic);
        }
    }


    public class OutMethodCall : OutMessage
    {
        public RemoteMethod RemoteMethod { get; }

        public OutMethodCall(short magic, RemoteMethod method, RemoteCommunicator responseCommunicator)
            : base(magic, Common.MESSAGE_TYPE__METHOD_CALL, method.Instance.Communicator)
        {
            RemoteMethod = method;

            Stream.Write(Common.ProtocolToByte(responseCommunicator.Protocol));
            Stream.Write(responseCommunicator.Port);

            byte[] buffer = method.GetStreamContent();
            Stream.Write(buffer, 0, buffer.Length);
        }
    }

    public class OutMethodResponse : OutMessage
    {
        public byte StatusFlag { get; }
        public Exception Exception { get; }
        

        public OutMethodResponse(InMethodCall call)
            : base(call.Magic, Common.MESSAGE_TYPE__RESPONSE, call.ResponseCommunicator)
        {
            Stream.Write((byte)0); //default flag
        }

        public OutMethodResponse(InMethodCall call, Exception e)
            : base(call.Magic, Common.MESSAGE_TYPE__RESPONSE, call.ResponseCommunicator)
        {
            if (e != null)
            {
                StatusFlag = Common.RESPONSE_STATUS__EXCEPTION;
                Stream.Write(StatusFlag);

                Stream.Write(e.GetType().FullName + ":" + e.Message);
            }
        }
    }
    
}
