using Mora.Com;
using Mora.Stream;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mora
{
    public class IMoraProxy
    {
        public RemoteObject RemoteObject { get; protected set; }
    }

    public abstract class AbstractProxy<T> : IMoraProxy
    {
        //override RemoteObject RemoteObject { get; protected set; }
        protected Communicator Communicator { get; }

        protected AbstractProxy(RemoteObject ro, Communicator com)
        {
            RemoteObject = ro;
            Communicator = com;
        }

        protected RemoteMethod CreateRemoteMethod(string signature)
        {
            return new RemoteMethod(RemoteObject, signature);
        }


        private RemoteMethod mRemoteGetType;

        private class RemoteCall_GetType_ : RemoteMethodCall<string>
        {
            public RemoteCall_GetType_(Communicator c, RemoteMethod rm)
                : base(c, rm)
            {
            }

            protected override void WriteParameters(IMoraOutputStream oStream, Communicator communicator)
            {
            }

            protected override string ReadReturnValue(IMoraInputStream iStream, Communicator communicator)
            {
                string result;
                iStream.Read(out result);
                return result;
            }

          
        }
        public bool checkType()
        {
            try
            {
                if (mRemoteGetType == null)
                    mRemoteGetType = CreateRemoteMethod("_getType_");

                RemoteCall_GetType_ call = new RemoteCall_GetType_(Communicator, mRemoteGetType);
                Task<string> task = call.invoke();
                task.Wait();
                string type = task.Result;
                return type == GetTypeIdentifier();
            }
            catch (Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                return false;
            }
        }

        public abstract string GetTypeIdentifier();
    }
}
