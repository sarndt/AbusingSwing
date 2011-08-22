package net.abusingjava.swing.finish.magic;

import javax.swing.JSlider;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("slider")
public class Slider extends Component {


	@XmlAttribute
	int $min = 0;
	
	@XmlAttribute
	int $max = 100;
	
	@XmlAttribute
	Integer $value;
	
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($value == null) {
			$value = ($min + $max) / 2;
		}

		JSlider $c = new JSlider($min, $max, $value);
		switch ($parent.getOrientation()) {
		case HORIZONTAL:
			if ($width == null) {
				$width = new Value("17px");
			}
			$c.setOrientation(JSlider.VERTICAL);
			break;
		case VERTICAL:
			if ($height == null) {
				$height = new Value("17px");
			}
			$c.setOrientation(JSlider.HORIZONTAL);
			break;
		case FIXED:
			break;
		}
		
		$component = $c;
		super.create($main, $parent);
		
	}
	
	
	
}