package net.abusingjava.swing;

import java.util.EventObject;

import javax.swing.JTable;

public class TableActionEvent extends EventObject {

	private static final long serialVersionUID = 5902067393858116983L;
	
	final JTable $table;
	final int $row;
	final int $col;
	
	public TableActionEvent(final JTable $table, final int $row, final int $col) {
		super($table);
		this.$table = $table;
		this.$row = $row;
		this.$col = $col;
	}
	
	public int getRowIndex() {
		return $row;
	}
	
	public int getColumnIndex() {
		return $col;
	}
	
	@Override
	public JTable getSource() {
		return $table;
	}
	
}
