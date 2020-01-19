package de.sos.generator.csharp

import de.sos.generator.ImportUtil
import de.sos.generator.TypeUtil
import de.sos.generator.TypeUtil.ProxyTypeDecl
import de.sos.mORA.Interface
import de.sos.mORA.PrimTypeDecl
import de.sos.mORA.TypeDecl
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import de.sos.mORA.Model

class CSharpTypeUtil extends ImportUtil {
	
	extension TypeUtil = new TypeUtil
	
	extension IQualifiedNameProvider = new QualifiedNameProvider()

	/////////////////////	Imports ////////////////////////////////////	
	def CharSequence getImportBlock(QualifiedName... toRemove)
	'''
		«FOR s : csfilteredImports(toRemove)»
			using «s.toString(".")»;
		«ENDFOR»
	'''
	
	def csfilteredImports(QualifiedName[] names) {
		val Set<QualifiedName> copy = new HashSet<QualifiedName>();
		for (imp : getAllImports()){
			if (imp !== null){
				val t = imp.skipLast(1);
				if (t !== null) 
					copy.add(t)					
			}
		}
		for (n : names){
			val t = n.skipLast(1);
			if (t !== null) 
				copy.remove(t)
		}
		return copy;		
	}
	
	
	
	def QualifiedName basePackage(Model m){
		if (m.options === null || m.options.csOptions === null)
			return QualifiedName::create("");
		return QualifiedName::create(m.options.csOptions.baseNamespace.split("\\."));
	}
	
	def QualifiedName fullyQualifiedName(TypeDecl decl){
		var qn = decl.model.basePackage;
		qn = qn.append(decl.model.name.toFirstUpper)
		qn = qn.append(decl.name)
		return qn;
	}
	def QualifiedName fullyQualifiedName(Interface decl){
		var qn = decl.model.basePackage;
		qn = qn.append(decl.model.name.toFirstUpper)
		qn = qn.append(decl.name)
		return qn;
	}
	
	
	
	def String getEncoder(TypeDecl type){
		if (type.singleType.isPrim){
			return "RPCTypes."+type.singleType.csTypeName.toUpperCase+"_ENCODER"
		}else if (type.singleType.enum){
			rememberImports(type.singleType.fullyQualifiedName);
			return type.singleType.csTypeName + "Util.ENCODER";
		}else if (type.singleType.proxy){
			rememberImports(type.iface.fullyQualifiedName);
			return type.iface.containerClassName + ".ENCODER";
		}
		rememberImports(type.singleType.fullyQualifiedName);
		return type.singleType.csTypeName + ".ENCODER";
	}
	
	
	/////////////////////	Types  ////////////////////////////////////	
	
	
	
	
	def String getSingleCsTypeName(TypeDecl td){
		if (td.isMany && td.singleType.isPrim){
			return td.singleType.primType.csTypeName
		}
		return td.singleType.csTypeName
	}
	
	
	def String csTypeName(PrimTypeDecl decl){
		switch(decl.name){
			case BOOL: return "bool"
			case BYTE: return "byte"
			case DOUBLE: return "double"
			case FLOAT: return "float"
			case INTEGER: return "int"
			case LONG: return "long"
			case SHORT: return "short"
			case STRING: return "string"
			case VOID: return "void"
		}
	}
	
	
	def String getCsTypeName(TypeDecl td){
		return getCsTypeName(td, false)
	}
	def String getCsTypeName(TypeDecl td, boolean asObject){
		if (td.isMany){
			rememberImports("System.Collections.Generic.List")
			val st = td.singleType;
			if (st.isPrim)
				return "List<" + st.primType.csTypeName + ">"
			return "List<"+st.csTypeName+">"
		}else if (td.prim){
			if (asObject)
				return td.primType.csTypeName
			else
				return td.primType.csTypeName
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
