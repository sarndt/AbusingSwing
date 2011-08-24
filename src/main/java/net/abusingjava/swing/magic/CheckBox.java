package net.abusingjava.swing.magic;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JCheckBox;

import net.abusingjava.swing.MagicPanel;
import net.abusingjava.swing.types.Value;
import net.abusingjava.xml.XmlAttribute;
import net.abusingjava.xml.XmlElement;

@XmlElement("checkbox")
public class CheckBox extends TextComponent {
	
    public static class JXCheckBox extends JCheckBox {
    	
    	private static final long serialVersionUID = -6466887398546764160L;

    	private PropertyChangeSupport $__xx = null;
		private boolean $lastState = false;
    	
		public JXCheckBox(final String $text, final boolean $selected) {
			super($text, $selected);
			$lastState = $selected;
		}

		public boolean getSelected() {
			return isSelected();
		}
		
		@Override
		public void setSelected(final boolean $true) {
			boolean $oldValue = getSelected();
			super.setSelected($true);
			if ($__xx != null) {
				$__xx.firePropertyChange("selected", $oldValue, $true);
			}
			System.out.println("huhu");
		}
    			
		@Override
		public void addPropertyChangeListener(final PropertyChangeListener $ev) {
			if ($__xx == null) {
				$__xx = new PropertyChangeSupport(this);
				addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(final ItemEvent $ev) {
						if ($__xx == null) {
							$__xx = new PropertyChangeSupport(this);
						}
						if ($lastState != getSelected()) {
							$__xx.firePropertyChange("selected", $lastState, getSelected());
							$lastState = getSelected();
						}
					}
				});
			}
			$__xx.addPropertyChangeListener($ev);
		}

		@Override
		public void removePropertyChangeListener(final PropertyChangeListener $ev) {
			if ($__xx == null) {
				$__xx = new PropertyChangeSupport(this);
			}
			$__xx.removePropertyChangeListener($ev);
		}

		@Override
		public PropertyChangeListener[] getPropertyChangeListeners() {
			if ($__xx == null) {
				$__xx = new PropertyChangeSupport(this);
			}
			return $__xx.getPropertyChangeListeners();
		}
    	
    }
	
	@XmlAttribute
	boolean $selected = false;
	
	@Override
	public void create(final MagicPanel $main, final MagicPanel $parent) {
		if ($height == null) {
			$height = new Value("27px");
		}
		
		JCheckBox $c = new JCheckBox($text, $selected);
		
		$component = $c;
		
		super.create($main, $parent);
	}
	
}