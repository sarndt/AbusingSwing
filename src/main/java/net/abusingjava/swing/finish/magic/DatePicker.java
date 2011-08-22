package net.abusingjava.swing.finish.magic;

import org.jdesktop.swingx.JXDatePicker;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Value;
import net.abusingjava.xml.XmlElement;

@XmlElement("datepicker")
public class DatePicker extends Component {
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		JXDatePicker $c = new JXDatePicker();
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}