package com.leosal.leotools.widgets;

import java.awt.print.Printable;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class PrintingJTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int BUSINESS = 1;
	
	private int printMode = -1;

	public PrintingJTable() {
	}

	public PrintingJTable(TableModel dm) {
		super(dm);
	}

	public PrintingJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
	}

	public PrintingJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	public PrintingJTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
	}

	public PrintingJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}

	public PrintingJTable(TableModel dm, TableColumnModel cm,
			ListSelectionModel sm) {
		super(dm, cm, sm);
		
	}
	
	public void setPrintingMode(int mode){
		this.printMode=mode;
	}
	
	@Override
	public Printable getPrintable(PrintMode printMode,MessageFormat headerFormat, MessageFormat footerFormat){
		Printable delegate = super.getPrintable(printMode, headerFormat, footerFormat);
		switch(this.printMode){
		case BUSINESS: return new BusinessPrintable(delegate);
		default: return delegate;
		}
	}
	
	

}
