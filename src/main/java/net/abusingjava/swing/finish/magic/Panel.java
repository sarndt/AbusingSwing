package net.abusingjava.swing.finish.magic;

import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("panel")
public class Panel {

	@XmlChildElements({HBox.class, VBox.class})
	Container[] $container = new Container[] {};
	
	public Container getContainer() {
		return $container[0];
	}
	
}
