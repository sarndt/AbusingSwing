package net.abusingjava.swing.finish.magic;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Value;
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
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}

		if ($value == null) {
			$value = $min;
		}
		final SpinnerNumberModel $m = new SpinnerNumberModel((double) $value, $min, $max, $step);
		final JSpinner $c = new JSpinner($m);
		
		$c.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(final MouseWheelEvent $ev) {
				try {
					if ($ev.getWheelRotation() > 0) {
						$m.setValue($m.getPreviousValue());
					} else if ($ev.getWheelRotation() < 0) {
						$m.setValue($m.getNextValue());
					}
				} catch (Exception $exc) {
					
				}
			}
		});
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}
