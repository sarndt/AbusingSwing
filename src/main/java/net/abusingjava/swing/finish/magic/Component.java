package net.abusingjava.swing.finish.magic;

import net.abusingjava.xml.XmlAttribute;

abstract public class Component {
	
	@XmlAttribute
	Value $width;
	
	@XmlAttribute
	Value $height;
	
	@XmlAttribute
	Value $posX;
	
	@XmlAttribute
	Value $posY;
	
	@XmlAttribute
	Color $foreground;
	
	@XmlAttribute
	Color $background;
	
	@XmlAttribute
	boolean $enabled = true;
	
	@XmlAttribute
	boolean $visible = true;
	
}