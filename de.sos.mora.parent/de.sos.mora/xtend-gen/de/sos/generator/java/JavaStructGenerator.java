package de.sos.generator.java;

import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.java.JavaTypeUtil;
import de.sos.mORA.EnumDecl;
import de.sos.mORA.Literal;
import de.sos.mORA.Member;
import de.sos.mORA.StructDecl;
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
public class JavaStructGenerator extends AbstractGenerator {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private JavaTypeUtil _javaTypeUtil = new JavaTypeUtil();
  
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
  
  public void generateEnum(final EnumDecl en, final IFileSystemAccess2 fsa) {
    this._javaTypeUtil.clearImports();
    this._javaTypeUtil.rememberImports("java.util.List");
    this._javaTypeUtil.rememberImports("java.util.ArrayList");
    this._javaTypeUtil.rememberImports("java.io.IOException");
    this._javaTypeUtil.rememberImports("de.sos.mora.types.IMoraTypeEncoder");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.Communicator");
    this._javaTypeUtil.rememberImports("de.sos.mora.types.MoraEncoders");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraInputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraOutputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.Common");
    final CharSequence content = this.generate(en);
    final QualifiedName qn = this._javaTypeUtil.fullyQualifiedName(en);
    final CharSequence imports = this._javaTypeUtil.getImportBlock(qn);
    String _string = qn.skipLast(1).toString(".");
    String _plus = ("package " + _string);
    final String header = (_plus + ";");
    String _string_1 = this._javaTypeUtil.fullyQualifiedName(en).toString("/");
    String _plus_1 = (_string_1 + ".java");
    fsa.generateFile(_plus_1, ((((header + "\n") + imports) + "\n") + content));
  }
  
  public CharSequence generate(final EnumDecl s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public enum ");
    String _javaTypeName = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      EList<Literal> _literals = s.getLiterals();
      boolean _hasElements = false;
      for(final Literal l : _literals) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        String _name = l.getName();
        _builder.append(_name, "\t");
      }
    }
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int valueOf(final ");
    String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_1, "\t");
    _builder.append(" v){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("switch(v) {");
    _builder.newLine();
    {
      EList<Literal> _literals_1 = s.getLiterals();
      for(final Literal l_1 : _literals_1) {
        _builder.append("\t\t\t");
        _builder.append("case ");
        String _name_1 = l_1.getName();
        _builder.append(_name_1, "\t\t\t");
        _builder.append(": return ");
        int _indexOf = s.getLiterals().indexOf(l_1);
        _builder.append(_indexOf, "\t\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    String _javaTypeName_2 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_2, "\t");
    _builder.append(" valueOf(final int v){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("switch(v){");
    _builder.newLine();
    {
      EList<Literal> _literals_2 = s.getLiterals();
      for(final Literal l_2 : _literals_2) {
        _builder.append("\t\t\t");
        _builder.append("case ");
        int _indexOf_1 = s.getLiterals().indexOf(l_2);
        _builder.append(_indexOf_1, "\t\t\t");
        _builder.append(": return ");
        String _name_2 = l_2.getName();
        _builder.append(_name_2, "\t\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int safe_compare(final ");
    String _javaTypeName_3 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_3, "\t");
    _builder.append(" s1, final ");
    String _javaTypeName_4 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_4, "\t");
    _builder.append(" s2){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if (s1 == null){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (s2 == null)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("else");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return 1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}else if (s2 == null){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return Integer.compare(valueOf(s1), valueOf(s2));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int safe_list_compare(List<");
    String _javaTypeName_5 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_5, "\t");
    _builder.append("> l1, List<");
    String _javaTypeName_6 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_6, "\t");
    _builder.append("> l2) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("int comp = MoraEncoders.safe_compare_Lists(l1, l2);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (comp != 0)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return comp;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < l1.size(); i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("comp = safe_compare(l1.get(i), l2.get(i));");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (comp != 0)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return comp;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void write(final List<");
    String _javaTypeName_7 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_7, "\t");
    _builder.append("> in, IMoraOutputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("stream.writeInteger(in.size());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < in.size(); i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("write(in.get(i), stream, communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void write(final ");
    String _javaTypeName_8 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_8, "\t");
    _builder.append(" value, IMoraOutputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("stream.writeInteger(valueOf(value));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static List<");
    String _javaTypeName_9 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_9, "\t");
    _builder.append("> readList(IMoraInputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("ArrayList<");
    String _javaTypeName_10 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_10, "\t\t");
    _builder.append("> out = new ArrayList<>();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("int count = stream.readInteger();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < count; i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("out.add(read(stream, communicator));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    String _javaTypeName_11 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_11, "\t");
    _builder.append(" read(IMoraInputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return valueOf(stream.readInteger());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public void generateStruct(final StructDecl struct, final IFileSystemAccess2 fsa) {
    this._javaTypeUtil.clearImports();
    this._javaTypeUtil.rememberImports("java.util.List");
    this._javaTypeUtil.rememberImports("java.util.ArrayList");
    this._javaTypeUtil.rememberImports("java.io.IOException");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraInputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.stream.IMoraOutputStream");
    this._javaTypeUtil.rememberImports("de.sos.mora.types.IMoraTypeEncoder");
    this._javaTypeUtil.rememberImports("de.sos.mora.com.Communicator");
    this._javaTypeUtil.rememberImports("de.sos.mora.types.MoraEncoders");
    this._javaTypeUtil.rememberImports("de.sos.mora.Common");
    final CharSequence content = this.generate(struct);
    final QualifiedName qn = this._javaTypeUtil.fullyQualifiedName(struct);
    final CharSequence imports = this._javaTypeUtil.getImportBlock(qn);
    QualifiedName _skipLast = qn.skipLast(1);
    String _plus = ("package " + _skipLast);
    final String header = (_plus + ";\n");
    String _string = qn.toString("/");
    String _plus_1 = (_string + ".java");
    fsa.generateFile(_plus_1, ((((header + "\n") + imports) + "\n") + content));
  }
  
  public CharSequence generate(final StructDecl s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public class ");
    String _javaTypeName = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//-------------- Member ---------------------");
    _builder.newLine();
    {
      EList<Member> _member = s.getMember();
      for(final Member m : _member) {
        _builder.append("\t");
        _builder.append("private ");
        String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m));
        _builder.append(_javaTypeName_1, "\t");
        _builder.append(" \t");
        String _memberName = this.getMemberName(m);
        _builder.append(_memberName, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("//-------------- Constructors ---------------");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Default constructor */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _javaTypeName_2 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_2, "\t");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//-------------- Setter / Getter ----------------------");
    _builder.newLine();
    {
      EList<Member> _member_1 = s.getMember();
      for(final Member m_1 : _member_1) {
        _builder.append("\t");
        _builder.append("public ");
        String _javaTypeName_3 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m_1));
        _builder.append(_javaTypeName_3, "\t");
        _builder.append(" get");
        String _firstUpper = StringExtensions.toFirstUpper(m_1.getName());
        _builder.append(_firstUpper, "\t");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _memberName_1 = this.getMemberName(m_1);
        _builder.append(_memberName_1, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void set");
        String _firstUpper_1 = StringExtensions.toFirstUpper(m_1.getName());
        _builder.append(_firstUpper_1, "\t");
        _builder.append("(");
        String _javaTypeName_4 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m_1));
        _builder.append(_javaTypeName_4, "\t");
        _builder.append(" _newValue_) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        String _memberName_2 = this.getMemberName(m_1);
        _builder.append(_memberName_2, "\t\t");
        _builder.append(" = _newValue_;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//--------------- Compare ----------------------");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean equals(Object obj) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (obj != null && obj instanceof ");
    String _javaTypeName_5 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_5, "\t\t");
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("return safe_compare(this, (");
    String _javaTypeName_6 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_6, "\t\t\t");
    _builder.append(") obj) == 0;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int safe_compare(final ");
    String _javaTypeName_7 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_7, "\t");
    _builder.append(" s1, final ");
    String _javaTypeName_8 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_8, "\t");
    _builder.append(" s2){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if (s1 == null){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (s2 == null)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("else");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return 1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}else if (s2 == null){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return -1;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return compare(s1, s2);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int compare(final ");
    String _javaTypeName_9 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_9, "\t");
    _builder.append(" s1, final ");
    String _javaTypeName_10 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_10, "\t");
    _builder.append(" s2){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("int result = 0;");
    _builder.newLine();
    {
      EList<Member> _member_2 = s.getMember();
      for(final Member m_2 : _member_2) {
        _builder.append("\t\t");
        _builder.append("result = ");
        String _memberName_3 = this.getMemberName(m_2);
        String _plus = ("s1." + _memberName_3);
        String _memberName_4 = this.getMemberName(m_2);
        String _plus_1 = ("s2." + _memberName_4);
        String _compare = this.compare(m_2, _plus, _plus_1);
        _builder.append(_compare, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("if (result != 0)");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("\t");
        _builder.append("return result;");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("return result;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static int safe_list_compare(List<");
    String _javaTypeName_11 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_11, "\t");
    _builder.append("> l1, List<");
    String _javaTypeName_12 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_12, "\t");
    _builder.append("> l2) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("int comp = Common.safe_compare_Lists(l1, l2);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (comp != 0)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return comp;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < l1.size(); i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("comp = safe_compare(l1.get(i), l2.get(i));");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (comp != 0)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return comp;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//--------------- Encoding / Decoding ----------------------");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void write(final List<");
    String _javaTypeName_13 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_13, "\t");
    _builder.append("> in, IMoraOutputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("stream.writeInteger(in.size());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < in.size(); i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("write(in.get(i), stream, communicator);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void write(final ");
    String _javaTypeName_14 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_14, "\t");
    _builder.append(" in, IMoraOutputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if (in == null){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stream.write(Common.STRUCT_NULL); //value does not exists");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return ;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}else{");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("stream.write(Common.STRUCT_START); //value exists");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    {
      EList<Member> _member_3 = s.getMember();
      for(final Member m_3 : _member_3) {
        {
          if ((this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_3))) && (this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(m_3)).equals("String") == false))) {
            _builder.append("\t\t");
            _builder.append("stream.write");
            String _streamPostFix = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(m_3))));
            _builder.append(_streamPostFix, "\t\t");
            _builder.append("(in.");
            String _memberName_5 = this.getMemberName(m_3);
            _builder.append(_memberName_5, "\t\t");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            {
              boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getType(m_3));
              if (_isPrim) {
                _builder.append("\t\t");
                _builder.append("stream.write");
                String _streamPostFix_1 = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getType(m_3)));
                _builder.append(_streamPostFix_1, "\t\t");
                _builder.append("(in.");
                String _memberName_6 = this.getMemberName(m_3);
                _builder.append(_memberName_6, "\t\t");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                String _javaTypeName_15 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
                _builder.append(_javaTypeName_15, "\t\t");
                _builder.append(".write(in.");
                String _memberName_7 = this.getMemberName(m_3);
                _builder.append(_memberName_7, "\t\t");
                _builder.append(", stream, communicator);");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("stream.write(Common.STRUCT_END);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static List<");
    String _javaTypeName_16 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_16, "\t");
    _builder.append("> readList(IMoraInputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("ArrayList<");
    String _javaTypeName_17 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_17, "\t\t");
    _builder.append("> out = new ArrayList<>();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("int count = stream.readInteger();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("for (int i = 0; i < count; i++){");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("out.add(read(stream, communicator));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    String _javaTypeName_18 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_18, "\t");
    _builder.append(" read(IMoraInputStream stream, Communicator communicator) throws IOException {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("byte _flag_ = stream.readByte();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (_flag_ == Common.STRUCT_NULL)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("assert(_flag_ == Common.STRUCT_START);");
    _builder.newLine();
    _builder.append("\t\t");
    String _javaTypeName_19 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_19, "\t\t");
    _builder.append(" out = new ");
    String _javaTypeName_20 = this._javaTypeUtil.getJavaTypeName(s);
    _builder.append(_javaTypeName_20, "\t\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    {
      EList<Member> _member_4 = s.getMember();
      for(final Member m_4 : _member_4) {
        {
          boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_4)));
          if (_isPrim_1) {
            _builder.append("\t\t");
            _builder.append("out.");
            String _memberName_8 = this.getMemberName(m_4);
            _builder.append(_memberName_8, "\t\t");
            _builder.append(" = stream.read");
            String _streamPostFix_2 = this._javaTypeUtil.getStreamPostFix(this._typeUtil.getPrimType(this._typeUtil.getSingleType(this._typeUtil.getType(m_4))));
            _builder.append(_streamPostFix_2, "\t\t");
            {
              boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(m_4));
              if (_isMany) {
                _builder.append("List");
              }
            }
            _builder.append("();");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            _builder.append("out.");
            String _memberName_9 = this.getMemberName(m_4);
            _builder.append(_memberName_9, "\t\t");
            _builder.append(" = ");
            String _javaTypeName_21 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_4)));
            _builder.append(_javaTypeName_21, "\t\t");
            _builder.append(".read");
            {
              boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(m_4));
              if (_isMany_1) {
                _builder.append("List");
              }
            }
            _builder.append("(stream, communicator);");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.append("byte _endFlag_ = stream.readByte();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("assert(_endFlag_ == Common.STRUCT_END);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return out;\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String compare(final Member member, final String s1, final String s2) {
    boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getType(member));
    if (_isPrim) {
      boolean _equals = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(member)).equals("String");
      boolean _equals_1 = (_equals == false);
      if (_equals_1) {
        String _javaObjectType = this._javaTypeUtil.getJavaObjectType(this._typeUtil.getPrimType(this._typeUtil.getType(member)));
        String _plus = (_javaObjectType + ".compare(");
        String _plus_1 = (_plus + s1);
        String _plus_2 = (_plus_1 + ", ");
        String _plus_3 = (_plus_2 + s2);
        return (_plus_3 + ")");
      } else {
        return (((("MoraEncoders.safe_compare(" + s1) + ", ") + s2) + ")");
      }
    } else {
      boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(member));
      if (_isMany) {
        boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(member)));
        if (_isPrim_1) {
          String _singleJavaTypeName = this._javaTypeUtil.getSingleJavaTypeName(this._typeUtil.getType(member));
          String _plus_4 = ("MoraEncoders.safe_compare_" + _singleJavaTypeName);
          String _plus_5 = (_plus_4 + "List(");
          String _plus_6 = (_plus_5 + s1);
          String _plus_7 = (_plus_6 + ", ");
          String _plus_8 = (_plus_7 + s2);
          return (_plus_8 + ")");
        } else {
          String _javaTypeName = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(member)));
          String _plus_9 = (_javaTypeName + ".safe_list_compare(");
          String _plus_10 = (_plus_9 + s1);
          String _plus_11 = (_plus_10 + ", ");
          String _plus_12 = (_plus_11 + s2);
          return (_plus_12 + ")");
        }
      } else {
        String _javaTypeName_1 = this._javaTypeUtil.getJavaTypeName(this._typeUtil.getType(member));
        String _plus_13 = (_javaTypeName_1 + ".safe_compare(");
        String _plus_14 = (_plus_13 + s1);
        String _plus_15 = (_plus_14 + ", ");
        String _plus_16 = (_plus_15 + s2);
        return (_plus_16 + ")");
      }
    }
  }
  
  public String getMemberName(final Member member) {
    String _firstUpper = StringExtensions.toFirstUpper(member.getName());
    return ("m" + _firstUpper);
  }
}
