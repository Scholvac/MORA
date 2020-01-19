package de.sos.generator;

import com.google.common.base.Objects;
import de.sos.mORA.EnumDecl;
import de.sos.mORA.Interface;
import de.sos.mORA.ListTypeDecl;
import de.sos.mORA.MORAFactory;
import de.sos.mORA.Member;
import de.sos.mORA.Method;
import de.sos.mORA.Model;
import de.sos.mORA.Parameter;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.SingleTypeDecl;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;
import de.sos.mORA.impl.TypeDeclImpl;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TypeUtil {
  public static class ProxyTypeDecl extends TypeDeclImpl {
    public Interface iface;
    
    public ProxyTypeDecl(final Interface i) {
      this.iface = i;
    }
  }
  
  private Map<PrimTypeLiteral, PrimTypeDecl> mPrimTypes = new HashMap<PrimTypeLiteral, PrimTypeDecl>();
  
  private Map<Interface, TypeUtil.ProxyTypeDecl> mIFaceTypes = new HashMap<Interface, TypeUtil.ProxyTypeDecl>();
  
  public TypeDecl getType(final Member member) {
    TypeDecl _complexType = member.getComplexType();
    boolean _tripleNotEquals = (_complexType != null);
    if (_tripleNotEquals) {
      return member.getComplexType();
    }
    return this._getPrimType(member.getPrimType());
  }
  
  public TypeDecl getType(final Parameter param) {
    TypeDecl _complexType = param.getComplexType();
    boolean _tripleNotEquals = (_complexType != null);
    if (_tripleNotEquals) {
      return param.getComplexType();
    }
    Interface _proxyType = param.getProxyType();
    boolean _tripleNotEquals_1 = (_proxyType != null);
    if (_tripleNotEquals_1) {
      return this._getIFaceType(param.getProxyType());
    }
    return this._getPrimType(param.getPrimType());
  }
  
  public TypeDecl getType(final Method meth) {
    Interface _returnProxyType = meth.getReturnProxyType();
    boolean _tripleNotEquals = (_returnProxyType != null);
    if (_tripleNotEquals) {
      return this._getIFaceType(meth.getReturnProxyType());
    }
    TypeDecl _complexType = meth.getComplexType();
    boolean _tripleNotEquals_1 = (_complexType != null);
    if (_tripleNotEquals_1) {
      return meth.getComplexType();
    }
    return this._getPrimType(meth.getPrimType());
  }
  
  public TypeDecl getSingleType(final TypeDecl td) {
    if ((td instanceof ListTypeDecl)) {
      final ListTypeDecl lt = ((ListTypeDecl) td);
      SingleTypeDecl _valueType = lt.getValueType();
      boolean _tripleNotEquals = (_valueType != null);
      if (_tripleNotEquals) {
        return lt.getValueType();
      }
      return this._getPrimType(lt.getPrimType());
    }
    return td;
  }
  
  public PrimTypeDecl getPrimType(final TypeDecl decl) {
    return ((PrimTypeDecl) decl);
  }
  
  public Interface getProxyType(final TypeDecl decl) {
    return ((TypeUtil.ProxyTypeDecl) decl).iface;
  }
  
  public StructDecl getStructType(final TypeDecl decl) {
    return ((StructDecl) decl);
  }
  
  public EnumDecl getEnumType(final TypeDecl decl) {
    return ((EnumDecl) decl);
  }
  
  public String getName(final TypeDecl decl) {
    boolean _isPrim = this.isPrim(decl);
    if (_isPrim) {
      return this.getPrimType(decl).getName().name();
    }
    boolean _isProxy = this.isProxy(decl);
    if (_isProxy) {
      return this.getProxyType(decl).getName();
    }
    boolean _isStruct = this.isStruct(decl);
    if (_isStruct) {
      return this.getStructType(decl).getName();
    }
    boolean _isEnum = this.isEnum(decl);
    if (_isEnum) {
      return this.getEnumType(decl).getName();
    }
    boolean _isMany = this.isMany(decl);
    if (_isMany) {
      return ((ListTypeDecl) decl).getName();
    }
    return null;
  }
  
  public boolean isMany(final TypeDecl td) {
    return (td instanceof ListTypeDecl);
  }
  
  public boolean isStruct(final TypeDecl td) {
    return (td instanceof StructDecl);
  }
  
  public boolean isPointer(final TypeDecl td) {
    return this.isStruct(td);
  }
  
  public boolean isProxy(final TypeDecl td) {
    return (td instanceof TypeUtil.ProxyTypeDecl);
  }
  
  public boolean isPrim(final TypeDecl td) {
    return (td instanceof PrimTypeDecl);
  }
  
  public boolean isEnum(final TypeDecl td) {
    return (td instanceof EnumDecl);
  }
  
  public boolean isVoid(final TypeDecl td) {
    boolean _isPrim = this.isPrim(td);
    boolean _equals = (_isPrim == false);
    if (_equals) {
      return false;
    }
    PrimTypeLiteral _name = this.getPrimType(td).getName();
    return Objects.equal(_name, PrimTypeLiteral.VOID);
  }
  
  public Model model(final TypeDecl t) {
    EObject c = t.eContainer();
    if ((c instanceof Model)) {
      return ((Model)c);
    }
    while ((c != null)) {
      {
        c = c.eContainer();
        if ((c instanceof Model)) {
          return ((Model)c);
        }
      }
    }
    return null;
  }
  
  public Model model(final Interface t) {
    EObject c = t.eContainer();
    if ((c instanceof Model)) {
      return ((Model)c);
    }
    while ((c != null)) {
      {
        c = c.eContainer();
        if ((c instanceof Model)) {
          return ((Model)c);
        }
      }
    }
    return null;
  }
  
  public PrimTypeDecl _getPrimType(final PrimTypeLiteral l) {
    PrimTypeDecl pt = this.mPrimTypes.get(l);
    if ((pt == null)) {
      pt = MORAFactory.eINSTANCE.createPrimTypeDecl();
      pt.setName(l);
      this.mPrimTypes.put(l, pt);
    }
    return pt;
  }
  
  public TypeUtil.ProxyTypeDecl _getIFaceType(final Interface l) {
    TypeUtil.ProxyTypeDecl pt = this.mIFaceTypes.get(l);
    if ((pt == null)) {
      TypeUtil.ProxyTypeDecl _proxyTypeDecl = new TypeUtil.ProxyTypeDecl(l);
      pt = _proxyTypeDecl;
      this.mIFaceTypes.put(l, pt);
    }
    return pt;
  }
  
  public String getSignature(final Method m) {
    String n = m.getName();
    boolean _isEmpty = m.getParameters().isEmpty();
    boolean _equals = (_isEmpty == false);
    if (_equals) {
      n = (n + "_");
      EList<Parameter> _parameters = m.getParameters();
      for (final Parameter p : _parameters) {
        String _name = this.getName(this.getType(p));
        String _plus = (n + _name);
        String _plus_1 = (_plus + "_");
        n = _plus_1;
      }
    }
    return StringExtensions.toFirstUpper(n);
  }
}
