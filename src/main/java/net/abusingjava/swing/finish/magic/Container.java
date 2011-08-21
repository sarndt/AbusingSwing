package net.abusingjava.swing.finish.magic;

import java.util.Iterator;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.arrays.AbusingArrays;
import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlChildElements;

@Author("Julian Fleischer")
@Version("2011-08-21")
@Since(value = "2011-08-21", version = "1.0")
abstract public class Container extends Component implements Iterable<Component> {

	@XmlChildElements({HBox.class, VBox.class, Any.class, Box.class, Button.class,
		Cards.class, CheckBox.class, ComboBox.class, DatePicker.class, List.class,
		Numeric.class, Panel.class, Panes.class, Password.class, ProgressBar.class,
		Slider.class, Table.class, Tabs.class, TextArea.class, TextField.class,
		ToggleButton.class, Tree.class})
	Component[] $components = new Component[] {};

	@Override
	public Iterator<Component> iterator() {
		return AbusingArrays.array($components).iterator();
	}

	@Override
	public void create(final MagicPanel $parent) {
		MagicPanel $c = new MagicPanel($parent, this);
		
		$component = $c;
		
		super.create($parent);
	}
	
}