package de.sos.generator

import de.sos.mORA.EnumDecl
import de.sos.mORA.Interface
import de.sos.mORA.ListTypeDecl
import de.sos.mORA.MORAFactory
import de.sos.mORA.Member
import de.sos.mORA.Method
import de.sos.mORA.Model
import de.sos.mORA.Parameter
import de.sos.mORA.PrimTypeDecl
import de.sos.mORA.PrimTypeLiteral
import de.sos.mORA.StructDecl
import de.sos.mORA.TypeDecl
import de.sos.mORA.impl.TypeDeclImpl
import java.util.HashMap
import java.util.Map

class TypeUtil {

	Map<PrimTypeLiteral, PrimTypeDecl> 	mPrimTypes = new HashMap
	Map<Interface, ProxyTypeDecl> 		mIFaceTypes = new HashMap
	
	static class ProxyTypeDecl extends TypeDeclImpl {
		public Interface		iface;
		new (Interface i) { iface = i; }
		
	}

	def TypeDecl getType(Member member){
		if (member.complexType !== null)
			return member.complexType;
		return _getPrimType(member.primType)
	}	
	
	def TypeDecl getType(Parameter param){
		if (param.complexType !== null)
			return param.complexType;
		if (param.proxyType !== null)
			return _getIFaceType(param.proxyType)
		return _getPrimType(param.primType)
	}
	
	def TypeDecl getType(Method meth){
		if (meth.returnProxyType !== null)
			return _getIFaceType(meth.returnProxyType)
		if (meth.complexType !== null)
			return meth.complexType
		return _getPrimType(meth.primType)	
	}
	
	
	def TypeDecl getSingleType(TypeDecl td){
		if(td instanceof ListTypeDecl){
			val lt = (td as ListTypeDecl);
			if (lt.valueType !== null)
				return lt.valueType;
			return _getPrimType(lt.primType)  
		}
		return td;
	}
	
	def PrimTypeDecl getPrimType(TypeDecl decl){
		return decl as PrimTypeDecl
	}
	def Interface getProxyType(TypeDecl decl){
		return (decl as ProxyTypeDecl).iface;
	}
	def StructDecl getStructType(TypeDecl decl){
		return (decl as StructDecl)
	}
	def EnumDecl getEnumType(TypeDecl decl){
		return decl as EnumDecl
	}
	
	def String getName(TypeDecl decl){
		if(decl.prim)
			return decl.primType.name.name();
		if (decl.proxy)
			return decl.proxyType.name
		if (decl.struct)
			return decl.structType.name
		if (decl.enum)
			return decl.enumType.name
		if (decl.many)
			return (decl as ListTypeDecl).name
	}
	

	def boolean isMany(TypeDecl td){ return td instanceof ListTypeDecl }
	def boolean isStruct(TypeDecl td) { return td instanceof StructDecl }
	def boolean isPointer(TypeDecl td) { return td.isStruct }
	def boolean isProxy(TypeDecl td) { return td instanceof ProxyTypeDecl }
	def boolean isPrim(TypeDecl td) { return td instanceof PrimTypeDecl }
	def boolean isEnum(TypeDecl td) { return td instanceof EnumDecl }
	def boolean isVoid(TypeDecl td){ 
		if (td.prim == false)
			return false;
		return td.primType.name == PrimTypeLiteral.VOID;
	}
	
	
	
	
	def Model model(TypeDecl t){
		var c = t.eContainer;
		if (c instanceof Model)
			return c;
		while(c !== null){
			c = c.eContainer;
			if (c instanceof Model)
				return c;
		}
		return null;
	}
	
	def Model model(Interface t){
		var c = t.eContainer;
		if (c instanceof Model)
			return c;
		while(c !== null){
			c = c.eContainer;
			if (c instanceof Model)
				return c;
		}
		return null;
	}
	
	
	
	
	def PrimTypeDecl _getPrimType(PrimTypeLiteral l){
		var pt = mPrimTypes.get(l);
		if (pt === null){
			pt = MORAFactory.eINSTANCE.createPrimTypeDecl
			pt.name = l;
			mPrimTypes.put(l, pt);
		}
		return pt;
	}
	def ProxyTypeDecl _getIFaceType(Interface l){
		var pt = mIFaceTypes.get(l);
		if (pt === null){
			pt = new ProxyTypeDecl(l)
			mIFaceTypes.put(l, pt);
		}
		return pt;
	}
	
	
	
	
	
	
	
	
	
	def String getSignature(Method m){
		var n = m.name;
		if (m.parameters.empty == false){
			n = n + "_";
			for (p : m.parameters) {
				n = n + p.type.name+ "_" 
			}
		}
		return n.toFirstUpper;
	}
	

}
