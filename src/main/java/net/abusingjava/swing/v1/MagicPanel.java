package net.abusingjava.swing.v1;

import java.io.InputStream;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.v1.components.XPanelDefinition;
import net.abusingjava.xml.AbusingXML;

@Author("Julian Fleischer")
@Version(value = "1.0", date = "2011-10-23")
@Since(version = "1.0", value = "2011-10-15")
public class MagicPanel {

	@SuppressWarnings("unused")
	final private XPanelDefinition $definition;
	
	MagicPanel(final InputStream $stream) {
		$definition = AbusingXML.loadXML($stream, XPanelDefinition.class);
	}
}
