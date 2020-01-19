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

    class ClientMain
    {

        class MyCallback : CallbackRPC.IFace
        {
            public void onEcho(float _value_)
            {
                Console.WriteLine("My Callback on C#: " + _value_);
            }
        }

        public static void Main1(string[] args)
        {
            for (int i = 0; i < 1000; i++)
            {

                Console.WriteLine("------------------" + i + "-----------------------");
                Options opt = new Options();
                opt.Adr = IPAddress.Parse("127.0.0.1");
                opt.Port = 0;

                Communicator com = new Communicator(opt);
                com.Start();
                var proxy = EchoManagerRPC.createProxy(com, "tcp://127.0.0.1:9242/myEcho");

                proxy.echo(true);
                Console.WriteLine("Echo_Bool");
                proxy.echo(42.89);
                Console.WriteLine("Echo_Double");

                proxy.setCallback(new MyCallback(), 992.3f);
                com.Stop();
                Thread.Sleep(1000);

            }
            //var s = new SerialisationServer();
            //s.Run();

            //Thread.Sleep(3000);

            //s.Stop();
        }
    }
}
