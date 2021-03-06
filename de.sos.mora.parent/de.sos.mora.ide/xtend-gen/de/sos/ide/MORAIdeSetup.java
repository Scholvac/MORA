/**
 * generated by Xtext 2.19.0
 */
package de.sos.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.sos.MORARuntimeModule;
import de.sos.MORAStandaloneSetup;
import de.sos.ide.MORAIdeModule;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class MORAIdeSetup extends MORAStandaloneSetup {
  @Override
  public Injector createInjector() {
    MORARuntimeModule _mORARuntimeModule = new MORARuntimeModule();
    MORAIdeModule _mORAIdeModule = new MORAIdeModule();
    return Guice.createInjector(Modules2.mixin(_mORARuntimeModule, _mORAIdeModule));
  }
}
