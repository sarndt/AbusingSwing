package net.abusingjava.swing.finish.magic;

import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("textfield")
public class TextField extends TextComponent {
	
	@XmlAttribute
	String $defaultText = "";
	
}