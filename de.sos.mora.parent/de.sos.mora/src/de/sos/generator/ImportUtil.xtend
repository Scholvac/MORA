package de.sos.generator

import java.util.ArrayList
import java.util.Comparator
import java.util.HashSet
import org.eclipse.xtext.naming.QualifiedName

class ImportUtil {
//	extension QualifiedNameProvider = new QualifiedNameProvider() 
	HashSet<QualifiedName> mImports = new HashSet<QualifiedName>();
	
	def rememberImports(QualifiedName qn){
		mImports.add(qn);
	}
	def rememberImports(String javaStr){
		rememberImports(QualifiedName::create(javaStr.split("\\.")))
	}
	def void clearImports(){
		mImports.clear();
	}
	

	
	def filteredImports(QualifiedName[] names) {
		val ArrayList<QualifiedName> copy = new ArrayList<QualifiedName>();
		copy.addAll(mImports);
		copy.removeAll(names);
		copy.sort(new Comparator<QualifiedName>(){
			override compare(QualifiedName o1, QualifiedName o2) {
				return o1.toString.compareTo(o2.toString)
			}			
		});
		return copy;
	}
	
	def getAllImports() {
		return mImports;
	}
	def importClass(String fullQualified, String name) {
		var qn = QualifiedName::create(fullQualified.split("\\."))
		rememberImports(qn)
		return name
	}
	}
