package net.abusingjava.swing.finish;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.*;

import net.abusingjava.swing.finish.magic.Panel;
import net.abusingjava.swing.finish.magic.Window;
import net.abusingjava.xml.AbusingXML;

public class MagicFactory {

	static Document loadDocument(final InputStream $stream) {
		try {
			final DocumentBuilderFactory $f = DocumentBuilderFactory.newInstance();
			$f.setNamespaceAware(true);
			$f.setValidating(true);
			$f.setXIncludeAware(true);
			final DocumentBuilder $b = $f.newDocumentBuilder();
			$b.setErrorHandler(new ErrorHandler() {
				@Override
				public void error(final SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}

				@Override
				public void warning(final SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}

				@Override
				public void fatalError(final SAXParseException $exc) throws SAXException {
					// TODO: Exception handling
					System.err.printf("%s\n\t(in %s)\n", $exc.getMessage(), $exc.getSystemId());
				}
			});

			$b.setEntityResolver(new EntityResolver() {
				@Override
				public InputSource resolveEntity(final String $publicId, final String $systemId) throws SAXException, IOException {
					if ("-//abusingjava.net//net.abusingjava.swing.MagicPanel//EN".equals($publicId))
						return new InputSource(MagicPanel.class.getResourceAsStream("magic.dtd"));
					return null;
				}
			});
			
			return $b.parse($stream);
		} catch (Exception $exc) {
			throw new RuntimeException($exc);
		}
	}
	
	public static MagicWindow makeWindow(final InputStream $stream) {
		Window $window = AbusingXML.loadXML($stream, Window.class);
		return new MagicWindow($window);
	}
	
	public static MagicPanel makePanel(final InputStream $stream) {
		Panel $panel = AbusingXML.loadXML($stream, Panel.class);
		return new MagicPanel($panel);
	}
	
}
