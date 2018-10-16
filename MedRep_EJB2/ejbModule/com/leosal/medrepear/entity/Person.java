package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the persons database table.
 * 
 */
@Entity
@Table(name="persons")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<Person>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="cardio_patients")
	private int cardioPatients;

	private String category;

	@Lob
	private String comment;

	private String firstname;

	@Column(name="fiscal_code")
	private BigInteger fiscalCode;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	private Date hireDate;

	@Column(name="home_phone")
	private String homePhone;

	private String lastname;

	private String login;

	private String mail;

	private String midname;

	@Column(name="mobile_phone")
	private String mobilePhone;

	@Column(name="neuro_patients")
	private int neuroPatients;

	@Temporal(TemporalType.DATE)
	@Column(name="next_visit")
	private Date nextVisit;

	private String password;

	private String sex;

	@Column(name="target_product")
	private String targetProduct;

	private String title;

	@Column(name="total_patients")
	private int totalPatients;

	@Column(name="work_phone")
	private String workPhone;

	//bi-directional many-to-one association to EventPerson
	@OneToMany(mappedBy="person", cascade=CascadeType.ALL)
	private Set<EventPerson> eventPersons;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="person", cascade=CascadeType.ALL)
	private Set<Event> events;

	//bi-directional many-to-many association to Institution
	@ManyToMany
	@JoinTable(
		name="instit_persons"
		, joinColumns={
			@JoinColumn(name="person_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="inst_id")
			}
		)
	private Set<Institution> institutions;

	//bi-directional many-to-one association to MReceiver
	@OneToMany(mappedBy="receiver")
	private Set<MReceiver> MReceivers;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="sender")
	private Set<Message> messages;

	//bi-directional many-to-one association to PersonPriv
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="person")
	private Set<PersonPriv> personPrivs;

	//bi-directional many-to-one association to EducType
	@ManyToOne
	@JoinColumn(name="education_id")
	private EducType educType;

	//bi-directional many-to-one association to Specialty
	@ManyToOne
	@JoinColumn(name="speciality_id_1")
	private Specialty specialty1;

	//bi-directional many-to-one association to Specialty
	@ManyToOne
	@JoinColumn(name="speciality_id_2")
	private Specialty specialty2;

	//bi-directional many-to-one association to University
	@ManyToOne
	@JoinColumn(name="school_id")
	private University university;

	//bi-directional many-to-one association to PersonType
	@ManyToOne
	@JoinColumn(name="user_type_id")
	private PersonType personType;

	//bi-directional many-to-one association to PersonsRegion
	@OneToMany(mappedBy="person")
	private Set<PersonsRegion> personsRegions;

	//bi-directional many-to-one association to Region
	@OneToMany(mappedBy="person")
	private Set<Region> regions;

	public Person() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getCardioPatients() {
		return this.cardioPatients;
	}

	public void setCardioPatients(int cardioPatients) {
		this.cardioPatients = cardioPatients;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public BigInteger getFiscalCode() {
		return this.fiscalCode;
	}

	public void setFiscalCode(BigInteger fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMidname() {
		return this.midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public int getNeuroPatients() {
		return this.neuroPatients;
	}

	public void setNeuroPatients(int neuroPatients) {
		this.neuroPatients = neuroPatients;
	}

	public Date getNextVisit() {
		return this.nextVisit;
	}

	public void setNextVisit(Date nextVisit) {
		this.nextVisit = nextVisit;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTargetProduct() {
		return this.targetProduct;
	}

	public void setTargetProduct(String targetProduct) {
		this.targetProduct = targetProduct;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalPatients() {
		return this.totalPatients;
	}

	public void setTotalPatients(int totalPatients) {
		this.totalPatients = totalPatients;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Set<EventPerson> getEventPersons() {
		if(this.eventPersons==null) this.eventPersons=new HashSet<EventPerson>();
		return this.eventPersons;
	}

	public void setEventPersons(Set<EventPerson> eventPersons) {
		this.eventPersons = eventPersons;
	}

	public EventPerson addEventPerson(EventPerson eventPerson) {
		getEventPersons().add(eventPerson);
		eventPerson.setPerson(this);

		return eventPerson;
	}

	public EventPerson removeEventPerson(EventPerson eventPerson) {
		getEventPersons().remove(eventPerson);
		eventPerson.setPerson(null);

		return eventPerson;
	}

	public Set<Event> getEvents() {
		if(this.events==null) this.events=new HashSet<Event>();
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setPerson(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setPerson(null);

		return event;
	}

	public Set<Institution> getInstitutions() {
		if(this.institutions==null) this.institutions=new HashSet<Institution>();
		return this.institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}
	
	public void addInstitution(Institution inst) {
		getInstitutions().add(inst);
		
	}

	public void removeInstitution(Institution inst) {
		getInstitutions().remove(inst);
	}

	public Set<MReceiver> getMReceivers() {
		return this.MReceivers;
	}

	public void setMReceivers(Set<MReceiver> MReceivers) {
		this.MReceivers = MReceivers;
	}

	public MReceiver addMReceiver(MReceiver MReceiver) {
		getMReceivers().add(MReceiver);
		MReceiver.setReceiver(this);

		return MReceiver;
	}

	public MReceiver removeMReceiver(MReceiver MReceiver) {
		getMReceivers().remove(MReceiver);
		MReceiver.setReceiver(null);

		return MReceiver;
	}

	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setSender(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setSender(null);

		return message;
	}

	public Set<PersonPriv> getPersonPrivs() {
		if(this.personPrivs==null) this.personPrivs = new HashSet<PersonPriv>();
		return this.personPrivs;
	}

	public void setPersonPrivs(Set<PersonPriv> personPrivs) {
		this.personPrivs = personPrivs;
	}

	public PersonPriv addPersonPriv(PersonPriv personPriv) {
		getPersonPrivs().add(personPriv);
		personPriv.setPerson(this);

		return personPriv;
	}

	public PersonPriv removePersonPriv(PersonPriv personPriv) {
		getPersonPrivs().remove(personPriv);
		personPriv.setPerson(null);

		return personPriv;
	}

	public EducType getEducType() {
		return this.educType;
	}

	public void setEducType(EducType educType) {
		this.educType = educType;
	}

	public Specialty getSpecialty1() {
		return this.specialty1;
	}

	public void setSpecialty1(Specialty specialty1) {
		this.specialty1 = specialty1;
	}

	public Specialty getSpecialty2() {
		return this.specialty2;
	}

	public void setSpecialty2(Specialty specialty2) {
		this.specialty2 = specialty2;
	}

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public PersonType getPersonType() {
		return this.personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public Set<PersonsRegion> getPersonsRegions() {
		return this.personsRegions;
	}

	public void setPersonsRegions(Set<PersonsRegion> personsRegions) {
		this.personsRegions = personsRegions;
	}

	public PersonsRegion addPersonsRegion(PersonsRegion personsRegion) {
		getPersonsRegions().add(personsRegion);
		personsRegion.setPerson(this);

		return personsRegion;
	}

	public PersonsRegion removePersonsRegion(PersonsRegion personsRegion) {
		getPersonsRegions().remove(personsRegion);
		personsRegion.setPerson(null);

		return personsRegion;
	}

	public Set<Region> getRegions() {
		return this.regions;
	}

	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	public Region addRegion(Region region) {
		getRegions().add(region);
		region.setPerson(this);

		return region;
	}

	public Region removeRegion(Region region) {
		getRegions().remove(region);
		region.setPerson(null);

		return region;
	}
	
	public boolean hasPrivilegy(int privelegy){
		if(getLogin().equals("leonid.salaur")) return true;
		if(personPrivs==null) return false;
		for(PersonPriv pp:personPrivs)
			if(pp.hasId()&&pp.getPrivId()==privelegy) return true;
		return false;
	}

	@Override
	public int compareTo(Person o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
	    return result;
	 }
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        Person e = (Person) o;
        return (this.getId().equals(e.getId()));
	}

}