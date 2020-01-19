package de.sos.generator.cpp;

import de.sos.generator.ImportUtil;
import de.sos.generator.TypeUtil;
import de.sos.mORA.Interface;
import de.sos.mORA.Model;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;
import java.io.File;
import java.util.ArrayList;
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CppTypeUtil extends ImportUtil {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private IQualifiedNameProvider qnp = new QualifiedNameProvider();
  
  public CharSequence getImportBlock(final QualifiedName... toRemove) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<QualifiedName> _filteredImports = this.filteredImports(toRemove);
      for(final QualifiedName s : _filteredImports) {
        _builder.append("#include<");
        String _string = s.toString();
        _builder.append(_string);
        _builder.append(">");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public QualifiedName basePackage(final Model m) {
    if (((m.getOptions() == null) || (m.getOptions().getJavaOptions() == null))) {
      return QualifiedName.create("");
    }
    return QualifiedName.create(m.getOptions().getJavaOptions().getBasePackage().split("\\."));
  }
  
  public QualifiedName fullyQualifiedName(final TypeDecl decl) {
    QualifiedName qn = this.basePackage(this._typeUtil.model(decl));
    qn = qn.append(this._typeUtil.model(decl).getName());
    qn = qn.append(this._typeUtil.getName(decl));
    return qn;
  }
  
  public QualifiedName fullyQualifiedName(final Interface decl) {
    QualifiedName qn = this.basePackage(this._typeUtil.model(decl));
    qn = qn.append(this._typeUtil.model(decl).getName());
    qn = qn.append(decl.getName());
    return qn;
  }
  
  public String getSingleCppTypeName(final TypeDecl td) {
    if ((this._typeUtil.isMany(td) && this._typeUtil.isPrim(this._typeUtil.getSingleType(td)))) {
      return this.cppTypeName(this._typeUtil.getPrimType(this._typeUtil.getSingleType(td)));
    }
    return this.getCppTypeName(this._typeUtil.getSingleType(td));
  }
  
  public String getStructName(final StructDecl s) {
    return StringExtensions.toFirstUpper(s.getName());
  }
  
  public String cppTypeName(final Interface decl) {
    String _firstUpper = StringExtensions.toFirstUpper(decl.getName());
    String _plus = ("I" + _firstUpper);
    return (_plus + "Ptr");
  }
  
  public String getIFaceName(final Interface decl) {
    String _firstUpper = StringExtensions.toFirstUpper(decl.getName());
    return ("I" + _firstUpper);
  }
  
  public String getProxyName(final Interface decl) {
    String _firstUpper = StringExtensions.toFirstUpper(decl.getName());
    return (_firstUpper + "Proxy");
  }
  
  public String getAdapterName(final Interface decl) {
    String _firstUpper = StringExtensions.toFirstUpper(decl.getName());
    return (_firstUpper + "Adapter");
  }
  
  public String cppTypeName(final PrimTypeDecl decl) {
    this.rememberImports("MoraPreReq.h");
    PrimTypeLiteral _name = decl.getName();
    if (_name != null) {
      switch (_name) {
        case BOOL:
          return "bool";
        case BYTE:
          return "::mora::int8";
        case DOUBLE:
          return "double";
        case FLOAT:
          return "float";
        case INTEGER:
          return "::mora::int32";
        case LONG:
          return "::mora::int64";
        case SHORT:
          return "::mora::int16";
        case STRING:
          return "std::string";
        case VOID:
          return "void";
        default:
          break;
      }
    }
    return null;
  }
  
  public String getCppTypeName(final TypeDecl td) {
    return this.getCppTypeName(td, false);
  }
  
  public String getCppTypeName(final TypeDecl td, final boolean asObject) {
    boolean _isMany = this._typeUtil.isMany(td);
    if (_isMany) {
      this.rememberImports("vector");
      final TypeDecl st = this._typeUtil.getSingleType(td);
      boolean _isPrim = this._typeUtil.isPrim(st);
      if (_isPrim) {
        String _cppTypeName = this.cppTypeName(this._typeUtil.getPrimType(st));
        String _plus = ("std::vector<" + _cppTypeName);
        return (_plus + ">");
      }
      boolean _isPointer = this._typeUtil.isPointer(st);
      if (_isPointer) {
        String _cppTypeName_1 = this.getCppTypeName(st);
        String _plus_1 = ("std::vector<" + _cppTypeName_1);
        return (_plus_1 + ">");
      }
      String _cppTypeName_2 = this.getCppTypeName(st);
      String _plus_2 = ("std::vector<" + _cppTypeName_2);
      return (_plus_2 + ">");
    } else {
      boolean _isPrim_1 = this._typeUtil.isPrim(td);
      if (_isPrim_1) {
        if (asObject) {
          return this.cppTypeName(this._typeUtil.getPrimType(td));
        } else {
          return this.cppTypeName(this._typeUtil.getPrimType(td));
        }
      } else {
        boolean _isProxy = this._typeUtil.isProxy(td);
        if (_isProxy) {
          return this.cppTypeName(this.getIface(td));
        } else {
          this.rememberImports(this.getHeaderFileName(td));
          boolean _isStruct = this._typeUtil.isStruct(td);
          if (_isStruct) {
            String _firstUpper = StringExtensions.toFirstUpper(this.fullyQualifiedName(td).getLastSegment());
            return (_firstUpper + "*");
          }
          return StringExtensions.toFirstUpper(this.fullyQualifiedName(td).getLastSegment());
        }
      }
    }
  }
  
  public String getHeaderFileName(final TypeDecl decl) {
    return this.getTypesHeaderFile(this._typeUtil.model(decl));
  }
  
  public String getTypesHeaderFile(final Model model) {
    String _fileString = model.eResource().getURI().toFileString();
    final File f = new File(_fileString);
    return f.getName().replace(".mora", ".h");
  }
  
  public String getTypesSourceFile(final Model model) {
    String _fileString = model.eResource().getURI().toFileString();
    final File f = new File(_fileString);
    return f.getName().replace(".mora", ".cpp");
  }
  
  public Interface getIface(final TypeDecl decl) {
    return ((TypeUtil.ProxyTypeDecl) decl).iface;
  }
}
