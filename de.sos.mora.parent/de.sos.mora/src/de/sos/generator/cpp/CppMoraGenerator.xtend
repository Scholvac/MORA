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

class CppMoraGenerator extends AbstractGenerator {
	
	extension CppUtils 		= new CppUtils
	extension TypeUtil 		= new TypeUtil
	extension CppTypeUtil	= new CppTypeUtil
	
	
	
	override doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
		val m = resource.getModel()
		m.generateHeader(fsa)
		m.generateSource(fsa);
		
	}
	
	
	
	def Model getModel(Resource resource){
		for (m : resource.allContents.filter(typeof(Model)).toIterable)
			return m;
		return null;
	}
	
	def void generateHeader(Model m, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("MoraGen.h")
		rememberImports("memory")
		rememberImports("map")
		rememberImports("future")
		
		val content = m.generateHeaderContent
		val headerFileName = m.getTypesHeaderFile();
		
		var allContent = "#ifndef " + m.name.toUpperCase + "_TYPES_H_\n#define " + m.name.toUpperCase+"_TYPES_H_\n\n\n";
		allContent = allContent + getImportBlock(QualifiedName::create(m.name.toFirstUpper+"Types.h")) + "\n";
		allContent = allContent + content;
		allContent = allContent + "#endif //"m.name.toUpperCase + "_TYPES_H_"
		fsa.generateFile(headerFileName, allContent);
		
		
	}
	def void generateSource(Model m, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("future")
		rememberImports(m.getTypesHeaderFile())
		
		val content = m.generateSourceContent
		val sourceFileName = m.getTypesSourceFile();
		
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
		
		bool «s.structName»::operator==(const «s.structName»& other) const {
			«FOR m : s.member»
				«IF m.type.singleType.struct && m.type.isMany == false»
					if («m.memberName» != other.«m.memberName») //pointer compare
						if (!(*«m.memberName» == *other.«m.memberName»))
							return false;
				«ELSE»
					if (!(«m.memberName» == other.«m.memberName»))
						return false;
				«ENDIF»
			«ENDFOR»
			return true;
		}
		
		bool operator==(const std::vector<«s.structName»*>& list1, const std::vector<«s.structName»*>& list2){
			if (list1.size() != list2.size())
				return false;
			for (size_t i = 0; i < list1.size(); i++){
				if (!(*list1[i] == *list2[i]))
					return false;
			}
			return true;
		}
		
		
		«s.structName»* «s.structName»::read(::mora::InputStream& stream) {
			::mora::int8 flag;
			stream >> flag;
			if (flag == ::mora::STRUCT_NULL)
				return NULL;
			SERIALIZER_CHECK_EQ(flag, ::mora::STRUCT_START, "Expecting %i (start flag) but found: %i");
			
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
			SERIALIZER_CHECK_EQ(flag, ::mora::STRUCT_END, "Expecting %i (end flag) but found: %i");
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
					using «s.cppTypeName» = std::shared_ptr<«s.IFaceName»>;
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
			
			bool operator== (const «s.structName»& other) const;
		};
		
		bool operator==(const std::vector<«s.structName»*>& list1, const std::vector<«s.structName»*>& list2);
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
		class «iface.proxyName» : public «iface.IFaceName», public ::mora::Proxy {
		public:
			«iface.proxyName»(::mora::RemoteObject remoteObject, ::mora::Communicator& communicator);
			virtual ~«iface.proxyName»();
			
			static std::shared_ptr<«iface.proxyName»> createProxy(const std::string& qualifiedAddress, ::mora::Communicator& communicator);
			
			static «iface.cppTypeName» read(::mora::InputStream& stream, ::mora::Communicator& communicator);
			static std::vector<«iface.cppTypeName»> readList(::mora::InputStream& stream, ::mora::Communicator& communicator);
			
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
		«i.proxyName»::«i.proxyName»(::mora::RemoteObject remoteObject, ::mora::Communicator& communicator)
			:	::mora::Proxy(remoteObject, "«i.name.toUpperCase»", communicator)
		{
		}
		«i.proxyName»::~«i.proxyName»(){
		}
		
		std::shared_ptr<«i.proxyName»> «i.proxyName»::createProxy(const std::string& qualifiedAddress, ::mora::Communicator& communicator){
			«i.proxyName»* proxy = new «i.proxyName»(::mora::RemoteObject{qualifiedAddress}, communicator);
			static std::string tn("«i.name.toUpperCase»");
			try{
				if (proxy->checkType(tn)){
					std::shared_ptr<«i.proxyName»> ptr(proxy);
					return ptr;
				}
			}catch(::mora::MoraException exp){
				LOG_WARN(exp.what());
			}
			delete proxy;
			return std::shared_ptr<«i.proxyName»>(NULL);
		}
		«i.cppTypeName» «i.proxyName»::read(::mora::InputStream& stream, ::mora::Communicator& communicator) {
			::mora::int8 flag;
			stream >> flag;
			if (flag == ::mora::STRUCT_NULL)
				return «i.cppTypeName»(NULL);
			SERIALIZER_CHECK_EQ(flag, ::mora::STRUCT_START, "Expected %i (start flag for «i.name» but found: %i");
			
			std::string quid;
			stream >> quid;
			«i.cppTypeName» proxy;
			
			::mora::MoraObjectPtr obj = communicator.getObject(quid);
			if (obj) {
				if (obj->type() == "«i.name.toUpperCase»") {
					if (obj->isProxy()) {
						«i.cppTypeName» ptr = std::dynamic_pointer_cast<«i.IFaceName»>(obj);
						if (ptr)
							return ptr;
					}
					else {
						std::shared_ptr<«i.adapterName»> aptr = std::dynamic_pointer_cast<«i.adapterName»>(obj);
						if (aptr) {
							return std::static_pointer_cast<«i.IFaceName»>(aptr->getDelegate());
						}
					}
				}
			}
			::mora::ProxyPtr ptr = communicator.getProxy(quid);
			if (ptr == nullptr){
				proxy = createProxy(quid, communicator);
				communicator.registerProxy(std::dynamic_pointer_cast<Proxy>(proxy));
			}else {
				proxy = std::dynamic_pointer_cast<«i.IFaceName»>(ptr);
			}
			if (proxy == nullptr){
				throw ::mora::ProxyException("Detected wrong type for proxy");
			}
			return proxy;
		}
		std::vector<«i.cppTypeName»> «i.proxyName»::readList(::mora::InputStream& stream, ::mora::Communicator& communicator) {
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
				std::future_status status = future.wait_for(timeout());
				if (status != std::future_status::ready)
					throw std::runtime_error("Call «i.proxyName»::«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName»«ENDFOR») did not return in time");
				«IF m.type.void»
					future.get();
				«ELSE»
					return future.get();
				«ENDIF»				
			}
		«ENDFOR»
		
		
		«FOR m : i.methods»
			class «m.signature»_RemoteCall : public ::mora::IRemoteMethodCall {
			public:
				std::promise<«m.type.cppTypeName»>	promise;
			
				«m.signature»_RemoteCall(const ::mora::RemoteMethod& targetMethod, ::mora::Communicator& communicator«IF m.parameters.empty==false», «FOR p : m.parameters SEPARATOR ', '»«p.type.cppTypeName» _«p.name»«ENDFOR»«ENDIF»)
					:	::mora::IRemoteMethodCall(targetMethod, communicator)
				{
					«IF m.parameters.empty==false»
						//Do the encoding
						«FOR p : m.parameters»
							«IF p.type.singleType.prim»
								parameterOutStream() << _«p.name»;
							«ELSEIF p.type.singleType.enum»
								«p.type.singleCppTypeName»Util::write(_«p.name», parameterOutStream());
							«ELSEIF p.type.singleType.proxy»
								«p.type.singleType.proxyType.adapterName»::write(_«p.name», parameterOutStream(), communicator);
							«ELSE»
								«p.type.singleType.structType.structName»::write(_«p.name», parameterOutStream());
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				}
				virtual ~«m.signature»_RemoteCall()
				{
				}
				
				virtual void handleResponse() {
					«IF m.type.void»
						promise.set_value();
					«ELSE»
						«IF m.type.singleType.prim»
							«m.type.cppTypeName» _result;
							responseInStream() >> _result;
						«ELSEIF m.type.singleType.enum»
							«m.type.cppTypeName» _result = «m.type.singleType.cppTypeName»Util::read«IF m.type.many»List«ENDIF»(responseInStream());
						«ELSEIF m.type.singleType.proxy»
							«m.type.cppTypeName» _result = «m.type.singleType.proxyType.proxyName»::read«IF m.type.many»List«ENDIF»(responseInStream(), communicator());
						«ELSE»
							«m.type.cppTypeName» _result = «m.type.singleType.structType.structName»::read«IF m.type.many»List«ENDIF»(responseInStream());
						«ENDIF»
						promise.set_value(_result);
					«ENDIF»
				}
			};
			std::future<«m.type.cppTypeName»> «i.proxyName»::async_«m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»){
				«m.signature»_RemoteCall* remoteCall = new «m.signature»_RemoteCall(getMethod("«m.signature»"), communicator()«IF m.parameters.empty==false», «FOR p:m.parameters SEPARATOR ', '»«p.name»«ENDFOR»«ENDIF»);
				
				call(remoteCall);
				
				return remoteCall->promise.get_future();
			}
		«ENDFOR»
		
	'''
	
	
		def AdapterHeader(Interface iface)
	'''
		class «iface.adapterName» : public ::mora::Adapter {
		private:
			static InvokerFunctionMap	sInvokerMap;
		public:
			«iface.adapterName»(«iface.cppTypeName» iface, const ::mora::RemoteObject& identifier);
			virtual ~«iface.adapterName»();
			
			static ::mora::AdapterPtr createAdapter(«iface.cppTypeName» iface, const std::string& identifier, ::mora::Communicator& communicator);
			
			static void write(«iface.cppTypeName» value, ::mora::OutputStream& stream, ::mora::Communicator& communicator);
			static void write(const std::vector<«iface.cppTypeName»>& value, ::mora::OutputStream& stream, ::mora::Communicator& communicator);
		};
	'''
	
	def adapterImpl(Interface i)
	'''
		static void _invoke_«i.name»__getType_(void* delegate, ::mora::IRemoteMethodCall& context){
			static std::string tn("«i.name.toUpperCase»");
			context.responseOutStream() << tn;
		}
		«FOR m : i.methods»
			static void _invoke_«i.name»_«m.signature»(void* ptr, ::mora::IRemoteMethodCall& call){
				«i.IFaceName»* delegate = static_cast<«i.IFaceName»*>(ptr);
				«FOR ip : m.parameters»
					«IF ip.type.singleType.prim»
						«ip.type.cppTypeName» _«ip.name»;
						call.parameterInStream() >> _«ip.name»;
					«ELSEIF ip.type.singleType.enum»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.cppTypeName»Util::read«IF ip.type.many»List«ENDIF»(call.parameterInStream());
					«ELSEIF ip.type.singleType.proxy»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.proxyType.proxyName»::read«IF ip.type.many»List«ENDIF»(call.parameterInStream(), call.communicator());
					«ELSE»
						«ip.type.cppTypeName» _«ip.name» = «ip.type.singleType.structType.structName»::read«IF ip.type.many»List«ENDIF»(call.parameterInStream());
					«ENDIF»
				«ENDFOR»
				
				«IF m.type.void»
					delegate->«m.name»(«FOR p : m.parameters SEPARATOR ', '»_«p.name»«ENDFOR»);
				«ELSE»
					«m.type.cppTypeName» _result = delegate->«m.name»(«FOR p : m.parameters SEPARATOR ', '»_«p.name»«ENDFOR»);
					
					«IF m.type.singleType.prim»
						call.responseOutStream() << _result;
					«ELSEIF m.type.singleType.enum»
						«m.type.singleType.cppTypeName»Util::write(_result, call.responseOutStream());
					«ELSEIF m.type.singleType.proxy»
						«m.type.singleType.proxyType.adapterName»::write(_result, call.responseOutStream(), call.communicator());
					«ELSE»
						«m.type.singleType.structType.structName»::write(_result, call.responseOutStream());
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
		
		«i.adapterName»::«i.adapterName»(«i.cppTypeName» iface, const ::mora::RemoteObject& identity)
			:	::mora::Adapter(std::static_pointer_cast<void>(iface), identity, "«i.name.toUpperCase»", sInvokerMap)
		{
		}
		
		«i.adapterName»::~«i.adapterName»()
		{
		}
		

		::mora::AdapterPtr «i.adapterName»::createAdapter(«i.cppTypeName» iface, const std::string& identifier, ::mora::Communicator& communicator)
		{
			::mora::AdapterPtr ptr(new «i.adapterName»(iface, communicator.createIdentity(identifier)));
			if (communicator.registerAdapter(ptr))
				return ptr;
			return ::mora::AdapterPtr(nullptr);
		}

		void «i.adapterName»::write(«i.cppTypeName» value, ::mora::OutputStream& stream, ::mora::Communicator& communicator){
			if (!value){
				stream << ::mora::STRUCT_NULL;
				return ;
			}
			
			::mora::MoraObject* mora_object = dynamic_cast<::mora::MoraObject*>(value.get());
			if (mora_object != nullptr){
				stream << ::mora::STRUCT_START;
				stream << mora_object->identity().qualifiedIdentifier();
			}else{
				::mora::AdapterPtr adapter = communicator.getAdapter((void*)value.get());
				if (!adapter){
					adapter = createAdapter(value, ::mora::MoraUtils::createRandomIdentifier(), communicator);
				}
				if (! (adapter) )
					throw ::mora::MoraException("Failed to create Adapter");
				
				stream << ::mora::STRUCT_START;
				stream << adapter->identity().qualifiedIdentifier();
			}
		}
		void «i.adapterName»::write(const std::vector<«i.cppTypeName»>& value, ::mora::OutputStream& stream, ::mora::Communicator& communicator){
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
