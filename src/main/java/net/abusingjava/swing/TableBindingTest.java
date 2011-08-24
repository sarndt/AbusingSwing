package net.abusingjava.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;

import org.jdesktop.beans.AbstractBean;
import org.jdesktop.swingx.JXTable;

public class TableBindingTest {

	public class Filtering extends AbstractBean {
		private RowFilter<Object,Object> $filter1;
		private RowFilter<Object,Object> $filter2;
		
		final JXTable $table;
		
		Filtering(final JXTable $table) {
			this.$table = $table;
		}
		
		private void updateFilters() {
			if (($filter1 != null) && ($filter2 != null)) {
				List<RowFilter<Object,Object>> $filters = new ArrayList<RowFilter<Object,Object>>(2);
				$filters.add($filter1);
				$filters.add($filter2);
				RowFilter<Object,Object> $comboFilter = RowFilter.andFilter($filters);
				$table.setRowFilter($comboFilter);
			} else if ($filter1 != null) {
				
			} else if ($filter2 != null) {
				
			}
		}
		
		
	}
	
	public static void main(final String... $args) {
		AbusingSwing.setNimbusLookAndFeel();
		MagicWindow $window = AbusingSwing.showWindow("table.xml");
		
		$window.setVisible(true);
		
		// JXTable $table = $window.$("#table").as(JXTable.class);
		$window.$("#table").addRows(new Object[][]{
			{"Fuchsbau", "Liebestränke", true},
			{"Unannehmlichkeiten", "Skeptik", false},
			{"Ministerium", "Wirkung", true},
			{"Morgen", "Gewicht", true},
			{"Nachgiebigkeit", "Attraktivität", true},
			{"Umblicken", "Streng", true},
			{"Stunde", "Ablenkung", true},
			{"Gesicht", "allerdings", true}
		});
	}
}
