package net.abusingjava.swing;

import java.awt.Dimension;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import net.abusingjava.swing.magic.*;
import net.abusingjava.xml.AbusingXML;


public class MagicPanel extends JPanel {
	
	public static enum Orientation {
		VERTICAL, HORIZONTAL, FIXED
	}
	
	private static final long serialVersionUID = -1112524113641835259L;
	
	private final Orientation $orientation;
	private final MagicPanel $main;
	private final Container $definition;
	
	Map<String,Component> $componentsByName = new HashMap<String,Component>();
	ArrayList<Component> $myComponents = new ArrayList<Component>();

	private Object $invocationHandler = null;
	
	public MagicPanel(final InputStream $stream) {
		Panel $panel = AbusingXML.loadXML($stream, Panel.class);

		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		this.$definition = $panel.getContainer();

		this.$definition.create(this, null);

		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));
		
		buildPanel();
	}
	
	public MagicPanel(final Panel $panel) {
		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		this.$definition = $panel.getContainer();

		this.$definition.create(this, null);
		
		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));
		
		buildPanel();
	}
	
	public MagicPanel(final MagicPanel $parent, final Container $container) {
		this.$main = $parent;
		this.$orientation = determineOrientation($container);
		this.$definition = $container;
		
		setLayout(new MagicLayoutManager($parent, this, $container));
		
		buildPanel();
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
			
		}
		return null;
	}
	
	public MagicComponents $(final Class<?> $componentClass) {
		List<Component> $components = new LinkedList<Component>();
		for (Component $c : $componentsByName.values()) {
			if (($c.getJComponent().getClass() == $componentClass)
					|| ($c.getRealComponent().getClass() == $componentClass)
					|| ($c.getClass() == $componentClass)) {
				$components.add($c);
			}
		}
		return new MagicComponents($components);
	}
	
	public Orientation getOrientation() {
		return $orientation;
	}
	
	public MagicPanel hideAll() {
		$("*").hide();
		return this;
	}
	
	public MagicPanel showAll() {
		$("*").show();
		return this;
	}
	
	public MagicPanel enableAll() {
		$("*").enable();
		return this;
	}
	
	public MagicPanel disableAll() {
		$("*").disable();
		return this;
	}
	
	public static void main(final String... $args) {
		
		JFrame $x = new JFrame();

		JSplitPane $p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JTextArea(), new JTextArea());
		
		$p.setDividerLocation(70);

		$x.setContentPane($p);
		$x.pack();
		
		$x.setMinimumSize(new Dimension(500, 300));
		
		$x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		$x.setVisible(true);
	}

	public MagicPanel setInvocationHandler(final Object $object) {
		$invocationHandler = $object;
		return this;
	}
	
	public Object getInvocationHandler() {
		return $invocationHandler;
	}
	
}
