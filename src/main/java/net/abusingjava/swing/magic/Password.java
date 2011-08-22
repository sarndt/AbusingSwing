package net.abusingjava.swing.magic;

import javax.swing.JPasswordField;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("password")
public class Password extends TextField {
	
	public static class EchoChar {
		public final Character $char;
		
		public EchoChar(final String $value) {
			if ($value.length() > 0) {
				$char = $value.charAt(0);
			} else {
				$char = '*';
			}
		}
	}
	
	@XmlAttribute("echo-char")
	EchoChar $echoChar;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		super.create($main, $parent);
		
		JPasswordField $c = new JPasswordField();
		
		if ($echoChar != null) {
			$c.setEchoChar($echoChar.$char);
		}
		
		$component = $c;
	}
}