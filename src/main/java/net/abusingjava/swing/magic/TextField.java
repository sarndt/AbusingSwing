package net.abusingjava.swing.magic;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("textfield")
public class TextField extends TextComponent {
	
	@XmlAttribute("default-text")
	String $defaultText = "";

	@XmlAttribute
	Filters $filter;
	
	@XmlAttribute("select-on-focus")
	boolean $selectOnFocus = true;
	
	public static class Filters {
		
		public Filters(final String $filters) {
			if (Pattern.matches("\#[a-z]+\(\)", "")) {
				
			}
		}
	}
	
	private boolean $textEntered = false;

	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		if (!$text.isEmpty()) {
			$textEntered = true;
		}
		
		final JTextField $c = new JTextField($text);
		
		$component = $c;

		super.create($main, $parent);
		
		final Color $foreground = $c.getForeground();
		
		if ($selectOnFocus) {
			$component.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(final FocusEvent $ev) {
					$c.selectAll();
				}
			});
		}
		
		if (!$defaultText.isEmpty()) {
			$component.addFocusListener(new FocusAdapter() {

				@Override
				public void focusGained(final FocusEvent $ev) {
					if (!$textEntered) {
						$c.setText("");
						$c.setForeground($foreground);
					}
				}

				@Override
				public void focusLost(final FocusEvent $ev) {
					if ($c.getText().isEmpty()) {
						$textEntered = false;
						$c.setText($defaultText);
						$c.setForeground(Color.GRAY);
					} else {
						$textEntered = true;
					}
				}
				
			});
			
			if ($c.getText().isEmpty()) {
				$textEntered = false;
				$c.setText($defaultText);
				$c.setForeground(Color.GRAY);
			}
		}
	}
	
}