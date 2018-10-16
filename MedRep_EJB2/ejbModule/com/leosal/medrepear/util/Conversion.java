package com.leosal.medrepear.util;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.leosal.medrepear.dto.AdvertDTO;
import com.leosal.medrepear.dto.DistribInstitDTO;
import com.leosal.medrepear.dto.DistribProdDTO;
import com.leosal.medrepear.dto.EducTypeDTO;
import com.leosal.medrepear.dto.EventDTO;
import com.leosal.medrepear.dto.EventGiftDTO;
import com.leosal.medrepear.dto.EventPersonDTO;
import com.leosal.medrepear.dto.EventProductDTO;
import com.leosal.medrepear.dto.EventTypeDTO;
import com.leosal.medrepear.dto.InstitTypeDTO;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.PersonPrivDTO;
import com.leosal.medrepear.dto.PersonTypeDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.dto.SaleDTO;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.dto.UniversityDTO;
import com.leosal.medrepear.eao.MedrepEAO;
import com.leosal.medrepear.entity.Advert;
import com.leosal.medrepear.entity.DistribInstit;
import com.leosal.medrepear.entity.DistribProd;
import com.leosal.medrepear.entity.EducType;
import com.leosal.medrepear.entity.Event;
import com.leosal.medrepear.entity.EventGift;
import com.leosal.medrepear.entity.EventPerson;
import com.leosal.medrepear.entity.EventProduct;
import com.leosal.medrepear.entity.EventType;
import com.leosal.medrepear.entity.InstitType;
import com.leosal.medrepear.entity.Institution;
import com.leosal.medrepear.entity.Person;
import com.leosal.medrepear.entity.PersonPriv;
import com.leosal.medrepear.entity.PersonType;
import com.leosal.medrepear.entity.Product;
import com.leosal.medrepear.entity.Region;
import com.leosal.medrepear.entity.Report;
import com.leosal.medrepear.entity.Sale;
import com.leosal.medrepear.entity.Specialty;
import com.leosal.medrepear.entity.University;

@Stateless
public class Conversion {
	@EJB MedrepEAO eao;
	
	private static Set<PersonPriv> personPrivs=null;
	private static Set<PersonPrivDTO> personPrivDTOs=null;
	
	public PersonDTO fromEntity(Person p){
		PersonDTO result = new PersonDTO();
		result.setAddress(p.getAddress());
		result.setBirthday(p.getBirthday());
		result.setCardioPatients(p.getCardioPatients());
		result.setCategory(p.getCategory());
		result.setComment(p.getComment());
		result.setFirstname(p.getFirstname());
		result.setFiscalCode(p.getFiscalCode());
		result.setHireDate(p.getHireDate());
		result.setHomePhone(p.getHomePhone());
		result.setId(p.getId());
		result.setLastname(p.getLastname());
		result.setLogin(p.getLogin());
		result.setMail(p.getMail());
		result.setMidname(p.getMidname());
		result.setMobilePhone(p.getMobilePhone());
		result.setNeuroPatients(p.getNeuroPatients());
		result.setNextVisit(p.getNextVisit());
		result.setPassword(p.getPassword());
		result.setSex(p.getSex());
		result.setTargetProduct(p.getTargetProduct());
		result.setTitle(p.getTitle());
		result.setTotalPatients(p.getTotalPatients());
		result.setWorkPhone(p.getWorkPhone());
		
		result.setEducType(fromEntity(p.getEducType()));
		result.setSpecialty1(fromEntity(p.getSpecialty1()));
		result.setSpecialty2(fromEntity(p.getSpecialty2()));
		result.setUniversity(fromEntity(p.getUniversity()));
		result.setPersonType(fromEntity(p.getPersonType()));
		Set<Institution> ins = p.getInstitutions();
		for(Institution in:ins)
			result.addInstitution(fromEntity(in));
		Set<PersonPriv> pps = p.getPersonPrivs();
		
		for(PersonPriv pp:pps)
			result.addPersonPriv(fromEntity(pp));
		
		return result;
	}
	
	public EducTypeDTO fromEntity(EducType e){
		if(e==null) return null;
		EducTypeDTO result = new EducTypeDTO();
		result.setId(e.getId());
		result.setType(e.getType());
		return result;
	}
	
	public InstitTypeDTO fromEntity(InstitType i){
		InstitTypeDTO result = new InstitTypeDTO();
		result.setId(i.getId());
		result.setName(i.getName());
		return result;
	}
	
	public InstitutionDTO fromEntity(Institution i){
		InstitutionDTO result = new InstitutionDTO();
		result.setActive(i.getActive()>0);
		result.setCode(i.getCode());
		result.setDistributor(i.getDistributor()>0);
		result.setId(i.getId());
		result.setInstitType(fromEntity(i.getInstitType()));
		result.setName(i.getName());
		result.setRegion(fromEntity(i.getRegion()));
		
		return result;
	}

	public RegionDTO fromEntity(Region r) {
		RegionDTO result = new RegionDTO();
		result.setId(r.getId());
		result.setName(r.getName());
		result.setRep_id(r.getPerson().getId());
		result.setRep_name(((r.getPerson().getFirstname()!=null?r.getPerson().getFirstname():"")+" "+
				(r.getPerson().getMidname()!=null?r.getPerson().getMidname():"")+" "+
				(r.getPerson().getLastname()!=null?r.getPerson().getLastname():"")).trim());
		return result;
	}
	
	public PersonPrivDTO fromEntity(PersonPriv pp){
		PersonPrivDTO result = new PersonPrivDTO();
		result.setId(pp.getId());
		result.setPrivId(pp.getPrivId());
		return result;
	}
	
	public PersonTypeDTO fromEntity(PersonType pt){
		PersonTypeDTO result = new PersonTypeDTO();
		result.setId(pt.getId());
		result.setName(pt.getName());
		return result;
	}
	
	public SpecialtyDTO fromEntity(Specialty sp){
		if(sp==null) return null;
		SpecialtyDTO result = new SpecialtyDTO();
		result.setId(sp.getId());
		result.setName(sp.getName());
		
		return result;
	}
	
	public UniversityDTO fromEntity(University un){
		if(un==null) return null;
		UniversityDTO result = new UniversityDTO();
		result.setId(un.getId());
		result.setType(un.getType());
		return result;
	}
	
	public EventDTO fromEntity(Event e){
		EventDTO res = new EventDTO();
		res.setComment(e.getComment());
		res.setDate(e.getDate());
		res.setEventType(fromEntity(e.getEventType()));
		for(EventGift eg:e.getEventGifts())
			res.addGift(generate(eg.getId(),res,fromEntity(eg.getAdvert()),eg.getQuantity()));
		res.setId(e.getId());
		res.setInitiator(fromEntity(e.getPerson()));
		res.setMessage(e.getMessage());
		for(EventPerson ep:e.getEventPersons())
			res.addParticipant(generate(ep.getId(),res,fromEntity(ep.getPerson()),ep.getComment()));
		for(EventProduct ep:e.getEventProducts())
			res.addProduct(generate(ep.getId(),res,fromEntity(ep.getProduct()),ep.getQuantity()));
		return res;
	}
	
	

	public ProductDTO fromEntity(Product p) {
		ProductDTO res = new ProductDTO();
		res.setBarCode(p.getBarCode());
		res.setId(p.getId());
		res.setName(p.getName());
		res.setPrice(p.getPrice());
		return res;
	}
	

	public AdvertDTO fromEntity(Advert p) {
		AdvertDTO res = new AdvertDTO();
		res.setBarCode(p.getBarCode());
		res.setId(p.getId());
		res.setName(p.getName());
		res.setPrice(p.getPrice());
		return res;
	}

	public EventTypeDTO fromEntity(EventType et) {
		EventTypeDTO res = new EventTypeDTO();
		res.setGroupEvent(et.getGroupEvent()>0);
		res.setId(et.getId());
		res.setName(et.getName());
		return res;
	}
	
	public SaleDTO fromEntity(Sale s){
		SaleDTO result = new SaleDTO();
		result.setId(s.getId());
		result.setInstitution(fromEntity(s.getInstitution()));
		result.setProduct(fromEntity(s.getProduct()));
		result.setQuant(s.getQuant());
		return result;
	}
	
	public ReportDTO fromEntity(Report r){
		ReportDTO result = new ReportDTO();
		result.setComment(r.getComment());
		result.setDistributor(fromEntity(r.getInstitution()));
		result.setId(r.getId());
		result.setPerEnd(r.getPerEnd());
		result.setPerStart(r.getPerStart());
		for(Sale s:r.getSales()){
			SaleDTO ss = fromEntity(s);
			result.addSale(ss);
		}
		return result;
	}
	
	
	
	public Region fromDTO(RegionDTO r) throws Exception{
		Region result = new Region();
		result.setId(r.getId());
		result.setName(r.getName());
		result.setPerson(eao.findOrFail(Person.class, r.getRep_id()));
		return result;
	}
	
	public Advert fromDTO(AdvertDTO a){
		Advert result = new Advert();
		result.setId(a.getId());
		result.setBarCode(a.getBarCode());
		result.setName(a.getName());
		result.setPrice(a.getPrice());
		return result;
	}
	
	public EducType fromDTO(EducTypeDTO et){
		if(et==null) return null; 
		EducType result= new EducType(); 
		result.setId(et.getId());
		result.setType(et.getType());
		return result;
	}
	public Event fromDTO(EventDTO ev){
		Event result = new Event();
		result.setComment(ev.getComment());
		result.setDate(ev.getDate());
		//result.setEventAdverts(eventAdverts);//not used (compatibility with old database version)
		System.out.println(ev.getGifts().size());
		if(ev.getGifts()!=null)
			for(EventGiftDTO ee:ev.getGifts()){
				if(ee==null){ 
					System.out.println("NULL event_gift");
					continue;
				}
				result.addEventGift(
						generate(ee, 
						result)
						);
			}
		for(EventPersonDTO ep:ev.getParticipants())
			result.addEventPerson(generate(ep,result));

		for(EventProductDTO ee:ev.getProducts())
			result.addEventProduct(generate(ee, result));
		result.setEventType(fromDTO(ev.getEventType()));//
		result.setId(ev.getId());
		result.setMessage(ev.getMessage());
		result.setPerson(fromDTO(ev.getInitiator()));//
		return result;
	}
	
	public Person fromDTO(PersonDTO p){
		Person result = new Person();
		result.setAddress(p.getAddress());
		result.setBirthday(p.getBirthday());
		result.setCardioPatients(p.getCardioPatients());
		result.setCategory(p.getCategory());
		result.setComment(p.getComment());
		result.setEducType(fromDTO(p.getEducType()));
		result.setFirstname(p.getFirstname());
		result.setFiscalCode(p.getFiscalCode());
		result.setHireDate(p.getHireDate());
		result.setHomePhone(p.getHomePhone());
		result.setId(p.getId());
		if(p.getInstitutions()!=null)
			for(InstitutionDTO i:p.getInstitutions())
				result.addInstitution(fromDTO(i));
		result.setLastname(p.getLastname());
		result.setLogin(p.getLogin());
		result.setMail(p.getMail());
		result.setMidname(p.getMidname());
		result.setMobilePhone(p.getMobilePhone());
		result.setNeuroPatients(p.getNeuroPatients());
		result.setNextVisit(p.getNextVisit());
		result.setPassword(p.getPassword());
		if(p.getPersonPrivs()!=null)
			for(PersonPrivDTO pp:p.getPersonPrivs())
				result.addPersonPriv(fromDTO(pp));
		result.setPersonType(fromDTO(p.getPersonType())); //
		result.setSex(p.getSex());
		result.setSpecialty1(fromDTO(p.getSpecialty1()));//
		result.setSpecialty2(fromDTO(p.getSpecialty2()));//
		result.setTargetProduct(p.getTargetProduct());
		result.setTitle(p.getTitle());
		result.setTotalPatients(p.getTotalPatients());
		result.setUniversity(fromDTO(p.getUniversity()));//
		result.setWorkPhone(p.getWorkPhone());
		
		return result;
	}
	
	public EventType fromDTO(EventTypeDTO et){
		EventType result=new EventType();
		result.setId(et.getId());
		result.setName(et.getName());
		return result;
	}
	
	public Institution fromDTO(InstitutionDTO inst){
		Institution result = new Institution();
		result.setActive((byte)(inst.isActive()?1:0));
		result.setCode(inst.getCode());
		result.setDistributor((byte)(inst.isDistributor()?1:0));
		result.setId(inst.getId());
		result.setInstitType(fromDTO(inst.getInstitType()));//
		result.setName(inst.getName());
		try {
			result.setRegion(fromDTO(inst.getRegion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public InstitType fromDTO(InstitTypeDTO it){
		InstitType result=new InstitType();
		result.setId(it.getId());
		result.setName(it.getName());
		return result;
	}
	
	public PersonPriv fromDTO(PersonPrivDTO pp){
		PersonPriv result = new PersonPriv();
		result.setId(pp.getId());
		result.setPrivId(pp.getPrivId());
		return result;
	}
	
	public PersonType fromDTO(PersonTypeDTO pt){
		if(pt==null) return null;
		
		PersonType result = new PersonType();
		result.setId(pt.getId());
		result.setName(pt.getName());
		return result;
	}
	
	public Product fromDTO(ProductDTO pr){
		Product result = new Product();
		result.setId(pr.getId());
		result.setBarCode(pr.getBarCode());
		result.setName(pr.getName());
		result.setPrice(pr.getPrice());
		return result;
	}
	
	
	
	public Specialty fromDTO(SpecialtyDTO sp){
		if(sp==null) return null;
		Specialty result = new Specialty();
		result.setId(sp.getId());
		result.setName(sp.getName());
		
		return result;
	}
	
	public University fromDTO(UniversityDTO un){
		if(un==null) return null;
		University result = new University();
		result.setId(un.getId());
		result.setType(un.getType());
		
		return result;
	}
	
	public EventGift generate(EventGiftDTO eg, Event ev){
		EventGift result = new EventGift();
		result.setId(eg.getId());
		result.setEvent(ev);
		result.setAdvert(fromDTO(eg.getAdvert()));
		result.setQuantity(eg.getQuantity());
		
		return result;
	}
	
	public EventProduct generate(EventProductDTO ep, Event ev){
		EventProduct result = new EventProduct();
		result.setId(ep.getId());
		result.setEvent(ev);
		result.setProduct(fromDTO(ep.getProduct()));
		result.setQuantity(ep.getQuantity());
		
		return result;
	}
	
	public EventPerson generate(EventPersonDTO ep, Event ev){
		EventPerson result = new EventPerson();
		result.setId(ep.getId());
		result.setEvent(ev);
		result.setPerson(fromDTO(ep.getPerson()));
		result.setComment(ep.getComment());
		return result;
	}
	
	public EventPersonDTO generate(int id, EventDTO ev, PersonDTO p, String comment){
		EventPersonDTO result = new EventPersonDTO();
		result.setId(id);
		result.setComment(comment);
		result.setEvent(ev);
		result.setPerson(p);
		return result;
	}
	
	public EventGiftDTO generate(int id, EventDTO ev, AdvertDTO adv, int quant){
		EventGiftDTO result = new EventGiftDTO();
		result.setAdvert(adv);
		result.setEvent(ev);
		result.setQuantity(quant);
		result.setId(id);
		return result;
	}
	public EventProductDTO generate(int id, EventDTO ev, ProductDTO adv, int quant){
		EventProductDTO result = new EventProductDTO();
		result.setProduct(adv);
		result.setEvent(ev);
		result.setQuantity(quant);
		result.setId(id);
		return result;
	}
	
	public Set<PersonPriv> getStandardPersonPrivs(Person pers){
		if(personPrivs==null){
			personPrivs = new HashSet<PersonPriv>();
			for(int p:PersonPrivDTO.STANDARD_USER_PRIVS){
				PersonPriv pp = new PersonPriv();
				pp.setPrivId(p);
				pp.setPerson(pers);
				personPrivs.add(pp);
			}
		}
		return personPrivs;
	}
	public Set<PersonPrivDTO> getStandardPersonPrivDTOs(){
		if(personPrivDTOs==null){
			personPrivDTOs = new HashSet<PersonPrivDTO>();
			for(int p:PersonPrivDTO.STANDARD_USER_PRIVS){
				PersonPrivDTO pp = new PersonPrivDTO();
				pp.setPrivId(p);
				personPrivDTOs.add(pp);
			}
		}
		return personPrivDTOs;
	}

	public DistribInstitDTO fromEntity(
			DistribInstit entity) {
		if(entity==null) return null;
		DistribInstitDTO result = new DistribInstitDTO();
		result.setDistribCod(entity.getDistribCod());
		result.setDistributor(this.fromEntity(entity.getInstitution1()));
		result.setId(entity.getId());
		result.setInstitName(entity.getInstitName());
		result.setInstitution(this.fromEntity(entity.getInstitution2()));
		
		return result;
	}
	public DistribProdDTO fromEntity(
			DistribProd entity) {
		if(entity==null) return null;
		DistribProdDTO result = new DistribProdDTO();
		result.setDistribCod(entity.getDistribCod());
		result.setDistributor(this.fromEntity(entity.getInstitution()));
		result.setId(entity.getId());
		result.setProdName(entity.getProdName());
		result.setProduct(this.fromEntity(entity.getProduct()));
		
		return result;
	}

	public DistribInstit fromDTO(DistribInstitDTO dto) {
		if(dto==null) return null;
		DistribInstit result = new DistribInstit();
		result.setDistribCod(dto.getDistribCod());
		result.setInstitution1(this.fromDTO(dto.getDistributor()));
		result.setId(dto.getId());
		result.setInstitName(dto.getInstitName());
		result.setInstitution2(this.fromDTO(dto.getInstitution()));
		
		return result;
	}

	public DistribProd fromDTO(DistribProdDTO dto) {
		if(dto==null) return null;
		DistribProd result = new DistribProd();
		result.setDistribCod(dto.getDistribCod());
		result.setInstitution(this.fromDTO(dto.getDistributor()));
		result.setId(dto.getId());
		result.setProdName(dto.getProdName());
		result.setProduct(this.fromDTO(dto.getProduct()));
		
		return result;
	}

	public Report fromDTO(ReportDTO dto) {
		if(dto==null) return null;
		Report result = new Report();
		result.setComment(dto.getComment());
		result.setId(dto.getId());
		result.setInstitution(fromDTO(dto.getDistributor()));
		result.setPerEnd(dto.getPerEnd());
		result.setPerStart(dto.getPerStart());
		for(SaleDTO sdto:dto.getSales()){
			Sale sres = new Sale();
			sres.setId(sdto.getId());
			sres.setInstitution(fromDTO(sdto.getInstitution()));
			sres.setProduct(fromDTO(sdto.getProduct()));
			sres.setQuant(sdto.getQuant());
			result.addSale(sres);
		}
		return result;
	}
	
	
	

}
