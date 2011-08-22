package net.abusingjava.swing.magic;

import javax.swing.JComponent;

import net.abusingjava.Author;

@Author("Julian Fleischer")
public class ComponentMeta {
	final Component $definition;
	final JComponent $component;
	
	ComponentMeta(final Component $definition, final JComponent $component) {
		this.$definition = $definition;
		this.$component = $component;
	}
}
