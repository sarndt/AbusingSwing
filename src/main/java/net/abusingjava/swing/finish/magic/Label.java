package net.abusingjava.swing.finish.magic;

import org.jdesktop.swingx.JXLabel;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("label")
public class Label extends TextComponent {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JXLabel $c = new JXLabel($text);
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}