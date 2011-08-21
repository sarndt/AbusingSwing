package net.abusingjava.swing.finish.magic;

import javax.swing.JScrollPane;

import org.jdesktop.swingx.JXTable;

import net.abusingjava.swing.finish.MagicPanel;
import net.abusingjava.swing.finish.types.Color;
import net.abusingjava.swing.finish.types.JavaType;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlTextContent;

@XmlElement("table")
public class Table extends Component {
	
	@XmlAttribute
	boolean $sortable = true;
	
	@XmlAttribute("grid-color")
	Color $gridColor;

	@XmlAttribute
	boolean $editable = false;
		
	@XmlChildElements
	Column[] $columns;

	@XmlElement("col")
	public static class Column {
		@XmlAttribute
		JavaType $type = new JavaType(java.lang.Object.class);
		
		@XmlAttribute("min-width")
		Integer $minWidth;
		
		@XmlAttribute("max-width")
		Integer $maxWidth;
		
		@XmlTextContent
		String $text = "";
	}
	
	@Override
	public void create(final MagicPanel $parent) {
		JXTable $c = new JXTable();
		
		$c.setEditable($editable);
		$c.setSortable($sortable);
		if ($gridColor != null) {
			$c.setGridColor($gridColor.getColor());
		}
		
		$component = new JScrollPane($c);
	}
	
}