package net.abusingjava.swing.magic;

import java.awt.Font;

import net.abusingjava.swing.types.Color;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("style")
public class Style {
	
	@XmlElement("rule")
	public static class Rule {
		
		@XmlAttribute
		String $match = "";
		
		@XmlAttribute("font-weight")
		Font $fontWeight;
		
		@XmlAttribute("font-style")
		Font $fontStyle;
		
		@XmlAttribute("font-size")
		Value $fontSize;
		
		@XmlAttribute
		Color $foreground;
		
		@XmlAttribute
		Color $background;
		
		@XmlAttribute
		Value $width;
		
		@XmlAttribute
		Value $height;
		
		@XmlAttribute("min-width")
		Value $minWidth;
		
		@XmlAttribute("min-height")
		Value $minHeight;
		
		@XmlAttribute
		Boolean $enabled;
		
		@XmlAttribute
		Boolean $visible;
		
		@XmlAttribute
		Boolean $expanded;
		
		@XmlAttribute
		Boolean $animated;

		@XmlAttribute
		Value $padding;
		
		@XmlAttribute("padding-x")
		Value $paddingX;
		
		@XmlAttribute("padding-y")
		Value $paddingY;
		
		@XmlAttribute("padding-left")
		Value $paddingLeft;
		
		@XmlAttribute("padding-right")
		Value $paddingRight;
		
		@XmlAttribute("padding-top")
		Value $paddingTop;
		
		@XmlAttribute("padding-bottom")
		Value $paddingBottom;

		@XmlAttribute("x")
		Value $posX;
		
		@XmlAttribute("y")
		Value $posY;
		
		@XmlAttribute("grid-color")
		Color $gridColor;
		
		@XmlAttribute("min")
		Integer $min;
		
		@XmlAttribute("max")
		Integer $max;
		
		@XmlAttribute("value")
		Integer $value;

		@XmlAttribute("auto-complete")
		Boolean $autoComplete;
		
		@XmlAttribute("column-control-visible")
		Boolean $columnControlVisible;
	}
	
	@XmlChildElements()
	Rule $rules[] = new Rule[] {};
	
}