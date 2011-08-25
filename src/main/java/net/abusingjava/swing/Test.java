package net.abusingjava.swing;

public class Test {

	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		MagicPanel $panel = AbusingSwing.showWindow("Table.xml").getMagicPanel();
		
		for (Staat $s : Staat.values()) {
			$panel.$("#table").addRow(new Object[] {$s.name(), $s.toString(), $s.getLangform()});
		}
		/*
		for (Sprache $s : Sprache.values()) {
			$panel.$("#sluggy").add($s);
		}
		
		JXTable $table = $panel.$("#table").as(JXTable.class);
		System.out.println($table.getColumn("Name1").getModelIndex());
		System.out.println($table.getColumn("Name2").getModelIndex());
		System.out.println($table.getColumn("Brool").getModelIndex());
		
		final TableFiltering $tf = new TableFiltering($panel.$("#sluggy").as(JXTable.class), new String[]{});
		final JTextField $jtf = $panel.$("#textfield").as(JTextField.class);
		
		$jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent $ev) {
				$tf.updateFilters($jtf.getText());
			}
			
		});*/
	}
	
}
