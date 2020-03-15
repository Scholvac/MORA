package de.sos.generator.cpp

import de.sos.mORA.CppOptions
import org.eclipse.xtext.naming.QualifiedName

class CppUtils {
	
	
	
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
}