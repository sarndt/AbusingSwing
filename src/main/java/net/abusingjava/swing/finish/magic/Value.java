package net.abusingjava.swing.finish.magic;

import net.abusingjava.AbusingJava;

public class Value {
	int $value = -1;
	Unit $unit = Unit.AUTO;
	
	public Value(final String $declaration) {
		if (!"auto".equalsIgnoreCase($declaration)) {
			$value = AbusingJava.parseInt($declaration.replaceAll("[^0-9]", ""), 1);
			if ($declaration.endsWith("px")) {
				$unit = Unit.PIXEL;
			} else if ($declaration.endsWith("*")) {
				$unit = Unit.STAR;
			}
		}
	}
}