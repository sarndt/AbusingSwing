package net.abusingjava.swing;

import java.util.EventObject;

import net.abusingjava.Author;
import net.abusingjava.Version;

@Author("Julian Fleischer")
@Version("2011-08-21")
public class ListSelectionEvent extends EventObject {

	private static final long serialVersionUID = -5841351621197465478L;
	
	final int $index;
	
	
	public ListSelectionEvent(final int $index, final Object $object) {
		super($object);
		this.$index = $index;
	}
	
	
	public int getIndex() {
		return $index;
	}
}
