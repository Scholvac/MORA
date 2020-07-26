package de.sos.generator.java

import de.sos.generator.TypeUtil
import de.sos.mORA.EnumDecl
import de.sos.mORA.Member
import de.sos.mORA.StructDecl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

class JavaStructGenerator extends AbstractGenerator {
	

	extension TypeUtil = new TypeUtil
	extension JavaTypeUtil	= new JavaTypeUtil

	
	
	override doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
		for (e : resource.allContents.filter(EnumDecl).toIterable){
			e.generateEnum(fsa);	
		}
		
		for (s : resource.allContents.filter(StructDecl).toIterable){
			s.generateStruct(fsa);
		}
	}
		
		
		
	def generateEnum(EnumDecl en, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("java.util.List");
		rememberImports("java.util.ArrayList");	
		rememberImports("java.io.IOException");
		rememberImports("de.sos.mora.types.IMoraTypeEncoder");
		rememberImports("de.sos.mora.com.Communicator");
		rememberImports("de.sos.mora.types.MoraEncoders")
		rememberImports("de.sos.mora.stream.IMoraInputStream");
		rememberImports("de.sos.mora.stream.IMoraOutputStream");
		rememberImports("de.sos.mora.Common")
		
		val content = en.generate
		
		val qn = en.fullyQualifiedName;
		val imports = getImportBlock(qn)
		val header = "package " + qn.skipLast(1).toString(".") + ";" 
		
		fsa.generateFile(en.fullyQualifiedName.toString("/")+".java", header + "\n" + imports +"\n"+ content);
//		fsa.generateFile(opt.baseDir + "/" + en.fullyQualifiedName.toString("/")+".java", content);
	}
	
	def CharSequence generate(EnumDecl s)
	'''		
		public enum «s.javaTypeName» {
			«FOR l : s.literals SEPARATOR ', '»«l.name»«ENDFOR»;
			
			public static int valueOf(final «s.javaTypeName» v){
				switch(v) {
					«FOR l: s.literals»
						case «l.name»: return «s.literals.indexOf(l)»;
					«ENDFOR»
				}
				throw new UnsupportedOperationException();
			}
			public static «s.javaTypeName» valueOf(final int v){
				switch(v){
					«FOR l : s.literals»
						case «s.literals.indexOf(l)»: return «l.name»;
					«ENDFOR»
				}
				throw new UnsupportedOperationException();
			}
							
			public static int safe_compare(final «s.javaTypeName» s1, final «s.javaTypeName» s2){
				if (s1 == null){
					if (s2 == null)
						return 0;
					else
						return 1;
				}else if (s2 == null){
					return -1;
				}
				return Integer.compare(valueOf(s1), valueOf(s2));
			}
			public static int safe_list_compare(List<«s.javaTypeName»> l1, List<«s.javaTypeName»> l2) {
				int comp = MoraEncoders.safe_compare_Lists(l1, l2);
				if (comp != 0)
					return comp;
				for (int i = 0; i < l1.size(); i++){
					comp = safe_compare(l1.get(i), l2.get(i));
					if (comp != 0)
						return comp;
				}
				return 0;
			};
			
			public static void write(final List<«s.javaTypeName»> in, IMoraOutputStream stream, Communicator communicator) throws IOException {
				stream.writeInteger(in.size());
				for (int i = 0; i < in.size(); i++){
					write(in.get(i), stream, communicator);
				}
			}
			public static void write(final «s.javaTypeName» value, IMoraOutputStream stream, Communicator communicator) throws IOException {
				stream.writeInteger(valueOf(value));
			}
			public static List<«s.javaTypeName»> readList(IMoraInputStream stream, Communicator communicator) throws IOException {
				ArrayList<«s.javaTypeName»> out = new ArrayList<>();
				int count = stream.readInteger();
				for (int i = 0; i < count; i++){
					out.add(read(stream, communicator));
				}
				return out;
			}
			public static «s.javaTypeName» read(IMoraInputStream stream, Communicator communicator) throws IOException {
				return valueOf(stream.readInteger());
			}
		}
	'''
	////////////////////////////////////////	STRUCTS		///////////////////////////////////////////////////
	
	def generateStruct(StructDecl struct, IFileSystemAccess2 fsa){
		clearImports
		
		rememberImports("java.util.List");
		rememberImports("java.util.ArrayList");
		rememberImports("java.io.IOException");	
		rememberImports("de.sos.mora.stream.IMoraInputStream");
		rememberImports("de.sos.mora.stream.IMoraOutputStream");
		rememberImports("de.sos.mora.types.IMoraTypeEncoder");
		rememberImports("de.sos.mora.com.Communicator");
		rememberImports("de.sos.mora.types.MoraEncoders")
		rememberImports("de.sos.mora.Common")
		
		val content = struct.generate
		val qn = struct.fullyQualifiedName;
		val imports = getImportBlock(qn)
		val header = "package " + qn.skipLast(1) + ";\n"
		
		fsa.generateFile(qn.toString("/")+".java", header + "\n" + imports +"\n"+ content);
	}
	
	def CharSequence generate(StructDecl s)
	'''
		public class «s.javaTypeName» {
			
			//-------------- Member ---------------------
			«FOR m : s.member»
				private «m.type.javaTypeName» 	«m.memberName»;
			«ENDFOR»
			//-------------- Constructors ---------------
			/** Default constructor */
			public «s.javaTypeName»() {
			}
			
			//-------------- Setter / Getter ----------------------
			«FOR m : s.member»
				public «m.type.javaTypeName» get«m.name.toFirstUpper»(){
					return «m.memberName»;
				}
				public void set«m.name.toFirstUpper»(«m.type.javaTypeName» _newValue_) {
					«m.memberName» = _newValue_;
				}
			«ENDFOR»
			
			
			//--------------- Compare ----------------------
			
			@Override
			public boolean equals(Object obj) {
				if (obj != null && obj instanceof «s.javaTypeName»)
					return safe_compare(this, («s.javaTypeName») obj) == 0;
				return false;
			}
			
			public static int safe_compare(final «s.javaTypeName» s1, final «s.javaTypeName» s2){
				if (s1 == null){
					if (s2 == null)
						return 0;
					else
						return 1;
				}else if (s2 == null){
					return -1;
				}
				return compare(s1, s2);
			}
			
			public static int compare(final «s.javaTypeName» s1, final «s.javaTypeName» s2){
				int result = 0;
				«FOR m : s.member»
					result = «m.compare("s1."+m.memberName, "s2."+m.memberName)»;
					if (result != 0)
						return result;
				«ENDFOR»
				return result;
			}
			
			public static int safe_list_compare(List<«s.javaTypeName»> l1, List<«s.javaTypeName»> l2) {
				int comp = Common.safe_compare_Lists(l1, l2);
				if (comp != 0)
					return comp;
				for (int i = 0; i < l1.size(); i++){
					comp = safe_compare(l1.get(i), l2.get(i));
					if (comp != 0)
						return comp;
				}
				return 0;
			};
			//--------------- Encoding / Decoding ----------------------
			
			public static void write(final List<«s.javaTypeName»> in, IMoraOutputStream stream, Communicator communicator) throws IOException {
				stream.writeInteger(in.size());
				for (int i = 0; i < in.size(); i++){
					write(in.get(i), stream, communicator);
				}
			}
			public static void write(final «s.javaTypeName» in, IMoraOutputStream stream, Communicator communicator) throws IOException {
				if (in == null){
					stream.write(Common.STRUCT_NULL); //value does not exists
					return ;
				}else{
					stream.write(Common.STRUCT_START); //value exists
				}
				«FOR m : s.member»
					«IF m.type.singleType.prim && m.type.javaTypeName.equals("String") == false»
						stream.write«m.type.singleType.primType.streamPostFix»(in.«m.memberName»);
					«ELSE»
						«IF m.type.prim»
							stream.write«m.type.primType.streamPostFix»(in.«m.memberName»);
						«ELSE»
							«m.type.singleType.javaTypeName».write(in.«m.memberName», stream, communicator);
						«ENDIF»
					«ENDIF»
				«ENDFOR»
				stream.write(Common.STRUCT_END);
			}
			
			public static List<«s.javaTypeName»> readList(IMoraInputStream stream, Communicator communicator) throws IOException {
				ArrayList<«s.javaTypeName»> out = new ArrayList<>();
				int count = stream.readInteger();
				for (int i = 0; i < count; i++){
					out.add(read(stream, communicator));
				}	
				return out;
			}
			public static «s.javaTypeName» read(IMoraInputStream stream, Communicator communicator) throws IOException {
				byte _flag_ = stream.readByte();
				if (_flag_ == Common.STRUCT_NULL)
					return null;
				assert(_flag_ == Common.STRUCT_START);
				«s.javaTypeName» out = new «s.javaTypeName»();
				«FOR m : s.member»
					«IF m.type.singleType.prim»
						out.«m.memberName» = stream.read«m.type.singleType.primType.streamPostFix»«IF m.type.many»List«ENDIF»();
					«ELSE»
						out.«m.memberName» = «m.type.singleType.javaTypeName».read«IF m.type.many»List«ENDIF»(stream, communicator);
					«ENDIF»
				«ENDFOR»
				byte _endFlag_ = stream.readByte();
				assert(_endFlag_ == Common.STRUCT_END);
				return out;	
			}
		}
	'''
	
	def String compare(Member member, String s1, String s2){
		
		if (member.type.prim){
			if (member.type.javaTypeName.equals("String") == false)
				return member.type.primType.javaObjectType + ".compare("+s1+", " +s2+")"
			else
				return "MoraEncoders.safe_compare("+s1+", "+s2+")"
		}else{
			if (member.type.many){
				if (member.type.singleType.prim)
					return "MoraEncoders.safe_compare_"+member.type.singleJavaTypeName+"List("+s1+", "+s2+")"
				else
					return member.type.singleType.javaTypeName + ".safe_list_compare("+s1 + ", " + s2 + ")" 
			}else{
				return member.type.javaTypeName + ".safe_compare("+s1 + ", " + s2 + ")"
			}
		}
	}
	
		
	
	def String getMemberName(Member member){
		return "m" + member.name.toFirstUpper
	}
	
	
}
