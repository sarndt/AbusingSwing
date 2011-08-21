package net.abusingjava.swing.finish.magic;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlTextContent;

@XmlElement("numeric")
public class Numeric extends Component {
	
	@XmlAttribute
	double $min = 0;
	
	@XmlAttribute
	double $max;
	
	@XmlAttribute
	double $step = 1;
	
	@XmlTextContent
	Double $value;

	@Override
	public void create(final MagicPanel $parent) {
		if ($value == null) {
			$value = $min;
		}
		JSpinner $c = new JSpinner(new SpinnerNumberModel((double) $value, $min, $max, $step));
		
		$component = $c;
		
		super.create($parent);
	}
	
}
