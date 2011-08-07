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

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import net.abusingjava.AbusingJava;
import net.abusingjava.Author;
import net.abusingjava.Version;
import net.abusingjava.functions.AbusingFunctions;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElement;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlSelect;
import net.abusingjava.xml.XmlTextContent;

@Author("Julian Fleischer")
@Version("2011-07-28")
@XmlElement("magic")
public final class Magic {

	@XmlSelect("title/text()")
	String $title;
	
	@XmlChildElements({HBox.class, VBox.class})
	Container[] $main = {};
	
	public static enum Unit {
		PIXEL, STAR, AUTO
	}
	
	public static class Value {
		int $value = -1;
		Unit $unit = Unit.AUTO;
		
		public Value(String $declaration) {
			if (!"auto".equalsIgnoreCase($declaration)) {
				$value = AbusingJava.parseInt($declaration.replaceAll("[^0-9]", ""), 1);
				if ($declaration.endsWith("px")) {
					$unit = Unit.PIXEL;
				} else if ($declaration.endsWith("*")) {
					$unit = Unit.STAR;
				}
			}
		}
	}
	
	public static class Color {
		java.awt.Color $color;
		
		public Color(String $color) {
			try {
				Field $f = java.awt.Color.class.getField($color);
				this.$color = (java.awt.Color) $f.get(null);
			} catch (Exception $exc) {
				this.$color = java.awt.Color.decode($color);
			}
		}

		public Color(java.awt.Color $color) {
			this.$color = $color;
		}
	}
	
	public static class FontStyle {
		final boolean $italic;
		
		public FontStyle(String $style) {
			$italic = "italic".equalsIgnoreCase($style);
		}
	}
	
	public static class FontWeight {
		final boolean $bold;
		
		public FontWeight(String $weight) {
			$bold = "bold".equalsIgnoreCase($weight);
		}
	}
	
	public static class JavaType {
		final Class<?> $class;
		
		public JavaType(String $javaType) throws ClassNotFoundException {
			Class<?> $class = null;
			if ("string".equalsIgnoreCase($javaType))
				$class = java.lang.String.class;
			else if ("byte".equalsIgnoreCase($javaType))
				$class = byte.class;
			else if ("short".equalsIgnoreCase($javaType))
				$class = int.class;
			else if ("long".equalsIgnoreCase($javaType))
				$class = long.class;
			else if ("int".equalsIgnoreCase($javaType) || "integer".equalsIgnoreCase("javaType"))
				$class = int.class;
			else if ("boolean".equalsIgnoreCase($javaType) || "bool".equalsIgnoreCase("javaType"))
				$class = boolean.class;
			else if ("float".equalsIgnoreCase($javaType))
				$class = float.class;
			else if ("double".equalsIgnoreCase($javaType))
				$class = double.class;
			else if ("date".equalsIgnoreCase($javaType))
				$class = java.util.Date.class;
			
			if ($class == null)
				$class = Class.forName($javaType);
			
			this.$class = $class;
		}
		
		public JavaType(Class<?> $theClass) {
			$class = $theClass;
		} 
	}
	
	public static class ValidatorType extends JavaType {

		public ValidatorType(String $javaType) throws ClassNotFoundException {
			super($javaType);
			if (!Validator.class.isAssignableFrom($class)) {
				throw new IllegalArgumentException();
			}
		}
		
	}

	public static class ActionType extends JavaType {

		public ActionType(String $javaType) throws ClassNotFoundException {
			super($javaType);
			if (!MagicAction.class.isAssignableFrom($class)) {
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	public static class Dimension {
		double $width;
		double $height;
		
		public Dimension(double $width, double $height) {
			this.$width = $width;
			this.$height = $height;
		}
	}
	
	public static class Bool {
		final boolean $true;
		
		public Bool(String $value) {
			boolean $true;
			if ("yes".equalsIgnoreCase($value) || "true".equalsIgnoreCase($value)) {
				$true = Boolean.TRUE;
			} else if ("no".equalsIgnoreCase($value) || "false".equalsIgnoreCase($value)) {
				$true = Boolean.FALSE;
			} else {
				throw new RuntimeException("Unrecognized value for Bool.");
			}
			this.$true = $true;
		}
	}
	
	public static class MethodType {
		final String $method;
		
		public MethodType(String $method) {
			if ($method == null || $method.isEmpty())
				throw new RuntimeException(); // TODO
			this.$method = $method;
		}
		
		public void call(Object $obj, Object... $args) {
			AbusingFunctions.callback($obj, $method).call($args);
		}
	}
	
	public static abstract class Component {
		private Map<String,Object> $notes = new HashMap<String,Object>();
		
		public void setNote(String $name, Object $value) {
			$notes.put($name, $value);
		}
		
		public Object getNote(String $name) {
			return $notes.get($name);
		}
		
		public String getNoteString(String $name) {
			return $notes.get($name).toString();
		}
		
		public int getNoteInt(String $name) {
			return AbusingJava.parseInt($notes.get($name).toString(), 0);
		}
		
		public boolean getNoteBoolean(String $name) {
			return AbusingJava.parseBoolean($notes.get($name).toString(), false);
		}
		
		@XmlAttribute("width")
		Value $width = new Value("150px");
		
		@XmlAttribute("height")
		Value $height = new Value("27px");
		
		@XmlAttribute("min-width")
		Value $minWidth = new Value("0");
		
		@XmlAttribute("min-height")
		Value $minHeight = new Value("0");
		
		@XmlAttribute
		boolean $disabled;
		
		@XmlAttribute
		String $name;
		
		@XmlAttribute
		Color $foreground;
		
		@XmlAttribute("group")
		String $groups;

		@XmlAttribute
		Color $background;
		
		@XmlAttribute
		Bool $opaque;
		
		@XmlAttribute
		int $padding = 0;
		
		@XmlAttribute
		ValidatorType $validator;
		
		@XmlAttribute
		MethodType $onaction;
		
		@XmlAttribute
		MethodType $onclick;
		
		@XmlAttribute
		MethodType $ondblclick;

		@XmlAttribute
		MethodType $onblur;
		
		@XmlAttribute
		MethodType $onfocus;
		
		@XmlAttribute
		MethodType $onmousedown;
		
		@XmlAttribute
		MethodType $onmouseup;
		
		@XmlAttribute
		MethodType $onmousemove;
		
		@XmlAttribute
		MethodType $onmousewheel;
		
		@XmlAttribute
		MethodType $onkeydown;
		
		@XmlAttribute
		MethodType $onkeypress;
		
		@XmlAttribute
		MethodType $onkeyup;
		
		int $index = -1;
		
		boolean $dirty = false;
		
		JComponent $component;
	}
	
	@XmlElement("combobox")
	public static class ComboBox extends Component {
		
		@XmlChildElements(Val.class)
		Val[] $values = new Val[] {};
		
		@XmlAttribute
		boolean $sorted;
		
		@XmlAttribute("autocomplete")
		boolean $autoComplete;
		
		@XmlAttribute
		JavaType $from;
		
		DefaultComboBoxModel $model;
		
		@XmlElement("val")
		public static class Val implements Comparable<Val> {
			
			public Val() {}
			
			public Val(String $text) {
				this.$text = $text;
			}
			
			@XmlTextContent
			String $text;
			
			@XmlAttribute
			boolean $selected = false;
			
			@Override
			public String toString() {
				return $text;
			}
			
			@Override
			public int compareTo(Val $obj) {
				return $text.compareTo($obj.$text);
			}
		}
	}
	
	@XmlElement("date")
	public static class DatePicker extends Component {
		@XmlTextContent
		Date $date;
		
		@XmlAttribute
		MethodType $onaction;
	}
	
	@XmlElement("textarea")
	public static class TextArea extends Component {
		@XmlTextContent
		String $text;
		
		@XmlAttribute("line-wrap")
		boolean $lineWrap = true;
		
		JTextArea $area;
	}
		
	@XmlElement("textfield")
	public static class TextField extends Component {
		@XmlTextContent
		String $text;

		@XmlAttribute
		MethodType $onaction;
		
		@XmlAttribute
		ActionType $action;
	}
	
	@XmlElement("password")
	public static class Password extends Component {
		@XmlTextContent
		String $text;

		@XmlAttribute
		ActionType $action;
	}
	
	@XmlElement("checkbox")
	public static class CheckBox extends Component {
		@XmlTextContent
		String $text;
	}
	
	@XmlElement("numeric")
	public static class Numeric extends Component {
		@XmlAttribute
		double $min;
		
		@XmlAttribute
		double $max;
		
		@XmlAttribute
		double $step;
		
		@XmlTextContent
		double $value;
	}
	
	@XmlElement("table")
	public static class Table extends Component {
		@XmlAttribute
		boolean $sortable = false;
		
		@XmlAttribute("grid-color")
		Color $gridColor;
		
		@XmlChildElements
		Columns[] $columns;
		
		JTable $table;
		
		DefaultTableModel $model;
			
		@XmlElement("col")
		public static class Columns {
			@XmlAttribute
			JavaType $type = new JavaType(java.lang.Object.class);
			
			@XmlAttribute("min-width")
			Integer $minWidth;
			
			@XmlAttribute("max-width")
			Integer $maxWidth;
			
			@XmlTextContent
			String $text = "";
		}
		
		
	}
	
	@XmlElement("tabs")
	public static class Tabs extends Component {
		
		@XmlChildElements
		Tab[] $tabs = new Tab[] {};
		
	}
	
	@XmlElement("tab")
	public static class Tab {
		
		@XmlChildElement
		Title $title;
		
		@XmlChildElements({HBox.class, VBox.class})
		Container[] $content;
	}
	
	@XmlElement("title")
	public static class Title {
		@XmlTextContent
		String $text;
	}
	
	@XmlElement("panes")
	public static class Panes extends Component {
		
		@XmlChildElements
		Pane[] $panes = new Pane[] {};
		
	}
	
	@XmlElement("pane")
	public static class Pane {

		@XmlChildElement
		Title $title;
		
		@XmlChildElements({HBox.class, VBox.class})
		Container[] $content;
		
		@XmlAttribute
		boolean $expanded = false;
		
		@XmlAttribute
		boolean $animated = false;
	}
	
	@XmlElement("label")
	public static class Label extends Component {
		@XmlTextContent
		String $text;
		
		@XmlAttribute("text-align")
		String $textAlign;
		
		@XmlAttribute("font-weight")
		FontWeight $fontWeight;

		@XmlAttribute("font-weight")
		FontStyle $fontStyle;
		
		@XmlAttribute("font-size")
		Value $fontSize;
		
		@XmlAttribute("vertical-alignment")
		String $verticalAlignment;
	}

	@XmlElement("button")
	public static class Button extends Component {
		@XmlTextContent
		String $text;
		
		@XmlAttribute
		ActionType $action;
	}
	
	public static abstract class Container extends Component {
		@XmlChildElements({HBox.class, VBox.class, Table.class,
			Label.class, TextField.class, Button.class, TextArea.class,
			Tabs.class, Panes.class, CheckBox.class, Numeric.class,
			ComboBox.class, DatePicker.class, Password.class})
		Component[] $content = {};
	}
	
	@XmlElement("hbox")
	public static class HBox extends Container {
		
	}
	
	@XmlElement("vbox")
	public static class VBox extends Container {
		
	}
}
