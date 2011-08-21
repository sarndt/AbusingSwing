package net.abusingjava.swing.finish.magic;

import javax.swing.JProgressBar;

import net.abusingjava.swing.finish.MagicPanel;
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
	
	@Override
	public void create(final MagicPanel $parent) {
		JProgressBar $c = new JProgressBar($min, $max);
		
		$component = $c;
		
		super.create($parent);
	}
	
}