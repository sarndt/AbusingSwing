package net.abusingjava.swing.finish.magic;

import net.abusingjava.swing.Magic.Tab;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("tabs")
public class Tabs {

	@XmlChildElements
	Tab[] $tabs = new Tab[] {};
	
}