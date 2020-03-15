package de.sos.generator.cpp;

import de.sos.mORA.CppOptions;
import java.util.List;
import org.eclipse.xtext.naming.QualifiedName;

@SuppressWarnings("all")
public class CppUtils {
  public String beginNamespace(final CppOptions options) {
    final QualifiedName qn = QualifiedName.create(options.getBaseNamespace().split("::"));
    String out = "";
    List<String> _segments = qn.getSegments();
    for (final String s : _segments) {
      out = (((out + "namespace ") + s) + "{ ");
    }
    return out;
  }
  
  public String endNamespace(final CppOptions options) {
    final QualifiedName qn = QualifiedName.create(options.getBaseNamespace().split("::"));
    String out = "";
    List<String> _segments = qn.getSegments();
    for (final String s : _segments) {
      out = (((out + "} /*") + s) + "*/ ");
    }
    return out;
  }
  
  public boolean hasNamesapce(final CppOptions options) {
    return (((options != null) && (options.getBaseNamespace() != null)) && (options.getBaseNamespace().isEmpty() == false));
  }
}
