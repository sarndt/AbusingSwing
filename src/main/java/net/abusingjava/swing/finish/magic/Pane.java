package net.abusingjava.swing.finish.magic;

import net.abusingjava.swing.Magic.Title;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElement;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("pane")
public class Pane {

	@XmlChildElement
	Title $title;
	
	@XmlChildElements({HBox.class, VBox.class})
	Container[] $content;
	
	@XmlAttribute
	boolean $expanded = false;
	
	@XmlAttribute
	boolean $animated = false;
}