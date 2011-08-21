package net.abusingjava.swing.finish.types;

public class FontWeight {
	final boolean $bold;
	
	public FontWeight(final String $weight) {
		$bold = "bold".equalsIgnoreCase($weight);
	}
}