package net.abusingjava.swing.magic;

import net.abusingjava.swing.types.MethodType;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("item")
public class MenuItem {

	@XmlAttribute
	String $title;
	
	@XmlAttribute
	MethodType $onaction;
	
	public String getTitle() {
		return $title;
	}
}
