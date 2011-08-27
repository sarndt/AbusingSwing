package net.abusingjava.swing.magic;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.MethodType;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("list")
public class List extends Component {

	@XmlAttribute
	MethodType $onselect;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		final DefaultListModel $m = new DefaultListModel();
		final JList $c = new JList($m);
		
		$c.setVisibleRowCount(-1);
		$c.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		if ($onselect != null) {
			$c.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(final ListSelectionEvent $ev) {
					if (!$ev.getValueIsAdjusting()) {
						Integer $index = null;
						if ($c.isSelectedIndex($ev.getFirstIndex())) {
							$index = $ev.getFirstIndex();
						}
						if ($c.isSelectedIndex($ev.getLastIndex())) {
							$index = $ev.getLastIndex();
						}
						if ($index != null) {
							Object $object = $m.elementAt($index);
							$onselect.call($main.getInvocationHandler(),
								new net.abusingjava.swing.ListSelectionEvent($index, $object));
						}
					}
				}
			});
		}
		
		$realComponent = $c;
		$component = new JScrollPane($c);
		
		super.create($main, $parent);
	}
	
	
	
}