package net.abusingjava.swing.types;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;
import org.jdesktop.swingx.JXTable;

import net.abusingjava.swing.AbusingSwing;
import net.abusingjava.swing.MagicWindow;

public class BeansBindingTest implements Runnable {

	public static class ObservableLinkedList<T> extends LinkedList<T> implements ObservableList<T> {

		private static final long serialVersionUID = -5006084488765260760L;

		List<ObservableListListener> $listeners = new LinkedList<ObservableListListener>();
		
		@Override
		public boolean add(final T $elem) {
			final int $index = size();
			try {
				$elem.getClass().getMethod("addPropertyChangeListener", PropertyChangeListener.class)
					.invoke($elem, new PropertyChangeListener() {
						@Override
						public void propertyChange(final PropertyChangeEvent $ev) {
							System.out.println("-----------");
							for (ObservableListListener $l : $listeners) {
								System.out.println("ASDIO " + $index);
								$l.listElementPropertyChanged(ObservableLinkedList.this, $index);
							}
						}
					});
			} catch (Exception $exc) {
				System.out.println("HÖLLE");
			}
			boolean $result = super.add($elem);
			for (ObservableListListener $l : $listeners) {
				$l.listElementsAdded(this, $index, 1);
			}
			return $result;
		}
		
		@Override
		public void addObservableListListener(final ObservableListListener $arg) {
			System.out.println("OOOOH");
			$listeners.add($arg);
		}

		@Override
		public void removeObservableListListener(final ObservableListListener $arg) {
			$listeners.remove($arg);
		}

		@Override
		public boolean supportsElementPropertyChanged() {
			System.out.println("AAAAH");
			return true;
		}
		
	}
	
	public static class Thingie {
		PropertyChangeSupport $x = new PropertyChangeSupport(this);
		
		String $name;
		int $number = 4711;
		
		public Thingie() {
		}
		
		public Thingie(final String $string, final int $i) {
			$name = $string;
			$number = $i;
		}

		public String getName() {
			return $name;
		}
		
		public void setName(final String $name) {
			String $oldValue = $name;
			this.$name = $name;
			$x.firePropertyChange("name", $oldValue, $name);
		}
		
		public int getNumberProperty() {
			return $number;
		}
		
		public void setNumberProperty(final int $number) {
			int $oldValue = $number;
			this.$number = $number;
			$x.firePropertyChange("numberProperty", $oldValue, $number);
		}
		
		public void addPropertyChangeListener(final PropertyChangeListener $ev) {
			$x.addPropertyChangeListener($ev);
		}

		public void removePropertyChangeListener(final PropertyChangeListener $ev) {
			$x.removePropertyChangeListener($ev);
		}

		public PropertyChangeListener[] getPropertyChangeListeners() {
			return $x.getPropertyChangeListeners();
		}
	}
	
	BeansBindingTest() {
		
	}
	
	@Override
	public void run() {
		AbusingSwing.setNimbusLookAndFeel();
		
		final MagicWindow $window = AbusingSwing.showWindow("Table.xml");
			
		JTable $table = $window.getMagicPanel().$(JTable.class).as(JXTable.class);
		
		$table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(final TableModelEvent $ev) {
				System.out.println("Table changed");
			}
		});

		final List<Thingie> $list = new LinkedList<Thingie>();
		
		Thingie $thingie = new Thingie();
		$thingie.setName("I lean against the wind");
		$list.add($thingie); 
		
		$thingie = new Thingie();
		$thingie.setName("Pretending I am weightless");
		$list.add($thingie);
		
		$thingie = new Thingie("Are you in?", 42);
		$window.getMagicPanel().bind("binding", $thingie);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException $exc) {
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$window.getMagicPanel().bind("table-binding", $list);
				
				Thingie $t = new Thingie("Hi, I’m a thingie.", 17);
				$window.getMagicPanel().bind("binding", $t);
			}
		});
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException $exc) {
		}
		
		final List<Thingie> $list2 = new LinkedList<Thingie>();
		
		$list2.add(new Thingie("Name", 37));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				$window.getMagicPanel().bind("table-binding", $list2);
				
				$window.getMagicPanel().bind("binding", new Thingie());
			}
		});
	}
	
	public static void main(final String... $args) {
		new BeansBindingTest().run();
	}
}
