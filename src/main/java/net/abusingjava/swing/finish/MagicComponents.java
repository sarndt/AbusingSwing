package net.abusingjava.swing.finish;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;

/**
 * A Collection of Components on which you may apply a function as you want.
 */
@Author("Julian Fleischer")
@Version("2011-08-19")
@Since(value="2011-08-19", version="1.0")
public class MagicComponents {

	final List<JComponent> $components;
	final List<JTextComponent> $textComponents;
	
	public MagicComponents(final JComponent... $components) {
		this.$components = new ArrayList<JComponent>($components.length);
		this.$textComponents = new ArrayList<JTextComponent>();
		for (JComponent $c : $components) {
			this.$components.add($c);
			if ($c instanceof JTextComponent) {
				this.$textComponents.add((JTextComponent) $c);
			}
		}
	}
	
	public MagicComponents setText(final String $text) {
		for (JTextComponent $c : $textComponents) {
			$c.setText($text);
		}
		return this;
	}
	
	public String getText() {
		for (JTextComponent $c : $textComponents) {
			return $c.getText();
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
