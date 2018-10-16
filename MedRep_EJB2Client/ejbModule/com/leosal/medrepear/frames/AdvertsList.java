package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.print.PrinterException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.AdvertDTO;
import com.leosal.medrepear.service.RestManager;

public class AdvertsList extends JPanel implements DatabaseCatalog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	

	private List<AdvertDTO> adverts, modified, removed;
	private JTable table;


	private MakeChangesToolbar toolBar;

	/**
	 * Create the frame.
	 */
	public AdvertsList() throws UserNotAuthorisedException{
		//setResizable(true);
		//setMaximizable(true);
		//setClosable(true);
		//setTitle("Adverts");
		
		adverts = RestManager.getInstance().getAdverts();
		
		//this.dispose();
		//setBounds(100, 100, 628, 452);
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.add(panelTop, BorderLayout.NORTH);
		
		toolBar = new MakeChangesToolbar(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void addItem() {
				AdvertsList.this.addItem();
				
			}

			@Override
			public void modifyItem() {
				AdvertsList.this.modifySelectedItem();
				
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
				AdvertsList.this.removeSelectedItem();
				
			}

			@Override
			public void saveChanges() {
				AdvertsList.this.saveChanges();
				
			}
			
		};
		toolBar.setPrintButton(true);
		toolBar.setFloatable(false);
		panelTop.add(toolBar);
		
		/*
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		});
		btnAdd.setMargin(new Insets(0, 0, 0, 0));
		btnAdd.setIcon(new ImageIcon(AdvertsList.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		btnAdd.setToolTipText("Add new item");
		toolBar.add(btnAdd);
		
		JButton buttonEdit = new JButton("");
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifySelectedItem();
			}
		});
		buttonEdit.setIcon(new ImageIcon(AdvertsList.class.getResource("/com/leosal/medrepear/frames/Graphics/document-edit.png")));
		buttonEdit.setToolTipText("Modify selected item");
		buttonEdit.setMargin(new Insets(0, 0, 0, 0));
		toolBar.add(buttonEdit);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSelectedItem();
			}
		});
		btnDelete.setMargin(new Insets(0, 0, 0, 0));
		btnDelete.setIcon(new ImageIcon(AdvertsList.class.getResource("/com/leosal/medrepear/frames/Graphics/edit-delete.png")));
		btnDelete.setToolTipText("Delete selected item");
		toolBar.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);
		
		btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		btnSave.setMargin(new Insets(0, 0, 0, 0));
		btnSave.setIcon(new ImageIcon(AdvertsList.class.getResource("/com/leosal/medrepear/frames/Graphics/document-save.png")));
		btnSave.setToolTipText("Save changes");
		toolBar.add(btnSave);
		*/
		JPanel panelLeft = new JPanel();
		this.add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		
		JPanel panelRight = new JPanel();
		this.add(panelRight, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		refresh();
		setSaved();

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



	protected void initDataBindings() {
		JTableBinding<AdvertDTO, List<AdvertDTO>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, adverts, table);
		//
		BeanProperty<AdvertDTO, String> advertDTOBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(advertDTOBeanProperty).setColumnName("Advert name");
		//
		BeanProperty<AdvertDTO, Float> advertDTOBeanProperty_1 = BeanProperty.create("price");
		jTableBinding.addColumnBinding(advertDTOBeanProperty_1).setColumnName("Price");
		//
		BeanProperty<AdvertDTO, BigInteger> advertDTOBeanProperty_2 = BeanProperty.create("barCode");
		jTableBinding.addColumnBinding(advertDTOBeanProperty_2).setColumnName("Bar code");
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
	
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#addItem()
	 */
	@Override
	public void addItem(){
		AdvertDTO advert = new AdvertDTO();
		AdvertDTOJPanel panel = new AdvertDTOJPanel(advert);
		int option = JOptionPane.showConfirmDialog(null, panel, "New Advert", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION&&advert.getName()!=null){
			adverts.add(advert);
			Collections.sort(adverts, new Comparator<AdvertDTO>() {

				@Override
				public int compare(AdvertDTO o1, AdvertDTO o2) {
					
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			if(modified==null) modified=new ArrayList<AdvertDTO>();
			if(!modified.contains(advert))modified.add(advert);
			System.out.println("Modified "+modified.size());
			refresh();
			setUnsaved();
		}
	}
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#modifySelectedItem()
	 */
	@Override
	public void modifySelectedItem(){
		int index = table.convertRowIndexToModel(table.getSelectedRow());
		if(index<0) return;
		AdvertDTO advert0 = adverts.get(index);
		AdvertDTO advert = advert0.clone();
		AdvertDTOJPanel panel = new AdvertDTOJPanel(advert);
		int option = JOptionPane.showConfirmDialog(null, panel, "Modify Advert", JOptionPane.OK_CANCEL_OPTION);
		if(option==JOptionPane.OK_OPTION&&advert.getName()!=null){
			advert0.getFrom(advert);
			Collections.sort(adverts, new Comparator<AdvertDTO>() {

				@Override
				public int compare(AdvertDTO o1, AdvertDTO o2) {
					
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			if(modified==null) modified=new ArrayList<AdvertDTO>();
			if(!modified.contains(advert0))modified.add(advert0);
			System.out.println("Modified "+modified.size());
			refresh();
			setUnsaved();
		}
	}
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#removeSelectedItem()
	 */
	@Override
	public void removeSelectedItem(){
		int index = table.convertRowIndexToModel(table.getSelectedRow());
		if(index<0) return;
		AdvertDTO advert = adverts.get(index);
		
		int option = JOptionPane.showConfirmDialog(null, "Remove "+advert.getName()+"?", "Remove advert", JOptionPane.YES_NO_OPTION);
		if(option==JOptionPane.YES_OPTION){
			adverts.remove(advert);
			if(removed==null) removed=new ArrayList<AdvertDTO>();
			if(advert.getId()!=null&&!removed.contains(advert)) removed.add(advert);
			if(modified!=null){
				modified.remove(advert);
				System.out.println("Modified "+modified.size());
			}
			System.out.println("Removed "+removed.size());
			
			
			refresh();
			if((modified==null||modified.size()==0)&&removed.size()==0) this.setSaved();
			else setUnsaved();
		}
	}
	private void setSaved(){
		//this.btnSave.setEnabled(false);
		toolBar.setSaved(true);
		
	}
	private void setUnsaved(){
		toolBar.setSaved(false);
		//this.btnSave.setEnabled(true);
	}
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#refresh()
	 */
	@Override
	public void refresh(){
		initDataBindings();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setPreferredWidth(68);
		table.getColumnModel().getColumn(2).setPreferredWidth(136);
	}
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#saveChanges()
	 */
	@Override
	public void saveChanges(){
		
		List<AdvertDTO> res1;
		try {
			//res1 = ConnectionManager.getInstance().storeDTOs(modified);
			res1 = RestManager.getInstance().saveToDatabase(modified);
			//int res2 = ConnectionManager.getInstance().removeDTOs(removed);
			int res2 = RestManager.getInstance().removeFromDatabase(removed);
			System.out.println("Deleted from database: "+res2);
			if(res1!=null&&res1.size()>0) {
				System.out.println("Modified in database: "+res1.size());
				adverts.removeAll(modified);
				adverts.addAll(res1);
				Collections.sort(adverts, new Comparator<AdvertDTO>() {
	
					@Override
					public int compare(AdvertDTO o1, AdvertDTO o2) {
						
						return o1.getName().compareToIgnoreCase(o2.getName());
					}
				});
			}
			modified=null; removed=null;
			setSaved();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.leosal.medrepear.frames.DatabaseCatalog#isSaved()
	 */
	@Override
	public boolean isSaved(){
		return !((modified!=null&&modified.size()>0)||(removed!=null&&removed.size()>0));
	}
}
