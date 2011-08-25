package net.abusingjava.swing.magic;

import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingx.JXTable;

import net.abusingjava.arrays.AbusingArrays;
import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.magic.Table.Column;
import net.abusingjava.swing.types.*;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlTextContent;

@XmlElement("table")
public class Table extends Component implements Iterable<Column> {

	
	@XmlChildElements
	Column[] $columns;
	
	
	@XmlAttribute
	boolean $sortable = true;
	
	@XmlAttribute("grid-color")
	Color $gridColor;

	@XmlAttribute
	boolean $editable = false;
		
	@XmlAttribute("column-control-visible")
	boolean $columnControlVisible = true;
	
	@XmlAttribute("horizontal-scroll-enabled")
	boolean $horizontalScrollEnabled = false;

	@XmlAttribute("column-margin")
	Integer $columnMargin;
	
	@XmlAttribute("terminate-edit-on-focus-lost")
	boolean $terminateEditOnFocusLost = true;
	
	@XmlAttribute("sorts-on-update")
	boolean $sortsOnUpdate = true;
	
	@XmlAttribute("row-height")
	Value $rowHeight;
	
	@XmlAttribute("filter-mode")
	FilterMode $filterMode = new FilterMode("and");

	
	@SuppressWarnings("rawtypes")
	private JTableBinding $binding = null;

	@SuppressWarnings("rawtypes")
	public void setBinding(final JTableBinding $binding) {
		this.$binding = $binding;
	}
	
	
	public void clearBinding() {
		if ($binding != null) {
			$binding.unbind();
			$binding = null;
		}
	}
	
	
	@XmlElement("col")
	public static class Column {
		@XmlAttribute
		JavaType $type = new JavaType(java.lang.Object.class);
		
		@XmlAttribute("min-width")
		Value $minWidth;
		
		@XmlAttribute("max-width")
		Value $maxWidth;
		
		@XmlAttribute("width")
		Value $width;
		
		@XmlTextContent
		String $text = "";
		
		public Class<?> getJavaType() {
			return $type.getJavaType();
		}
	}
	
	public Column getColumn(final String $label) {
		for (Column $c : $columns) {
			if ($c.$text.equals($label)) {
				return $c;
			}
		}
		return null;
	}
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		
		String[] $columnHeaders = new String[$columns.length];
		for (int $i = 0; $i < $columns.length; $i++) {
			$columnHeaders[$i] = $columns[$i].$text;
		}
		
		TableModel $model = new DefaultTableModel($columnHeaders, 0) {
			private static final long serialVersionUID = -135732270243460558L;

			@Override
			public Class<?> getColumnClass(final int $col) {
				try {
					return $columns[$col].$type.getJavaType();
				} catch (Exception $exc) {
					return Object.class;
				}
			}
		};
		
		JXTable $c = new JXTable($model);
		$c.setEditable($editable);
		$c.setSortable($sortable);
		$c.setColumnControlVisible($columnControlVisible);
		$c.setHorizontalScrollEnabled($horizontalScrollEnabled);
		$c.setSortsOnUpdates($sortsOnUpdate);
		$c.setTerminateEditOnFocusLost($terminateEditOnFocusLost);
		if (($rowHeight != null) && ($rowHeight.getUnit() == Unit.PIXEL) && ($rowHeight.getValue() >= 0)) {
			$c.setRowHeight($rowHeight.getValue());
		}
		if (($columnMargin != null) && ($columnMargin >= 0)) {
			$c.setColumnMargin($columnMargin);
		}
		if ($gridColor != null) {
			$c.setGridColor($gridColor.getColor());
		}
		for (int $i = 0; $i < $columns.length; $i++) {
			if (($columns[$i].$maxWidth != null) && ($columns[$i].$maxWidth.getUnit() == Unit.PIXEL)) {
				$c.getColumn($columnHeaders[$i]).setMaxWidth($columns[$i].$maxWidth.getValue());
			}
			if (($columns[$i].$minWidth != null)  && ($columns[$i].$maxWidth.getUnit() == Unit.PIXEL)) {
				$c.getColumn($columnHeaders[$i]).setMinWidth($columns[$i].$minWidth.getValue());
			}
			if (($columns[$i].$width != null)  && ($columns[$i].$width.getUnit() == Unit.PIXEL)) {
				$c.getColumn($columnHeaders[$i]).setPreferredWidth($columns[$i].$width.getValue());
			}
		}
		$realComponent = $c;
		$component = new JScrollPane($c);
		$c.setFillsViewportHeight(true);
		
		super.create($main, $parent);
	}

	@Override
	public Iterator<Column> iterator() {
		return AbusingArrays.array($columns).iterator();
	}
	
}