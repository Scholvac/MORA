package de.sos.generator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;

@SuppressWarnings("all")
public class ImportUtil {
  private HashSet<QualifiedName> mImports = new HashSet<QualifiedName>();
  
  public boolean rememberImports(final QualifiedName qn) {
    return this.mImports.add(qn);
  }
  
  public boolean rememberImports(final String javaStr) {
    return this.rememberImports(QualifiedName.create(javaStr.split("\\.")));
  }
  
  public void clearImports() {
    this.mImports.clear();
  }
  
  public ArrayList<QualifiedName> filteredImports(final QualifiedName[] names) {
    final ArrayList<QualifiedName> copy = new ArrayList<QualifiedName>();
    copy.addAll(this.mImports);
    CollectionExtensions.<QualifiedName>removeAll(copy, names);
    copy.sort(new Comparator<QualifiedName>() {
      @Override
      public int compare(final QualifiedName o1, final QualifiedName o2) {
        return o1.toString().compareTo(o2.toString());
      }
    });
    return copy;
  }
  
  public HashSet<QualifiedName> getAllImports() {
    return this.mImports;
  }
  
  public String importClass(final String fullQualified, final String name) {
    QualifiedName qn = QualifiedName.create(fullQualified.split("\\."));
    this.rememberImports(qn);
    return name;
  }
}
