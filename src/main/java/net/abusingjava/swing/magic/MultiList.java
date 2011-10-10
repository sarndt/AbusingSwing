package net.abusingjava.swing.magic;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import net.abusingjava.functions.AbusingFunctions;
import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.magix.types.FilterMode;
import net.abusingjava.swing.magix.types.JavaType;
import net.abusingjava.swing.magix.types.MethodType;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

import org.jdesktop.swingx.JXTable;

@XmlElement("multilist")
public class MultiList extends Table {

	@XmlAttribute("column-head")
	String $columnHead;

	@XmlAttribute("from")
	JavaType $from;

	@XmlAttribute
	MethodType $onchange;

	public static class MultiListTable extends JXTable {

		private static final long serialVersionUID = -2230230778375628917L;
	
		MultiListTable(final String $columnHead) {
			super(new DefaultTableModel(new String[]{"", $columnHead}, 0));
		}
		
		@Override
		public Class<?> getColumnClass(final int $arg) {
			if ($arg == 0) {
				return Boolean.class;
			}
			return Object.class;
		}

		@Override
		public boolean isCellEditable(final int $row, final int $column) {
			return $column == 0;
		}
	}
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {

		$filterMode = new FilterMode("or");

		super.create($main, $parent);

		@SuppressWarnings("serial")
		final JXTable $c = new MultiListTable($columnHead);

		if ($from != null) {
			Object[] $values = (Object[]) AbusingFunctions.callback($from.getJavaType(), "values").call();
			for (Object $v : $values) {
				((DefaultTableModel) $c.getModel()).addRow(new Object[]{false, $v});
			}
		}

		if ($onchange != null) {
			$c.getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(final TableModelEvent $ev) {
					new Thread(eventListener($onchange, $ev)).start();
				}
			});
		}

		$c.getColumn("").setMinWidth(22);
		$c.getColumn("").setMaxWidth(22);
		$c.setFillsViewportHeight(true);
		$c.setHorizontalScrollEnabled(true);
		$c.setSortsOnUpdates(false);
		$c.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		$realComponent = $c;
		$component = new JScrollPane($c);

		if ($columnHead == null) {
			$c.setTableHeader(null);
			((JScrollPane) $component).setColumnHeader(null);
		}
	}

	public void showSelectedOnly(final boolean $selected) {
		$selectedFilter = $selected;
		updateFilters();
	}
}
