package net.abusingjava.swing.magic;

import javax.swing.JButton;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.magix.types.Value;
import net.abusingjava.xml.XmlElement;

@XmlElement("button")
public class Button extends TextComponent {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		$text = $text.trim();
		
		$component = new JButton($text);
		
		super.create($main, $parent);
	}
	
}