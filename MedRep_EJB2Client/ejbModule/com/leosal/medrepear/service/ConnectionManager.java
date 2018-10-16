package com.leosal.medrepear.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.prefs.Preferences;

import javax.naming.NamingException;

import com.leosal.leotools.exceptions.UserNotAuthorisedException;
import com.leosal.medrepear.dto.AdvertDTO;
import com.leosal.medrepear.dto.DistribInstitDTO;
import com.leosal.medrepear.dto.DistribProdDTO;
import com.leosal.medrepear.dto.EventDTO;
import com.leosal.medrepear.dto.EventTypeDTO;
import com.leosal.medrepear.dto.InstitTypeDTO;
import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.dto.PersonDTO;
import com.leosal.medrepear.dto.PersonPrivDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.dtolist.RegionsListDTO;

public class ConnectionManager {
	
	public static final String AUTHORIZATION = "User_Auth_Token";
	
	private static ConnectionManager manager=null;
	private MedrepSessionBeanRemote serviceBean;
	private static Preferences prefs = 
			Preferences.userNodeForPackage(com.leosal.medrepear.service.ConnectionManager.class);
	
	
	private int attempts = 0; 
	private String token = "";
	private Set<PersonPrivDTO> permissions=null;
	
	private static final int MAX_ATTEMPS=3;
	private static final String LOGIN = "login";
	private static final String PASSWORD = "pasword";
	
	public static void main(String[] args){
		ConnectionManager conn = new ConnectionManager();
		
	}
	
	private ConnectionManager(){
		
	}
	
	@SuppressWarnings("unused")
	private static ConnectionManager getInstance(){
		if(manager==null)
				manager = new ConnectionManager();
			
		return manager;
	}
	/*
	public String getLastUser(){
		return prefs.get(LOGIN, "");
	}
	public String getLastPassword(){
		return prefs.get(PASSWORD, "");
	}
	
	public void connect(String login, String password) 
			throws NamingException, UserNotAuthorisedException{
		
		
		
	}

	
	public long totalContacts() throws UserNotAuthorisedException {
		return -1;
		
		
	}

	
	public List<PersonDTO> getContacts()
			throws UserNotAuthorisedException {
		return null;
		
	}

	
	public PersonDTO getContactById(int id)
			throws UserNotAuthorisedException {
		try {
			attempts++;
			PersonDTO result = serviceBean.getContactById(id,token);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getContactById(id,token);
					
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<PersonDTO> getRepresentatives()
			throws UserNotAuthorisedException {
		/*
		try {
			attempts++;
			//List<PersonDTO> result = serviceBean.getRepresentatives(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getRepresentatives(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
		/
		return null;
	}
	
	
	public List<RegionDTO> getAllRegions()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<RegionDTO> result = serviceBean.getAllRegions(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getAllRegions(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<RegionDTO> getRegions()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<RegionDTO> result = serviceBean.getRegions(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getRegions(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<EventDTO> getEvents(Date from, Date to )
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<EventDTO> result = serviceBean.getEvents(from,to,token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getEvents(from,to,token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<EventTypeDTO> getEventTypes()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<EventTypeDTO> result = serviceBean.getEventTypes(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getEventTypes(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<AdvertDTO> getAdverts()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<AdvertDTO> result = serviceBean.getAdverts(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getAdverts(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<ProductDTO> getProducts()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<ProductDTO> result = serviceBean.getProducts(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getProducts(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<InstitutionDTO> getInstitutions()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<InstitutionDTO> result = serviceBean.getInstitutions(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getInstitutions(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<InstitTypeDTO> getInstitutionTypes()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<InstitTypeDTO> result = serviceBean.getInstitutionTypes(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getInstitutionTypes(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<SpecialtyDTO> getSpecialties()
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<SpecialtyDTO> result = serviceBean.getSpecialties(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getSpecialties(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	public List<RegionDTO> storeRegions(List<RegionDTO> list )
			throws UserNotAuthorisedException {
		try {
			attempts++;
			List<RegionDTO> result = serviceBean.storeRegions(new RegionsListDTO(list),token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.storeRegions(new RegionsListDTO(list),token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				} catch (Exception e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}
	
	public <T> int removeDTOs(List<T> list){
		if(list==null) return 0;
		try {
			attempts++;
			int result = serviceBean.removeFromDatabase(list, token);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.removeFromDatabase(list,token);
				} catch (NamingException e1) {
					attempts=0;
					return -2;
				} catch (UserNotAuthorisedException e1) {
					attempts=0;
					return -3;
				} catch (Exception e1) {
					e1.printStackTrace();
					attempts=0;
					return -4;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return -5;
			}
				
			
		}
	}
	
	public <T> T storeDTO(T dto) throws UserNotAuthorisedException{
		List<T> list = new ArrayList<T>();
		list.add(dto);
		list = storeDTOs(list);
		if(list==null || list.size()==0)
			return null;
		return list.get(0);
	}
	
	public <T> List<T> storeDTOs(List<T> list )
			throws UserNotAuthorisedException {
		if(list==null) return null;
		try {
			attempts++;
			List<T> result = serviceBean.storeDTOs(list,token);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.storeDTOs(list,token);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				} catch (Exception e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	
	
	public  PersonDTO currentUser() {
		try {
			attempts++;
			PersonDTO result = serviceBean.identifyAuthTokenValid(token);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.identifyAuthTokenValid(token);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				} catch (UserNotAuthorisedException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}
	
	public boolean hasPermission(int priv_id){
		if(permissions==null)
			permissions=currentUser().getPersonPrivs();
		for(PersonPrivDTO pp:permissions){
			if(pp.getPrivId()==priv_id) return true;
		}
		return false;
	}

	public List<InstitutionDTO> getDistributors() throws UserNotAuthorisedException {
		try {
			attempts++;
			List<InstitutionDTO> result = serviceBean.getDistributors(token).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getDistributors(token).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public List<ReportDTO> getReports(Date dFrom, Date dTo) throws UserNotAuthorisedException {
		try {
			attempts++;
			List<ReportDTO> result = serviceBean.getReports(token,dFrom,dTo).getList();
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getReports(token,dFrom,dTo).getList();
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public ProductDTO getProduct(int prId) throws UserNotAuthorisedException {
		try {
			attempts++;
			ProductDTO result = serviceBean.getProduct(token, prId);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getProduct(token, prId);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public InstitutionDTO getInstitution(int inId) throws UserNotAuthorisedException {
		try {
			attempts++;
			InstitutionDTO result = serviceBean.getInstitution(token,inId);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getInstitution(token,inId);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public DistribInstitDTO getDistribInstitutionByName(InstitutionDTO distr,
			String name) throws UserNotAuthorisedException {
		try {
			attempts++;
			DistribInstitDTO result = serviceBean.getDistributorInstitutionByName(token, distr, name);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getDistributorInstitutionByName(token, distr, name);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public DistribInstitDTO getDistribInstitutionByCode(InstitutionDTO distr,
			String code) throws UserNotAuthorisedException {
		try {
			attempts++;
			DistribInstitDTO result = serviceBean.getDistributorInstitutionByCode(token,distr,code);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getDistributorInstitutionByCode(token,distr,code);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public DistribProdDTO getDistribProductByName(InstitutionDTO distributor,
			String product) throws UserNotAuthorisedException {
		try {
			attempts++;
			DistribProdDTO result = serviceBean.getDistributorProductByName(token,distributor, product);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getDistributorProductByName(token,distributor, product);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}

	public DistribProdDTO getDistribProductByCode(InstitutionDTO distributor,
			String code) throws UserNotAuthorisedException {
		try {
			attempts++;
			DistribProdDTO result = serviceBean.getDistributorProductByCode(token,distributor, code);
			attempts=0;
			return result;
		} catch (Exception e) {
			if(attempts<=MAX_ATTEMPS){
				try {
					connect(null,null);
					return serviceBean.getDistributorProductByCode(token,distributor, code);
				} catch (NamingException e1) {
					attempts=0;
					return null;
				}
				
			}else{
				e.printStackTrace();
				attempts=0;
				return null;
			}
				
			
		}
	}
*/
}
