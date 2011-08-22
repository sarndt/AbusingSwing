package net.abusingjava.swing.types;

import java.lang.reflect.Field;

public class Color {
	java.awt.Color $color;
	
	public Color(final String $color) {
		try {
			Field $f = java.awt.Color.class.getField($color);
			this.$color = (java.awt.Color) $f.get(null);
		} catch (Exception $exc) {
			this.$color = java.awt.Color.decode($color);
		}
	}

	public Color(final java.awt.Color $color) {
		this.$color = $color;
	}
	
	public java.awt.Color getColor() {
		return $color;
	}
}