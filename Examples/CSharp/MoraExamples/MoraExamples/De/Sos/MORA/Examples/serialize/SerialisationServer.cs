using De.Sos.MORA.Examples.Serialize;
using Mora.Com;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace MoraExamples.De.Sos.MORA.Examples.serialize
{
    class SerialisationServer : EchoManagerRPC.IFace
    {
        private Communicator mCommunicator;

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

        public void Run()
        {
            Options opt = new Options();
            opt.Port = 9242;
            mCommunicator = new Communicator(opt);
            EchoManagerRPC.publishAdapter("myEcho", this, mCommunicator);
            mCommunicator.Start();
        }

        public void setCallback(global::De.Sos.MORA.Examples.Serialize.CallbackRPC.IFace _cb_, float _firstValue_)
        {
            throw new NotImplementedException();
        }

        public void Stop()
        {
            mCommunicator.Stop();
        }

        public void throwUnknownException()
        {
            throw new Exception("Some Exception from C#");
        }
    }
}
