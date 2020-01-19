using Mora.Com;
using Mora.Stream;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora
{

    public interface IMethodInvokation<D>
    {
        void Invoke(D deleg, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator);
    }

    public interface IMoraAdapter
    {
        void Invoke(string signature, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator);
        bool represents(object iface);
        string getIdentifier();
        string getQualifiedIdentifier();
    }

    public abstract class AbstractAdapter<T> : IMoraAdapter
    {
        private string mQualifiedIdentifier;

        protected Communicator Communicator { get; }
        protected string Identifier { get; }

        protected T Delegate { get; }


        protected AbstractAdapter(T deleg, string id, Communicator com)
        {
            Communicator = com;
            Identifier = id;
            Delegate = deleg;
        }
        public string getIdentifier()
        {
            return Identifier;
        }
        public bool represents(object iface)
        {
            return Delegate.Equals(iface);
        }

        public void Invoke(string signature, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator)
        {
            IMethodInvokation<T> method = getInvoker(signature);
            if (method == null)
                throw new NullReferenceException("Missing method with signature: " + signature + " on Object: " + getIdentifier());
            method.Invoke(Delegate, iStream, oStream, communicator);
        }

        protected abstract IMethodInvokation<T> getInvoker(string methodName);

        public string getQualifiedIdentifier()
        {
            if (mQualifiedIdentifier == null)
            {
                mQualifiedIdentifier = Communicator.ResponseCommunicator.ToString() + "/" + getIdentifier();
            }
            return mQualifiedIdentifier;
        }
    }


}
