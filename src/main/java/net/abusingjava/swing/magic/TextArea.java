package net.abusingjava.swing.magic;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("textarea")
public class TextArea extends TextComponent {
	
	@XmlAttribute("linewrap")
	boolean $lineWrap = true;
	
	@XmlAttribute("wrapstyleword")
	boolean $wrapStyleWord = true;
	
	@XmlAttribute("tabsize")
	Integer $tabSize = 4;
	
	@XmlAttribute("editable")
	boolean $editable = true;

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JTextArea $c = new JTextArea($text);
		
		$c.setEditable($editable);
		$c.setLineWrap($lineWrap);
		$c.setWrapStyleWord($wrapStyleWord);
		if ($tabSize != null) {
			$c.setTabSize($tabSize);
		}
		
		$realComponent = $c;
		$component = new JScrollPane($c);
		
		super.create($main, $parent);
	}
	
}