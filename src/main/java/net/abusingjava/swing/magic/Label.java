package net.abusingjava.swing.magic;

import javax.swing.JLabel;

import org.jdesktop.swingx.JXLabel;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("label")
public class Label extends TextComponent {

	@XmlAttribute("text-align")
	String $textAlign;
	
	@XmlAttribute("vertical-align")
	String $verticalAlignment;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("20px");
		}

		int $align = JXLabel.LEFT;
		if ("right".equals($textAlign)) {
			$align = JLabel.RIGHT;
		} else if ("center".equals($textAlign)) {
			$align = JLabel.CENTER;
		}
		
		JLabel $c = new JLabel($text, $align);
		
		if ("top".equals($verticalAlignment)) {
			$c.setVerticalAlignment(JLabel.TOP);
		} else if ("bottom".equals($verticalAlignment)) {
			$c.setVerticalAlignment(JLabel.BOTTOM);
		}

		if (($background != null) && ($opaque == null)) {
			$opaque = true;
		}
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}