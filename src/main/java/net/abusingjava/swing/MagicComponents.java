package net.abusingjava.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.magic.Component;

/**
 * A Collection of Components on which you may apply a function as you want.
 */
@Author("Julian Fleischer")
@Version("2011-08-19")
@Since(value = "2011-08-19", version = "1.0")
public class MagicComponents {

	final private ArrayList<Component> $components = new ArrayList<Component>();

	public MagicComponents(final Component... $components) {
		this.$components.ensureCapacity($components.length);
		for (Component $c : $components) {
			this.$components.add($c);
		}
	}

	public MagicComponents(final Collection<Component> $components) {
		this.$components.ensureCapacity($components.size());
		for (Component $c : $components) {
			this.$components.add($c);
		}
	}

	public MagicComponents setText(final String $text) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTextComponent) {
				((JTextComponent) $c).setText($text);
			} else if ($c instanceof JButton) {
				((JButton) $c).setText($text);
			} else if ($c instanceof JLabel) {
				((JLabel) $c).setText($text);
			} else if ($c instanceof JCheckBox) {
				((JCheckBox) $c).setText($text);
			}
		}
		return this;
	}

	public String getText() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTextComponent) {
				return ((JTextComponent) $c).getText();
			}
		}
		return "";
	}

	public MagicComponents show(final int $index) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					if ($c.getLayout() instanceof CardLayout) {
						((CardLayout) $c.getLayout()).show($c, "card" + $index);
					}
				}
			}
		});
		return this;
	}

	public MagicComponents showNext() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					if ($c.getLayout() instanceof CardLayout) {
						((CardLayout) $c.getLayout()).next($c);
					}
				}
			}
		});
		return this;
	}

	public MagicComponents showPrev() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					if ($c.getLayout() instanceof CardLayout) {
						((CardLayout) $c.getLayout()).previous($c);
					}
				}
			}
		});
		return this;
	}

	public MagicComponents showFirst() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					if ($c.getLayout() instanceof CardLayout) {
						((CardLayout) $c.getLayout()).first($c);
					}
				}
			}
		});
		return this;
	}

	public MagicComponents showLast() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					if ($c.getLayout() instanceof CardLayout) {
						((CardLayout) $c.getLayout()).last($c);
					}
				}
			}
		});
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends JComponent> T as(final Class<T> $class) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($class.isAssignableFrom($c.getClass())) {
				return (T) $c;
			}
		}
		return null;
	}

	public MagicComponents show() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getJComponent().setVisible(true);
				}
			}
		});
		return this;
	}

	public MagicComponents hide() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getJComponent().setVisible(false);
				}
			}
		});
		return this;
	}

	public MagicComponents enable() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				for (Component $comp : $components) {
					$comp.getJComponent().setEnabled(true);
				}
			}
		});
		return this;
	}

	public MagicComponents disable() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getJComponent().setEnabled(false);
				}
			}
		});
		return this;
	}

	public MagicComponents setForeground(final Color $color) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getRealComponent().setForeground($color);
				}
			}
		});
		return this;
	}

	public MagicComponents setForeground(final String $hexColor) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getRealComponent().setForeground(new net.abusingjava.swing.types.Color($hexColor).getColor());
				}
			}
		});
		return this;
	}

	public MagicComponents setBackground(final Color $color) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getRealComponent().setBackground($color);
				}
			}
		});
		return this;
	}

	public MagicComponents setBackground(final String $hexColor) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					$comp.getRealComponent().setBackground(new net.abusingjava.swing.types.Color($hexColor).getColor());
				}
			}
		});
		return this;
	}

	public MagicComponents setFont(final String $font) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					$c.setFont(Font.decode($font));
				}
			}
		});
		return this;
	}

	public MagicComponents setFontSize(final int $size) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					$c.setFont($c.getFont().deriveFont((float) $size));
				}
			}
		});
		return this;
	}

	public MagicComponents add(final Object[] $values) {
		return this;
	}

	public MagicComponents add(final Object $value) {
		return this;
	}

	public MagicComponents setValue(final int $value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					try {
						Method $m;
						if (($m = $c.getClass().getMethod("setValue", int.class)) != null) {
							$m.invoke($c, $value);
						} else if (($m = $c.getClass().getMethod("setValue", Integer.class)) != null) {
							$m.invoke($c, $value);
						} else if (($m = $c.getClass().getMethod("setValue", double.class)) != null) {
							$m.invoke($c, (double) $value);
						}
					} catch (Exception $exc) {
						$exc.printStackTrace(System.err);
					}
				}
			}
		});
		return this;
	}

	public MagicComponents setMax(final int $value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Component $comp : $components) {
					JComponent $c = $comp.getRealComponent();
					try {
						Method $m;
						if (($m = $c.getClass().getMethod("setMax", int.class)) != null) {
							$m.invoke($c, $value);
						} else if (($m = $c.getClass().getMethod("setMax", Integer.class)) != null) {
							$m.invoke($c, $value);
						} else if (($m = $c.getClass().getMethod("setMax", double.class)) != null) {
							$m.invoke($c, (double) $value);
						}
					} catch (Exception $exc) {
						$exc.printStackTrace(System.err);
					}
				}
			}
		});
		return this;
	}

	public int getMax() {
		return 0;
	}

	public int getMin() {
		return 0;
	}

	public int getValue() {
		return 0;
	}

	public MagicComponents setDate(final Date $date) {
		return this;
	}

	public Date getDate() {
		return null;
	}
}
