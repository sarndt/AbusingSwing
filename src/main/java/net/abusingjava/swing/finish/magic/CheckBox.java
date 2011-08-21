package net.abusingjava.swing.finish.magic;

import javax.swing.JCheckBox;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("checkbox")
public class CheckBox extends TextComponent {
	
	@XmlAttribute
	boolean $selected = false;
	
	@Override
	public void create(final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		JCheckBox $c = new JCheckBox($text, $selected);
		
		$component = $c;
		
		super.create($parent);
	}
	
}