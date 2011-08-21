package net.abusingjava.swing.finish;

import javax.swing.JFrame;

import net.abusingjava.Author;
import net.abusingjava.swing.AbusingSwing;
import net.abusingjava.swing.finish.magic.Window;

@Author("Julian Fleischer")
final public class MagicWindow extends JFrame {

	private static final long serialVersionUID = 8856951705627589850L;

	MagicWindow(final Window $window) {
		
		if ($window.hasMinSize()) {
			setMinimumSize($window.getMinSize());
		}
		if ($window.hasSize()) {
			setSize($window.getSize());
		}
		setLocationRelativeTo(null);
		setContentPane(new MagicPanel($window.getPanel()));
		
		pack();
	}
	
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		MagicWindow $win = MagicFactory.makeWindow(MagicWindow.class.getResourceAsStream("MagicWindow.xml"));
		$win.setVisible(true);
		$win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
