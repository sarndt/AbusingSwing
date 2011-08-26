package net.abusingjava.swing;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.*;

import net.abusingjava.Author;
import net.abusingjava.swing.magic.Menu;
import net.abusingjava.swing.magic.MenuBar;
import net.abusingjava.swing.magic.Window;

/**
 * A JFrame-component that is built by an XML-definition and offers certain extra functionality
 * such as easy fullscreen, minimum-, and maximum-size. A MagicWindow contains a {@link MagicPanel}.
 * <p>
 * To create a MagicWindow, use {@link AbusingSwing#makeWindow(java.io.InputStream) AbusingSwing.makeWindow}.
 * <p>
 * A MagicWindow is by default created at the center of the screen.
 */
@Author("Julian Fleischer")
public class MagicWindow extends JFrame {

	private static final long serialVersionUID = 8856951705627589850L;

	private JFrame $fullscreenFrame = null;
	private Dimension $savedSize = null;

	final MagicPanel $magicPanel;

	public class ToggleFullscreenAction extends AbstractAction {

		public ToggleFullscreenAction() {
			super("Fullscreen");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F11"));
		}
		
		private static final long serialVersionUID = 1890428096218498993L;

		@Override
		public void actionPerformed(final ActionEvent $ev) {
			toggleFullscreen();
		}
	}
	
	MagicWindow(final Window $window) {
		super($window.getTitle());
		
		if ($window.hasMinSize()) {
			setMinimumSize($window.getMinSize());
		}
		if ($window.hasSize()) {
			setSize($window.getSize());
			setPreferredSize($window.getSize());
		}
		
		if ($window.hasMenuBar()) {
			MenuBar $menuBar = $window.getMenuBar();
			
			JMenuBar $jMenuBar = new JMenuBar();
			
			for (Menu $menu : $menuBar.getMenus()) {
				$jMenuBar.add(MagicPanel.buildMenu($menu));
			}
			
			setJMenuBar($jMenuBar);
		}
		
		$magicPanel = new MagicPanel($window.getPanel());

		setLocationRelativeTo(null);
		setContentPane(new JScrollPane($magicPanel));
		setResizable($window.getResizable());

		new JPopupMenu().add(new JMenuItem(new ToggleFullscreenAction()));
		
		pack();
	}

	
	/**
	 * Returns the MagicPanel that is the contentPane of this MagicWindow.
	 * 
	 * @return The MagicPanel (never null).
	 */
	public MagicPanel getMagicPanel() {
		return $magicPanel;
	}

	/**
	 * Enters- or exits fullscreen.
	 */
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
	
	/**
	 * Sets the invocation handler on the underlying magic Panel.
	 */
	public MagicWindow setInvocationHandler(final Object $object) {
		$magicPanel.setInvocationHandler($object);
		return this;
	}
}
