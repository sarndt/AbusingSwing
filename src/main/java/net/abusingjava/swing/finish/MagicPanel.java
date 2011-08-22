package net.abusingjava.swing.finish;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import net.abusingjava.swing.finish.magic.*;


final public class MagicPanel extends JPanel {
	
	public static enum Orientation {
		VERTICAL, HORIZONTAL, FIXED
	}
	
	private static final long serialVersionUID = -1112524113641835259L;
	
	private final Orientation $orientation;
	private final MagicPanel $main;
	private final Container $definition;
	
	Map<String,Component> $componentsByName = new HashMap<String,Component>();
	ArrayList<Component> $myComponents = new ArrayList<Component>();
	
	public MagicPanel(final Panel $panel) {
		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		this.$definition = $panel.getContainer();
		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));
		
		this.$definition.create(this, null);
		
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
	
	public MagicComponents $(final String $selector) {
		if ($selector.equals("*")) {
			
		} else if ($selector.startsWith("#")) {
			return new MagicComponents($componentsByName.get($selector.substring(1)).getJComponent());
		} else if ($selector.startsWith(".")) {
			
		}
		return null;
	}
	
	public MagicComponents $(final Class<?> $componentClass) {
		return null;
	}
	
	public Orientation getOrientation() {
		return $orientation;
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
	
}
