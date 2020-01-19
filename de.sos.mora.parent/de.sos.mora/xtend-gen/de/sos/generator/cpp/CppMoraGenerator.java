package de.sos.generator.cpp;

import com.google.common.collect.Iterators;
import de.sos.generator.TypeUtil;
import de.sos.generator.cpp.CppTypeUtil;
import de.sos.mORA.CppOptions;
import de.sos.mORA.EnumDecl;
import de.sos.mORA.Interface;
import de.sos.mORA.Literal;
import de.sos.mORA.Member;
import de.sos.mORA.Method;
import de.sos.mORA.Model;
import de.sos.mORA.Parameter;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;
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
public class CppMoraGenerator extends AbstractGenerator {
  @Extension
  private TypeUtil _typeUtil = new TypeUtil();
  
  @Extension
  private CppTypeUtil _cppTypeUtil = new CppTypeUtil();
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    final Model m = this.getModel(resource);
    this.generateHeader(m, fsa);
    this.generateSource(m, fsa);
  }
  
  public Model getModel(final Resource resource) {
    Iterable<Model> _iterable = IteratorExtensions.<Model>toIterable(Iterators.<Model>filter(resource.getAllContents(), Model.class));
    for (final Model m : _iterable) {
      return m;
    }
    return null;
  }
  
  public void generateHeader(final Model m, final IFileSystemAccess2 fsa) {
    this._cppTypeUtil.clearImports();
    this._cppTypeUtil.rememberImports("MoraGen.h");
    this._cppTypeUtil.rememberImports("memory");
    this._cppTypeUtil.rememberImports("map");
    this._cppTypeUtil.rememberImports("future");
    final CharSequence content = this.generateHeaderContent(m);
    final String headerFileName = this._cppTypeUtil.getTypesHeaderFile(m);
    String _upperCase = m.getName().toUpperCase();
    String _plus = ("#ifndef " + _upperCase);
    String _plus_1 = (_plus + "_TYPES_H_\n#define ");
    String _upperCase_1 = m.getName().toUpperCase();
    String _plus_2 = (_plus_1 + _upperCase_1);
    String allContent = (_plus_2 + "_TYPES_H_\n\n\n");
    String _firstUpper = StringExtensions.toFirstUpper(m.getName());
    String _plus_3 = (_firstUpper + "Types.h");
    CharSequence _importBlock = this._cppTypeUtil.getImportBlock(QualifiedName.create(_plus_3));
    String _plus_4 = (allContent + _importBlock);
    String _plus_5 = (_plus_4 + "\n");
    allContent = _plus_5;
    allContent = (allContent + content);
    allContent = (allContent + "#endif //");
    String _upperCase_2 = m.getName().toUpperCase();
    /* (_upperCase_2 + "_TYPES_H_"); */
    fsa.generateFile(headerFileName, allContent);
  }
  
  public void generateSource(final Model m, final IFileSystemAccess2 fsa) {
    this._cppTypeUtil.clearImports();
    this._cppTypeUtil.rememberImports("loguru.hpp");
    this._cppTypeUtil.rememberImports("future");
    this._cppTypeUtil.rememberImports(this._cppTypeUtil.getTypesHeaderFile(m));
    final CharSequence content = this.generateSourceContent(m);
    final String sourceFileName = this._cppTypeUtil.getTypesSourceFile(m);
    String _firstUpper = StringExtensions.toFirstUpper(m.getName());
    String _plus = (_firstUpper + "Types.h");
    CharSequence _importBlock = this._cppTypeUtil.getImportBlock(QualifiedName.create(_plus));
    String allContent = (_importBlock + "\n");
    allContent = (allContent + content);
    fsa.generateFile(sourceFileName, allContent);
  }
  
  public CharSequence generateSourceContent(final Model m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasNamesapce = this.hasNamesapce(m.getOptions().getCppOptions());
      if (_hasNamesapce) {
        String _beginNamespace = this.beginNamespace(m.getOptions().getCppOptions());
        _builder.append(_beginNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      boolean _isEmpty = IteratorExtensions.isEmpty(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
      boolean _equals = (_isEmpty == false);
      if (_equals) {
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("//\t\t\t\t\t\t\tEnumerations\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        {
          Iterable<EnumDecl> _iterable = IteratorExtensions.<EnumDecl>toIterable(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
          for(final EnumDecl en : _iterable) {
            _builder.append("/*******************\tEnumeration: ");
            String _name = en.getName();
            _builder.append(_name);
            _builder.append("    ********************/");
            _builder.newLineIfNotEmpty();
            CharSequence _generateEnumSourceContent = this.generateEnumSourceContent(en);
            _builder.append(_generateEnumSourceContent);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    {
      boolean _isEmpty_1 = IteratorExtensions.isEmpty(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
      boolean _equals_1 = (_isEmpty_1 == false);
      if (_equals_1) {
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("//\t\t\t\t\t\t\t\tStructs\t\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.newLine();
        {
          Iterable<StructDecl> _iterable_1 = IteratorExtensions.<StructDecl>toIterable(Iterators.<StructDecl>filter(m.eAllContents(), StructDecl.class));
          for(final StructDecl s : _iterable_1) {
            _builder.append("/********************* struct ");
            String _name_1 = s.getName();
            _builder.append(_name_1);
            _builder.append(" *********************************/");
            _builder.newLineIfNotEmpty();
            CharSequence _generateStructSourceContent = this.generateStructSourceContent(s);
            _builder.append(_generateStructSourceContent);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    {
      boolean _isEmpty_2 = IteratorExtensions.isEmpty(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
      boolean _equals_2 = (_isEmpty_2 == false);
      if (_equals_2) {
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("//\t\t\t\t\t\t\tInterfaces\t\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.newLine();
        {
          Iterable<Interface> _iterable_2 = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
          for(final Interface s_1 : _iterable_2) {
            _builder.append("/********************* interface ");
            String _name_2 = s_1.getName();
            _builder.append(_name_2);
            _builder.append(" *********************************/");
            _builder.newLineIfNotEmpty();
            CharSequence _generateInterfaceSourceContent = this.generateInterfaceSourceContent(s_1);
            _builder.append(_generateInterfaceSourceContent);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasNamesapce_1 = this.hasNamesapce(m.getOptions().getCppOptions());
      if (_hasNamesapce_1) {
        String _endNamespace = this.endNamespace(m.getOptions().getCppOptions());
        _builder.append(_endNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateEnumSourceContent(final EnumDecl en) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("::mora::int32 ");
    String _cppTypeName = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName);
    _builder.append("Util::valueOf(const ");
    String _cppTypeName_1 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_1);
    _builder.append(" value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("switch(value) {");
    _builder.newLine();
    {
      EList<Literal> _literals = en.getLiterals();
      for(final Literal l : _literals) {
        _builder.append("\t");
        _builder.append("case ");
        String _name = l.getName();
        _builder.append(_name, "\t");
        _builder.append(": return ");
        int _indexOf = en.getLiterals().indexOf(l);
        _builder.append(_indexOf, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw \"Invalid value\";");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String _cppTypeName_2 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_2);
    _builder.append(" ");
    String _cppTypeName_3 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_3);
    _builder.append("Util::valueOf(const ::mora::int32 value){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("switch(value){");
    _builder.newLine();
    {
      EList<Literal> _literals_1 = en.getLiterals();
      for(final Literal l_1 : _literals_1) {
        _builder.append("\t");
        _builder.append("case ");
        int _indexOf_1 = en.getLiterals().indexOf(l_1);
        _builder.append(_indexOf_1, "\t");
        _builder.append(": return ");
        String _name_1 = l_1.getName();
        _builder.append(_name_1, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw \"Invalid value\";");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    String _cppTypeName_4 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_4);
    _builder.append(" ");
    String _cppTypeName_5 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_5);
    _builder.append("Util::read(::mora::InputStream& stream){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 val;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> val;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return valueOf(val);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("std::vector<");
    String _cppTypeName_6 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_6);
    _builder.append("> ");
    String _cppTypeName_7 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_7);
    _builder.append("Util::readList(::mora::InputStream& stream){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 c;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> c;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("std::vector<");
    String _cppTypeName_8 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_8, "\t");
    _builder.append("> out(c);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < c; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("out[i] = read(stream);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void ");
    String _cppTypeName_9 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_9);
    _builder.append("Util::write(const ");
    String _cppTypeName_10 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_10);
    _builder.append(" value, ::mora::OutputStream& stream){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("stream << valueOf(value);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void ");
    String _cppTypeName_11 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_11);
    _builder.append("Util::write(const std::vector<");
    String _cppTypeName_12 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_12);
    _builder.append(">& value, ::mora::OutputStream& stream){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 size = (::mora::int32)value.size();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < size; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream << valueOf(value[i]);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateStructSourceContent(final StructDecl s) {
    StringConcatenation _builder = new StringConcatenation();
    String _structName = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName);
    _builder.append("::");
    String _structName_1 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_1);
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(":\t");
    {
      EList<Member> _member = s.getMember();
      boolean _hasElements = false;
      for(final Member m : _member) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _memberName = this.getMemberName(m);
        _builder.append(_memberName, "\t");
        _builder.append("(");
        String _defaultValue = this.defaultValue(this._typeUtil.getType(m));
        _builder.append(_defaultValue, "\t");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("{");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    String _structName_2 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_2);
    _builder.append("::");
    String _structName_3 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_3);
    _builder.append("(const ");
    String _structName_4 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_4);
    _builder.append("& copy)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(":\t");
    {
      EList<Member> _member_1 = s.getMember();
      boolean _hasElements_1 = false;
      for(final Member m_1 : _member_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        _builder.newLineIfNotEmpty();
        {
          boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(m_1));
          if (_isMany) {
            _builder.append("\t");
            String _memberName_1 = this.getMemberName(m_1);
            _builder.append(_memberName_1, "\t");
            _builder.append("(copy.");
            String _memberName_2 = this.getMemberName(m_1);
            _builder.append(_memberName_2, "\t");
            _builder.append(".begin(), copy.");
            String _memberName_3 = this.getMemberName(m_1);
            _builder.append(_memberName_3, "\t");
            _builder.append(".end())");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            String _memberName_4 = this.getMemberName(m_1);
            _builder.append(_memberName_4, "\t");
            _builder.append("(copy.");
            String _memberName_5 = this.getMemberName(m_1);
            _builder.append(_memberName_5, "\t");
            _builder.append(")");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("{");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String _structName_5 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_5);
    _builder.append("::~");
    String _structName_6 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_6);
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    {
      EList<Member> _member_2 = s.getMember();
      for(final Member m_2 : _member_2) {
        {
          boolean _isStruct = this._typeUtil.isStruct(this._typeUtil.getSingleType(this._typeUtil.getType(m_2)));
          if (_isStruct) {
            {
              boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(m_2));
              if (_isMany_1) {
                _builder.append("\t");
                _builder.append("for (size_t i = 0; i < ");
                String _memberName_6 = this.getMemberName(m_2);
                _builder.append(_memberName_6, "\t");
                _builder.append(".size(); i++)");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("if (");
                String _memberName_7 = this.getMemberName(m_2);
                _builder.append(_memberName_7, "\t\t");
                _builder.append("[i] != NULL)");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t\t");
                _builder.append("delete ");
                String _memberName_8 = this.getMemberName(m_2);
                _builder.append(_memberName_8, "\t\t\t");
                _builder.append("[i];");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t");
                _builder.append("if (");
                String _memberName_9 = this.getMemberName(m_2);
                _builder.append(_memberName_9, "\t");
                _builder.append(" != NULL)");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("delete ");
                String _memberName_10 = this.getMemberName(m_2);
                _builder.append(_memberName_10, "\t\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    String _structName_7 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_7);
    _builder.append("* ");
    String _structName_8 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_8);
    _builder.append("::read(::mora::InputStream& stream) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int8 flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (flag == ::mora::STRUCT_NULL)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return NULL;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("CHECK_EQ_F(flag, ::mora::STRUCT_START, \"Expecting %i (start flag) but found: %i\", ::mora::STRUCT_START, flag);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _structName_9 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_9, "\t");
    _builder.append("* out = new ");
    String _structName_10 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_10, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    {
      EList<Member> _member_3 = s.getMember();
      for(final Member m_3 : _member_3) {
        {
          boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
          if (_isPrim) {
            _builder.append("\t");
            _builder.append("stream >> out->");
            String _memberName_11 = this.getMemberName(m_3);
            _builder.append(_memberName_11, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
            if (_isEnum) {
              _builder.append("\t");
              _builder.append("out->");
              String _memberName_12 = this.getMemberName(m_3);
              _builder.append(_memberName_12, "\t");
              _builder.append(" = ");
              String _cppTypeName = this._cppTypeUtil.getCppTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_3)));
              _builder.append(_cppTypeName, "\t");
              _builder.append("Util::read");
              {
                boolean _isMany_2 = this._typeUtil.isMany(this._typeUtil.getType(m_3));
                if (_isMany_2) {
                  _builder.append("List");
                }
              }
              _builder.append("(stream);");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("out->");
              String _memberName_13 = this.getMemberName(m_3);
              _builder.append(_memberName_13, "\t");
              _builder.append(" = ");
              String _structName_11 = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(m_3))));
              _builder.append(_structName_11, "\t");
              _builder.append("::read");
              {
                boolean _isMany_3 = this._typeUtil.isMany(this._typeUtil.getType(m_3));
                if (_isMany_3) {
                  _builder.append("List");
                }
              }
              _builder.append("(stream);");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("CHECK_EQ_F(flag, ::mora::STRUCT_END, \"Expecting %i (end flag) but found: %i\", ::mora::STRUCT_END, flag);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("std::vector<");
    String _structName_12 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_12);
    _builder.append("*> ");
    String _structName_13 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_13);
    _builder.append("::readList(::mora::InputStream& stream){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 c;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> c;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("std::vector<");
    String _structName_14 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_14, "\t");
    _builder.append("*> out(c);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < c; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("out[i] = read(stream);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void ");
    String _structName_15 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_15);
    _builder.append("::write(const ");
    String _structName_16 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_16);
    _builder.append("* value, ::mora::OutputStream& stream) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (value == NULL){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream << ::mora::STRUCT_NULL;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << ::mora::STRUCT_START;");
    _builder.newLine();
    {
      EList<Member> _member_4 = s.getMember();
      for(final Member m_4 : _member_4) {
        {
          boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_4)));
          if (_isPrim_1) {
            _builder.append("\t");
            _builder.append("stream << value->");
            String _memberName_14 = this.getMemberName(m_4);
            _builder.append(_memberName_14, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_4)));
            if (_isEnum_1) {
              _builder.append("\t");
              String _singleCppTypeName = this._cppTypeUtil.getSingleCppTypeName(this._typeUtil.getType(m_4));
              _builder.append(_singleCppTypeName, "\t");
              _builder.append("Util::write(value->");
              String _memberName_15 = this.getMemberName(m_4);
              _builder.append(_memberName_15, "\t");
              _builder.append(", stream);");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              String _structName_17 = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(m_4))));
              _builder.append(_structName_17, "\t");
              _builder.append("::write(value->");
              String _memberName_16 = this.getMemberName(m_4);
              _builder.append(_memberName_16, "\t");
              _builder.append(", stream);");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("stream << ::mora::STRUCT_END;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void ");
    String _structName_18 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_18);
    _builder.append("::write(const std::vector<");
    String _structName_19 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_19);
    _builder.append("*>& value, ::mora::OutputStream& stream) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 size = (::mora::int32)value.size();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < size; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("write(value[i], stream);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateHeaderContent(final Model m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasNamesapce = this.hasNamesapce(m.getOptions().getCppOptions());
      if (_hasNamesapce) {
        String _beginNamespace = this.beginNamespace(m.getOptions().getCppOptions());
        _builder.append(_beginNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      boolean _isEmpty = IteratorExtensions.isEmpty(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
      boolean _equals = (_isEmpty == false);
      if (_equals) {
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("//\t\t\t\t\t\t\tEnumerations\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        {
          Iterable<EnumDecl> _iterable = IteratorExtensions.<EnumDecl>toIterable(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
          for(final EnumDecl en : _iterable) {
            _builder.append("\t");
            CharSequence _generateEnumHeaderContent = this.generateEnumHeaderContent(en);
            _builder.append(_generateEnumHeaderContent, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _isEmpty_1 = IteratorExtensions.isEmpty(Iterators.<EnumDecl>filter(m.eAllContents(), EnumDecl.class));
      boolean _equals_1 = (_isEmpty_1 == false);
      if (_equals_1) {
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("//\t\t\t\t\t\t\t\tStructs\t\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/********************* Pre Definition *********************************/");
        _builder.newLine();
        {
          Iterable<StructDecl> _iterable_1 = IteratorExtensions.<StructDecl>toIterable(Iterators.<StructDecl>filter(m.eAllContents(), StructDecl.class));
          for(final StructDecl s : _iterable_1) {
            _builder.append("\t");
            _builder.append("struct ");
            String _structName = this._cppTypeUtil.getStructName(s);
            _builder.append(_structName, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/********************* Full Definition *********************************/");
        _builder.newLine();
        {
          Iterable<StructDecl> _iterable_2 = IteratorExtensions.<StructDecl>toIterable(Iterators.<StructDecl>filter(m.eAllContents(), StructDecl.class));
          for(final StructDecl s_1 : _iterable_2) {
            _builder.append("\t");
            CharSequence _generateStructHeaderContent = this.generateStructHeaderContent(s_1);
            _builder.append(_generateStructHeaderContent, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      boolean _isEmpty_2 = IteratorExtensions.isEmpty(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
      boolean _equals_2 = (_isEmpty_2 == false);
      if (_equals_2) {
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("//\t\t\t\t\t\t\t\tInterfaces\t\t\t\t\t\t\t\t\t\t  //");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("////////////////////////////////////////////////////////////////////////////////");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/********************* Pre Definition *********************************/");
        _builder.newLine();
        {
          Iterable<Interface> _iterable_3 = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
          for(final Interface s_2 : _iterable_3) {
            _builder.append("\t");
            _builder.append("class ");
            String _iFaceName = this._cppTypeUtil.getIFaceName(s_2);
            _builder.append(_iFaceName, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("using ");
            String _cppTypeName = this._cppTypeUtil.cppTypeName(s_2);
            _builder.append(_cppTypeName, "\t");
            _builder.append(" = std::shared_ptr<");
            String _iFaceName_1 = this._cppTypeUtil.getIFaceName(s_2);
            _builder.append(_iFaceName_1, "\t");
            _builder.append(">;");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("class ");
            String _adapterName = this._cppTypeUtil.getAdapterName(s_2);
            _builder.append(_adapterName, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("class ");
            String _proxyName = this._cppTypeUtil.getProxyName(s_2);
            _builder.append(_proxyName, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/********************* Full Definition *********************************/");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        {
          Iterable<Interface> _iterable_4 = IteratorExtensions.<Interface>toIterable(Iterators.<Interface>filter(m.eAllContents(), Interface.class));
          for(final Interface iface : _iterable_4) {
            _builder.append("\t");
            CharSequence _generateInterfaceHeaderContent = this.generateInterfaceHeaderContent(iface);
            _builder.append(_generateInterfaceHeaderContent, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasNamesapce_1 = this.hasNamesapce(m.getOptions().getCppOptions());
      if (_hasNamesapce_1) {
        String _endNamespace = this.endNamespace(m.getOptions().getCppOptions());
        _builder.append(_endNamespace);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateInterfaceHeaderContent(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("//--------------- ");
    String _name = iface.getName();
    _builder.append(_name);
    _builder.append(" --------------------------//");
    _builder.newLineIfNotEmpty();
    CharSequence _IFaceHeader = this.IFaceHeader(iface);
    _builder.append(_IFaceHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _AdapterHeader = this.AdapterHeader(iface);
    _builder.append(_AdapterHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _ProxyHeader = this.ProxyHeader(iface);
    _builder.append(_ProxyHeader);
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence IFaceHeader(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ");
    String _iFaceName = this._cppTypeUtil.getIFaceName(iface);
    _builder.append(_iFaceName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("public:");
    _builder.newLine();
    _builder.append("\t");
    String _iFaceName_1 = this._cppTypeUtil.getIFaceName(iface);
    _builder.append(_iFaceName_1, "\t");
    _builder.append("(){}");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("virtual ~");
    String _iFaceName_2 = this._cppTypeUtil.getIFaceName(iface);
    _builder.append(_iFaceName_2, "\t");
    _builder.append("(){}");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t");
        _builder.append("virtual ");
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
        _builder.append(") = 0;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("};");
    _builder.newLine();
    return _builder;
  }
  
  public String beginNamespace(final CppOptions options) {
    final QualifiedName qn = QualifiedName.create(options.getBaseNamespace().split("::"));
    String out = "";
    List<String> _segments = qn.getSegments();
    for (final String s : _segments) {
      out = (((out + "namespace ") + s) + "{ ");
    }
    return out;
  }
  
  public String endNamespace(final CppOptions options) {
    final QualifiedName qn = QualifiedName.create(options.getBaseNamespace().split("::"));
    String out = "";
    List<String> _segments = qn.getSegments();
    for (final String s : _segments) {
      out = (((out + "} /*") + s) + "*/ ");
    }
    return out;
  }
  
  public boolean hasNamesapce(final CppOptions options) {
    return (((options != null) && (options.getBaseNamespace() != null)) && (options.getBaseNamespace().isEmpty() == false));
  }
  
  public CharSequence generateStructHeaderContent(final StructDecl s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("struct ");
    String _structName = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<Member> _member = s.getMember();
      for(final Member m : _member) {
        _builder.append("\t");
        String _cppTypeName = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName, "\t");
        _builder.append(" ");
        String _memberName = this.getMemberName(m);
        _builder.append(_memberName, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    String _structName_1 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_1, "\t");
    _builder.append("();\t\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _structName_2 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_2, "\t");
    _builder.append("(const ");
    String _structName_3 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_3, "\t");
    _builder.append("& copy);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("~");
    String _structName_4 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_4, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static ");
    String _structName_5 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_5, "\t");
    _builder.append("* read(::mora::InputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static std::vector<");
    String _structName_6 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_6, "\t");
    _builder.append("*> readList(::mora::InputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static void write(const ");
    String _structName_7 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_7, "\t");
    _builder.append("* value, ::mora::OutputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static void write(const std::vector<");
    String _structName_8 = this._cppTypeUtil.getStructName(s);
    _builder.append(_structName_8, "\t");
    _builder.append("*>& value, ::mora::OutputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public String defaultValue(final TypeDecl t) {
    boolean _isMany = this._typeUtil.isMany(t);
    if (_isMany) {
      String _cppTypeName = this._cppTypeUtil.getCppTypeName(t);
      return (_cppTypeName + "()");
    } else {
      boolean _isPointer = this._typeUtil.isPointer(t);
      if (_isPointer) {
        return "NULL";
      } else {
        boolean _isEnum = this._typeUtil.isEnum(t);
        if (_isEnum) {
          return this._typeUtil.getEnumType(t).getLiterals().get(0).getName();
        } else {
          boolean _isPrim = this._typeUtil.isPrim(t);
          if (_isPrim) {
            PrimTypeLiteral _name = this._typeUtil.getPrimType(t).getName();
            if (_name != null) {
              switch (_name) {
                case BOOL:
                  return "false";
                case BYTE:
                  return "(::mora::int8)0";
                case DOUBLE:
                  return "0.0";
                case FLOAT:
                  return "0.0f";
                case INTEGER:
                  return "0";
                case LONG:
                  return "0";
                case SHORT:
                  return "(::mora::int16)0";
                case STRING:
                  return "\"\"";
                default:
                  break;
              }
            }
          }
        }
      }
    }
    return "NULL";
  }
  
  public CharSequence generateEnumHeaderContent(final EnumDecl en) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("enum ");
    String _cppTypeName = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName);
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<Literal> _literals = en.getLiterals();
      boolean _hasElements = false;
      for(final Literal l : _literals) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t");
        }
        _builder.append("\t");
        String _name = l.getName();
        _builder.append(_name, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class ");
    String _cppTypeName_1 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_1);
    _builder.append("Util {");
    _builder.newLineIfNotEmpty();
    _builder.append("public:");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static ::mora::int32 valueOf(const ");
    String _cppTypeName_2 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_2, "\t");
    _builder.append(" value);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static ");
    String _cppTypeName_3 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_3, "\t");
    _builder.append(" valueOf(const ::mora::int32 value);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static ");
    String _cppTypeName_4 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_4, "\t");
    _builder.append(" read(::mora::InputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static std::vector<");
    String _cppTypeName_5 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_5, "\t");
    _builder.append("> readList(::mora::InputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static void write(const ");
    String _cppTypeName_6 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_6, "\t");
    _builder.append(" value, ::mora::OutputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static void write(const std::vector<");
    String _cppTypeName_7 = this._cppTypeUtil.getCppTypeName(en);
    _builder.append(_cppTypeName_7, "\t");
    _builder.append(">& value, ::mora::OutputStream& stream);");
    _builder.newLineIfNotEmpty();
    _builder.append("};");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence ProxyHeader(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ");
    String _proxyName = this._cppTypeUtil.getProxyName(iface);
    _builder.append(_proxyName);
    _builder.append(" : public ");
    String _iFaceName = this._cppTypeUtil.getIFaceName(iface);
    _builder.append(_iFaceName);
    _builder.append(", public ::mora::Proxy {");
    _builder.newLineIfNotEmpty();
    _builder.append("public:");
    _builder.newLine();
    _builder.append("\t");
    String _proxyName_1 = this._cppTypeUtil.getProxyName(iface);
    _builder.append(_proxyName_1, "\t");
    _builder.append("(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("virtual ~");
    String _proxyName_2 = this._cppTypeUtil.getProxyName(iface);
    _builder.append(_proxyName_2, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static std::shared_ptr<");
    String _proxyName_3 = this._cppTypeUtil.getProxyName(iface);
    _builder.append(_proxyName_3, "\t");
    _builder.append("> createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static ");
    String _cppTypeName = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName, "\t");
    _builder.append(" read(::mora::InputStream& stream, ::mora::Communicator* communicator);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static std::vector<");
    String _cppTypeName_1 = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName_1, "\t");
    _builder.append("> readList(::mora::InputStream& stream, ::mora::Communicator* communicator);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("public:");
    _builder.newLine();
    {
      EList<Method> _methods = iface.getMethods();
      for(final Method m : _methods) {
        _builder.append("\t");
        String _cppTypeName_2 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName_2, "\t");
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
            String _cppTypeName_3 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p));
            _builder.append(_cppTypeName_3, "\t");
            _builder.append(" ");
            String _name_1 = p.getName();
            _builder.append(_name_1, "\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("public:");
    _builder.newLine();
    {
      EList<Method> _methods_1 = iface.getMethods();
      for(final Method m_1 : _methods_1) {
        _builder.append("\t");
        _builder.append("std::future<");
        String _cppTypeName_4 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
        _builder.append(_cppTypeName_4, "\t");
        _builder.append("> async_");
        String _name_2 = m_1.getName();
        _builder.append(_name_2, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_1 = m_1.getParameters();
          boolean _hasElements_1 = false;
          for(final Parameter p_1 : _parameters_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _cppTypeName_5 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p_1));
            _builder.append(_cppTypeName_5, "\t");
            _builder.append(" ");
            String _name_3 = p_1.getName();
            _builder.append(_name_3, "\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("};");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateInterfaceSourceContent(final Interface decl) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// -------------------------- Adapter -------------------------------//");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _adapterImpl = this.adapterImpl(decl);
    _builder.append(_adapterImpl, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// -------------------------- Proxy -------------------------------//");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _proxyImpl = this.proxyImpl(decl);
    _builder.append(_proxyImpl, "\t");
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence proxyImpl(final Interface i) {
    StringConcatenation _builder = new StringConcatenation();
    String _proxyName = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName);
    _builder.append("::");
    String _proxyName_1 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_1);
    _builder.append("(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(":\t::mora::Proxy(communicator, remoteObject)");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String _proxyName_2 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_2);
    _builder.append("::~");
    String _proxyName_3 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_3);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("std::shared_ptr<");
    String _proxyName_4 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_4);
    _builder.append("> ");
    String _proxyName_5 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_5);
    _builder.append("::createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _proxyName_6 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_6, "\t");
    _builder.append("* proxy = new ");
    String _proxyName_7 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_7, "\t");
    _builder.append("(communicator, ::mora::RemoteObject::create(qualifiedAddress));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static std::string tn(\"");
    String _upperCase = i.getName().toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (proxy->checkType(tn)){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("std::shared_ptr<");
    String _proxyName_8 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_8, "\t\t");
    _builder.append("> ptr(proxy);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return ptr;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}else{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("delete proxy;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return std::shared_ptr<");
    String _proxyName_9 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_9, "\t\t");
    _builder.append(">(NULL);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    String _cppTypeName = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName);
    _builder.append(" ");
    String _proxyName_10 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_10);
    _builder.append("::read(::mora::InputStream& stream, ::mora::Communicator* communicator) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int8 flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> flag;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (flag == ::mora::STRUCT_NULL)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ");
    String _cppTypeName_1 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_1, "\t\t");
    _builder.append("(NULL);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("CHECK_EQ_F(flag, ::mora::STRUCT_START, \"Expected %i (start flag for ");
    String _name = i.getName();
    _builder.append(_name, "\t");
    _builder.append(" but found: %i\", ::mora::STRUCT_START, flag);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("std::string quid;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> quid;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("::mora::IProxyPtr ptr = communicator->getProxy(quid);");
    _builder.newLine();
    _builder.append("\t");
    String _cppTypeName_2 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_2, "\t");
    _builder.append(" proxy;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (ptr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("proxy = std::dynamic_pointer_cast<");
    String _iFaceName = this._cppTypeUtil.getIFaceName(i);
    _builder.append(_iFaceName, "\t\t");
    _builder.append(">(ptr);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (proxy == nullptr){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("proxy = createProxy(communicator, quid);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("CHECK_F(proxy != nullptr, \"Failed to create proxy of: %s\", quid.c_str());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return proxy;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("std::vector<");
    String _cppTypeName_3 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_3);
    _builder.append("> ");
    String _proxyName_11 = this._cppTypeUtil.getProxyName(i);
    _builder.append(_proxyName_11);
    _builder.append("::readList(::mora::InputStream& stream, ::mora::Communicator* communicator) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream >> size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("std::vector<");
    String _cppTypeName_4 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_4, "\t");
    _builder.append("> out(size);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < size; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("out.push_back(read(stream, communicator));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return out;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("//----------\tInterface implementation \t---------------------//");
    _builder.newLine();
    _builder.newLine();
    {
      EList<Method> _methods = i.getMethods();
      for(final Method m : _methods) {
        String _cppTypeName_5 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName_5);
        _builder.append(" ");
        String _proxyName_12 = this._cppTypeUtil.getProxyName(i);
        _builder.append(_proxyName_12);
        _builder.append("::");
        String _name_1 = m.getName();
        _builder.append(_name_1);
        _builder.append("(");
        {
          EList<Parameter> _parameters = m.getParameters();
          boolean _hasElements = false;
          for(final Parameter p : _parameters) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            String _cppTypeName_6 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p));
            _builder.append(_cppTypeName_6);
            _builder.append(" ");
            String _name_2 = p.getName();
            _builder.append(_name_2);
          }
        }
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("std::future<");
        String _cppTypeName_7 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
        _builder.append(_cppTypeName_7, "\t");
        _builder.append("> future = async_");
        String _name_3 = m.getName();
        _builder.append(_name_3, "\t");
        _builder.append("(");
        {
          EList<Parameter> _parameters_1 = m.getParameters();
          boolean _hasElements_1 = false;
          for(final Parameter p_1 : _parameters_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "\t");
            }
            String _name_4 = p_1.getName();
            _builder.append(_name_4, "\t");
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m));
          if (_isVoid) {
            _builder.append("\t");
            _builder.append("future.get();");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("return future.get();");
            _builder.newLine();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.newLine();
    {
      EList<Method> _methods_1 = i.getMethods();
      for(final Method m_1 : _methods_1) {
        _builder.append("class ");
        String _signature = this._typeUtil.getSignature(m_1);
        _builder.append(_signature);
        _builder.append("_RemoteCall : public ::mora::IRemoteMethodCall {");
        _builder.newLineIfNotEmpty();
        _builder.append("private:");
        _builder.newLine();
        {
          EList<Parameter> _parameters_2 = m_1.getParameters();
          for(final Parameter p_2 : _parameters_2) {
            _builder.append("\t");
            String _cppTypeName_8 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p_2));
            _builder.append(_cppTypeName_8, "\t");
            _builder.append("\t_");
            String _name_5 = p_2.getName();
            _builder.append(_name_5, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("std::promise<");
        String _cppTypeName_9 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
        _builder.append(_cppTypeName_9, "\t");
        _builder.append(">\tpromise;");
        _builder.newLineIfNotEmpty();
        _builder.append("public:");
        _builder.newLine();
        _builder.append("\t");
        String _signature_1 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_1, "\t");
        _builder.append("_RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod");
        {
          boolean _isEmpty = m_1.getParameters().isEmpty();
          boolean _equals = (_isEmpty == false);
          if (_equals) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_3 = m_1.getParameters();
              boolean _hasElements_2 = false;
              for(final Parameter p_3 : _parameters_3) {
                if (!_hasElements_2) {
                  _hasElements_2 = true;
                } else {
                  _builder.appendImmediate(", ", "\t");
                }
                String _cppTypeName_10 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p_3));
                _builder.append(_cppTypeName_10, "\t");
                _builder.append(" ");
                String _name_6 = p_3.getName();
                _builder.append(_name_6, "\t");
              }
            }
          }
        }
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append(":\t::mora::IRemoteMethodCall(communicator, targetMethod)");
        {
          boolean _isEmpty_1 = m_1.getParameters().isEmpty();
          boolean _equals_1 = (_isEmpty_1 == false);
          if (_equals_1) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_4 = m_1.getParameters();
              boolean _hasElements_3 = false;
              for(final Parameter p_4 : _parameters_4) {
                if (!_hasElements_3) {
                  _hasElements_3 = true;
                } else {
                  _builder.appendImmediate(", ", "\t\t");
                }
                _builder.append("_");
                String _name_7 = p_4.getName();
                _builder.append(_name_7, "\t\t");
                _builder.append("(");
                String _name_8 = p_4.getName();
                _builder.append(_name_8, "\t\t");
                _builder.append(")");
              }
            }
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("virtual ~");
        String _signature_2 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_2, "\t");
        _builder.append("_RemoteCall()");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("{");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("std::future<");
        String _cppTypeName_11 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
        _builder.append(_cppTypeName_11, "\t");
        _builder.append("> invoke() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("::mora::OutputStream& parameterStream = getParameterStream();");
        _builder.newLine();
        {
          boolean _isEmpty_2 = m_1.getParameters().isEmpty();
          boolean _equals_2 = (_isEmpty_2 == false);
          if (_equals_2) {
            _builder.append("\t\t");
            _builder.append("//Do the encoding");
            _builder.newLine();
            {
              EList<Parameter> _parameters_5 = m_1.getParameters();
              for(final Parameter p_5 : _parameters_5) {
                {
                  boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(p_5)));
                  if (_isPrim) {
                    _builder.append("\t\t");
                    _builder.append("parameterStream << _");
                    String _name_9 = p_5.getName();
                    _builder.append(_name_9, "\t\t");
                    _builder.append(";");
                    _builder.newLineIfNotEmpty();
                  } else {
                    boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(p_5)));
                    if (_isEnum) {
                      _builder.append("\t\t");
                      String _singleCppTypeName = this._cppTypeUtil.getSingleCppTypeName(this._typeUtil.getType(p_5));
                      _builder.append(_singleCppTypeName, "\t\t");
                      _builder.append("Util::write(_");
                      String _name_10 = p_5.getName();
                      _builder.append(_name_10, "\t\t");
                      _builder.append(", parameterStream);");
                      _builder.newLineIfNotEmpty();
                    } else {
                      boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(p_5)));
                      if (_isProxy) {
                        _builder.append("\t\t");
                        String _adapterName = this._cppTypeUtil.getAdapterName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(p_5))));
                        _builder.append(_adapterName, "\t\t");
                        _builder.append("::write(_");
                        String _name_11 = p_5.getName();
                        _builder.append(_name_11, "\t\t");
                        _builder.append(", parameterStream, mCommunicator);");
                        _builder.newLineIfNotEmpty();
                      } else {
                        _builder.append("\t\t");
                        String _structName = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(p_5))));
                        _builder.append(_structName, "\t\t");
                        _builder.append("::write(_");
                        String _name_12 = p_5.getName();
                        _builder.append(_name_12, "\t\t");
                        _builder.append(", parameterStream);");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
              }
            }
          }
        }
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("send();");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("return promise.get_future();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("void handleResult(::mora::InputStream& response) {");
        _builder.newLine();
        {
          boolean _isVoid_1 = this._typeUtil.isVoid(this._typeUtil.getType(m_1));
          if (_isVoid_1) {
            _builder.append("\t\t");
            _builder.append("promise.set_value();");
            _builder.newLine();
          } else {
            {
              boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
              if (_isPrim_1) {
                _builder.append("\t\t");
                String _cppTypeName_12 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
                _builder.append(_cppTypeName_12, "\t\t");
                _builder.append(" _result;");
                _builder.newLineIfNotEmpty();
                _builder.append("\t\t");
                _builder.append("response >> _result;");
                _builder.newLine();
              } else {
                boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
                if (_isEnum_1) {
                  _builder.append("\t\t");
                  String _cppTypeName_13 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
                  _builder.append(_cppTypeName_13, "\t\t");
                  _builder.append(" _result = ");
                  String _cppTypeName_14 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
                  _builder.append(_cppTypeName_14, "\t\t");
                  _builder.append("Util::read");
                  {
                    boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(m_1));
                    if (_isMany) {
                      _builder.append("List");
                    }
                  }
                  _builder.append("(response);");
                  _builder.newLineIfNotEmpty();
                } else {
                  boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m_1)));
                  if (_isProxy_1) {
                    _builder.append("\t\t");
                    String _cppTypeName_15 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
                    _builder.append(_cppTypeName_15, "\t\t");
                    _builder.append(" _result = ");
                    String _proxyName_13 = this._cppTypeUtil.getProxyName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m_1))));
                    _builder.append(_proxyName_13, "\t\t");
                    _builder.append("::read");
                    {
                      boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(m_1));
                      if (_isMany_1) {
                        _builder.append("List");
                      }
                    }
                    _builder.append("(response, mCommunicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t\t");
                    String _cppTypeName_16 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
                    _builder.append(_cppTypeName_16, "\t\t");
                    _builder.append(" _result = ");
                    String _structName_1 = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(m_1))));
                    _builder.append(_structName_1, "\t\t");
                    _builder.append("::read");
                    {
                      boolean _isMany_2 = this._typeUtil.isMany(this._typeUtil.getType(m_1));
                      if (_isMany_2) {
                        _builder.append("List");
                      }
                    }
                    _builder.append("(response);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
            _builder.append("\t\t");
            _builder.append("promise.set_value(_result);");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("};");
        _builder.newLine();
        _builder.append("std::future<");
        String _cppTypeName_17 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m_1));
        _builder.append(_cppTypeName_17);
        _builder.append("> ");
        String _proxyName_14 = this._cppTypeUtil.getProxyName(i);
        _builder.append(_proxyName_14);
        _builder.append("::async_");
        String _name_13 = m_1.getName();
        _builder.append(_name_13);
        _builder.append("(");
        {
          EList<Parameter> _parameters_6 = m_1.getParameters();
          boolean _hasElements_4 = false;
          for(final Parameter p_6 : _parameters_6) {
            if (!_hasElements_4) {
              _hasElements_4 = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            String _cppTypeName_18 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(p_6));
            _builder.append(_cppTypeName_18);
            _builder.append(" ");
            String _name_14 = p_6.getName();
            _builder.append(_name_14);
          }
        }
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        String _signature_3 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_3, "\t");
        _builder.append("_RemoteCall* call = new ");
        String _signature_4 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_4, "\t");
        _builder.append("_RemoteCall(getCommunicator(), getMethod(\"");
        String _signature_5 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_5, "\t");
        _builder.append("\")");
        {
          boolean _isEmpty_3 = m_1.getParameters().isEmpty();
          boolean _equals_3 = (_isEmpty_3 == false);
          if (_equals_3) {
            _builder.append(", ");
            {
              EList<Parameter> _parameters_7 = m_1.getParameters();
              boolean _hasElements_5 = false;
              for(final Parameter p_7 : _parameters_7) {
                if (!_hasElements_5) {
                  _hasElements_5 = true;
                } else {
                  _builder.appendImmediate(", ", "\t");
                }
                String _name_15 = p_7.getName();
                _builder.append(_name_15, "\t");
              }
            }
          }
        }
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("//the call will be removed by the communicator, after receiving the response");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("return call->invoke();");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence AdapterHeader(final Interface iface) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class ");
    String _adapterName = this._cppTypeUtil.getAdapterName(iface);
    _builder.append(_adapterName);
    _builder.append(" : public ::mora::Adapter<");
    String _iFaceName = this._cppTypeUtil.getIFaceName(iface);
    _builder.append(_iFaceName);
    _builder.append("> {");
    _builder.newLineIfNotEmpty();
    _builder.append("private:");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static InvokerFunctionMap\tsInvokerMap;");
    _builder.newLine();
    _builder.append("public:");
    _builder.newLine();
    _builder.append("\t");
    String _adapterName_1 = this._cppTypeUtil.getAdapterName(iface);
    _builder.append(_adapterName_1, "\t");
    _builder.append("(::mora::Communicator* communicator, ");
    String _cppTypeName = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName, "\t");
    _builder.append(" iface, const std::string& identifier);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("virtual ~");
    String _adapterName_2 = this._cppTypeUtil.getAdapterName(iface);
    _builder.append(_adapterName_2, "\t");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("inline InvokerFunctionMap& getInvokerFunctionMap() { return sInvokerMap; }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("virtual const std::string getTypeIdentifier() const { return std::string(\"");
    String _upperCase = iface.getName().toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("\"); }");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static ::mora::IAdapterPtr createAdapter(::mora::Communicator* communicator, ");
    String _cppTypeName_1 = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName_1, "\t");
    _builder.append(" iface, const std::string& identifier);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static void write(");
    String _cppTypeName_2 = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName_2, "\t");
    _builder.append(" value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static void write(const std::vector<");
    String _cppTypeName_3 = this._cppTypeUtil.cppTypeName(iface);
    _builder.append(_cppTypeName_3, "\t");
    _builder.append(">& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);");
    _builder.newLineIfNotEmpty();
    _builder.append("};");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence adapterImpl(final Interface i) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("static void _invoke_");
    String _name = i.getName();
    _builder.append(_name);
    _builder.append("__getType_(");
    String _iFaceName = this._cppTypeUtil.getIFaceName(i);
    _builder.append(_iFaceName);
    _builder.append("* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static std::string tn(\"");
    String _upperCase = i.getName().toUpperCase();
    _builder.append(_upperCase, "\t");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("os << tn;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    {
      EList<Method> _methods = i.getMethods();
      for(final Method m : _methods) {
        _builder.append("static void _invoke_");
        String _name_1 = i.getName();
        _builder.append(_name_1);
        _builder.append("_");
        String _signature = this._typeUtil.getSignature(m);
        _builder.append(_signature);
        _builder.append("(");
        String _iFaceName_1 = this._cppTypeUtil.getIFaceName(i);
        _builder.append(_iFaceName_1);
        _builder.append("* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){");
        _builder.newLineIfNotEmpty();
        {
          EList<Parameter> _parameters = m.getParameters();
          for(final Parameter ip : _parameters) {
            {
              boolean _isPrim = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(ip)));
              if (_isPrim) {
                _builder.append("\t");
                String _cppTypeName = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(ip));
                _builder.append(_cppTypeName, "\t");
                _builder.append(" _");
                String _name_2 = ip.getName();
                _builder.append(_name_2, "\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("is >> _");
                String _name_3 = ip.getName();
                _builder.append(_name_3, "\t");
                _builder.append(";");
                _builder.newLineIfNotEmpty();
              } else {
                boolean _isEnum = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(ip)));
                if (_isEnum) {
                  _builder.append("\t");
                  String _cppTypeName_1 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(ip));
                  _builder.append(_cppTypeName_1, "\t");
                  _builder.append(" _");
                  String _name_4 = ip.getName();
                  _builder.append(_name_4, "\t");
                  _builder.append(" = ");
                  String _cppTypeName_2 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(ip)));
                  _builder.append(_cppTypeName_2, "\t");
                  _builder.append("Util::read");
                  {
                    boolean _isMany = this._typeUtil.isMany(this._typeUtil.getType(ip));
                    if (_isMany) {
                      _builder.append("List");
                    }
                  }
                  _builder.append("(is);");
                  _builder.newLineIfNotEmpty();
                } else {
                  boolean _isProxy = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(ip)));
                  if (_isProxy) {
                    _builder.append("\t");
                    String _cppTypeName_3 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(ip));
                    _builder.append(_cppTypeName_3, "\t");
                    _builder.append(" _");
                    String _name_5 = ip.getName();
                    _builder.append(_name_5, "\t");
                    _builder.append(" = ");
                    String _proxyName = this._cppTypeUtil.getProxyName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(ip))));
                    _builder.append(_proxyName, "\t");
                    _builder.append("::read");
                    {
                      boolean _isMany_1 = this._typeUtil.isMany(this._typeUtil.getType(ip));
                      if (_isMany_1) {
                        _builder.append("List");
                      }
                    }
                    _builder.append("(is, communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    String _cppTypeName_4 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(ip));
                    _builder.append(_cppTypeName_4, "\t");
                    _builder.append(" _");
                    String _name_6 = ip.getName();
                    _builder.append(_name_6, "\t");
                    _builder.append(" = ");
                    String _structName = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(ip))));
                    _builder.append(_structName, "\t");
                    _builder.append("::read");
                    {
                      boolean _isMany_2 = this._typeUtil.isMany(this._typeUtil.getType(ip));
                      if (_isMany_2) {
                        _builder.append("List");
                      }
                    }
                    _builder.append("(is);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.newLine();
        {
          boolean _isVoid = this._typeUtil.isVoid(this._typeUtil.getType(m));
          if (_isVoid) {
            _builder.append("\t");
            _builder.append("delegate->");
            String _name_7 = m.getName();
            _builder.append(_name_7, "\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_1 = m.getParameters();
              boolean _hasElements = false;
              for(final Parameter p : _parameters_1) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "\t");
                }
                _builder.append("_");
                String _name_8 = p.getName();
                _builder.append(_name_8, "\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            String _cppTypeName_5 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getType(m));
            _builder.append(_cppTypeName_5, "\t");
            _builder.append(" _result = delegate->");
            String _name_9 = m.getName();
            _builder.append(_name_9, "\t");
            _builder.append("(");
            {
              EList<Parameter> _parameters_2 = m.getParameters();
              boolean _hasElements_1 = false;
              for(final Parameter p_1 : _parameters_2) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "\t");
                }
                _builder.append("_");
                String _name_10 = p_1.getName();
                _builder.append(_name_10, "\t");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.newLine();
            {
              boolean _isPrim_1 = this._typeUtil.isPrim(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
              if (_isPrim_1) {
                _builder.append("\t");
                _builder.append("os << _result;");
                _builder.newLine();
              } else {
                boolean _isEnum_1 = this._typeUtil.isEnum(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                if (_isEnum_1) {
                  _builder.append("\t");
                  String _cppTypeName_6 = this._cppTypeUtil.getCppTypeName(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                  _builder.append(_cppTypeName_6, "\t");
                  _builder.append("Util::write(_result, os);");
                  _builder.newLineIfNotEmpty();
                } else {
                  boolean _isProxy_1 = this._typeUtil.isProxy(this._typeUtil.getSingleType(this._typeUtil.getType(m)));
                  if (_isProxy_1) {
                    _builder.append("\t");
                    String _adapterName = this._cppTypeUtil.getAdapterName(this._typeUtil.getProxyType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                    _builder.append(_adapterName, "\t");
                    _builder.append("::write(_result, os, communicator);");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    String _structName_1 = this._cppTypeUtil.getStructName(this._typeUtil.getStructType(this._typeUtil.getSingleType(this._typeUtil.getType(m))));
                    _builder.append(_structName_1, "\t");
                    _builder.append("::write(_result, os);");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("\t");
        _builder.newLine();
        {
          EList<Parameter> _parameters_3 = m.getParameters();
          for(final Parameter ip_1 : _parameters_3) {
            {
              boolean _isStruct = this._typeUtil.isStruct(this._typeUtil.getSingleType(this._typeUtil.getType(ip_1)));
              if (_isStruct) {
                {
                  boolean _isMany_3 = this._typeUtil.isMany(this._typeUtil.getType(ip_1));
                  if (_isMany_3) {
                    _builder.append("\t");
                    _builder.append("for (size_t _i_ = 0; _i_ < _");
                    String _name_11 = ip_1.getName();
                    _builder.append(_name_11, "\t");
                    _builder.append(".size(); ++_i_)");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("if (_");
                    String _name_12 = ip_1.getName();
                    _builder.append(_name_12, "\t\t");
                    _builder.append("[_i_] != NULL)");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t\t");
                    _builder.append("delete _");
                    String _name_13 = ip_1.getName();
                    _builder.append(_name_13, "\t\t\t");
                    _builder.append("[_i_];");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    _builder.append("if (_");
                    String _name_14 = ip_1.getName();
                    _builder.append(_name_14, "\t");
                    _builder.append(" != NULL)");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("delete _");
                    String _name_15 = ip_1.getName();
                    _builder.append(_name_15, "\t\t");
                    _builder.append(";");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.newLine();
    String _adapterName_1 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_1);
    _builder.append("::InvokerFunctionMap create");
    String _firstUpper = StringExtensions.toFirstUpper(i.getName());
    _builder.append(_firstUpper);
    _builder.append("InvokerMap()");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    String _adapterName_2 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_2, "\t");
    _builder.append("::InvokerFunctionMap im;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("im.insert(std::make_pair(\"_getType_\", &_invoke_");
    String _name_16 = i.getName();
    _builder.append(_name_16, "\t");
    _builder.append("__getType_));");
    _builder.newLineIfNotEmpty();
    {
      EList<Method> _methods_1 = i.getMethods();
      for(final Method m_1 : _methods_1) {
        _builder.append("\t");
        _builder.append("im.insert(std::make_pair(\"");
        String _signature_1 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_1, "\t");
        _builder.append("\", &_invoke_");
        String _name_17 = i.getName();
        _builder.append(_name_17, "\t");
        _builder.append("_");
        String _signature_2 = this._typeUtil.getSignature(m_1);
        _builder.append(_signature_2, "\t");
        _builder.append("));");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("return im;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    String _adapterName_3 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_3);
    _builder.append("::InvokerFunctionMap ");
    String _adapterName_4 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_4);
    _builder.append("::sInvokerMap = create");
    String _firstUpper_1 = StringExtensions.toFirstUpper(i.getName());
    _builder.append(_firstUpper_1);
    _builder.append("InvokerMap();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    String _adapterName_5 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_5);
    _builder.append("::");
    String _adapterName_6 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_6);
    _builder.append("(::mora::Communicator* communicator, ");
    String _cppTypeName_7 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_7);
    _builder.append(" iface, const std::string& identifier)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append(":\t::mora::Adapter<");
    String _iFaceName_2 = this._cppTypeUtil.getIFaceName(i);
    _builder.append(_iFaceName_2, "\t");
    _builder.append(">(communicator, iface, identifier)");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    String _adapterName_7 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_7);
    _builder.append("::~");
    String _adapterName_8 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_8);
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("::mora::IAdapterPtr ");
    String _adapterName_9 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_9);
    _builder.append("::createAdapter(::mora::Communicator* communicator, ");
    String _cppTypeName_8 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_8);
    _builder.append(" iface, const std::string& identifier)");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return communicator->registerAdapter(::mora::IAdapterPtr(new ");
    String _adapterName_10 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_10, "\t");
    _builder.append("(communicator, iface, identifier)));");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void ");
    String _adapterName_11 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_11);
    _builder.append("::write(");
    String _cppTypeName_9 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_9);
    _builder.append(" value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (!value){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("stream << ::mora::STRUCT_NULL;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return ;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << ::mora::STRUCT_START;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("::mora::IAdapterPtr adapter = communicator->getAdapter(value);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (!adapter){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("adapter = createAdapter(communicator, value, ::mora::MoraUtils::createRandomIdentifier());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("CHECK_F(adapter != nullptr, \"Failed to create Adapter\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << adapter->getQualifiedIdentifier();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void ");
    String _adapterName_12 = this._cppTypeUtil.getAdapterName(i);
    _builder.append(_adapterName_12);
    _builder.append("::write(const std::vector<");
    String _cppTypeName_10 = this._cppTypeUtil.cppTypeName(i);
    _builder.append(_cppTypeName_10);
    _builder.append(">& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("::mora::int32 size = (::mora::int32)value.size();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("stream << size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for (int i = 0; i < size; i++)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("write(value[i], stream, communicator);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String getMemberName(final Member member) {
    return member.getName();
  }
}
