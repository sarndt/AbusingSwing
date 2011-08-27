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

	public MagicComponents add(final Iterable<Object> $values) {
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

	public MagicComponents addRows(final Object[][] $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTable) {
				TableModel $m = ((JTable)$c).getModel();
				if ($m instanceof DefaultTableModel) {
					for (Object[] $row : $values) {
						((DefaultTableModel)$m).addRow($row);
					}
				}
			}
		}
		return this;
	}

	public MagicComponents add(final Object[] $values) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					for (Object $value : $values) {
						((DefaultComboBoxModel)$m).addElement($value);
					}
				}
			} else if ($c instanceof JList) {
				ListModel $m = ((JList)$c).getModel();
				if ($m instanceof DefaultListModel) {
					for (Object $value : $values) {
						((DefaultListModel)$m).addElement($value);
					}
				}
			}
		}
		return this;
	}

	public MagicComponents add(final Object $value) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JComboBox) {
				final ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							((DefaultComboBoxModel)$m).addElement($value);
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
				ComboBoxModel $m = ((JComboBox)$c).getModel();
				if ($m instanceof DefaultComboBoxModel) {
					$m.setSelectedItem($item);
				}
			} else if ($c instanceof JList) {
				((JList)$c).setSelectedValue($item, true);
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
}
