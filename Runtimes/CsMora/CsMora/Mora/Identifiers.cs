using Mora.Com;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;

namespace Mora
{
    public enum PROTOCOL
    {
        TCP, UDP
    }
    public class RemoteCommunicator
    {
        public IPAddress Address { get; }
        public int Port { get; }
        public PROTOCOL Protocol {get;}
        private string mIdentifier;
        public string Identifier { get {
                if (mIdentifier == null)
                {
                    mIdentifier = Common.ProtocolToString(Protocol)+"://"+ Address.ToString() + ":" + Port;
                }
                return mIdentifier;
            } }

        public RemoteCommunicator(IPAddress adr, int port, PROTOCOL prot)
        {
            Address = adr;
            Port = port;
            Protocol = prot;
        }

        internal static RemoteCommunicator create(string adr)
        {
            int idx = adr.IndexOf("//");
            if (idx < 0)
                return null;
            int idx2 = adr.IndexOf(':', idx);
            if (idx2 < 0)
                return null;
            string prot = adr.Substring(0, idx - 1);
            string host = adr.Substring(idx + 2, (idx2-(idx+2)));
            string port = adr.Substring(idx2 + 1);
            try
            {
                var _host = IPAddress.Parse(host);
                var _port = int.Parse(port);
                var _prot = Common.StringToProtocol(prot);
                return new RemoteCommunicator(IPAddress.Parse(host), int.Parse(port), Common.StringToProtocol(prot));
            }
            catch (Exception e)
            {
                throw new Exception("Failed to parse remote communicator from: " + adr, e);
            }
        }

        public override string ToString()
        {
            return Identifier;
        }
    }

    public class RemoteObject
    {
        public RemoteCommunicator     Communicator { get; }
		public String                 ObjectIdentifier { get; }
		
		private String mIdentifier;

        public string Identifier { get
            {
                if (mIdentifier == null)
                {
                    mIdentifier = Communicator.Identifier + "/" + ObjectIdentifier;
                }
                return mIdentifier;
            } }
        

        public RemoteObject(RemoteCommunicator com, String id)
        {
            Communicator = com;
            ObjectIdentifier = id;
        }

        public static RemoteObject create(string adr)
        {
            int idx = adr.LastIndexOf('/');
            if (idx < 0)
                return null;
            RemoteCommunicator rc = RemoteCommunicator.create(adr.Substring(0, idx));
            return new RemoteObject(rc, adr.Substring(idx + 1));
        }
    }

    public class RemoteMethod
    {
        public RemoteObject Instance { get; }
        public String Signature { get; }

        private byte[] mStreamContent;
        public RemoteMethod(RemoteObject inst, String sig)
        {
            Instance = inst;
            Signature = sig;
        }

        internal byte[] GetStreamContent()
        {
            if (mStreamContent == null)
            {
                Encoding.Write(Instance.ObjectIdentifier + ":" + Signature, out mStreamContent);
            }
            return mStreamContent;
        }
    }
}
