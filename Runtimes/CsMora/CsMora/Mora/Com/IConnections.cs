using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;

namespace Mora.Com
{
    interface IServer
    {
        void StartListening(Communicator communicator);
        void StopListening();
        RemoteCommunicator GetResponseCommunicator();
    }

    public abstract class AbstractConnection
    {
        public bool Used { get; internal set; }

        public int Index { get; }

        protected AbstractConnection(int idx)
        {
            Index = idx;
        }


        public void Relsease()
        {
            Used = false;
        }
        public abstract int getMaximumMessageSize();

        public abstract bool Send(OutMsg msg);

        internal abstract void Close();
    }
}
