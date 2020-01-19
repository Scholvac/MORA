package de.sos.generator.csharp

import de.sos.generator.TypeUtil
import de.sos.mORA.StructDecl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.mwe2.language.scoping.QualifiedNameProvider
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.eclipse.xtext.naming.IQualifiedNameProvider
import de.sos.mORA.EnumDecl
import de.sos.mORA.Member
import de.sos.generator.csharp.CSharpGenerator.CSharpOptions

class CSharpStructGenerator extends AbstractGenerator {

	
	val CSharpOptions opt;
	
	extension TypeUtil = new TypeUtil();
	extension CSharpTypeUtil = new CSharpTypeUtil();
	extension IQualifiedNameProvider = new QualifiedNameProvider()
	
	new(CSharpOptions options) {
		opt = options;
	}
	
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		for (e : resource.allContents.filter(EnumDecl).toIterable){
			e.generateEnum(fsa);	
		}
		
		for (s : resource.allContents.filter(StructDecl).toIterable){
			s.generateStruct(fsa);	
		}
	}
	
	def void generateStruct(StructDecl st, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("System.IO.StreamWriter")
		rememberImports("System.Collections.IList")
		rememberImports("System.Collections.Generic.List")
		
		rememberImports("Mora.Stream.IMoraOutputStream")
		rememberImports("Mora.Stream.IMoraInputStream")
		rememberImports("Mora.Com.Communicator")
		rememberImports("Mora.Common")
		val content = st.generate
		
		val qn = st.fullyQualifiedName;
		val imports = getImportBlock(qn)
		
		val allContent = "\n" + imports +"\n"+ content;
		val fileName = opt.namespace.replace(".", "/") + "/" + st.fullyQualifiedName.toString("/")+".cs";
		fsa.generateFile(fileName, allContent);
	}
	
	def generate(StructDecl st)
	'''
		namespace «IF opt.namespace.empty==false»«opt.namespace».«ENDIF»«st.fullyQualifiedName.skipLast(1).toString(".")» {
			«IF opt.generateForUnity»[System.Serializable]«ENDIF»
			public class «st.csTypeName» {
				
				#region member
				«FOR m : st.member»
					«IF opt.generateForUnity»public«ELSE»private«ENDIF» «m.type.csTypeName» «m.memberName»;
				«ENDFOR»
				#endregion
				
				#region constructor
				public «st.csTypeName»() {
				}
				#endregion
				
				«IF opt.generateForUnity == false»
					#region properties
					«FOR m : st.member»
						public «m.type.csTypeName» «m.propertyName» {
							get { return «m.memberName»; }
							set { «m.memberName» = value; }
						} 
					«ENDFOR»
					#endregion
				«ENDIF»
				
				#region serialisation
				
				public static void Write(List<«st.csTypeName»> _in, IMoraOutputStream stream, Communicator communicator){
					stream.Write(_in.Count);
					for (int i = 0; i < _in.Count; i++)
						Write(_in[i], stream, communicator);
				}
				public static void Write(«st.csTypeName» _in, IMoraOutputStream stream, Communicator communicator){
					if (_in == null){
						stream.Write(Common.STRUCT_NULL); //value does not exists
						return ;
					}else{
						stream.Write(Common.STRUCT_START); //value exists
					}
					«FOR m : st.member»
						«IF m.type.singleType.prim»
							stream.Write(_in.«m.propertyName»);
						«ELSE»
							«IF m.type.singleType.enum»
								«m.type.singleType.csTypeName»Util.Write(_in.«m.propertyName», stream, communicator);
							«ELSE»
								«m.type.singleType.csTypeName».Write(_in.«m.propertyName», stream, communicator);
							«ENDIF»
						«ENDIF»
					«ENDFOR»
					stream.Write(Common.STRUCT_END);
				}
				
				
				public static void Read(IMoraInputStream stream, Communicator communicator, out List<«st.csTypeName»> result){
					int count;
					stream.Read(out count);
					result = new List<«st.csTypeName»>(count);
					for (int i = 0; i < count; i++){
						«st.csTypeName» value;
						Read(stream, communicator, out value);
						result.Add(value);
					}
				}
				
				public static bool Read(IMoraInputStream stream, Communicator communicator, out «st.csTypeName» result){
					byte _flag_;
					stream.Read(out _flag_);					
					if (_flag_ == Common.STRUCT_NULL){
						result = null;
						return true;
					}
					if (_flag_ != Common.STRUCT_START)
						throw new System.Exception("Missing start flag");
					result = new «st.csTypeName»();
					«FOR m : st.member»
						«IF m.type.singleType.prim»
							stream.Read(out result.«m.memberName»);
						«ELSE»
							«m.type.singleType.csTypeName»«IF m.type.singleType.enum»Util«ENDIF».Read(stream, communicator, out result.«m.memberName»);
						«ENDIF»
					«ENDFOR»
					byte _endFlag_;
					stream.Read(out _endFlag_);
					if (_endFlag_ != Common.STRUCT_END)
						throw new System.Exception("Missing end flag");
					return true;
				}
				#endregion
			}
		}
	'''
	
	
	
	def String getMemberName(Member member){
		if (opt.generateForUnity)
			return member.name.toFirstUpper;
		return "m" + member.name.toFirstUpper
	}
	
	def String getPropertyName(Member member){
		return member.name.toFirstUpper
	}
	
	def void generateEnum(EnumDecl en, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("System.ArgumentException")
		rememberImports("System.IO.BinaryWriter")
		rememberImports("System.Collections.Generic.List")
		rememberImports("System.Collections.IList")
			
		rememberImports("Mora.Stream.IMoraOutputStream")
		rememberImports("Mora.Stream.IMoraInputStream")
		rememberImports("Mora.Com.Communicator")
		rememberImports("Mora.Common")
		val content = en.generate
		
		val qn = en.fullyQualifiedName;
		val imports = getImportBlock(qn)
		
		val allContent = "\n" + imports +"\n"+ content;
		val fileName = opt.namespace.replace('.', '/') + "/" + en.fullyQualifiedName.toString("/")+".cs";
		fsa.generateFile(fileName, allContent);
	}
	
	def generate(EnumDecl decl)
	'''
		namespace «IF opt.namespace.empty==false»«opt.namespace».«ENDIF»«decl.fullyQualifiedName.skipLast(1).toString(".")» {
			public enum «decl.csTypeName» {
				«FOR l : decl.literals SEPARATOR ','»
					«l.name»
				«ENDFOR»
			}
			public static class «decl.csTypeName»Util {
				public static int valueOf(«decl.csTypeName» v){
					switch(v) {
						«FOR l: decl.literals»
							case «decl.csTypeName».«l.name»: return «decl.literals.indexOf(l)»;
						«ENDFOR»
					}
					throw new ArgumentException();
				}
				public static «decl.csTypeName» valueOf(int v){
					switch(v){
						«FOR l : decl.literals»
							case «decl.literals.indexOf(l)»: return «decl.csTypeName».«l.name»;
						«ENDFOR»
					}
					throw new ArgumentException();
				}
				
				#region serialisation
				
				public static void Write(List<«decl.csTypeName»> _in, IMoraOutputStream stream, Communicator communicator) {
					stream.Write(_in.Count);
					for (int i = 0; i < _in.Count; i++)
						Write(_in[i], stream, communicator);
				}
				public static void Write(«decl.csTypeName» _in, IMoraOutputStream stream, Communicator communicator) {
					stream.Write(valueOf(_in));
				}
				
				public static void Read(IMoraInputStream stream, Communicator communicator, out List<«decl.csTypeName»> result)
		        {
		            List<int> intValues;
		            stream.Read(out intValues);
		            result = new List<«decl.csTypeName»>(intValues.Count);
		            for (int i = 0; i < intValues.Count; i++)
		            {
		                result.Add(valueOf(intValues[i]));
		            }
		        }
		        public static void Read(IMoraInputStream stream, Communicator communicator, out «decl.csTypeName» result)
		        {
		        	int intValue;
		        	stream.Read(out intValue);
		            result = valueOf(intValue);
		        }
				#endregion
			}
		}
	'''
	
}
