using Mora.Com;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Mora
{
    internal class MethodHandler 
    {

        public static void invoke(InMethodCall call, Communicator communicator)
        {
            try
            {
                handle(call, communicator);
            }catch(Exception e)
            {
                Console.WriteLine(e.ToString());
                //we got an exception thus we will send the exception as a response back to the caller to handle the exception on his side.
                communicator.sendResponse(new OutMethodResponse(call, e));
            }
        }
        public static void handle(InMethodCall call, Communicator communicator)
        {
            IMoraAdapter adapter = communicator.getAdapter(call.ObjectIdentifier);
            if (adapter == null)
                throw new Exception("Could not find adapter with identifier: " + call.ObjectIdentifier);

            OutMethodResponse response = new OutMethodResponse(call);//create a call expecting that the call will success, if thats not the case, we will ignore this instance and create a new one
            //also there is no need to catch any exceptions as this is done in the Invoke - method
            try
            {
                adapter.Invoke(call.MethodSignature, call.Stream, response.Stream, communicator);
            }catch(Exception e)
            {
                Console.Error.WriteLine(e.ToString());
                throw e;
            }
            communicator.sendResponse(response);
        }
    }
}
