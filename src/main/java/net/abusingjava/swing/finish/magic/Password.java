package net.abusingjava.swing.finish.magic;

import javax.swing.JPasswordField;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("password")
public class Password extends TextField {
	
	public static class Char {
		public final Character $char;
		
		public Char(final String $value) {
			if ($value.length() > 0) {
				$char = $value.charAt(0);
			} else {
				$char = '*';
			}
		}
	}
	
	@XmlAttribute("echo-char")
	Char $echoChar;
	
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