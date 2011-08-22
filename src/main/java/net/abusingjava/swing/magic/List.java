package net.abusingjava.swing.magic;

import org.jdesktop.swingx.JXList;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("list")
public class List extends Component {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JXList $c = new JXList();
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
	
	
}