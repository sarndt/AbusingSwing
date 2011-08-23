package net.abusingjava.swing.magic;

import javax.swing.JComboBox;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("combobox")
public class ComboBox extends Component {

	@XmlAttribute("auto-complete")
	boolean $autoComplete;
	

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		JComboBox $c = new JComboBox();
		
		if ($autoComplete) {
			AutoCompleteDecorator.decorate($c);
		}
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
	
}