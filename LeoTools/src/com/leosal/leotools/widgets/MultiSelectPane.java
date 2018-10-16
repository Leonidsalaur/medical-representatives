package com.leosal.leotools.widgets;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;



public class MultiSelectPane<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> list;
	private List<Boolean> selectedList;
	private JTable table;
	private JTextField txtFilter;
	
	private boolean singleSelection;
	private JLabel lblFilter;

	/**
	 * Create the panel.
	 */
	public MultiSelectPane(final List<T> list, final boolean singleSelection) {
		this.list = list;
		this.singleSelection = singleSelection;
		this.selectedList = new ArrayList<Boolean>();
		for (int i = 0; i < list.size(); i++) {
			selectedList.add(false);
		}
		//System.out.println("Creating list");
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panelTop, BorderLayout.NORTH);
		
		JButton btnSelectAll = new JButton("");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAllItems();
			}
		});
		btnSelectAll.setToolTipText("Select All");
		btnSelectAll.setIcon(new ImageIcon(MultiSelectPane.class.getResource("/com/leosal/leotools/graphics/SelectAll.png")));
		btnSelectAll.setMargin(new Insets(0, 0, 0, 0));
		panelTop.add(btnSelectAll);
		
		JButton btnDeselectAll = new JButton("");
		btnDeselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deselectAllItems();
			}
		});
		btnDeselectAll.setIcon(new ImageIcon(MultiSelectPane.class.getResource("/com/leosal/leotools/graphics/DeselectAll.png")));
		btnDeselectAll.setMargin(new Insets(0, 0, 0, 0));
		btnDeselectAll.setToolTipText("Deselect All");
		panelTop.add(btnDeselectAll);
		
		JPanel panelBottom = new JPanel();
		add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new MigLayout("", "[][grow]", "[20px]"));
		
		lblFilter = new JLabel("Filter:");
		panelBottom.add(lblFilter, "cell 0 0,alignx trailing");
		
		txtFilter = new JTextField();
		
		panelBottom.add(txtFilter, "cell 1 0,growx,aligny top");
		txtFilter.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new MultitableModel());
		table.setPreferredScrollableViewportSize(new Dimension(100, 100));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		//table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setMaxWidth(30);;
		scrollPane.setViewportView(table);
		table.requestFocus();
	
		txtFilter.getDocument().addDocumentListener(new DocumentListener() {
			
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
			public void keyTyped(KeyEvent e) {
				txtFilter.setText(String.valueOf(e.getKeyChar()));
				txtFilter.requestFocus();
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		

	}
	
	protected void deselectAllItems() {
		if(list.size()>0){
			
			for(int i=0;i<table.getRowCount();i++)
				selectedList.set(table.convertRowIndexToModel(i),false);
			refreshViews();
		}
		
	}

	protected void selectAllItems() {
		if(list.size()>0){
			
			for(int i=0;i<table.getRowCount();i++)
				selectedList.set(table.convertRowIndexToModel(i),true);
			refreshViews();
		}
		
	}
	
	private void refreshViews(){
		table.setModel(new MultitableModel());
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(1).setMaxWidth(30);;
		table.requestFocus();
	}

	public void setFilter(boolean visible){
		txtFilter.setVisible(visible);
		lblFilter.setVisible(visible);
	}
	
	public void setPreferedViewportSize(int width, int height){
		table.setPreferredScrollableViewportSize(new Dimension(width, height));
	}
	public int[] getSelectedIndices(){
		List<Integer> selInd = new LinkedList<Integer>();
		for(int i = 0;i<selectedList.size(); i++){
			if(selectedList.get(i)) selInd.add(i);
		}
		int[] selAr = new int[selInd.size()];
		for(int i =0; i< selInd.size(); i++)
			selAr[i] = selInd.get(i);
		return selAr;
	}
	
	
	@SuppressWarnings("unchecked")
	private void setFilter(){
		TableRowSorter<MultitableModel> sorter = new TableRowSorter<MultitableModel>((MultitableModel)table.getModel());
		final String filterText = txtFilter.getText();
		if(filterText.length()==0)
			sorter.setRowFilter(null);
		else{
			RowFilter<MultitableModel, Integer> filter = new RowFilter<MultiSelectPane<T>.MultitableModel, Integer>(){

				@Override
				public boolean include(
						javax.swing.RowFilter.Entry<? extends MultitableModel, ? extends Integer> entry) {
					Object ob = list.get(entry.getIdentifier());
					return ob.toString().toLowerCase().contains(filterText.toLowerCase());
				}
				
			};
			sorter.setRowFilter(filter);
			System.out.println("Filter set to ["+txtFilter.getText()+"]");
		}
			
		table.setRowSorter(sorter);	
	}
	public void setSelectedItems(String[] selectedItems){
		if(list==null || selectedItems==null || selectedItems.length==0) return;
		List<String> itemList = Arrays.asList(selectedItems);
		for(int i=0;i<list.size();i++){
			if(itemList.contains(list.get(i).toString()))
				selectedList.set(i, true);
		}
		//refreshViews();
	}
	public void setSelectedItems(Integer[] selectedIndices){
		if(list==null || selectedIndices==null || selectedIndices.length==0) return;
		List<Integer> itemList = Arrays.asList(selectedIndices);
		for(int i=0;i<list.size();i++){
			if(itemList.contains(new Integer(i)))
				selectedList.set(i, true);
		}
		//refreshViews();
	}
	public void selectAll(){
		for(int i =0;i<selectedList.size(); i++)
			selectedList.set(i, true);
	}
	public void setChangeValueOnClick(){
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				int row = table.rowAtPoint(e.getPoint());
				if(col==0){
					int index = table.convertRowIndexToModel(row);
					selectedList.set(index, !selectedList.get(index));
					refreshViews();
					table.setRowSelectionInterval(row, row);
				}
			}
		});
	}
	private class MultitableModel extends AbstractTableModel{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private  final String[] colNames = new String[]{
			"", ""
		};

		public MultitableModel(){
			
			if(list.size()>0 && selectedList==null){
				selectedList = new ArrayList<Boolean>();
				for(int i=0;i<list.size();i++)
					selectedList.add(false);
			}
		}

		@Override
		public int getColumnCount() {
			
			return 2;
		}

		@Override
		public int getRowCount() {
			
			return list.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col){
			case 0: return list.get(row);
			case 1: return selectedList.get(row);
			}
			return null;
		}
		
		 @Override
         public Class<?> getColumnClass(int column) {
			 switch(column){
			 case 1: return Boolean.class;
			 default: return String.class;
			 }
		 }
		 
		 @Override
		 public boolean isCellEditable(int row, int col){
			 return col==1;
		 }
		 
		 @Override
		 public void setValueAt(Object obj,int row, int col){
				 selectedList.set(row, !selectedList.get(row));
				 if(singleSelection&&selectedList.get(row)){
					 for(int i=0;i<selectedList.size();i++){
						 if(i!=row)selectedList.set(i, false);
					 }
				 }
		 }
		 
		 @Override
		 public String getColumnName(int col){
			 return colNames[col];
		 }
		 
		
	}
	
	public static void main(String[] arg){
		List<String> l = new ArrayList<String>();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("2");
		MultiSelectPane<String> panel = new MultiSelectPane<String>(l, false);
		panel.setPreferedViewportSize(100, 200);
		panel.selectAll();
		panel.setChangeValueOnClick();
		JOptionPane.showConfirmDialog(null, panel, "Test", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	

}
