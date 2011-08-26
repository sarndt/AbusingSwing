package net.abusingjava.swing.magic;

import net.abusingjava.xml.XmlChildElements;
import net.abusingjava.xml.XmlElement;

@XmlElement("menu")
public class Menu extends MenuItem {
	
	@XmlChildElements({Menu.class, MenuItem.class})
	MenuItem[] $items;
	
	public MenuItem[] getMenuItems() {
		return $items;
	}
}
