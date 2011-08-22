package net.abusingjava.swing.finish;

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
import net.abusingjava.swing.finish.magic.Component;

/**
 * A Collection of Components on which you may apply a function as you want.
 */
@Author("Julian Fleischer")
@Version("2011-08-19")
@Since(value="2011-08-19", version="1.0")
public class MagicComponents {

	final private ArrayList<JComponent> $components = new ArrayList<JComponent>();


	public MagicComponents(final JComponent... $components) {
		this.$components.ensureCapacity($components.length);
		for (JComponent $c : $components) {
			this.$components.add($c);
		}
	}
	
	public MagicComponents(final Collection<Component> $components) {
		this.$components.ensureCapacity($components.size());
		for (Component $c : $components) {
			this.$components.add($c.getJComponent());
		}
	}

	public MagicComponents setText(final String $text) {
		for (JComponent $c : $components) {
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
		for (JComponent $c : $components) {
			if ($c instanceof JTextComponent) {
				return ((JTextComponent)$c).getText();
			}
		}
		return "";
	}
	
	public MagicComponents show() {
		return this;
	}
	
	MagicComponents hide() {
		return this;
	}
	
	MagicComponents enable() {
		return this;
	}
	
	MagicComponents disable() {
		return this;
	}
	
	MagicComponents setForeground(final Color $color) {
		return this;
	}
	
	MagicComponents setForeground(final String $hexColor) {
		return this;
	}
	
	MagicComponents setBackground(final Color $color) {
		return this;
	}
	
	MagicComponents setBackground(final String $hexColor) {
		return this;
	}
	
	MagicComponents setFont(final String $font) {
		return this;
	}
	
	MagicComponents setFontSize(final int $size) {
		return this;
	}
	
	MagicComponents showBalloonTip(final String $message) {
		return this;
	}
	
	MagicComponents showBalloonTip(final String $message, final int $timeout) {
		return this;
	}
	
	MagicComponents add(final Object[] $values) {
		return this;
	}
	
	MagicComponents add(final Object $value) {
		return this;
	}
	
	MagicComponents setValue(final int $value) {
		return this;
	}
	
	MagicComponents setMax(final int $value) {
		return this;
	}
	
	int getMax() {
		return 0;
	}
	
	int getMin() {
		return 0;
	}
	
	int getValue() {
		return 0;
	}
	
	MagicComponents setDate(final Date $date) {
		return this;
	}
	
	Date getDate() {
		return null;
	}
}
