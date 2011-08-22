package net.abusingjava.swing.finish.magic;

import org.jdesktop.swingx.JXDatePicker;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("datepicker")
public class DatePicker extends Component {
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JXDatePicker $c = new JXDatePicker();
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}