package net.abusingjava.swing.magic;

import java.util.Iterator;

import org.jdesktop.beansbinding.BindingGroup;

import net.abusingjava.AbusingArrays;
import net.abusingjava.swing.magic.Binding.Property;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;
import net.abusingjava.xml.XmlTextContent;

@XmlElement("binding")
public class Binding implements Iterable<Property> {
	
	@XmlAttribute
	String $name;
	
	@XmlAttribute("table")
	String $tableName;
	
	@XmlChildElements
	Property $properties[];
	
	@XmlElement("property")
	public static class Property {
		@XmlAttribute
		String $name;
		
		@XmlTextContent
		String $target;
		
		public String getName() {
			return $name;
		}
		
		public String getTarget() {
			return $target;
		}
	}
	
	BindingGroup $group = null;
	
	public void setBinding(final BindingGroup $group) {
		this.$group = $group;
	}
	
	public void clearBinding() {
		if ($group != null) {
			$group.unbind();
			$group = null;
		}
	}
	
	public String getName() {
		if ($name.charAt(0) == '#') {
			return $name.substring(1);
		}
		return $name;
	}
	
	public boolean isTableBinding() {
		return ($tableName != null) && !$tableName.isEmpty();
	}
	
	public String getTableName() {
		return $tableName;
	}

	@Override
	public Iterator<Property> iterator() {
		return AbusingArrays.array($properties).iterator();
	}
}