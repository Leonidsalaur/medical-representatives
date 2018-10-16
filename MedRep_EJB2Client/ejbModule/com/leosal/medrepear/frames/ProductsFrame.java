package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.AbstractTableModel;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.service.RestManager;

public class ProductsFrame extends JInternalFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductsFrame frame = new ProductsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<ProductDTO> products;
	private JTable table;
	private MakeChangesToolbar toolBar;
	private ArrayList<ProductDTO> modified;

	/**
	 * Create the frame.
	 */
	public ProductsFrame() throws UserNotAuthorisedException{
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Products");
		
		products = RestManager.getInstance().getProducts();
		
		this.dispose();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		toolBar = new MakeChangesToolbar() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void saveChanges() {
				ProductsFrame.this.saveChanges();
				
			}
			
			@Override
			public void removeItem() {
				ProductsFrame.this.removeSelected();
				
			}
			
			@Override
			public void refresh() {
				ProductsFrame.this.refresh();
				
			}
			
			@Override
			public void print() {
				ProductsFrame.this.print();
				
			}
			
			@Override
			public void modifyItem() {
				ProductsFrame.this.modifySelected();
			}
			
			@Override
			public void addItem() {
				ProductsFrame.this.addProduct();
			}
		};
		toolBar.setPrintButton(true);
		toolBar.setFloatable(false);
		
		panelTop.add(toolBar);
		
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel();
		getContentPane().add(panelRight, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new AdvertModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setPreferredWidth(68);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}
	
	protected void addProduct() {
		ProductDTO prod = new ProductDTO();
		ProductDTOJPanel panel = new ProductDTOJPanel(prod);
		int option = JOptionPane.showConfirmDialog(null, panel, "New Product", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION&&prod.getName()!=null){
			products.add(prod);
			Collections.sort(products, new Comparator<ProductDTO>() {

				@Override
				public int compare(ProductDTO o1, ProductDTO o2) {
					
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			if(modified==null) modified=new ArrayList<ProductDTO>();
			if(!modified.contains(prod))modified.add(prod);
			//System.out.println("Modified "+modified.size());
			refresh();
			setSaved(false);
		}
	}

	private void setSaved(boolean b) {
		toolBar.setSaved(b);
		
	}

	protected void modifySelected() {
		int index = table.convertRowIndexToModel(table.getSelectedRow());
		if(index<0) return;
		ProductDTO prod = products.get(index);
		ProductDTOJPanel panel = new ProductDTOJPanel(prod);
		int option = JOptionPane.showConfirmDialog(null, panel, "Modify Product", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION&&prod.getName()!=null){
			products.add(prod);
			Collections.sort(products, new Comparator<ProductDTO>() {

				@Override
				public int compare(ProductDTO o1, ProductDTO o2) {
					
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			if(modified==null) modified=new ArrayList<ProductDTO>();
			if(!modified.contains(prod))modified.add(prod);
			//System.out.println("Modified "+modified.size());
			refresh();
			setSaved(false);
		}
	}

	protected void print() {
		MessageFormat header = new MessageFormat("Regions list");
		MessageFormat footer = new MessageFormat("- {0} -");
		
		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	protected void refresh() {
		// TODO Auto-generated method stub
		
	}

	protected void removeSelected() {
		// TODO Auto-generated method stub
		
	}

	protected void saveChanges() {
		// TODO Auto-generated method stub
		
	}

	private class AdvertModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String[] colNames = new String[] {
			"Name", "Price", "Bar code"
		};

		@Override
		public int getRowCount() {
			return products.size();
		}

		@Override
		public int getColumnCount() {
			return colNames.length;
		}
		
		@Override
		public String getColumnName(int colIndex){
			return colNames[colIndex];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return products.get(rowIndex).getName();
			case 1:
				return products.get(rowIndex).getPrice();
			case 2:
				return products.get(rowIndex).getBarCode();

			default:
				break;
			}
			return null;
		}
		
	}

}
