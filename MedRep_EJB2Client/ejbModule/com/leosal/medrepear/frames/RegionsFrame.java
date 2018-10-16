package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.service.RestManager;

public class RegionsFrame extends JInternalFrame {
	

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
					RegionsFrame frame = new RegionsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<RegionDTO> regions;
	private JTable table;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public RegionsFrame() throws UserNotAuthorisedException{
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Regions");
		
		regions = RestManager.getInstance().getRegions();
		
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
		
		/*
		JButton buttonAdd = new JButton("");
		toolBar.add(buttonAdd);
		buttonAdd.setMargin(new Insets(0, 0, 0, 0));
		buttonAdd.setToolTipText("Add new");
		buttonAdd.setIcon(new ImageIcon(RegionsFrame.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		
		JButton buttonEdit = new JButton("");
		toolBar.add(buttonEdit);
		buttonEdit.setIcon(new ImageIcon(RegionsFrame.class.getResource("/com/leosal/medrepear/frames/Graphics/document-edit.png")));
		buttonEdit.setToolTipText("Modify selected item");
		buttonEdit.setMargin(new Insets(0, 0, 0, 0));
		
		JButton buttonDelete = new JButton("");
		toolBar.add(buttonDelete);
		buttonDelete.setIcon(new ImageIcon(RegionsFrame.class.getResource("/com/leosal/medrepear/frames/Graphics/edit-delete.png")));
		buttonDelete.setToolTipText("Delete selected item");
		buttonDelete.setMargin(new Insets(0, 0, 0, 0));
		
		JButton buttonSave = new JButton("");
		toolBar.add(buttonSave);
		buttonSave.setIcon(new ImageIcon(RegionsFrame.class.getResource("/com/leosal/medrepear/frames/Graphics/document-save.png")));
		buttonSave.setToolTipText("Save changes");
		buttonSave.setMargin(new Insets(0, 0, 0, 0));
		*/
		textField = new JTextField();
		panelTop.add(textField);
		textField.setColumns(10);
		
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel();
		getContentPane().add(panelRight, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable() ;
		
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initDataBindings();
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		

	}
	protected void printTable() {

			MessageFormat header = new MessageFormat("Regions list");
			MessageFormat footer = new MessageFormat("- {0} -");
			
			try {
				table.print(PrintMode.FIT_WIDTH, header, footer);
			} catch (PrinterException e) {
				e.printStackTrace();
			}
			
		
	}
	protected void initDataBindings() {
		JTableBinding<RegionDTO, List<RegionDTO>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, regions, table);
		//
		BeanProperty<RegionDTO, String> regionDTOBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(regionDTOBeanProperty).setColumnName("Region").setEditable(false);
		//
		BeanProperty<RegionDTO, String> regionDTOBeanProperty_1 = BeanProperty.create("rep_name");
		jTableBinding.addColumnBinding(regionDTOBeanProperty_1).setColumnName("Representative").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
		//
		BeanProperty<JTable, String> jTableBeanProperty = BeanProperty.create("selectedElement.rep_name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JTable, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, table, jTableBeanProperty, textField, jTextFieldBeanProperty, "textRep");
		autoBinding.bind();
	}
}
