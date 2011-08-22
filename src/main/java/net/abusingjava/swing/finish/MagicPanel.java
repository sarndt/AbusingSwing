package net.abusingjava.swing.finish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import net.abusingjava.swing.finish.magic.*;


final public class MagicPanel extends JPanel {
	
	public static enum Orientation {
		VERTICAL, HORIZONTAL, FIXED
	}
	
	private static final long serialVersionUID = -1112524113641835259L;
	
	private final Orientation $orientation;
	private final MagicPanel $main;
	
	Map<String,Component> $componentsByName = new HashMap<String,Component>();
	ArrayList<Component> $myComponents = new ArrayList<Component>();
	
	public MagicPanel(final Panel $panel) {
		$main = this;
		this.$orientation = determineOrientation($panel.getContainer());
		setLayout(new MagicLayoutManager(this, this, $panel.getContainer()));
		buildPanel($panel.getContainer());
	}
	
	public MagicPanel(final MagicPanel $parent, final Container $container) {
		this.$main = $parent;
		this.$orientation = determineOrientation($container);
		setLayout(new MagicLayoutManager($parent, this, $container));
		buildPanel($container);
	}
	
	private static Orientation determineOrientation(final Container $container) {
		if ($container instanceof HBox) {
			return Orientation.HORIZONTAL;
		} else if ($container instanceof VBox) {
			return Orientation.VERTICAL;
		}
		return Orientation.FIXED;
	}
	
	private void buildPanel(final Container $container) {
		for (Component $c : $container) {
			$c.create($main, this);
			System.out.println($c.getJComponent());
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
		
		
		
	}
	
}
