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

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.abusingjava.Author;
import net.abusingjava.Version;

@Author("Julian Fleischer")
@Version("2011-06-30")
public class MagicEvent {

	public static enum Action {
		KEY_DOWN, KEY_UP, KEY_PRESSED,
		MOUSE_DOWN, MOUSE_UP, MOUSE_CLICKED,
		MOUSE_OVER, MOUSE_OUT, MOUSE_MOVE,
		WHEEL
	}
	
	private final InputEvent $src;
	private final Action $type;
	
	public MagicEvent(InputEvent $ev, Action $type) {
		$src = $ev;
		this.$type = $type;
	}

	public int getX() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getX();
		}
		return 0;
	}
	
	public int getY() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getY();
		}
		return 0;
	}
	
	public Point getPoint() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getPoint();
		}
		return null;
	}
	
	public int getXOnScreen() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getXOnScreen();
		}
		return 0;
	}
	
	public int getYOnScreen() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getYOnScreen();
		}
		return 0;
	}
	
	public int getKeyCode() {
		if ($src instanceof KeyEvent) {
			return ((KeyEvent)$src).getKeyCode();
		}
		return 0;
	}

	public char getKeyChar() {
		if ($src instanceof KeyEvent) {
			return ((KeyEvent)$src).getKeyChar();
		}
		return 0;
	}
	
	public Point getLocationOnScreen() {
		if ($src instanceof MouseEvent) {
			return ((MouseEvent)$src).getLocationOnScreen();
		}
		return null;
	}
	
	public Action getType() {
		return $type;
	}
}
