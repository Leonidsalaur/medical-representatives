package com.leosal.medrepear.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.medrepear.dto.AdvertDTO;
import com.leosal.medrepear.dto.DistribInstitDTO;
import com.leosal.medrepear.dto.DistribProdDTO;
import com.leosal.medrepear.dto.EducTypeDTO;
import com.leosal.medrepear.dto.EventDTO;
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
import com.leosal.medrepear.dtolist.AdvertListDTO;
import com.leosal.medrepear.dtolist.EducTypeListDTO;
import com.leosal.medrepear.dtolist.EventTypesListDTO;
import com.leosal.medrepear.dtolist.EventsListDTO;
import com.leosal.medrepear.dtolist.InstitutionTypesListDTO;
import com.leosal.medrepear.dtolist.InstitutionsListDTO;
import com.leosal.medrepear.dtolist.PersonPrivListDTO;
import com.leosal.medrepear.dtolist.PersonTypeListDTO;
import com.leosal.medrepear.dtolist.PersonsListDTO;
import com.leosal.medrepear.dtolist.ProductListDTO;
import com.leosal.medrepear.dtolist.RegionsListDTO;
import com.leosal.medrepear.dtolist.ReportListDTO;
import com.leosal.medrepear.dtolist.SpecialtiesListDTO;


public class RestManager {
	public static final boolean DISPLAY_REST_LOGS = false;
	private static RestManager manager = null;
	
	private static final int MAX_ATTEMPTS = 3;
	private int CONNECTIONS_ATTEMPTS = 0;
	
	private static final Logger log = Logger.getLogger(RestManager.class.getName());
	private static final Preferences prefs = Preferences.userNodeForPackage(com.leosal.medrepear.service.RestManager.class);
	private static final String PASSWORD = "PASSWORD";
	private static final String USER = "USER";
	private String token;
	private Set<PersonPrivDTO> permissions;
	private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	
	private static final String HOST = "http://localhost:8080/Medrep2Web/rest/bean/";
	
	private RestManager(){
		
	}
	
	public static RestManager getInstance(){
		if(manager==null)
			manager=new RestManager();
		
		return manager;
	}
	
	public void login(String user, String password) throws UserNotAuthorisedException{
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		if(user==null&&password==null){
			user = prefs.get(USER,null);
			password = prefs.get(PASSWORD, null);
		}
		if(user==null&&password==null){
			token=null;
			throw new UserNotAuthorisedException("NULL user and password");
		}
		
		WebTarget target = client.target(HOST+"login/"+user+"/"+password);
		
		Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			throw new ServerErrorException(response);
		}
		token = response.readEntity(String.class);
		
		prefs.put(USER, user);
		prefs.put(PASSWORD, password);
		
		
		
	}
	public String getLastUser(){
		return prefs.get(USER, "");
	}
	public String getLastPassword(){
		return prefs.get(PASSWORD, "");
	}
	public boolean hasPermission(int priv_id){
		if(permissions==null)
			permissions=currentUser().getPersonPrivs();
		for(PersonPrivDTO pp:permissions){
			if(pp.getPrivId()==priv_id) return true;
		}
		return false;
	}
	
	public String simpleAdd(int a, int b){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		WebTarget target = client.target(HOST+"add/"+a+"/"+b);
		
		Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=200){
			//throw new ServerErrorException(response);
		}
		String result = response.readEntity(String.class);
		
		return result;
	}
	public PersonDTO currentUser(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.CHECK_TOKEN);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return currentUser();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return currentUser();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		PersonDTO result = response.readEntity(PersonDTO.class);
		
		return result;
	}
	
	public List<PersonDTO> getRepresentatives(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_REPRESENTATIVES);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getRepresentatives();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getRepresentatives();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		PersonsListDTO result = response.readEntity(PersonsListDTO.class);
		
		return result.getList();
	}

	public List<PersonDTO> getContacts(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_CONTACTS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getContacts();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getContacts();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		PersonsListDTO result = response.readEntity(PersonsListDTO.class);
		
		return result.getList();
	}
	
	public PersonDTO getContactById(int id){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_CONTACT_BY_ID+"/"+id);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getContactById(id);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getContactById(id);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		PersonDTO result = response.readEntity(PersonDTO.class);

		return result;
	}
	
	public List<RegionDTO> getRegions() {
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_REGIONS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getRegions();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getRegions();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		RegionsListDTO result = response.readEntity(RegionsListDTO.class);

		return result.getList();
	}
	
	public List<EventDTO> getEvents(Date from, Date to) {
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		String cFrom;
		if(from==null)
			cFrom="2015-01-01";
		else
			cFrom = format.format(from);
		String cTo;
		if(to==null)
			cTo = format.format(new Date());
		else
			cTo = format.format(to);
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_EVENTS+"/"+cFrom+"/"+cTo);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getEvents(from, to);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getEvents(from, to);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		EventsListDTO result = response.readEntity(EventsListDTO.class);
		
		return result.getList();
		
	}
	
	public List<EventTypeDTO> getEventTypes() {
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_EVENT_TYPES);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getEventTypes();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getEventTypes();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		EventTypesListDTO result = response.readEntity(EventTypesListDTO.class);
		
		return result.getList();
	}

	public List<AdvertDTO> getAdverts(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_ADVERTS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getAdverts();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getAdverts();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		AdvertListDTO result = response.readEntity(AdvertListDTO.class);
		
		return result.getList();
	}

	public List<ProductDTO> getProducts(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_PRODUCTS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getProducts();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getProducts();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		ProductListDTO result = response.readEntity(ProductListDTO.class);
		
		return result.getList();
	}

	public List<InstitutionDTO> getInstitutions(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_INSTITUTIONS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getInstitutions();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getInstitutions();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		InstitutionsListDTO result = response.readEntity(InstitutionsListDTO.class);
		
		return result.getList();
	}
	
	public List<InstitTypeDTO> getInstitutionTypes(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_INSTITUTION_TYPES);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getInstitutionTypes();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getInstitutionTypes();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		InstitutionTypesListDTO result = response.readEntity(InstitutionTypesListDTO.class);
		
		return result.getList();
	}
	
	public List<SpecialtyDTO> getSpecialties(){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_SPECIALTIES);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getSpecialties();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getSpecialties();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		SpecialtiesListDTO result = response.readEntity(SpecialtiesListDTO.class);

		return result.getList();
	}
	public <T> T saveObject(T ob){
		ArrayList<T> list = new ArrayList<T>();
		list.add(ob);
		List<T> list2 = this.saveToDatabase(list);
		if(list2==null) return null;
		if(list2.size()==0) return null;
		return list2.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> saveToDatabase(List<T> list){
		if(list==null || list.size()==0)
			return null;
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		Class<? extends Object> obClass = list.get(0).getClass();
		List<T> result = null;
		
		if(obClass.equals(EventDTO.class)){
			List<EventDTO> resList = (List<EventDTO>) list;
			EventsListDTO entity = new EventsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_EVENTS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(EventsListDTO.class).getList();
		}else if(obClass.equals(RegionDTO.class)){
			List<RegionDTO> resList = (List<RegionDTO>) list;
			RegionsListDTO entity = new RegionsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_REGIONS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(RegionsListDTO.class).getList();
		}else if(obClass.equals(AdvertDTO.class)){
			List<AdvertDTO> resList = (List<AdvertDTO>) list;
			AdvertListDTO entity = new AdvertListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_ADVERTS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(AdvertListDTO.class).getList();
		}else if(obClass.equals(EducTypeDTO.class)){
			List<EducTypeDTO> resList = (List<EducTypeDTO>) list;
			EducTypeListDTO entity = new EducTypeListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_EDUC_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(EducTypeListDTO.class).getList();
		}else if(obClass.equals(EventTypeDTO.class)){
			List<EventTypeDTO> resList = (List<EventTypeDTO>) list;
			EventTypesListDTO entity = new EventTypesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_EVENT_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(EventTypesListDTO.class).getList();
		}else if(obClass.equals(InstitTypeDTO.class)){
			List<InstitTypeDTO> resList = (List<InstitTypeDTO>) list;
			InstitutionTypesListDTO entity = new InstitutionTypesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_INSTITUTION_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(InstitutionTypesListDTO.class).getList();
		}else if(obClass.equals(InstitutionDTO.class)){
			List<InstitutionDTO> resList = (List<InstitutionDTO>) list;
			InstitutionsListDTO entity = new InstitutionsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_INSTITUTIONS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(InstitutionsListDTO.class).getList();
		}else if(obClass.equals(PersonDTO.class)){
			List<PersonDTO> resList = (List<PersonDTO>) list;
			PersonsListDTO entity = new PersonsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_PERSONS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(PersonsListDTO.class).getList();
		}else if(obClass.equals(PersonPrivDTO.class)){
			List<PersonPrivDTO> resList = (List<PersonPrivDTO>) list;
			PersonPrivListDTO entity = new PersonPrivListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_PERSON_PRIVS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(PersonPrivListDTO.class).getList();
		}else if(obClass.equals(PersonTypeDTO.class)){
			List<PersonTypeDTO> resList = (List<PersonTypeDTO>) list;
			PersonTypeListDTO entity = new PersonTypeListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_PERSON_TYPE);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(PersonTypeListDTO.class).getList();
		}else if(obClass.equals(ProductDTO.class)){
			List<ProductDTO> resList = (List<ProductDTO>) list;
			ProductListDTO entity = new ProductListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_PRODUCTS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(ProductListDTO.class).getList();
		}else if(obClass.equals(SpecialtyDTO.class)){
			List<SpecialtyDTO> resList = (List<SpecialtyDTO>) list;
			SpecialtiesListDTO entity = new SpecialtiesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_SPECIALTIES);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(SpecialtiesListDTO.class).getList();
		}else if(obClass.equals(UniversityDTO.class)){
			log.warning("Saving is not yet implemented for: "+obClass);
		}else if(obClass.equals(DistribInstitDTO.class)){
			log.warning("Saving is not yet implemented for: "+obClass);
		}else if(obClass.equals(DistribProdDTO.class)){
			log.warning("Saving is not yet implemented for: "+obClass);
		}else if(obClass.equals(ReportDTO.class)){
			List<ReportDTO> resList = (List<ReportDTO>) list;
			ReportListDTO entity = new ReportListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_STORE_REPORTS);
			Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = (List<T>) response.readEntity(ReportListDTO.class).getList();
		}else{
			log.warning(obClass+" is not defined as entity available to store");
		}
	
		return result;
	}
	
	public <T> int removeFromDatabase(List<T> list){
		int result=-1;
		if(list==null || list.size()==0)
			return result;
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		Class<? extends Object> obClass = list.get(0).getClass();

		
		if(obClass.equals(EventDTO.class)){
			@SuppressWarnings("unchecked")
			List<EventDTO> resList = (List<EventDTO>) list;
			EventsListDTO entity = new EventsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_EVENTS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(RegionDTO.class)){
			@SuppressWarnings("unchecked")
			List<RegionDTO> resList = (List<RegionDTO>) list;
			RegionsListDTO entity = new RegionsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_REGIONS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(AdvertDTO.class)){
			@SuppressWarnings("unchecked")
			List<AdvertDTO> resList = (List<AdvertDTO>) list;
			AdvertListDTO entity = new AdvertListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_ADVERTS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(EducTypeDTO.class)){
			@SuppressWarnings("unchecked")
			List<EducTypeDTO> resList = (List<EducTypeDTO>) list;
			EducTypeListDTO entity = new EducTypeListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_EDUC_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(EventTypeDTO.class)){
			@SuppressWarnings("unchecked")
			List<EventTypeDTO> resList = (List<EventTypeDTO>) list;
			EventTypesListDTO entity = new EventTypesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_EVENT_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(InstitTypeDTO.class)){
			@SuppressWarnings("unchecked")
			List<InstitTypeDTO> resList = (List<InstitTypeDTO>) list;
			InstitutionTypesListDTO entity = new InstitutionTypesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_INSTITUTION_TYPES);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(InstitutionDTO.class)){
			@SuppressWarnings("unchecked")
			List<InstitutionDTO> resList = (List<InstitutionDTO>) list;
			InstitutionsListDTO entity = new InstitutionsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_INSTITUTIONS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(PersonDTO.class)){
			@SuppressWarnings("unchecked")
			List<PersonDTO> resList = (List<PersonDTO>) list;
			PersonsListDTO entity = new PersonsListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_PERSONS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(PersonPrivDTO.class)){
			@SuppressWarnings("unchecked")
			List<PersonPrivDTO> resList = (List<PersonPrivDTO>) list;
			PersonPrivListDTO entity = new PersonPrivListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_PERSON_PRIVS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(PersonTypeDTO.class)){
			@SuppressWarnings("unchecked")
			List<PersonTypeDTO> resList = (List<PersonTypeDTO>) list;
			PersonTypeListDTO entity = new PersonTypeListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_PERSON_TYPE);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(ProductDTO.class)){
			@SuppressWarnings("unchecked")
			List<ProductDTO> resList = (List<ProductDTO>) list;
			ProductListDTO entity = new ProductListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_PRODUCTS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(SpecialtyDTO.class)){
			@SuppressWarnings("unchecked")
			List<SpecialtyDTO> resList = (List<SpecialtyDTO>) list;
			SpecialtiesListDTO entity = new SpecialtiesListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_SPECIALTIES);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else if(obClass.equals(UniversityDTO.class)){
			log.warning("Removing is not yet implemented for: "+obClass);
		}else if(obClass.equals(DistribInstitDTO.class)){
			log.warning("Removing is not yet implemented for: "+obClass);
		}else if(obClass.equals(DistribProdDTO.class)){
			log.warning("Removing is not yet implemented for: "+obClass);
		}else if(obClass.equals(ReportDTO.class)){
			@SuppressWarnings("unchecked")
			List<ReportDTO> resList = (List<ReportDTO>) list;
			ReportListDTO entity = new ReportListDTO(resList);
			
			WebTarget target = client.target(HOST)
					.path(MedrepSessionBeanRemote.REST_REMOVE_REPORTS);
			Invocation.Builder invBuilder = target.request(MediaType.TEXT_PLAIN).header(ConnectionManager.AUTHORIZATION, token);
			Response response = invBuilder.post(Entity.entity(entity, MediaType.APPLICATION_XML));
			if(response.getStatus()!=200){
				throw new ServerErrorException(response);
			}
			result = Integer.parseInt(response.readEntity(String.class));
		}else{
			log.warning(obClass+" is not defined as entity available to store");
		}
	
		return result;
	}
	
	

	public List<InstitutionDTO> getDistributors() {
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_DISTRIBUTORS);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getDistributors();
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getDistributors();
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		InstitutionsListDTO result = response.readEntity(InstitutionsListDTO.class);
		
		return result.getList();
	}

	public List<ReportDTO> getReports(Date from, Date to){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		String cFrom;
		if(from==null)
			cFrom="2015-01-01";
		else
			cFrom = format.format(from);
		String cTo;
		if(to==null)
			cTo = format.format(new Date());
		else
			cTo = format.format(to);
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_REPORTS+"/"+cFrom+"/"+cTo);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getReports(from, to);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getReports(from, to);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		ReportListDTO result = response.readEntity(ReportListDTO.class);
		
		return result.getList();
	}
	
	public ProductDTO getProduct(int id){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_PRODUCT_BY_ID+"/"+id);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getProduct(id);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getProduct(id);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		ProductDTO result = response.readEntity(ProductDTO.class);
		
		return result;
	}

	public InstitutionDTO getInstitution(int id) {
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_INSTITUTION_BY_ID+"/"+id);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getInstitution(id);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getInstitution(id);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		InstitutionDTO result = response.readEntity(InstitutionDTO.class);
		
		return result;
	}

	public DistribInstitDTO getDistributorInstitutionByName(InstitutionDTO distributor, String name) {
		
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST)
				.path(MedrepSessionBeanRemote.REST_DISTRIB_INSTIT_BY_NAME+"/"+name);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.post(Entity.entity(distributor, MediaType.APPLICATION_XML));
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getDistributorInstitutionByName(distributor, name);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getDistributorInstitutionByName(distributor, name);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		DistribInstitDTO result = response.readEntity(DistribInstitDTO.class);
		
		return result;
		
	}

	public DistribInstitDTO getDistributorInstitutionByCode(InstitutionDTO distributor, String code){
		
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST)
				.path(MedrepSessionBeanRemote.REST_DISTRIB_INSTIT_BY_CODE+"/"+code);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.post(Entity.entity(distributor, MediaType.APPLICATION_XML));
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getDistributorInstitutionByCode(distributor, code);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getDistributorInstitutionByCode(distributor, code);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		DistribInstitDTO result = response.readEntity(DistribInstitDTO.class);
		
		return result;
		
	}

	public DistribProdDTO getDistributorProductByName(InstitutionDTO distributor, String name){
		
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST)
				.path(MedrepSessionBeanRemote.REST_DISTRIB_PRODUCT_BY_NAME+"/"+name);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.post(Entity.entity(distributor, MediaType.APPLICATION_XML));
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getDistributorProductByName(distributor, name);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getDistributorProductByName(distributor, name);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		DistribProdDTO result = response.readEntity(DistribProdDTO.class);

		return result;
	}

	
	public DistribProdDTO getDistributorProductByCode(InstitutionDTO distributor, String code){
		
		
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST)
				.path(MedrepSessionBeanRemote.REST_DISTRIB_PRODUCT_BY_CODE+"/"+code);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.post(Entity.entity(distributor, MediaType.APPLICATION_XML));
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getDistributorProductByCode(distributor, code);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getDistributorProductByCode(distributor, code);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		DistribProdDTO result = response.readEntity(DistribProdDTO.class);
		return result;
	}
	
	public InstitutionDTO getInstitutionById(int id){
		Client client;
		if(DISPLAY_REST_LOGS)
			client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		else
			client = ClientBuilder.newClient(new ClientConfig());
		
		WebTarget target = client.target(HOST).path(MedrepSessionBeanRemote.REST_INSTITUTION_BY_ID+"/"+id);
		
		Invocation.Builder invBuilder = target.request(MediaType.APPLICATION_XML).header(ConnectionManager.AUTHORIZATION, token);
		Response response = invBuilder.get();
		if(response.getStatus()!=Status.OK.getStatusCode()){
			try{
				login(null,null);
				return getInstitutionById(id);
			}catch(Exception e){
				CONNECTIONS_ATTEMPTS++;
				if(CONNECTIONS_ATTEMPTS>=MAX_ATTEMPTS){
					CONNECTIONS_ATTEMPTS=0;
					throw new ServerErrorException(response);
				}else{
					return getInstitutionById(id);
				}
					
			}
			
		}
		CONNECTIONS_ATTEMPTS = 0;
		InstitutionDTO result = response.readEntity(InstitutionDTO.class);
		
		return result;
	}
	

}
