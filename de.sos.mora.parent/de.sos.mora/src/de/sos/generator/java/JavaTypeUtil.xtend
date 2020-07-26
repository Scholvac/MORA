package de.sos.generator.java

import de.sos.generator.ImportUtil
import de.sos.generator.TypeUtil
import de.sos.generator.TypeUtil.ProxyTypeDecl
import de.sos.mORA.Interface
import de.sos.mORA.Model
import de.sos.mORA.PrimTypeDecl
import de.sos.mORA.TypeDecl
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName

class JavaTypeUtil extends ImportUtil {
	
	extension TypeUtil = new TypeUtil
	
	extension IQualifiedNameProvider qnp = new QualifiedNameProvider()

	/////////////////////	Imports ////////////////////////////////////	
	def CharSequence getImportBlock(QualifiedName... toRemove)
	'''
		«FOR s : filteredImports(toRemove)»
			import «s.toString(".")»;
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

	
	
	
	def String getSingleJavaTypeName(TypeDecl td){
		if (td.isMany && td.singleType.isPrim){
			return td.singleType.primType.javaObjectType
		}
		return td.singleType.javaTypeName
	}
	def String getStreamPostFix(PrimTypeDecl decl){
		return getJavaObjectType(decl);
	}
	def String getJavaObjectType(PrimTypeDecl decl){
		switch(decl.name){
			case BOOL: return "Boolean"
			case BYTE: return "Byte"
			case DOUBLE: return "Double"
			case FLOAT: return "Float"
			case INTEGER: return "Integer"
			case LONG: return "Long"
			case SHORT: return "Short"
			case STRING: return "String"
			case VOID: return "Void"
		}
	}
	def String getJavaTypeName(PrimTypeDecl decl){
		switch(decl.name){
			case BOOL: return "boolean"
			case BYTE: return "byte"
			case DOUBLE: return "double"
			case FLOAT: return "float"
			case INTEGER: return "int"
			case LONG: return "long"
			case SHORT: return "short"
			case STRING: return "String"
			case VOID: return "void"
		}
	}
	def String getJavaListTypeName(PrimTypeDecl decl){
		switch(decl.name){
			case BOOL: { rememberImports("de.sos.mora.list.BoolArrayList") return "BoolArrayList"}
			case BYTE: { rememberImports("de.sos.mora.list.ByteArrayList") return "ByteArrayList"}
			case DOUBLE: { rememberImports("de.sos.mora.list.DoubleArrayList") return "DoubleArrayList"}
			case FLOAT: { rememberImports("de.sos.mora.list.FloatArrayList") return "FloatArrayList"}
			case INTEGER: { rememberImports("de.sos.mora.list.IntArrayList") return "IntArrayList"}
			case LONG: { rememberImports("de.sos.mora.list.LongArrayList") return "LongArrayList"}
			case SHORT: { rememberImports("de.sos.mora.list.ShortArrayList") return "ShortArrayList"}
			case STRING: { rememberImports("de.sos.mora.list.StringArrayList") return "StringArrayList"}
			case VOID: return "void"
		}
	}
	
	
	def String getJavaTypeName(TypeDecl td){
		return getJavaTypeName(td, false)
	}
	def String getJavaTypeName(TypeDecl td, boolean asObject){
		if (td.isMany){
			rememberImports("java.util.List")
			val st = td.singleType;
			if (st.isPrim)
				return st.primType.javaListTypeName;
			return "List<"+st.javaTypeName+">"
		}else if (td.prim){
			if (asObject)
				return td.primType.javaObjectType
			else
				return td.primType.javaTypeName
		}else if (td.proxy){
			return td.iface.IFaceName
		}else {
			rememberImports(td.fullyQualifiedName);
			return td.fullyQualifiedName.lastSegment.toFirstUpper
		}
	}
	
	
	
	def Interface getIface(TypeDecl decl){
		return (decl as ProxyTypeDecl).iface;
	}
	
	def String getIFaceName(Interface iface){
		rememberImports(iface.QContainerName)
		return iface.containerClassName + ".IFace"
	}
	def String getProxyName(Interface iface){
		rememberImports(iface.QContainerName)
		return iface.containerClassName + ".Proxy"
	}
	def String getAdapterName(Interface iface){
		rememberImports(iface.QContainerName)
		return iface.containerClassName + ".Adapter"
	}
	
	def QualifiedName getQContainerName(Interface iface){
		val qn = iface.fullyQualifiedName.skipLast(1).append(iface.containerClassName)
		return qn;
	}
	def String getContainerClassName(Interface iface){
		return iface.name.toFirstUpper + "RPC"
	}

	
	
	
	
	
	
}
