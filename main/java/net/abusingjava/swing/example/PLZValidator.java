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

import java.util.regex.Pattern;

import net.abusingjava.Author;
import net.abusingjava.Version;
import net.abusingjava.swing.Validator;

@Author("Julian Fleischer")
@Version("2011-06-30")
public class PLZValidator implements Validator<String> {

	@Override
	public boolean validate(final String $input) {
		return Pattern.matches("^[0-9]{5}$", $input);
	}

	@Override
	public String getMessage() {
		return "Dies ist keine gültige PLZ!";
	}
}
