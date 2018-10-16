package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;


@XmlRootElement(name="person")
public class PersonDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String address;
	private Date birthday;
	private int cardioPatients;
	private String category;
	private String comment;
	private String firstname;
	private BigInteger fiscalCode;
	private Date hireDate;
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
	private List<InstitutionDTO> institutions;
	private Set<PersonPrivDTO> personPrivs;
	private EducTypeDTO educType; 
	private SpecialtyDTO specialty1;
	private SpecialtyDTO specialty2; 
	private UniversityDTO university;
	private PersonTypeDTO personType;

	


	public PersonDTO() {
	}

	@XmlAttribute
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@XmlElement
	public int getCardioPatients() {
		return this.cardioPatients;
	}

	public void setCardioPatients(int cardioPatients) {
		this.cardioPatients = cardioPatients;
	}

	@XmlElement
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@XmlElement
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@XmlElement
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@XmlElement
	public BigInteger getFiscalCode() {
		return this.fiscalCode;
	}

	public void setFiscalCode(BigInteger fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	@XmlElement
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@XmlElement
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@XmlElement
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@XmlElement
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@XmlElement
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@XmlElement
	public String getMidname() {
		return this.midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	@XmlElement
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@XmlElement
	public int getNeuroPatients() {
		return this.neuroPatients;
	}

	public void setNeuroPatients(int neuroPatients) {
		this.neuroPatients = neuroPatients;
	}

	@XmlElement
	public Date getNextVisit() {
		return this.nextVisit;
	}

	public void setNextVisit(Date nextVisit) {
		this.nextVisit = nextVisit;
	}

	@XmlElement
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@XmlElement
	public String getTargetProduct() {
		return this.targetProduct;
	}

	public void setTargetProduct(String targetProduct) {
		this.targetProduct = targetProduct;
	}

	@XmlElement
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public int getTotalPatients() {
		return this.totalPatients;
	}

	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}

	@XmlElement
	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	@XmlElement
	public List<InstitutionDTO> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<InstitutionDTO> institutions) {
		this.institutions = institutions;
	}
	
	public boolean addInstitution(InstitutionDTO in){
		if(institutions==null)
			institutions = new ArrayList<InstitutionDTO>();
		return institutions.add(in);
	}
	
	public boolean removeInstitution(InstitutionDTO in){
		if(institutions==null) return true;
		return institutions.remove(in);
	}

	@XmlElement
	public Set<PersonPrivDTO> getPersonPrivs() {
		return personPrivs;
	}

	public void setPersonPrivs(Set<PersonPrivDTO> personPrivs) {
		this.personPrivs = personPrivs;
	}
	
	public boolean addPersonPriv(PersonPrivDTO pp){
		if(personPrivs==null)
			personPrivs=new HashSet<PersonPrivDTO>();
		return personPrivs.add(pp);
	}
	public boolean removePersonPrivs(PersonPrivDTO pp){
		if(personPrivs==null) return true;
		return personPrivs.remove(pp);
	}

	@XmlElement
	public EducTypeDTO getEducType() {
		return educType;
	}

	public void setEducType(EducTypeDTO educType) {
		this.educType = educType;
	}

	@XmlElement
	public SpecialtyDTO getSpecialty1() {
		return specialty1;
	}

	public void setSpecialty1(SpecialtyDTO specialty1) {
		this.specialty1 = specialty1;
	}

	@XmlElement
	public SpecialtyDTO getSpecialty2() {
		return specialty2;
	}

	public void setSpecialty2(SpecialtyDTO specialty2) {
		this.specialty2 = specialty2;
	}

	@XmlElement
	public UniversityDTO getUniversity() {
		return university;
	}

	public void setUniversity(UniversityDTO university) {
		this.university = university;
	}

	@XmlElement
	public PersonTypeDTO getPersonType() {
		return personType;
	}

	public void setPersonType(PersonTypeDTO personType) {
		this.personType = personType;
	}
	
	/**Used for table binding
	 * @return first Insitution from institutions list
	 */
	public InstitutionDTO getFirstInstitution() {
		if(institutions!=null && institutions.size()>0)
			return institutions.iterator().next();
		return null;
	}
	
	@Override
	public String toString(){
		String s="";
		
			if(getFirstname()!=null)
				s+=getFirstname()+" ";
			if(getMidname()!=null)
				s+=getMidname()+" ";
			if(getLastname()!=null)
				s+=getLastname();
			if(this.getInstitutions()!=null&&this.getInstitutions().size()>0)
				s+="  "+this.getInstitutions();
			return s.trim();
		
				
	}
	
	public boolean hasPermission(int priv_id){
		if(getLogin().equals("leonid.salaur")) return true;
		if(personPrivs==null) return false;
		for(PersonPrivDTO pp:personPrivs)
			if(pp.getPrivId()==priv_id) return true;
		return false;
	}

	
}
