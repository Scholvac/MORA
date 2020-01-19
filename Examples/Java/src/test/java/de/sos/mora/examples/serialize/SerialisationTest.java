package de.sos.mora.examples.serialize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.sos.mora.com.Communicator;
import de.sos.mora.com.NetMessagees;
import de.sos.mora.com.Communicator.Options;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.examples.common.EchoManager;
import de.sos.mora.examples.serialize.EchoManagerRPC.IFace;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.MoraInputStream;
import de.sos.mora.stream.MoraOutputStream;

public class SerialisationTest {

	public static class SerialisationServer {
		
		public void run(int port) throws UnknownHostException, MoraException {
			Communicator.Options opt = new Options();
			opt.name = "Server";
//			opt.maxConnectionsPerClient = 1;
//			opt.workerThreads = 1;
			
			opt.protocol = PROTOCOL.TCP;
			opt.port = port;
			opt.adr = InetAddress.getByName("localhost");		
			Communicator com = new Communicator(opt);
			EchoManagerRPC.publishAdapter("myEcho", new EchoManager(), com);
			
			com.start();
		}
	}
	
	
	
	public static class SerialisationClient {				
		private Communicator com;
		
		public SerialisationClient() throws UnknownHostException, MoraException {
			Communicator.Options opt = new Options();
			opt.name = "Client";
//			opt.maxConnectionsPerClient = 1;
//			opt.workerThreads = 1;
			
			opt.timeout = 100;
			opt.timeoutUnit = TimeUnit.SECONDS;
			opt.protocol = PROTOCOL.TCP;
			opt.port = -1;
			
			com = new Communicator(opt); //automatically choose host and port
			com.start();
		}
		public void runSerialisation() throws UnknownHostException, MoraException {
			EchoManagerRPC.IFace echo = EchoManagerRPC.createProxy(com, "tcp://127.0.0.1:9242/myEcho");
			
			double sum = 0;
			int count = 10;
			for (int i = 0; i < count; i++) {
				long s = System.currentTimeMillis();
				System.out.println("-------------" + i + "-----------------");
				assertEquals(true, echo.echo(true));
				System.out.println("Echo - bool");
				assertEquals((byte)42, echo.echo((byte)42));
				System.out.println("Echo - byte");
				short v = -37;//Short.MAX_VALUE -15;
				assertEquals(v, echo.echo(v));
				System.out.println("Echo - short");
				assertEquals(42, echo.echo(42));
				System.out.println("Echo - int");
				assertNotEquals(88, echo.echo(39));
				assertEquals((long)98, echo.echo((long)98));
				System.out.println("Echo - long");
				assertEquals(13.42f, echo.echo(13.42f));
				System.out.println("Echo - float");
				assertEquals(42.42, echo.echo(42.42));
				System.out.println("Echo - double");
				assertEquals("Hello Echo-Server", echo.echo("Hello Echo-Server"));
				System.out.println("Echo - String");
				assertEquals(MyEnum.VALUE, MyEnum.VALUE);
				System.out.println("Echo - enum");
				SimpleStruct ss = EchoManager.createRandomSimpleStruct();
				System.out.println("struct = " + ss);
				assertEquals(ss, echo.echo(ss));
				System.out.println("Echo - Struct");
				ListStruct ls = EchoManager.createRandomListStruct();
				assertEquals(ls, echo.echo(ls));
				System.out.println("Echo - ListStruct");
								
				
				CallbackRPC.IFace cb = new CallbackRPC.IFace() {
					
					@Override
					public void onEcho(float _value_) {
						System.out.println("Callback: " + _value_);
					}
				};
				echo.setCallback(cb, 42.42f);
				long e = System.currentTimeMillis();
				sum += (e-s);
				System.out.println("-------------" + i + "=" + (e-s)+"[ms] -----------------");
				
			}
			System.out.println("Need: " + (sum / count) + "[ms] in average");
		}
		
		public void runException() throws UnknownHostException, MoraException {
			EchoManagerRPC.IFace echo = EchoManagerRPC.createProxy(com, "tcp://127.0.0.1:9242/myEcho");
			
			assertEquals(true, echo.echo(true));
			assertThrows(RuntimeException.class, () -> echo.throwUnknownException());
			
		}
	}
	
	
		
	public static void main(String[] args) throws UnknownHostException, MoraException, InterruptedException {
//		new SerialisationServer().run(9242);
		SerialisationClient c = new SerialisationClient();
		c.runSerialisation();
		c.runException();
		System.exit(0);
	}
}
