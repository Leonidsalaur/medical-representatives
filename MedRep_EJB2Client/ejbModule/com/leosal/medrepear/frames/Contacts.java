package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.widgets.FilterTextBox;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.PersonTypeDTO;
import com.leosal.medrepear.service.RestManager;
import com.leosal.medrepear.util.PersonTypeBuilder;
import com.toedter.calendar.JDateChooser;

public class Contacts extends JInternalFrame implements DatabaseCatalog{
	

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
					Contacts frame = new Contacts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<PersonDTO> contacts;
	private PersonDTO selectedContact;
	private ContactsModel contactsModel;
	private JTable tableContacts;
	private JTable tableInstits;
	private JTextField textWorkphone;
	private JTextField textAddres;
	private JTextField textMobile;
	private JTextField textEmail;
	private JTextField textTotalPatients;
	private JTextField textCardioPatients;
	private JTextField textNeuroPatients;
	private JDateChooser dateNextVisit;
	private JDateChooser dateBirthday;
	private ArrayList<PersonDTO> modified, deleted;
	private JTextField txtFilter;
	private MakeChangesToolbar toolBar;
	

	/**
	 * Create the frame.
	 */
	public Contacts() throws com.leosal.leotools.exceptions.UserNotAuthorisedException{
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Contacts");
		
		contacts = RestManager.getInstance().getContacts();
		//allContacts = contacts;
		
		this.dispose();
		setBounds(100, 100, 785, 555);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		toolBar = new MakeChangesToolbar(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void addItem() {
				Contacts.this.addItem();
			}

			@Override
			public void modifyItem() {
				Contacts.this.modifySelectedItem();
				
			}

			@Override
			public void print() {
				Contacts.this.printTable();
			}

			@Override
			public void refresh() {
				Contacts.this.refresh();
			}

			@Override
			public void removeItem() {
				Contacts.this.removeSelectedItem();
				
			}

			@Override
			public void saveChanges() {
				Contacts.this.saveChanges();
				
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
		btnAdd.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		btnAdd.setToolTipText("Add new contact");
		toolBar.add(btnAdd);
		
		JButton btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifySelectedItem();
			}
		});
		btnEdit.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/document-edit.png")));
		btnEdit.setToolTipText("Edit selected item");
		toolBar.add(btnEdit);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSelectedItem();
			}
		});
		btnDelete.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/edit-delete.png")));
		btnDelete.setToolTipText("Delete selected item");
		toolBar.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);
		
		btnSave = new JButton("");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		btnSave.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/document-save.png")));
		btnSave.setToolTipText("Save changes");
		toolBar.add(btnSave);
		*/
		JPanel panelLeft = new JPanel();
		getContentPane().add(panelLeft, BorderLayout.WEST);
		
		JPanel panelBottom = new JPanel();
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblFilter = new JLabel("Filter:");
		panelBottom.add(lblFilter, "cell 0 0,alignx trailing");
		
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.7);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		contactsModel = new ContactsModel();
		tableContacts = new JTable(contactsModel);
		tableContacts.setFillsViewportHeight(true);
		tableContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//  Auto-generated method stub
				if(tableContacts.getRowCount()==0) return;
				int row = tableContacts.getSelectedRow();
				if(row < 0) {
					tableContacts.getSelectionModel().setSelectionInterval(0, 0);
				}
				selectedContact=contacts.get(tableContacts.convertRowIndexToModel(tableContacts.getSelectedRow()));
				//selectedContact = (PersonDTO) tableContacts.getModel().getValueAt(row, 0);
				refresh();;
				
			}
		});
		scrollPane.setViewportView(tableContacts);
		
		
		
		txtFilter = new FilterTextBox<ContactsModel>(tableContacts,FilterTextBox.ALL_COLUMNS);
		/*
		txtFilter.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(tableContacts.getSelectedRow()<0)
					tableContacts.getSelectionModel().setSelectionInterval(0, 0);
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(tableContacts.getSelectedRow()<0)
					tableContacts.getSelectionModel().setSelectionInterval(0, 0);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(tableContacts.getSelectedRow()<0)
					tableContacts.getSelectionModel().setSelectionInterval(0, 0);
			}
		});
		*/
		panelBottom.add(txtFilter, "cell 1 0,growx");
		txtFilter.setColumns(10);
		
		/*
		txtFilter.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				Contacts.this.filterTable();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				Contacts.this.filterTable();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				Contacts.this.filterTable();
			}
		});
		*/
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setMinimumSize(new Dimension(100, 1000));
		panelRight.setLayout(new MigLayout("", "[:100:100px,right][180px:300:500,grow]", "[][grow][][grow][21.00][grow][][][][50][][][][][][][]"));
		
		JLabel lblNextVisit = new JLabel("Next visit:");
		panelRight.add(lblNextVisit, "cell 0 1");
		
		dateNextVisit = new JDateChooser();
		dateNextVisit.setEnabled(false);
		dateNextVisit.setDateFormatString("dd/MM/yyyy");
		panelRight.add(dateNextVisit, "cell 1 1,grow");
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setVisible(false);
		toolBar_1.setFloatable(false);
		panelRight.add(toolBar_1, "cell 0 2 2 1,alignx left");
		
		JButton btnAddInstitution = new JButton("");
		btnAddInstitution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddInstitution.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/list-add.png")));
		btnAddInstitution.setToolTipText("Add institution");
		toolBar_1.add(btnAddInstitution);
		
		JButton btnModifyInstitution = new JButton("");
		btnModifyInstitution.setToolTipText("Modify selected institution");
		btnModifyInstitution.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/document-edit.png")));
		toolBar_1.add(btnModifyInstitution);
		
		JButton btnDeleteInstitution = new JButton("");
		btnDeleteInstitution.setIcon(new ImageIcon(Contacts.class.getResource("/com/leosal/medrepear/frames/Graphics/edit-delete.png")));
		btnDeleteInstitution.setToolTipText("Delete selected institution");
		toolBar_1.add(btnDeleteInstitution);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelRight.add(scrollPane_1, "cell 0 3 2 1");
		
		tableInstits = new JTable();
		tableInstits.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableInstits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableInstits.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tableInstits);
		
		JLabel lblAddress = new JLabel("Address:");
		panelRight.add(lblAddress, "cell 0 4,alignx trailing");
		
		textAddres = new JTextField();
		textAddres.setEditable(false);
		panelRight.add(textAddres, "cell 1 4,grow");
		textAddres.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		panelRight.add(lblBirthday, "cell 0 5,alignx trailing");
		
		dateBirthday = new JDateChooser();
		dateBirthday.setEnabled(false);
		dateBirthday.setDateFormatString("dd/MM/yyyy");
		panelRight.add(dateBirthday, "cell 1 5,grow");
		
		JLabel lblMobilePhone = new JLabel("Mobile phone:");
		panelRight.add(lblMobilePhone, "cell 0 6,alignx trailing");
		
		textMobile = new JTextField();
		textMobile.setEditable(false);
		panelRight.add(textMobile, "cell 1 6,grow");
		textMobile.setColumns(10);
		
		JLabel lblWorkPhone = new JLabel("Work phone:");
		panelRight.add(lblWorkPhone, "cell 0 7");
		
		textWorkphone = new JTextField();
		textWorkphone.setEditable(false);
		panelRight.add(textWorkphone, "cell 1 7,grow");
		textWorkphone.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		panelRight.add(lblEmail, "cell 0 8,alignx trailing");
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		panelRight.add(textEmail, "cell 1 8,grow");
		textEmail.setColumns(10);
		
		JLabel lblTotalPatients = new JLabel("Total patients:");
		panelRight.add(lblTotalPatients, "cell 0 10,alignx trailing");
		
		textTotalPatients = new JTextField();
		textTotalPatients.setEditable(false);
		textTotalPatients.setMaximumSize(new Dimension(50, 2147483647));
		panelRight.add(textTotalPatients, "cell 1 10,alignx left,growy");
		textTotalPatients.setColumns(10);
		
		JLabel lblCardioPatients = new JLabel("Cardio patients:");
		panelRight.add(lblCardioPatients, "cell 0 11,alignx trailing");
		
		textCardioPatients = new JTextField();
		textCardioPatients.setEditable(false);
		textCardioPatients.setMaximumSize(new Dimension(50, 2147483647));
		panelRight.add(textCardioPatients, "cell 1 11,alignx left,growy");
		textCardioPatients.setColumns(10);
		
		JLabel lblNeuroPatients = new JLabel("Neuro patients:");
		panelRight.add(lblNeuroPatients, "cell 0 12,alignx trailing");
		
		textNeuroPatients = new JTextField();
		textNeuroPatients.setEditable(false);
		textNeuroPatients.setMaximumSize(new Dimension(50, 2147483647));
		panelRight.add(textNeuroPatients, "cell 1 12,alignx left,growy");
		textNeuroPatients.setColumns(10);
		
		if(tableContacts.getRowCount()>0)
			tableContacts.getSelectionModel().setSelectionInterval(0, 0);
		
		refresh();
		
		
		tableContacts.getColumnModel().getColumn(0).setPreferredWidth(200);
		
		

	}


	@Override
	public void addItem() {
		PersonDTO pers = new PersonDTO();
		PersonDTOJPanel panel = new PersonDTOJPanel(pers);
		//panel.setPersonDTO(pers);
		int option = JOptionPane.showConfirmDialog(null, panel, "New contact", JOptionPane.OK_CANCEL_OPTION);
		
		if(option==JOptionPane.OK_OPTION
				&&(pers.getFirstname()!=null
				||pers.getLastname()!=null)
				&&pers.getInstitutions()!=null
				&&pers.getInstitutions().size()>0){
			pers.setPersonType(PersonTypeBuilder.build(PersonTypeDTO.HOSPITAL_WORKER));
			contacts.add(pers);
			
			if(modified==null) modified=new ArrayList<PersonDTO>();
			if(!modified.contains(pers))modified.add(pers);
			Logger.getLogger(this.getClass().getName()).info("Modified "+modified.size());
			Collections.sort(contacts, new Comparator<PersonDTO>() {

				@Override
				public int compare(PersonDTO o1, PersonDTO o2) {
					
					return o1.toString().compareTo(o2.toString());
				}
			});
			int in = contacts.indexOf(pers);
			if (in>=0){
				int row = tableContacts.convertRowIndexToView(in);
				if(row>=0)
					tableContacts.getSelectionModel().setSelectionInterval(row, row);
			}
			refresh();
			
			setSaved(false);
		}else{
			Logger.getLogger(this.getClass().getName()).warning("Undefined FML: Contact not saved");
		}
		
	}




	private void setSaved(boolean isSaved) {
		//this.btnSave.setEnabled(!isSaved);
		toolBar.setSaved(isSaved);
	}




	@Override
	public void modifySelectedItem() {
		int row = tableContacts.getSelectedRow();
		if(row<0)return;
		int index = tableContacts.convertRowIndexToModel(row);
		PersonDTO pers = contacts.get(index);
		PersonDTOJPanel panel = new PersonDTOJPanel(pers);
		//panel.setPersonDTO(pers);
		int option = JOptionPane.showConfirmDialog(null, panel, "Modify contact", JOptionPane.OK_CANCEL_OPTION);
		
		if(option==JOptionPane.OK_OPTION
				&&(pers.getFirstname()!=null
				||pers.getLastname()!=null)
				&&pers.getInstitutions()!=null
				&&pers.getInstitutions().size()>0){
			if(pers.getPersonType()==null)
				pers.setPersonType(PersonTypeBuilder.build(PersonTypeDTO.HOSPITAL_WORKER));
			
			if(modified==null) modified=new ArrayList<PersonDTO>();
			if(!modified.contains(pers))modified.add(pers);
			Logger.getLogger(this.getClass().getName()).info("Modified "+modified.size());
			refresh();
			setSaved(false);
		}else{
			Logger.getLogger(this.getClass().getName()).warning("Undefined FML: Contact not saved");
		}
		
	}




	@Override
	public void removeSelectedItem() {
		int row = tableContacts.getSelectedRow();
		if(row<0)return;
		int index = tableContacts.convertRowIndexToModel(row);
		PersonDTO pers = contacts.get(index);
		contacts.remove(index);
		
		if(deleted==null) deleted = new ArrayList<PersonDTO>();
		if(pers.getId()!=null && !deleted.contains(pers))
			deleted.add(pers);
		if(modified!=null && modified.size()>0)
			modified.remove(pers);
		Logger.getLogger(this.getClass().getName()).info("Deleted items: "+deleted.size());
		refresh();
		if(tableContacts.getRowCount()>0){
			if(row==0)
				tableContacts.getSelectionModel().setSelectionInterval(0, 0);
			else
				tableContacts.getSelectionModel().setSelectionInterval(row-1, row-1);
		}
		
			
		setSaved(false);
		
	}
	
	private void printTable(){
		MessageFormat header = new MessageFormat("Contacts list");
		MessageFormat footer = new MessageFormat("- {0} -");
		
		try {
			tableContacts.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void refresh() {
		/*
		Collections.sort(contacts, new Comparator<PersonDTO>() {

			@Override
			public int compare(PersonDTO o1, PersonDTO o2) {
				
				return o1.toString().compareTo(o2.toString());
			}
		});
		*/
		
		
		if(tableContacts.getRowCount()>0)
			;//tableContacts.setRowSelectionInterval(0, 0);
		else return;
		
		initDataBindings();
		
		if(selectedContact!=null && selectedContact.getFirstInstitution()!=null){
			tableInstits.getColumnModel().getColumn(0).setPreferredWidth(200);
			tableInstits.getColumnModel().getColumn(1).setPreferredWidth(150);
			tableInstits.getColumnModel().getColumn(2).setPreferredWidth(150);
		}
		
		
		
		//txtFilter.setText("a");
		//txtFilter.setText("");
		
		//sorter = new TableRowSorter<TableModel>(tableContacts.getModel());
		//tableContacts.setRowSorter(sorter);
		
	}




	@Override
	public void saveChanges() {

		if(modified!=null && modified.size()>0){
			try {
				RestManager.getInstance().saveToDatabase(modified);
				Logger.getLogger(this.getClass().getName()).info(""+modified.size()+" items stored/modified on server");
				modified=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(deleted!=null && deleted.size()>0){
			RestManager.getInstance().removeFromDatabase(deleted);
			Logger.getLogger(this.getClass().getName()).info(""+deleted.size()+" items deleted from server");
			deleted=null;
		}
		setSaved(true);
		
		
	}




	@Override
	public boolean isSaved() {
		// TODO Auto-generated method stub
		return true;
	}
	/*
	 	BeanProperty<JTable, List<InstitutionDTO>> jTableBeanProperty = BeanProperty.create("selectedElement.institutions");
		JTableBinding<InstitutionDTO, JTable, JTable> jTableBinding_1 = SwingBindings.createJTableBinding(UpdateStrategy.READ, tableContacts, jTableBeanProperty, tableInstits);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty = BeanProperty.create("name");
		jTableBinding_1.addColumnBinding(institutionDTOBeanProperty).setColumnName("Institution").setEditable(false);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty_1 = BeanProperty.create("region.name");
		jTableBinding_1.addColumnBinding(institutionDTOBeanProperty_1).setColumnName("Region").setEditable(false);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty_2 = BeanProperty.create("region.rep_name");
		jTableBinding_1.addColumnBinding(institutionDTOBeanProperty_2).setColumnName("Represenatative").setEditable(false);
		//
		jTableBinding_1.setEditable(false);
		jTableBinding_1.bind();
		//
	 */
	
	private class ContactsModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String[] colNames = new String[] {
			"FML", "Category", "Specialty 1", "Specialty 2"
		};

		@Override
		public int getRowCount() {
			return contacts.size();
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
				return contacts.get(rowIndex);
			case 1:
				return contacts.get(rowIndex).getCategory();
			case 2:
				return contacts.get(rowIndex).getSpecialty1();
			case 3:
				return contacts.get(rowIndex).getSpecialty2();

			default:
				break;
			}
			return null;
		}
		
	}
	protected void initDataBindings() {
		BeanProperty<PersonDTO, Date> personDTOBeanProperty = BeanProperty.create("nextVisit");
		BeanProperty<JDateChooser, Date> jDateChooserBeanProperty = BeanProperty.create("date");
		AutoBinding<PersonDTO, Date, JDateChooser, Date> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty, dateNextVisit, jDateChooserBeanProperty);
		autoBinding.bind();
		
		//
		BeanProperty<PersonDTO, List<InstitutionDTO>> personDTOBeanProperty_1 = BeanProperty.create("institutions");
		JTableBinding<InstitutionDTO, PersonDTO, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_1, tableInstits);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(institutionDTOBeanProperty).setColumnName("Institution").setEditable(false);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty_1 = BeanProperty.create("region.name");
		jTableBinding.addColumnBinding(institutionDTOBeanProperty_1).setColumnName("Region").setEditable(false);
		//
		BeanProperty<InstitutionDTO, String> institutionDTOBeanProperty_2 = BeanProperty.create("region.rep_name");
		jTableBinding.addColumnBinding(institutionDTOBeanProperty_2).setColumnName("Represenatative").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
		
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_2 = BeanProperty.create("address");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_2, textAddres, jTextFieldBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<PersonDTO, Date> personDTOBeanProperty_3 = BeanProperty.create("birthday");
		AutoBinding<PersonDTO, Date, JDateChooser, Date> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_3, dateBirthday, jDateChooserBeanProperty);
		autoBinding_2.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_4 = BeanProperty.create("mobilePhone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_4, textMobile, jTextFieldBeanProperty_1);
		autoBinding_3.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_5 = BeanProperty.create("workPhone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_5, textWorkphone, jTextFieldBeanProperty_2);
		autoBinding_4.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_6 = BeanProperty.create("mail");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_6, textEmail, jTextFieldBeanProperty_3);
		autoBinding_5.bind();
		//
		BeanProperty<PersonDTO, Integer> personDTOBeanProperty_7 = BeanProperty.create("totalPatients");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty.create("text");
		AutoBinding<PersonDTO, Integer, JTextField, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_7, textTotalPatients, jTextFieldBeanProperty_4);
		autoBinding_6.bind();
		//
		BeanProperty<PersonDTO, Integer> personDTOBeanProperty_8 = BeanProperty.create("cardioPatients");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_5 = BeanProperty.create("text");
		AutoBinding<PersonDTO, Integer, JTextField, String> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_8, textCardioPatients, jTextFieldBeanProperty_5);
		autoBinding_7.bind();
		//
		BeanProperty<PersonDTO, Integer> personDTOBeanProperty_9 = BeanProperty.create("neuroPatients");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_6 = BeanProperty.create("text");
		AutoBinding<PersonDTO, Integer, JTextField, String> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedContact, personDTOBeanProperty_9, textNeuroPatients, jTextFieldBeanProperty_6);
		autoBinding_8.bind();
	}
}
