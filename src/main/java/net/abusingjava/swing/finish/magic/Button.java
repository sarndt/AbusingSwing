package net.abusingjava.swing.finish.magic;

import javax.swing.JButton;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Value;
import net.abusingjava.xml.XmlElement;

@XmlElement("button")
public class Button extends TextComponent {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		$component = new JButton($text);
		
		super.create($main, $parent);
	}
	
}