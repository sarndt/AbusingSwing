package net.abusingjava.swing.finish.magic;

import net.abusingjava.swing.Magic.Container;
import net.abusingjava.swing.Magic.HBox;
import net.abusingjava.swing.Magic.Title;
import net.abusingjava.swing.Magic.VBox;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElement;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("tab")
public class Tab {

	@XmlChildElement
	Title $title;
	
	@XmlChildElements({HBox.class, VBox.class})
	Container[] $content;
	
	@XmlAttribute("echochar")
	Character $echoChar;
}