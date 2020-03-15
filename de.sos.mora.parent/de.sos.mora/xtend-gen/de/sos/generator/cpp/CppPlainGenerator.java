package de.sos.generator.cpp;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.cpp.CppTypeUtil;
import de.sos.generator.cpp.CppUtils;
import de.sos.mORA.AbstractType;
import de.sos.mORA.Annotation;
import de.sos.mORA.Interface;
import de.sos.mORA.Member;
import de.sos.mORA.Method;
import de.sos.mORA.Model;
import de.sos.mORA.Parameter;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CppPlainGenerator extends AbstractGenerator {
  public static class Options {
  }
  
  @Extension
  private CppUtils _cppUtils = new CppUtils();
  
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private CppTypeUtil _cppTypeUtil = new CppTypeUtil();
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    final Model m = this.getModel(resource);
    this.generateHeader(m, fsa);
  }
  
  public Model getModel(final Resource resource) {
    Iterable<Model> _iterable = IteratorExtensions.<Model>toIterable(Iterators.<Model>filter(resource.getAllContents(), Model.class));
    for (final Model m : _iterable) {
      return m;
    }
    return null;
  }
  
  public void generateHeader(final Model m, final IFileSystemAccess2 fsa) {
    Iterable<Interface> _iterable = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
    for (final Interface i : _iterable) {
      {
        this._cppTypeUtil.clearImports();
        final CharSequence content = this.generateInterfaceHeader(i);
        String _PlainName = this.PlainName(i);
        String _plus = ("include/" + _PlainName);
        final String headerFileName = (_plus + ".h");
        String _upperCase = this.PlainName(i).toUpperCase();
        String _plus_1 = ("#ifndef " + _upperCase);
        String _plus_2 = (_plus_1 + "_H_\n#define ");
        String _upperCase_1 = this.PlainName(i).toUpperCase();
        String _plus_3 = (_plus_2 + _upperCase_1);
        String allContent = (_plus_3 + "_H_\n\n\n");
        String _firstUpper = StringExtensions.toFirstUpper(m.getName());
        String _plus_4 = (_firstUpper + "Types.h");
        CharSequence _importBlock = this._cppTypeUtil.getImportBlock(QualifiedName.create(_plus_4));
        String _plus_5 = (allContent + _importBlock);
        String _plus_6 = (_plus_5 + "\n");
        allContent = _plus_6;
        allContent = (allContent + content);
        allContent = (allContent + "#endif //");
        String _upperCase_2 = m.getName().toUpperCase();
        /* (_upperCase_2 + "_TYPES_H_"); */
        fsa.generateFile(headerFileName, allContent);
      }
    }
    Iterable<StructDecl> _iterable_1 = IteratorExtensions.<StructDecl>toIterable(Iterators.<StructDecl>filter(m.eAllContents(), StructDecl.class));
    for (final StructDecl s : _iterable_1) {
      {
        this._cppTypeUtil.clearImports();
        final CharSequence content = this.generateStructHeader(s);
        String _PlainName = this.PlainName(s);
        String _plus = ("include/" + _PlainName);
        final String headerFileName = (_plus + ".h");
        String _upperCase = this.PlainName(s).toUpperCase();
        String _plus_1 = ("#ifndef " + _upperCase);
        String _plus_2 = (_plus_1 + "_H_\n#define ");
        String _upperCase_1 = this.PlainName(s).toUpperCase();
        String _plus_3 = (_plus_2 + _upperCase_1);
        String allContent = (_plus_3 + "_H_\n\n\n");
        String _firstUpper = StringExtensions.toFirstUpper(m.getName());
        String _plus_4 = (_firstUpper + "Types.h");
        CharSequence _importBlock = this._cppTypeUtil.getImportBlock(QualifiedName.create(_plus_4));
        String _plus_5 = (allContent + _importBlock);
        String _plus_6 = (_plus_5 + "\n");
        allContent = _plus_6;
        allContent = (allContent + content);
        allContent = (allContent + "#endif //");
        String _upperCase_2 = m.getName().toUpperCase();
        /* (_upperCase_2 + "_TYPES_H_"); */
        fsa.generateFile(headerFileName, allContent);
      }
    }
  }
  
  public CharSequence generateInterfaceHeader(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasNamesapce = this._cppUtils.hasNamesapce(this._typeUtil.model(iface).getOptions().getCppOptions());
      if (_hasNamesapce) {
        String _beginNamespace = this._cppUtils.beginNamespace(this._typeUtil.model(iface).getOptions().getCppOptions());
        _builder.append(_beginNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasDoc = this.hasDoc(iface);
      if (_hasDoc) {
        String _printDoc = this.printDoc(iface);
        _builder.append(_printDoc);
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("class ");
    String _API_EXPORT = this.API_EXPORT(this._typeUtil.model(iface));
    _builder.append(_API_EXPORT);
    _builder.append(" ");
    String _PlainName = this.PlainName(iface);
    _builder.append(_PlainName);
    _builder.append(" ");
    String _generalisations = this.generalisations(iface);
    _builder.append(_generalisations);
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    {
      boolean _hasAnnotation = this.hasAnnotation(iface, "Data");
      if (_hasAnnotation) {
        _builder.append("private:");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("class ");
        String _PlainName_1 = this.PlainName(iface);
        _builder.append(_PlainName_1, "\t");
        _builder.append("Data;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _PlainName_2 = this.PlainName(iface);
        _builder.append(_PlainName_2, "\t");
        _builder.append("Data* data;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("public:");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_3 = this.PlainName(iface);
    _builder.append(_PlainName_3, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("GENERATED_LINE virtual ~");
    String _PlainName_4 = this.PlainName(iface);
    _builder.append(_PlainName_4, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_5 = this.PlainName(iface);
    _builder.append(_PlainName_5, "\t");
    _builder.append("(const ");
    String _PlainName_6 = this.PlainName(iface);
    _builder.append(_PlainName_6, "\t");
    _builder.append("& cp);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_7 = this.PlainName(iface);
    _builder.append(_PlainName_7, "\t");
    _builder.append("(const ");
    String _PlainName_8 = this.PlainName(iface);
    _builder.append(_PlainName_8, "\t");
    _builder.append("&& mv);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_9 = this.PlainName(iface);
    _builder.append(_PlainName_9, "\t");
    _builder.append("& operator=(const ");
    String _PlainName_10 = this.PlainName(iface);
    _builder.append(_PlainName_10, "\t");
    _builder.append("& cp);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_11 = this.PlainName(iface);
    _builder.append(_PlainName_11, "\t");
    _builder.append("& operator=(const ");
    String _PlainName_12 = this.PlainName(iface);
    _builder.append(_PlainName_12, "\t");
    _builder.append("&& mv);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_13 = this.PlainName(iface);
    _builder.append(_PlainName_13, "\t");
    _builder.append("& operator==(const ");
    String _PlainName_14 = this.PlainName(iface);
    _builder.append(_PlainName_14, "\t");
    _builder.append("& cp) const;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t");
        {
          boolean _hasDoc_1 = this.hasDoc(m);
          if (_hasDoc_1) {
            String _printDoc_1 = this.printDoc(m);
            _builder.append(_printDoc_1, "\t");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("GENERATED_LINE ");
        String _cppTypeName = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName, "\t");
        _builder.append(" ");
        String _name = m.getName();
        _builder.append(_name, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters = m.getParameters();
          boolean _hasElements = false;
          for(final Parameter p : _parameters) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _cppTypeName_1 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p));
            _builder.append(_cppTypeName_1, "\t");
            _builder.append(" ");
            String _name_1 = p.getName();
            _builder.append(_name_1, "\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasNamesapce_1 = this._cppUtils.hasNamesapce(this._typeUtil.model(iface).getOptions().getCppOptions());
      if (_hasNamesapce_1) {
        String _endNamespace = this._cppUtils.endNamespace(this._typeUtil.model(iface).getOptions().getCppOptions());
        _builder.append(_endNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence predefine(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isEmpty = this.getUsedTypes(iface).isEmpty();
      boolean _equals = (_isEmpty == false);
      if (_equals) {
        _builder.append("////////////////\tPredefinitions ///////////////");
        _builder.newLine();
        _builder.newLine();
        _builder.append("///////////////\t\tType definition //////////////");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public List<AbstractType> getUsedTypes(final Interface iface) {
    final HashSet<AbstractType> set = new HashSet<AbstractType>();
    set.addAll(iface.getParents());
    EList<Method> _methods = iface.getMethods();
    for (final Method m : _methods) {
      {
        boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getType(m));
        if (_isProxy) {
          set.add(this._typeUtil.getProxyType(this._typeUtil.getType(m)));
        } else {
          TypeDecl _singleType = this._typeUtil.getSingleType(this._typeUtil.getType(m));
          if ((_singleType instanceof AbstractType)) {
            TypeDecl _singleType_1 = this._typeUtil.getSingleType(this._typeUtil.getType(m));
            set.add(((AbstractType) _singleType_1));
          }
        }
        EList<Parameter> _parameters = m.getParameters();
        for (final Parameter p : _parameters) {
          boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getType(p));
          if (_isProxy_1) {
            set.add(this._typeUtil.getProxyType(this._typeUtil.getType(p)));
          } else {
            TypeDecl _singleType_2 = this._typeUtil.getSingleType(this._typeUtil.getType(p));
            if ((_singleType_2 instanceof AbstractType)) {
              TypeDecl _singleType_3 = this._typeUtil.getSingleType(this._typeUtil.getType(p));
              set.add(((AbstractType) _singleType_3));
            }
          }
        }
      }
    }
    boolean _isEmpty = set.isEmpty();
    boolean _equals = (_isEmpty == false);
    if (_equals) {
      final ArrayList<AbstractType> l = new ArrayList<AbstractType>(set);
      return l;
    }
    return null;
  }
  
  public String generalisations(final Interface iface) {
    boolean _isEmpty = iface.getParents().isEmpty();
    if (_isEmpty) {
      return "";
    }
    String out = ": ";
    EList<Interface> _parents = iface.getParents();
    for (final Interface p : _parents) {
      {
        this._cppTypeUtil.rememberImports(p.getName());
        out = out;
        String _PlainName = this.PlainName(p);
        String _plus = ("public " + _PlainName);
        /* (_plus + ", "); */
      }
    }
    int _length = out.length();
    int _minus = (_length - 2);
    out = out.substring(0, _minus);
    return out;
  }
  
  public CharSequence generateStructHeader(final StructDecl st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasDoc = this.hasDoc(st);
      if (_hasDoc) {
        String _printDoc = this.printDoc(st);
        _builder.append(_printDoc);
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("class ");
    String _API_EXPORT = this.API_EXPORT(this._typeUtil.model(st));
    _builder.append(_API_EXPORT);
    _builder.append(" ");
    String _PlainName = this.PlainName(st);
    _builder.append(_PlainName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ");
    String _PlainName_1 = this.PlainName(st);
    _builder.append(_PlainName_1, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("GENERATED_LINE ~");
    String _PlainName_2 = this.PlainName(st);
    _builder.append(_PlainName_2, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Member> _member = st.getMember();
      for(final Member m : _member) {
        _builder.append("\t");
        {
          boolean _hasDoc_1 = this.hasDoc(m);
          if (_hasDoc_1) {
            String _printDoc_1 = this.printDoc(m);
            _builder.append(_printDoc_1, "\t");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("GENERATED_LINE ");
        String _cppTypeName = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName, "\t");
        _builder.append(" ");
        String _memberName = this.getMemberName(m);
        _builder.append(_memberName, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("};");
    _builder.newLine();
    return _builder;
  }
  
  public boolean hasAnnotation(final Interface iface, final String name) {
    EList<Annotation> _anno = iface.getAnno();
    for (final Annotation a : _anno) {
      String _name = a.getName();
      boolean _equals = Objects.equal(_name, name);
      if (_equals) {
        return true;
      }
    }
    return false;
  }
  
  public String printDoc(final Member s) {
    return this.formatDoc(s.getDoc());
  }
  
  public boolean hasDoc(final Member s) {
    boolean _isNullOrEmpty = Strings.isNullOrEmpty(s.getDoc());
    return (!_isNullOrEmpty);
  }
  
  public String printDoc(final StructDecl s) {
    return this.formatDoc(s.getDoc());
  }
  
  public boolean hasDoc(final StructDecl s) {
    boolean _isNullOrEmpty = Strings.isNullOrEmpty(s.getDoc());
    return (!_isNullOrEmpty);
  }
  
  public String printDoc(final Method method) {
    return this.formatDoc(method.getDoc());
  }
  
  public boolean hasDoc(final Method method) {
    boolean _isNullOrEmpty = Strings.isNullOrEmpty(method.getDoc());
    return (!_isNullOrEmpty);
  }
  
  public String printDoc(final Interface iface) {
    return this.formatDoc(iface.getDoc());
  }
  
  public boolean hasDoc(final Interface iface) {
    boolean _isNullOrEmpty = Strings.isNullOrEmpty(iface.getDoc());
    return (!_isNullOrEmpty);
  }
  
  public String formatDoc(final String doc) {
    return doc;
  }
  
  public String API_EXPORT(final Model model) {
    String _upperCase = model.getName().toUpperCase();
    return (_upperCase + "_API");
  }
  
  public String PlainName(final Interface i) {
    return StringExtensions.toFirstUpper(i.getName());
  }
  
  public String PlainName(final StructDecl i) {
    return StringExtensions.toFirstUpper(i.getName());
  }
  
  public void generateSource(final Model m, final IFileSystemAccess2 fsa) {
    this._cppTypeUtil.clearImports();
    this._cppTypeUtil.rememberImports("Streams.h");
    this._cppTypeUtil.rememberImports("loguru.hpp");
    this._cppTypeUtil.rememberImports("Communicator.h");
    this._cppTypeUtil.rememberImports("RemoteMethodCall.h");
    this._cppTypeUtil.rememberImports("future");
    this._cppTypeUtil.rememberImports(this._cppTypeUtil.getTypesHeaderFile(m));
  }
  
  public String getMemberName(final Member member) {
    return member.getName();
  }
}
