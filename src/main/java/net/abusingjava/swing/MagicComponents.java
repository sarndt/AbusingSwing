package net.abusingjava.swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.swing.magic.Component;

/**
 * A Collection of Components on which you may apply a function as you want.
 */
@Author("Julian Fleischer")
@Version("2011-08-19")
@Since(value="2011-08-19", version="1.0")
public class MagicComponents {

	final private ArrayList<Component> $components = new ArrayList<Component>();


	public MagicComponents(final Component... $components) {
		this.$components.ensureCapacity($components.length);
		for (Component $c : $components) {
			this.$components.add($c);
		}
	}
	
	public MagicComponents(final Collection<Component> $components) {
		this.$components.ensureCapacity($components.size());
		for (Component $c : $components) {
			this.$components.add($c);
		}
	}

	public MagicComponents setText(final String $text) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTextComponent) {
				((JTextComponent)$c).setText($text);
			} else if ($c instanceof JButton) {
				((JButton)$c).setText($text);
			} else if ($c instanceof JLabel) {
				((JLabel)$c).setText($text);
			} else if ($c instanceof JCheckBox) {
				((JCheckBox)$c).setText($text);
			}
		}
		return this;
	}
	
	public String getText() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c instanceof JTextComponent) {
				return ((JTextComponent)$c).getText();
			}
		}
		return "";
	}
	
	public MagicComponents show() {
		return this;
	}
	
	public MagicComponents show(final int $index) {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c.getLayout() instanceof CardLayout) {
				((CardLayout)$c.getLayout()).show($c, "card" + $index);
			}
		}
		return this;
	}

	public MagicComponents showNext() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c.getLayout() instanceof CardLayout) {
				((CardLayout)$c.getLayout()).next($c);
				System.out.println("harhar");
			}
		}
		return this;
	}

	public MagicComponents showPrev() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c.getLayout() instanceof CardLayout) {
				((CardLayout)$c.getLayout()).previous($c);
			}
		}
		return this;
	}

	public MagicComponents showFirst() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c.getLayout() instanceof CardLayout) {
				((CardLayout)$c.getLayout()).first($c);
			}
		}
		return this;
	}

	public MagicComponents showLast() {
		for (Component $comp : $components) {
			JComponent $c = $comp.getRealComponent();
			if ($c.getLayout() instanceof CardLayout) {
				((CardLayout)$c.getLayout()).last($c);
			}
		}
		return this;
	}
	
	public MagicComponents hide() {
		return this;
	}
	
	public MagicComponents enable() {
		return this;
	}
	
	public MagicComponents disable() {
		return this;
	}
	
	public MagicComponents setForeground(final Color $color) {
		return this;
	}
	
	public MagicComponents setForeground(final String $hexColor) {
		return this;
	}
	
	public MagicComponents setBackground(final Color $color) {
		return this;
	}
	
	public MagicComponents setBackground(final String $hexColor) {
		return this;
	}
	
	public MagicComponents setFont(final String $font) {
		return this;
	}
	
	public MagicComponents setFontSize(final int $size) {
		return this;
	}
	
	public MagicComponents showBalloonTip(final String $message) {
		return this;
	}
	
	public MagicComponents showBalloonTip(final String $message, final int $timeout) {
		return this;
	}
	
	public MagicComponents add(final Object[] $values) {
		return this;
	}
	
	public MagicComponents add(final Object $value) {
		return this;
	}
	
	public MagicComponents setValue(final int $value) {
		return this;
	}
	
	public MagicComponents setMax(final int $value) {
		return this;
	}
	
	public int getMax() {
		return 0;
	}
	
	public int getMin() {
		return 0;
	}
	
	public int getValue() {
		return 0;
	}
	
	public MagicComponents setDate(final Date $date) {
		return this;
	}
	
	public Date getDate() {
		return null;
	}
}
