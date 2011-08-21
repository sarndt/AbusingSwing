package net.abusingjava.swing.finish.magic;

import javax.swing.JTextArea;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("textarea")
public class TextArea extends TextComponent {
	
	@XmlAttribute("linewrap")
	boolean $lineWrap = true;
	
	@XmlAttribute("wrapstyleword")
	boolean $wrapStyleWord = true;
	
	@XmlAttribute("tabsize")
	int $tabSize = 4;

	@Override
	public void create(final MagicPanel $parent) {
		JTextArea $c = new JTextArea($text);
		
		$component = $c;
		
		super.create($parent);
	}
	
}