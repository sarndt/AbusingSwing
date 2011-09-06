package net.abusingjava.swing.magix;

import java.awt.Component;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.abusingjava.Author;
import net.abusingjava.swing.AbusingSwing;

import org.jdesktop.swingx.JXTable;

@Author("Julian Fleischer")
public class XTable extends JXTable {

	private static final long serialVersionUID = -1110662927738700425L;
	private SimpleDateFormat $dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public XTable() {
		
	}
	
	public XTable(final String[] $columns) {
		super(new DefaultTableModel($columns, 0) {

			private static final long serialVersionUID = -8458404688992294956L;
			
		});
	}
	
	@Override
	public DefaultTableModel getModel() {
		return (DefaultTableModel) super.getModel();
	}
	
	public void setDateFormat(final String $dateFormat) {
		this.$dateFormat = new SimpleDateFormat($dateFormat);
	}
	
	public String getDateFormat() {
		return "";
	}
	
	@Override
	public Component prepareRenderer(final TableCellRenderer $renderer,
            final int $rowIndex, final int $columnIndex) {
		
		Component $c = super.prepareRenderer($renderer, $rowIndex, $columnIndex);
		Object $value = getValueAt($rowIndex, $columnIndex);
		if ($c instanceof JComponent) {
			JComponent $jc = (JComponent) $c;
			if (($value instanceof String)) {
				$jc.setToolTipText($value.toString());
			} else if ($value instanceof Date) {
				$jc.setToolTipText($dateFormat.format((Date) $value));
			}
			if ($jc instanceof JLabel) {
				JLabel $label = (JLabel) $jc;
				if ($value instanceof Date) {
					$label.setText($dateFormat.format((Date) $value));
				}
			}
		}
		return $c;
	}
		
	@SuppressWarnings("serial")
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		final XTable $xtable = new XTable(new String[]{"Darth Vader", "When?"});
		
		$xtable.getModel().addRow(new Object[] {"Yes", new Date()});
		
		$xtable.setFillsViewportHeight(true);
		
		new JFrame() {{
			
			setContentPane(new JPanel() {{
				setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				add(new JScrollPane($xtable));
			}});
			setSize(new Dimension(400, 200));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}}.setVisible(true);
	}
}
