package net.abusingjava.swing.magic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.reflection.AbusingReflection;
import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.*;
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
	
	@XmlAttribute("onaction-dispatch")
	boolean $onactionDispatch = true;
	
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
	
	@XmlAttribute("font-style")
	FontStyle $fontStyle;
	
	@XmlAttribute("font-size")
	Value $fontSize;
	
	@XmlAttribute
	Boolean $opaque;
	
	@XmlAttribute
	ClassNames $classes;
	
	@XmlAttribute
	String $name;
	
	
	protected JComponent $component;
	
	protected JComponent $realComponent = null;
	
	
	public JComponent getJComponent() {
		return $component;
	}
	
	public JComponent getRealComponent() {
		if ($realComponent == null) {
			return $component;
		}
		return $realComponent;
	}
	
	public String getName() {
		return $name;
	}
	
	public ClassNames getClasses() {
		return $classes;
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

	public void create(final MagicPanel $main,
			@SuppressWarnings("unused") final MagicPanel $parent) {
		
		if ($realComponent == null) {
			$realComponent = $component;
		}
		
		$main.registerComponent($name, this);
		
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
		if (($fontWeight != null) && $fontWeight.isBold()) {
			$realComponent.setFont($realComponent.getFont().deriveFont(Font.BOLD));
		}
		if (($fontStyle != null) && $fontStyle.isItalic()) {
			$realComponent.setFont($realComponent.getFont().deriveFont(Font.ITALIC));
		}
		if (($fontSize != null) && ($fontSize.getUnit() == Unit.PIXEL)) {
			$realComponent.setFont($realComponent.getFont().deriveFont((float)$fontSize.getValue()));
		}
		
		if ($onaction != null) {
			ActionListener $listener = new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent $ev) {
					if ($onactionDispatch) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								$onaction.call($main.getInvocationHandler());
							}
						}).start();
					} else {
						$onaction.call($main.getInvocationHandler());
					}
				}
			};
			if (AbusingReflection.hasMethod($realComponent, "addActionListener")) {
				try {
					$realComponent.getClass().getMethod("addActionListener", ActionListener.class).invoke($realComponent, $listener);
				} catch (Exception $exc) {
					System.err.println($exc);
				}
			}
		}
	}
}