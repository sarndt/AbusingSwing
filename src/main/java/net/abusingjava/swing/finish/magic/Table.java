package net.abusingjava.swing.finish.magic;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		
		String[] $columnHeaders = new String[$columns.length];
		for (int $i = 0; $i < $columns.length; $i++) {
			$columnHeaders[$i] = $columns[$i].$text;
		}
		
		TableModel $model = new DefaultTableModel($columnHeaders, 0) {
			private static final long serialVersionUID = -135732270243460558L;

			@Override public Class<?> getColumnClass(final int $col) {
				try {
					return $columns[$col].$type.getClass();
				} catch (Exception $exc) {
					return Object.class;
				}
			}
		};
		
		JXTable $c = new JXTable($model);
		$c.setEditable($editable);
		$c.setSortable($sortable);
		if ($gridColor != null) {
			$c.setGridColor($gridColor.getColor());
		}
		
		for (int $i = 0; $i < $columns.length; $i++) {
			if ($columns[$i].$maxWidth != null)
				$c.getColumn($columnHeaders[$i]).setMaxWidth($columns[$i].$maxWidth);
			if ($columns[$i].$minWidth != null)
				$c.getColumn($columnHeaders[$i]).setMinWidth($columns[$i].$minWidth);
		}
		
		
		$realComponent = $c;
		$component = new JScrollPane($c);
		$c.setFillsViewportHeight(true);
		
		super.create($main, $parent);
	}
	
}