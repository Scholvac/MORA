using Mora.Com;
using Mora.Stream;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mora
{

    public abstract class IRemoteMethodCall
    {
        public short Magic { get; protected set; }

        protected Communicator mCommunicator;
        internal RemoteMethod RemoteMethod { get; }
        protected OutMethodCall mMessage;

        protected IRemoteMethodCall(Communicator com, RemoteMethod rm)
        {
            mCommunicator = com;
            RemoteMethod = rm;
            Magic = Common.generateShortMagic();
        }

        public OutMethodCall getMessage()
        {
            return mMessage;
        }
        protected bool executeCall()
        {
            try
            {
                mMessage = mCommunicator.createCall(RemoteMethod, Magic);

                WriteParameters(mMessage.Stream, mCommunicator);

                if (!mCommunicator.call(this))
                    return false;
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                return false;
            }
        }
        public abstract void HandleResponse(InMethodRespond response, Communicator communicator);

        protected abstract void WriteParameters(IMoraOutputStream oStream, Communicator communicator);

    }


    public abstract class RemoteMethodCall<T> : IRemoteMethodCall
    {
        private TaskCompletionSource<T> mTask;


        protected RemoteMethodCall(Communicator communicator, RemoteMethod remoteMethod)
            : base(communicator, remoteMethod)
        {
        }
        public Task<T> invoke()
        {
            mTask = new TaskCompletionSource<T>();
            if (executeCall())
                return mTask.Task;
            throw new MoraException("Failed to send RemoteMessage");
        }


        public override void HandleResponse(InMethodRespond response, Communicator communicator)
        {
            T result = ReadReturnValue(response.Stream, communicator);
            mTask.SetResult(result);
        }
        protected abstract T ReadReturnValue(IMoraInputStream iStream, Communicator communicator);
    }

    public abstract class RemoteVoidMethodCall : IRemoteMethodCall
    {
        private TaskCompletionSource<int> mTask;


        protected RemoteVoidMethodCall(Communicator communicator, RemoteMethod remoteMethod)
            : base(communicator, remoteMethod)
        {
        }
        public Task invoke()
        {
            mTask = new TaskCompletionSource<int>();
            if (executeCall())
                return mTask.Task;
            throw new MoraException("Execute Remote Call failed");
        }
        public override void HandleResponse(InMethodRespond response, Communicator communicator)
        {
            mTask.SetResult(1);
        }

    }

}
