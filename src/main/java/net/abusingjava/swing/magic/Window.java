package net.abusingjava.swing.magic;

import java.awt.Dimension;

import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElement;
import net.abusingjava.xml.XmlElement;

@XmlElement("window")
public class Window {

	@XmlAttribute("min-width")
	Integer $minWidth;
	
	@XmlAttribute("max-width")
	Integer $maxWidth;
	
	@XmlAttribute("min-height")
	Integer $minHeight;
	
	@XmlAttribute("max-height")
	Integer $maxHeight;
	
	@XmlAttribute("width")
	Integer $width;
	
	@XmlAttribute("height")
	Integer $height;

	@XmlAttribute("title")
	String $title = "";
	
	@XmlAttribute
	boolean $resizable = true;
	
	@XmlChildElement
	Menu $menu;
	
	@XmlChildElement
	Panel $contentPane;

	
	public boolean hasMinSize() {
		return ($minWidth != null) && ($minHeight != null);
	}
	
	public Dimension getMinSize() {
		return new Dimension($minWidth, $minHeight);
	}
	
	public boolean hasSize() {
		return ($width != null) && ($height != null);
	}
	
	public Dimension getSize() {
		return new Dimension($width, $height);
	}
	
	public boolean getResizable() {
		return $resizable;
	}
	
	public String getTitle() {
		return $title;
	}
	
	public Panel getPanel() {
		return $contentPane;
	}
	
	public Menu getMenu() {
		return $menu;
	}
}
