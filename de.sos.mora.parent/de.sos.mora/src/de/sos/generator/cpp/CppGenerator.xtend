package de.sos.generator.cpp

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class CppGenerator extends AbstractGenerator {

	static class JavaOptions {
		public val String basePackage;
		
		new (String bp){
			basePackage = bp;
		}
	}
	

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		val cppMoraGen = new CppMoraGenerator()
		cppMoraGen.doGenerate(resource, fsa, context);
//		val javaIFaceGen = new JavaInterfaceGenerator()
//		javaIFaceGen.doGenerate(resource, fsa, context)
	}
}
