package net.abusingjava.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeModel;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.magic.CheckBox;
import net.abusingjava.swing.magic.Component;
import net.abusingjava.swing.magic.MultiList;
import net.abusingjava.swing.magic.TextComponent;
import net.java.balloontip.BalloonTip;

import org.jdesktop.swingx.JXTable;

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
	

	public Component get(final int $index) {
		return $components.get($index);
	}
	
	public MagicComponents setText(final String $text) {
		for (final Component $comp : $components) {
			if ($comp instanceof TextComponent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						((TextComponent) $comp).setText($text);
					}
				});
			}
		}
		return this;
	}
	
	public MagicComponents setModel(final Object $model) {
		if ($model instanceof TableModel) {
			for (Component $comp : $components) {
				JComponent $c = $comp.getRealComponent();
				if ($c instanceof JTable) {
					((JTable)$c).setModel((TableModel) $model);
				}
			}
		} else if ($model instanceof ListModel) {
			for (Component $comp : $components) {
				JComponent $c = $comp.getRealComponent();
				if ($c instanceof JList) {
					((JList)$c).setModel((ListModel) $model);
				}
			}
		} else if ($model instanceof TreeModel) {
			for (Component $comp : $components) {
				JComponent $c = $comp.getRealComponent();
				if ($c instanceof JTree) {
					((JTree)$c).setModel((TreeModel) $model);
				}
			}
		} else if ($model instanceof ComboBoxModel) {
			for (Component $comp : $components) {
				JComponent $c = $comp.getRealComponent();
				if ($c instanceof JComboBox) {
					((JComboBox)$c).setModel((ComboBoxModel) $model);
				}
			}
		}
		return this;
	}

	public boolean isSelected() {
		for (final Component $comp : $components) {
			if ($comp instanceof CheckBox) {
				FutureTask<Boolean> $task = new FutureTask<Boolean>(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ((JCheckBox) $comp.getRealComponent()).isSelected();
					}
				});
				SwingUtilities.invokeLater($task);
				try {
					return $task.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public String getText() {
		for (final Component $comp : $components) {
			if ($comp instanceof TextComponent) {
				FutureTask<String> $task = new FutureTask<String>(new Callable<String>() {
					@Override
					public String call() {
						return ((TextComponent)$comp).getText();
					}
				});
				SwingUtilities.invokeLater($task);
				try {
					return $task.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	public MagicComponents clear() {
		for (Component $comp : $components) {
			JComponent $real = $comp.getRealComponent();
			if ($real instanceof JTable) {
				final TableModel $m = ((JTable) $real).getModel();
				if ($m instanceof DefaultTableModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							while ($m.getRowCount() > 0) {
								((DefaultTableModel)$m).removeRow(0);
							}
						}
					});
				}
			} else if ($real instanceof JList) {
				final ListModel $m = ((JList) $real).getModel();
				if ($m instanceof DefaultListModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultListModel) $m).setSize(0);
						}
					});
				}
			}
		}
		return this;
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

	/**
	 * Returns the underlying components as the specified $class.
	 * <p>
	 * This applies to the first component in the list which can be
	 * cast to the specified list.
	 * <p>
	 * <b>Example:</b> <code>$(".allComponentsOfThisClass").as(JTable.class)</code>
	 * will return the underlying JTable of the first component in the class
	 * “.allComponentsOfThisClass” that is actually realized by a JTable.
	 * <p>
	 * The check whether the shoe fits or not is done using
	 * {@link Class#isAssignableFrom(Class)}, i.e. if the underlying {@link JComponent}
	 * is (for example) a {@link JXTable} (which is a JTable, too) it will match
	 * in the above example.
	 * <p>
	 * This method is <b>not</b> <i>thread-safe</i>, since it exposes the underlying
	 * JComponents to any calling thread. Don’t get me wrong dude, calling the method
	 * itself from any thread causes no harm, however, you will be responsible for
	 * invoking methods of the returned components on the AWT Event Queue.
	 */
	@SuppressWarnings("unchecked")
	public <T extends JComponent> T as(final Class<T> $class) {
		if ($class.isArray()) {
			List<T> $list = new LinkedList<T>();
			for (Component $comp : $components) {
				JComponent $c = $comp.getRealComponent();
				if ($class.isAssignableFrom($c.getClass())) {
					$list.add((T) $c);
				}
			}
			return (T) (Object) $list.toArray((Object[]) Array.newInstance($class.getComponentType(), $list.size()));
		}
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($class.isAssignableFrom($c.getClass())) {
				return (T) $c;
			}
		}
		return null;
	}
	
	public int count() {
		return $components.size();
	}

	/**
	 * Applies to any component. Shows the components.
	 * <p>
	 * This works by calling {@link JComponent#setVisible(boolean)}.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents show() {
		for (final Component $comp : $components) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					$comp.getJComponent().setVisible(true);
				}
			});
		}
		return this;
	}

	/**
	 * Applies to any component. Hides the components.
	 * <p>
	 * This works by calling {@link JComponent#setVisible(boolean)}. The components will simply
	 * not be shown anymore, however, they are not removed from the layout (i.e. they will
	 * leave an empty space).
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents hide() {
		for (final Component $comp : $components) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
						$comp.getJComponent().setVisible(false);
					
				}
			});
		}
		return this;
	}

	/**
	 * Applies to any component. Enables the components.
	 * <p>
	 * This works by calling {@link JComponent#setEnabled(boolean)}.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents enable() {
		for (final Component $comp : $components) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					$comp.getJComponent().setEnabled(true);
				}
			});
		}
		return this;
	}

	/**
	 * Applies to any component. Disables the components.
	 * <p>
	 * This works by calling {@link JComponent#setEnabled(boolean)}.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents disable() {
		for (final Component $comp : $components) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					$comp.getJComponent().setEnabled(false);
				}
			});
		}
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
					$comp.getRealComponent().setForeground(new net.abusingjava.swing.magix.types.Color($hexColor).getColor());
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

	/**
	 * Applies to any component. Sets the background color of the particular components.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents setBackground(final String $hexColor) {
		for (final Component $comp : $components) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					$comp.getRealComponent().setBackground(new net.abusingjava.swing.magix.types.Color($hexColor).getColor());
				}
			});
		}
		return this;
	}

	/**
	 * Applies to any component. Sets the font of the particular components.
	 * <p>
	 * The font is identified by calling {@link Font#decode(String)}.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
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

	/**
	 * Applies to any component. Sets the font size of the particular component.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents setFontSize(final int $size) {
		for (Component $comp : $components) {
			final JComponent $c = $comp.getRealComponent();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				$c.setFont($c.getFont().deriveFont((float) $size));
				}
			});
		}
		return this;
	}

	public MagicComponents add(final Iterable<?> $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				final ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					for (final Object $value : $values) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								((DefaultComboBoxModel)$m).addElement($value);
							}
						});
					}
				}
			} else if ($c instanceof JList) {
				final ListModel $m = ((JList)$c).getModel();
				if ($m instanceof DefaultListModel) {
					for (final Object $value : $values) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								((DefaultListModel)$m).addElement($value);
							}
						});
					}
				}
			}
		}
		return this;
	}

	/**
	 * Applies to all &lt;table&gt;-Objects. Adds the row, specified as Array.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents addRow(final Object[] $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTable) {
				final TableModel $m = ((JTable)$c).getModel();
				if ($m instanceof DefaultTableModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultTableModel)$m).addRow($values);
						}
					});
				}
			}
		}
		return this;
	}

	/**
	 * Applies to all &lt;table&gt;-Objects. Adds multiple rows.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents addRows(final Object[][] $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTable) {
				final TableModel $m = ((JTable)$c).getModel();
				if ($m instanceof DefaultTableModel) {
					for (final Object[] $row : $values) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								((DefaultTableModel)$m).addRow($row);
							}
						});
					}
				}
			}
		}
		return this;
	}

	/**
	 * Applies to all &lt;combobx&gt;, and &lt;list&gt;-Objects. Adds an item to the underlying model.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents add(final Object[] $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				final ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					for (final Object $value : $values) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								((DefaultComboBoxModel)$m).addElement($value);
							}
						});
					}
				}
			} else if ($c instanceof JList) {
				final ListModel $m = ((JList)$c).getModel();
				if ($m instanceof DefaultListModel) {
					for (final Object $value : $values) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								((DefaultListModel)$m).addElement($value);
							}
						});
					}
				}
			}
		}
		return this;
	}

	/**
	 * Applies to all &lt;combobox&gt;, &lt;list&gt;, and &lt;multilist&gt;-Objects. Adds an item to the underlying model.
	 * <p>
	 * This method is <i>thread-safe</i>. You can call it from any thread, it’s
	 * actions will execute in the AWT-Event-Queue.
	 */
	public MagicComponents add(final Object $value) {
		for (final Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				final ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							$comp.setUpdate(true);
							((DefaultComboBoxModel)$m).addElement($value);
							$comp.setUpdate(false);
						}
					});
				}
			} else if ($c instanceof JList) {
				final ListModel $m = ((JList)$c).getModel();
				if ($m instanceof DefaultListModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultListModel)$m).addElement($value);
						}
					});
				}
			} else if ($comp instanceof MultiList) {
				final TableModel $m = ((JTable)$c).getModel();
				if ($m instanceof DefaultTableModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultTableModel)$m).addRow(new Object[] { false, $value });
						}
					});
				}
			}
		}
		return this;
	}

	public MagicComponents add(final Object $value, final boolean $selected) {
		for (Component $comp : $components) {
			if ($comp instanceof MultiList) {
				JComponent $c = $comp.getRealComponent();
				final TableModel $m = ((JTable)$c).getModel();
				if ($m instanceof DefaultTableModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultTableModel)$m).addRow(new Object[] { $selected, $value });
						}
					});
				}
			}
		}
		return this;
	}

	public MagicComponents setSelectedItem(final Object $item) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				$comp.setUpdate(true);
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					$m.setSelectedItem($item);
				}
				$comp.setUpdate(false);
			} else if ($c instanceof JList) {
				((JList)$c).setSelectedValue($item, true);
			}
		}
		return this;
	}
	
	public MagicComponents setSelectedIndex(final int $index) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				$comp.setUpdate(true);
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					$m.setSelectedItem($m.getElementAt($index));
				}
				$comp.setUpdate(false);
			} else if ($c instanceof JList) {
				((JList)$c).setSelectedIndex($index);
			}
		}
		return this;
	}
	
	public MagicComponents setSelected(final boolean $selected) {
		for (Component $comp : $components) {
			if ($comp instanceof CheckBox) {
				final JCheckBox $checkBox = (JCheckBox) $comp.getRealComponent();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						$checkBox.setSelected($selected);
					}
				});
			}
		}
		return this;
	}

	public Object getSelectedItem() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					return $m.getSelectedItem();
				}
			} else if ($c instanceof JList) {
				ListModel $m = ((JList)$c).getModel();
				try {
					return $m.getElementAt(((JList)$c).getSelectedIndex());
				} catch (ArrayIndexOutOfBoundsException $exc) {
					return null;
				}
			}
		}
		return null;
	}
	
	public Object getValueAt(final int $modelRow, final int $modelColumn) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTable) {
				TableModel $m = ((JTable) $c).getModel();
				if ($m instanceof DefaultTableModel) {
					
				}
				return $m.getValueAt($modelRow, $modelColumn);
			}
		}
		return null;
	}

	public int getSelectedIndex() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					return ((DefaultComboBoxModel) $m).getIndexOf($m.getSelectedItem());
				}
			} else if ($c instanceof JList) {
				return ((JList)$c).getSelectedIndex();
			}
		}
		return -1;
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

	/**
	 * Applies to all Components which have a “setMax” method (such as &lt;progressbar&gt;, &lt;numeric&gt;)
	 */
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

	/**
	 * Applies on &lt;multilist&gt;-Components only: Shows only the selected items in the list.
	 */
	public void showSelectedOnly(final boolean $selected) {
		for (final Component $comp : $components) {
			if ($comp instanceof MultiList) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						((MultiList) $comp).showSelectedOnly($selected);
					}
				});
			}
		}
	}
	
	public void showBubble(final String $message) {
		for (final Component $comp : $components) {
			JComponent $c = $comp.getJComponent();
			if ($comp.getBalloonTip() != null) {
				$comp.getBalloonTip().closeBalloon();
			}
			BalloonTip $tip = new BalloonTip($c, $message);
			$comp.setBalloonTip($tip);
		}
	}
	
	public void clearBubble() {
		for (final Component $comp : $components) {
			if ($comp.getBalloonTip() != null) {
				$comp.getBalloonTip().closeBalloon();
				$comp.setBalloonTip(null);
			}
		}
	}
}
