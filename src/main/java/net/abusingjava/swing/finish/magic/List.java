package net.abusingjava.swing.finish.magic;

import org.jdesktop.swingx.JXList;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("list")
public class List extends Component {

	@Override
	public void create(final MagicPanel $parent) {
		JXList $c = new JXList();
		
		$component = $c;
		
		super.create($parent);
	}
	
	
	
}