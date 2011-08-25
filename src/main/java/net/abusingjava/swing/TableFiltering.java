package net.abusingjava.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;

import org.jdesktop.beans.AbstractBean;
import org.jdesktop.swingx.JXTable;

public class TableFiltering extends AbstractBean {

	JXTable $table;
	
	TableFiltering(final JXTable $table) {
		this.$table = $table;
	}
	
	void updateFilters(final String $v) {
		List<RowFilter<Object,Object>> $filters = new ArrayList<RowFilter<Object,Object>>(2);
		final String $value = $v.toLowerCase(); 
		if (!$value.isEmpty()) {
			$filters.add(new RowFilter<Object,Object>() {
				@Override
				public boolean include(final javax.swing.RowFilter.Entry<? extends Object, ? extends Object> $entry) {
					return $entry.getStringValue(1).toLowerCase().contains($value);
				}
			});
		}
		/*$filters.add(new RowFilter<Object,Object>() {
			@Override
			public boolean include(final javax.swing.RowFilter.Entry<? extends Object, ? extends Object> $entry) {
				System.out.println($entry.getStringValue(1));
				return false;
			}
		});*/
		RowFilter<Object,Object> $filter = RowFilter.andFilter($filters);
		
		$table.setRowFilter($filter);
	}
	
}
