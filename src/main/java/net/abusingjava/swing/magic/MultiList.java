package net.abusingjava.swing.magic;

import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

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
		private final PropertyChangeSupport $propertyChangeSupport = new PropertyChangeSupport(this);
	
		MultiListTable(final String $columnHead) {
			super(new DefaultTableModel(new String[]{"", $columnHead}, 0));
			
			getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(final TableModelEvent $ev) {
					$propertyChangeSupport.firePropertyChange("selectedObjects", "", "");
				}
			});
		}
		
		public void setSelectedObjects(final java.util.List<?> $objects) {
			int $rows = getModel().getRowCount();
			for (int $i = 0; $i < $rows; $i++) {
				getModel().setValueAt($objects.contains(getModel().getValueAt($i, 1)), $i, 0);
			}
			
		}
		
		public java.util.List<?> getSelectedObjects() {
			int $rows = getModel().getRowCount();
			java.util.List<Object> $list = new LinkedList<Object>();
			for (int $i = 0; $i < $rows; $i++) {
				if (getModel().getValueAt($i, 0).equals(true)) {
					$list.add(getModel().getValueAt($i, 1));
				}
			}
			return $list;
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
		
		@Override
		public DefaultTableModel getModel() {
			return (DefaultTableModel) super.getModel();
		}
	}
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {

		$filterMode = new FilterMode("or");

		super.create($main, $parent);

		@SuppressWarnings("serial")
		final MultiListTable $c = new MultiListTable($columnHead);

		if ($from != null) {
			Object[] $values = (Object[]) AbusingFunctions.callback($from.getJavaType(), "values").call();
			for (Object $v : $values) {
				$c.getModel().addRow(new Object[]{false, $v});
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
