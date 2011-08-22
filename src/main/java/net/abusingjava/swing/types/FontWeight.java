package net.abusingjava.swing.types;

public class FontWeight {
	final boolean $bold;
	
	public FontWeight(final String $weight) {
		$bold = "bold".equalsIgnoreCase($weight);
	}

	public boolean isBold() {
		return $bold;
	}
}