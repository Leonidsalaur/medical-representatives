package com.leosal.medrepear.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
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

@Remote
public interface MedrepSessionBeanRemote {
	public static final String USER_AUTH_TOKEN = "User_Auth_Token";
	
	//REST paths
	public static final String REST_PARENT = "bean";
	
	public static final String REST_LOGIN = "login";
	public static final String REST_CONTACTS="contacts";
	public static final String REST_CONTACT_BY_ID = "contact_id";
	public static final String REST_REGIONS = "regions";
	public static final String REST_EVENTS = "events";
	public static final String REST_EVENT_TYPES = "event_types";
	public static final String REST_ADVERTS = "adverts";
	public static final String REST_PRODUCTS = "products";
	public static final String REST_INSTITUTIONS = "institutions";
	public static final String REST_INSTITUTION_TYPES = "institution_types";
	public static final String REST_REPRESENTATIVES = "reps";
	public static final String REST_SPECIALTIES = "specialties";
	public static final String REST_DISTRIBUTORS = "distributors";
	
	public static final String REST_REPORTS = "reports";
	public static final String REST_PRODUCT_BY_ID = "product_by_id";
	public static final String REST_INSTITUTION_BY_ID = "institution_by_id";
	public static final String REST_DISTRIB_INSTIT_BY_NAME = "distrib_instit_by_name";
	public static final String REST_DISTRIB_INSTIT_BY_CODE = "distrib_instit_by_code";
	public static final String REST_DISTRIB_PRODUCT_BY_NAME = "distrib_product_by_name";
	public static final String REST_DISTRIB_PRODUCT_BY_CODE = "distrib_product_by_code";
	
	public static final String REST_STORE_REGIONS = "store_regions";
	public static final String REST_REMOVE_REGIONS = "remove_regions";
	public static final String REST_STORE_ADVERTS = "store_adverts";
	public static final String REST_REMOVE_ADVERTS = "remove_adverts";
	public static final String REST_STORE_EVENTS = "store_events";
	public static final String REST_REMOVE_EVENTS = "remove_events";
	public static final String REST_STORE_EDUC_TYPES = "store_educ_types";
	public static final String REST_REMOVE_EDUC_TYPES = "remove_educ_types";
	public static final String REST_STORE_EVENT_TYPES = "store_event_types";
	public static final String REST_REMOVE_EVENT_TYPES = "remove_event_types";
	public static final String REST_STORE_INSTITUTION_TYPES = "store_institution_types";
	public static final String REST_REMOVE_INSTITUTION_TYPES = "remove_institution_types";
	public static final String REST_STORE_INSTITUTIONS = "store_institutions";
	public static final String REST_REMOVE_INSTITUTIONS = "remove_institutions";
	public static final String REST_STORE_PERSONS = "store_persons";
	public static final String REST_REMOVE_PERSONS = "remove_persons";
	public static final String REST_STORE_PERSON_PRIVS = "store_person_priv";
	public static final String REST_REMOVE_PERSON_PRIVS = "remove_person_priv";
	public static final String REST_STORE_PERSON_TYPE = "store_person_type";
	public static final String REST_REMOVE_PERSON_TYPE = "remove_person_type";
	public static final String REST_STORE_PRODUCTS = "store_products";
	public static final String REST_REMOVE_PRODUCTS = "remove_products";
	public static final String REST_STORE_SPECIALTIES = "store_specialty";
	public static final String REST_REMOVE_SPECIALTIES = "remove_specialty";

	public static final String REST_STORE_REPORTS = "store_reports";

	public static final String REST_REMOVE_REPORTS = "remove_reports";

	public static final String CHECK_TOKEN = "check_token";
	
	
	/**Counts all contacts registered in database
	 * @param token USER_TOKEN from login()
	 * @return Total number of contacts in database 
	 * @throws UserNotAuthorisedException
	 */
	public long totalContacts(String token) throws UserNotAuthorisedException;
	/**Gets contacts from database according to the current user's permissions
	 * @param token USER_TOKEN from login()
	 * @return List of contacts
	 * @throws UserNotAuthorisedException
	 */
	public List<PersonDTO> getContacts(String token) throws UserNotAuthorisedException;
	/**Searches contact id in database, if user has the permission
	 * @param id unique contact identifier
	 * @param token USER_TOKEN from login()
	 * @return Contact with the given id or null
	 * @throws UserNotAuthorisedException
	 */
	public Response getContactById(int id, String token);
	/**
	 * @param token
	 * @return
	 */
	public Response getRepresentatives(String token);
	/**
	 * @param token
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public RegionsListDTO getAllRegions(String token) throws UserNotAuthorisedException;
	/**
	 * @param token
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public Response getRegions(String token);
	/**
	 * @param from
	 * @param to
	 * @param token
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public EventsListDTO getEvents(Date from,Date to, String token) throws UserNotAuthorisedException;
	/**
	 * @param token
	 * @return
	 */
	public Response getEventTypes(String token);
	/**
	 * @param token
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public Response getAdverts(String token);
	/**
	 * @param token
	 * @return
	 */
	public Response getProducts(String token) ;
	/**
	 * @param token
	 * @return
	 */
	public Response getInstitutions(String token);
	/**
	 * @param token
	 * @return
	 */
	public Response getInstitutionTypes(String token);
	/**
	 * @param token
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public Response getSpecialties(String token);
	/**Saves or updates regions in database
	 * @param list regions to save/update
	 * @return updated list of stored regions with their current id's
	 */
	public Response storeRegions(RegionsListDTO list, String token) ;
	/**Saves or updates Entities from DTO in database
	 * @param list DTO's to save/update
	 * @return updated list of stored DTO's with their current id's
	 * @throws Exception
	 */
	public <T> List<T> storeDTOs(List<T> list, String token) throws Exception;
	/** Removes Entities from database
	 * @param list DTO's to remove
	 * @param token User token
	 * @return number of removed records
	 * @throws UserNotAuthorisedException
	 * @throws Exception 
	 */
	public <T> int removeFromDatabase(List<T> list, String token) throws UserNotAuthorisedException, Exception;
	/**
	 * @param user
	 * @param password
	 * @return
	 * @throws UserNotAuthorisedException
	 */
	public boolean authorize(String user, String password) throws UserNotAuthorisedException;
	/**
	 * @param token
	 * @return
	 */
	public PersonDTO isAuthorized(String token);
	
	/**Registers the user for the current session
	 * @param username User's login
	 * @param password User's password
	 * @return USER_TOKEN to use in HttpHeader for WebService, or as argument in SessionBean commands
	 * @throws UserNotAuthorisedException
	 */
	public Response login(String username, String password);
	
	
	/**
	 * @param authToken
	 * @return
	 */
	public PersonDTO identifyAuthTokenValid(String authToken);
	public Response getDistributors(String token);
	public Response getReports(String token, Date dFrom, Date dTo);
	public Response getProduct(String token, int prId);
	public Response getInstitution(String token, int inId);
	public Response getDistributorInstitutionByName(String token,InstitutionDTO distr, String name);
	public Response getDistributorInstitutionByCode(String token,InstitutionDTO distr, String code);
	public Response getDistributorProductByName(String token,InstitutionDTO distributor, String product);
	public Response getDistributorProductByCode(String token,InstitutionDTO distributor, String code);
	public Response add(int a, int b);
	public Response getRestContacts(String token);
	public Response getReports(String from, String to, String token);
	public Response getEvents(String from, String to, String token);
	Response removeRegions(RegionsListDTO list, String token);
	Response storeAdverts(AdvertListDTO list, String token);
	Response removeAdverts(AdvertListDTO list, String token);
	Response storeEvents(EventsListDTO list, String token);
	Response removeEvents(EventsListDTO list, String token);
	Response storeEducTypes(EducTypeListDTO list, String token);
	Response removeEducTypes(EducTypeListDTO list, String token);
	Response storeEventTypes(EventTypesListDTO list, String token);
	Response removeEventTypes(EventTypesListDTO list, String token);
	Response storeInstitutionTypes(InstitutionTypesListDTO list,String token);
	Response removeInstitutionTypes(InstitutionTypesListDTO list, String token);
	Response storeInstitutions(InstitutionsListDTO list, String token);
	Response removeInstitutions(InstitutionsListDTO list, String token);
	Response storePersons(PersonsListDTO list, String token);
	Response removePersons(PersonsListDTO list, String token);
	Response storeProducts(ProductListDTO list, String token);
	Response removeProducts(ProductListDTO list, String token);
	Response storeSpecialties(SpecialtiesListDTO list, String token);
	Response removeSpecialties(SpecialtiesListDTO list, String token);
	Response storeReports(ReportListDTO list, String token);
	Response removeReports(ReportListDTO list, String token);
	Response storePersonPrivs(PersonPrivListDTO list, String token);
	Response removePersonPrivs(PersonPrivListDTO list, String token);
	Response storePersonType(PersonTypeListDTO list, String token);
	Response removePersonType(PersonTypeListDTO list, String token);
	
	
	
	
	
	
	
	
	

}
