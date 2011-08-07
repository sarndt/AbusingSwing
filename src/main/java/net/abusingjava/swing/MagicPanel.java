/* Part of the AbusingJava-Library.
 * 
 * Source:  http://github.com/scravy/AbusingJava
 * Home:    http://www.abusingjava.net/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.abusingjava.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Stable;
import net.abusingjava.Version;
import net.abusingjava.arrays.AbusingArrays;
import net.abusingjava.functions.AbusingFunctions;
import net.abusingjava.functions.Function;
import net.abusingjava.strings.AbusingStrings;
import net.abusingjava.swing.BalloonTipFactory.Balloon;
import net.abusingjava.swing.Magic.Button;
import net.abusingjava.swing.Magic.CheckBox;
import net.abusingjava.swing.Magic.ComboBox;
import net.abusingjava.swing.Magic.DatePicker;
import net.abusingjava.swing.Magic.Numeric;
import net.abusingjava.swing.Magic.Panes;
import net.abusingjava.swing.Magic.Table;
import net.abusingjava.swing.Magic.Tabs;
import net.abusingjava.swing.Magic.TextField;
import net.abusingjava.xml.AbusingXML;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * A Panel which is built according to a certain XML-Definition.
 */
@Author("Julian Fleischer")
@Version("2011-07-17")
public class MagicPanel extends JPanel {

	private static final long serialVersionUID = 7502176225561354934L;
	
	final Color $dirty = Color.decode("0xFFF0F0");
	BalloonTipStyle $balloonTipStyle = new RoundedBalloonStyle(5, 5, Color.BLACK, Color.BLACK) {{
		setHorizontalOffset(30);
		setVerticalOffset(20);
	}};

	final List<JComponent> $order = new LinkedList<JComponent>();
	final Map<String,Magic.Component> $componentsByName = new HashMap<String,Magic.Component>();
	final Map<String,Set<Magic.Component>> $groups = new HashMap<String,Set<Magic.Component>>();
	final Magic $def;
	final MagicLayout $layout = new MagicLayout();
	final BalloonTipFactory $balloonFactory = BalloonTipFactory.newInstance();
	int $n = 0;

	Object $handler = this;
	
	/**
	 * Standard-Constructor taking a URI as XML definition.
	 * 
	 * @param $uri The URI to the XML resource which defines this panel.
	 */
	@Stable
	public MagicPanel(URI $uri) {
		try {
			this.$def = parseXML($uri.toURL().openStream());
			
			init();
		} catch (Exception $exc) {
			throw new IllegalArgumentException($exc);
		}
	}

	/**
	 * Standard-Constructor taking a URL as XML definition.
	 * 
	 * @param $url The URL for the XML resource which defines this panel.
	 */
	@Stable
	public MagicPanel(URL $url) {
		try {
			this.$def = parseXML($url.openStream());
			
			init();
		} catch (Exception $exc) {
			throw new IllegalArgumentException($exc);
		}
	}

	/**
	 * Standard-Constructor taking an InputStream form which the XML definition of this panel is read.
	 * 
	 * @param $stream The InputStream.
	 */
	@Stable
	public MagicPanel(InputStream $stream) {
		this.$def = parseXML($stream);
		
		init();
	}
	
	public MagicPanel(Magic $def) {
		this.$def = $def;

		init();
	}
	
	public void setHandler(Object $handler) {
		this.$handler = $handler;
	}
	
	private void init() {
		setLayout($layout);
		buildPanel($def);
		setFocusCycleRoot(true);
		setFocusTraversalPolicy(new MagicFocusTraversalPolicy());
	}
	
	private static Magic parseXML(InputStream $stream) {
		try {
			final DocumentBuilderFactory $f = DocumentBuilderFactory.newInstance();
			$f.setNamespaceAware(true);
			$f.setValidating(true);
			final DocumentBuilder $b1 = $f.newDocumentBuilder();
			$f.setValidating(false);
			final DocumentBuilder $b2 = $f.newDocumentBuilder();
			$b1.setErrorHandler(new ErrorHandler() {
				@Override
				public void error(SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}

				@Override
				public void warning(SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}

				@Override
				public void fatalError(SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}
			});
			
			$b1.setEntityResolver(new EntityResolver() {
				@Override
				public InputSource resolveEntity(String $publicId, String $systemId) throws SAXException, IOException {
					if ("-//abusingjava.net//net.abusingjava.swing.MagicPanel//EN".equals($publicId))
						return new InputSource(MagicPanel.class.getResourceAsStream("magic.dtd"));
					return null;
				}
			});
			
			final Document $in = $b1.parse($stream);
			
			final PipedOutputStream $out = new PipedOutputStream();
			final DOMSource $src = new DOMSource($in);
			final StreamResult $res = new StreamResult($out);
			final PipedInputStream $in2 = new PipedInputStream($out);
			
			final Document $stylesheet = $b2.parse(Magic.class.getResourceAsStream("prepare.xsl"));
			final DOMSource $properties = new DOMSource($b2.parse(Magic.class.getResourceAsStream("properties.xml")));
			
			Thread $t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Transformer $f = TransformerFactory
							.newInstance()
							.newTransformer(new DOMSource($stylesheet));
						
						$f.setURIResolver(new URIResolver() {
							
							@Override
							public Source resolve(String $href, String $base) throws TransformerException {
								if ("properties.xml".equals($href)) {
									return $properties;
								}
								return null;
							}
						});
						
						$f.transform($src, $res);
						$out.close();
					} catch (Exception $exc) {
						// TODO: Exception handling
						throw new RuntimeException($exc);
					}
				}
			});
			$t.start();

			Magic $def = AbusingXML.loadXML($in2, Magic.class);
			$in2.close();

			return $def;
		} catch (Exception $exc) {
			// TODO: Exception handling
			throw new RuntimeException($exc);
		}
	}
	
	protected void dumpDefinition(Magic $definition) {
		for (Magic.Container $c : $definition.$main) {
			dumpDefinition($c, 0);
		}
	}
	
	protected void dumpDefinition(Magic.Container $definition, int $depth) {
		for (Magic.Component $c : $definition.$content) {
			System.out.print(AbusingStrings.repeat("  ", $depth)
					+ $c.getClass().getSimpleName() + ":"
					+ $c.$name + " (");
			dumpDefinition($c);
			System.out.println(")");
			if ($c instanceof Magic.Container) {
				dumpDefinition((Magic.Container)$c, $depth + 1);
			}
		}
	}
	
	protected void dumpDefinition(Magic.Component $c) {
		System.out.print($c.$width.$unit);
		if ($c.$width.$unit == Magic.Unit.AUTO && $c instanceof Magic.Container) {
			System.out.print("[" + $layout.calculateHorizontalSize((Magic.Container)$c) + "]");
		} else if ($c.$width.$unit == Magic.Unit.PIXEL) {
			System.out.print("[" + $c.$width.$value + "]");
		}
		System.out.print("," + $c.$height.$unit);
		if ($c.$height.$unit == Magic.Unit.AUTO && $c instanceof Magic.Container) {
			System.out.print("[" + $layout.calculateVerticalSize((Magic.Container)$c) + "]");
		} else if ($c.$height.$unit == Magic.Unit.PIXEL) {
			System.out.print("[" + $c.$height.$value + "]");
		}
	}
	
	private void buildPanel(Magic $definition) {
		
		AtomicInteger $n = new AtomicInteger(0);
		for (Magic.Container $c : $definition.$main) {
			buildContainer($c, false, false, $n);
		}
	}
	
	private void buildContainer(Magic.Container $def, boolean $autoContainerX, boolean $autoContainerY, AtomicInteger $n) {
		$def.$index = $n.getAndIncrement();
		if ($def.$name == null)
			$def.$name = "$" + $def.$index;
		
		if ($autoContainerX && $def.$width.$unit == Magic.Unit.STAR) {
			$def.$width.$unit = Magic.Unit.AUTO;
		}
		if ($autoContainerY && $def.$height.$unit == Magic.Unit.STAR) {
			$def.$height.$unit = Magic.Unit.AUTO;
		}
		
		for (Magic.Component $c : $def.$content) {
			if ($c instanceof Magic.Container) {
				buildContainer((Magic.Container) $c,
						($def.$width.$unit == Magic.Unit.AUTO) || $autoContainerX,
						($def.$height.$unit == Magic.Unit.AUTO) || $autoContainerY,
						$n);
			} else if ($c != null) {
				$c.$index = $n.getAndIncrement();
				if ($c.$name == null)
					$c.$name = "$" + $c.$index;
				buildComponent($c);
			}
		}
	}
	
	private void buildComponent(final Magic.Component $def) {
		JComponent $c = null;
		if ($def instanceof Magic.Button) {
			$c = createButton((Magic.Button)$def);
		} else if ($def instanceof Magic.Label) {
			$c = createLabel((Magic.Label)$def);
		} else if ($def instanceof Magic.TextField) {
			$c = createTextField((Magic.TextField)$def);
		} else if ($def instanceof Magic.CheckBox) {
			$c = createCheckBox((Magic.CheckBox)$def); 
		} else if ($def instanceof Magic.Numeric) {
			$c = createSpinner((Magic.Numeric)$def);
		} else if ($def instanceof Magic.ComboBox) {
			$c = createComboBox((Magic.ComboBox)$def);
		} else if ($def instanceof Magic.Tabs) {
			$c = createTabbedPane((Magic.Tabs)$def);
		} else if ($def instanceof Magic.Panes) {
			$c = createTaskPane((Magic.Panes)$def);
		} else if ($def instanceof Magic.TextArea) {
			$c = createTextArea((Magic.TextArea)$def);
		} else if ($def instanceof Magic.Password) {
			$c = createPasswordField((Magic.Password)$def);
		} else if ($def instanceof Magic.Table) {
			$c = createTable((Magic.Table)$def);
		} else if ($def instanceof Magic.DatePicker) {
			$c = createDatePicker((Magic.DatePicker)$def);
		} else {
			// TODO: Exception handling
			System.err.println("Unknown component");
			return;
		}
		
		$def.$component = $c;
		if ($def.$name == null || $def.$name.isEmpty()) {
			$def.$name = "component" + $n++;
		}
		$componentsByName.put($def.$name, $def);
		add($def.$name, $c);
		
		if ($def.$groups != null) {
			String[] $myGroups = AbusingStrings.explode($def.$groups, " ");
			
			for (String $group : $myGroups) {
				if (!$groups.containsKey($group)) {
					$groups.put($group, new HashSet<Magic.Component>());
				}
				$groups.get($group).add($def);
			}
		}
		
		$def.$component.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ($def.$onclick != null) {
					$def.$onclick.call($handler);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if ($def.$onmousedown != null) {
					$def.$onmousedown.call($handler);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if ($def.$onmouseup != null) {
					$def.$onmouseup.call($handler);
				}
			}
			
		});
		
		$def.$component.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if ($def.$onkeydown != null) {
					$def.$onkeydown.call($handler);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if ($def.$onkeyup != null) {
					$def.$onkeyup.call($handler);
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				if ($def.$onkeypress != null) {
					$def.$onkeypress.call($handler);
				}
			}
			
		});
		
		if ($def.$foreground != null) {
			$c.setForeground($def.$foreground.$color);
		}
		if ($def.$background != null) {
			$c.setBackground($def.$background.$color);
		}
	}
	
	private JComponent createDatePicker(final DatePicker $def) {
		try {
			// @SuppressWarnings("unchecked")
			JXDatePicker $c = new JXDatePicker();
			$c.setFormats(new String[] {"dd.MM.yyyy", "yyyy-MM-dd"});

			if ($def.$onaction != null) {
				$c.addActionListener(new ActionListener() {
					@Override public void actionPerformed(ActionEvent e) {
						$def.$onaction.call($handler);
					}
				});
			}
			/*Class<JComponent> $class = (Class<JComponent>) Class.forName("org.jdesktop.swingx.JXDatePicker");
			JComponent $c = $class.newInstance();
			AbusingFunctions.callback($c, "setFormats").call((Object) new String[] {"dd.MM.yyyy", "yyyy-MM-dd"});
			
			if ($def.$onaction != null) {
				AbusingFunctions.callback($c, "addActionListener").call((ActionListener) new ActionListener() {
					@Override public void actionPerformed(ActionEvent e) {
						$def.$onaction.call($this);
					}
				});
			}*/
			
			return $c;
		} catch (Exception $exc) {
			$exc.printStackTrace(System.err);
			
			JComponent $c = new JSpinner(new SpinnerDateModel());
			$order.add($c);
			return $c;
		}
	}

	private JComboBox createComboBox(ComboBox $def) {
		if ($def.$from != null) {
			Object[] $values = new Object[] {};
			if ($def.$from.$class.isEnum()) {
				$values = (Object[]) AbusingFunctions.callback($def.$from.$class, "values").call();
			} else if ($def.$from != null && Iterable.class.isAssignableFrom($def.$from.$class)) {
				try {
					$values = AbusingArrays.toArray((Iterable<?>) $def.$from.$class.newInstance());
				} catch (Exception $exc) {
				}
			}
			$def.$values = new Magic.ComboBox.Val[$values.length];
			for (int $i = 0; $i < $def.$values.length; $i++) {
				$def.$values[$i] = new Magic.ComboBox.Val($values[$i].toString());
			}
		}
		if ($def.$sorted)
			Arrays.sort($def.$values);
		JComboBox $box = new JComboBox($def.$model = new DefaultComboBoxModel($def.$values));
		
		for (int $i = 0; $i < $def.$values.length; $i++) {
			if ($def.$values[$i].$selected) {
				$box.setSelectedIndex($i);
				break;
			}
		}
		$order.add($box);
		return $box;
	}

	private JComponent createTaskPane(Panes $def) {
		JXTaskPaneContainer $taskPaneContainer = new JXTaskPaneContainer();
		
		for (Magic.Pane $pane : $def.$panes) {
			Magic $d = new Magic();
			$d.$main = $pane.$content;
			MagicPanel $inner = new MagicPanel($d);
			JXTaskPane $taskPane = new JXTaskPane();
			$taskPane.setTitle($pane.$title.$text);
			$taskPane.add($inner);
			$componentsByName.putAll($inner.getNamedComponents());
			$taskPaneContainer.add($taskPane);
			$taskPane.setCollapsed(!$pane.$expanded);
			$taskPane.setAnimated($pane.$animated);
			
		}
		JScrollPane $scrollPane = new JScrollPane($taskPaneContainer);
		$scrollPane.setBorder(BorderFactory.createEmptyBorder());
		return $scrollPane;
	}

	private JTabbedPane createTabbedPane(Tabs $def) {
		JTabbedPane $pane = new JTabbedPane();
		
		for (Magic.Tab $tab : $def.$tabs) {
			Magic $d = new Magic();
			$d.$main = $tab.$content;
			MagicPanel $inner = new MagicPanel($d);
			$inner.setBorder(BorderFactory.createLineBorder(Color.gray));
			$pane.addTab($tab.$title.$text, $inner);
			$componentsByName.putAll($inner.getNamedComponents());
		}
		
		$order.add($pane);
		return $pane;
	}

	private JPasswordField createPasswordField(final Magic.Password $def) {
		JPasswordField $field = new JPasswordField();
		
		if ($def.$onaction != null) {
			$field.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					$def.$onaction.call($handler);
				}
			});
		}
		
		$order.add($field);
		return $field;
	}

	private JComponent createTextArea(Magic.TextArea $def) {
		JTextArea $area = new JTextArea($def.$text);
		JScrollPane $pane = new JScrollPane($area);
		if ($def.$lineWrap)
			$area.setLineWrap(true);
		$order.add($area);
		$def.$area = $area;
		return $pane;
	}

	private JComponent createTable(final Table $def) {
		String[] $columnHeaders = new String[$def.$columns.length];
		for (int $i = 0; $i < $def.$columns.length; $i++) {
			$columnHeaders[$i] = $def.$columns[$i].$text;
		}
		
		$def.$model = new DefaultTableModel($columnHeaders, 0) {
			private static final long serialVersionUID = -135732270243460558L;

			@Override public Class<?> getColumnClass(int $col) {
				try {
					return $def.$columns[$col].$type.$class;
				} catch (Exception $exc) {
					return Object.class;
				}
			}
		};
		
		$def.$table = new JTable($def.$model);
		
		for (int $i = 0; $i < $def.$columns.length; $i++) {
			if ($def.$columns[$i].$maxWidth != null)
				$def.$table.getColumn($columnHeaders[$i]).setMaxWidth($def.$columns[$i].$maxWidth);
			if ($def.$columns[$i].$minWidth != null)
				$def.$table.getColumn($columnHeaders[$i]).setMinWidth($def.$columns[$i].$minWidth);
		}
		
		JScrollPane $pane = new JScrollPane($def.$table);
		$def.$table.setFillsViewportHeight(true);
		
		if ($def.$sortable) {
			$def.$table.setAutoCreateRowSorter(true);
		}
		if ($def.$gridColor != null) {
			$def.$table.setShowGrid(true);
			$def.$table.setGridColor($def.$gridColor.$color);
		}
		$order.add($def.$table);
		
		return $pane;
	}


	private JComponent createSpinner(Numeric $def) {
		JSpinner $spinner = new JSpinner();
		SpinnerNumberModel $model = new SpinnerNumberModel(
				$def.$value,
				$def.$min,
				$def.$max,
				$def.$step);
		$spinner.setModel($model);
		$order.add($spinner);
		return $spinner;
	}


	private JComponent createCheckBox(CheckBox $def) {
		JCheckBox $checkBox = new JCheckBox($def.$text);
		$order.add($checkBox);
		return $checkBox;
	}

	private JComponent createTextField(final TextField $def) {
		final JTextField $textField = new JTextField($def.$text);
		$order.add($textField);
		if ($def.$validator != null) {
			try {
				@SuppressWarnings("unchecked")
				final Validator<String> $validator = (Validator<String>) $def.$validator.$class.newInstance();
				$textField.addFocusListener(new FocusListener() {

					@Override
					public void focusGained(FocusEvent $ev) {
						if ($def.$dirty && $validator.validate($textField.getText())) {
							markClean($def);
						}
					}

					@Override
					public void focusLost(FocusEvent $ev) {
						if (!$validator.validate($textField.getText())) {
							markFaulty($def, $validator.getMessage());
						} else if ($def.$dirty) {
							markClean($def);
						}
					}
				});
				$textField.addKeyListener(new KeyListener() {
					@Override public void keyTyped(KeyEvent $ev) {}
					@Override public void keyPressed(KeyEvent $ev) {}
					@Override public void keyReleased(KeyEvent $ev) {
						if ($def.$dirty) {
							if ($validator.validate($textField.getText())) {
								markClean($def);
							}
						}
					}
				});
			} catch (Exception $exc) {
				// TODO: Exception handling
			}
		}
		
		if ($def.$action != null) {
			try {
				final MagicAction $action = (MagicAction) $def.$action.$class.newInstance();
				$textField.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						$action.performAction(MagicPanel.this, $textField, null);
					}
				});
			} catch (Exception $exc) {
				System.err.println($exc);
			}
		}

		if ($def.$onaction != null) {
			$textField.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					$def.$onaction.call($handler);
				}
			});
		}
		
		
		return $textField;
	}


	private JComponent createButton(final Button $def) {
		final JButton $button = new JButton($def.$text);
		
		if ($def.$action != null) {
			try {
				final MagicAction $action = (MagicAction) $def.$action.$class.newInstance();
				$button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						$action.performAction(MagicPanel.this, $button, null);
					}
				});
			} catch (Exception $exc) {
				System.err.println($exc);
			}
		}

		if ($def.$onaction != null) {
			$button.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					$def.$onaction.call($handler);
				}
			});
		}
		
		$order.add($button);
		return $button;
	}


	private static JLabel createLabel(Magic.Label $def) {
		int $align = JLabel.LEFT;
		if ("right".equals($def.$textAlign)) {
			$align = JLabel.RIGHT;
		} else if ("center".equals($def.$textAlign)) {
			$align = JLabel.CENTER;
		}
		JLabel $label = new JLabel($def.$text, $align);
		if ("top".equals($def.$verticalAlignment)) {
			$label.setVerticalAlignment(JLabel.TOP);
		} else if ("bottom".equals($def.$verticalAlignment)) {
			$label.setVerticalAlignment(JLabel.BOTTOM);
		}
		if ($def.$background != null) {
			$label.setOpaque(true);
		}
		if ($def.$fontWeight != null && $def.$fontWeight.$bold) {
			$label.setFont($label.getFont().deriveFont(Font.BOLD));
		}
		if ($def.$fontStyle != null && $def.$fontStyle.$italic) {
			$label.setFont($label.getFont().deriveFont(Font.ITALIC));
		}
		if ($def.$fontSize != null && $def.$fontSize.$unit == Magic.Unit.PIXEL) {
			$label.setFont($label.getFont().deriveFont((float)$def.$fontSize.$value));
		}
		return $label;
	}

	class MagicLayout implements LayoutManager {

		@Override
		public void addLayoutComponent(String $name, Component $c) {
			// throw new UnsupportedOperationException();	
		}

		@Override
		public void removeLayoutComponent(Component $c) {
			// throw new UnsupportedOperationException();
		}

		@Override
		public Dimension preferredLayoutSize(Container $c) {
			return minimumLayoutSize($c);
		}

		@Override
		public Dimension minimumLayoutSize(Container $c) {
			
			Dimension $minSize = new Dimension(
					minimumLayoutWidth($def.$main[0]),
					minimumLayoutHeight($def.$main[0]));
			return $minSize;
		}
		
		private int minimumLayoutHeight(Magic.Container $container) {
			int $height = 0;
			
			for (Magic.Component $c : $container.$content) {
				int $size = 0;
				switch ($c.$height.$unit) {
				case AUTO:
					if ($c instanceof Magic.Container)
						$size = (int) calculateVerticalSize((Magic.Container) $c);
					break;
				case PIXEL:
					$size = $c.$height.$value;
					break;
				case STAR:
					if ($c instanceof Magic.Container)
						$size = minimumLayoutHeight((Magic.Container) $c);
					else
						$size = $c.$minHeight.$value;
					break;
				}
				$height = $container instanceof Magic.HBox
						? Math.max($height, $size)
						: $height + $size;
			}
			return $height;
		}
		
		private int minimumLayoutWidth(Magic.Container $container) {
			int $width = 0;
			
			for (Magic.Component $c : $container.$content) {
				int $size = 0;
				switch ($c.$width.$unit) {
				case AUTO:
					if ($c instanceof Magic.Container)
						$size = (int) calculateHorizontalSize((Magic.Container) $c);
					break;
				case PIXEL:
					$size = $c.$width.$value;
					break;
				case STAR:
					if ($c instanceof Magic.Container)
						$size = minimumLayoutWidth((Magic.Container) $c);
					else
						$size = $c.$minWidth.$value;
					break;
				}
				$width = $container instanceof Magic.VBox
						? Math.max($width, $size)
						: $width + $size;
			}
			
			return $width;
		}

		@Override
		public void layoutContainer(Container $c) {
			if ($def.$main.length > 0) {
				layout($def.$main[0], 0, 0, $c.getWidth(), $c.getHeight());
			}
		}

		private Magic.Dimension layout(Magic.Container $container, double $x, double $y, double $w, double $h) {
			double $boxWidth = 0;
			double $boxHeight = 0;

			double $remainingWidth = $w;
			double $remainingHeight = $h;
			
			double $starsWidth = 0;
			double $starsHeight = 0;
			
			double $consumedWidth = 0;
			double $consumedHeight = 0;
			
			if ($container instanceof Magic.HBox) {
				for (Magic.Component $c : $container.$content) {
					if ($c.$width.$unit == Magic.Unit.STAR) {
						$starsWidth += $c.$width.$value;
					} else if ($c.$width.$unit == Magic.Unit.PIXEL) {
						$consumedWidth += $c.$width.$value;
					} else if ($c.$width.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							double $s = calculateHorizontalSize((Magic.Container)$c);
							$consumedWidth += $s;
						}
					}
				}
				$starsHeight = 1;
			} else if ($container instanceof Magic.VBox) {
				for (Magic.Component $c : $container.$content) {
					if ($c.$height.$unit == Magic.Unit.STAR) {
						$starsHeight += $c.$height.$value;
					} else if ($c.$height.$unit == Magic.Unit.PIXEL) {
						$consumedHeight += $c.$height.$value;
					} else if ($c.$height.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							double $s = calculateVerticalSize((Magic.Container)$c);
							$consumedHeight += $s;
						}
					}
				}
				$starsWidth = 1;
			}

			if ($h > 0) {
				$remainingHeight = $h - $consumedHeight;
			} else {
				$remainingHeight = 0;
				$h = $consumedHeight;
			}
			if ($w > 0) {
				$remainingWidth = $w - $consumedWidth;
			} else {
				$remainingWidth = 0;
				$w = $consumedWidth;
			}
			
			
			for (Magic.Component $c : $container.$content) {
				double $newWidth = 0;
				double $newHeight = 0;
				double $newPosX = $x;
				double $newPosY = $y;
				
				if ($c.$width.$unit == Magic.Unit.STAR) {
					$newWidth = $c.$width.$value / $starsWidth * $remainingWidth;
				} else if ($c.$width.$unit == Magic.Unit.PIXEL) {
					$newWidth = $c.$width.$value;
				}
				
				if ($c.$height.$unit == Magic.Unit.STAR) {
					$newHeight = $c.$height.$value / $starsHeight * $remainingHeight;
				} else if ($c.$height.$unit == Magic.Unit.PIXEL) {
					$newHeight = $c.$height.$value;
				}

				if ($container instanceof Magic.HBox) {
					$newPosX = $x + $boxWidth;
				} else if ($container instanceof Magic.VBox) {
					$newPosY = $y + $boxHeight;
				}
				
				if ($c instanceof Magic.Container) {
					Magic.Dimension $dim = layout((Magic.Container)$c, $newPosX, $newPosY, $newWidth, $newHeight);
					// System.out.printf("\n%f,%f %f,%f [%s,%s] (%s)\n", $dim.$width, $dim.$height, $newWidth, $newHeight, $c.$width.$unit, $c.$height.$unit, $c.$name);
					if ($c.$width.$unit == Magic.Unit.AUTO)
						$newWidth = $dim.$width;
					if ($c.$height.$unit == Magic.Unit.AUTO)
						$newHeight = $dim.$height;
				} else if ($c.$component != null) {
					$c.$component.setLocation((int)$newPosX, (int)$newPosY);
					$c.$component.setSize((int)$newWidth, (int)$newHeight);
				}
				
				if ($container instanceof Magic.HBox) {
					$boxWidth += $newWidth;
					$boxHeight = Math.max($boxHeight, $newHeight);
				} else if ($container instanceof Magic.VBox) {
					$boxHeight += $newHeight;
					$boxWidth = Math.max($boxWidth, $newWidth);
				}
			}
			
			return new Magic.Dimension($boxWidth, $boxHeight);
		}
		
		double calculateVerticalSize(Magic.Container $container) {
			double $height = 0;
			if ($container instanceof Magic.HBox) {
				for (Magic.Component $c : $container.$content) {
					if ($c.$height.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							$height = Math.max($height, calculateVerticalSize((Magic.Container)$c));
						}
					} else if ($c.$height.$unit == Magic.Unit.PIXEL) {
						$height = Math.max($height, $c.$height.$value);
					}
				}
			} else {
				for (Magic.Component $c : $container.$content) {
					if ($c.$height.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							$height += calculateVerticalSize((Magic.Container)$c);
						}
					} else if ($c.$height.$unit == Magic.Unit.PIXEL) {
						$height += $c.$height.$value;
					}
				}
			}
			return $height;
		}

		double calculateHorizontalSize(Magic.Container $container) {
			double $width = 0;
			if ($container instanceof Magic.HBox) {
				for (Magic.Component $c : $container.$content) {
					if ($c.$width.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							$width = Math.max($width, calculateHorizontalSize((Magic.Container)$c));
						}
					} else if ($c.$width.$unit == Magic.Unit.PIXEL) {
						$width = Math.max($width, $c.$width.$value);
					}
				}
			} else {
				for (Magic.Component $c : $container.$content) {
					if ($c.$width.$unit == Magic.Unit.AUTO) {
						if ($c instanceof Magic.Container) {
							$width += calculateHorizontalSize((Magic.Container)$c);
						}
					} else if ($c.$width.$unit == Magic.Unit.PIXEL) {
						$width += $c.$width.$value;
					}
				}
			}
			return $width;
		}
	}
	
	class MagicFocusTraversalPolicy extends FocusTraversalPolicy {
		
		@Override
		public Component getComponentAfter(Container $focusCycleRoot, Component $component) {
			int $index = ($order.indexOf($component) + 1) % $order.size();
			return $order.get($index);
		}

		@Override
		public Component getComponentBefore(Container $focusCycleRoot, Component $component) {
			int $index = ($order.indexOf($component) + $order.size() - 1) % $order.size();
			return $order.get($index);
		}

		@Override
		public Component getFirstComponent(Container aContainer) {
			return $order.get(0);
		}

		@Override
		public Component getLastComponent(Container aContainer) {
			return $order.get($order.size() - 1);
		}

		@Override
		public Component getDefaultComponent(Container aContainer) {
			return $order.get(0);
		}
	}
	
	static class Lock {
		public boolean $locked;
		
		public Lock(boolean $state) { $locked = $state; }
	}
	
	private static void bind(final JCheckBox $c, final Object $object, final String $property) {
		final Lock $lock = new Lock(false);
	
		try {
			Object $checked = AbusingFunctions.callback($object, "get" + AbusingStrings.capitalize($property)).call();
			if ($checked instanceof Boolean) {
				$c.setSelected((Boolean) $checked);
			} else {
				$c.setSelected(false);
			}
		} catch (Exception $exc) {
			System.err.println("Error retrieving Boolean from " + $object);
		}
		
		$c.addItemListener(new ItemListener() {
			@Override public void itemStateChanged(ItemEvent $ev) {
				if (!$lock.$locked) {
					$lock.$locked = true;
					String $name = "set" + AbusingStrings.capitalize($property);
					Method $m = null;
					try {
						try {
							$m = $object.getClass().getMethod($name, boolean.class);
						} catch (NoSuchMethodException $exc) {
							$m = $object.getClass().getMethod($name, Boolean.class);
						}

						try {
							$m.invoke($object, $c.isSelected());
						} catch (Exception $exc) {
							System.err.println("boolean setter is broken"); // TODO
						}
					} catch (NoSuchMethodException $exc) {
						System.err.println("No boolean setter found"); // TODO
					}
					$lock.$locked = false;
				}
			}
			
		});

		Function<PropertyChangeListener,Void> $f = AbusingFunctions.function($object, "addPropertyChangeListener", PropertyChangeListener.class, void.class);
		if ($f != null) {
			$f.call(new PropertyChangeListener() {
				@Override public void propertyChange(PropertyChangeEvent $ev) {
					if ($property.equals($ev.getPropertyName())) {
						if (!$lock.$locked) {
							$lock.$locked = true;
							$c.setSelected((Boolean) $ev.getNewValue());
							$lock.$locked = false;
						}
					}
				}
			});
		}
	}
	
	private Map<JTextComponent,DocumentListener> $L = new HashMap<JTextComponent,DocumentListener>();
	
	private void bind(final JTextComponent $c, final Object $object, final String $property) {

		final Lock $lock = new Lock(false);

		try {
			Object $value = AbusingFunctions.callback($object, "get" + AbusingStrings.capitalize($property)).call();
			$c.setText($value == null ? "" : $value.toString());
		} catch (Exception $exc) {
			System.err.println("Error retrieving String from " + $object);
			$exc.printStackTrace(System.err); // TODO
		}
		
		DocumentListener $l;
		$c.getDocument().addDocumentListener($l = new DocumentListener() {
			@Override public void insertUpdate(DocumentEvent e) { change(); }
			@Override public void removeUpdate(DocumentEvent e) { change(); }
			@Override public void changedUpdate(DocumentEvent e) { change(); }
			public void change() {
				if (!$lock.$locked) {
					$lock.$locked = true;
					AbusingFunctions.callback($object, "set" + AbusingStrings.capitalize($property)).call($c.getText());
					$lock.$locked = false;
				}
			}
		});
		$L.put($c, $l);

		Function<PropertyChangeListener,Void> $f = AbusingFunctions.function($object, "addPropertyChangeListener", PropertyChangeListener.class, void.class);
		if ($f != null) {
			$f.call(new PropertyChangeListener() {
				@Override public void propertyChange(PropertyChangeEvent $ev) {
					if ($property.equals($ev.getPropertyName())) {
						if (!$lock.$locked) {
							$lock.$locked = true;
							$c.setText($ev.getNewValue().toString());
							$lock.$locked = false;
						}
					}
				}
			});
		}
	}
	
	public void bind(final String $componentName, final Object $object, final String $property) {
		JComponent $c = getComponent($componentName);
		if ($c instanceof JTextComponent) {
			bind((JTextComponent) $c, $object, $property);
		} else if ($c instanceof JCheckBox) {
			bind((JCheckBox) $c, $object, $property);
		}
	}
	
	public void bindTable(String $tableName, Object $object, final String $property, final String[] $properties) {
		DefaultTableModel $model = getTableModel($tableName);
		
		Method[] $getters = null;
		
		try {
			Method $getter = $object.getClass().getMethod("get" + AbusingStrings.capitalize($property));
			Object $list = $getter.invoke($object);
			if ($list.getClass().isArray()) {
				$list = AbusingArrays.array((Object[]) $list);
			}
			if ($list instanceof Iterable<?>) {
				for (Object $x : (Iterable<?>) $list) {
					Object[] $data = new Object[$properties.length];
					if ($getters == null) {
						$getters = new Method[$properties.length];
						for (int $i = 0; $i < $properties.length; $i++) {
							$getters[$i] = $x.getClass().getMethod("get" + AbusingStrings.capitalize($properties[$i]));
						}
					}
					for (int $i = 0; $i < $properties.length; $i++) {
						$data[$i] = $getters[$i].invoke($x);
					}
					$model.addRow($data);
				}
			} else {
				System.err.println("Property not iterable: " + $property);
			}
		} catch (Exception $exc) {
			$exc.printStackTrace(); // TODO
		}
		
		// Function<PropertyChangeListener,Void> $f = AbusingFunctions.function($object, "addPropertyChangeListener", PropertyChangeListener.class, void.class);
	}
	
	public void clearBindings() {
		for (Magic.Component $m : $componentsByName.values()) {
			JComponent $c = $m.$component;
			if ($c instanceof JTextComponent) {
				clearBindings((JTextComponent) $c);
			} else if ($c instanceof JCheckBox) {
				clearBindings((JCheckBox) $c);
			}
		}
	}
	
	private static void clearBindings(JCheckBox $c) {
		for (ItemListener $l : $c.getListeners(ItemListener.class)) {
			$c.removeItemListener($l);
		}
	}
	
	private void clearBindings(JTextComponent $c) {
		$c.getDocument().removeDocumentListener($L.get($c));
	}
	
	public Map<String,Magic.Component> getNamedComponents() {
		return Collections.unmodifiableMap($componentsByName);
	}
	
	public JComponent getComponent(String $name) {
		Magic.Component $c = $componentsByName.get($name);
		if ($c instanceof Magic.Table) {
			return ((Magic.Table)$c).$table;
		} else if ($c == null) {
			return null;
		}
		return $c.$component;
	}
	
	public JButton getButton(String $name) {
		Component $c = getComponent($name);
		if ($c instanceof JButton) {
			return (JButton) $c;
		}
		// TODO: Warning
		return null;
	}

	/**
	 * Retrieves the component with $name of type $class.
	 * 
	 * @param $name The name of the component.
	 * @param $class The class of the component (e.g. <code>JButton.class</code>).
	 * 
	 * @return The component with $name of type $class, or null.
	 */
	public <C extends Component> C getComponent(String $name, Class<C> $class) {
		try {
			@SuppressWarnings("unchecked")
			C $c = (C) getComponent($name);
			return $c;
		} catch (ClassCastException $exc) {
			// TODO: Warning
		}
		return null;
	}
	
	public JTextField getTextField(String $name) {
		Component $c = getComponent($name);
		if ($c instanceof JTextField) {
			return (JTextField) $c;
		}
		// TODO: Warning
		return null;
	}
	
	public JTextArea getTextArea(String $name) {
		return ((Magic.TextArea)getComponentMagic($name)).$area;
	}

	public JTextComponent getTextComponent(String $name) {
		Component $c = getComponent($name);
		if ($c instanceof JTextComponent) {
			return (JTextComponent) $c;
		}
		// TODO: Warning
		return null;
	}
	
	
	private void markFaulty(Magic.Component $def, String $message) {
		$def.$dirty = true;
		$def.$background = new Magic.Color($def.$component.getBackground());
		$def.$component.setBackground($dirty);
		Balloon $balloon = $balloonFactory.newBalloonTip($def.$component, $message);
		$balloon.show(2000);
	}
	
	public void markFaulty(String $name, String $message) {
		markFaulty(getComponentMagic($name), $message);
	}
	
	private static void markClean(Magic.Component $def) {
		if ($def.$dirty) {
			$def.$dirty = false;
			$def.$component.setBackground($def.$background.$color);
		}
	}
	
	public void markClean(String $name) {
		markClean(getComponentMagic($name));
	}
	
	public void markAllClean() {
		for (Magic.Component $component : $componentsByName.values()) {
			markClean($component.$name);
		}
	}
	
	public boolean isDirty(String $name) {
		return getComponentMagic($name).$dirty;
	}
	
	public Magic.Component getComponentMagic(String $name) {
		return $componentsByName.get($name);
	}
	
	public Set<Magic.Component> getGroup(String $name) {
		return Collections.unmodifiableSet($groups.get($name));
	}
	
	public DefaultTableModel getTableModel(String $name) {
		return ((Magic.Table)$componentsByName.get($name)).$model;
	}
	
	public DefaultComboBoxModel getComboBoxModel(String $name) {
		return ((Magic.ComboBox)$componentsByName.get($name)).$model;
	}
	
	public void enable(String... $names) {
		for (String $name : $names) {
			getComponent($name).setEnabled(true);
		}
	}
	
	public void enableGroup(String $name) {
		Set<Magic.Component> $components = $groups.get($name);
		if ($components != null) {
			for (Magic.Component $component : $components) {
				$component.$component.setEnabled(true);
			}
		}
	}
	
	public void enableAll() {
		for (Magic.Component $component : $componentsByName.values()) {
			$component.$component.setEnabled(true);
		}
	}
	
	public void disable(String... $names) {
		for (String $name : $names) {
			getComponent($name).setEnabled(false);
		}
	}
	
	public void disableGroup(String $name) {
		Set<Magic.Component> $components = $groups.get($name);
		if ($components != null) {
			for (Magic.Component $component : $components) {
				$component.$component.setEnabled(false);
			}
		}
	}
	
	public void disableAll() {
		for (Magic.Component $component : $componentsByName.values()) {
			$component.$component.setEnabled(false);
		}
	}
	
	public void show(String $name) {
		getComponent($name).setVisible(true);
	}
	
	public void showGroup(String $name) {
		Set<Magic.Component> $components = $groups.get($name);
		if ($components != null) {
			for (Magic.Component $component : $components) {
				$component.$component.setVisible(true);
			}
		}
	}
	
	/**
	 * Shows all components in this MagicPanel (only has an effect if something is hidden).
	 */
	public void showAll() {
		for (Magic.Component $component : $componentsByName.values()) {
			$component.$component.setVisible(true);
		}
	}
	
	/**
	 * Hide the components which has the given $name.
	 */
	public MagicPanel hide(String $name) {
		if ($componentsByName.containsKey($name)) {
			getComponent($name).setVisible(false);
		}
		return this;
	}
	
	/**
	 * Hide a group of components in this MagicPanel.
	 */
	public MagicPanel hideGroup(String $name) {
		Set<Magic.Component> $components = $groups.get($name);
		if ($components != null) {
			for (Magic.Component $component : $components) {
				$component.$component.setVisible(false);
			}
		}
		return this;
	}
	
	/**
	 * Hide all components in this MagicPanel.
	 */
	public void hideAll() {
		for (Magic.Component $component : $componentsByName.values()) {
			$component.$component.setVisible(false);
		}
	}
	
	/**
	 * Returns a MagicComponent-Object, which can be used to manipulate arbitrary components.
	 */
	@Since("2011-07-26")
	public MagicComponents $(String $name) {
		if ($name.charAt(0) == ':') {
			List<Magic.Component> $list = new LinkedList<Magic.Component>();
			String $className = $name.substring(1);
			for (Magic.Component $m : $componentsByName.values()) {
				if ($m.$component != null) {
					Class<?> $class = $m.$component.getClass();
					if ($class.getSimpleName().equals($className) || $class.getCanonicalName().equals($className)) {
						$list.add($m);
					}
				}
			}
			return new MagicComponents($list);
		}
		
		if ($componentsByName.containsKey($name)) {
			return new MagicComponents($componentsByName.get($name));
		}
		return new MagicComponents();
	}
}
