package net.abusingjava.swing.finish.magic;

import javax.swing.JSlider;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.xml.XmlElement;

@XmlElement("slider")
public class Slider extends Component {

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		JSlider $c = new JSlider();
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
	
	
}