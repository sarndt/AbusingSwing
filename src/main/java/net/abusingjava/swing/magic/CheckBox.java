package net.abusingjava.swing.magic;

import javax.swing.JCheckBox;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("checkbox")
public class CheckBox extends TextComponent {
	
	@XmlAttribute
	boolean $selected = false;

	@XmlAttribute
	String $filters;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		final JCheckBox $c = new JCheckBox($text, $selected);
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}