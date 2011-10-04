package net.abusingjava.swing.magix;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JTextField;


public class XTextField extends JTextField {

	private static final long serialVersionUID = -3604515948365529165L;

	private final PropertyChangeSupport $$ = new PropertyChangeSupport(this);
	private final boolean $init;

	private String $oldValue = "";
	
	public XTextField() {
		super();
		$init = true;
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(final KeyEvent $ev) {
				String $newValue = getText();
				if (!$oldValue.equals($newValue)) {
					$$.firePropertyChange("text", $oldValue, $newValue);
				}
				$oldValue = $newValue;
			}
			
		});
	}
	
	public XTextField(final String $text) {
		super($text);
		$init = true;
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(final KeyEvent $ev) {
				String $newValue = getText();
				if (!$oldValue.equals($newValue)) {
					$$.firePropertyChange("text", $oldValue, $newValue);
				}
				$oldValue = $newValue;
			}
			
		});
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
