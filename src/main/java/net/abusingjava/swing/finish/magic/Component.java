package net.abusingjava.swing.finish.magic;

import javax.swing.JComponent;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.Magic.MethodType;
import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Color;
import net.abusingjava.swing.finish.types.FontWeight;
import net.abusingjava.swing.finish.types.Value;
import net.abusingjava.xml.XmlAttribute;

@Author("Julian Fleischer")
@Version("2011-08-21")
@Since(value = "2011-08-21", version = "1.0")
abstract public class Component {
	
	@XmlAttribute
	Value $width;
	
	@XmlAttribute
	Value $height;
	
	@XmlAttribute
	Value $minWidth = new Value("0px");
	
	@XmlAttribute
	Value $minHeight = new Value("0px");
	
	@XmlAttribute("x")
	Value $posX = new Value("0px");
	
	@XmlAttribute("y")
	Value $posY = new Value("0px");
	
	@XmlAttribute
	Color $foreground;
	
	@XmlAttribute
	Color $background;
	
	@XmlAttribute
	boolean $enabled = true;
	
	@XmlAttribute
	boolean $visible = true;
	
	@XmlAttribute
	MethodType $onaction;
	
	@XmlAttribute
	MethodType $onclick;
	
	@XmlAttribute
	MethodType $ondblclick;

	@XmlAttribute
	MethodType $onblur;
	
	@XmlAttribute
	MethodType $onfocus;
	
	@XmlAttribute
	MethodType $onmousedown;
	
	@XmlAttribute
	MethodType $onmouseup;
	
	@XmlAttribute
	MethodType $onmousemove;
	
	@XmlAttribute
	MethodType $onkeydown;
	
	@XmlAttribute
	MethodType $onkeypress;
	
	@XmlAttribute
	MethodType $onkeyup;
	
	@XmlAttribute("tool-tip")
	String $toolTip = "";
	
	@XmlAttribute("font-weight")
	FontWeight $fontWeight;
	
	@XmlAttribute
	Boolean $opaque;
	
	protected JComponent $component;
	
	
	public JComponent getJComponent() {
		return $component;
	}
	
	public Value getWidth() {
		return $width;
	}
	
	public Value getHeight() {
		return $height;
	}
	
	public Value getPosX() {
		return $posX;
	}
	
	public Value getPosY() {
		return $posY;
	}

	public void create(
			@SuppressWarnings("unused") final MagicPanel $main,
			@SuppressWarnings("unused") final MagicPanel $parent) {
		
		$component.setEnabled($enabled);
		$component.setVisible($visible);
		if (!$toolTip.isEmpty()) {
			$component.setToolTipText($toolTip);
		}
		if ($foreground != null) {
			$component.setForeground($foreground.getColor());
		}
		if ($background != null) {
			$component.setBackground($background.getColor());
		}
		if ($width == null) {
			$width = new Value();
		}
		if ($height == null) {
			$height = new Value();
		}
		if ($opaque != null) {
			$component.setOpaque($opaque);
		}
	}
}