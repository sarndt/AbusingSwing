package net.abusingjava.swing.finish.types;

public class FontStyle {
	final boolean $italic;
	
	public FontStyle(final String $style) {
		$italic = "italic".equalsIgnoreCase($style);
	}
}