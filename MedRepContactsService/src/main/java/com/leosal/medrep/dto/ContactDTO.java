package com.leosal.medrep.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.leosal.medrep.entity.Contact;
import com.leosal.medrep.entity.EducType;
import com.leosal.medrep.entity.Institution;
import com.leosal.medrep.entity.PersonType;
import com.leosal.medrep.entity.Specialty;
import com.leosal.medrep.entity.University;

public class ContactDTO {
	private Integer id;
	private String address;
	private Date birthday;
	private int cardioPatients;
	private String category;
	private String comment;
	private String firstname;
	private String fiscalCode;
	private String homePhone;
	private String lastname;
	private String login;
	private String mail;
	private String midname;
	private String mobilePhone;
	private int neuroPatients;
	private Date nextVisit;
	private String password;
	private String sex;
	private String targetProduct;
	private String title;
	private int totalPatients;
	private String workPhone;
	private Set<InstitutionDTO> institutions;
	private EducTypeDTO educType;
	private SpecialtyDTO specialty1;
	private SpecialtyDTO specialty2;
	private UniversityDTO university;
	private PersonTypeDTO personType;
	
	public ContactDTO() {
		
	}

	public ContactDTO(Contact contact) {
		super();
		this.id = contact.getId();
		this.address = contact.getAddress();
		this.birthday = contact.getBirthday();
		this.cardioPatients = contact.getCardioPatients();
		this.category = contact.getCategory();
		this.comment = contact.getComment();
		this.firstname = contact.getFirstname();
		this.fiscalCode = contact.getFiscalCode();
		this.homePhone = contact.getHomePhone();
		this.lastname = contact.getLastname();
		this.login = contact.getLogin();
		this.mail = contact.getMail();
		this.midname = contact.getMidname();
		this.mobilePhone = contact.getMobilePhone();
		this.neuroPatients = contact.getNeuroPatients();
		this.nextVisit = contact.getNextVisit();
		this.password = contact.getPassword();
		this.sex = contact.getSex();
		this.targetProduct = contact.getTargetProduct();
		this.title = contact.getTitle();
		this.totalPatients = contact.getTotalPatients();
		this.workPhone = contact.getWorkPhone();
		this.institutions = new HashSet<>();
		contact.getInstitutions().forEach(inst -> this.institutions.add(new InstitutionDTO(inst.getId(), inst.getName())));
		
		this.educType = new EducTypeDTO(contact.getEducType());
		this.specialty1 = new SpecialtyDTO(contact.getSpecialty1());
		this.specialty2 = new SpecialtyDTO(contact.getSpecialty2());
		this.university = new UniversityDTO(contact.getUniversity());
		this.personType = new PersonTypeDTO(contact.getPersonType());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getCardioPatients() {
		return cardioPatients;
	}

	public void setCardioPatients(int cardioPatients) {
		this.cardioPatients = cardioPatients;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public int getNeuroPatients() {
		return neuroPatients;
	}

	public void setNeuroPatients(int neuroPatients) {
		this.neuroPatients = neuroPatients;
	}

	public Date getNextVisit() {
		return nextVisit;
	}

	public void setNextVisit(Date nextVisit) {
		this.nextVisit = nextVisit;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTargetProduct() {
		return targetProduct;
	}

	public void setTargetProduct(String targetProduct) {
		this.targetProduct = targetProduct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalPatients() {
		return totalPatients;
	}

	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Set<InstitutionDTO> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Set<InstitutionDTO> institutions) {
		this.institutions = institutions;
	}

	public EducTypeDTO getEducType() {
		return educType;
	}

	public void setEducType(EducTypeDTO educType) {
		this.educType = educType;
	}

	public SpecialtyDTO getSpecialty1() {
		return specialty1;
	}

	public void setSpecialty1(SpecialtyDTO specialty1) {
		this.specialty1 = specialty1;
	}

	public SpecialtyDTO getSpecialty2() {
		return specialty2;
	}

	public void setSpecialty2(SpecialtyDTO specialty2) {
		this.specialty2 = specialty2;
	}

	public UniversityDTO getUniversity() {
		return university;
	}

	public void setUniversity(UniversityDTO university) {
		this.university = university;
	}

	public PersonTypeDTO getPersonType() {
		return personType;
	}

	public void setPersonType(PersonTypeDTO personType) {
		this.personType = personType;
	}
	
	

	
}
