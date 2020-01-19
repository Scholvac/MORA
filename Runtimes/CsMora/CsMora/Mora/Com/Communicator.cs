using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Mora.Com
{

    public class Options
    {
        public PROTOCOL Protocol;
        public int Port;
        public IPAddress Adr;
        /**
		 * requested maximum length of the queue of incoming
		 * connections.
		 */
        public int backlog = 0;
        public int workerThreads = 5;
        public int maxConnectionsPerClient = 5;
        /** Timeout in milliseconds */
        public long timeout = 30;
    }
    public class Communicator
    {
        private IServer mServer;
        private ClientConnections mClients;

        public RemoteCommunicator ResponseCommunicator
        {
            get
            {
                return mServer.GetResponseCommunicator();
            }
        }

        private Dictionary<string, IMoraAdapter> mAdapter = new Dictionary<string, IMoraAdapter>();

        private Dictionary<string, IMoraProxy> mProxies = new Dictionary<string, IMoraProxy>();
        private Dictionary<short, IRemoteMethodCall> mOpenCalls = new Dictionary<short, IRemoteMethodCall>();

        public Options Options { get; }


        public Communicator(Options opt)
        {
            Options = opt;
        }

        public void Start()
        {
            if (mServer != null)
                throw new Exception("Server already started");

            if (Options.Protocol == PROTOCOL.TCP)
                mServer = new TCP.TCPServer();
            else if (Options.Protocol == PROTOCOL.UDP)
                mServer = new UDP.UDPServer();
            else
                Console.Error.WriteLine("Protocol: " + Options.Protocol + " not yet implemented");
            mServer.StartListening(this);

            mClients = new ClientConnections(Options.maxConnectionsPerClient);
        }

        public void Stop()
        {
            if (mServer == null)
                return;
            mServer.StopListening();
            mServer = null;

            mClients.CloseAll();
            mClients = null;

            mAdapter.Clear();
            mProxies.Clear();
            mOpenCalls.Clear();
        }


        internal void HandleIncomingMessage(InMessageCollection mc)
        {
            byte flags;
            mc.Stream.Read(out flags);
            short magic;
            mc.Stream.Read(out magic);

            if ((flags & Common.MESSAGE_TYPE__RESPONSE) == Common.MESSAGE_TYPE__RESPONSE)
            {
                InMethodRespond respMsg = new InMethodRespond(magic, flags, mc);
                handleResponse(respMsg);
            }
            else if ((flags & Common.MESSAGE_TYPE__METHOD_CALL) == Common.MESSAGE_TYPE__METHOD_CALL)
            {
                InMethodCall call = new InMethodCall(magic, flags, mc);
                handleCall(call);
            }
            else
            {
                throw new Exception("Don't know how to handle flag: " + flags);
            }
        }

        public T registerAdapter<T>(T adapter) where T : IMoraAdapter
        {
            if (hasAdapter(adapter))
            {
                IMoraAdapter res = getAdapter(adapter);
                return (T)res;
            }

            mAdapter.Add(adapter.getIdentifier(), adapter);
            return adapter;
        }
        public bool hasAdapter(object adapter)
        {
            return getAdapter(adapter) != null;
        }
        public IMoraAdapter getAdapter(object adapter)
        {
            foreach (IMoraAdapter a in mAdapter.Values)
            {
                if (a.represents(adapter))
                    return a;
            }
            return null;
        }
        public IMoraAdapter getAdapter(string objectIdentifier)
        {
            IMoraAdapter res;
            if (mAdapter.TryGetValue(objectIdentifier, out res))
                return res;
            return null;
        }


        public IMoraProxy registerProxy(IMoraProxy proxy)
        {
            mProxies.Add(proxy.RemoteObject.Identifier, proxy);
            return proxy;
        }
        public IMoraProxy getProxy(string objectIdentifier)
        {
            IMoraProxy res;
            if (mProxies.TryGetValue(objectIdentifier, out res))
                return res;
            return null;
        }


        internal OutMethodCall createCall(RemoteMethod remoteMethod, short magic)
        {
            return new OutMethodCall(magic, remoteMethod, ResponseCommunicator);
        }

        public bool call(IRemoteMethodCall call)
        {
            short magic = call.Magic;
            mOpenCalls.Add(magic, call);
            return mClients.send_sync(call.getMessage());
        }

        private void handleResponse(InMethodRespond msg)
        {
            short magic = msg.Magic;
            IRemoteMethodCall remoteCall;
            if (mOpenCalls.TryGetValue(magic, out remoteCall))
            {
                mOpenCalls.Remove(magic);
                remoteCall.HandleResponse(msg, this);
            }
            else
            {
                Console.Error.WriteLine("No open call for message: " + magic);
            }

        }

        internal void CloseConnections(IPAddress address)
        {
            mClients.CloseStack(address.ToString());
        }

        private void handleCall(InMethodCall call)
        {
            //this method decouples the method handling from the socket receiving thread
            Task.Run(() =>
            {
                MethodHandler.invoke(call, this);
            }).ConfigureAwait(false); //fire and forget - the invoke method handles exceptions
        }





        internal bool sendResponse(OutMethodResponse response)
        {
            return mClients.send_sync(response);
        }






    }
}
