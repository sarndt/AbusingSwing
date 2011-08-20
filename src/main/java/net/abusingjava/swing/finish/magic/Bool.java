package net.abusingjava.swing.finish.magic;

public class Bool {
	final boolean $true;
	
	public Bool(final String $value) {
		boolean $true;
		if ("yes".equalsIgnoreCase($value) || "true".equalsIgnoreCase($value)) {
			$true = Boolean.TRUE;
		} else if ("no".equalsIgnoreCase($value) || "false".equalsIgnoreCase($value)) {
			$true = Boolean.FALSE;
		} else {
			throw new RuntimeException("Unrecognized value for Bool.");
		}
		this.$true = $true;
	}
}