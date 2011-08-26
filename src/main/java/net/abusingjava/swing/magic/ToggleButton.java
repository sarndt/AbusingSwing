package net.abusingjava.swing.magic;

import javax.swing.JToggleButton;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("togglebutton")
public class ToggleButton extends TextComponent {

	@XmlAttribute
	Boolean $selected;
	
	@XmlAttribute
	String $filters;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		if ($selected == null) {
			$selected = false;
		}
		
		final JToggleButton $c = new JToggleButton($text, $selected);
		
		$component = $c;
				
		super.create($main, $parent);
	}
	
}