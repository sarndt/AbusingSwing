package net.abusingjava.swing.finish.magic;

import org.jdesktop.swingx.JXTree;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("tree")
public class Tree extends Component {

	@Override
	public void create(final MagicPanel $parent) {
		JXTree $c = new JXTree();
		
		$component = $c;
		
		super.create($parent);
	}
	
	
	
}
