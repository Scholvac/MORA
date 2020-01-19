/**
 * generated by Xtext 2.19.0
 */
package de.sos.generator.java;

import de.sos.generator.java.JavaInterfaceGenerator;
import de.sos.generator.java.JavaStructGenerator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class JavaGenerator extends AbstractGenerator {
  public static class JavaOptions {
    public final String basePackage;
    
    public JavaOptions(final String bp) {
      this.basePackage = bp;
    }
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    final JavaStructGenerator javaStructGen = new JavaStructGenerator();
    javaStructGen.doGenerate(resource, fsa, context);
    final JavaInterfaceGenerator javaIFaceGen = new JavaInterfaceGenerator();
    javaIFaceGen.doGenerate(resource, fsa, context);
  }
}