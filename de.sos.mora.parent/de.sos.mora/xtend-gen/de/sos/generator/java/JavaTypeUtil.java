package de.sos.generator.java;

import de.sos.generator.ImportUtil;
import de.sos.generator.TypeUtil;
import de.sos.mORA.Interface;
import de.sos.mORA.Model;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.TypeDecl;
import java.util.ArrayList;
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaTypeUtil extends ImportUtil {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private IQualifiedNameProvider qnp = new QualifiedNameProvider();
  
  public CharSequence getImportBlock(final QualifiedName... toRemove) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<QualifiedName> _filteredImports = this.filteredImports(toRemove);
      for(final QualifiedName s : _filteredImports) {
        _builder.append("import ");
        String _string = s.toString(".");
        _builder.append(_string);
        _builder.append(";");
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
  
  public String getSingleJavaTypeName(final TypeDecl td) {
    if ((this._typeUtil.isMany(td) && this._typeUtil.isPrim(this._typeUtil.getSingleType(td)))) {
      return this.getJavaObjectType(this._typeUtil.getPrimType(this._typeUtil.getSingleType(td)));
    }
    return this.getJavaTypeName(this._typeUtil.getSingleType(td));
  }
  
  public String getStreamPostFix(final PrimTypeDecl decl) {
    return this.getJavaObjectType(decl);
  }
  
  public String getJavaObjectType(final PrimTypeDecl decl) {
    PrimTypeLiteral _name = decl.getName();
    if (_name != null) {
      switch (_name) {
        case BOOL:
          return "Boolean";
        case BYTE:
          return "Byte";
        case DOUBLE:
          return "Double";
        case FLOAT:
          return "Float";
        case INTEGER:
          return "Integer";
        case LONG:
          return "Long";
        case SHORT:
          return "Short";
        case STRING:
          return "String";
        case VOID:
          return "Void";
        default:
          break;
      }
    }
    return null;
  }
  
  public String getJavaTypeName(final PrimTypeDecl decl) {
    PrimTypeLiteral _name = decl.getName();
    if (_name != null) {
      switch (_name) {
        case BOOL:
          return "boolean";
        case BYTE:
          return "byte";
        case DOUBLE:
          return "double";
        case FLOAT:
          return "float";
        case INTEGER:
          return "int";
        case LONG:
          return "long";
        case SHORT:
          return "short";
        case STRING:
          return "String";
        case VOID:
          return "void";
        default:
          break;
      }
    }
    return null;
  }
  
  public String getJavaListTypeName(final PrimTypeDecl decl) {
    PrimTypeLiteral _name = decl.getName();
    if (_name != null) {
      switch (_name) {
        case BOOL:
          this.rememberImports("de.sos.mora.list.BoolArrayList");
          return "BoolArrayList";
        case BYTE:
          this.rememberImports("de.sos.mora.list.ByteArrayList");
          return "ByteArrayList";
        case DOUBLE:
          this.rememberImports("de.sos.mora.list.DoubleArrayList");
          return "DoubleArrayList";
        case FLOAT:
          this.rememberImports("de.sos.mora.list.FloatArrayList");
          return "FloatArrayList";
        case INTEGER:
          this.rememberImports("de.sos.mora.list.IntArrayList");
          return "IntArrayList";
        case LONG:
          this.rememberImports("de.sos.mora.list.LongArrayList");
          return "LongArrayList";
        case SHORT:
          this.rememberImports("de.sos.mora.list.ShortArrayList");
          return "ShortArrayList";
        case STRING:
          this.rememberImports("de.sos.mora.list.StringArrayList");
          return "StringArrayList";
        case VOID:
          return "void";
        default:
          break;
      }
    }
    return null;
  }
  
  public String getJavaTypeName(final TypeDecl td) {
    return this.getJavaTypeName(td, false);
  }
  
  public String getJavaTypeName(final TypeDecl td, final boolean asObject) {
    boolean _isMany = this._typeUtil.isMany(td);
    if (_isMany) {
      this.rememberImports("java.util.List");
      final TypeDecl st = this._typeUtil.getSingleType(td);
      boolean _isPrim = this._typeUtil.isPrim(st);
      if (_isPrim) {
        return this.getJavaListTypeName(this._typeUtil.getPrimType(st));
      }
      String _javaTypeName = this.getJavaTypeName(st);
      String _plus = ("List<" + _javaTypeName);
      return (_plus + ">");
    } else {
      boolean _isPrim_1 = this._typeUtil.isPrim(td);
      if (_isPrim_1) {
        if (asObject) {
          return this.getJavaObjectType(this._typeUtil.getPrimType(td));
        } else {
          return this.getJavaTypeName(this._typeUtil.getPrimType(td));
        }
      } else {
        boolean _isProxy = this._typeUtil.isProxy(td);
        if (_isProxy) {
          return this.getIFaceName(this.getIface(td));
        } else {
          this.rememberImports(this.fullyQualifiedName(td));
          return StringExtensions.toFirstUpper(this.fullyQualifiedName(td).getLastSegment());
        }
      }
    }
  }
  
  public Interface getIface(final TypeDecl decl) {
    return ((TypeUtil.ProxyTypeDecl) decl).iface;
  }
  
  public String getIFaceName(final Interface iface) {
    this.rememberImports(this.getQContainerName(iface));
    String _containerClassName = this.getContainerClassName(iface);
    return (_containerClassName + ".IFace");
  }
  
  public String getProxyName(final Interface iface) {
    this.rememberImports(this.getQContainerName(iface));
    String _containerClassName = this.getContainerClassName(iface);
    return (_containerClassName + ".Proxy");
  }
  
  public String getAdapterName(final Interface iface) {
    this.rememberImports(this.getQContainerName(iface));
    String _containerClassName = this.getContainerClassName(iface);
    return (_containerClassName + ".Adapter");
  }
  
  public QualifiedName getQContainerName(final Interface iface) {
    final QualifiedName qn = this.fullyQualifiedName(iface).skipLast(1).append(this.getContainerClassName(iface));
    return qn;
  }
  
  public String getContainerClassName(final Interface iface) {
    String _firstUpper = StringExtensions.toFirstUpper(iface.getName());
    return (_firstUpper + "RPC");
  }
}
