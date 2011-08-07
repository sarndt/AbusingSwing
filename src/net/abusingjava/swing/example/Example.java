/* Part of the AbusingJava-Library.
 * 
 * Source:  http://github.com/scravy/AbusingJava
 * Home:    http://www.abusingjava.net/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.abusingjava.swing.example;

import java.util.LinkedList;
import java.util.List;

public class Example {

	private String $value = "This IIIIS a Value";
	private boolean $b = true;
    private final java.beans.PropertyChangeSupport propertySupport = new java.beans.PropertyChangeSupport(this);
    private List<Example> $list = new LinkedList<Example>();

    public void addPropertyChangeListener(final java.beans.PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(final java.beans.PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
	
    public String getValue() {
    	return $value;
    }
    
    public void setValue(final String $string) {
    	propertySupport.firePropertyChange("value", $value, $string);
    	$value = $string;
    }
    
    public boolean getBoolean() {
    	return $b;
    }
    
    public void setBoolean(final Boolean $b) {
    	propertySupport.firePropertyChange("boolean", (Object) this.$b, $b);
    	this.$b = $b;
    }
    
    public List<Example> getList() {
    	return this.$list;
    }
    
    @SuppressWarnings("cast")
	public void setList(final List<Example> $list) {
    	propertySupport.firePropertyChange("list", (Object) this.$list, $list);
    	this.$list = $list;
    }
}
