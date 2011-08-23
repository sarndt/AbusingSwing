/* Part of the AbusingJava-Library.
 * 
 * Source:  http://github.com/scravy/AbusingJava
 * Home:    http://www.abusingjava.net/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.abusingjava.swing;

import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import net.abusingjava.Author;
import net.abusingjava.NotGonnaHappenException;
import net.abusingjava.Version;
import net.abusingjava.swing.magic.Panel;
import net.abusingjava.swing.magic.Window;
import net.abusingjava.xml.AbusingXML;

@Author("Julian Fleischer")
@Version("2011-06-21")
final public class AbusingSwing {
	
	private AbusingSwing() {}

	public static void setLookAndFeel(final String $name) {
		try {
			for (LookAndFeelInfo $info : UIManager.getInstalledLookAndFeels()) {
			    if ($name.equals($info.getName())) {
			        UIManager.setLookAndFeel($info.getClassName());
			        break;
			    }
			}
		} catch (Exception $exc) {
			// TODO: AbusingExceptions.warning($exc);
		}
	}

	/**
	 * Sets the Nimbus Look And Feel which is available since Java 6 Update 10.
	 */
	public static void setNimbusLookAndFeel() {
		setLookAndFeel("Nimbus");
	}

	/**
	 * Show a $panel in a temporary frame.
	 */
	public static JFrame showPanel(final JPanel $panel) {
		return showPanel($panel, true);
	}
	
	/**
	 * Show a $panel in a temporary frame. If $close, than closing the frame will also shut down the application.
	 */
	public static JFrame showPanel(final JPanel $panel, final boolean $close) {
		final JFrame $frame = new JFrame() {
			private static final long serialVersionUID = 4965692958830497098L;
			{
				if ($close)
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setContentPane($panel);
				pack();
				setMinimumSize($panel.getMinimumSize());
			}
		};
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$frame.setVisible(true);
			}
		});
		return $frame;
	}

	/**
	 * Show a $panel with size $width and $height.
	 */
	public static JFrame showPanel(final JPanel $panel, final int $width, final int $height) {
		return showPanel($panel, true, $width, $height);
	}
	
	public static JFrame showPanel(final JPanel $panel, final boolean $close, final int $width, final int $height) {
		final JFrame $frame = new JFrame() {
			private static final long serialVersionUID = -5067180055790051146L;
			{
				if ($close)
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setContentPane(new JScrollPane($panel));
				pack();
				setSize($width, $height);
				// setMinimumSize(new Dimension($width, $height));
			}
		};
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$frame.setVisible(true);
			}
		});
		return $frame;
	}

	public static MagicWindow showWindow(final String $resource) {
		MagicWindow $window = makeWindow($resource);
		$window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		$window.setVisible(true);
		return $window;
	}
	
	public static MagicWindow makeWindow(final String $resource) {
		String $className = new Exception().getStackTrace()[0].getClassName();
		try {
			InputStream $stream = Class.forName($className).getResourceAsStream($resource);
			return makeWindow($stream);
		} catch (ClassNotFoundException $exc) {
			throw new NotGonnaHappenException($exc);
		}
	}
	
	public static MagicWindow makeWindow(final InputStream $stream) {
		Window $window = AbusingXML.loadXML($stream, Window.class);
		return new MagicWindow($window);
	}
	
	public static MagicPanel makePanel(final InputStream $stream) {
		Panel $panel = AbusingXML.loadXML($stream, Panel.class);
		return new MagicPanel($panel);
	}
	
}
