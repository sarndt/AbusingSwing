package net.abusingjava.swing.magic;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("list")
public class MultiList extends Component {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JList $c = new JList(new DefaultListModel());
		
		$c.setVisibleRowCount(-1);
		$c.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		$realComponent = $c;
		$component = new JScrollPane($c);
		
		super.create($main, $parent);
	}
	
	
	
}