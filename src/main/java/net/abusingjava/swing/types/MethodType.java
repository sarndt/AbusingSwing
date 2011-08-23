package net.abusingjava.swing.types;

import net.abusingjava.functions.AbusingFunctions;
import net.abusingjava.functions.DynamicInvocationTargetException;

public class MethodType {
	final String $method;
	
	public MethodType(final String $method) {
		if (($method == null) || $method.isEmpty())
			throw new RuntimeException(); // TODO
		this.$method = $method;
	}
	
	public void call(final Object $obj, final Object... $args) {
		try {
			AbusingFunctions.callback($obj, $method).call($args);
		} catch (DynamicInvocationTargetException $exc) {
			if ($exc.getCause() instanceof NoSuchMethodException) {
				AbusingFunctions.callback($obj, $method).call();
			} else {
				throw $exc;
			}
		}
	}
}
