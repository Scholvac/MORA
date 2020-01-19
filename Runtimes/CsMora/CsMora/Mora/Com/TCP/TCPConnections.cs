using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Mora.Com.TCP
{


    public class TCPConnection : AbstractConnection
    {
        private Socket mSocket;
        private bool requestAck = true;
        private byte[] mAckBuffer = new byte[4];
        public TCPConnection(RemoteCommunicator com, int idx) : base(idx)
        {
            mSocket = new Socket(com.Address.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
            mSocket.Connect(com.Address, com.Port);
        }

        public override int getMaximumMessageSize()
        {
            return 10 * 1024 * 1024;//10MB
        }

        public override bool Send(OutMsg msg)
        {
            try
            {
                msg.RequestAcknowledge(requestAck);
                int l = mSocket.Send(msg.data);
                if (requestAck)
                {
                    int readBytes = mSocket.Receive(mAckBuffer);
                    if (msg.CheckResponse(mAckBuffer))
                        return true;
                    return false;
                }
                return l == msg.data.Length;
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                return false;
            }
        }

        internal override void Close()
        {
            mSocket.Close();
            mSocket = null;
        }

    }

    public class TCPServer : IServer
    {
        private bool mAlive = false;
        private Thread mServerThread;
        public static ManualResetEvent allDone = new ManualResetEvent(false);
        private Communicator mCommunicator;
        private IPEndPoint mEndpoint;
        private RemoteCommunicator mResponseCommunicator;


        public void StartListening(Communicator communicator)
        {
            if (mAlive == true)
            {
                throw new Exception("Server already started");
            }
            mAlive = true;
            mCommunicator = communicator;
            mServerThread = new Thread(new ThreadStart(this.runServer));
            mServerThread.Start();
        }

        public void StopListening()
        {
            mAlive = false;
            if (mServerThread != null)
            {
                allDone.Set();
                mSocketListener.Close();
                //send some null-message to the socket
                mServerThread.Join();
                mServerThread = null;
            }
        }

        public IPEndPoint LocalEndpoint()
        {
            return mEndpoint;
        }
        public RemoteCommunicator GetResponseCommunicator()
        {
            if (mResponseCommunicator == null && LocalEndpoint() != null)
            {
                mResponseCommunicator = new RemoteCommunicator(LocalEndpoint().Address, LocalEndpoint().Port, PROTOCOL.TCP);
            }
            return mResponseCommunicator;
        }






        private void runServer()
        {
            Options opt = mCommunicator.Options;
            // Create a TCP/IP socket.  
            mSocketListener = new Socket(opt.Adr.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
            int port = opt.Port;
            if (port < 0)
                port = 0;
            IPEndPoint localEndPoint = new IPEndPoint(opt.Adr, opt.Port);
            try
            {
                mSocketListener.Bind(localEndPoint);
                mSocketListener.Listen(opt.backlog);
                mEndpoint = mSocketListener.LocalEndPoint as IPEndPoint;
                while (mAlive)
                {
                    // Set the event to nonsignaled state.
                    allDone.Reset();
                    Console.WriteLine("Waiting for connection on: " + localEndPoint);

                    mSocketListener.BeginAccept(new AsyncCallback(ConnectionHandler), mSocketListener);
                    // Wait until a connection is made before continuing.  
                    allDone.WaitOne();
                }
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
            }

        }

        private void ConnectionHandler(IAsyncResult ar)
        {
            allDone.Set();
            // Get the socket that handles the client request.  
            Socket listener = (Socket)ar.AsyncState;
            Socket handler = listener.EndAccept(ar);

            IPEndPoint remoteEndpoint = (IPEndPoint)handler.RemoteEndPoint;
            byte[] buffer = new byte[1024];
            int state = 0;
            int bytesRead = 0;

            InMsg msg = null;
            Boolean socketAlive = true;
            int reqRead = Common.NET_MESSAGES__HEADER_LENGTH;
            while (mAlive && socketAlive == true)
            {
                while (bytesRead <= reqRead)
                {
                    try
                    {
                        int tmp = handler.Receive(buffer, bytesRead, 1024 - bytesRead, SocketFlags.None);
                        bytesRead += tmp;
                    }
                    catch (SocketException se)
                    {
                        if (se.ErrorCode == 10054)
                        { //connection has been closed by the remote host
                            Console.WriteLine("Connection has been closed by the remote host");
                            mCommunicator.CloseConnections(remoteEndpoint.Address);
                            socketAlive = false;
                            break;
                        }
                        else
                        {
                            Console.WriteLine(se.StackTrace);
                        }
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine(e.StackTrace);
                    }
                }
                if (bytesRead > 0)
                {
                    switch (state)
                    {
                        case 0:
                            msg = new InMsg(buffer, bytesRead);
                            bytesRead = 0;
                            break;
                        case 1:
                            byte[] remaining = msg.Append(buffer, bytesRead);
                            if (remaining != null)
                            {
                                Array.Copy(remaining, 0, buffer, 0, remaining.Length);
                                bytesRead = remaining.Length;
                            }
                            else
                                bytesRead = 0;
                            break;
                    }

                    if (msg.complete())
                    {
                        handleNetMessage(msg, handler.RemoteEndPoint);
                        if (msg.RequestsAck())
                        {
                            byte[] ackMsg = msg.AckMessage();
                            handler.Send(ackMsg);
                        }
                        state = 0;
                        reqRead = Common.NET_MESSAGES__HEADER_LENGTH;
                        msg = null;
                    }
                    else
                    {
                        state = 1;
                        reqRead = 0;
                    }
                }
            }
        }

        private Dictionary<byte, InMessageCollection> mCollections = new Dictionary<byte, InMessageCollection>();
        private Socket mSocketListener;

        private void handleNetMessage(InMsg msg, EndPoint remoteEndPoint)
        {
            InMessageCollection mc;
            if (mCollections.ContainsKey(msg.messageMagic) == false)
            {
                mCollections.Add(msg.messageMagic, mc = new InMessageCollection(msg, remoteEndPoint));
            }
            else
            {
                mc = mCollections[msg.messageMagic];
                mc.Append(msg);
            }
            if (mc.IsComplete())
            {
                mCollections.Remove(msg.messageMagic);
                mc.BuildStream();
                mCommunicator.HandleIncomingMessage(mc);
            }
        }


    }
}
