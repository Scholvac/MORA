package de.sos.generator.cpp

import de.sos.generator.TypeUtil
import de.sos.mORA.Member
import de.sos.mORA.Model
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import de.sos.mORA.EnumDecl
import de.sos.mORA.StructDecl
import de.sos.mORA.CppOptions
import org.eclipse.xtext.naming.QualifiedName
import de.sos.mORA.TypeDecl
import de.sos.mORA.Interface
import de.sos.mORA.Method
import com.google.common.base.Strings

class CppPlainGenerator extends AbstractGenerator {
	

	extension TypeUtil = new TypeUtil
	extension CppTypeUtil	= new CppTypeUtil

	
	
	override doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
		
		val m = resource.getModel()
		m.generateHeader(fsa)
//		m.generateSource(fsa);
		
	}
	
	
	
	def Model getModel(Resource resource){
		for (m : resource.allContents.filter(typeof(Model)).toIterable)
			return m;
		return null;
	}
	
	def void generateHeader(Model m, IFileSystemAccess2 fsa){
		for (i : m.eAllContents.filter(Interface).toIterable){
			clearImports
			val content = i.generateInterfaceHeader
			val headerFileName = "include/"+i.PlainName + ".h"
			
			var allContent = "#ifndef " + i.PlainName.toUpperCase + "_H_\n#define " + i.PlainName.toUpperCase+"_H_\n\n\n";
			allContent = allContent + getImportBlock(QualifiedName::create(m.name.toFirstUpper+"Types.h")) + "\n";
			allContent = allContent + content;
			allContent = allContent + "#endif //"m.name.toUpperCase + "_TYPES_H_"
			fsa.generateFile(headerFileName, allContent);	
		}
		
		for (s : m.eAllContents.filter(StructDecl).toIterable){
			clearImports
			val content = s.generateStructHeader
			val headerFileName = "include/"+s.PlainName + ".h"
			
			var allContent = "#ifndef " + s.PlainName.toUpperCase + "_H_\n#define " + s.PlainName.toUpperCase+"_H_\n\n\n";
			allContent = allContent + getImportBlock(QualifiedName::create(m.name.toFirstUpper+"Types.h")) + "\n";
			allContent = allContent + content;
			allContent = allContent + "#endif //"m.name.toUpperCase + "_TYPES_H_"
			fsa.generateFile(headerFileName, allContent);	
		}
	}
	
	def generateInterfaceHeader(Interface iface)
	'''
		«IF iface.hasDoc»«iface.printDoc»«ENDIF»
		class «iface.PlainName» {
		«IF iface.hasAnnotation("Data")»
			private:
				class «iface.PlainName»Data;
				«iface.PlainName»Data* data;
		«ENDIF»
		public:
			GENERATED_LINE «iface.PlainName»();
			GENERATED_LINE virtual ~«iface.PlainName»();
			
			«FOR m : iface.methods»
				«IF m.hasDoc»«m.printDoc»«ENDIF»
				GENERATED_LINE «m.type.cppTypeName» «m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»);
			«ENDFOR»
		};
	'''
	
	def generateStructHeader(StructDecl st)
	'''
		«IF st.hasDoc»«st.printDoc»«ENDIF»
		class «st.PlainName» {
			GENERATED_LINE «st.PlainName»();
			GENERATED_LINE ~«st.PlainName»();
			
			«FOR m : st.member»
				«IF m.hasDoc»«m.printDoc»«ENDIF»
				GENERATED_LINE «m.type.cppTypeName» «m.memberName»
			«ENDFOR»
		};
	'''
	
	def boolean hasAnnotation(Interface iface, String name){
		for (a : iface.anno)
			if (a.name == name)
				return true;
		return false;
	}
	
	def String printDoc(Member s){ return s.doc.formatDoc} //TODO: do some format magic
	def boolean hasDoc(Member s) { return !Strings.isNullOrEmpty(s.doc) }
	
	def String printDoc(StructDecl s){ return s.doc.formatDoc} //TODO: do some format magic
	def boolean hasDoc(StructDecl s) { return !Strings.isNullOrEmpty(s.doc) }
	
	def String printDoc(Method method){ return method.doc.formatDoc} //TODO: do some format magic
	def boolean hasDoc(Method method) { return !Strings.isNullOrEmpty(method.doc) }
	
	def String printDoc(Interface iface){ return iface.doc.formatDoc} //TODO: do some format magic
	def boolean hasDoc(Interface iface) { return !Strings.isNullOrEmpty(iface.doc) }
	
	def String formatDoc(String doc){ return doc; }
	
	
	
	def String PlainName(Interface i){ return i.name.toFirstUpper }
	def String PlainName(StructDecl i){ return i.name.toFirstUpper }
	
	def void generateSource(Model m, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("Streams.h")
		rememberImports("loguru.hpp")
		rememberImports("Communicator.h")
		rememberImports("RemoteMethodCall.h")
		rememberImports("future")
		rememberImports(m.getTypesHeaderFile())
		
		val content = m.generateSourceContent
		val sourceFileName = "src/"+m.name.toFirstUpper+"Types.cpp";
		
		var allContent = getImportBlock(QualifiedName::create(m.name.toFirstUpper+"Types.h")) + "\n";
		allContent = allContent + content;
		
		fsa.generateFile(sourceFileName, allContent);
	}
	def generateSourceContent(Model m)
	'''
		«IF m.options.cppOptions.hasNamesapce»
			«m.options.cppOptions.beginNamespace»
		«ENDIF»
		
		«IF m.eAllContents.filter(typeof(EnumDecl)).empty == false»
			////////////////////////////////////////////////////////////////////////////////
			//							Enumerations									  //
			////////////////////////////////////////////////////////////////////////////////
			«FOR en : m.eAllContents.filter(typeof(EnumDecl)).toIterable»
				/*******************	Enumeration: «en.name»    ********************/
				«en.generateEnumSourceContent»
			«ENDFOR»
		«ENDIF»
		
		«IF m.eAllContents.filter(typeof(EnumDecl)).empty == false»
			////////////////////////////////////////////////////////////////////////////////
			//								Structs										  //
			////////////////////////////////////////////////////////////////////////////////
						
			«FOR s : m.eAllContents.filter(typeof(StructDecl)).toIterable»
				/********************* struct «s.name» *********************************/
				«s.generateStructSourceContent»
			«ENDFOR»
		«ENDIF»
		
		
		
		«IF m.eAllContents.filter(typeof(Interface)).empty == false»
			////////////////////////////////////////////////////////////////////////////////
			//							Interfaces										  //
			////////////////////////////////////////////////////////////////////////////////
						
			«FOR s : m.eAllContents.filter(typeof(Interface)).toIterable»
				/********************* interface «s.name» *********************************/
				«s.generateInterfaceSourceContent»
			«ENDFOR»
		«ENDIF»
		
		
		«IF m.options.cppOptions.hasNamesapce»
			«m.options.cppOptions.endNamespace»
		«ENDIF»
	'''
	

	
	def generateEnumSourceContent(EnumDecl en)
	'''
		::mora::int32 «en.cppTypeName»Util::valueOf(const «en.cppTypeName» value) {
			switch(value) {
			«FOR l : en.literals»
				case «l.name»: return «en.literals.indexOf(l)»;
			«ENDFOR»
			}	
			throw "Invalid value";
		}
		«en.cppTypeName» «en.cppTypeName»Util::valueOf(const ::mora::int32 value){
			switch(value){
			«FOR l : en.literals»
				case «en.literals.indexOf(l)»: return «l.name»;
			«ENDFOR»	
			}
			throw "Invalid value";
		}
		
		«en.cppTypeName» «en.cppTypeName»Util::read(::mora::InputStream& stream){
			::mora::int32 val;
			stream >> val;
			return valueOf(val);
		}
		std::vector<«en.cppTypeName»> «en.cppTypeName»Util::readList(::mora::InputStream& stream){
			::mora::int32 c;
			stream >> c;
			std::vector<«en.cppTypeName»> out(c);
			for (int i = 0; i < c; i++)
				out[i] = read(stream);
			return out;
		}
		
		void «en.cppTypeName»Util::write(const «en.cppTypeName» value, ::mora::OutputStream& stream){
			stream << valueOf(value);
		}
		void «en.cppTypeName»Util::write(const std::vector<«en.cppTypeName»>& value, ::mora::OutputStream& stream){
			::mora::int32 size = (::mora::int32)value.size();
			stream << size;
			for (int i = 0; i < size; i++)
				stream << valueOf(value[i]);
		}
	'''
	
	def generateStructSourceContent(StructDecl s)
	'''
		«s.structName»::«s.structName»()
			:	«FOR m : s.member SEPARATOR ', '»
					«m.memberName»(«m.type.defaultValue»)
				«ENDFOR»
		{
		}
		
		«s.structName»::«s.structName»(const «s.structName»& copy)
			:	«FOR m : s.member SEPARATOR ', '»
				«IF m.type.many»
					«m.memberName»(copy.«m.memberName».begin(), copy.«m.memberName».end())
				«ELSE»
					«m.memberName»(copy.«m.memberName»)
				«ENDIF»	
				«ENDFOR»
		{
		}
		«s.structName»::~«s.structName»()
		{
			«FOR m : s.member»
				«IF m.type.singleType.struct»
					«IF m.type.many»
						for (size_t i = 0; i < «m.memberName».size(); i++)
							if («m.memberName»[i] != NULL)
								delete «m.memberName»[i];
					«ELSE»
						if («m.memberName» != NULL)
							delete «m.memberName»;
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		}
		
		
		
		«s.structName»* «s.structName»::read(::mora::InputStream& stream) {
			::mora::int8 flag;
			stream >> flag;
			if (flag == ::mora::STRUCT_NULL)
				return NULL;
			CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expecting %i (start flag) but found: %i", ::mora::STRUCT_START, flag);
			
			«s.structName»* out = new «s.structName»();
			«FOR m : s.member»
				«IF m.type.singleType.prim»
					stream >> out->«m.memberName»;
				«ELSEIF m.type.singleType.enum»
					out->«m.memberName» = «m.type.singleType.cppTypeName»Util::read«IF m.type.many»List«ENDIF»(stream);
				«ELSE»
					out->«m.memberName» = «m.type.singleType.structType.structName»::read«IF m.type.many»List«ENDIF»(stream);
				«ENDIF»
			«ENDFOR»
			
			stream >> flag;
			CHECK_EQ_F(flag, ::mora::STRUCT_END, "Expecting %i (end flag) but found: %i", ::mora::STRUCT_END, flag);
			return out;
		}
		std::vector<«s.structName»*> «s.structName»::readList(::mora::InputStream& stream){
			::mora::int32 c;
			stream >> c;
			std::vector<«s.structName»*> out(c);
			for (int i = 0; i < c; i++)
				out[i] = read(stream);
			return out;
		}
		
		void «s.structName»::write(const «s.structName»* value, ::mora::OutputStream& stream) {
			if (value == NULL){
				stream << ::mora::STRUCT_NULL;
				return ;
			}
			
			stream << ::mora::STRUCT_START;
			«FOR m : s.member»
				«IF m.type.singleType.prim»
					stream << value->«m.memberName»;
				«ELSEIF m.type.singleType.enum»
					«m.type.singleCppTypeName»Util::write(value->«m.memberName», stream);
				«ELSE»
					«m.type.singleType.structType.structName»::write(value->«m.memberName», stream);
				«ENDIF»
			«ENDFOR»
			stream << ::mora::STRUCT_END;
		}
		void «s.structName»::write(const std::vector<«s.structName»*>& value, ::mora::OutputStream& stream) {
			::mora::int32 size = (::mora::int32)value.size();
			stream << size;
			for (int i = 0; i < size; i++)
				write(value[i], stream);
		}
	'''
	
	def generateHeaderContent(Model m)
	'''
		«IF m.options.cppOptions.hasNamesapce»
			«m.options.cppOptions.beginNamespace»
		«ENDIF»
		
			«IF m.eAllContents.filter(typeof(EnumDecl)).empty == false»
				////////////////////////////////////////////////////////////////////////////////
				//							Enumerations									  //
				////////////////////////////////////////////////////////////////////////////////
				«FOR en : m.eAllContents.filter(typeof(EnumDecl)).toIterable»
					«en.generateEnumHeaderContent»
				«ENDFOR»
			«ENDIF»
			
			«IF m.eAllContents.filter(typeof(EnumDecl)).empty == false»
				////////////////////////////////////////////////////////////////////////////////
				//								Structs										  //
				////////////////////////////////////////////////////////////////////////////////
				
				/********************* Pre Definition *********************************/
				«FOR s : m.eAllContents.filter(typeof(StructDecl)).toIterable»
					struct «s.structName»;
				«ENDFOR»
				
				/********************* Full Definition *********************************/
				«FOR s : m.eAllContents.filter(typeof(StructDecl)).toIterable»
					«s.generateStructHeaderContent»
				«ENDFOR»
			«ENDIF»
			
			
			«IF m.eAllContents.filter(typeof(Interface)).empty == false»
				////////////////////////////////////////////////////////////////////////////////
				//								Interfaces										  //
				////////////////////////////////////////////////////////////////////////////////
				
				/********************* Pre Definition *********************************/
				«FOR s : m.eAllContents.filter(typeof(Interface)).toIterable»
					class «s.IFaceName»;
					typedef std::shared_ptr<«s.IFaceName»> «s.cppTypeName»;
					class «s.adapterName»;
					class «s.proxyName»;
				«ENDFOR»
				
				/********************* Full Definition *********************************/
				
				«FOR iface : m.eAllContents.filter(typeof(Interface)).toIterable»
					«iface.generateInterfaceHeaderContent»
					
				«ENDFOR»
			«ENDIF»
			
		
		«IF m.options.cppOptions.hasNamesapce»
			«m.options.cppOptions.endNamespace»
		«ENDIF»
	'''
	
	def generateInterfaceHeaderContent(Interface iface)
	'''
		//--------------- «iface.name» --------------------------//
		«iface.IFaceHeader»
		«iface.AdapterHeader»
		«iface.ProxyHeader»
	'''
	
	def IFaceHeader(Interface iface)
	'''
		class «iface.IFaceName» {
		public:
			«iface.IFaceName»(){}
			virtual ~«iface.IFaceName»(){}
			
			«FOR m : iface.methods»
				virtual «m.type.cppTypeName» «m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR») = 0;
			«ENDFOR»
		};
	'''
	
	
	

	
	def String beginNamespace(CppOptions options){
		val qn = QualifiedName::create(options.baseNamespace.split("::"))
		var out = "";
		for (s : qn.segments){
			out = out + "namespace " + s + "{ "
		}
		return out; 
	}
	def String endNamespace(CppOptions options){
		val qn = QualifiedName::create(options.baseNamespace.split("::"))
		var out = "";
		for (s : qn.segments){
			out = out + "} /*" + s + "*/ "
		}
		return out; 
	}
	
	def boolean hasNamesapce(CppOptions options){
		return options !== null && options.baseNamespace !== null && options.baseNamespace.empty == false;
	}
	
	def generateStructHeaderContent(StructDecl s)
	'''
		struct «s.structName» {
			«FOR m : s.member»
				«m.type.cppTypeName» «m.memberName»;
			«ENDFOR»
			
			«s.structName»();			
			«s.structName»(const «s.structName»& copy);
			~«s.structName»();
			
			
			static «s.structName»* read(::mora::InputStream& stream);
			static std::vector<«s.structName»*> readList(::mora::InputStream& stream);
			
			static void write(const «s.structName»* value, ::mora::OutputStream& stream);
			static void write(const std::vector<«s.structName»*>& value, ::mora::OutputStream& stream);
		};
		
		
	'''
	
	def String defaultValue(TypeDecl t){
		if (t.many){
			return t.cppTypeName+"()";
		}else if (t.isPointer){
			return "NULL";
		}else if (t.enum){
			return t.enumType.literals.get(0).name;	
		}else if (t.isPrim){
			switch(t.primType.name){
			case BOOL: return "false"
			case BYTE: return "(::mora::int8)0"
			case DOUBLE: return "0.0"
			case FLOAT: return "0.0f"
			case INTEGER: return "0"
			case LONG: return "0"
			case SHORT: return "(::mora::int16)0"
			case STRING: return "\"\""
		}
		}
		return "NULL";		
	}

	
	def generateEnumHeaderContent(EnumDecl en)
	'''
		enum «en.cppTypeName» {
			«FOR l : en.literals SEPARATOR ', '»
				«l.name»
			«ENDFOR»
		};
		
		class «en.cppTypeName»Util {
		public:
			static ::mora::int32 valueOf(const «en.cppTypeName» value);
			static «en.cppTypeName» valueOf(const ::mora::int32 value);
			
			static «en.cppTypeName» read(::mora::InputStream& stream);
			static std::vector<«en.cppTypeName»> readList(::mora::InputStream& stream);
			
			static void write(const «en.cppTypeName» value, ::mora::OutputStream& stream);
			static void write(const std::vector<«en.cppTypeName»>& value, ::mora::OutputStream& stream);
		};
	'''
	
	
	
	
	
	
	
	

	def ProxyHeader(Interface iface)
	'''
		class «iface.proxyName» : public «iface.IFaceName», public ::mora::IMoraProxy {
		public:
			«iface.proxyName»(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject);
			virtual ~«iface.proxyName»();
			
			static std::shared_ptr<«iface.proxyName»> createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress);
			
			static «iface.cppTypeName» read(::mora::InputStream& stream, ::mora::Communicator* communicator);
			static std::vector<«iface.cppTypeName»> readList(::mora::InputStream& stream, ::mora::Communicator* communicator);
			
		public:
			«FOR m : iface.methods»
				«m.type.cppTypeName» «m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»);
			«ENDFOR»
		public:
			«FOR m : iface.methods»
				std::future<«m.type.cppTypeName»> async_«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»);
			«ENDFOR»
		};
		'''
	
	
	def generateInterfaceSourceContent(Interface decl)
	'''
	
	
		// -------------------------- Adapter -------------------------------//
		«decl.adapterImpl»
		
		
		// -------------------------- Proxy -------------------------------//
		«decl.proxyImpl»		
	'''
	
	def proxyImpl(Interface i)
	'''
		«i.proxyName»::«i.proxyName»(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject)
			:	::mora::IMoraProxy(communicator, remoteObject)
		{
		}
		«i.proxyName»::~«i.proxyName»(){
		}
		
		std::shared_ptr<«i.proxyName»> «i.proxyName»::createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress){
			«i.proxyName»* proxy = new «i.proxyName»(communicator, ::mora::RemoteObject::create(qualifiedAddress));
			static std::string tn("«i.name.toUpperCase»");
			if (proxy->checkType(tn)){
				std::shared_ptr<«i.proxyName»> ptr(proxy);
				return ptr;
			}else{
				delete proxy;
				return std::shared_ptr<«i.proxyName»>(NULL);
			}
		}
		«i.cppTypeName» «i.proxyName»::read(::mora::InputStream& stream, ::mora::Communicator* communicator) {
			::mora::int8 flag;
			stream >> flag;
			if (flag == ::mora::STRUCT_NULL)
				return «i.cppTypeName»(NULL);
			CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expected %i (start flag for «i.name» but found: %i", ::mora::STRUCT_START, flag);
			std::string quid;
			stream >> quid;
			::mora::IMoraProxyPtr ptr = communicator->getProxy(quid);
			«i.cppTypeName» proxy;
			if (ptr) {
				proxy = std::dynamic_pointer_cast<«i.IFaceName»>(ptr);
			}
			if (proxy == nullptr){
				proxy = createProxy(communicator, quid);
			}
			CHECK_F(proxy != nullptr, "Failed to create proxy of: %s", quid.c_str());
			return proxy;
		}
		std::vector<«i.cppTypeName»> «i.proxyName»::readList(::mora::InputStream& stream, ::mora::Communicator* communicator) {
			::mora::int32 size;
			stream >> size;
			std::vector<«i.cppTypeName»> out(size);
			for (int i = 0; i < size; i++)
				out.push_back(read(stream, communicator));
			return out;
		}
		
		
		//----------	Interface implementation 	---------------------//
		
		«FOR m : i.methods»
			«m.type.cppTypeName» «i.proxyName»::«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»){
				std::future<«m.type.cppTypeName»> future = async_«m.name»(«FOR p : m.parameters SEPARATOR ', '»«p.name»«ENDFOR»);
				«IF m.type.void»
					future.get();
				«ELSE»
					return future.get();
				«ENDIF»
			}
		«ENDFOR»
		
		
		«FOR m : i.methods»
			class «m.signature»_RemoteCall : public ::mora::IRemoteMethodCall {
			private:
				«FOR p : m.parameters»
					«p.type.cppTypeName»	_«p.name»;
				«ENDFOR»
				std::promise<«m.type.cppTypeName»>	promise;
			public:
				«m.signature»_RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod«IF m.parameters.empty==false», «FOR p : m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»«ENDIF»)
					:	::mora::IRemoteMethodCall(communicator, targetMethod)«IF m.parameters.empty==false», «FOR p : m.parameters SEPARATOR ', '»_«p.name»(«p.name»)«ENDFOR»«ENDIF»
				{
				}
				virtual ~«m.signature»_RemoteCall()
				{
				}
				
				std::future<«m.type.cppTypeName»> invoke() {
					mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
					«IF m.parameters.empty==false»
						//Do the encoding
						«FOR p : m.parameters»
							«IF p.type.singleType.prim»
								mMethodCall->stream << _«p.name»;
							«ELSEIF p.type.singleType.enum»
								«p.type.singleCppTypeName»Util::write(_«p.name», mMethodCall->stream);
							«ELSEIF p.type.singleType.proxy»
								«p.type.singleType.proxyType.adapterName»::write(_«p.name», mMethodCall->stream, mCommunicator);
							«ELSE»
								«p.type.singleType.structType.structName»::write(_«p.name», mMethodCall->stream);
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					
					mCommunicator->call(this);
					return promise.get_future();
				}
				void handleResult(::mora::InMethodResponse& response) {
					«IF m.type.void»
						promise.set_value();
					«ELSE»
						«IF m.type.singleType.prim»
							«m.type.cppTypeName» _result;
							response.stream() >> _result;
						«ELSEIF m.type.singleType.enum»
							«m.type.cppTypeName» _result = «m.type.singleType.cppTypeName»Util::read«IF m.type.many»List«ENDIF»(response.stream());
						«ELSEIF m.type.singleType.proxy»
							«m.type.cppTypeName» _result = «m.type.singleType.proxyType.proxyName»::read«IF m.type.many»List«ENDIF»(response.stream(), mCommunicator);
						«ELSE»
							«m.type.cppTypeName» _result = «m.type.singleType.structType.structName»::read«IF m.type.many»List«ENDIF»(response.stream());
						«ENDIF»
						promise.set_value(_result);
					«ENDIF»
				}
			};
			std::future<«m.type.cppTypeName»> «i.proxyName»::async_«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»){
				«m.signature»_RemoteCall* call = new «m.signature»_RemoteCall(getCommunicator(), getMethod("«m.signature»")«IF m.parameters.empty==false», «FOR p:m.parameters SEPARATOR ', '»«p.name»«ENDFOR»«ENDIF»);
				//the call will be removed by the communicator, after receiving the response
				return call->invoke();
			}
		«ENDFOR»
		
	'''
	
	
		def AdapterHeader(Interface iface)
	'''
		class «iface.adapterName» : public ::mora::MoraAdapter<«iface.IFaceName»> {
«««		public:
«««			typedef std::map<std::string, InvokerFuncPtr> InvokerFunctionMap;
		private:
			static InvokerFunctionMap	sInvokerMap;
		public:
			«iface.adapterName»(::mora::Communicator* communicator, «iface.cppTypeName» iface, const std::string& identifier);
			virtual ~«iface.adapterName»();
			
			inline InvokerFunctionMap& getInvokerFunctionMap() { return sInvokerMap; }
			virtual const std::string getTypeIdentifier() const { return std::string("«iface.name.toUpperCase»"); }
			
			static ::mora::IMoraAdapterPtr createAdapter(::mora::Communicator* communicator, «iface.cppTypeName» iface, const std::string& identifier);
			
			static void write(«iface.cppTypeName» value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
			static void write(const std::vector<«iface.cppTypeName»>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
		};
	'''
	
	def adapterImpl(Interface i)
	'''
		static void _invoke_«i.name»__getType_(«i.IFaceName»* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
			static std::string tn("«i.name.toUpperCase»");
			os << tn;
		}
		«FOR m : i.methods»
			static void _invoke_«i.name»_«m.signature»(«i.IFaceName»* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
				«FOR ip : m.parameters»
					«IF ip.type.singleType.prim»
						«ip.type.cppTypeName» _«ip.name»;
						is >> _«ip.name»;
					«ELSEIF ip.type.singleType.enum»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.cppTypeName»Util::read«IF ip.type.many»List«ENDIF»(is);
					«ELSEIF ip.type.singleType.proxy»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.proxyType.proxyName»::read«IF ip.type.many»List«ENDIF»(is, communicator);
					«ELSE»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.structType.structName»::read«IF ip.type.many»List«ENDIF»(is);
					«ENDIF»
				«ENDFOR»
				
				«IF m.type.void»
					delegate->«m.name»(«FOR p : m.parameters SEPARATOR ', '»_«p.name»«ENDFOR»);
				«ELSE»
					«m.type.cppTypeName» _result = delegate->«m.name»(«FOR p : m.parameters SEPARATOR ', '»_«p.name»«ENDFOR»);
					
					«IF m.type.singleType.prim»
						os << _result;
					«ELSEIF m.type.singleType.enum»
						«m.type.singleType.cppTypeName»Util::write(_result, os);
					«ELSEIF m.type.singleType.proxy»
						«m.type.singleType.proxyType.adapterName»::write(_result, os, communicator);
					«ELSE»
						«m.type.singleType.structType.structName»::write(_result, os);
					«ENDIF»
				«ENDIF»
				
				«FOR ip : m.parameters»
					«IF ip.type.singleType.struct»
						«IF ip.type.many»
							for (size_t _i_ = 0; _i_ < _«ip.name».size(); ++_i_)
								if (_«ip.name»[_i_] != NULL)
									delete _«ip.name»[_i_];
						«ELSE»
							if (_«ip.name» != NULL)
								delete _«ip.name»;
						«ENDIF»
					«ENDIF»
				«ENDFOR»
			}
		«ENDFOR»
		
		«i.adapterName»::InvokerFunctionMap create«i.name.toFirstUpper»InvokerMap()
		{
			«i.adapterName»::InvokerFunctionMap im;
			im.insert(std::make_pair("_getType_", &_invoke_«i.name»__getType_));
			«FOR m : i.methods»
				im.insert(std::make_pair("«m.signature»", &_invoke_«i.name»_«m.signature»));
			«ENDFOR»
			return im;
		}
		
		«i.adapterName»::InvokerFunctionMap «i.adapterName»::sInvokerMap = create«i.name.toFirstUpper»InvokerMap();
		
		«i.adapterName»::«i.adapterName»(::mora::Communicator* communicator, «i.cppTypeName» iface, const std::string& identifier)
			:	::mora::MoraAdapter<«i.IFaceName»>(communicator, iface, identifier)
		{
		}
		
		«i.adapterName»::~«i.adapterName»()
		{
		}
		

		::mora::IMoraAdapterPtr «i.adapterName»::createAdapter(::mora::Communicator* communicator, «i.cppTypeName» iface, const std::string& identifier)
		{
			return communicator->registerAdapter(::mora::IMoraAdapterPtr(new «i.adapterName»(communicator, iface, identifier)));
		}

		void «i.adapterName»::write(«i.cppTypeName» value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
			if (!value){
				stream << ::mora::STRUCT_NULL;
				return ;
			}
			stream << ::mora::STRUCT_START;
			::mora::IMoraAdapterPtr adapter = communicator->getAdapter(value);
			if (!adapter){
				adapter = createAdapter(communicator, value, communicator->createRandomIdentifier());
			}
			CHECK_F(adapter != nullptr, "Failed to create Adapter");
			stream << adapter->getQualifiedIdentifier();
		}
		void «i.adapterName»::write(const std::vector<«i.cppTypeName»>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
			::mora::int32 size = (::mora::int32)value.size();
			stream << size;
			for (int i = 0; i < size; i++)
				write(value[i], stream, communicator);
		}
	'''
	
	
	
	
	
	
	
		
	
	def String getMemberName(Member member){
		return member.name
	}
	
	
}
