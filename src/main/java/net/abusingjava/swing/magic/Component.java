package net.abusingjava.swing.magic;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import net.abusingjava.AbusingReflection;
import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.magix.types.*;
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
	
	@XmlAttribute("left")
	Value $posX = new Value("0px");
	
	@XmlAttribute("top")
	Value $posY = new Value("0px");
	
	@XmlAttribute
	Color $foreground;
	
	@XmlAttribute
	Color $background;
	
	@XmlAttribute
	Boolean $enabled; // true
	
	@XmlAttribute
	Boolean $visible; // true
	
	@XmlAttribute
	MethodType $onaction;
	
	@XmlAttribute
	MethodType $onclick;
	
	@XmlAttribute
	MethodType $onblur;
	
	@XmlAttribute
	MethodType $onfocus;
	
	@XmlAttribute
	MethodType $onmousedown;
	
	@XmlAttribute
	MethodType $onmouseup;
	
	@XmlAttribute
	MethodType $onmouseover;

	@XmlAttribute
	MethodType $onmouseout;

	@XmlAttribute
	MethodType $onmousemove;

	@XmlAttribute
	MethodType $onmousewheel;
	
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
	String $cursor;
	
	@XmlAttribute
	ClassNames $class;
	
	@XmlAttribute
	String $name;
	
	@XmlAttribute("popup-menu")
	String $popupMenu;
	
	
	protected JComponent $component;
	
	protected JComponent $realComponent = null;

	private boolean $update = false;
	
	
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
		return $class;
	}
	
	public boolean hasClass(final String $className) {
		if (($class == null) || ($className == null)) {
			return false;
		}
		return $class.contains($className);
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
		if ($enabled == null) {
			$enabled = true;
		}
		if ($visible == null) {
			$visible = true;
		}
		if ($realComponent == null) {
			$realComponent = $component;
		}
		
		if (($cursor != null) && $cursor.equalsIgnoreCase("hand")) {
			$component.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		if ($popupMenu != null) {
			final JPopupMenu $menu = $main.getPopupMenu($popupMenu);
			if ($menu != null) {
				$realComponent.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(final MouseEvent $ev) {
						if ($ev.isPopupTrigger()) {
							doPop($ev);
						}
					}
					
					@Override
					public void mouseReleased(final MouseEvent $ev) {
						if ($ev.isPopupTrigger()) {
							doPop($ev);
						}
					}
					
					private void doPop(final MouseEvent $ev) {
						$menu.show($realComponent, $ev.getX(), $ev.getY());
					}
				});
			}
		}
		
		if ($onaction != null) {
			ActionListener $listener = new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent $ev) {
					if (!$update) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								$onaction.call($main.getInvocationHandler(), $ev.getSource());
							}
						}).start();
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

		if ($onclick != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mouseClicked(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onclick.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmouseover != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mouseEntered(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmouseover.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmouseout != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mouseExited(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmouseout.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmousemove != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mouseMoved(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmousemove.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmousedown != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mousePressed(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmousedown.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmouseup != null) {
			MouseAdapter $listener = new MouseAdapter() {
				@Override
				public void mouseReleased(final MouseEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmouseup.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseListener($listener);
		}

		if ($onmousewheel != null) {
			MouseWheelListener $listener = new MouseWheelListener() {
				
				@Override
				public void mouseWheelMoved(final MouseWheelEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onmousewheel.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addMouseWheelListener($listener);
		}

		if ($onkeydown != null) {
			KeyAdapter $listener = new KeyAdapter() {
				
				@Override
				public void keyPressed(final KeyEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onkeydown.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addKeyListener($listener);
		}

		if ($onkeyup != null) {
			KeyAdapter $listener = new KeyAdapter() {
				
				@Override
				public void keyReleased(final KeyEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onkeyup.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addKeyListener($listener);
		}

		if ($onkeypress != null) {
			KeyAdapter $listener = new KeyAdapter() {
				
				@Override
				public void keyTyped(final KeyEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onkeypress.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addKeyListener($listener);
		}
		
		if ($onfocus != null) {
			FocusAdapter $listener = new FocusAdapter() {
				@Override
				public void focusGained(final FocusEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onfocus.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addFocusListener($listener);
		}

		if ($onblur != null) {
			FocusAdapter $listener = new FocusAdapter() {
				@Override
				public void focusLost(final FocusEvent $ev) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							$onfocus.call($main.getInvocationHandler(), $ev);
						}
					}).start();
				}
			};
			$realComponent.addFocusListener($listener);
		}
		
		$realComponent.setLocale(Locale.GERMAN);
	}

	public void setUpdate(final boolean $updateMode) {
		$update = $updateMode;
	}
}