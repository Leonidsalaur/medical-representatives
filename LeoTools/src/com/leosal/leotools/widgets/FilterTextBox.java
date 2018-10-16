package com.leosal.leotools.widgets;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;

public class FilterTextBox<T extends TableModel> extends AbstractFilterTextBox<T> {
	public static final int ALL_COLUMNS = -1;
	public static final int SELECTED_COLUMN = -2;
	

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type_or_col;

	public FilterTextBox(JTable table, int type_or_col) {
		super(table);
		this.type_or_col = type_or_col;
		
	}
	



	@Override
	public RowFilter<T, Integer> getRowFilter() {
		final String m = this.getText();
		//System.out.println("Filter ["+m+"]");
		if(m.length()==0){
			return null;
		}if(type_or_col>0){
			//set filter for selected column index
			return new RowFilter<T, Integer>(){


				@Override
				public boolean include(
						javax.swing.RowFilter.Entry<? extends T, ? extends Integer> entry) {
					if(type_or_col>=entry.getValueCount())
						return true;
					else{
						//System.out.println(entry.getIdentifier());
						return entry.getValue(type_or_col).toString().toLowerCase().contains(m.toLowerCase());
					}
						
				}
				
			};
		}else if(type_or_col==ALL_COLUMNS){
			//
			return new RowFilter<T, Integer>(){


				@Override
				public boolean include(
						javax.swing.RowFilter.Entry<? extends T, ? extends Integer> entry) {
					String row = "";
					for(int i=0;i<entry.getValueCount();i++)
						row+=entry.getValue(i);
					return row.toLowerCase().contains(m.toLowerCase());
					
						
				}
				
			};
		}else if(type_or_col==SELECTED_COLUMN){
			final int col = table.getSelectedColumn();
			return new RowFilter<T, Integer>(){


				@Override
				public boolean include(
						javax.swing.RowFilter.Entry<? extends T, ? extends Integer> entry) {
					
					Object ob = entry.getValue(col<0?0:col);
					if(ob==null) return false;
					else return ob.toString().toLowerCase().contains(m.toLowerCase());
					
						
				}
				
			};
		}else{
			return null;
		}
			
		
	}
	

}
