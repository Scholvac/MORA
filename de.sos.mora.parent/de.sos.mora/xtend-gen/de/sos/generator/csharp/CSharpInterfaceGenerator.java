package de.sos.generator.csharp;

import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.csharp.CSharpGenerator;
import de.sos.generator.csharp.CSharpTypeUtil;
import de.sos.mORA.Interface;
import de.sos.mORA.Method;
import de.sos.mORA.Parameter;
import de.sos.mORA.TypeDecl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CSharpInterfaceGenerator extends AbstractGenerator {
  private final CSharpGenerator.CSharpOptions opt;
  
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private CSharpTypeUtil _cSharpTypeUtil = new CSharpTypeUtil();
  
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider = new QualifiedNameProvider();
  
  public CSharpInterfaceGenerator(final CSharpGenerator.CSharpOptions options) {
    this.opt = options;
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    Iterable<Interface> _iterable = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(resource.getAllContents(), Interface.class));
    for (final Interface e : _iterable) {
      this.generate(e, fsa);
    }
  }
  
  public void generate(final Interface i, final IFileSystemAccess2 fsa) {
    this._cSharpTypeUtil.clearImports();
    this._cSharpTypeUtil.rememberImports("System.NullReferenceException");
    this._cSharpTypeUtil.rememberImports("System.Threading.Tasks.Task");
    this._cSharpTypeUtil.rememberImports("System.IO.StreamWriter");
    this._cSharpTypeUtil.rememberImports("System.Collections.Generic.List");
    this._cSharpTypeUtil.rememberImports("System.Collections.IList");
    this._cSharpTypeUtil.rememberImports("Mora.AbstractAdapter");
    this._cSharpTypeUtil.rememberImports("Mora.IMethodInvokation");
    this._cSharpTypeUtil.rememberImports("Mora.Stream.IMoraOutputStream");
    this._cSharpTypeUtil.rememberImports("Mora.Com.Communicator");
    final CharSequence content = this.generate(i);
    final QualifiedName qn = this._cSharpTypeUtil.fullyQualifiedName(i);
    final CharSequence imports = this._cSharpTypeUtil.getImportBlock(qn);
    final String allContent = ((("\n" + imports) + "\n") + content);
    String _replace = this.opt.namespace.replace(".", "/");
    String _plus = (_replace + "/");
    String _string = this._cSharpTypeUtil.fullyQualifiedName(i).toString("/");
    String _plus_1 = (_plus + _string);
    final String fileName = (_plus_1 + ".cs");
    fsa.generateFile(fileName, allContent);
  }
  
  public CharSequence generate(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("namespace ");
    {
      boolean _isEmpty = this.opt.namespace.isEmpty();
      boolean _equals = (_isEmpty == false);
      if (_equals) {
        _builder.append(this.opt.namespace);
        _builder.append(".");
      }
    }
    String _string = this._cSharpTypeUtil.fullyQualifiedName(iface).skipLast(1).toString(".");
    _builder.append(_string);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public class ");
    String _containerClassName = this.getContainerClassName(iface);
    _builder.append(_containerClassName, "\t");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static readonly string TYPE_IDENTIFIER = \"");
    String _upperCase = iface.getName().toUpperCase();
    _builder.append(_upperCase, "\t\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region IFace");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateIFace = this.generateIFace(iface);
    _builder.append(_generateIFace, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region Creation");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _creation = this.creation(iface);
    _builder.append(_creation, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region Serialisation");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateEncoder = this.generateEncoder(iface);
    _builder.append(_generateEncoder, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region Proxy");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateProxy = this.generateProxy(iface);
    _builder.append(_generateProxy, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region Adapter");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateAdapter = this.generateAdapter(iface);
    _builder.append(_generateAdapter, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence creation(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static Adapter publishAdapter(string identifier, IFace impl, Communicator communicator)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (communicator.hasAdapter(identifier))");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (communicator.getAdapter(identifier).represents(impl))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return (Adapter)communicator.getAdapter(identifier);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new MoraAdapterException(\"An Adapter with identifier: \" + identifier + \" already exists\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Adapter result = new Adapter(impl, identifier, communicator);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return communicator.registerAdapter(result);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public static Proxy createProxy(Communicator communicator, String remoteObjectAddress) ");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return createProxy(communicator, RemoteObject.create(remoteObjectAddress));");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public static Proxy createProxy(Communicator communicator, RemoteObject remoteObject)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Proxy proxy = new Proxy(remoteObject, communicator);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (proxy.checkType())");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return proxy;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateEncoder(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static void Write(List<IFace> adapter, IMoraOutputStream stream, Communicator communicator)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.Write(adapter.Count);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < adapter.Count; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Write(adapter[i], stream, communicator);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("public static void Write(IFace iface, IMoraOutputStream stream, Communicator communicator)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (iface == null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream.Write(Common.STRUCT_NULL);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("else");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream.Write(Common.STRUCT_START);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Adapter adapter = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (iface is Adapter) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adapter = (Adapter)iface;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("//create an adapter with random id");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adapter = publishAdapter(System.Guid.NewGuid().ToString(), iface, communicator);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("string qid = adapter.getQualifiedIdentifier();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.Write(qid);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public static void Read(IMoraInputStream stream, Communicator communicator, out List<IFace> proxies)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int count;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.Read(out count);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("proxies = new List<IFace>(count);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < count; i++){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("IFace proxy;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Read(stream, communicator, out proxy);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("proxies.Add(proxy);\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("public static void Read(IMoraInputStream stream, Communicator communicator, out IFace result)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("byte flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.Read(out flag);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (flag == Common.STRUCT_NULL){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("result = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("string quid;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.Read(out quid);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("IMoraProxy proxy = communicator.getProxy(quid);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (proxy == null){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("proxy = new Proxy(RemoteObject.create(quid), communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("communicator.registerProxy(proxy);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("result = proxy as Proxy;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateIFace(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public interface IFace {");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t");
        String _csTypeName = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
        _builder.append(_csTypeName, "\t");
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
            String _csTypeName_1 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p));
            _builder.append(_csTypeName_1, "\t");
            _builder.append(" ");
            String _parameterName = this.getParameterName(p);
            _builder.append(_parameterName, "\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateProxy(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class Proxy : AbstractProxy<IFace>, IFace {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private RemoteMethod[] mMethods = new RemoteMethod[");
    int _size = iface.getMethods().size();
    _builder.append(_size, "\t");
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Proxy(RemoteObject remoteObject, Communicator communicator) ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(":\tbase(remoteObject, communicator)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public override string GetTypeIdentifier()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    String _containerClassName = this.getContainerClassName(iface);
    _builder.append(_containerClassName, "\t\t");
    _builder.append(".TYPE_IDENTIFIER;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private class RemoteCall_");
        String _signature = this._typeUtil.getSignature(m);
        _builder.append(_signature, "\t");
        _builder.append(" : Remote");
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
          if (_isVoid) {
            _builder.append("Void");
          }
        }
        _builder.append("MethodCall");
        {
          boolean _isVoid_1 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals = (_isVoid_1 == false);
          if (_equals) {
            _builder.append("<");
            String _csTypeName = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
            _builder.append(_csTypeName, "\t");
            _builder.append(">");
          }
        }
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        {
          EList<Parameter> _parameters = m.getParameters();
          for(final Parameter p : _parameters) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("private ");
            String _csTypeName_1 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p));
            _builder.append(_csTypeName_1, "\t\t");
            _builder.append(" m");
            String _firstUpper = StringExtensions.toFirstUpper(p.getName());
            _builder.append(_firstUpper, "\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public RemoteCall_");
        String _signature_1 = this._typeUtil.getSignature(m);
        _builder.append(_signature_1, "\t\t");
        _builder.append("(Communicator c, RemoteMethod rm");
        {
          boolean _isEmpty = m.getParameters().isEmpty();
          boolean _equals_1 = (_isEmpty == false);
          if (_equals_1) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_1 = m.getParameters();
              boolean _hasElements = false;
              for(final Parameter p_1 : _parameters_1) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t");
                }
                String _csTypeName_2 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p_1));
                _builder.append(_csTypeName_2, "\t\t");
                _builder.append(" ");
                String _parameterName = this.getParameterName(p_1);
                _builder.append(_parameterName, "\t\t");
              }
            }
          }
        }
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append(":\tbase(c, rm) ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        {
          EList<Parameter> _parameters_2 = m.getParameters();
          for(final Parameter p_2 : _parameters_2) {
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("m");
            String _firstUpper_1 = StringExtensions.toFirstUpper(p_2.getName());
            _builder.append(_firstUpper_1, "\t\t\t");
            _builder.append(" = ");
            String _parameterName_1 = this.getParameterName(p_2);
            _builder.append(_parameterName_1, "\t\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        {
          EList<Parameter> _parameters_3 = m.getParameters();
          for(final Parameter p_3 : _parameters_3) {
            {
              boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
              if (_isPrim) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("_oStream.Write(m");
                String _firstUpper_2 = StringExtensions.toFirstUpper(p_3.getName());
                _builder.append(_firstUpper_2, "\t\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
                  if (_isProxy) {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _containerClassName_1 = this.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(p_3))));
                    _builder.append(_containerClassName_1, "\t\t\t");
                    _builder.append(".Write(m");
                    String _firstUpper_3 = StringExtensions.toFirstUpper(p_3.getName());
                    _builder.append(_firstUpper_3, "\t\t\t");
                    _builder.append(", _oStream, _communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _singleCsTypeName = this._cSharpTypeUtil.getSingleCsTypeName(this._typeUtil.getType(p_3));
                    _builder.append(_singleCsTypeName, "\t\t\t");
                    {
                      boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
                      if (_isEnum) {
                        _builder.append("Util");
                      }
                    }
                    _builder.append(".Write(m");
                    String _firstUpper_4 = StringExtensions.toFirstUpper(p_3.getName());
                    _builder.append(_firstUpper_4, "\t\t\t");
                    _builder.append(", _oStream, _communicator);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        {
          boolean _isVoid_2 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_2 = (_isVoid_2 == false);
          if (_equals_2) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("protected override ");
            String _csTypeName_3 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
            _builder.append(_csTypeName_3, "\t\t");
            _builder.append(" ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("{");
            _builder.newLine();
            {
              boolean _isVoid_3 = this._typeUtil.isVoid(this._typeUtil.getType(m));
              if (_isVoid_3) {
              } else {
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                String _csTypeName_4 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
                _builder.append(_csTypeName_4, "\t\t\t");
                _builder.append(" _result;");
                _builder.newLineIfNotEmpty();
                {
                  boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                  if (_isPrim_1) {
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("_iStream.Read(out _result);");
                    _builder.newLine();
                  } else {
                    {
                      boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                      if (_isProxy_1) {
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        String _containerClassName_2 = this.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                        _builder.append(_containerClassName_2, "\t\t\t");
                        _builder.append(".Read(_iStream, _communicator, out _result);");
                        _builder.newLineIfNotEmpty();
                      } else {
                        _builder.append("\t");
                        _builder.append("\t");
                        _builder.append("\t");
                        String _singleCsTypeName_1 = this._cSharpTypeUtil.getSingleCsTypeName(this._typeUtil.getType(m));
                        _builder.append(_singleCsTypeName_1, "\t\t\t");
                        {
                          boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                          if (_isEnum_1) {
                            _builder.append("Util");
                          }
                        }
                        _builder.append(".Read(_iStream, _communicator, out _result);");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("return _result;");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public Task");
        {
          boolean _isVoid_4 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_3 = (_isVoid_4 == false);
          if (_equals_3) {
            _builder.append("<");
            String _csTypeName_5 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
            _builder.append(_csTypeName_5, "\t");
            _builder.append(">");
          }
        }
        _builder.append(" Async_");
        String _name = m.getName();
        _builder.append(_name, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_4 = m.getParameters();
          boolean _hasElements_1 = false;
          for(final Parameter p_4 : _parameters_4) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _csTypeName_6 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p_4));
            _builder.append(_csTypeName_6, "\t");
            _builder.append(" ");
            String _parameterName_2 = this.getParameterName(p_4);
            _builder.append(_parameterName_2, "\t");
          }
        }
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("int methodIndex = ");
        int _indexOf = iface.getMethods().indexOf(m);
        _builder.append(_indexOf, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("RemoteMethod method = mMethods[methodIndex];");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("if (method == null)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("mMethods[methodIndex] = method = CreateRemoteMethod(\"");
        String _signature_2 = this._typeUtil.getSignature(m);
        _builder.append(_signature_2, "\t\t\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("RemoteCall_");
        String _signature_3 = this._typeUtil.getSignature(m);
        _builder.append(_signature_3, "\t\t");
        _builder.append(" call = new RemoteCall_");
        String _signature_4 = this._typeUtil.getSignature(m);
        _builder.append(_signature_4, "\t\t");
        _builder.append("(Communicator, method");
        {
          boolean _isEmpty_1 = m.getParameters().isEmpty();
          boolean _equals_4 = (_isEmpty_1 == false);
          if (_equals_4) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_5 = m.getParameters();
              boolean _hasElements_2 = false;
              for(final Parameter p_5 : _parameters_5) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t");
                }
                String _parameterName_3 = this.getParameterName(p_5);
                _builder.append(_parameterName_3, "\t\t");
                _builder.append(" ");
              }
            }
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return call.invoke();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public ");
        String _csTypeName_7 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
        _builder.append(_csTypeName_7, "\t");
        _builder.append(" ");
        String _name_1 = m.getName();
        _builder.append(_name_1, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_6 = m.getParameters();
          boolean _hasElements_3 = false;
          for(final Parameter p_6 : _parameters_6) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _csTypeName_8 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p_6));
            _builder.append(_csTypeName_8, "\t");
            _builder.append(" ");
            String _parameterName_4 = this.getParameterName(p_6);
            _builder.append(_parameterName_4, "\t");
          }
        }
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("Task");
        {
          boolean _isVoid_5 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_5 = (_isVoid_5 == false);
          if (_equals_5) {
            _builder.append("<");
            String _csTypeName_9 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
            _builder.append(_csTypeName_9, "\t\t");
            _builder.append(">");
          }
        }
        _builder.append(" _task = Async_");
        String _name_2 = m.getName();
        _builder.append(_name_2, "\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_7 = m.getParameters();
          boolean _hasElements_4 = false;
          for(final Parameter p_7 : _parameters_7) {
            if (!_hasElements_4) {
              _hasElements_4 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            String _parameterName_5 = this.getParameterName(p_7);
            _builder.append(_parameterName_5, "\t\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("_task.Wait();");
        _builder.newLine();
        {
          boolean _isVoid_6 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_6 = (_isVoid_6 == false);
          if (_equals_6) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return _task.Result;");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateAdapter(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class Adapter : AbstractAdapter<IFace> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static Dictionary<string, IMethodInvokation<IFace>> sMethods = new Dictionary<string, IMethodInvokation<IFace>>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static Adapter() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sMethods.Add(\"_getType_\", new _getType_Invoker());");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t\t");
        _builder.append("sMethods.Add(\"");
        String _signature = this._typeUtil.getSignature(m);
        _builder.append(_signature, "\t\t");
        _builder.append("\", new ");
        String _signature_1 = this._typeUtil.getSignature(m);
        _builder.append(_signature_1, "\t\t");
        _builder.append("Invoker());");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t            \t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Adapter(IFace deleg, string identifier, Communicator communicator)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(": base(deleg, identifier, communicator)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected override IMethodInvokation<IFace> getInvoker(string methodName)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return sMethods[methodName];");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("#region invoker");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private class _getType_Invoker : IMethodInvokation<IFace>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public void Invoke(IFace deleg, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("oStream.Write(");
    String _containerClassName = this.getContainerClassName(iface);
    _builder.append(_containerClassName, "\t\t\t");
    _builder.append(".TYPE_IDENTIFIER);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Method> _methods_1 = iface.getMethods();
      for(final Method m_1 : _methods_1) {
        _builder.append("\t");
        _builder.append("private class ");
        String _signature_2 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_2, "\t");
        _builder.append("Invoker : IMethodInvokation<IFace> {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        {
          EList<Parameter> _parameters = m_1.getParameters();
          for(final Parameter p : _parameters) {
            _builder.append("\t");
            _builder.append("\t\t");
            String _csTypeName = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(p));
            _builder.append(_csTypeName, "\t\t\t");
            _builder.append(" ");
            String _parameterName = this.getParameterName(p);
            _builder.append(_parameterName, "\t\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            {
              boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
              if (_isPrim) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("_iStream.Read(out ");
                String _parameterName_1 = this.getParameterName(p);
                _builder.append(_parameterName_1, "\t\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
                  if (_isProxy) {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _containerClassName_1 = this.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(p))));
                    _builder.append(_containerClassName_1, "\t\t\t");
                    _builder.append(".Read(_iStream, _communicator, out ");
                    String _parameterName_2 = this.getParameterName(p);
                    _builder.append(_parameterName_2, "\t\t\t");
                    _builder.append(");");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _singleCsTypeName = this._cSharpTypeUtil.getSingleCsTypeName(this._typeUtil.getType(p));
                    _builder.append(_singleCsTypeName, "\t\t\t");
                    {
                      boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
                      if (_isEnum) {
                        _builder.append("Util");
                      }
                    }
                    _builder.append(".Read(_iStream, _communicator, out ");
                    String _parameterName_3 = this.getParameterName(p);
                    _builder.append(_parameterName_3, "\t\t\t");
                    _builder.append(");");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m_1));
          if (_isVoid) {
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("_deleg.");
            String _name = m_1.getName();
            _builder.append(_name, "\t\t\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_1 = m_1.getParameters();
              boolean _hasElements = false;
              for(final Parameter p_1 : _parameters_1) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t\t");
                }
                String _parameterName_4 = this.getParameterName(p_1);
                _builder.append(_parameterName_4, "\t\t\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("\t\t");
            String _csTypeName_1 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m_1));
            _builder.append(_csTypeName_1, "\t\t\t");
            _builder.append(" _result_ = _deleg.");
            String _name_1 = m_1.getName();
            _builder.append(_name_1, "\t\t\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_2 = m_1.getParameters();
              boolean _hasElements_1 = false;
              for(final Parameter p_2 : _parameters_2) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t\t");
                }
                String _parameterName_5 = this.getParameterName(p_2);
                _builder.append(_parameterName_5, "\t\t\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            {
              boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
              if (_isPrim_1) {
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("_oStream.Write(_result_);");
                _builder.newLine();
              } else {
                {
                  boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
                  if (_isProxy_1) {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _containerClassName_2 = this.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m_1))));
                    _builder.append(_containerClassName_2, "\t\t\t");
                    _builder.append(".Write(_result_, _oStream, _communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    _builder.append("\t\t");
                    String _singleCsTypeName_1 = this._cSharpTypeUtil.getSingleCsTypeName(this._typeUtil.getType(m_1));
                    _builder.append(_singleCsTypeName_1, "\t\t\t");
                    {
                      boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
                      if (_isEnum_1) {
                        _builder.append("Util");
                      }
                    }
                    _builder.append(".Write(_result_, _oStream, _communicator);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String csTypeNameOrVoid(final TypeDecl decl) {
    boolean _equals = this._cSharpTypeUtil.getCsTypeName(decl).equals("void");
    if (_equals) {
      return "Object";
    }
    return this._cSharpTypeUtil.getCsTypeName(decl);
  }
  
  public String getParameterName(final Parameter parameter) {
    return parameter.getName();
  }
  
  public String getContainerClassName(final Interface iface) {
    String _firstUpper = StringExtensions.toFirstUpper(iface.getName());
    return (_firstUpper + "RPC");
  }
}
