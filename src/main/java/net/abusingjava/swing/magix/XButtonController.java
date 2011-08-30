package net.abusingjava.swing.magix;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.SwingUtilities;

import net.abusingjava.NotGonnaHappenException;
import net.abusingjava.xml.XmlAttribute;

public class XButtonController {
	
	@XmlAttribute
	Boolean $enabled;
	
	@XmlAttribute
	Boolean $visible;
	
	@XmlAttribute
	Boolean $selected;
	
	
	private XButton $button;

	public void init() {
		FutureTask<XButton> $task = new FutureTask<XButton>(new Callable<XButton>() {
			@Override
			public XButton call() {
				$button = new XButton();
				
				if ($enabled != null) {
					$button.setEnabled($enabled);
				}
				if ($visible != null) {
					$button.setVisible($visible);
				}
				if ($selected != null) {
					$button.setSelected($selected);
				}
				return $button;
			}
		});
		SwingUtilities.invokeLater($task);
		try {
			$button = $task.get();
		} catch (Exception $exc) {
			throw new NotGonnaHappenException($exc);
		}
	}
	
	public void reset() {
		
	}
	
	public void setSelected(final boolean $selected) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$button.setSelected($selected);
			}
		});
	}
	
	public boolean getSelected() {
		FutureTask<Boolean> $task = new FutureTask<Boolean>(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return $button.isSelected();
			}
		});
		SwingUtilities.invokeLater($task);
		try {
			return $task.get();
		} catch (Exception $exc) {
			throw new NotGonnaHappenException($exc);
		}
	}
	
	public void setEnabled(final boolean $enabled) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$button.setEnabled($enabled);
			}
		});
	}
	
	public void setVisible(final boolean $visible) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$button.setVisible($visible);
			}
		});
	}
	
}
