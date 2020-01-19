/*
 * Created by SharpDevelop.
 * User: sschweigert
 * Date: 30.09.2019
 * Time: 15:01
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using NetMQ.Sockets;
using NetMQ;
using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using System.Collections;
using System.Net.Sockets;

namespace RPCLibrary
{
    public class RPCException : Exception
    {
        public RPCException() : base() { }

        public RPCException(string message) : base(message) { }

        public RPCException(string message, Exception innerException) : base(message, innerException) { }
    }

    public class RPCAdapterException : Exception
    {
        public RPCAdapterException() : base() { }

        public RPCAdapterException(string message) : base(message) { }

        public RPCAdapterException(string message, Exception innerException) : base(message, innerException) { }
    }


    public interface IRPCProxy
    {
        string getQualifiedIdentifier();
    }
    public abstract class RPCProxy<T> : IRPCProxy
    {
        private RPCCommunicator mCommunicator;
        private string mQualifiedAddress;
        private string mClientIdentifier;
        private string mClientAddress;

        public RPCCommunicator Communicator { get { return mCommunicator; } }
        public string QualifiedAddress { get { return mQualifiedAddress; } }
        public string ClientIdentifier { get { return mClientIdentifier; } }
        public string ClientAddress { get { return mClientAddress; } }

        public RPCProxy(RPCCommunicator communicator, string qualifiedAddress)
        {
            mCommunicator = communicator;
            mQualifiedAddress = qualifiedAddress;

            int idx1 = qualifiedAddress.IndexOf(':');
            if (idx1 < 0)
                throw new RPCAdapterException("Missing target port section in: " + qualifiedAddress);
            int idx2 = qualifiedAddress.IndexOf('/', idx1 + 3);
            if (idx2 < 0)
                throw new RPCAdapterException("Missing client identifier in: " + qualifiedAddress);
            mClientIdentifier = qualifiedAddress.Substring(idx2 + 1);
            mClientAddress = qualifiedAddress.Substring(0, idx2);
        }

        public string getQualifiedIdentifier()
        {
            return mQualifiedAddress;
        }
        public abstract string GetTypeIdentifier();

        public override bool Equals(object obj)
        {
            if (obj is RPCProxy<T>)
            {
                return (obj as RPCProxy<T>).mQualifiedAddress.Equals(mQualifiedAddress);
            }
            return false;
        }
        public override int GetHashCode()
        {
            return mQualifiedAddress.GetHashCode();
        }
        public override string ToString()
        {
            return "Proxy [" + mQualifiedAddress + "]";
        }
    }

    public interface IRPCMethodInvokation<D>
    {
        RPCInternalMethodResult Invoke(D deleg, RPCIncommingMethodCall call, RPCCommunicator communicator);
    }

    public interface IRPCAdapter
    {
        RPCInternalMethodResult Invoke(RPCIncommingMethodCall call, RPCCommunicator mCommunicator);

        bool represents(object iface);
        string getIdentifier();
    }
    public abstract class RPCAdapter<T> : IRPCAdapter
    {
        private RPCCommunicator mCommunicator;
        private String mIdentifier;

        protected T mDelegate;

        protected RPCAdapter(RPCCommunicator com, T dele, string id)
        {
            mCommunicator = com;
            mDelegate = dele;
            mIdentifier = id;
        }

        public T getDelegate() { return mDelegate; }
        public string getIdentifier() { return mIdentifier; }

        public bool represents(object iface)
        {
            return getDelegate().Equals(iface);
        }

        protected abstract IRPCMethodInvokation<T> getInvoker(string methodName);

        public RPCInternalMethodResult Invoke(RPCIncommingMethodCall call, RPCCommunicator communicator)
        {
            IRPCMethodInvokation<T> invoker = getInvoker(call.MethodName);
            if (invoker == null)
            {
                throw new RPCAdapterException("Could not find a method with signature: " + call.MethodName);
            }
            RPCInternalMethodResult res = invoker.Invoke(mDelegate, call, communicator);
            return res;
        }
    }

    public class RPCServer
    {
        private Thread mListenerThread;
        private List<ThreadWorker> mWorkerThreads;
        private RPCCommunicator mCommunicator;
        private string mEndPoint;
        private bool mAlive = true;
        private Proxy mProxy;
        private RouterSocket mFrontend;
        private DealerSocket mBackend;

        public void Start(RPCCommunicator com)
        {
            mCommunicator = com;
            mListenerThread = new Thread(new ThreadStart(this.serverTask));
            mListenerThread.Start();
        }

        public void Stop()
        {
            mAlive = false;

            for (int i = 0; i < mWorkerThreads.Count; i++)
            {
                if (!mWorkerThreads[i].Stop(300))
                {
                    Console.Error.WriteLine("Failed to stop ThreadWorker:" + mWorkerThreads[i]);
                }
            }

            mProxy.Stop();
            if (mListenerThread.Join(300) == false)
            {
                mListenerThread.Interrupt();
                mListenerThread.Join(300);
            }
        }

        public string EndPoint { get { return mEndPoint; } }

        private void serverTask()
        {
            RPCCommunicator.Options opt = mCommunicator.Opt;
            String adr = "";
            switch (opt.Protocol)
            {
                case RPCCommunicator.PROTOCOL.PROCESS: adr += "inproc"; break;
                case RPCCommunicator.PROTOCOL.TCP: adr += "tcp"; break;
                case RPCCommunicator.PROTOCOL.UDP: throw new NotImplementedException();
                default: throw new NotImplementedException();
            }
            adr += "://";
            if (opt.IP == null || opt.IP.Length == 0)
                adr += "*";
            else
                adr += opt.IP;
            adr += ":";
            if (opt.Port > 0)
                adr += opt.Port;
            else
                adr += "*";


            using (mFrontend = new RouterSocket())
            using (mBackend = new DealerSocket())
            {
                mFrontend.Bind(adr);
                mEndPoint = mFrontend.Options.LastEndpoint;
                Console.WriteLine("Start Router at: " + mEndPoint);

                mBackend.Bind("inproc://server_backend");

                mWorkerThreads = new List<ThreadWorker>();
                for (int i = 0; i < opt.ServerThreadCount; i++)
                {
                    ThreadWorker tw = new ThreadWorker(mCommunicator);
                    mWorkerThreads.Add(tw);
                    tw.Start(i);
                }
                mProxy = new Proxy(mFrontend, mBackend);
                mProxy.Start();
            }
        }

        class ThreadWorker
        {
            private Thread mThread = null;
            private Boolean mAlive = true;
            private DealerSocket mWorker;
            private RPCCommunicator mCommunicator;

            public ThreadWorker(RPCCommunicator com)
            {
                mCommunicator = com;
            }

            public void Start(int num)
            {
                mThread = new Thread(() => Run());
                mThread.Name = "ThreadWorker_" + num;
                mThread.Start();
            }
            public bool Stop(int millis)
            {
                mAlive = false;
                return mThread.Join(millis);
            }
            public void Run()
            {
                using (mWorker = new DealerSocket())
                {
                    mWorker.Connect("inproc://server_backend");
                    Console.WriteLine("Started ThreadWorkder: " + mThread.Name);
                    TimeSpan timeout = new TimeSpan(0, 0, 0, 0, 100);//100[ms]
                    while (mAlive)
                    {
                        try
                        {
                            var msg = new NetMQMessage();

                            if (mWorker.TryReceiveMultipartMessage(timeout, ref msg) == false)
                                continue;
                            //var msg = mWorker.ReceiveMultipartMessage();
                            var adr = msg.Pop();
                            var content = msg.Pop();
                            msg.Clear();

                            byte[] resp = handleMessage(content);
                            var outMsg = new NetMQMessage();
                            outMsg.Append(adr);

                            if (resp != null && resp.Length > 0)
                                outMsg.Append(resp);
                            else
                                outMsg.AppendEmptyFrame();
                            mWorker.SendMultipartMessage(outMsg);
                        }
                        catch (Exception e)
                        {
                            //send something anyhow
                            Console.WriteLine("Error: " + e);
                            mWorker.SendFrameEmpty();
                        }
                    }
                    mWorker.Close();
                    Console.WriteLine("Closed ThreadWorkder: " + mThread.Name);
                }
            }

            private byte[] handleMessage(NetMQFrame msg)
            {
                var call = new RPCIncommingMethodCall(msg.Buffer);
                //Console.WriteLine("Handle Message: " + call.TargetIdentifier + "/" + call.MethodName);
                var adapter = mCommunicator.getAdapter(call.TargetIdentifier);
                if (adapter != null)
                {
                    RPCInternalMethodResult result = adapter.Invoke(call, mCommunicator);
                    if (result != null)
                    {
                        RPCMethodCallResponse resp;
                        if (result.Throwable != null)
                        {
                            resp = new RPCMethodCallResponse(call.Magic, RPCConstants.RESPONSE_TYPE_ERROR);
                            //TODO: set the exception or even the stack - trace
                        }
                        else
                        {
                            resp = new RPCMethodCallResponse(call.Magic, RPCConstants.RESPONSE_TYPE_OK);
                            if (result.Value != null)
                            {
                                if (result.Value is IList)
                                    result.Encoder.EncodeList(result.Value as IList, resp.writer, mCommunicator);
                                else
                                    result.Encoder.Encode(result.Value, resp.writer, mCommunicator);
                            }
                        }
                        byte[] resp_data = resp.byteArray.ToArray();
                        return resp_data;
                    }
                }
                else
                {
                    Console.Error.WriteLine("Could not find adapter: " + call.TargetIdentifier);
                }
                return new byte[] { };
            }
        }


        void Run()
        {
            RPCCommunicator.Options opt = mCommunicator.Opt;
            String adr = "";
            switch (opt.Protocol)
            {
                case RPCCommunicator.PROTOCOL.PROCESS: adr += "inproc"; break;
                case RPCCommunicator.PROTOCOL.TCP: adr += "tcp"; break;
                case RPCCommunicator.PROTOCOL.UDP: throw new NotImplementedException();
                default: throw new NotImplementedException();
            }
            adr += "://";
            if (opt.IP == null || opt.IP.Length == 0)
                adr += "*";
            else
                adr += opt.IP;
            adr += ":";
            if (opt.Port > 0)
                adr += opt.Port;
            else
                adr += "*";

            using (var responseSocket = new ResponseSocket())
            {
                responseSocket.Bind(adr);
                mEndPoint = responseSocket.Options.LastEndpoint;
                Console.WriteLine("Bind Server to: " + mEndPoint);

                while (mAlive)
                {
                    try
                    {
                        Msg msg = new Msg();
                        msg.InitEmpty();
                        responseSocket.Receive(ref msg);

                        //byte[] data = server.ReceiveFrameBytes();
                        var t = Task.Factory.StartNew(() =>
                        {
                            var call = new RPCIncommingMethodCall(msg.Data);
                            var adapter = mCommunicator.getAdapter(call.TargetIdentifier);
                            if (adapter != null)
                            {
                                RPCInternalMethodResult result = adapter.Invoke(call, mCommunicator);
                                if (result != null)
                                {
                                    RPCMethodCallResponse resp;
                                    if (result.Throwable != null)
                                    {
                                        resp = new RPCMethodCallResponse(call.Magic, RPCConstants.RESPONSE_TYPE_ERROR);
                                        //TODO: set the exception or even the stack - trace
                                    }
                                    else
                                    {
                                        resp = new RPCMethodCallResponse(call.Magic, RPCConstants.RESPONSE_TYPE_OK);
                                        if (result.Value != null)
                                        {
                                            if (result.Value is IList)
                                                result.Encoder.EncodeList(result.Value as IList, resp.writer, mCommunicator);
                                            else
                                                result.Encoder.Encode(result.Value, resp.writer, mCommunicator);
                                        }
                                    }
                                    byte[] resp_data = resp.byteArray.ToArray();
                                    responseSocket.SendFrame(resp_data);
                                }
                            }
                            else
                            {
                                Console.Error.WriteLine("Could not find adapter: " + call.TargetIdentifier);
                            }
                        }
                        );
                        if (t != null)
                        {
                            responseSocket.SendFrameEmpty();
                        }
                    }
                    catch (Exception exp)
                    {
                        Console.WriteLine("Error: " + exp);
                    }
                }
            }
        }


    }



    public class RPCClientConnection
    {

        private String mAddress;
        private DealerSocket mSocket;

        public RPCClientConnection(String address)
        {
            mAddress = address;
        }

        public bool connect()
        {
            if (mSocket != null)
                return true;
            mSocket = new DealerSocket();
            mSocket.Connect(mAddress);
            return true;
        }

        public RPCIncommingMethodResponse call(RPCMethodCallRequest call)
        {
            if (mSocket == null)
            {
                if (!connect())
                    return null;
            }
            mSocket.SendFrame(call.byteArray.ToArray());
            Msg repl = new Msg();
            repl.InitEmpty();
            mSocket.Receive(ref repl);
            RPCIncommingMethodResponse resp = new RPCIncommingMethodResponse(repl.Data);
            repl.Close();
            return resp;
        }

        internal void Stop()
        {
            mSocket.Close();
        }
    }

    class ClientConnectionStack
    {

        public string address;
        public RPCClientConnection[] connections;
        public int index;

        public ClientConnectionStack(string adr, int num)
        {
            address = adr;
            connections = new RPCClientConnection[num];
            index = 0;
        }

        public RPCClientConnection get()
        {
            RPCClientConnection cc = connections[index];
            if (cc == null)
            {
                cc = new RPCClientConnection(address);
                connections[index] = cc;
            }
            index++;
            return cc;
        }

        public void release(RPCClientConnection cc)
        {
            index--;
        }

        internal void Stop()
        {
            foreach (var cc in connections)
            {
                if (cc != null)
                    cc.Stop();
            }
        }
    }


    /// <summary>
    /// Description of MyClass.
    /// </summary>
    public class RPCCommunicator
    {
        public enum PROTOCOL
        {
            TCP, UDP, PROCESS
        }
        public class Options
        {
            public int Port = -1;
            public String IP = "localhost";
            public PROTOCOL Protocol = PROTOCOL.TCP;
            public int ClientThreads = 5;
            public int ServerThreadCount = 5;
            public long Timeout = 30;
        }
        private Options opt;
        private RPCServer mServer;

        public Options Opt { get { return opt; } }

        public string ServerAddress
        {
            get { return mServer.EndPoint; }
        }

        private Dictionary<string, IRPCAdapter> mAdapters = new Dictionary<string, IRPCAdapter>();
        private Dictionary<string, IRPCProxy> mProxies = new Dictionary<string, IRPCProxy>();

        private Dictionary<String, ClientConnectionStack> mConnectionStacks = new Dictionary<string, ClientConnectionStack>();

        public RPCCommunicator(Options opt)
        {
            this.opt = opt;
            mServer = new RPCServer();
        }

        public void Start()
        {
            mServer.Start(this);
        }

        public void Stop()
        {
            mServer.Stop();
            foreach (var ccs in mConnectionStacks)
            {
                ccs.Value.Stop();
            }
        }

        private ClientConnectionStack getClientStack(String adr)
        {
            ClientConnectionStack ccs = null;
            if (mConnectionStacks.TryGetValue(adr, out ccs) == false)
            {
                mConnectionStacks.Add(adr, ccs = new ClientConnectionStack(adr, opt.ClientThreads));
            }
            return ccs;
        }

        public Task<RPCIncommingMethodResponse> Call(RPCMethodCallRequest call)
        {
            return Task.Factory.StartNew(() => {
                var ccs = getClientStack(call.ClientAddress);
                var cs = ccs.get();
                var resp = cs.call(call);
                ccs.release(cs);
                return resp;
            });
        }

        public IRPCAdapter getAdapter(string identifier)
        {
            IRPCAdapter result = null;
            if (mAdapters.TryGetValue(identifier, out result))
                return result;
            return null;
        }


        public bool hasAdapter(string identifier)
        {
            return mAdapters.ContainsKey(identifier);
        }

        public T registerAdapter<T>(T adapter) where T : IRPCAdapter
        {
            mAdapters.Add(adapter.getIdentifier(), adapter);
            return adapter;
        }
        public IRPCAdapter removeAdapter<T>(T adapter) where T : IRPCAdapter
        {
            if (adapter == null)
                return null;
            var key = adapter.getIdentifier();
            if (mAdapters.Remove(key))
                return adapter;
            return null;
        }

        public IRPCProxy getProxy(string quid)
        {
            IRPCProxy value;
            if (mProxies.TryGetValue(quid, out value))
                return value;
            return null;
        }

        public void registerProxy(IRPCProxy proxy)
        {
            mProxies.Add(proxy.getQualifiedIdentifier(), proxy);
        }


    }
}