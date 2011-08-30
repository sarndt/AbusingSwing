package net.abusingjava.swing.magix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JToggleButton;


public class XToggleButton extends JToggleButton {

	private static final long serialVersionUID = 346881449274433975L;


	private final PropertyChangeSupport $$ = new PropertyChangeSupport(this);
	private final boolean $init;
	
	public XToggleButton() {
		$init = true;
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent $ev) {
				$$.firePropertyChange("selected", !getSelected(), getSelected());
			}
		});
	}
	
	public boolean getEnabled() {
		return super.isEnabled();
	}
	
	@Override
	public void setEnabled(final boolean $enabled) {
		if ($init) {
			boolean $oldValue = getEnabled();
			super.setEnabled($enabled);
			$$.firePropertyChange("enabled", $oldValue, getEnabled());
		}
	}
	
	public boolean getSelected() {
		return isSelected();
	}
	
	@Override
	public void setSelected(final boolean $selected) {
		if ($init) {
			boolean $oldValue = getSelected();
			super.setSelected($selected);
			$$.firePropertyChange("selected", $oldValue, getSelected());
		} else {
			super.setSelected($selected);
		}
	}

	public boolean getVisible() {
		return super.isVisible();
	}
	
	@Override
	public void setVisible(final boolean $visible) {
		if ($init) {
			boolean $oldValue = getVisible();
			super.setEnabled($visible);
			$$.firePropertyChange("visible", $oldValue, getVisible());
		}
	}
	
	@Override
	public void addPropertyChangeListener(final PropertyChangeListener $l) {
		if ($init) {
			$$.addPropertyChangeListener($l);
		} else {
			super.addPropertyChangeListener($l);
		}
	}

}
