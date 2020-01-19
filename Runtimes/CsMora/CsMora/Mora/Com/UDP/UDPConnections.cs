using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Mora.Com.UDP
{


    public class UDPConnection : AbstractConnection
    {
        private Socket mSocket;
        public UDPConnection(RemoteCommunicator com, int idx) : base(idx)
        {
            mSocket = new Socket(com.Address.AddressFamily, SocketType.Dgram, ProtocolType.Udp);
            mSocket.Connect(com.Address, com.Port);
        }

        public override int getMaximumMessageSize()
        {
            return 64000;
        }

        public override bool Send(OutMsg msg)
        {
            int l = mSocket.Send(msg.data);
            return l == msg.data.Length;
        }

        internal override void Close()
        {
            mSocket.Close();
            mSocket = null;
        }

    }

    public class UDPServer : IServer
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
                mResponseCommunicator = new RemoteCommunicator(LocalEndpoint().Address, LocalEndpoint().Port, PROTOCOL.UDP);
            }
            return mResponseCommunicator;
        }






        private void runServer()
        {
            Options opt = mCommunicator.Options;

            
            // Create a TCP/IP socket.  
            //Socket socketListener = new Socket(opt.Adr.AddressFamily, SocketType.Dgram, ProtocolType.Udp);
            int port = opt.Port;
            if (port < 0)
                port = 0;
            IPEndPoint localEndPoint = new IPEndPoint(opt.Adr, opt.Port);
            try
            {
                UdpClient listener = new UdpClient(localEndPoint);
                mEndpoint = listener.Client.LocalEndPoint as IPEndPoint;
                IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, port);
                int bytesRead = 0;
                InMsg msg = null;
                int state = 0;
                while (mAlive)
                {
                    byte[] buffer = listener.Receive(ref remoteEP);
                    if (buffer.Length > 0)
                    {
                        bytesRead = buffer.Length;
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
                            handleNetMessage(msg, remoteEP);
                            if (msg.RequestsAck())
                            {
                                byte[] ackMsg = msg.AckMessage();
                            }
                            state = 0;
                            msg = null;
                        }
                        else
                            state = 1;
                    }
                }
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
            }
        }

        private Dictionary<byte, InMessageCollection> mCollections = new Dictionary<byte, InMessageCollection>();
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
