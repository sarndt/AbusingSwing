package net.abusingjava.swing.magic;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.magix.types.Value;
import net.abusingjava.xml.XmlElement;

import org.jdesktop.swingx.JXDatePicker;

@XmlElement("datepicker")
public class DatePicker extends Component {
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		//JSpinner $c = new JSpinner(new SpinnerDateModel());
		JXDatePicker $c = new JXDatePicker();
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}