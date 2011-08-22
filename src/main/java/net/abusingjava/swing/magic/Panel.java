package net.abusingjava.swing.magic;

import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("panel")
public class Panel {

	@XmlChildElements({HBox.class, VBox.class, FixedBox.class})
	Container[] $containers = new Container[] {};
	
	public Container getContainer() {
		return $containers[0];
	}
	
}
