package de.sos.generator.cpp

import com.google.common.base.Strings
import de.sos.generator.TypeUtil
import de.sos.mORA.AbstractType
import de.sos.mORA.Interface
import de.sos.mORA.Member
import de.sos.mORA.Method
import de.sos.mORA.Model
import de.sos.mORA.StructDecl
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.naming.QualifiedName

class CppPlainGenerator extends AbstractGenerator {
	

	extension CppUtils 		= new CppUtils
	extension TypeUtil 		= new TypeUtil
	extension CppTypeUtil	= new CppTypeUtil

	static class Options {
		
	}
	
	
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
		«IF iface.model.options.cppOptions.hasNamesapce»
			«iface.model.options.cppOptions.beginNamespace»
		«ENDIF»
		
«««		«iface.predefines»
		
		«IF iface.hasDoc»«iface.printDoc»«ENDIF»
		class «iface.model.API_EXPORT» «iface.PlainName» «iface.generalisations»{
		«IF iface.hasAnnotation("Data")»
			private:
				class «iface.PlainName»Data;
				«iface.PlainName»Data* data;
		«ENDIF»
		public:
			GENERATED_LINE «iface.PlainName»();
			GENERATED_LINE virtual ~«iface.PlainName»();
			
			GENERATED_LINE «iface.PlainName»(const «iface.PlainName»& cp);
			GENERATED_LINE «iface.PlainName»(const «iface.PlainName»&& mv);
			
			GENERATED_LINE «iface.PlainName»& operator=(const «iface.PlainName»& cp);
			GENERATED_LINE «iface.PlainName»& operator=(const «iface.PlainName»&& mv);
			
			GENERATED_LINE «iface.PlainName»& operator==(const «iface.PlainName»& cp) const;
			
			«FOR m : iface.methods»
				«IF m.hasDoc»«m.printDoc»«ENDIF»
				GENERATED_LINE «m.type.cppTypeName» «m.name»(«FOR p: m.parameters SEPARATOR ', '»«p.type.cppTypeName» «p.name»«ENDFOR»);
			«ENDFOR»
		};
		
		«IF iface.model.options.cppOptions.hasNamesapce»
			«iface.model.options.cppOptions.endNamespace»
		«ENDIF»
	'''
	
	def predefine(Interface iface)
	'''
		«IF iface.usedTypes.empty == false»
		////////////////	Predefinitions ///////////////
		
		///////////////		Type definition //////////////
		«ENDIF»
	'''
	
	def List<AbstractType> getUsedTypes(Interface iface){
		val set = new HashSet<AbstractType>();
		set.addAll(iface.parents);
		for (m : iface.methods){
			if (m.type.proxy)
				set.add(m.type.proxyType)
			else if (m.type.singleType instanceof AbstractType)
				set.add(m.type.singleType as AbstractType)
				
			for (p : m.parameters){
				if (p.type.proxy)
					set.add(p.type.proxyType)
				else if (p.type.singleType instanceof AbstractType)
					set.add(p.type.singleType as AbstractType)
			}
		}
		if (set.empty == false){
			val l = new ArrayList<AbstractType>(set);
//			Collections.sort(l, new Comparator<AbstractType>{				
//				override compare(AbstractType o1, AbstractType o2) {
//					return o1.
//				}		 		
//			});
			return l;
		}
		return null;
	}
	
	def String generalisations(Interface iface){
		if (iface.parents.empty)
			return "";
		var out = ": ";
		for (p : iface.parents){
			rememberImports(p.name)
			out = out "public " + p.PlainName + ", "; 
		}
		out = out.substring(0, out.length-2);
		return out;
	}
	

	
	def generateStructHeader(StructDecl st)
	'''
		«IF st.hasDoc»«st.printDoc»«ENDIF»
		class «st.model.API_EXPORT» «st.PlainName» {
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
	
	def String API_EXPORT(Model model){ model.name.toUpperCase + "_API" }
	
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
		
//		val content = m.generateSourceContent
//		val sourceFileName = "src/"+m.name.toFirstUpper+"Types.cpp";
//		
//		var allContent = getImportBlock(QualifiedName::create(m.name.toFirstUpper+"Types.h")) + "\n";
//		allContent = allContent + content;
//		
//		fsa.generateFile(sourceFileName, allContent);
	}
		
	def String getMemberName(Member member){
		return member.name
	}
	
	
}
