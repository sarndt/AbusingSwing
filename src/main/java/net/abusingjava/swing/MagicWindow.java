package net.abusingjava.swing;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

import net.abusingjava.Author;
import net.abusingjava.swing.magic.Window;

@Author("Julian Fleischer")
public class MagicWindow extends JFrame {

	private static final long serialVersionUID = 8856951705627589850L;

	JFrame $fullscreenFrame = null;
	Dimension $savedSize = null;

	final MagicPanel $magicPanel;

	MagicWindow(final Window $window) {
		super($window.getTitle());
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
		setResizable($window.getResizable());

		pack();
	}

	public MagicPanel getMagicPanel() {
		return $magicPanel;
	}

	public static class Toggle {
		final MagicPanel $panel;
		final MagicWindow $window;
		
		Toggle(final MagicWindow $window) {
			this.$panel = $window.getMagicPanel();
			this.$window = $window;
		}

		public void toggle() {
			$panel.$("#top").showNext();
		}
		
		public void fullscreen() {
			$window.toggleFullscreen();
		}
		
		public void mouseover() {
			System.out.println("mouseover");
		}
		
		public void mouseout() {
			System.out.println("mouseout");
		}
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
				setLocation(0, 0);
				setVisible(true);
			}
		}
	}
	
	public MagicComponents $(final String $selector) {
		return $magicPanel.$($selector);
	}
	
	public MagicWindow setInvocationHandler(final Object $object) {
		$magicPanel.setInvocationHandler($object);
		return this;
	}
	
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();

		MagicWindow $win = AbusingSwing.showWindow("MagicTable.xml");
		$win.getMagicPanel().setInvocationHandler(new Toggle($win));
		
		$win.getMagicPanel().$("#table").addRows(new Object[][] {
				{"Julian", "Meier", 27},
				{"Georg", "Meyer", 32},
				{"Christian", "Müller", 35},
				{"Stephan", "From", 23},
				{"Michael", "Meister", 23},
				{"Michal", "Youth", 100},
		});
		
		for (int $i = 0; $i < 100; $i++) {
			$win.$("#list").add($i);
		}
		System.out.println($win.$("#list").getSelectedItem());
		$win.$("#list").setSelectedItem(27);
		
		$win.$("#combobox").add(new String[] {
				"Huhu", "Hula", "Hulalala", "Hullalalaalla"
		}).setSelectedItem("Hula");
	}

}
