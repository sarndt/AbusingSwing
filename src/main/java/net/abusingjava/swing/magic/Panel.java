package net.abusingjava.swing.magic;

import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("panel")
public class Panel {

	@XmlChildElements({HBox.class, VBox.class, FixedBox.class})
	Container[] $containers = new Container[] {};
	
	@XmlChildElements
	Binding[] $bindings = new Binding[] {};
	
	@XmlChildElements
	Style[] $style = new Style[] {};
	
	
	public Container getContainer() {
		return $containers[0];
	}
	
	public Binding getBinding(final String $name) {
		for (Binding $b : $bindings) {
			if ($b.getName().equals($name)) {
				return $b;
			}
		}
		return null;
	}
	
}
