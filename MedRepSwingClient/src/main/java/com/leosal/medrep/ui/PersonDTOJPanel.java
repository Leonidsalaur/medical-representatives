package com.leosal.medrep.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.adapter.SpinnerAdapterFactory;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.binder.BeanBinder;
import com.jgoodies.binding.binder.Binders;
import com.jgoodies.binding.value.ValueModel;
import com.leosal.medrep.models.ContactModel;
import com.leosal.medrep.models.ContactPresentationModel;
import com.leosal.medrepear.dto.ContactDTO;
import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

@Component
@Scope("prototype")
public class PersonDTOJPanel extends JPanel  implements PropertyChangeListener {

	@Autowired
	private ContactPresentationModel presentationModel;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private BindingGroup m_bindingGroup;
	private ContactDTO personDTO = new ContactDTO();
	
	
	private JTextField firstnameJTextField;
	private JTextField midnameJTextField;
	private JTextField lastnameJTextField;
	private JComboBox<String> sexJComboBox;
	private JTextField addressJTextField;
	private JTextField homePhoneJTextField;
	private JTextField mobilePhoneJTextField;
	private JTextField workPhoneJTextField;
	private JTextField mailJTextField;
	private JTextField titleJTextField;
	private JComboBox<String> categoryJComboBox;
	private JTextField targetProductJTextField;
	private JSpinner totalPatientsJSpinner;
	private JSpinner cardioPatientsJSpinner;
	private JSpinner neuroPatientsJSpinner;
	private JTextArea commentJTextArea;
	private JTable tableInstitutions;
	private JDateChooser dateBirthday;

	private BeanBinder contactBinder;

	public PersonDTOJPanel() {

	}
	
	@PostConstruct
	private void init() {
//		presentationModel = new ContactPresentationModel(new ContactModel());
		
		setLayout(new MigLayout("", "[180.00][165.00px][162.00px]", "[14px][20px][][20px][20px][][22px][20px][20px][20px][20px][20px][][][][][69.00px]"));
		
		JLabel firstnameLabel = new JLabel("Firstname:");
		add(firstnameLabel, "cell 0 0,alignx left,aligny center");
		
				JLabel midnameLabel = new JLabel("Midname:");
				add(midnameLabel, "cell 1 0,alignx left,aligny center");
		
				JLabel lastnameLabel = new JLabel("Lastname:");
				add(lastnameLabel, "cell 2 0,alignx left,aligny center");

		firstnameJTextField = new JTextField();
		add(firstnameJTextField, "cell 0 1,growx,aligny center");

		midnameJTextField = new JTextField();
		add(midnameJTextField, "cell 1 1,growx,aligny center");

		lastnameJTextField = new JTextField();
		add(lastnameJTextField, "cell 2 1,growx,aligny center");
						
						JLabel lblBirthday = new JLabel("Birthday:");
						add(lblBirthday, "cell 1 2");
				
						JLabel sexLabel = new JLabel("Sex:");
						add(sexLabel, "cell 2 2,alignx left,aligny center");
				
				dateBirthday = new JDateChooser();
				dateBirthday.setDateFormatString("dd/MM/yyyy");
				add(dateBirthday, "cell 1 3,grow");
		
				sexJComboBox = new JComboBox<String>();
				sexJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {null,"M", "F"}));
				add(sexJComboBox, "cell 2 3,growx,aligny center");

		JLabel addressLabel = new JLabel("Address:");
		add(addressLabel, "cell 0 4,alignx right,aligny center");

		addressJTextField = new JTextField();
		add(addressJTextField, "cell 1 4 2 1,growx,aligny center");
		
				JLabel mailLabel = new JLabel("Mail:");
				add(mailLabel, "cell 0 5,alignx right,aligny center");
		
				mailJTextField = new JTextField();
				add(mailJTextField, "cell 1 5 2 1,growx,aligny center");

		JLabel homePhoneLabel = new JLabel("HomePhone:");
		add(homePhoneLabel, "cell 0 6,alignx left,aligny center");
		
				JLabel mobilePhoneLabel = new JLabel("MobilePhone:");
				add(mobilePhoneLabel, "cell 1 6,alignx left,aligny center");
		
				JLabel workPhoneLabel = new JLabel("WorkPhone:");
				add(workPhoneLabel, "cell 2 6,alignx left,aligny center");

		homePhoneJTextField = new JTextField();
		add(homePhoneJTextField, "cell 0 7,growx,aligny center");

		mobilePhoneJTextField = new JTextField();
		add(mobilePhoneJTextField, "cell 1 7,growx,aligny center");

		workPhoneJTextField = new JTextField();
		add(workPhoneJTextField, "cell 2 7,growx,aligny center");

		JLabel titleLabel = new JLabel("Title:");
		add(titleLabel, "cell 0 8,alignx left,aligny center");
				
						JLabel targetProductLabel = new JLabel("TargetProduct:");
						add(targetProductLabel, "cell 1 8,alignx left,aligny center");
		
				JLabel categoryLabel = new JLabel("Category:");
				add(categoryLabel, "cell 2 8,alignx left,aligny center");

		titleJTextField = new JTextField();
		add(titleJTextField, "cell 0 9,growx,aligny center");
		
				targetProductJTextField = new JTextField();
				add(targetProductJTextField, "cell 1 9,growx,aligny center");

		categoryJComboBox = new JComboBox<String>();
		categoryJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {null,"G", "S", "M", "I"}));
		add(categoryJComboBox, "cell 2 9,growx,aligny center");

		JLabel totalPatientsLabel = new JLabel("TotalPatients:");
		add(totalPatientsLabel, "cell 0 10,alignx left,aligny center");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 10 2 6,grow");
//				panel.setLayout(new MigLayout("", "[grow]", "[][132.00]"));
//				
//				MakeChangesToolbar toolBar = new MakeChangesToolbar() {
//					
//					/**
//					 * 
//					 */
//					private static final long serialVersionUID = 1L;
//
//					@Override
//					public void saveChanges() {
//						//  Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void removeItem() {
//						// Auto-generated method stub
//						PersonDTOJPanel.this.removeInstitution();
//						
//					}
//					
//					@Override
//					public void refresh() {
//						//  Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void print() {
//						//  Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void modifyItem() {
//						//  Auto-generated method stub
//						
//						
//					}
//					
//					@Override
//					public void addItem() {
//						//  Auto-generated method stub
//						PersonDTOJPanel.this.addInstitution();
//						
//					}
//				};
//				toolBar.setSaveButton(false);
//				toolBar.setPrintButton(false);
//				toolBar.setRefreshButton(false);
//				toolBar.setModifyButton(false);
//				
//				panel.add(toolBar, "cell 0 0");
				
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 1,grow");
		
		//TODO
		tableInstitutions = new JTable(presentationModel.getTableModel());
		scrollPane_1.setViewportView(tableInstitutions);

		totalPatientsJSpinner = new JSpinner();
		add(totalPatientsJSpinner, "cell 0 11,growx,aligny center");
		
				JLabel cardioPatientsLabel = new JLabel("CardioPatients:");
				add(cardioPatientsLabel, "cell 0 12,alignx left,aligny center");
		
				cardioPatientsJSpinner = new JSpinner();
				add(cardioPatientsJSpinner, "cell 0 13,growx,aligny top");
		
				JLabel neuroPatientsLabel = new JLabel("NeuroPatients:");
				add(neuroPatientsLabel, "cell 0 14,alignx left,aligny center");
		
				neuroPatientsJSpinner = new JSpinner();
				add(neuroPatientsJSpinner, "cell 0 15,growx,aligny top");

		JLabel commentLabel = new JLabel("Comment:");
		add(commentLabel, "cell 0 16,alignx right,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 16 2 1,grow");

		commentJTextArea = new JTextArea();
		scrollPane.setViewportView(commentJTextArea);
	}

	protected void addInstitution() {
		//  Auto-generated method stub
//		try {
//			List<InstitutionDTO> insts = RestManager.getInstance().getInstitutions();
//			MultiSelectPane<InstitutionDTO> pane = new MultiSelectPane<InstitutionDTO>(insts, false);
//			pane.setPreferedViewportSize(300, 600);
//			int resp = JOptionPane.showConfirmDialog(null, pane, "Select institutions", JOptionPane.OK_CANCEL_OPTION);
//			if(resp==JOptionPane.CANCEL_OPTION) return;
//			int[] indices = pane.getSelectedIndices();
//			if(indices==null || indices.length==0) return;
//			List<InstitutionDTO> selected = new ArrayList<InstitutionDTO>();
//			for(int i:indices){
//				InstitutionDTO ii = insts.get(i);
//				if(!selected.contains(ii))
//					selected.add(ii);
//			}
//			this.personDTO.setInstitutions(selected);
//			refresh();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	private void refresh() {
//		if (m_bindingGroup != null) {
//			m_bindingGroup.unbind();
//			m_bindingGroup = null;
//		}
//		if (personDTO != null) {
//			m_bindingGroup = initDataBindings();
//		}
	}

	protected void removeInstitution() {
		// TODO Auto-generated method stub
		
	}

	public ContactDTO getContact() {
		return personDTO;
	}

	public void setContact(ContactDTO contact) {
		setContact(contact, true);
	}

	public void setContact(ContactDTO newPersonDTO,
			boolean update) {
		personDTO = newPersonDTO;
		if (update) {
			initDataBindings();
		}
	}
	
	protected void initDataBindings() {
		contactBinder = Binders.binderFor(presentationModel.getBean());
		contactBinder.bindProperty(ContactModel.FIRSTNAME_FIELD).to(firstnameJTextField);
		contactBinder.bindProperty(ContactModel.MIDNAME_FIELD).to(midnameJTextField);
		contactBinder.bindProperty(ContactModel.LASTNAME_FIELD).to(lastnameJTextField);
		contactBinder.bindProperty(ContactModel.ADDRESS_FIELD).to(addressJTextField);
		contactBinder.bindProperty(ContactModel.HOMEPHONE_FIELD).to(homePhoneJTextField);
		contactBinder.bindProperty(ContactModel.MOBILEPHONE_FIELD).to(mobilePhoneJTextField);
		contactBinder.bindProperty(ContactModel.WORKPHONE_FIELD).to(workPhoneJTextField);
		contactBinder.bindProperty(ContactModel.MAIL_FIELD).to(mailJTextField);
		contactBinder.bindProperty(ContactModel.TITLE_FIELD).to(titleJTextField);
		contactBinder.bindProperty(ContactModel.TARGETPRODUCT_FIELD).to(targetProductJTextField);
		contactBinder.bindProperty(ContactModel.COMMENT_FIELD).to(commentJTextArea);
		
		BeanAdapter<ContactModel> beanAdapter = new BeanAdapter<ContactModel>(presentationModel.getBean(), true);
		
		Bindings.bind(sexJComboBox, new ComboBoxAdapter<>(sexJComboBox.getModel(), beanAdapter.getValueModel(ContactModel.SEX_FIELD)));
		
		ValueModel totalPatientsModel = beanAdapter.getValueModel(ContactModel.TOTALPATIENTS_FIELD);
		SpinnerAdapterFactory.connect(totalPatientsJSpinner.getModel(), totalPatientsModel, 0);
		
		ValueModel cardioPatientsModel = beanAdapter.getValueModel(ContactModel.CARDIOPATIENTS_FIELD);
		SpinnerAdapterFactory.connect(cardioPatientsJSpinner.getModel(), cardioPatientsModel, 0);
		
		ValueModel neuroPatientsModel = beanAdapter.getValueModel(ContactModel.NEUROPATIENTS_FIELD);
		SpinnerAdapterFactory.connect(neuroPatientsJSpinner.getModel(), neuroPatientsModel, 0);

		PropertyConnector.connect(dateBirthday, "date", presentationModel.getBean(), ContactModel.BIRTHDAY_FIELD);
		
		this.beanToModel();
		
	}
	
	private void modelToBean() {
		
		if(personDTO == null) {
			return;
		}
		
		ContactModel source = presentationModel.getBean();
		ContactDTO target = personDTO;
		
		target.setFirstname(source.getFirstname());
		target.setMidname(source.getMidname());
		target.setLastname(source.getLastname());
		target.setAddress(source.getAddress());
		target.setHomePhone(source.getHomePhone());
		target.setMobilePhone(source.getMobilePhone());
		target.setWorkPhone(source.getWorkPhone());
		target.setMail(source.getMail());
		target.setTitle(source.getTitle());
		target.setTargetProduct(source.getTargetProduct());
		target.setComment(source.getComment());
		target.setSex(source.getSex());
		
		target.setTotalPatients(source.getTotalPatients());
		target.setCardioPatients(source.getCardioPatients());
		target.setNeuroPatients(source.getNeuroPatients());
		target.setBirthday(source.getBirthday());
		target.setInstitutions(source.getInstitutions());
		
	}
	
	private void beanToModel() {
		
		if(personDTO == null) {
			return;
		}
		
		ContactModel target = presentationModel.getBean();
		ContactDTO source = personDTO;
		
		target.setFirstname(source.getFirstname());
		target.setMidname(source.getMidname());
		target.setLastname(source.getLastname());
		target.setAddress(source.getAddress());
		target.setHomePhone(source.getHomePhone());
		target.setMobilePhone(source.getMobilePhone());
		target.setWorkPhone(source.getWorkPhone());
		target.setMail(source.getMail());
		target.setTitle(source.getTitle());
		target.setTargetProduct(source.getTargetProduct());
		target.setComment(source.getComment());
		target.setSex(source.getSex());
		target.setTotalPatients(source.getTotalPatients());
		target.setCardioPatients(source.getCardioPatients());
		target.setNeuroPatients(source.getNeuroPatients());
		target.setBirthday(source.getBirthday());
		target.setInstitutions(source.getInstitutions());
		
		tableInstitutions.setModel(presentationModel.getTableModel());
	}


	@Override
	public String toString() {
		return "PersonDTOJPanel [contactModel=" + presentationModel.getBean() +  "]";
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println("Property changed " + arg0.getPropertyName());
		
	}
}
