package net.abusingjava.swing;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

public class BindingTest {

	@SuppressWarnings("serial")
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		new JFrame() {{
			
			setSize(500, 300);
			setContentPane(new JPanel() {{
				JCheckBox $checkBox1 = new JCheckBox("1");
				JCheckBox $checkBox2 = new JCheckBox("2");
				
				add(new JButton("Text"));
				add($checkBox1);
				add($checkBox2);
				
				BindingGroup $group = new BindingGroup();
				
				$group.addBinding(Bindings.createAutoBinding(UpdateStrategy.READ,
						$checkBox1, BeanProperty.create("selected"),
						$checkBox2, BeanProperty.create("selected")));
				
				$group.bind();
			}});
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			pack();
		}}.setVisible(true);
		
	}
	
}
