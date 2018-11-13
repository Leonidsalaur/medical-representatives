package com.leosal.medrep.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.jgoodies.binding.binder.BeanBinder;
import com.jgoodies.binding.binder.Binders;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.leosal.medrepear.dto.ContactDTO;
import com.leosal.medrepear.dto.InstitutionDTO;

import javax.swing.JPanel;

public class Test extends JFrame implements PropertyChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;
	
	String advert;

	private BeanBinder advertBinder;
	private JTextField textField_1;
	private PersonDTOJPanel panel;

	/**
	 * Create the panel.
	 */
	public Test() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		textField = new JTextField();
		getContentPane().add(textField, "2, 2, fill, default");
		textField.setColumns(10);
		
		advert = "Advert Name";
		
		advertBinder = Binders.binderFor(this);
		
		advertBinder.bindProperty("advert").to(textField);
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "2, 4, fill, default");
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(advert);
				System.out.println(panel.toString());
			}
		});
		getContentPane().add(btnNewButton, "2, 6");
		
		ContactDTO contact = new ContactDTO();
		contact.setFirstname("Leonid");
		contact.setLastname("Salaur");
		contact.setTotalPatients(100);
		contact.setCardioPatients(70);
		contact.setBirthday(new Date());
		contact.setSex("M");
		contact.setInstitutions(Arrays.asList(new InstitutionDTO("CMF1", "000"), new InstitutionDTO("CMF2", "001")));
		
		panel = new PersonDTOJPanel();
		panel.setContact(contact);
		getContentPane().add(panel, "2, 8, fill, fill");
		

	}
	
	public static void main(String[] args) {
		Test f = new Test();
		f.setVisible(true);
	}
	
	

	public String getAdvert() {
		return advert;
	}

	public void setAdvert(String advert) {
		this.advert = advert;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println(advert);
		
	}

}
