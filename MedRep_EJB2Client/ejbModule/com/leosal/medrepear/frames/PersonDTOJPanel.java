package com.leosal.medrepear.frames;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.leosal.leotools.widgets.MakeChangesToolbar;
import com.leosal.leotools.widgets.MultiSelectPane;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.service.RestManager;
import com.toedter.calendar.JDateChooser;

public class PersonDTOJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BindingGroup m_bindingGroup;
	private com.leosal.medrepear.dto.PersonDTO personDTO = new com.leosal.medrepear.dto.PersonDTO();
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

	public PersonDTOJPanel(com.leosal.medrepear.dto.PersonDTO newPersonDTO) {
		this();
		setPersonDTO(newPersonDTO);
	}

	public PersonDTOJPanel() {
		
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
				panel.setLayout(new MigLayout("", "[grow]", "[][132.00]"));
				
				MakeChangesToolbar toolBar = new MakeChangesToolbar() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void saveChanges() {
						//  Auto-generated method stub
						
					}
					
					@Override
					public void removeItem() {
						// Auto-generated method stub
						PersonDTOJPanel.this.removeInstitution();
						
					}
					
					@Override
					public void refresh() {
						//  Auto-generated method stub
						
					}
					
					@Override
					public void print() {
						//  Auto-generated method stub
						
					}
					
					@Override
					public void modifyItem() {
						//  Auto-generated method stub
						
						
					}
					
					@Override
					public void addItem() {
						//  Auto-generated method stub
						PersonDTOJPanel.this.addInstitution();
						
					}
				};
				toolBar.setSaveButton(false);
				toolBar.setPrintButton(false);
				toolBar.setRefreshButton(false);
				toolBar.setModifyButton(false);
				
				panel.add(toolBar, "cell 0 0");
				
				JScrollPane scrollPane_1 = new JScrollPane();
				panel.add(scrollPane_1, "cell 0 1,grow");
				
				tableInstitutions = new JTable();
				scrollPane_1.setViewportView(tableInstitutions);
		
				totalPatientsJSpinner = new JSpinner();
				add(totalPatientsJSpinner, "cell 0 11,growx,aligny center");
				
						JLabel cardioPatientsLabel = new JLabel("CardioPatients:");
						add(cardioPatientsLabel, "cell 0 12,alignx left,aligny center");
				
						cardioPatientsJSpinner = new JSpinner();
						add(cardioPatientsJSpinner, "cell 0 13,growx,aligny center");
				
						JLabel neuroPatientsLabel = new JLabel("NeuroPatients:");
						add(neuroPatientsLabel, "cell 0 14,alignx left,aligny center");
				
						neuroPatientsJSpinner = new JSpinner();
						add(neuroPatientsJSpinner, "cell 0 15,growx,aligny center");
		
				JLabel commentLabel = new JLabel("Comment:");
				add(commentLabel, "cell 0 16,alignx right,aligny top");
				
				JScrollPane scrollPane = new JScrollPane();
				add(scrollPane, "cell 1 16 2 1,grow");
		
				commentJTextArea = new JTextArea();
				scrollPane.setViewportView(commentJTextArea);
				

		if (personDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected void addInstitution() {
		//  Auto-generated method stub
		try {
			List<InstitutionDTO> insts = RestManager.getInstance().getInstitutions();
			MultiSelectPane<InstitutionDTO> pane = new MultiSelectPane<InstitutionDTO>(insts, false);
			pane.setPreferedViewportSize(300, 600);
			int resp = JOptionPane.showConfirmDialog(null, pane, "Select institutions", JOptionPane.OK_CANCEL_OPTION);
			if(resp==JOptionPane.CANCEL_OPTION) return;
			int[] indices = pane.getSelectedIndices();
			if(indices==null || indices.length==0) return;
			List<InstitutionDTO> selected = new ArrayList<InstitutionDTO>();
			for(int i:indices){
				InstitutionDTO ii = insts.get(i);
				if(!selected.contains(ii))
					selected.add(ii);
			}
			this.personDTO.setInstitutions(selected);
			refresh();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void refresh() {
		if (m_bindingGroup != null) {
			m_bindingGroup.unbind();
			m_bindingGroup = null;
		}
		if (personDTO != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected void removeInstitution() {
		// TODO Auto-generated method stub
		
	}

	public com.leosal.medrepear.dto.PersonDTO getPersonDTO() {
		return personDTO;
	}

	public void setPersonDTO(com.leosal.medrepear.dto.PersonDTO newPersonDTO) {
		setPersonDTO(newPersonDTO, true);
	}

	public void setPersonDTO(com.leosal.medrepear.dto.PersonDTO newPersonDTO,
			boolean update) {
		personDTO = newPersonDTO;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (personDTO != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}
	protected BindingGroup initDataBindings() {
		BeanProperty<PersonDTO, String> firstnameProperty = BeanProperty.create("firstname");
		BeanProperty<JTextField, String> textProperty = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, firstnameProperty, firstnameJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<PersonDTO, String> midnameProperty = BeanProperty.create("midname");
		BeanProperty<JTextField, String> textProperty_1 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, midnameProperty, midnameJTextField, textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<PersonDTO, String> lastnameProperty = BeanProperty.create("lastname");
		BeanProperty<JTextField, String> textProperty_2 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, lastnameProperty, lastnameJTextField, textProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<PersonDTO, String> addressProperty = BeanProperty.create("address");
		BeanProperty<JTextField, String> textProperty_3 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, addressProperty, addressJTextField, textProperty_3);
		autoBinding_4.bind();
		//
		BeanProperty<PersonDTO, String> homePhoneProperty = BeanProperty.create("homePhone");
		BeanProperty<JTextField, String> textProperty_4 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, homePhoneProperty, homePhoneJTextField, textProperty_4);
		autoBinding_5.bind();
		//
		BeanProperty<PersonDTO, String> mobilePhoneProperty = BeanProperty.create("mobilePhone");
		BeanProperty<JTextField, String> textProperty_5 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, mobilePhoneProperty, mobilePhoneJTextField, textProperty_5);
		autoBinding_6.bind();
		//
		BeanProperty<PersonDTO, String> workPhoneProperty = BeanProperty.create("workPhone");
		BeanProperty<JTextField, String> textProperty_6 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, workPhoneProperty, workPhoneJTextField, textProperty_6);
		autoBinding_7.bind();
		//
		BeanProperty<PersonDTO, String> mailProperty = BeanProperty.create("mail");
		BeanProperty<JTextField, String> textProperty_7 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, mailProperty, mailJTextField, textProperty_7);
		autoBinding_8.bind();
		//
		BeanProperty<PersonDTO, String> titleProperty = BeanProperty.create("title");
		BeanProperty<JTextField, String> textProperty_8 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, titleProperty, titleJTextField, textProperty_8);
		autoBinding_9.bind();
		//
		BeanProperty<PersonDTO, String> targetProductProperty = BeanProperty.create("targetProduct");
		BeanProperty<JTextField, String> textProperty_9 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextField, String> autoBinding_11 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, targetProductProperty, targetProductJTextField, textProperty_9);
		autoBinding_11.bind();
		//
		BeanProperty<PersonDTO, Integer> totalPatientsProperty = BeanProperty.create("totalPatients");
		BeanProperty<JSpinner, Object> valueProperty = BeanProperty.create("value");
		AutoBinding<PersonDTO, Integer, JSpinner, Object> autoBinding_12 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, totalPatientsProperty, totalPatientsJSpinner, valueProperty);
		autoBinding_12.bind();
		//
		BeanProperty<PersonDTO, Integer> cardioPatientsProperty = BeanProperty.create("cardioPatients");
		BeanProperty<JSpinner, Object> valueProperty_1 = BeanProperty.create("value");
		AutoBinding<PersonDTO, Integer, JSpinner, Object> autoBinding_13 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, cardioPatientsProperty, cardioPatientsJSpinner, valueProperty_1);
		autoBinding_13.bind();
		//
		BeanProperty<PersonDTO, Integer> neuroPatientsProperty = BeanProperty.create("neuroPatients");
		BeanProperty<JSpinner, Object> valueProperty_2 = BeanProperty.create("value");
		AutoBinding<PersonDTO, Integer, JSpinner, Object> autoBinding_14 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, neuroPatientsProperty, neuroPatientsJSpinner, valueProperty_2);
		autoBinding_14.bind();
		//
		BeanProperty<PersonDTO, String> commentProperty = BeanProperty.create("comment");
		BeanProperty<JTextArea, String> textProperty_10 = BeanProperty.create("text");
		AutoBinding<PersonDTO, String, JTextArea, String> autoBinding_15 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, commentProperty, commentJTextArea, textProperty_10);
		autoBinding_15.bind();
		//
		BeanProperty<PersonDTO, List<InstitutionDTO>> personDTOBeanProperty = BeanProperty.create("institutions");
		JTableBinding<InstitutionDTO, PersonDTO, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, personDTO, personDTOBeanProperty, tableInstitutions);
		//
		BeanProperty<InstitutionDTO, String> institutionBeanProperty = BeanProperty.create("name");
		jTableBinding.addColumnBinding(institutionBeanProperty).setColumnName("Institutions").setEditable(false);
		//
		jTableBinding.bind();
		//
		BeanProperty<PersonDTO, Date> personDTOBeanProperty_1 = BeanProperty.create("birthday");
		BeanProperty<JDateChooser, Date> jDateChooserBeanProperty = BeanProperty.create("date");
		AutoBinding<PersonDTO, Date, JDateChooser, Date> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, personDTO, personDTOBeanProperty_1, dateBirthday, jDateChooserBeanProperty);
		autoBinding_3.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_5);
		bindingGroup.addBinding(autoBinding_6);
		bindingGroup.addBinding(autoBinding_7);
		bindingGroup.addBinding(autoBinding_8);
		bindingGroup.addBinding(autoBinding_9);
		bindingGroup.addBinding(autoBinding_11);
		bindingGroup.addBinding(autoBinding_12);
		bindingGroup.addBinding(autoBinding_13);
		bindingGroup.addBinding(autoBinding_14);
		bindingGroup.addBinding(autoBinding_15);
		bindingGroup.addBinding(jTableBinding);
		bindingGroup.addBinding(autoBinding_3);
		return bindingGroup;
	}
}
