package net.abusingjava.swing.demo;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import net.abusingjava.swing.AbusingSwing;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Test {

	public static void main(String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		JPanel $panel = new JPanel();
		AbusingSwing.showPanel($panel, 600, 400);
		
		JComboBox $comboBox = new JComboBox(new DefaultComboBoxModel(Staat.values()));
		$panel.add($comboBox);
		
		AutoCompleteDecorator.decorate($comboBox);
	}
	
}
