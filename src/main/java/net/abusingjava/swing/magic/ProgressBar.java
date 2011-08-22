package net.abusingjava.swing.magic;

import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("progressbar")
public class ProgressBar extends Component {

	@XmlAttribute
	int $min;
	
	@XmlAttribute
	int $max;
	
	@XmlAttribute
	int $value;
	
	@XmlAttribute
	boolean $indeterminate;
	
	@XmlAttribute("string-painted")
	boolean $stringPainted;
	
	@XmlAttribute
	String $string = "%s / %s"; 
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		final JProgressBar $c = new JProgressBar($min, $max);
		
		$c.setIndeterminate($indeterminate);
		$c.setStringPainted($stringPainted);
		
		$component = $c;
		
		switch ($parent.getOrientation()) {
		case HORIZONTAL:
			if ($width == null) {
				$width = new Value("17px");
			}
			$c.setOrientation(JProgressBar.VERTICAL);
			break;
		case VERTICAL:
			if ($height == null) {
				$height = new Value("17px");
			}
			$c.setOrientation(JProgressBar.HORIZONTAL);
			break;
		case FIXED:
			break;
		}
		
		$c.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent $ev) {
				$c.setString(String.format($string, $c.getValue(), $c.getMaximum(), $c.getMinimum()));
			}
		});
		
		$c.setValue($value);
		$c.setValue($value + 1);
		
		super.create($main, $parent);
	}
	
}