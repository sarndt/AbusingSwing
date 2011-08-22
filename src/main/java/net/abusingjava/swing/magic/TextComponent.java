package net.abusingjava.swing.magic;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlTextContent;


abstract public class TextComponent extends Component {
	
	@XmlTextContent
	String $text = "";
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		
		
		super.create($main, $parent);
	}
	
}