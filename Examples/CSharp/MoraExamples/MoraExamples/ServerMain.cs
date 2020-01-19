using De.Sos.MORA.Examples.Serialize;
using Mora.Com;
using MoraExamples.De.Sos.MORA.Examples.serialize;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;

namespace MoraExamples
{

    class MyManager : EchoManagerRPC.IFace
    {
        public bool echo(bool _value_)
        {
            return _value_;
        }

        public byte echo(byte _value_)
        {
            return _value_;
        }

        public short echo(short _value_)
        {
            return _value_;
        }

        public int echo(int _value_)
        {
            return _value_;
        }

        public long echo(long _value_)
        {
            return _value_;
        }

        public float echo(float _value_)
        {
            return _value_;
        }

        public double echo(double _value_)
        {
            return _value_;
        }

        public string echo(string _value_)
        {
            return _value_;
        }

        public MyEnum echo(MyEnum _value_)
        {
            return _value_;
        }

        public SimpleStruct echo(SimpleStruct _value_)
        {
            return _value_;
        }

        public ListStruct echo(ListStruct _value_)
        {
            return _value_;
        }

        public List<bool> echo1(List<bool> _value_)
        {
            return _value_;
        }

        public List<SimpleStruct> echo10(List<SimpleStruct> _value_)
        {
            return _value_;
        }

        public List<ListStruct> echo11(List<ListStruct> _value_)
        {
            return _value_;
        }

        public List<byte> echo2(List<byte> _value_)
        {
            return _value_;
        }

        public List<short> echo3(List<short> _value_)
        {
            return _value_;
        }

        public List<int> echo4(List<int> _value_)
        {
            return _value_;
        }

        public List<long> echo5(List<long> _value_)
        {
            return _value_;
        }

        public List<float> echo6(List<float> _value_)
        {
            return _value_;
        }

        public List<double> echo7(List<double> _value_)
        {
            return _value_;
        }

        public List<string> echo8(List<string> _value_)
        {
            return _value_;
        }

        public List<MyEnum> echo9(List<MyEnum> _value_)
        {
            return _value_;
        }

        public CallbackRPC.IFace getCallback()
        {
            throw new NotImplementedException();
        }

        public void setCallback(global::De.Sos.MORA.Examples.Serialize.CallbackRPC.IFace _cb_, float _firstValue_)
        {
            _cb_.onEcho(_firstValue_);
        }

        public void throwUnknownException()
        {
            throw new Exception("Some Exception from c#");
        }
    }
    class ServerMain
    {
        public static void Main(string[] args)
        {

            int iv = (short.MaxValue - 15) * 2;
            ushort usv = (ushort)iv;
            short sv = unchecked((short)iv);
            Console.WriteLine("Int: " + iv + " --- Short: " + sv);
            Options opt = new Options();
            opt.Port = 9242;
            opt.Adr = IPAddress.Parse("127.0.0.1");
            opt.Protocol = Mora.PROTOCOL.TCP;
            Communicator com = new Communicator(opt);

            EchoManagerRPC.publishAdapter("myEcho", new MyManager(), com);            


            com.Start();
            




            //var s = new SerialisationServer();
            //s.Run();

            Thread.Sleep(3000);

            //s.Stop();
        }
    }
}
