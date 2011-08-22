package net.abusingjava.swing.magic;

import javax.swing.JToggleButton;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("togglebutton")
public class ToggleButton extends TextComponent {

	@XmlAttribute
	boolean $selected;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JToggleButton $c = new JToggleButton($text, $selected);
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}