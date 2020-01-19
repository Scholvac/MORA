package de.sos.generator.csharp

import de.sos.generator.TypeUtil
import de.sos.mORA.Interface
import de.sos.mORA.TypeDecl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.naming.IQualifiedNameProvider
import de.sos.mORA.Parameter
import de.sos.generator.csharp.CSharpGenerator.CSharpOptions

class CSharpInterfaceGenerator extends AbstractGenerator {

	
	val CSharpOptions opt;
	
	extension TypeUtil = new TypeUtil();
	extension CSharpTypeUtil = new CSharpTypeUtil();
	extension IQualifiedNameProvider = new QualifiedNameProvider()
	
	new(CSharpOptions options) {
		opt = options;
	}
	
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		for (e : resource.allContents.filter(Interface).toIterable){
			e.generate(fsa);	
		}
	}
	
	def void generate(Interface i, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("System.NullReferenceException")
		rememberImports("System.Threading.Tasks.Task")
		rememberImports("System.IO.StreamWriter")
		rememberImports("System.Collections.Generic.List")
		rememberImports("System.Collections.IList")
		rememberImports("Mora.AbstractAdapter")
		rememberImports("Mora.IMethodInvokation")
		rememberImports("Mora.Stream.IMoraOutputStream")
		rememberImports("Mora.Com.Communicator")
		val content = i.generate
		
		val qn = i.fullyQualifiedName;
		val imports = getImportBlock(qn)
		
		val allContent = "\n" + imports +"\n"+ content;
		val fileName = opt.namespace.replace('.', '/') + "/" + i.fullyQualifiedName.toString("/")+".cs";
		fsa.generateFile(fileName, allContent);
	}
	
	def generate(Interface iface)
	'''
		namespace «IF opt.namespace.empty==false»«opt.namespace».«ENDIF»«iface.fullyQualifiedName.skipLast(1).toString(".")» {
			
			public class «iface.containerClassName» {
				
				public static readonly string TYPE_IDENTIFIER = "«iface.name.toUpperCase»";
				
				#region IFace
				«iface.generateIFace»
				#endregion
				
				#region Creation
				«iface.creation»
				#endregion
				
				#region Serialisation
				«iface.generateEncoder»
				#endregion
				
				#region Proxy
				«iface.generateProxy»
				#endregion
				
				#region Adapter
				«iface.generateAdapter»
				#endregion
			}
		}
	'''
	
	def creation(Interface iface)
	'''
		public static Adapter publishAdapter(string identifier, IFace impl, Communicator communicator)
		{
			if (communicator.hasAdapter(identifier))
			{
				if (communicator.getAdapter(identifier).represents(impl))
					return (Adapter)communicator.getAdapter(identifier);
				throw new MoraAdapterException("An Adapter with identifier: " + identifier + " already exists");
			}
			Adapter result = new Adapter(impl, identifier, communicator);
			return communicator.registerAdapter(result);
		}
		
		public static Proxy createProxy(Communicator communicator, String remoteObjectAddress) 
		{
			return createProxy(communicator, RemoteObject.create(remoteObjectAddress));
		}
		
		public static Proxy createProxy(Communicator communicator, RemoteObject remoteObject)
		{
			Proxy proxy = new Proxy(remoteObject, communicator);
			if (proxy.checkType())
				return proxy;
			return null;
		}
	'''

	def generateEncoder(Interface iface)
	'''
		public static void Write(List<IFace> adapter, IMoraOutputStream stream, Communicator communicator)
		{
			stream.Write(adapter.Count);
			for (int i = 0; i < adapter.Count; i++)
				Write(adapter[i], stream, communicator);
		}
		public static void Write(IFace iface, IMoraOutputStream stream, Communicator communicator)
		{
			if (iface == null)
				stream.Write(Common.STRUCT_NULL);
			else
				stream.Write(Common.STRUCT_START);
				
			Adapter adapter = null;
			if (iface is Adapter) {
				adapter = (Adapter)iface;
			}else {
				//create an adapter with random id
				adapter = publishAdapter(System.Guid.NewGuid().ToString(), iface, communicator);
			}
			string qid = adapter.getQualifiedIdentifier();
			stream.Write(qid);
		}
		
		public static void Read(IMoraInputStream stream, Communicator communicator, out List<IFace> proxies)
		{
			int count;
			stream.Read(out count);
			proxies = new List<IFace>(count);
			for (int i = 0; i < count; i++){
				IFace proxy;
				Read(stream, communicator, out proxy);
				proxies.Add(proxy);	
			}
		}
		public static void Read(IMoraInputStream stream, Communicator communicator, out IFace result)
		{
			byte flag;
			stream.Read(out flag);
			if (flag == Common.STRUCT_NULL){
				result = null;
				return ;
			}
			string quid;
			stream.Read(out quid);
			IMoraProxy proxy = communicator.getProxy(quid);
			if (proxy == null){
				proxy = new Proxy(RemoteObject.create(quid), communicator);
				communicator.registerProxy(proxy);
			}
			result = proxy as Proxy;
		}
		
	'''

	def generateIFace(Interface iface)
	'''
		public interface IFace {
			«FOR m : iface.methods»
				«m.type.csTypeName» «m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.csTypeName» «p.parameterName»«ENDFOR»);
			«ENDFOR»
		}
	'''
	
	def generateProxy(Interface iface)
	'''
		public class Proxy : AbstractProxy<IFace>, IFace {
			
			private RemoteMethod[] mMethods = new RemoteMethod[«iface.methods.size»];
			
			public Proxy(RemoteObject remoteObject, Communicator communicator) 
				:	base(remoteObject, communicator)
			{
			}
			
			public override string GetTypeIdentifier()
			{
				return «iface.containerClassName».TYPE_IDENTIFIER;
			}
						
			«FOR m : iface.methods»
				
				private class RemoteCall_«m.signature» : Remote«IF m.type.singleType.void»Void«ENDIF»MethodCall«IF m.type.void==false»<«m.type.csTypeName»>«ENDIF» 
				{
					
					«FOR p : m.parameters»
						private «p.type.csTypeName» m«p.name.toFirstUpper»;
					«ENDFOR»
					
					public RemoteCall_«m.signature»(Communicator c, RemoteMethod rm«IF m.parameters.empty == false», «FOR p : m.parameters SEPARATOR ', '»«p.type.csTypeName» «p.parameterName»«ENDFOR»«ENDIF»)
						:	base(c, rm) 
					{
						«FOR p : m.parameters»
							m«p.name.toFirstUpper» = «p.parameterName»;
						«ENDFOR»
					}
					
					protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
					{
						«FOR p : m.parameters»
							«IF p.type.singleType.prim»
								_oStream.Write(m«p.name.toFirstUpper»);
							«ELSE»
								«IF p.type.singleType.proxy»
									«p.type.singleType.proxyType.containerClassName».Write(m«p.name.toFirstUpper», _oStream, _communicator);
								«ELSE»
									«p.type.singleCsTypeName»«IF p.type.singleType.enum»Util«ENDIF».Write(m«p.name.toFirstUpper», _oStream, _communicator);
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					}
					
					«IF m.type.void == false»
						protected override «m.type.csTypeName» ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
						{
							«IF m.type.void»
							«ELSE»
								«m.type.csTypeName» _result;
								«IF m.type.singleType.prim»
									_iStream.Read(out _result);
								«ELSE»
									«IF m.type.singleType.proxy»
										«m.type.singleType.proxyType.containerClassName».Read(_iStream, _communicator, out _result);
									«ELSE»
										«m.type.singleCsTypeName»«IF m.type.singleType.enum»Util«ENDIF».Read(_iStream, _communicator, out _result);
									«ENDIF»
								«ENDIF»
								return _result;
							«ENDIF»
						}
					«ENDIF»
				}
				
				public Task«IF m.type.void==false»<«m.type.csTypeName»>«ENDIF» Async_«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.csTypeName» «p.parameterName»«ENDFOR»)
				{
					int methodIndex = «iface.methods.indexOf(m)»;
					RemoteMethod method = mMethods[methodIndex];
					if (method == null)
					{
						mMethods[methodIndex] = method = CreateRemoteMethod("«m.signature»");
					}
					RemoteCall_«m.signature» call = new RemoteCall_«m.signature»(Communicator, method«IF m.parameters.empty==false», «FOR p : m.parameters SEPARATOR ', '»«p.parameterName» «ENDFOR»«ENDIF»);
					return call.invoke();
				}
				
				public «m.type.csTypeName» «m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.type.csTypeName» «p.parameterName»«ENDFOR»){
					Task«IF m.type.void==false»<«m.type.csTypeName»>«ENDIF» _task = Async_«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
					_task.Wait();
					«IF m.type.void == false»
						return _task.Result;
					«ENDIF»
				}
			«ENDFOR»
		}
	'''
	
	def generateAdapter(Interface iface)
	'''
		public class Adapter : AbstractAdapter<IFace> {
			private static Dictionary<string, IMethodInvokation<IFace>> sMethods = new Dictionary<string, IMethodInvokation<IFace>>();
			static Adapter() {
				sMethods.Add("_getType_", new _getType_Invoker());
				«FOR m : iface.methods»
					sMethods.Add("«m.signature»", new «m.signature»Invoker());
				«ENDFOR»				
			}
			            		
			public Adapter(IFace deleg, string identifier, Communicator communicator)
				: base(deleg, identifier, communicator)
			{
			}
		
			protected override IMethodInvokation<IFace> getInvoker(string methodName)
			{
				return sMethods[methodName];
			}
		
			#region invoker
			
			private class _getType_Invoker : IMethodInvokation<IFace>
			{
				public void Invoke(IFace deleg, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator)
				{
					oStream.Write(«iface.containerClassName».TYPE_IDENTIFIER);
				}
			}
			
			«FOR m : iface.methods»
				private class «m.signature»Invoker : IMethodInvokation<IFace> {
					public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
					{
						«FOR p : m.parameters»
							«p.type.csTypeName» «p.parameterName»;
							«IF p.type.singleType.prim»
								_iStream.Read(out «p.parameterName»);
							«ELSE»
								«IF p.type.singleType.proxy»
									«p.type.singleType.proxyType.containerClassName».Read(_iStream, _communicator, out «p.parameterName»);
								«ELSE»
									«p.type.singleCsTypeName»«IF p.type.singleType.enum»Util«ENDIF».Read(_iStream, _communicator, out «p.parameterName»);
								«ENDIF»
							«ENDIF»
						«ENDFOR»
						«IF m.type.void»
							_deleg.«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
						«ELSE»
							«m.type.csTypeName» _result_ = _deleg.«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.parameterName»«ENDFOR»);
							«IF m.type.singleType.prim»
								_oStream.Write(_result_);
							«ELSE»
								«IF m.type.singleType.proxy»
									«m.type.singleType.proxyType.containerClassName».Write(_result_, _oStream, _communicator);
								«ELSE»
									«m.type.singleCsTypeName»«IF m.type.singleType.enum»Util«ENDIF».Write(_result_, _oStream, _communicator);
								«ENDIF»
							«ENDIF»
						«ENDIF»
					}
				}
			«ENDFOR»
			#endregion
		}
	'''
	
	def String csTypeNameOrVoid(TypeDecl decl){
		if (decl.csTypeName.equals("void"))
			return "Object";
		return decl.csTypeName; 
	}
	
	
	def String getParameterName(Parameter parameter){
		return parameter.name;
	}
	def String getContainerClassName(Interface iface){
		return iface.name.toFirstUpper + "RPC"
	}
	
}
