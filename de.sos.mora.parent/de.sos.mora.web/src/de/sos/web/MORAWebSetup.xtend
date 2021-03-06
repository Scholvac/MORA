/*
 * generated by Xtext 2.19.0
 */
package de.sos.web

import com.google.inject.Guice
import com.google.inject.Injector
import de.sos.MORARuntimeModule
import de.sos.MORAStandaloneSetup
import de.sos.ide.MORAIdeModule
import org.eclipse.xtext.util.Modules2

/**
 * Initialization support for running Xtext languages in web applications.
 */
class MORAWebSetup extends MORAStandaloneSetup {
	
	override Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new MORARuntimeModule, new MORAIdeModule, new MORAWebModule))
	}
	
}
