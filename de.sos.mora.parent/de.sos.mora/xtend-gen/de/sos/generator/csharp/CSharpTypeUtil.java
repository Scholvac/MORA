package de.sos.generator.csharp;

import de.sos.generator.ImportUtil;
import de.sos.generator.TypeUtil;
import de.sos.mORA.Interface;
import de.sos.mORA.Model;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.TypeDecl;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CSharpTypeUtil extends ImportUtil {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider = new QualifiedNameProvider();
  
  public CharSequence getImportBlock(final QualifiedName... toRemove) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Set<QualifiedName> _csfilteredImports = this.csfilteredImports(toRemove);
      for(final QualifiedName s : _csfilteredImports) {
        _builder.append("using ");
        String _string = s.toString(".");
        _builder.append(_string);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public Set<QualifiedName> csfilteredImports(final QualifiedName[] names) {
    final Set<QualifiedName> copy = new HashSet<QualifiedName>();
    HashSet<QualifiedName> _allImports = this.getAllImports();
    for (final QualifiedName imp : _allImports) {
      if ((imp != null)) {
        final QualifiedName t = imp.skipLast(1);
        if ((t != null)) {
          copy.add(t);
        }
      }
    }
    for (final QualifiedName n : names) {
      {
        final QualifiedName t_1 = n.skipLast(1);
        if ((t_1 != null)) {
          copy.remove(t_1);
        }
      }
    }
    return copy;
  }
  
  public QualifiedName basePackage(final Model m) {
    if (((m.getOptions() == null) || (m.getOptions().getCsOptions() == null))) {
      return QualifiedName.create("");
    }
    return QualifiedName.create(m.getOptions().getCsOptions().getBaseNamespace().split("\\."));
  }
  
  public QualifiedName fullyQualifiedName(final TypeDecl decl) {
    QualifiedName qn = this.basePackage(this._typeUtil.model(decl));
    qn = qn.append(StringExtensions.toFirstUpper(this._typeUtil.model(decl).getName()));
    qn = qn.append(this._typeUtil.getName(decl));
    return qn;
  }
  
  public QualifiedName fullyQualifiedName(final Interface decl) {
    QualifiedName qn = this.basePackage(this._typeUtil.model(decl));
    qn = qn.append(StringExtensions.toFirstUpper(this._typeUtil.model(decl).getName()));
    qn = qn.append(decl.getName());
    return qn;
  }
  
  public String getEncoder(final TypeDecl type) {
    boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(type));
    if (_isPrim) {
      String _upperCase = this.getCsTypeName(this._typeUtil.getSingleType(type)).toUpperCase();
      String _plus = ("RPCTypes." + _upperCase);
      return (_plus + "_ENCODER");
    } else {
      boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(type));
      if (_isEnum) {
        this.rememberImports(this.fullyQualifiedName(this._typeUtil.getSingleType(type)));
        String _csTypeName = this.getCsTypeName(this._typeUtil.getSingleType(type));
        return (_csTypeName + "Util.ENCODER");
      } else {
        boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(type));
        if (_isProxy) {
          this.rememberImports(this.fullyQualifiedName(this.getIface(type)));
          String _containerClassName = this.getContainerClassName(this.getIface(type));
          return (_containerClassName + ".ENCODER");
        }
      }
    }
    this.rememberImports(this.fullyQualifiedName(this._typeUtil.getSingleType(type)));
    String _csTypeName_1 = this.getCsTypeName(this._typeUtil.getSingleType(type));
    return (_csTypeName_1 + ".ENCODER");
  }
  
  public String getSingleCsTypeName(final TypeDecl td) {
    if ((this._typeUtil.isMany(td) && this._typeUtil.isPrim(this._typeUtil.getSingleType(td)))) {
      return this.csTypeName(this._typeUtil.getPrimType(this._typeUtil.getSingleType(td)));
    }
    return this.getCsTypeName(this._typeUtil.getSingleType(td));
  }
  
  public String csTypeName(final PrimTypeDecl decl) {
    PrimTypeLiteral _name = decl.getName();
    if (_name != null) {
      switch (_name) {
        case BOOL:
          return "bool";
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
          return "string";
        case VOID:
          return "void";
        default:
          break;
      }
    }
    return null;
  }
  
  public String getCsTypeName(final TypeDecl td) {
    return this.getCsTypeName(td, false);
  }
  
  public String getCsTypeName(final TypeDecl td, final boolean asObject) {
    boolean _isMany = this._typeUtil.isMany(td);
    if (_isMany) {
      this.rememberImports("System.Collections.Generic.List");
      final TypeDecl st = this._typeUtil.getSingleType(td);
      boolean _isPrim = this._typeUtil.isPrim(st);
      if (_isPrim) {
        String _csTypeName = this.csTypeName(this._typeUtil.getPrimType(st));
        String _plus = ("List<" + _csTypeName);
        return (_plus + ">");
      }
      String _csTypeName_1 = this.getCsTypeName(st);
      String _plus_1 = ("List<" + _csTypeName_1);
      return (_plus_1 + ">");
    } else {
      boolean _isPrim_1 = this._typeUtil.isPrim(td);
      if (_isPrim_1) {
        if (asObject) {
          return this.csTypeName(this._typeUtil.getPrimType(td));
        } else {
          return this.csTypeName(this._typeUtil.getPrimType(td));
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
