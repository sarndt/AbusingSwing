package net.abusingjava.swing.legacy;

import java.util.List;

import net.abusingjava.Author;
import net.abusingjava.Since;
import net.abusingjava.Version;
import net.abusingjava.reflection.AbusingReflection;

@Author("Julian Fleischer")
@Version("2011-07-26")
@Since("2011-07-26")
public class MagicComponents {

	final Magic.Component[] $magic; 
	
	public MagicComponents() {
		$magic = new Magic.Component[0];
	}
	
	public MagicComponents(final net.abusingjava.swing.legacy.Magic.Component $definition) {
		$magic = new Magic.Component[] { $definition };
	}

	public MagicComponents(final List<net.abusingjava.swing.legacy.Magic.Component> $list) {
		$magic = new Magic.Component[$list.size()];
		$list.toArray($magic);
	}

	public MagicComponents hide() {
		for (Magic.Component $m : $magic) {
			$m.$component.setVisible(true);
		}
		return this;
	}
	
	public MagicComponents show() {
		for (Magic.Component $m : $magic) {
			$m.$component.setVisible(false);
		}
		return this;
	}
	
	public MagicComponents enable() {
		for (Magic.Component $m : $magic) {
			$m.$component.setEnabled(true);
		}
		return this;
	}
	
	public MagicComponents disable() {
		for (Magic.Component $m : $magic) {
			$m.$component.setEnabled(false);
		}
		return this;
	}
	
	public MagicComponents setText(final String $text) {
		for (Magic.Component $m : $magic) {
			if (AbusingReflection.hasMethod($m.$component, "setText")) {
				AbusingReflection.call($m.$component, "setText", $text);
			}
		}
		return this;
	}
}
