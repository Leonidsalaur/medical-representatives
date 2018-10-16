package com.leosal.leotools.tools;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.beansbinding.Converter;

public  class RowSorterToStringConverter extends Converter<RowSorter<TableModel>,String> {
	private JTable table;

	@Override
	public String convertForward(RowSorter<TableModel> value) {
		return value.toString();
	}

	@Override
	public RowSorter<TableModel> convertReverse(String mask) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getTable().getModel());
		String m = mask.toString();
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<m.length();i++){
			char c = m.charAt(i);
			sb.append('[').append(Character.toLowerCase(c)).append(Character.toUpperCase(c)).append(']');
		}
		sorter.setRowFilter(RowFilter.regexFilter(".\\*"+sb+".\\*", 0));
		return sorter;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	

}
