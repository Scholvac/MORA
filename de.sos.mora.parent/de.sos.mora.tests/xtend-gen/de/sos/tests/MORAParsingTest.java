/**
 * generated by Xtext 2.19.0
 */
package de.sos.tests;

import com.google.inject.Inject;
import de.sos.mORA.Model;
import de.sos.tests.MORAInjectorProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InjectionExtension.class)
@InjectWith(MORAInjectorProvider.class)
@SuppressWarnings("all")
public class MORAParsingTest {
  @Inject
  private ParseHelper<Model> parseHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package serialize {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//MORA reMOte pRoxy cAll");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("enum MyEnum {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("KEY;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("VALUE;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("struct SimpleStruct {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("1: bool boolValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("2: byte byteValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("3: short shortValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("4: int intValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("5: long longValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("6: float floatValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("7: double doubleValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("8: string stringValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("9: MyEnum enumValue; ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("10:SimpleStruct structValue;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<bool> BoolList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<byte> ByteList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<short> ShortList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<int>\tIntList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<long> LongList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<float> FloatList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<double> DoubleList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<string> StringList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<MyEnum> MyEnumList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<SimpleStruct> SimpleStructList;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("struct ListStruct {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("1: BoolList boolListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("2: ByteList byteListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("3: ShortList shortListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("4: IntList\tintListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("5: LongList\tlongListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("6: FloatList floatListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("7: DoubleList doubleListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("8: StringList stringListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("9: MyEnumList enumListValue;");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("10: SimpleStructList structListValue;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("List<ListStruct> ListListStruct;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("interface EchoManager {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("bool echo(1: bool value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("byte echo(1: byte value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("short echo(1: short value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("int echo(1: int value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("long echo(1: long value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("float echo(1: float value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("double echo(1: double value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("string echo(1: string value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("MyEnum echo(1: MyEnum value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("SimpleStruct echo(1: SimpleStruct value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ListStruct echo(1: ListStruct value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("BoolList echo(1: bool value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ByteList echo(1: byte value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ShortList echo(1: short value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("IntList echo(1: int value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("LongList echo(1: long value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("FloatList echo(1: float value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("DoubleList echo(1: double value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("StringList echo(1: string value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("MyEnumList echo(1: MyEnum value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("SimpleStructList echo(1: SimpleStruct value);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ListListStruct echo(1: ListListStruct value);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append(" ");
      _builder.newLine();
      _builder.append("}   ");
      _builder.newLine();
      final Model result = this.parseHelper.parse(_builder);
      Assertions.assertNotNull(result);
      final EList<Resource.Diagnostic> errors = result.eResource().getErrors();
      boolean _isEmpty = errors.isEmpty();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Unexpected errors: ");
      String _join = IterableExtensions.join(errors, ", ");
      _builder_1.append(_join);
      Assertions.assertTrue(_isEmpty, _builder_1.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
