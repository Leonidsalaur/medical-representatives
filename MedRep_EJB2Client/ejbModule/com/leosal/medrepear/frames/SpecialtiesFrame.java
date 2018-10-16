package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.AbstractTableModel;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.service.RestManager;

public class SpecialtiesFrame extends JInternalFrame {
	

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
					Adverts frame = new Adverts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<SpecialtyDTO> specs;
	SpecialtyDTO specialty;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public SpecialtiesFrame() throws UserNotAuthorisedException{
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Specialties");
		
		specs = RestManager.getInstance().getSpecialties();
		specialty = new SpecialtyDTO();
		this.dispose();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		MakeChangesToolbar toolBar = new MakeChangesToolbar(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void addItem() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void modifyItem() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void print() {
				printTable();
				
			}

			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeItem() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void saveChanges() {
				// TODO Auto-generated method stub
				
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
		table.setModel(new TabModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}
	
	protected void printTable() {
		MessageFormat header = new MessageFormat("Adverts list");
		MessageFormat footer = new MessageFormat("- {0} -");
		
		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	private class TabModel extends AbstractTableModel{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private final String[] colNames = new String[] {
			"Specialty"
		};

		@Override
		public int getRowCount() {
			return specs.size();
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
				return specs.get(rowIndex).getName();
			default:
				break;
			}
			return null;
		}
		
	}

}
