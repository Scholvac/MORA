package de.sos.mora.examples.lost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.net.UnknownHostException;

import de.sos.mora.com.Communicator;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.examples.common.EchoManager;
import de.sos.mora.examples.serialize.EchoManagerRPC;
import de.sos.mora.examples.serialize.ListStruct;
import de.sos.mora.examples.serialize.MyEnum;
import de.sos.mora.examples.serialize.SimpleStruct;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;

public class ConnectionLostExample {

	public static boolean startServer = false;
	public static int serverPort = 9242;
	
	
	public static class Server {
		Communicator com ;
		public void run(int port) throws UnknownHostException, MoraException {
			com = new Communicator(PROTOCOL.TCP, "localhost", port);
			EchoManagerRPC.publishAdapter("myEcho", new EchoManager(), com);

			com.start();
		}

		public void stop() {
			com.stop();
		}
	}

	public static class Client {
		EchoManagerRPC.IFace  echo;
		public Client() throws MoraException, UnknownHostException {
			Communicator com = new Communicator(PROTOCOL.TCP); // automatically choose host and port
			com.start();
			echo = EchoManagerRPC.createProxy(com, "tcp://localhost:9242/myEcho");
		}
		public void run() throws MoraInvalidAddressException {
			
			try {
				assertEquals(true, echo.echo(true));
			}catch(Exception me) {
				System.out.println("Exit ");
				me.printStackTrace();
				System.exit(0);
			}
			assertEquals((byte) 42, echo.echo((byte) 42));
			assertEquals((short) 24, echo.echo((short) 24));
			assertEquals(42, echo.echo(42));
			assertNotEquals(88, echo.echo(39));
			assertEquals((long) 98, echo.echo((long) 98));
			assertEquals(13.42f, echo.echo(13.42f));
			assertEquals(42.42, echo.echo(42.42));
			assertEquals("Hello Echo-Server", echo.echo("Hello Echo-Server"));
			assertEquals(MyEnum.VALUE, MyEnum.VALUE);
			SimpleStruct ss = EchoManager.createRandomSimpleStruct();
			assertEquals(ss, echo.echo(ss));

			ListStruct ls = EchoManager.createRandomListStruct();
			System.out.println(ls.getStringListValue().size());
			System.out.println(ls.getStructListValue().size());
			assertEquals(ls, echo.echo(ls));

		}
	}

	public static void main(String[] args) throws UnknownHostException, MoraException {
//		Server s = new Server();
//		s.run(serverPort);
//		s.stop();
//		System.out.println("Server stopped");
//		s = new Server();
//		s.run(serverPort);
		
		Client c = new Client();
		
		c.run();
		c.run();
		
//		s.stop();
		c.run();
		

		System.exit(00);
	}
}
