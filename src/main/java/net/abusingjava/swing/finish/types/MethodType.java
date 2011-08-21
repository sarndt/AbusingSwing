package net.abusingjava.swing.finish.types;

import net.abusingjava.functions.AbusingFunctions;

public class MethodType {
	final String $method;
	
	public MethodType(final String $method) {
		if (($method == null) || $method.isEmpty())
			throw new RuntimeException(); // TODO
		this.$method = $method;
	}
	
	public void call(final Object $obj, final Object... $args) {
		AbusingFunctions.callback($obj, $method).call($args);
	}
}
