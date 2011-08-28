package net.abusingjava.swing.magic;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
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
	Boolean $sortable;

	@XmlAttribute
	Boolean $editable;
	
	@XmlAttribute("grid-color")
	Color $gridColor;
		
	@XmlAttribute("column-control-visible")
	Boolean $columnControlVisible;
	
	@XmlAttribute("horizontal-scroll-enabled")
	Boolean $horizontalScrollEnabled;

	@XmlAttribute("column-margin")
	Integer $columnMargin;
	
	@XmlAttribute("terminate-edit-on-focus-lost")
	Boolean $terminateEditOnFocusLost;
	
	@XmlAttribute("sorts-on-update")
	Boolean $sortsOnUpdate;
	
	@XmlAttribute("row-height")
	Value $rowHeight;
	
	@XmlAttribute("filter-mode")
	FilterMode $filterMode = new FilterMode("and");
	
	// @XmlAttribute("")
	
	boolean $selectedFilter = false;
	
	public class Filter {
		
		final int[] $columnIndizes;
		String $filterString = "";
		
		Filter(final String[] $columns) {
			
			if (Table.this instanceof MultiList) {
				this.$columnIndizes = new int[] { 1 };
			} else {
				this.$columnIndizes = new int[$columns.length];
				for (int $i = 0; $i < $columns.length; $i++) {
					$columnIndizes[$i] = ((JXTable)$realComponent).getColumn($columns[$i]).getModelIndex();
				}
			}
		}
		
		public void setFilterString(final String $filterString) {
			this.$filterString = $filterString.toLowerCase();
		}
		
		public boolean apply(final javax.swing.RowFilter.Entry<? extends Object, ? extends Object> $entry) {
			if ($filterString.isEmpty()) {
				if ($selectedFilter) {
					return false;
				}
				return true;
			}
			boolean $result = false;
			for (int $index : $columnIndizes) {
				$result = $result || $entry.getStringValue($index).toLowerCase().contains($filterString);
			}
			return $result;
		}
	}
	
	List<Filter> $filterList = new ArrayList<Filter>();
	
	public Filter newFilter(final String[] $columns) {
		Filter $filter = new Filter($columns);
		$filterList.add($filter);
		return $filter;
	}
	
	public void updateFilters() {
		List<RowFilter<Object,Object>> $filters = new LinkedList<RowFilter<Object,Object>>();
		
		for (final Filter $f : $filterList) {
			$filters.add(new RowFilter<Object,Object>() {
				@Override
				public boolean include(final javax.swing.RowFilter.Entry<? extends Object, ? extends Object> $entry) {
					return $f.apply($entry);
				}
			});
		}
		
		if ($selectedFilter) {
			$filters.add(new RowFilter<Object,Object>() {
				@Override
				public boolean include(final javax.swing.RowFilter.Entry<? extends Object, ? extends Object> $entry) {
					return $entry.getValue(0) == Boolean.TRUE;
				}
			});
		}
		
		RowFilter<Object,Object> $filter = $filterMode.isAnd()
				? RowFilter.andFilter($filters)
				: RowFilter.orFilter($filters);
		
		((JXTable)$realComponent).setRowFilter($filter);
	}
	
	
	
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
		if ($editable == null) {
			$editable = false;
		}
		if ($sortable == null) {
			$sortable = true;
		}
		if ($columnControlVisible == null) {
			$columnControlVisible = true;
		}
		
		
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
		if ($horizontalScrollEnabled != null) {
			$c.setHorizontalScrollEnabled($horizontalScrollEnabled);
		}
		if ($sortsOnUpdate != null) {
			$c.setSortsOnUpdates($sortsOnUpdate);
		}
		if ($terminateEditOnFocusLost != null) {
			$c.setTerminateEditOnFocusLost($terminateEditOnFocusLost);
		}
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
		
		$c.setDefaultRenderer(java.util.Date.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 8519130125457693769L;

			@Override
			public java.awt.Component getTableCellRendererComponent(final JTable $table,
					final Object $value, final boolean $isSelected, final boolean $hasFocus, final int $row, final int $column) {
				
				super.getTableCellRendererComponent($table, $value, $isSelected, $hasFocus, $row, $column);
				if ($value instanceof Date) {
					String $date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) $value);
					this.setText($date);
				}
				
				return this;
			}

		});
		
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