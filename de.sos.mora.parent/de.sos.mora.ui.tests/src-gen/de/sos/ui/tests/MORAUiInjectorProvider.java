/*
 * generated by Xtext 2.19.0
 */
package de.sos.ui.tests;

import com.google.inject.Injector;
import de.sos.mora.ui.internal.MoraActivator;
import org.eclipse.xtext.testing.IInjectorProvider;

public class MORAUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return MoraActivator.getInstance().getInjector("de.sos.MORA");
	}

}
