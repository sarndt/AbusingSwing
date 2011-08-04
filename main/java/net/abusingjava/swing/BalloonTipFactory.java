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

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.abusingjava.Author;
import net.abusingjava.Version;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.TimingUtils;

@Author("Julian Fleischer")
@Version("2011-07-17")
class BalloonTipFactory {
	
	private Color $border = Color.black;
	private Color $background = Color.black;
	private Color $foreground = Color.white;
	private int $radius = 5;
	private boolean $closeable = false;
	
	public static BalloonTipFactory newInstance() {
		return new BalloonTipFactory();
	}
	
	private BalloonTipFactory() {
		
	}
	
	public static class Balloon extends BalloonTip {
		
		private static final long serialVersionUID = 6524863478659942286L;

		public Balloon(JComponent $attachTo, JLabel $label, BalloonTipStyle $style, boolean $closeable) {
			super($attachTo, $label, $style, $closeable);
		}

		public void show(int $milliseconds) {
			TimingUtils.showTimedBalloon(this, $milliseconds);
		}
	}
	
	public Balloon newBalloonTip(JComponent $attachTo, String $text) {
		JLabel $label = new JLabel($text);
		$label.setForeground($foreground);
		BalloonTipStyle $style = new RoundedBalloonStyle($radius, $radius, $background, $border);
		Balloon $tip = new Balloon($attachTo, $label, $style, $closeable);
		return $tip;
	}
	
	public void setBorderColor(Color $c) {
		$border = $c;
	}
	
	public void setBackgroundColor(Color $c) {
		$background = $c;
	}
	
	public void setForegroundColor(Color $c) {
		$foreground = $c;
	}
	
	public void setArc(int $radius) {
		this.$radius = $radius;
	}
	
}
