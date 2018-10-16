package com.leosal.medrepear.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
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
import com.leosal.medrepear.entity.Specialty;
import com.leosal.medrepear.entity.University;
import com.leosal.medrepear.util.Conversion;

/**
 * Session Bean implementation class MedrepSessionBean
 */

@Stateless//(mappedName="ejb/MedrepBean")
@EJB(name="ejb/MedrepBean",beanInterface=MedrepSessionBeanRemote.class)
public class MedrepSessionBean implements MedrepSessionBeanRemote {
	@EJB MedrepEAO eao;
	@EJB Conversion conv;
		
	//private int userId;
	//private Person user;
	private static Logger logger=Logger.getLogger("SessionBean");
	private final Map<Person, String> authTokensStorage = new HashMap<>();

    /**
     * Default constructor. 
     */
    public MedrepSessionBean() {
    	
    	
    }
    //======================================= Extracting data from database =====================================

	@Override
	public long totalContacts(String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		long count = eao.countPersons();
		logger.log(Level.INFO,"Total contacts requested from user "+user.getLogin()+": "+count);
		return count;
	}

	@Override
	public List<PersonDTO> getContacts(String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<PersonDTO> result = new ArrayList<PersonDTO>();
		if(user.hasPermission(PersonPrivDTO.READ_ALL_CONTACTS)){
			List<Person> list = eao.getContacts();
			for(Person p:list)
				result.add(conv.fromEntity(p));
			logger.log(Level.INFO,"All contacts list requested from user "+user.getLogin()+": "+result.size());
		}else{
			/*
			 
			List<Region> regs = eao.getRegionsByRep(user.getId());
			
			for(Region r:regs)
				for(Institution i:r.getInstitutions())
					for(Person p:i.getPersons())
						result.add(conv.fromEntity(p));
			*/
			List<Person> list = eao.getContactsByRep(user.getId());
			for(Person p:list)
				result.add(conv.fromEntity(p));
			Collections.sort(result, new Comparator<PersonDTO>() {

				@Override
				public int compare(PersonDTO o1,
						PersonDTO o2) {
					//Auto-generated method stub
					return (o1.getFirstname()+o1.getMidname()+o1.getLastname()).toLowerCase()
							.compareTo(
									(o2.getFirstname()+o2.getMidname()+o2.getLastname())
									.toLowerCase()
									);
				}
			});
			logger.log(Level.INFO,"Personal contacts list requested from user "+user.getLogin()+": "+result.size());
		}
				
		return result;
	}

	@Override
	public PersonDTO getContactById(int id, String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		Person p = eao.getContactById(id);
		//logger.log(Level.INFO,"Contact by id request from user "+user.getLogin());
		return conv.fromEntity(p);
	}

	@Override
	public List<PersonDTO> getRepresentatives(String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token); 
		if(user==null) throw new UserNotAuthorisedException();
		//if(!user.hasPermission(PersonPrivDTO.READ_ALL_CONTACTS)) return null;
		List<Person> allPersons = eao.getRepresentatives();
		List<PersonDTO> result = new ArrayList<PersonDTO>();
		for(Person p:allPersons)
			result.add(conv.fromEntity(p));
		logger.log(Level.INFO,"Representatives list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	@Override
	public List<RegionDTO>  getAllRegions(String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		if(!user.hasPermission(PersonPrivDTO.READ_ALL_REGIONS)) return getRegions(token);
		List<Region> regions;
		regions = eao.getAllRegions();
		
		List<RegionDTO> result = new ArrayList<>();
		for(Region r:regions)
			result.add(conv.fromEntity(r));
		logger.log(Level.INFO,"All regions list requested from user "+user.getLogin()+": "+result.size());
		return result ;
	}
	
	@Override
	public List<RegionDTO> getRegions(String token) throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		if(user.hasPermission(PersonPrivDTO.READ_ALL_REGIONS)) return getAllRegions(token);
		List<Region> regions;
		int id = user.getId();
		regions = eao.getRegionsByRep(id);
		List<RegionDTO> result = new ArrayList<>();
		for(Region r:regions)
			result.add(conv.fromEntity(r));
		logger.log(Level.INFO,"Personal regions list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	@Override
	public List<EventDTO> getEvents(Date from, Date to,String token)  throws UserNotAuthorisedException{
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		int id = user.getId();
		if(user.hasPermission(PersonPrivDTO.READ_ALL_EVENTS))
			id=0; //all events will be extracted
		List<Event> events = eao.getEvents(from, to, id);
		List<EventDTO> result = new ArrayList<>();
		for(Event e:events)
			result.add(conv.fromEntity(e));
		logger.log(Level.INFO,"Activities list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	

	

	@Override
	public List<EventTypeDTO> getEventTypes(String token) throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<EventType> list = eao.getEventTypes();
		List<EventTypeDTO> result = new ArrayList<>();
		for(EventType et:list){
			EventTypeDTO edto = conv.fromEntity(et) ;
			result.add(edto);
			//logger.log(Level.INFO,edto.getName());
		}
		logger.log(Level.INFO,"Event types list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	@Override
	public List<AdvertDTO> getAdverts(String token) throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<Advert> list = eao.getAdverts();
		List<AdvertDTO> result = new ArrayList<>();
		for(Advert et:list)
			result.add(conv.fromEntity(et));
		logger.log(Level.INFO,"Adverts list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	@Override
	public List<ProductDTO> getProducts(String token) throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<Product> list = eao.getProducts();
		List<ProductDTO> result = new ArrayList<>();
		for(Product et:list)
			result.add(conv.fromEntity(et));
		logger.log(Level.INFO,"Products list requested from user "+user.getLogin()+": "+result.size());
		return result;

	}

	@Override
	public List<InstitutionDTO> getInstitutions(String token)
			throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<InstitutionDTO> result = new ArrayList<>();
		List<Institution> list;
		if(user.hasPermission(PersonPrivDTO.READ_ALL_REGIONS))
			list=eao.getInstitutions();
		else
			list = eao.getInstitutionsByRep(user.getId());
		
		for(Institution i:list)
				result.add(conv.fromEntity(i));
		logger.log(Level.INFO,"Institutions list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}

	@Override
	public List<InstitTypeDTO> getInstitutionTypes(String token)
			throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<InstitType> list = eao.getInstitutionTypes();
		List<InstitTypeDTO> result = new ArrayList<>();
		for(InstitType et:list)
			result.add(conv.fromEntity(et));
		logger.log(Level.INFO,"Institution types list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}
	@Override
	public List<SpecialtyDTO> getSpecialties(String token)
			throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		List<Specialty> list = eao.getSpecialties();
		List<SpecialtyDTO> result = new ArrayList<>();
		for(Specialty et:list)
			result.add(conv.fromEntity(et));
		logger.log(Level.INFO,"Specialties list requested from user "+user.getLogin()+": "+result.size());
		return result;
	}
	
	
	@Override
	public String login(String username, String password) throws UserNotAuthorisedException{
		//String authKey = username+password;
		Person p = eao.getUser(username, password);
		if(p==null) throw new UserNotAuthorisedException();
		if(p.getPersonPrivs()==null || p.getPersonPrivs().isEmpty()){
			p.setPersonPrivs(conv.getStandardPersonPrivs(p));
			//for(PersonPriv pp:p.getPersonPrivs())
				eao.persist(p);
		}//else
			//System.out.println(p.getPersonPrivs()) ;
		if(authTokensStorage .containsKey(p)) return authTokensStorage.get(p);
		String authToken = UUID.randomUUID().toString();
		authTokensStorage.put(p, authToken);
		logger = Logger.getLogger(this.getClass().getName()) ;
		return authToken;
	}
	
	//============================= Storing data in database ========================
	
	@Override
	public List<RegionDTO> storeRegions(List<RegionDTO> list, String token)
			throws UserNotAuthorisedException {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		if(list==null)return null;
		for(RegionDTO r:list){
			try{
				Region reg = conv.fromDTO(r);
				eao.persist(reg);
				r.setId(reg.getId());
				//r.setRep_id(reg.getPerson().getId());
				//r.setRep_name(reg.getPerson().getLogin() );
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public <T> List<T> storeDTOs(List<T> list, String token)
			throws Exception {
		PersonDTO user = isAuthorized(token);
		if(user==null) throw new UserNotAuthorisedException();
		if(list==null) {
			logger.warning("Storing List<> requested from user "+user.getLogin()+": null list");
			return null;
		}
		
		for(T ob:list){
			
			if(ob.getClass().equals(EventDTO.class)){
				//Save Event
				EventDTO dto =(EventDTO)ob;
				Event ev = conv.fromDTO(dto);
				eao.persist(ev);
				dto.setId(ev.getId());
				dto.setGifts(new ArrayList<EventGiftDTO>());
				for(EventGift ea:ev.getEventGifts()){
					eao.persist(ea);
					dto.addGift(conv.generate(ea.getId(), 
							dto, 
							conv.fromEntity(ea.getAdvert()), 
							ea.getQuantity()));
				}
				dto.setProducts(new ArrayList<EventProductDTO>());
				for(EventProduct ep:ev.getEventProducts()){
					eao.persist(ep);
					dto.addProduct(conv.generate(ep.getId(), 
							dto, 
							conv.fromEntity(ep.getProduct()), 
							ep.getQuantity()));
				}
				dto.setParticipants(new ArrayList<EventPersonDTO>());
				for(EventPerson ep:ev.getEventPersons()){
					eao.persist(ep);
					dto.addParticipant(conv.generate(ep.getId(), 
							dto, 
							conv.fromEntity(ep.getPerson()),
							ep.getComment()));
				}
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
				//System.out.println("Event saved");
				
			}else if(ob.getClass().equals(RegionDTO.class)){
				RegionDTO r = (RegionDTO)ob; 
				Region reg = conv.fromDTO(r);
				eao.persist(reg);
				r.setId(reg.getId());
				r.setRep_name(reg.getPerson().toString() );
				if(r.getId()==null)
					logger.warning(r.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(AdvertDTO.class)){
				AdvertDTO dto = (AdvertDTO) ob;
				Advert entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(EducTypeDTO.class)){
				EducTypeDTO dto = (EducTypeDTO) ob;
				EducType entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(EventTypeDTO.class)){
				EventTypeDTO dto = (EventTypeDTO) ob;
				EventType entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(InstitTypeDTO.class)){
				InstitTypeDTO dto = (InstitTypeDTO) ob;
				InstitType entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(InstitutionDTO.class)){
				InstitutionDTO dto = (InstitutionDTO) ob;
				Institution entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(PersonDTO.class)){
				PersonDTO dto = (PersonDTO) ob;
				Person entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(PersonPrivDTO.class)){
				PersonPrivDTO dto = (PersonPrivDTO) ob;
				PersonPriv entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(PersonTypeDTO.class)){
				PersonTypeDTO dto = (PersonTypeDTO) ob;
				PersonType entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(ProductDTO.class)){
				ProductDTO dto = (ProductDTO) ob;
				Product entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(SpecialtyDTO.class)){
				SpecialtyDTO dto = (SpecialtyDTO) ob;
				Specialty entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(UniversityDTO.class)){
				UniversityDTO dto = (UniversityDTO) ob;
				University entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(DistribInstitDTO.class)){
				DistribInstitDTO dto = (DistribInstitDTO) ob;
				DistribInstit entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(DistribProdDTO.class)){
				DistribProdDTO dto = (DistribProdDTO) ob;
				DistribProd entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else if(ob.getClass().equals(ReportDTO.class)){
				ReportDTO dto = (ReportDTO) ob;
				Report entity = conv.fromDTO(dto);
				eao.persist(entity);
				dto.setId(entity.getId());
				if(dto.getId()==null)
					logger.warning(dto.toString()+" has null ID after saving");
			}else{
				logger.warning(ob.getClass()+" is not defined as entity available to store");
				break;
			}
			
				
			
			
		}
		logger.log(Level.INFO,"Stored List<"+list.get(0).getClass().getSimpleName()+"> requested from user "+user.getLogin()+": "+list.size());
		return  list;
	}
	
	@Override
	public <T> int removeFromDatabase(List<T> list, String token)
			throws Exception {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		
		if(list==null)return 0;
		int counter=0;
		for(T ob:list){
			if(ob.getClass().equals(EventDTO.class)){
				EventDTO dto =(EventDTO)ob;
				Event ev = conv.fromDTO(dto);
				if(eao.removeEntity(ev)) counter++;
			}else if(ob.getClass().equals(RegionDTO.class)){
				RegionDTO r = (RegionDTO)ob;
				Region reg = conv.fromDTO(r);
				if(eao.removeEntity(reg)) counter++;
			}else if(ob.getClass().equals(AdvertDTO.class)){
				AdvertDTO dto = (AdvertDTO) ob;
				Advert entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(EducTypeDTO.class)){
				EducTypeDTO dto = (EducTypeDTO) ob;
				EducType entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(EventTypeDTO.class)){
				EventTypeDTO dto = (EventTypeDTO) ob;
				EventType entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(InstitTypeDTO.class)){
				InstitTypeDTO dto = (InstitTypeDTO) ob;
				InstitType entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(InstitutionDTO.class)){
				InstitutionDTO dto = (InstitutionDTO) ob;
				Institution entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(PersonDTO.class)){
				PersonDTO dto = (PersonDTO) ob;
				Person entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(PersonPrivDTO.class)){
				PersonPrivDTO dto = (PersonPrivDTO) ob;
				PersonPriv entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(PersonTypeDTO.class)){
				PersonTypeDTO dto = (PersonTypeDTO) ob;
				PersonType entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(ProductDTO.class)){
				ProductDTO dto = (ProductDTO) ob;
				Product entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(SpecialtyDTO.class)){
				SpecialtyDTO dto = (SpecialtyDTO) ob;
				Specialty entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(UniversityDTO.class)){
				UniversityDTO dto = (UniversityDTO) ob;
				University entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(DistribInstitDTO.class)){
				DistribInstitDTO dto = (DistribInstitDTO) ob;
				DistribInstit entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(DistribProdDTO.class)){
				DistribProdDTO dto = (DistribProdDTO) ob;
				DistribProd entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else if(ob.getClass().equals(ReportDTO.class)){
				ReportDTO dto = (ReportDTO) ob;
				Report entity = conv.fromDTO(dto);
				if(eao.removeEntity(entity)) counter++;
			}else{
				logger.warning(ob.getClass()+" is not defined as entity available to delete");
				break;
			}
		}
		return  counter;
		 
	}
	
	@Override
	public PersonDTO identifyAuthTokenValid(String authToken){
		
		for(Entry<Person, String> e:authTokensStorage.entrySet()){
			if(e.getValue().equals(authToken)) {
				logger.log(Level.INFO,authToken+" :: identified for user "+e.getKey().getLogin());
				
				return conv.fromEntity(e.getKey());
			}
		}
		logger.log(Level.INFO,authToken+" :: not identified");
		return null;
	}
	@Override
	public PersonDTO isAuthorized(String token) {
		return identifyAuthTokenValid(token);
	}
	
	@Override
	public boolean authorize(String user, String password)  throws UserNotAuthorisedException{
		Person p = eao.getUser(user, password);
		
		if(p==null) throw new UserNotAuthorisedException();
		//logger = Logger.getLogger(user+" Session:");
		return true;
		
	}

	@Override
	public List<InstitutionDTO> getDistributors(String token) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		List<Institution> list = eao.getDistributors();
		if(list==null || list.size()==0)
			return null;
		List<InstitutionDTO> result = new ArrayList<InstitutionDTO>();
		for(Institution in:list)
			result.add(conv.fromEntity(in));
		logger.info(user.toString()+" requested Distributor list ["+list.size()+" entities returned]");
		return result;
	}

	@Override
	public List<ReportDTO> getReports(String token, Date dFrom, Date dTo) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		List<Report> list = eao.getReports(dFrom,dTo);
		if(list==null ||list.size()==0)
			return null;
		List<ReportDTO> result = new ArrayList<ReportDTO>();
		for(Report re:list)
			result.add(conv.fromEntity(re));
		logger.info(user.toString()+" requested Reports list ["+list.size()+" entities returned]");
		return result;
	}

	@Override
	public ProductDTO getProduct(String token, int prId) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		ProductDTO result = conv.fromEntity(eao.getProduct(prId));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}

	@Override
	public InstitutionDTO getInstitution(String token, int inId) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		InstitutionDTO result = conv.fromEntity(eao.getInstitution(inId));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}

	@Override
	public DistribInstitDTO getDistributorInstitutionByName(String token,
			InstitutionDTO distr, String name) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		
		DistribInstitDTO result = conv.fromEntity(eao.getDistributorInstitutionByName(distr,name));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}

	@Override
	public DistribInstitDTO getDistributorInstitutionByCode(String token,
			InstitutionDTO distr, String code) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		// 
		DistribInstitDTO result = conv.fromEntity(eao.getDistributorInstitutionByCode(distr,code));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}

	@Override
	public DistribProdDTO getDistributorProductByName(String token,
			InstitutionDTO distr, String name) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		// 
		DistribProdDTO result = conv.fromEntity(eao.getDistributorProductByName(distr,name));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}

	@Override
	public DistribProdDTO getDistributorProductByCode(String token,
			InstitutionDTO distr, String code) throws UserNotAuthorisedException {
		PersonDTO user =  isAuthorized(token);
		if(user==null) throw new  UserNotAuthorisedException();
		// 
		DistribProdDTO result = conv.fromEntity(eao.getDistributorProductByCode(distr,code));
		logger.info(user.toString()+" requested "+result+" information");
		return result;
	}
	
	
	

	

	

	

	

	

	

}
