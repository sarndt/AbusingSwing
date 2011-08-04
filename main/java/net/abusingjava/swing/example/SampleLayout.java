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
package net.abusingjava.swing.example;

import net.abusingjava.Author;
import net.abusingjava.Version;
import net.abusingjava.swing.AbusingSwing;
import net.abusingjava.swing.MagicPanel;

@Author("Julian Fleischer")
@Version("2011-06-20")
public class SampleLayout {

	static int $width = 900;
	static int $height = 500;

	static XPanel $panel;
	
	@SuppressWarnings("serial")
	public static class XPanel extends MagicPanel {
		public XPanel(final String $file) {
			super(SampleLayout.class.getResourceAsStream($file));
		}
		
		public void doIt() {
			System.out.println(" I DID IT ---> !");
		}
	}
	
	private static void show(final String $file) {
		System.out.printf("Preparing %s.\n", $file);
		$panel = new XPanel($file);

		AbusingSwing.showPanel($panel, 800, 500);
		System.out.printf("Showing %s.\n", $file);
	}
	
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		System.out.println("LookAndFeel set.");
		
		// TODO: AbusingExceptions.noticePrintStream = System.err;
		
		show("x2.xml");
		
	}
	
}
