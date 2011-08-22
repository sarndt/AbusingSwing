package net.abusingjava.swing.finish.magic;

import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("pane")
public class Pane extends Panel {

	@XmlAttribute
	String $title = "";
	
	@XmlAttribute
	boolean $expanded = true;
	
	@XmlAttribute
	boolean $animated = false;
	
	@XmlAttribute
	int $height;
	
	public String getTitle() {
		return $title;
	}
	
	public boolean getExpanded() {
		return $expanded;
	}
	
	public boolean getAnimated() {
		return $animated;
	}
	
	public int getHeight() {
		return $height;
	}
}