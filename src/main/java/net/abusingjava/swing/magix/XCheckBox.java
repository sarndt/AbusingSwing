package net.abusingjava.swing.magix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import net.abusingjava.beans.SupportsPropertyChangeEvents;
import net.abusingjava.swing.AbusingSwing;

/**
 * A JCheckBox with Bean-Style Properties “visible”, “selected”, and “enabled”.
 */
public class XCheckBox extends JCheckBox implements SupportsPropertyChangeEvents {

	private static final long serialVersionUID = 8023132873983973635L;

	private final PropertyChangeSupport $$ = new PropertyChangeSupport(this);
	private final boolean $init;
	
	public XCheckBox() {
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

	@SuppressWarnings("serial")
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		
		final XCheckBox $1 = new XCheckBox();
		final XToggleButton $2 = new XToggleButton();
		final XCheckBox $3 = new XCheckBox();
		new JFrame() {{
			setContentPane(new JPanel() {{
				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				add($1);
				add($2);
				add($3);
			}});
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}}.setVisible(true);
		
		Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			$1, BeanProperty.create("selected"),
			$2, BeanProperty.create("selected")).bind();

		Bindings.createAutoBinding(UpdateStrategy.READ_WRITE,
			$2, BeanProperty.create("selected"),
			$3, BeanProperty.create("enabled")).bind();
	}
}
