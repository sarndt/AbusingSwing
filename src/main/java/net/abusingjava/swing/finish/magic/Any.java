package net.abusingjava.swing.finish.magic;

import javax.swing.JComponent;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.JavaType;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("any")
public class Any extends Component {
	
	@XmlAttribute("class")
	JavaType $class;
	
	public JavaType getJavaType() {
		return $class;
	}

	@Override
	public void create(final MagicPanel $parent) {
		try {
			$component = (JComponent) $class.getJavaType().newInstance();
		} catch (Exception $exc) {
			$exc.printStackTrace(System.err);
		}
		
		super.create($parent);
	}
}