package de.sos.generator.cpp

import de.sos.generator.ImportUtil
import de.sos.generator.TypeUtil
import de.sos.generator.TypeUtil.ProxyTypeDecl
import de.sos.mORA.Interface
import de.sos.mORA.PrimTypeDecl
import de.sos.mORA.StructDecl
import de.sos.mORA.TypeDecl
import java.io.File
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import de.sos.mORA.Model

class CppTypeUtil extends ImportUtil {
	
	extension TypeUtil = new TypeUtil
	
	extension IQualifiedNameProvider qnp = new QualifiedNameProvider()

	/////////////////////	Imports ////////////////////////////////////	
	def CharSequence getImportBlock(QualifiedName... toRemove)
	'''
		«FOR s : filteredImports(toRemove)»
			#include<«s.toString»>
		«ENDFOR»
		
	'''
	
	
	
	
	/////////////////////	Types  ////////////////////////////////////	
	
	def QualifiedName basePackage(Model m){
		if (m.options === null || m.options.javaOptions === null)
			return QualifiedName::create("");
		return QualifiedName::create(m.options.javaOptions.basePackage.split("\\."));
	}
	
	def QualifiedName fullyQualifiedName(TypeDecl decl){
		var qn = decl.model.basePackage;
		qn = qn.append(decl.model.name)
		qn = qn.append(decl.name)
		return qn;
	}
	def QualifiedName fullyQualifiedName(Interface decl){
		var qn = decl.model.basePackage;
		qn = qn.append(decl.model.name)
		qn = qn.append(decl.name)
		return qn;
	}

	
	
	
	def String getSingleCppTypeName(TypeDecl td){
		if (td.isMany && td.singleType.isPrim){
			return td.singleType.primType.cppTypeName
		}
		return td.singleType.cppTypeName
	}
	
	def String getStructName(StructDecl s){
		return s.name.toFirstUpper;
	}
	def String cppTypeName(Interface decl){
		return "I" + decl.name.toFirstUpper + "Ptr";
	}
	def String getIFaceName(Interface decl){
		return "I" + decl.name.toFirstUpper;
	}
	def String getProxyName(Interface decl){
		return decl.name.toFirstUpper + "Proxy";
	}
	def String getAdapterName(Interface decl){
		return decl.name.toFirstUpper + "Adapter";
	}
	def String cppTypeName(PrimTypeDecl decl){
		rememberImports("MoraPreReq.h")
		switch(decl.name){
			case BOOL: return "bool"
			case BYTE: return "::mora::int8"
			case DOUBLE: return "double"
			case FLOAT: return "float"
			case INTEGER: return"::mora::int32"
			case LONG: return "::mora::int64"
			case SHORT: return "::mora::int16"
			case STRING: return "std::string"
			case VOID: return "void"
		}
	}
	
	
	def String getCppTypeName(TypeDecl td){
		return getCppTypeName(td, false)
	}
	def String getCppTypeName(TypeDecl td, boolean asObject){
		if (td.isMany){
			rememberImports("vector")
			val st = td.singleType;
			if (st.isPrim)
				return "std::vector<" + st.primType.cppTypeName + ">"
			if (st.pointer)
				return "std::vector<"+st.cppTypeName+">"
			return "std::vector<"+st.cppTypeName+">"
		}else if (td.prim){
			if (asObject)
				return td.primType.cppTypeName
			else
				return td.primType.cppTypeName
		}else if (td.proxy){
			return td.iface.cppTypeName 
		}else {
			rememberImports(td.getHeaderFileName());
			if (td.struct)
				return td.fullyQualifiedName.lastSegment.toFirstUpper+"*"
			return td.fullyQualifiedName.lastSegment.toFirstUpper
		}
	}
	
	def String getHeaderFileName(TypeDecl decl){
		return decl.model.getTypesHeaderFile();
	}
	
	def String getTypesHeaderFile(Model model){
		val f = new File(model.eResource.URI.toFileString)
		return f.name.replace(".mora", ".h");
//		return model.name.toFirstUpper + "Types.h";
	}
	def String getTypesSourceFile(Model model){
		val f = new File(model.eResource.URI.toFileString)
		return f.name.replace(".mora", ".cpp");
	}
	
	def Interface getIface(TypeDecl decl){
		return (decl as ProxyTypeDecl).iface;
	}
		
	
	
	
	
}
