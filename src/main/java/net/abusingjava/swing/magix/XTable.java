package net.abusingjava.swing.magix;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXTable;


public class XTable extends JXTable {

	private static final long serialVersionUID = -1110662927738700425L;

	public XTable() {
		
		setDefaultRenderer(java.util.Date.class, new DefaultTableCellRenderer() {

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
		
	}
	
	@Override
	public Component prepareRenderer(final TableCellRenderer $renderer,
            final int $rowIndex, final int $vColIndex) {
		Component $c = super.prepareRenderer($renderer, $rowIndex, $vColIndex);
		if ($c instanceof JComponent) {
			JComponent $jc = (JComponent) $c;
			Object $value = getValueAt($rowIndex, $vColIndex);
			if ($value instanceof String) {
				$jc.setToolTipText($value.toString());
			}
		}
		return $c;
	}
	
}
