using Mora.Com.TCP;
using Mora.Com.UDP;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Mora.Com
{
    public class ConnectionStack
    {
        private RemoteCommunicator    mTarget;
		
		private AbstractConnection[]  mConnections;
		
		
		public ConnectionStack(RemoteCommunicator com, int maxConnections)
        {
            mTarget = com;
            mConnections = new AbstractConnection[maxConnections];
        }

        public AbstractConnection get()
        {
            for (int i = 0; i < mConnections.Length; i++)
            {
                AbstractConnection con = mConnections[i];
                if (con == null)
                    mConnections[i] = con = ClientConnections.createConnection(mTarget, i);
                if (con.Used == false)
                {
                    con.Used = true;
                    return con;
                }
            }
            return null; //all available connections are in use - wait until a connection has been released
        }

        internal void Shutdown()
        {
            for (int i = 0; i < mConnections.Length; i++)
            {
                if (mConnections[i] != null)
                    mConnections[i].Close();
            }
        }
    }

    public class ClientConnections
    {
        private int maxConnectionsPerClient = 5;
        private Dictionary<string, ConnectionStack> mConnectionStacks = new Dictionary<string, ConnectionStack>();

        public ClientConnections(int maxConnectionsPerClient)
        {
            this.maxConnectionsPerClient = maxConnectionsPerClient;
        }

        internal static AbstractConnection createConnection(RemoteCommunicator target, int index)
        {
            try
            {
                switch (target.Protocol)
                {
                    case PROTOCOL.TCP:
                        return new TCPConnection(target, index);
                    case PROTOCOL.UDP:
                        return new UDPConnection(target, index);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            return null;
        }

        internal void CloseAll()
        {
            while(mConnectionStacks.Count > 0)
            {
                var k = mConnectionStacks.First().Key;
                CloseStack(k);
            }
        }

        private static bool Send(ConnectionStack connectionStack, OutMessage msg, int maxAttempts)
        {
            AbstractConnection con = connectionStack.get();
            int attempts = 0;
            while (con == null && attempts < maxAttempts)
            { //for the case, that all connections are in use
                attempts++;
                Thread.Sleep(5);
                con = connectionStack.get();
            }
            try
            {
                List<OutMsg> messages = OutMsg.split(msg.Stream.getBuffer(), msg.Stream.getSize(), con.getMaximumMessageSize());
                for (int i = 0; i < messages.Count; i++)
                {
                    attempts = maxAttempts;
                    while (attempts-- > 0)
                    {
                        if (con.Send(messages[i]))
                            break;
                    }
                    if (attempts <= 0)
                    {
                        con.Relsease();
                        return false;
                    }
                }
                con.Relsease();
                return true;
            }
            catch (Exception t)
            {
                Console.Error.WriteLine(t.ToString());
            }
            con.Relsease();
            return false;
        }

        public ConnectionStack getConnectionStack(RemoteCommunicator com)
        {
            ConnectionStack stack;
            mConnectionStacks.TryGetValue(com.Identifier, out stack);
            if (stack == null)
            {
                mConnectionStacks.Add(com.Identifier, stack = new ConnectionStack(com, maxConnectionsPerClient));
            }
            return stack;
        }

        public bool send_sync(OutMessage message)
        {
            ConnectionStack stack = getConnectionStack(message.Target);
            return Send(stack, message, 3);
        }

        internal void CloseStack(string address)
        {
            List<string> toRemove = new List<string>();
            foreach( var key in mConnectionStacks.Keys)
            {
                if (key.Contains(address))
                {
                    toRemove.Add(key);
                }
            }
            for (int i = 0; i < toRemove.Count; i++)
            {
                ConnectionStack stack = mConnectionStacks[toRemove[i]];
                mConnectionStacks.Remove(toRemove[i]);
                stack.Shutdown();
            }
        }
    }
}
