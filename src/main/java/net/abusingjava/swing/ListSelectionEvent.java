package net.abusingjava.swing;

import net.abusingjava.Author;
import net.abusingjava.Version;

@Author("Julian Fleischer")
@Version("2011-08-21")
public class ListSelectionEvent {

	final int $index;
	final Object $object;
	
	
	public ListSelectionEvent(final int $index, final Object $object) {
		this.$index = $index;
		this.$object = $object;
	}
	
	
	public int getIndex() {
		return $index;
	}
	
	public Object getObject() {
		return $object;
	}
}
