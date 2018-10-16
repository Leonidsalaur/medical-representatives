package com.leosal.leotools.widgets;

import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public abstract class AbstractFilterTextBox<T extends TableModel> extends JTextField {
	protected JTable table;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AbstractFilterTextBox(JTable table){
		super();
		this.table = table;
		//new TableRowSorter<TableModel>(table.getModel());
		
		this.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				setFilter();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				setFilter();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				setFilter();
				
			}
		});
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				AbstractFilterTextBox.this.setText(""+e.getKeyChar());
				AbstractFilterTextBox.this.setCaretPosition(1);
				AbstractFilterTextBox.this.requestFocus();
			}
			
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				
			}
		});
		/*
		setFilter();
		@SuppressWarnings("unchecked")
		TableRowSorter<T> sorter = (TableRowSorter<T>) table.getRowSorter();
		sorter.setRowFilter(new RowFilter<T, Integer>(){

			@Override
			public boolean include(
					javax.swing.RowFilter.Entry<? extends T, ? extends Integer> entry) {
				return true;
			}
			
		});
		*/
		//this.setText("t");//testing
		//this.setText("");
		
	}
	
	@SuppressWarnings("unchecked")
	protected void setFilter(){
		//System.out.println(this.getText());
		TableRowSorter<T> sorter = (TableRowSorter<T>) table.getRowSorter();
		if(sorter == null){
			
			sorter = new TableRowSorter<T>((T) table.getModel());
			table.setRowSorter(sorter);
			System.out.println("Sorter created");
		}
		sorter.setRowFilter(getRowFilter());
		if(table.getRowCount()>0)
			table.getSelectionModel().setSelectionInterval(0, 0);
	}
	
	public abstract javax.swing.RowFilter<T, Integer> getRowFilter();

}
