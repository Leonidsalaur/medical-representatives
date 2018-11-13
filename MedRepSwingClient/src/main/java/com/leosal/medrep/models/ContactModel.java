package com.leosal.medrep.models;

import java.util.Date;
import java.util.List;

import javax.swing.ListModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;
import com.leosal.medrepear.dto.EducTypeDTO;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonTypeDTO;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.dto.UniversityDTO;

@Component
@Scope("prototype")
public class ContactModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ADDRESS_FIELD = "address";
	public static final String BIRTHDAY_FIELD = "birthday";
	public static final String CARDIOPATIENTS_FIELD = "cardioPatients";
	public static final String CATEGORY_FIELD = "category";
	public static final String COMMENT_FIELD = "comment";
	public static final String FIRSTNAME_FIELD = "firstname";
	public static final String FISCALCODE_FIELD = "fiscalCode";
	public static final String HOMEPHONE_FIELD = "homePhone";
	public static final String LASTNAME_FIELD = "lastname";
	public static final String MAIL_FIELD = "mail";
	public static final String MIDNAME_FIELD = "midname";
	public static final String MOBILEPHONE_FIELD = "mobilePhone";
	public static final String NEUROPATIENTS_FIELD = "neuroPatients";
	public static final String NEXTVISIT_FIELD = "nextVisit";
	public static final String SEX_FIELD = "sex";
	public static final String TARGETPRODUCT_FIELD = "targetProduct";
	public static final String TITLE_FIELD = "title";
	public static final String TOTALPATIENTS_FIELD = "totalPatients";
	public static final String WORKPHONE_FIELD = "workPhone";
	public static final String EDUCATION_FIELD = "educType"; 
	public static final String SPECIALTY1_FIELD = "specialty1";
	public static final String SPECIALTY2_FIELD = "specialty2"; 
	public static final String UNIVERSITY_FIELD = "university";
	public static final String CONTACTTYPE_FIELD = "personType";
	
	public static final String INSTITUTIONS_MODEL_FIELD = "intituionsListModel";
	public static final String INSTITUTION_SELECTION_FIELD = "institutionSelection";
	
	
	private String address;
	private Date birthday;
	private int cardioPatients;
	private String category;
	private String comment;
	private String firstname;
	private String fiscalCode;
	private String homePhone;
	private String lastname;
	private String mail;
	private String midname;
	private String mobilePhone;
	private int neuroPatients;
	private Date nextVisit;
	private String sex;
	private String targetProduct;
	private String title;
	private int totalPatients;
	private String workPhone;
	private EducTypeDTO educType; 
	private SpecialtyDTO specialty1;
	private SpecialtyDTO specialty2; 
	private UniversityDTO university;
	private PersonTypeDTO personType;
	private List<InstitutionDTO> institutions;
	
	private ObservableList<InstitutionDTO> instituionsListModel;
	private InstitutionDTO institutionSelection;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		String oldValue = this.address;
		this.address = address;
		firePropertyChange(ADDRESS_FIELD, oldValue, address);
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		Date oldValue = this.birthday;
		this.birthday = birthday;
		firePropertyChange(BIRTHDAY_FIELD, oldValue, birthday);
	}
	
	public int getCardioPatients() {
		return cardioPatients;
	}
	public void setCardioPatients(int cardioPatients) {
		int oldValue = this.cardioPatients;
		this.cardioPatients = cardioPatients;
		firePropertyChange(CARDIOPATIENTS_FIELD, oldValue, cardioPatients);
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		String oldValue = this.category;
		this.category = category;
		firePropertyChange(CATEGORY_FIELD, oldValue, category);
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		String oldValue = this.comment;
		this.comment = comment;
		firePropertyChange(COMMENT_FIELD, oldValue, comment);
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		String oldValue = this.firstname;
		this.firstname = firstname;
		firePropertyChange(FIRSTNAME_FIELD, oldValue, firstname);
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		String oldValue = this.fiscalCode;
		this.fiscalCode = fiscalCode;
		firePropertyChange(FISCALCODE_FIELD, oldValue, fiscalCode);
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		String oldValue = this.homePhone;
		this.homePhone = homePhone;
		firePropertyChange(HOMEPHONE_FIELD, oldValue, homePhone);
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		String oldValue = this.lastname;
		this.lastname = lastname;
		firePropertyChange(LASTNAME_FIELD, oldValue, lastname);
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		String oldValue = this.mail;
		this.mail = mail;
		firePropertyChange(MAIL_FIELD, oldValue, mail);
	}
	
	public String getMidname() {
		return midname;
	}
	public void setMidname(String midname) {
		String oldValue = this.midname;
		this.midname = midname;
		firePropertyChange(MIDNAME_FIELD, oldValue, midname);
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		String oldValue = this.mobilePhone;
		this.mobilePhone = mobilePhone;
		firePropertyChange(MOBILEPHONE_FIELD, oldValue, mobilePhone);
	}
	
	public int getNeuroPatients() {
		return neuroPatients;
	}
	public void setNeuroPatients(int neuroPatients) {
		int oldValue = this.neuroPatients;
		this.neuroPatients = neuroPatients;
		firePropertyChange(NEUROPATIENTS_FIELD, oldValue, neuroPatients);
	}
	
	public Date getNextVisit() {
		return nextVisit;
	}
	public void setNextVisit(Date nextVisit) {
		Date oldValue = this.nextVisit;
		this.nextVisit = nextVisit;
		firePropertyChange(NEXTVISIT_FIELD, oldValue, nextVisit);
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		String oldValue = this.sex;
		this.sex = sex;
		firePropertyChange(SEX_FIELD, oldValue, sex);
	}
	
	public String getTargetProduct() {
		return targetProduct;
	}
	public void setTargetProduct(String targetProduct) {
		String oldValue = this.targetProduct;
		this.targetProduct = targetProduct;
		firePropertyChange(TARGETPRODUCT_FIELD, oldValue, targetProduct);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		String oldValue = this.title;
		this.title = title;
		firePropertyChange(TITLE_FIELD, oldValue, title);
	}
	
	public int getTotalPatients() {
		return totalPatients;
	}
	public void setTotalPatients(int totalPatients) {
		int oldValue = this.totalPatients;
		this.totalPatients = totalPatients;
		firePropertyChange(TOTALPATIENTS_FIELD, oldValue, totalPatients);
	}
	
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		String oldValue = this.workPhone;
		this.workPhone = workPhone;
		firePropertyChange(WORKPHONE_FIELD, oldValue, workPhone);
	}
	
	public EducTypeDTO getEducType() {
		return educType;
	}
	public void setEducType(EducTypeDTO educType) {
		EducTypeDTO oldValue = this.educType;
		this.educType = educType;
		firePropertyChange(EDUCATION_FIELD, oldValue, educType);
	}
	
	public SpecialtyDTO getSpecialty1() {
		return specialty1;
	}
	public void setSpecialty1(SpecialtyDTO specialty1) {
		SpecialtyDTO oldValue = this.specialty1;
		this.specialty1 = specialty1;
		firePropertyChange(SPECIALTY1_FIELD, oldValue, specialty1);
	}
	
	public SpecialtyDTO getSpecialty2() {
		return specialty2;
	}
	public void setSpecialty2(SpecialtyDTO specialty2) {
		SpecialtyDTO oldValue = this.specialty2;
		this.specialty2 = specialty2;
		firePropertyChange(SPECIALTY2_FIELD, oldValue, specialty2);
	}
	
	public UniversityDTO getUniversity() {
		return university;
	}
	public void setUniversity(UniversityDTO university) {
		UniversityDTO oldValue = this.university;
		this.university = university;
		firePropertyChange(UNIVERSITY_FIELD, oldValue, university);
	}
	
	public PersonTypeDTO getPersonType() {
		return personType;
	}
	public void setPersonType(PersonTypeDTO personType) {
		PersonTypeDTO oldValue = this.personType;
		this.personType = personType;
		firePropertyChange(CONTACTTYPE_FIELD, oldValue, personType);
	}
	
	public ObservableList<InstitutionDTO> getInstituionsListModel() {
		return instituionsListModel;
	}
	
	public InstitutionDTO getInstitutionSelection() {
		return institutionSelection;
	}
	public void setInstitutionSelection(InstitutionDTO institutionSelection) {
		InstitutionDTO oldValue = this.institutionSelection;
		this.institutionSelection = institutionSelection;
		firePropertyChange(INSTITUTION_SELECTION_FIELD, oldValue, institutionSelection);
	}
	
	public List<InstitutionDTO> getInstitutions() {
		return institutions;
	}
	public void setInstitutions(List<InstitutionDTO> institutions) {
		ListModel<InstitutionDTO> oldValue = this.instituionsListModel;
		this.institutions = institutions;
		
		if(institutions == null) {
			instituionsListModel = null;
		} else {
			instituionsListModel = new ArrayListModel<>(institutions);
		}
		
		firePropertyChange(INSTITUTIONS_MODEL_FIELD, oldValue, instituionsListModel);
	}
	
	@Override
	public String toString() {
		return "ContactModel [address=" + address + ", birthday=" + birthday + ", cardioPatients=" + cardioPatients
				+ ", category=" + category + ", comment=" + comment + ", firstname=" + firstname + ", fiscalCode="
				+ fiscalCode + ", homePhone=" + homePhone + ", lastname=" + lastname + ", mail=" + mail + ", midname="
				+ midname + ", mobilePhone=" + mobilePhone + ", neuroPatients=" + neuroPatients + ", nextVisit="
				+ nextVisit + ", sex=" + sex + ", targetProduct=" + targetProduct + ", title=" + title
				+ ", totalPatients=" + totalPatients + ", workPhone=" + workPhone + ", educType=" + educType
				+ ", specialty1=" + specialty1 + ", specialty2=" + specialty2 + ", university=" + university
				+ ", personType=" + personType + ", instituionsListModel=" + instituionsListModel
				+ ", institutionSelection=" + institutionSelection + "]";
	}
	
	
}
