package de.sos.generator.csharp;

import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.csharp.CSharpGenerator;
import de.sos.generator.csharp.CSharpTypeUtil;
import de.sos.mORA.EnumDecl;
import de.sos.mORA.Literal;
import de.sos.mORA.Member;
import de.sos.mORA.StructDecl;
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
public class CSharpStructGenerator extends AbstractGenerator {
  private final CSharpGenerator.CSharpOptions opt;
  
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private CSharpTypeUtil _cSharpTypeUtil = new CSharpTypeUtil();
  
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider = new QualifiedNameProvider();
  
  public CSharpStructGenerator(final CSharpGenerator.CSharpOptions options) {
    this.opt = options;
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    Iterable<EnumDecl> _iterable = IteratorExtensions.<EnumDecl>toIterable(Iterators.<EnumDecl>filter(resource.getAllContents(), EnumDecl.class));
    for (final EnumDecl e : _iterable) {
      this.generateEnum(e, fsa);
    }
    Iterable<StructDecl> _iterable_1 = IteratorExtensions.<StructDecl>toIterable(Iterators.<StructDecl>filter(resource.getAllContents(), StructDecl.class));
    for (final StructDecl s : _iterable_1) {
      this.generateStruct(s, fsa);
    }
  }
  
  public void generateStruct(final StructDecl st, final IFileSystemAccess2 fsa) {
    this._cSharpTypeUtil.clearImports();
    this._cSharpTypeUtil.rememberImports("System.IO.StreamWriter");
    this._cSharpTypeUtil.rememberImports("System.Collections.IList");
    this._cSharpTypeUtil.rememberImports("System.Collections.Generic.List");
    this._cSharpTypeUtil.rememberImports("Mora.Stream.IMoraOutputStream");
    this._cSharpTypeUtil.rememberImports("Mora.Stream.IMoraInputStream");
    this._cSharpTypeUtil.rememberImports("Mora.Com.Communicator");
    this._cSharpTypeUtil.rememberImports("Mora.Common");
    final CharSequence content = this.generate(st);
    final QualifiedName qn = this._cSharpTypeUtil.fullyQualifiedName(st);
    final CharSequence imports = this._cSharpTypeUtil.getImportBlock(qn);
    final String allContent = ((("\n" + imports) + "\n") + content);
    String _replace = this.opt.namespace.replace(".", "/");
    String _plus = (_replace + "/");
    String _string = this._cSharpTypeUtil.fullyQualifiedName(st).toString("/");
    String _plus_1 = (_plus + _string);
    final String fileName = (_plus_1 + ".cs");
    fsa.generateFile(fileName, allContent);
  }
  
  public CharSequence generate(final StructDecl st) {
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
    String _string = this._cSharpTypeUtil.fullyQualifiedName(st).skipLast(1).toString(".");
    _builder.append(_string);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      if (this.opt.generateForUnity) {
        _builder.append("[System.Serializable]");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public class ");
    String _csTypeName = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName, "\t");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region member");
    _builder.newLine();
    {
      EList<Member> _member = st.getMember();
      for(final Member m : _member) {
        _builder.append("\t\t");
        {
          if (this.opt.generateForUnity) {
            _builder.append("public");
          } else {
            _builder.append("private");
          }
        }
        _builder.append(" ");
        String _csTypeName_1 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m));
        _builder.append(_csTypeName_1, "\t\t");
        _builder.append(" ");
        String _memberName = this.getMemberName(m);
        _builder.append(_memberName, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region constructor");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public ");
    String _csTypeName_2 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_2, "\t\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#endregion");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    {
      if ((this.opt.generateForUnity == false)) {
        _builder.append("\t\t");
        _builder.append("#region properties");
        _builder.newLine();
        {
          EList<Member> _member_1 = st.getMember();
          for(final Member m_1 : _member_1) {
            _builder.append("\t\t");
            _builder.append("public ");
            String _csTypeName_3 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getType(m_1));
            _builder.append(_csTypeName_3, "\t\t");
            _builder.append(" ");
            String _propertyName = this.getPropertyName(m_1);
            _builder.append(_propertyName, "\t\t");
            _builder.append(" {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("get { return ");
            String _memberName_1 = this.getMemberName(m_1);
            _builder.append(_memberName_1, "\t\t\t");
            _builder.append("; }");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("\t");
            _builder.append("set { ");
            String _memberName_2 = this.getMemberName(m_1);
            _builder.append(_memberName_2, "\t\t\t");
            _builder.append(" = value; }");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("} ");
            _builder.newLine();
          }
        }
        _builder.append("\t\t");
        _builder.append("#endregion");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region serialisation");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Write(List<");
    String _csTypeName_4 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_4, "\t\t");
    _builder.append("> _in, IMoraOutputStream stream, Communicator communicator){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("stream.Write(_in.Count);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for (int i = 0; i < _in.Count; i++)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Write(_in[i], stream, communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Write(");
    String _csTypeName_5 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_5, "\t\t");
    _builder.append(" _in, IMoraOutputStream stream, Communicator communicator){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("if (_in == null){");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("stream.Write(Common.STRUCT_NULL); //value does not exists");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return ;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}else{");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("stream.Write(Common.STRUCT_START); //value exists");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    {
      EList<Member> _member_2 = st.getMember();
      for(final Member m_2 : _member_2) {
        {
          boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_2)));
          if (_isPrim) {
            _builder.append("\t\t\t");
            _builder.append("stream.Write(_in.");
            String _propertyName_1 = this.getPropertyName(m_2);
            _builder.append(_propertyName_1, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            {
              boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_2)));
              if (_isEnum) {
                _builder.append("\t\t\t");
                String _csTypeName_6 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_2)));
                _builder.append(_csTypeName_6, "\t\t\t");
                _builder.append("Util.Write(_in.");
                String _propertyName_2 = this.getPropertyName(m_2);
                _builder.append(_propertyName_2, "\t\t\t");
                _builder.append(", stream, communicator);");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t\t");
                String _csTypeName_7 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_2)));
                _builder.append(_csTypeName_7, "\t\t\t");
                _builder.append(".Write(_in.");
                String _propertyName_3 = this.getPropertyName(m_2);
                _builder.append(_propertyName_3, "\t\t\t");
                _builder.append(", stream, communicator);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t\t");
    _builder.append("stream.Write(Common.STRUCT_END);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Read(IMoraInputStream stream, Communicator communicator, out List<");
    String _csTypeName_8 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_8, "\t\t");
    _builder.append("> result){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("int count;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stream.Read(out count);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result = new List<");
    String _csTypeName_9 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_9, "\t\t\t");
    _builder.append(">(count);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("for (int i = 0; i < count; i++){");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    String _csTypeName_10 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_10, "\t\t\t\t");
    _builder.append(" value;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("Read(stream, communicator, out value);");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("result.Add(value);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static bool Read(IMoraInputStream stream, Communicator communicator, out ");
    String _csTypeName_11 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_11, "\t\t");
    _builder.append(" result){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("byte _flag_;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stream.Read(out _flag_);\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (_flag_ == Common.STRUCT_NULL){");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("result = null;");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (_flag_ != Common.STRUCT_START)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("throw new System.Exception(\"Missing start flag\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("result = new ");
    String _csTypeName_12 = this._cSharpTypeUtil.getCsTypeName(st);
    _builder.append(_csTypeName_12, "\t\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    {
      EList<Member> _member_3 = st.getMember();
      for(final Member m_3 : _member_3) {
        {
          boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
          if (_isPrim_1) {
            _builder.append("\t\t\t");
            _builder.append("stream.Read(out result.");
            String _memberName_3 = this.getMemberName(m_3);
            _builder.append(_memberName_3, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t\t");
            String _csTypeName_13 = this._cSharpTypeUtil.getCsTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
            _builder.append(_csTypeName_13, "\t\t\t");
            {
              boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
              if (_isEnum_1) {
                _builder.append("Util");
              }
            }
            _builder.append(".Read(stream, communicator, out result.");
            String _memberName_4 = this.getMemberName(m_3);
            _builder.append(_memberName_4, "\t\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t\t");
    _builder.append("byte _endFlag_;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stream.Read(out _endFlag_);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (_endFlag_ != Common.STRUCT_END)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("throw new System.Exception(\"Missing end flag\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
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
  
  public String getMemberName(final Member member) {
    if (this.opt.generateForUnity) {
      return StringExtensions.toFirstUpper(member.getName());
    }
    String _firstUpper = StringExtensions.toFirstUpper(member.getName());
    return ("m" + _firstUpper);
  }
  
  public String getPropertyName(final Member member) {
    return StringExtensions.toFirstUpper(member.getName());
  }
  
  public void generateEnum(final EnumDecl en, final IFileSystemAccess2 fsa) {
    this._cSharpTypeUtil.clearImports();
    this._cSharpTypeUtil.rememberImports("System.ArgumentException");
    this._cSharpTypeUtil.rememberImports("System.IO.BinaryWriter");
    this._cSharpTypeUtil.rememberImports("System.Collections.Generic.List");
    this._cSharpTypeUtil.rememberImports("System.Collections.IList");
    this._cSharpTypeUtil.rememberImports("Mora.Stream.IMoraOutputStream");
    this._cSharpTypeUtil.rememberImports("Mora.Stream.IMoraInputStream");
    this._cSharpTypeUtil.rememberImports("Mora.Com.Communicator");
    this._cSharpTypeUtil.rememberImports("Mora.Common");
    final CharSequence content = this.generate(en);
    final QualifiedName qn = this._cSharpTypeUtil.fullyQualifiedName(en);
    final CharSequence imports = this._cSharpTypeUtil.getImportBlock(qn);
    final String allContent = ((("\n" + imports) + "\n") + content);
    String _replace = this.opt.namespace.replace(".", "/");
    String _plus = (_replace + "/");
    String _string = this._cSharpTypeUtil.fullyQualifiedName(en).toString("/");
    String _plus_1 = (_plus + _string);
    final String fileName = (_plus_1 + ".cs");
    fsa.generateFile(fileName, allContent);
  }
  
  public CharSequence generate(final EnumDecl decl) {
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
    String _string = this._cSharpTypeUtil.fullyQualifiedName(decl).skipLast(1).toString(".");
    _builder.append(_string);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("public enum ");
    String _csTypeName = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName, "\t");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<Literal> _literals = decl.getLiterals();
      boolean _hasElements = false;
      for(final Literal l : _literals) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "\t\t");
        }
        _builder.append("\t\t");
        String _name = l.getName();
        _builder.append(_name, "\t\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class ");
    String _csTypeName_1 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_1, "\t");
    _builder.append("Util {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("public static int valueOf(");
    String _csTypeName_2 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_2, "\t\t");
    _builder.append(" v){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("switch(v) {");
    _builder.newLine();
    {
      EList<Literal> _literals_1 = decl.getLiterals();
      for(final Literal l_1 : _literals_1) {
        _builder.append("\t\t\t\t");
        _builder.append("case ");
        String _csTypeName_3 = this._cSharpTypeUtil.getCsTypeName(decl);
        _builder.append(_csTypeName_3, "\t\t\t\t");
        _builder.append(".");
        String _name_1 = l_1.getName();
        _builder.append(_name_1, "\t\t\t\t");
        _builder.append(": return ");
        int _indexOf = decl.getLiterals().indexOf(l_1);
        _builder.append(_indexOf, "\t\t\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new ArgumentException();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static ");
    String _csTypeName_4 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_4, "\t\t");
    _builder.append(" valueOf(int v){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("switch(v){");
    _builder.newLine();
    {
      EList<Literal> _literals_2 = decl.getLiterals();
      for(final Literal l_2 : _literals_2) {
        _builder.append("\t\t\t\t");
        _builder.append("case ");
        int _indexOf_1 = decl.getLiterals().indexOf(l_2);
        _builder.append(_indexOf_1, "\t\t\t\t");
        _builder.append(": return ");
        String _csTypeName_5 = this._cSharpTypeUtil.getCsTypeName(decl);
        _builder.append(_csTypeName_5, "\t\t\t\t");
        _builder.append(".");
        String _name_2 = l_2.getName();
        _builder.append(_name_2, "\t\t\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new ArgumentException();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#region serialisation");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Write(List<");
    String _csTypeName_6 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_6, "\t\t");
    _builder.append("> _in, IMoraOutputStream stream, Communicator communicator) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("stream.Write(_in.Count);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("for (int i = 0; i < _in.Count; i++)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Write(_in[i], stream, communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Write(");
    String _csTypeName_7 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_7, "\t\t");
    _builder.append(" _in, IMoraOutputStream stream, Communicator communicator) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("stream.Write(valueOf(_in));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public static void Read(IMoraInputStream stream, Communicator communicator, out List<");
    String _csTypeName_8 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_8, "\t\t");
    _builder.append("> result)");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("{");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("List<int> intValues;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("stream.Read(out intValues);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("result = new List<");
    String _csTypeName_9 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_9, "            ");
    _builder.append(">(intValues.Count);");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("for (int i = 0; i < intValues.Count; i++)");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("{");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("result.Add(valueOf(intValues[i]));");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("public static void Read(IMoraInputStream stream, Communicator communicator, out ");
    String _csTypeName_10 = this._cSharpTypeUtil.getCsTypeName(decl);
    _builder.append(_csTypeName_10, "        ");
    _builder.append(" result)");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("{");
    _builder.newLine();
    _builder.append("        \t");
    _builder.append("int intValue;");
    _builder.newLine();
    _builder.append("        \t");
    _builder.append("stream.Read(out intValue);");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("result = valueOf(intValue);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
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
}
