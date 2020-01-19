package de.sos.generator.java;

import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.java.JavaTypeUtil;
import de.sos.mORA.Interface;
import de.sos.mORA.Method;
import de.sos.mORA.Parameter;
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
public class JavaInterfaceGenerator extends AbstractGenerator {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private JavaTypeUtil _javaTypeUtil = new JavaTypeUtil();
  
  public JavaInterfaceGenerator() {
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    Iterable<Interface> _iterable = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(resource.getAllContents(), Interface.class));
    for (final Interface e : _iterable) {
      this.generate(e, fsa);
    }
  }
  
  public void generate(final Interface iface, final IFileSystemAccess2 fsa) {
    this._javaTypeUtil.clearImports();
    this._javaTypeUtil.rememberImports("java.util.HashMap");
    this._javaTypeUtil.rememberImports("java.util.concurrent.CompletableFuture");
    this._javaTypeUtil.rememberImports("java.util.concurrent.ExecutionException");
    this._javaTypeUtil.rememberImports("java.util.UUID");
    this._javaTypeUtil.rememberImports("java.io.IOException");
    this._javaTypeUtil.rememberImports("java.net.InetAddress");
    this._javaTypeUtil.rememberImports("de.sos.mora.IMoraAdapter");
    this._javaTypeUtil.rememberImports("de.sos.mora.types.IMoraTypeEncoder");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraOutputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraInputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.IMoraProxy");
    this._javaTypeUtil.rememberImports("de.sos.mora.RemoteObjects.RemoteObject");
    this._javaTypeUtil.rememberImports("de.sos.mora.exceptions.MoraException");
    this._javaTypeUtil.rememberImports("de.sos.mora.exceptions.MoraProxyException");
    this._javaTypeUtil.rememberImports("de.sos.mora.exceptions.MoraAdapterException");
    this._javaTypeUtil.rememberImports("de.sos.mora.exceptions.MoraInvalidAddressException");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.Communicator");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.Communicator.PROTOCOL");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.MoraMessages.OutMethodCall");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.MoraMessages.InMethodRespond");
    this._javaTypeUtil.rememberImports("de.sos.mora.RemoteObjects.RemoteMethod");
    this._javaTypeUtil.rememberImports("de.sos.mora.RemoteMethodCall");
    this._javaTypeUtil.rememberImports("de.sos.mora.Common");
    final CharSequence content = this.generate(iface);
    final QualifiedName qn = this._javaTypeUtil.fullyQualifiedName(iface);
    final CharSequence imports = this._javaTypeUtil.getImportBlock(qn);
    QualifiedName _skipLast = qn.skipLast(1);
    String _plus = ("package " + _skipLast);
    final String header = (_plus + ";\n");
    String _string = qn.skipLast(1).toString("/");
    String _plus_1 = (_string + "/");
    String _containerClassName = this._javaTypeUtil.getContainerClassName(iface);
    String _plus_2 = (_plus_1 + _containerClassName);
    String _plus_3 = (_plus_2 + ".java");
    fsa.generateFile(_plus_3, ((((header + "\n") + imports) + "\n") + content));
  }
  
  public CharSequence generate(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    String _containerClassName = this._javaTypeUtil.getContainerClassName(iface);
    _builder.append(_containerClassName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//////////////////////////\tIFace\t////////////////////////////");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateIFace = this.generateIFace(iface);
    _builder.append(_generateIFace, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("////////////////////////// User Interaction ////////////////////////////");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _createAdapter = this.createAdapter(iface);
    _builder.append(_createAdapter, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _createProxy = this.createProxy(iface);
    _builder.append(_createProxy, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("////////////////////////// Internal Stuff ////////////////////////////");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final String\t\t\tTYPE_IDENTIFIER = \"");
    String _upperCase = iface.getName().toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateEncoder = this.generateEncoder(iface);
    _builder.append(_generateEncoder, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateProxy = this.generateProxy(iface);
    _builder.append(_generateProxy, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateAdapter = this.generateAdapter(iface);
    _builder.append(_generateAdapter, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createProxy(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static IFace createProxy(Communicator communicator, final String remoteObjectAddress) throws MoraInvalidAddressException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return createProxy(communicator, RemoteObject.createRemoteObject(remoteObjectAddress));");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("public static IFace createProxy(Communicator communicator, final RemoteObject remoteObject) throws MoraInvalidAddressException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Proxy proxy = new Proxy(communicator, remoteObject);");
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
  
  public CharSequence createAdapter(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static Adapter publishAdapter(String identifier, IFace impl, Communicator communicator) throws MoraAdapterException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (communicator.hasAdapter(identifier)) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (communicator.getAdapter(identifier).getDelegate() == impl) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return communicator.getAdapter(identifier);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new MoraAdapterException(\"An Adapter with identifier: \" + identifier + \" already exists\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Adapter adapter = new Adapter(communicator, impl, identifier);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return communicator.registerAdapter(adapter);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateEncoder(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static void write(final IFace in, IMoraOutputStream stream, Communicator communicator) throws IOException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (in == null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream.writeByte(Common.STRUCT_NULL);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("else");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream.writeByte(Common.STRUCT_START);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Adapter adapter = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (in instanceof Adapter) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adapter = (Adapter)in;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}else {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("//create an adapter with random id");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("adapter = publishAdapter(UUID.randomUUID().toString(), in, communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (MoraAdapterException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new IOException(\"Failed to publish adapter\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("assert(adapter != null);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("final String qid = adapter.getQualifiedIdentifier();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream.writeString(qid);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public static Proxy read(IMoraInputStream stream, Communicator communicator) throws IOException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("byte flag = stream.readByte();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (flag == Common.STRUCT_NULL)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("assert(flag == Common.STRUCT_START);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("final String quid = stream.readString();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Proxy proxy = communicator.getProxy(quid);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (proxy == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("proxy = new Proxy(communicator, RemoteObject.createRemoteObject(quid));");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("communicator.registerProxy(proxy);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (MoraInvalidAddressException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}\t\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return proxy;\t");
    _builder.newLine();
    _builder.append("}");
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
        _builder.append("public ");
        String _javaTypeName = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
        _builder.append(_javaTypeName, "\t");
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
            String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
            _builder.append(_javaTypeName_1, "\t");
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
    _builder.append("public static class Proxy extends IMoraProxy<IFace> implements IFace {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private RemoteMethod[] \t\tmRemoteMethods = new RemoteMethod[");
    int _size = iface.getMethods().size();
    _builder.append(_size, "\t");
    _builder.append("];");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Proxy(Communicator communicator, RemoteObject remoteObject) throws MoraInvalidAddressException {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(communicator, remoteObject);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getTypeIdentifier() { return TYPE_IDENTIFIER; }");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("private static class RemoteCall_");
        String _signature = this._typeUtil.getSignature(m);
        _builder.append(_signature, "\t\t");
        _builder.append(" extends RemoteMethodCall<");
        String _javaTypeName = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m), true);
        _builder.append(_javaTypeName, "\t\t");
        _builder.append("> {");
        _builder.newLineIfNotEmpty();
        {
          EList<Parameter> _parameters = m.getParameters();
          for(final Parameter p : _parameters) {
            _builder.append("\t\t\t");
            _builder.append("private final ");
            String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
            _builder.append(_javaTypeName_1, "\t\t\t");
            _builder.append(" m");
            String _firstUpper = StringExtensions.toFirstUpper(p.getName());
            _builder.append(_firstUpper, "\t\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("public RemoteCall_");
        String _signature_1 = this._typeUtil.getSignature(m);
        _builder.append(_signature_1, "\t\t\t");
        _builder.append("(Communicator c, RemoteMethod rm");
        {
          boolean _isEmpty = m.getParameters().isEmpty();
          boolean _equals = (_isEmpty == false);
          if (_equals) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_1 = m.getParameters();
              boolean _hasElements = false;
              for(final Parameter p_1 : _parameters_1) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t\t");
                }
                String _javaTypeName_2 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p_1));
                _builder.append(_javaTypeName_2, "\t\t\t");
                _builder.append(" ");
                String _parameterName = this.getParameterName(p_1);
                _builder.append(_parameterName, "\t\t\t");
              }
            }
          }
        }
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("super(c, rm);");
        _builder.newLine();
        {
          EList<Parameter> _parameters_2 = m.getParameters();
          for(final Parameter p_2 : _parameters_2) {
            _builder.append("\t\t\t\t");
            _builder.append("m");
            String _firstUpper_1 = StringExtensions.toFirstUpper(p_2.getName());
            _builder.append(_firstUpper_1, "\t\t\t\t");
            _builder.append(" = ");
            String _parameterName_1 = this.getParameterName(p_2);
            _builder.append(_parameterName_1, "\t\t\t\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {");
        _builder.newLine();
        {
          EList<Parameter> _parameters_3 = m.getParameters();
          for(final Parameter p_3 : _parameters_3) {
            {
              boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
              if (_isPrim) {
                _builder.append("\t\t\t\t");
                _builder.append("os.write");
                String _streamPostFix = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(p_3))));
                _builder.append(_streamPostFix, "\t\t\t\t");
                _builder.append("(m");
                String _firstUpper_2 = StringExtensions.toFirstUpper(p_3.getName());
                _builder.append(_firstUpper_2, "\t\t\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
                  if (_isProxy) {
                    _builder.append("\t\t\t\t");
                    String _containerClassName = this._javaTypeUtil.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(p_3))));
                    _builder.append(_containerClassName, "\t\t\t\t");
                    _builder.append(".write(m");
                    String _firstUpper_3 = StringExtensions.toFirstUpper(p_3.getName());
                    _builder.append(_firstUpper_3, "\t\t\t\t");
                    _builder.append(", os, communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t\t\t\t");
                    String _javaTypeName_3 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(p_3)));
                    _builder.append(_javaTypeName_3, "\t\t\t\t");
                    _builder.append(".write(m");
                    String _firstUpper_4 = StringExtensions.toFirstUpper(p_3.getName());
                    _builder.append(_firstUpper_4, "\t\t\t\t");
                    _builder.append(", os, communicator);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("protected ");
        String _javaTypeName_4 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m), true);
        _builder.append(_javaTypeName_4, "\t\t\t");
        _builder.append(" decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {");
        _builder.newLineIfNotEmpty();
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_1 = (_isVoid == false);
          if (_equals_1) {
            {
              boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
              if (_isPrim_1) {
                _builder.append("\t\t\t\t");
                _builder.append("final ");
                String _javaTypeName_5 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
                _builder.append(_javaTypeName_5, "\t\t\t\t");
                _builder.append(" result = is.read");
                String _streamPostFix_1 = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                _builder.append(_streamPostFix_1, "\t\t\t\t");
                {
                  boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(m));
                  if (_isMany) {
                    _builder.append("List");
                  }
                }
                _builder.append("();");
                _builder.newLineIfNotEmpty();
              } else {
                {
                  boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                  if (_isProxy_1) {
                    _builder.append("\t\t\t\t");
                    _builder.append("final ");
                    String _javaTypeName_6 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
                    _builder.append(_javaTypeName_6, "\t\t\t\t");
                    _builder.append(" result = ");
                    String _containerClassName_1 = this._javaTypeUtil.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                    _builder.append(_containerClassName_1, "\t\t\t\t");
                    _builder.append(".read(is, communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t\t\t\t");
                    _builder.append("final ");
                    String _javaTypeName_7 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
                    _builder.append(_javaTypeName_7, "\t\t\t\t");
                    _builder.append(" result = ");
                    String _javaTypeName_8 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                    _builder.append(_javaTypeName_8, "\t\t\t\t");
                    _builder.append(".read");
                    {
                      boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(m));
                      if (_isMany_1) {
                        _builder.append("List");
                      }
                    }
                    _builder.append("(is, communicator);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
            _builder.append("\t\t\t\t");
            _builder.append("return result;");
            _builder.newLine();
          } else {
            _builder.append("\t\t\t\t");
            _builder.append("return null;");
            _builder.newLine();
          }
        }
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public CompletableFuture<");
        String _javaTypeName_9 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m), true);
        _builder.append(_javaTypeName_9, "\t\t");
        _builder.append("> async_");
        String _name = m.getName();
        _builder.append(_name, "\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_4 = m.getParameters();
          boolean _hasElements_1 = false;
          for(final Parameter p_4 : _parameters_4) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            String _javaTypeName_10 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p_4));
            _builder.append(_javaTypeName_10, "\t\t");
            _builder.append(" ");
            String _parameterName_2 = this.getParameterName(p_4);
            _builder.append(_parameterName_2, "\t\t");
          }
        }
        _builder.append(")  throws MoraException {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("final int methodIndex = ");
        int _indexOf = iface.getMethods().indexOf(m);
        _builder.append(_indexOf, "\t\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("if (mRemoteMethods[methodIndex] == null)");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("mRemoteMethods[methodIndex] = createRemoteMethod(\"");
        String _signature_2 = this._typeUtil.getSignature(m);
        _builder.append(_signature_2, "\t\t\t\t");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("RemoteCall_");
        String _signature_3 = this._typeUtil.getSignature(m);
        _builder.append(_signature_3, "\t\t\t");
        _builder.append(" call = new RemoteCall_");
        String _signature_4 = this._typeUtil.getSignature(m);
        _builder.append(_signature_4, "\t\t\t");
        _builder.append("(getCommunicator(), mRemoteMethods[methodIndex]");
        {
          boolean _isEmpty_1 = m.getParameters().isEmpty();
          boolean _equals_2 = (_isEmpty_1 == false);
          if (_equals_2) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_5 = m.getParameters();
              boolean _hasElements_2 = false;
              for(final Parameter p_5 : _parameters_5) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t\t");
                }
                String _parameterName_3 = this.getParameterName(p_5);
                _builder.append(_parameterName_3, "\t\t\t");
                _builder.append(" ");
              }
            }
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("return call.invoke();");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("public ");
        String _javaTypeName_11 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
        _builder.append(_javaTypeName_11, "\t\t");
        _builder.append(" ");
        String _name_1 = m.getName();
        _builder.append(_name_1, "\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_6 = m.getParameters();
          boolean _hasElements_3 = false;
          for(final Parameter p_6 : _parameters_6) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            String _javaTypeName_12 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p_6));
            _builder.append(_javaTypeName_12, "\t\t");
            _builder.append(" ");
            String _parameterName_4 = this.getParameterName(p_6);
            _builder.append(_parameterName_4, "\t\t");
          }
        }
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("try{");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("CompletableFuture<");
        String _javaTypeName_13 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m), true);
        _builder.append(_javaTypeName_13, "\t\t\t\t");
        _builder.append("> future = async_");
        String _name_2 = m.getName();
        _builder.append(_name_2, "\t\t\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_7 = m.getParameters();
          boolean _hasElements_4 = false;
          for(final Parameter p_7 : _parameters_7) {
            if (!_hasElements_4) {
              _hasElements_4 = true;
            } else {
              _builder.appendImmediate(",", "\t\t\t\t");
            }
            String _parameterName_5 = this.getParameterName(p_7);
            _builder.append(_parameterName_5, "\t\t\t\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("future.exceptionally(f -> {");
        _builder.newLine();
        _builder.append("\t\t\t\t\t");
        _builder.append("throw new RuntimeException(f);");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("});");
        _builder.newLine();
        {
          boolean _isVoid_1 = this._typeUtil.isVoid(this._typeUtil.getType(m));
          boolean _equals_3 = (_isVoid_1 == false);
          if (_equals_3) {
            _builder.append("\t\t\t\t");
            _builder.append("final ");
            String _javaTypeName_14 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
            _builder.append(_javaTypeName_14, "\t\t\t\t");
            _builder.append(" res = future.get();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("return res;");
            _builder.newLine();
          } else {
            _builder.append("\t\t\t\t");
            _builder.append("future.get();");
            _builder.newLine();
          }
        }
        _builder.append("\t\t\t");
        _builder.append("} catch (InterruptedException | ExecutionException | MoraException e) {");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("throw new RuntimeException(e);");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t");
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
    _builder.append("public static class Adapter extends IMoraAdapter<IFace> implements IFace {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static HashMap<String, IMethodInvokation<IFace>>\tsMethods = new HashMap<>();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sMethods.put(\"_getType_\", new _getType_Invoker());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t\t");
        _builder.append("sMethods.put(\"");
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
    _builder.append("\t");
    _builder.append("protected Adapter(Communicator communicator, IFace delegate, final String identifier) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(communicator, delegate, identifier);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected IMethodInvokation<IFace> getInvoker(String methodName) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return sMethods.get(methodName);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    {
      EList<Method> _methods_1 = iface.getMethods();
      for(final Method m_1 : _methods_1) {
        _builder.append("\t");
        _builder.append("public ");
        String _javaTypeName = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m_1));
        _builder.append(_javaTypeName, "\t");
        _builder.append(" ");
        String _name = m_1.getName();
        _builder.append(_name, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters = m_1.getParameters();
          boolean _hasElements = false;
          for(final Parameter p : _parameters) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
            _builder.append(_javaTypeName_1, "\t");
            _builder.append(" ");
            String _parameterName = this.getParameterName(p);
            _builder.append(_parameterName, "\t");
          }
        }
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m_1));
          if (_isVoid) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("mDelegate.");
            String _name_1 = m_1.getName();
            _builder.append(_name_1, "\t\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_1 = m_1.getParameters();
              boolean _hasElements_1 = false;
              for(final Parameter p_1 : _parameters_1) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t");
                }
                String _parameterName_1 = this.getParameterName(p_1);
                _builder.append(_parameterName_1, "\t\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return mDelegate.");
            String _name_2 = m_1.getName();
            _builder.append(_name_2, "\t\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_2 = m_1.getParameters();
              boolean _hasElements_2 = false;
              for(final Parameter p_2 : _parameters_2) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t");
                }
                String _parameterName_2 = this.getParameterName(p_2);
                _builder.append(_parameterName_2, "\t\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static class _getType_Invoker implements IMethodInvokation<IFace> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("os.writeString(TYPE_IDENTIFIER);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    {
      EList<Method> _methods_2 = iface.getMethods();
      for(final Method m_2 : _methods_2) {
        _builder.append("\t");
        CharSequence _generateInvoker = this.generateInvoker(m_2, iface);
        _builder.append(_generateInvoker, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateInvoker(final Method m, final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static class ");
    String _signature = this._typeUtil.getSignature(m);
    _builder.append(_signature);
    _builder.append("Invoker implements IMethodInvokation<IFace> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {");
    _builder.newLine();
    {
      EList<Parameter> _parameters = m.getParameters();
      for(final Parameter p : _parameters) {
        {
          boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
          if (_isPrim) {
            _builder.append("\t\t");
            _builder.append("final ");
            String _javaTypeName = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
            _builder.append(_javaTypeName, "\t\t");
            _builder.append(" ");
            String _parameterName = this.getParameterName(p);
            _builder.append(_parameterName, "\t\t");
            _builder.append(" = is.read");
            String _streamPostFix = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(p))));
            _builder.append(_streamPostFix, "\t\t");
            {
              boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(p));
              if (_isMany) {
                _builder.append("List");
              }
            }
            _builder.append("();");
            _builder.newLineIfNotEmpty();
          } else {
            {
              boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
              if (_isProxy) {
                _builder.append("\t\t");
                _builder.append("final ");
                String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
                _builder.append(_javaTypeName_1, "\t\t");
                _builder.append(" ");
                String _parameterName_1 = this.getParameterName(p);
                _builder.append(_parameterName_1, "\t\t");
                _builder.append(" = ");
                String _containerClassName = this._javaTypeUtil.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(p))));
                _builder.append(_containerClassName, "\t\t");
                _builder.append(".read");
                {
                  boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(p));
                  if (_isMany_1) {
                    _builder.append("List");
                  }
                }
                _builder.append("(is, communicator);");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                _builder.append("final ");
                String _javaTypeName_2 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(p));
                _builder.append(_javaTypeName_2, "\t\t");
                _builder.append(" ");
                String _parameterName_2 = this.getParameterName(p);
                _builder.append(_parameterName_2, "\t\t");
                _builder.append(" = ");
                String _javaTypeName_3 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(p)));
                _builder.append(_javaTypeName_3, "\t\t");
                _builder.append(".read");
                {
                  boolean _isMany_2 = this._typeUtil.isMany(this._typeUtil.getType(p));
                  if (_isMany_2) {
                    _builder.append("List");
                  }
                }
                _builder.append("(is, communicator);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    {
      boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m));
      if (_isVoid) {
        _builder.append("\t\t");
        _builder.append("delegate.");
        String _name = m.getName();
        _builder.append(_name, "\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_1 = m.getParameters();
          boolean _hasElements = false;
          for(final Parameter p_1 : _parameters_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            String _parameterName_3 = this.getParameterName(p_1);
            _builder.append(_parameterName_3, "\t\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("\t\t");
        _builder.append("final ");
        String _javaTypeName_4 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
        _builder.append(_javaTypeName_4, "\t\t");
        _builder.append(" _result_ = delegate.");
        String _name_1 = m.getName();
        _builder.append(_name_1, "\t\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_2 = m.getParameters();
          boolean _hasElements_1 = false;
          for(final Parameter p_2 : _parameters_2) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t\t");
            }
            String _parameterName_4 = this.getParameterName(p_2);
            _builder.append(_parameterName_4, "\t\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        {
          boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
          if (_isPrim_1) {
            _builder.append("\t\t");
            _builder.append("os.write");
            String _streamPostFix_1 = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
            _builder.append(_streamPostFix_1, "\t\t");
            _builder.append("(_result_);");
            _builder.newLineIfNotEmpty();
          } else {
            {
              boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
              if (_isProxy_1) {
                _builder.append("\t\t");
                String _containerClassName_1 = this._javaTypeUtil.getContainerClassName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                _builder.append(_containerClassName_1, "\t\t");
                _builder.append(".write(_result_, os, communicator);");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                String _javaTypeName_5 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                _builder.append(_javaTypeName_5, "\t\t");
                _builder.append(".write(_result_, os, communicator);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String getParameterName(final Parameter parameter) {
    return parameter.getName();
  }
}
