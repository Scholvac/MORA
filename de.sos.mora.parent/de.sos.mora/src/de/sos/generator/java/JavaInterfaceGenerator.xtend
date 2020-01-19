package de.sos.generator.java

import de.sos.generator.TypeUtil
import de.sos.mORA.Interface
import de.sos.mORA.TypeDecl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.naming.IQualifiedNameProvider
import de.sos.mORA.Method
import de.sos.mORA.Parameter
import de.sos.mORA.PrimTypeDecl

class JavaInterfaceGenerator extends AbstractGenerator {
	
		
	extension TypeUtil = new TypeUtil
	extension JavaTypeUtil	= new JavaTypeUtil
	 
	new(){
	
	}
	
	override doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
		for (e : resource.allContents.filter(Interface).toIterable){
			e.generate(fsa);	
		}
		
	}
		
		
		

	
	def generate(Interface iface, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("java.util.HashMap")
		rememberImports("java.util.concurrent.CompletableFuture")
		rememberImports("java.util.concurrent.ExecutionException")
		rememberImports("java.util.UUID")
		
		rememberImports("java.io.IOException")
		rememberImports("java.net.InetAddress")
		
		rememberImports("de.sos.mora.IMoraAdapter")
		rememberImports("de.sos.mora.types.IMoraTypeEncoder")
		rememberImports("de.sos.mora.stream.IMoraOutputStream")
		rememberImports("de.sos.mora.stream.IMoraInputStream")
		rememberImports("de.sos.mora.IMoraProxy")
		rememberImports("de.sos.mora.RemoteObjects.RemoteObject")
		rememberImports("de.sos.mora.exceptions.MoraException")
		rememberImports("de.sos.mora.exceptions.MoraProxyException")
		rememberImports("de.sos.mora.exceptions.MoraAdapterException")
		rememberImports("de.sos.mora.exceptions.MoraInvalidAddressException")
		rememberImports("de.sos.mora.com.Communicator")
		rememberImports("de.sos.mora.com.Communicator.PROTOCOL")
		rememberImports("de.sos.mora.com.MoraMessages.OutMethodCall");
		rememberImports("de.sos.mora.com.MoraMessages.InMethodRespond")
		rememberImports("de.sos.mora.RemoteObjects.RemoteMethod")
		rememberImports("de.sos.mora.RemoteMethodCall")
		rememberImports("de.sos.mora.Common")
		
		
		val content = iface.generate
		val qn = iface.fullyQualifiedName;
		val imports = getImportBlock(qn)
		val header = "package " + qn.skipLast(1) + ";\n"
		
		fsa.generateFile(qn.skipLast(1).toString("/")+"/"+iface.containerClassName + ".java", header + "\n" + imports +"\n"+ content);
	}
	
	def CharSequence generate(Interface iface)
	'''
		public class «iface.containerClassName» {
			
			//////////////////////////	IFace	////////////////////////////
			«iface.generateIFace»
			
			////////////////////////// User Interaction ////////////////////////////
			«iface.createAdapter»
			«iface.createProxy»
			
			
			
			////////////////////////// Internal Stuff ////////////////////////////
			public static final String			TYPE_IDENTIFIER = "«iface.name.toUpperCase»";
			
			«iface.generateEncoder»
			«iface.generateProxy»
			«iface.generateAdapter»
		}
	'''
	
	def createProxy(Interface iface)
	'''
		public static IFace createProxy(Communicator communicator, final String remoteObjectAddress) throws MoraInvalidAddressException {
			return createProxy(communicator, RemoteObject.createRemoteObject(remoteObjectAddress));
		}
		public static IFace createProxy(Communicator communicator, final RemoteObject remoteObject) throws MoraInvalidAddressException {
			Proxy proxy = new Proxy(communicator, remoteObject);
			if (proxy.checkType())
				return proxy;
			return null;
		}
	'''
	
	def createAdapter(Interface iface)
	'''
		public static Adapter publishAdapter(String identifier, IFace impl, Communicator communicator) throws MoraAdapterException {
			if (communicator.hasAdapter(identifier)) {
				if (communicator.getAdapter(identifier).getDelegate() == impl) {
					return communicator.getAdapter(identifier);
				}
				else
					throw new MoraAdapterException("An Adapter with identifier: " + identifier + " already exists");
			}
			Adapter adapter = new Adapter(communicator, impl, identifier);
			return communicator.registerAdapter(adapter);
		}
	'''
	
	def generateEncoder(Interface iface)
	'''
		public static void write(final IFace in, IMoraOutputStream stream, Communicator communicator) throws IOException {
			if (in == null)
				stream.writeByte(Common.STRUCT_NULL);
			else
				stream.writeByte(Common.STRUCT_START);
			Adapter adapter = null;
			if (in instanceof Adapter) {
				adapter = (Adapter)in;
			}else {
				//create an adapter with random id
				try {
					adapter = publishAdapter(UUID.randomUUID().toString(), in, communicator);
				} catch (MoraAdapterException e) {
					throw new IOException("Failed to publish adapter");
				}
			}
			assert(adapter != null);
			final String qid = adapter.getQualifiedIdentifier();
			stream.writeString(qid);
		}
		
		public static Proxy read(IMoraInputStream stream, Communicator communicator) throws IOException {
			byte flag = stream.readByte();
			if (flag == Common.STRUCT_NULL)
				return null;
			assert(flag == Common.STRUCT_START);
			
			final String quid = stream.readString();
			Proxy proxy = communicator.getProxy(quid);
			if (proxy == null) {
				try {
					proxy = new Proxy(communicator, RemoteObject.createRemoteObject(quid));
					communicator.registerProxy(proxy);
				} catch (MoraInvalidAddressException e) {
					e.printStackTrace();
					return null;
				}				
			}
			return proxy;	
		}
	'''
	
	def generateIFace(Interface iface)
	'''
		public interface IFace {
			«FOR m : iface.methods»
				public «m.type.javaTypeName» «m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.javaTypeName» «p.parameterName»«ENDFOR»);
			«ENDFOR»
		}
	'''
	
	def generateProxy(Interface iface)
	'''
		public static class Proxy extends IMoraProxy<IFace> implements IFace {
			private RemoteMethod[] 		mRemoteMethods = new RemoteMethod[«iface.methods.size»];
			
			public Proxy(Communicator communicator, RemoteObject remoteObject) throws MoraInvalidAddressException {
				super(communicator, remoteObject);
			}
			@Override
			public String getTypeIdentifier() { return TYPE_IDENTIFIER; }
			«FOR m : iface.methods»

				private static class RemoteCall_«m.signature» extends RemoteMethodCall<«m.type.getJavaTypeName(true)»> {
					«FOR p : m.parameters»
						private final «p.type.javaTypeName» m«p.name.toFirstUpper»;
					«ENDFOR»

					public RemoteCall_«m.signature»(Communicator c, RemoteMethod rm«IF m.parameters.empty == false», «FOR p : m.parameters SEPARATOR ', '»«p.type.javaTypeName» «p.parameterName»«ENDFOR»«ENDIF») {
						super(c, rm);
						«FOR p : m.parameters»
							m«p.name.toFirstUpper» = «p.parameterName»;
						«ENDFOR»
					}
		
					@Override
					protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
						«FOR p : m.parameters»
							«IF p.type.singleType.prim»
								os.write«p.type.singleType.primType.streamPostFix»(m«p.name.toFirstUpper»);
							«ELSE»
								«IF p.type.singleType.proxy»
									«p.type.singleType.proxyType.containerClassName».write(m«p.name.toFirstUpper», os, communicator);
								«ELSE»
									«p.type.singleType.javaTypeName».write(m«p.name.toFirstUpper», os, communicator);
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					}
		
					@Override
					protected «m.type.getJavaTypeName(true)» decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
						«IF m.type.void == false»
							«IF m.type.singleType.prim»
								final «m.type.javaTypeName» result = is.read«m.type.singleType.primType.streamPostFix»«IF m.type.many»List«ENDIF»();
							«ELSE»
								«IF m.type.singleType.proxy»
									final «m.type.javaTypeName» result = «m.type.singleType.proxyType.containerClassName».read(is, communicator);
								«ELSE»
									final «m.type.javaTypeName» result = «m.type.singleType.javaTypeName».read«IF m.type.many»List«ENDIF»(is, communicator);
								«ENDIF»
							«ENDIF»
							return result;
						«ELSE»
							return null;
						«ENDIF»
					}
					
				}
						
				public CompletableFuture<«m.type.getJavaTypeName(true)»> async_«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.javaTypeName» «p.parameterName»«ENDFOR»)  throws MoraException {
					final int methodIndex = «iface.methods.indexOf(m)»;
					if (mRemoteMethods[methodIndex] == null)
						mRemoteMethods[methodIndex] = createRemoteMethod("«m.signature»");
					RemoteCall_«m.signature» call = new RemoteCall_«m.signature»(getCommunicator(), mRemoteMethods[methodIndex]«IF m.parameters.empty==false», «FOR p : m.parameters SEPARATOR ', '»«p.parameterName» «ENDFOR»«ENDIF»);
					return call.invoke();
				}

				@Override
				public «m.type.javaTypeName» «m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.javaTypeName» «p.parameterName»«ENDFOR»){
					try{
						CompletableFuture<«m.type.getJavaTypeName(true)»> future = async_«m.name»(«FOR p : m.parameters SEPARATOR ','»«p.parameterName»«ENDFOR»);
						future.exceptionally(f -> {
							throw new RuntimeException(f);
						});
						«IF m.type.void == false»
							final «m.type.javaTypeName» res = future.get();
							return res;
						«ELSE»
							future.get();
						«ENDIF» 
					} catch (InterruptedException | ExecutionException | MoraException e) {
						throw new RuntimeException(e);
					}
				}
			«ENDFOR»
		}
	'''
	
	
	
	
	def generateAdapter(Interface iface)
	'''
		public static class Adapter extends IMoraAdapter<IFace> implements IFace {
			private static HashMap<String, IMethodInvokation<IFace>>	sMethods = new HashMap<>();
			static {
				sMethods.put("_getType_", new _getType_Invoker());
				
				«FOR m : iface.methods»
					sMethods.put("«m.signature»", new «m.signature»Invoker());
				«ENDFOR»
			}
			protected Adapter(Communicator communicator, IFace delegate, final String identifier) {
				super(communicator, delegate, identifier);
			}
			@Override
			protected IMethodInvokation<IFace> getInvoker(String methodName) {
				return sMethods.get(methodName);
			}
			«FOR m : iface.methods»
				public «m.type.javaTypeName» «m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.javaTypeName» «p.parameterName»«ENDFOR»){
					«IF m.type.void»
						mDelegate.«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
					«ELSE»
						return mDelegate.«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
					«ENDIF»
				}
			«ENDFOR»
					
			private static class _getType_Invoker implements IMethodInvokation<IFace> {
				@Override
				public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeString(TYPE_IDENTIFIER);
				}
			}
			«FOR m : iface.methods»
				«m.generateInvoker(iface)»
			«ENDFOR»
		}
	'''
	
	def generateInvoker(Method m, Interface iface)
	'''
		private static class «m.signature»Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				«FOR p : m.parameters»
					«IF p.type.singleType.prim»
						final «p.type.javaTypeName» «p.parameterName» = is.read«p.type.singleType.primType.streamPostFix»«IF p.type.many»List«ENDIF»();
					«ELSE»
						«IF p.type.singleType.proxy»
							final «p.type.javaTypeName» «p.parameterName» = «p.type.singleType.proxyType.containerClassName».read«IF p.type.many»List«ENDIF»(is, communicator);
						«ELSE»	
							final «p.type.javaTypeName» «p.parameterName» = «p.type.singleType.javaTypeName».read«IF p.type.many»List«ENDIF»(is, communicator);
						«ENDIF»
					«ENDIF»
				«ENDFOR»
				«IF m.type.void»
					delegate.«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
				«ELSE»
					final «m.type.javaTypeName» _result_ = delegate.«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
					«IF m.type.singleType.prim»
						os.write«m.type.singleType.primType.streamPostFix»(_result_);
					«ELSE»
						«IF m.type.singleType.proxy»
							«m.type.singleType.proxyType.containerClassName».write(_result_, os, communicator);
						«ELSE»
							«m.type.singleType.javaTypeName».write(_result_, os, communicator);
						«ENDIF»
					«ENDIF»
				«ENDIF»
			}
		}
	'''
		
	
	def String getParameterName(Parameter parameter){
		return parameter.name ;
	}
	
	
	
	
}
