package com.leosal.medrepear.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.service.RestManager;
import com.toedter.calendar.JDateChooser;

public class RepsFrame extends JInternalFrame {
	

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
					RepsFrame frame = new RepsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<PersonDTO> reps;
	private JTable table;
	private JTextField txtAddress;
	private JTextField txtHomePhone;
	private JTextField txtMobilePhone;
	private JTextField txtSpecialty;
	
	private PersonDTO selectedRep;
	private JDateChooser dateBirthday;

	/**
	 * Create the frame.
	 */
	public RepsFrame() throws UserNotAuthorisedException{
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Representatives");
		
		reps = RestManager.getInstance().getRepresentatives();
		
		this.dispose();
		setBounds(100, 100, 800, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);
		
		MakeChangesToolbar toolBar = new MakeChangesToolbar() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void saveChanges() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeItem() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void print() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void modifyItem() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addItem() {
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
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable();
		
		//table.setModel(new RepsModel());
		
		if(reps!=null){
			initDataBindings();
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
		}
		
		
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(table.getRowCount()==0) return;
				int row = table.getSelectedRow();
				if(row < 0) {
					table.getSelectionModel().setSelectionInterval(0, 0);
				}
				selectedRep=reps.get(table.convertRowIndexToModel(table.getSelectedRow()));
				//selectedContact = (PersonDTO) tableContacts.getModel().getValueAt(row, 0);
				refreshRepInfo();
			}
		});
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new MigLayout("", "[45.00:n][177.00:n,grow]", "[][][][][]"));
		
		JLabel lblBirthday = new JLabel(" Birthday:  ");
		panel.add(lblBirthday, "cell 0 0,alignx left");
		
		dateBirthday = new JDateChooser();
		dateBirthday.setEnabled(false);
		dateBirthday.setDateFormatString("dd/MM/yyyy");
		panel.add(dateBirthday, "cell 1 0,grow");
		
		JLabel lblAdress = new JLabel("Adress:  ");
		panel.add(lblAdress, "cell 0 1,alignx left");
		
		txtAddress = new JTextField();
		txtAddress.setEnabled(false);
		panel.add(txtAddress, "cell 1 1,growx");
		txtAddress.setColumns(10);
		
		JLabel lblHomePhone = new JLabel("Home phone:  ");
		panel.add(lblHomePhone, "cell 0 2,alignx left");
		
		txtHomePhone = new JTextField();
		txtHomePhone.setEnabled(false);
		panel.add(txtHomePhone, "cell 1 2,growx");
		txtHomePhone.setColumns(10);
		
		JLabel lblMobilePhone = new JLabel("Mobile phone:  ");
		panel.add(lblMobilePhone, "cell 0 3,alignx left");
		
		txtMobilePhone = new JTextField();
		txtMobilePhone.setEnabled(false);
		panel.add(txtMobilePhone, "cell 1 3,growx");
		txtMobilePhone.setColumns(10);
		
		JLabel lblSpecialty = new JLabel("Specialty:  ");
		panel.add(lblSpecialty, "cell 0 4,alignx left");
		
		txtSpecialty = new JTextField();
		txtSpecialty.setEnabled(false);
		panel.add(txtSpecialty, "cell 1 4,growx");
		txtSpecialty.setColumns(10);
		initDataBindings();

	}
	
	

	@SuppressWarnings("unused")
	private class RepsModel extends AbstractTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String[] colNames = new String[] {
			"First name", "Mid name", "Last name", "Mobile phone"
		};

		@Override
		public int getRowCount() {
			return reps.size();
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
				return reps.get(rowIndex).getFirstname();
			case 1:
				return reps.get(rowIndex).getMidname();
			case 2:
				return reps.get(rowIndex).getLastname();
			case 3:
				return reps.get(rowIndex).getMobilePhone();
			default:
				break;
			}
			return null;
		}
		
	}
	
	protected void refreshRepInfo() {
		//
		BeanProperty<PersonDTO, Date> personDTOBeanProperty = BeanProperty.create("birthday");
		BeanProperty<JDateChooser, Date> jDateChooserBeanProperty = BeanProperty.create("date");
		AutoBinding<PersonDTO, Date, JDateChooser, Date> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, selectedRep, personDTOBeanProperty, dateBirthday, jDateChooserBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_1 = BeanProperty.create("address");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedRep, personDTOBeanProperty_1, txtAddress, jTextFieldBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_2 = BeanProperty.create("homePhone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedRep, personDTOBeanProperty_2, txtHomePhone, jTextFieldBeanProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_3 = BeanProperty.create("mobilePhone");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedRep, personDTOBeanProperty_3, txtMobilePhone, jTextFieldBeanProperty_2);
		autoBinding_3.bind();
		//
		BeanProperty<PersonDTO, String> personDTOBeanProperty_4 = BeanProperty.create("specialty1.name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, selectedRep, personDTOBeanProperty_4, txtSpecialty, jTextFieldBeanProperty_4);
		autoBinding_4.bind();
		
	}
	protected void initDataBindings() {
		JTableBinding<PersonDTO, List<PersonDTO>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, reps, table);
		//
		BeanProperty<PersonDTO, String> pesrsonDTOBeanProperty = BeanProperty.create("firstname");
		jTableBinding.addColumnBinding(pesrsonDTOBeanProperty).setColumnName("First Name");
		//
		BeanProperty<PersonDTO, String> pesrsonDTOBeanProperty_2 = BeanProperty.create("lastname");
		jTableBinding.addColumnBinding(pesrsonDTOBeanProperty_2).setColumnName("Last Name");
		//
		BeanProperty<PersonDTO, String> pesrsonDTOBeanProperty_3 = BeanProperty.create("midname");
		jTableBinding.addColumnBinding(pesrsonDTOBeanProperty_3).setColumnName("Middle Name");
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
		//
		
	}
}
