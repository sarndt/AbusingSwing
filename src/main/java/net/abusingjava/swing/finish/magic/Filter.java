package net.abusingjava.swing.finish.magic;

import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlTextContent;

@XmlElement("filter")
public class Filter {

	@XmlAttribute("table")
	String $table = "";
	
	@XmlChildElements
	Column[] $columns = new Column[] {};
	
	@XmlElement("col")
	public static class Column {
		@XmlTextContent
		String $column;
		
		@XmlAttribute
		String $mode = "contains";
	}
	
}
