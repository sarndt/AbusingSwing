package net.abusingjava.swing.finish;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;

import net.abusingjava.Author;
import net.abusingjava.swing.AbusingSwing;
import net.abusingjava.swing.finish.magic.Window;

@Author("Julian Fleischer")
final public class MagicWindow extends JFrame {

	private static final long serialVersionUID = 8856951705627589850L;

	JFrame $fullscreenFrame = null;
	Dimension $savedSize = null;
	
	final MagicPanel $magicPanel;

	MagicWindow(final Window $window) {
		
		if ($window.hasMinSize()) {
			setMinimumSize($window.getMinSize());
		}
		if ($window.hasSize()) {
			setSize($window.getSize());
			setPreferredSize($window.getSize());
		}
		$magicPanel = new MagicPanel($window.getPanel());
		setLocationRelativeTo(null);
		setContentPane($magicPanel);
		$magicPanel.setInvocationHandler(this);
		
		pack();
	}
	
	public void doIt() {
		
	}
	
	public MagicPanel getMagicPanel() {
		return $magicPanel;
	}
	
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		final MagicWindow $win = MagicFactory.makeWindow(MagicWindow.class.getResourceAsStream("MagicWindow.xml"));
		$win.setVisible(true);
		$win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void toggleFullscreen() {
		final GraphicsDevice $dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if ($dev.isFullScreenSupported()) {
			if ($fullscreenFrame != null) {
				$fullscreenFrame.setVisible(false);
				setContentPane($fullscreenFrame.getContentPane());
				pack();
				$dev.setFullScreenWindow(null);
				$fullscreenFrame.dispose();
				$fullscreenFrame = null;
				setVisible(true);
			} else {
				setVisible(false);
				$fullscreenFrame = new JFrame($dev.getDefaultConfiguration());
				$fullscreenFrame.setUndecorated(true);
				$fullscreenFrame.setContentPane(getContentPane());
				$fullscreenFrame.pack();
				$fullscreenFrame.setVisible(true);
				$dev.setFullScreenWindow($fullscreenFrame);
			}
		} else {
			if ($savedSize != null) {
				dispose();
				setUndecorated(false);
				pack();
				setSize($savedSize);
				setAlwaysOnTop(false);
				setVisible(true);
				$savedSize = null;
			} else {
				$savedSize = getSize();
				setVisible(false);
				dispose();
				setUndecorated(true);
				setAlwaysOnTop(true);
				pack();
				setSize(Toolkit.getDefaultToolkit().getScreenSize());
				setLocation(0,0);
				setVisible(true);
			}
		}
	}
}
