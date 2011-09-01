package net.abusingjava.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.jdesktop.beansbinding.*;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.JTableBinding.ColumnBinding;
import org.jdesktop.swingbinding.SwingBindings;

import net.abusingjava.AbusingReflection;
import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.magic.*;
import net.abusingjava.swing.magic.Binding.Property;
import net.abusingjava.swing.magic.Cards.Card;
import net.abusingjava.swing.magic.Panes.Pane;
import net.abusingjava.swing.magic.Style.Rule;
import net.abusingjava.swing.magic.Table.Filter;
import net.abusingjava.swing.magic.Tabs.Tab;
import net.abusingjava.swing.magic.Binding;
import net.abusingjava.xml.AbusingXML;
import net.abusingjava.xml.XmlElement;

/**
 * A MagicWindow is a JPanel that is built according to a specified definition written in XML.
 * <p>
 */
@Author("Julian Fleischer")
@Since(value = "2011-08-15", version = "1.0")
@Version("2011-08-25")
public class MagicPanel extends JPanel {

	public static enum Orientation {
		VERTICAL, HORIZONTAL, FIXED
	}

	private static final long serialVersionUID = -1112524113641835259L;

	private final Orientation $orientation;
	private final MagicPanel $main;
	private final Container $definition;
	private final Panel $panel;

	Map<String,Component> $componentsByName = new HashMap<String,Component>();
	Map<String,JPopupMenu> $popupMenus = new HashMap<String,JPopupMenu>();
	ArrayList<Component> $myComponents = new ArrayList<Component>();

	private Object $invocationHandler = this;

	public MagicPanel(final InputStream $stream) {
		if ($stream == null) {
			throw new IllegalArgumentException("$stream may not be null.");
		}
		$panel = AbusingXML.loadXML($stream, Panel.class);

		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		this.$definition = $panel.getContainer();

		this.$definition.create(this, null);

		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));

		applyStyles($panel.getContainer());
		makeMenus();
		buildPanel();
		bind();
	}

	public MagicPanel(final Panel $panel) {
		if ($panel == null) {
			throw new IllegalArgumentException("$panel must not be null.");
		}
		this.$panel = $panel;

		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		this.$definition = $panel.getContainer();

		this.$definition.create(this, null);

		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));

		applyStyles($panel.getContainer());
		makeMenus();
		buildPanel();
		bind();
	}

	public MagicPanel(final MagicPanel $main, final Container $container) {
		if ($main == null) {
			throw new IllegalArgumentException("Won’t construct a panel which is not the root panel without a parent ($main may not be null).");
		}
		this.$panel = $main.getDefinitionPanel();

		this.$main = $main;
		this.$orientation = determineOrientation($container);
		this.$definition = $container;

		setLayout(new MagicLayoutManager($main, this, $container));

		buildPanel();
	}

	private void makeMenus() {
		for (Menu $m : $panel.getMenus()) {
			JPopupMenu $menu = buildPopupMenu($m);
			$popupMenus.put($m.getName(), $menu);
		}
	}

	public JPopupMenu getPopupMenu(final String $name) {
		return $popupMenus.get($name);
	}

	private void bind() {

		for (Component $c : $componentsByName.values()) {
			if ($c instanceof TextField) {
				TextField $f = (TextField) $c;
				if ($f.hasFilter()) {
					final Component $t = $componentsByName.get($f.getFilterTableName());
					if ($t instanceof Table) {
						final Filter $filter = ((Table)$t).newFilter($f.getFilterColumns());
						final JTextField $tf = (JTextField) $f.getRealComponent();
						$tf.addKeyListener(new KeyAdapter() {
							@Override
							public void keyReleased(final KeyEvent $ev) {
								$filter.setFilterString($tf.getText());
								((Table)$t).updateFilters();
							}
						});
					}
				}
			}
		}
	}

	private static Orientation determineOrientation(final Container $container) {
		if ($container instanceof HBox) {
			return Orientation.HORIZONTAL;
		} else if ($container instanceof VBox) {
			return Orientation.VERTICAL;
		}
		return Orientation.FIXED;
	}

	public Container getDefinition() {
		return $definition;
	}

	@Override
	public boolean isOptimizedDrawingEnabled() {
		return $orientation != Orientation.FIXED;
	}

	private void buildPanel() {
		for (Component $c : $definition) {
			$c.create($main, this);
			add($c.getJComponent());
		}
	}

	int $noName = 0;

	public void registerComponent(String $name, final Component $component) {
		if ($name == null) {
			$name = "#" + $noName++;
		}
		$componentsByName.put($name, $component);
	}

	public MagicComponents $(final String $selector) {
		if ($selector.equals("*")) {
			return new MagicComponents($componentsByName.values());
		} else if ($selector.startsWith("#")) {
			Component $c = $componentsByName.get($selector.substring(1));
			if ($c != null) {
				return new MagicComponents($c);
			}
			return new MagicComponents();
		} else if ($selector.startsWith(".")) {
			String $className = $selector.substring(1);
			List<Component> $list = new LinkedList<Component>();
			for (Component $c : $componentsByName.values()) {
				if ($c.hasClass($className)) {
					$list.add($c);
				}
			}
			return new MagicComponents($list);
		} else {
			List<Component> $list = new LinkedList<Component>();
			for (Component $c : $componentsByName.values()) {
				if ($c.getClass().getAnnotation(XmlElement.class).value().equals($selector)) {
					$list.add($c);
				}
			}
			return new MagicComponents($list);
		}
	}

	public MagicComponents $(final Class<?> $componentClass) {
		List<Component> $components = new LinkedList<Component>();
		for (Component $c : $componentsByName.values()) {
			if (($componentClass.isAssignableFrom($c.getJComponent().getClass()))
					|| ($componentClass.isAssignableFrom($c.getRealComponent().getClass()))
					|| ($componentClass.isAssignableFrom($c.getClass()))) {
				$components.add($c);
			}
		}
		return new MagicComponents($components);
	}

	public Orientation getOrientation() {
		return $orientation;
	}

	public MagicPanel setInvocationHandler(final Object $object) {
		$invocationHandler = $object;
		return this;
	}

	public Object getInvocationHandler() {
		return $invocationHandler;
	}

	public Panel getDefinitionPanel() {
		return $panel;
	}

	static JMenu buildMenu(final Menu $menuDef) {
		JMenu $menu = new JMenu($menuDef.getTitle());

		for (MenuItem $item : $menuDef.getMenuItems()) {
			if ($item instanceof Menu) {
				$menu.add(buildMenu((Menu) $item));
			} else {
				$menu.add(new JMenuItem($item.getTitle()));
			}
		}

		return $menu;
	}

	static JPopupMenu buildPopupMenu(final Menu $menuDef) {
		JPopupMenu $menu = new JPopupMenu();

		for (MenuItem $item : $menuDef.getMenuItems()) {
			if ($item instanceof Menu) {
				$menu.add(buildMenu((Menu) $item));
			} else {
				$menu.add(new JMenuItem($item.getTitle()));
			}
		}
		return $menu;
	}

	private void applyStyles(final Container $container) {
		applyStyle($container);
		for (Component $c : $container) {
			if ($c instanceof Container) {
				applyStyles((Container) $c);
			} else if ($c instanceof Tabs) {
				for (Tab $tab : (Tabs)$c) {
					applyStyle($tab);
					applyStyles($tab.getContainer());
				}
			} else if ($c instanceof Cards) {
				for (Card $card : (Cards)$c) {
					applyStyle($card);
					applyStyles($card.getContainer());
				}
			} else if ($c instanceof Panes) {
				for (Pane $pane : (Panes)$c) {
					applyStyle($pane);
					applyStyles($pane.getContainer());
				}
			} else {
				applyStyle($c);
			}
		}
	}
	
	private void applyStyle(final Object $o) {
		
		for (Rule $rule : $panel.getStyleRules()) {
			if ($rule.matches($o)) {
				Field[] $fields = AbusingReflection.fields($o.getClass());
				for (Field $field : $fields) {
					$field.setAccessible(true);
					try {
						if ($field.get($o) == null) {
							$field.set($o, $rule.get($field.getName()));
						}
					} catch (IllegalAccessException $exc) {}
					
					$field.setAccessible(false);
				}
			}
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public MagicPanel bind(final String $bindingName, final Object $object) {
		Binding $b = $panel.getBinding($bindingName);
		if ($b == null) {
			System.err.println("Binding: no such binding found");
			return this;
		}

		if ($b.isTableBinding() && ($object instanceof List)) {
			Table $tableDefinition = (Table) $main.$componentsByName.get($b.getTableName());
			JTable $table = $main.$("#" + $b.getTableName()).as(JTable.class);

			$tableDefinition.clearBinding();

			JTableBinding $tableBinding = SwingBindings.createJTableBinding(
					AutoBinding.UpdateStrategy.READ_WRITE, (List<?>) $object, $table);

			for (Property $p : $b) {
				ColumnBinding $columnBinding = $tableBinding.addColumnBinding(
						BeanProperty.create($p.getName()));
				$columnBinding.setColumnName($p.getTarget());
				$columnBinding.setColumnClass($tableDefinition.getColumn($p.getTarget()).getJavaType());
			}

			$tableDefinition.setBinding($tableBinding);
			$tableBinding.bind();
		} else {
			$b.clearBinding();

			BindingGroup $bindingGroup = new BindingGroup();

			for (Property $p : $b) {
				JComponent $target = $main.$("#" + $p.getTarget()).as(JComponent.class);

				
				
				String $targetProperty = "";

				if ($target instanceof JTextComponent) {
					$targetProperty = "text";
				} else if ($target instanceof JCheckBox) {
					$targetProperty = "selected";
				}

				AutoBinding $binding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
						$object, BeanProperty.create($p.getName()),
						$target, BeanProperty.create($targetProperty));

				/* try {
					Class<?> $type = $object.getClass().getMethod("get" + AbusingStrings.capitalize($p.getName())).getReturnType();
					if (($type == Integer.class) || ($type == int.class)) {
						$binding.setConverter(new Converter() {

							@Override
							public Object convertForward(final Object $value) {
								return $value.toString();
							}

							@Override
							public Object convertReverse(final Object $value) {
								return Integer.parseInt($value.toString());
							}

						});
					}
				} catch (Exception $exc) {
					System.err.println("Binding: No such source property found." + $exc);
				} */

				$bindingGroup.addBinding($binding);
			}

			$b.setBinding($bindingGroup);
			$bindingGroup.bind();
		}

		return this;
	}

	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		AbusingSwing.showWindow("MagicPanel.xml");
	}

}
