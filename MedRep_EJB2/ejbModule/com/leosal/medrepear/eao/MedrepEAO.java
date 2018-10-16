package com.leosal.medrepear.eao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.leosal.medrepear.dto.InstitutionDTO;
import com.leosal.medrepear.entity.Advert;
import com.leosal.medrepear.entity.DistribInstit;
import com.leosal.medrepear.entity.DistribProd;
import com.leosal.medrepear.entity.Event;
import com.leosal.medrepear.entity.EventType;
import com.leosal.medrepear.entity.InstitType;
import com.leosal.medrepear.entity.Institution;
import com.leosal.medrepear.entity.Person;
import com.leosal.medrepear.entity.Product;
import com.leosal.medrepear.entity.Region;
import com.leosal.medrepear.entity.Report;
import com.leosal.medrepear.entity.Specialty;
import com.leosal.util.jpa.Entity;

/**
 * Session Bean implementation class MedrepEAO
 */
@Stateless
@LocalBean
@WebService
public class MedrepEAO {
	@PersistenceContext
	EntityManager etmn; 
	
	Logger logger;

    /**
     * Default constructor. 
     */
    public MedrepEAO() {
        logger = Logger.getLogger(this.getClass().toString());
    }
    
    public long countPersons(){
    	long result;
    	Query q = etmn.createQuery("select count(pe) from Person pe");
    	result = (Long)q.getSingleResult();
    	return result;
    }
    

	@SuppressWarnings("unchecked")
	public List<Person> allPersons() {
		List<Person> result;
		Query q = etmn.createQuery("select pe from Person pe "
				+ "order by pe.firstname, pe.midname, pe.lastname");
		result = (List<Person>)q.getResultList();
		return result;
	}
	
	public Person getContactById(int id) {
		Person result;
		Query q = etmn.createQuery("select pe from Person pe where pe.id=:id");
		q.setParameter("id", id);
		result = (Person)q.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getContacts(){
		List<Person> result;
		Query q = etmn.createQuery("select pe from Person pe "
				+ "WHERE pe.login IS NULL "
				+ "order by LOWER(pe.firstname), LOWER(pe.midname), LOWER(pe.lastname)");
		result = (List<Person>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getContactsByRep(int rep_id){
		List<Person> result = new ArrayList<Person>();;
		Query q = etmn.createQuery("SELECT DISTINCT pe FROM Person pe JOIN pe.institutions AS inst "
				+ "WHERE inst.region.person.id=:repId  "
				+ "");
		q.setParameter("repId", rep_id);
		result = (List<Person>)q.getResultList();
		
		System.out.println("Ordered 3 contacts");
		return result;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Person> getRepresentatives() {
		List<Person> result;
		Query q = etmn.createQuery("select pe from Person pe where pe.login is not null "
				+ "order by pe.firstname, pe.midname, pe.lastname");
		result = (List<Person>)q.getResultList();
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<Region> getAllRegions() {
		List<Region> result;
		Query q = etmn.createQuery("select reg from Region reg order by reg.name");
		result = (List<Region>)q.getResultList();
		return result;
	}

	
	@SuppressWarnings("unchecked")
	public List<Region> getRegionsByRep(int rep_id) {
		List<Region> result;
		Query q = etmn.createQuery("select reg from Region reg where reg.person.id=:rep_id "
				+ "order by reg.name");
		q.setParameter("rep_id", rep_id);
		result = (List<Region>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getEvents(Date from, Date to, int rep_id){
		
		//logger.log(Level.INFO,"\n\tExtracting events from "+from+" to "+to+" for rep_id "+rep_id);
		
		List<Event> result;
		Query q;
		if(rep_id>0){
			q = etmn.createQuery("select ev from Event ev where "
					+ "ev.date >= :from "
					+ "and ev.date <= :to "
					+ "and ev.person.id=:rep_id order by ev.date desc, "
					+ "ev.person.firstname, ev.person.midname, ev.person.lastname");
			q.setParameter("rep_id", rep_id);
		}else{
			q = etmn.createQuery("select ev from Event ev where "
					+ "ev.date >= :from "
					+ "and ev.date <= :to order by ev.date desc, "
					+ "ev.person.firstname, ev.person.midname, ev.person.lastname");
		}
		q.setParameter("from", from, javax.persistence.TemporalType.DATE);
		q.setParameter("to",  to, TemporalType.DATE);
		result = (List<Event>)q.getResultList();
		//logger.log(Level.INFO,"\n\t\tExtracted "+result.size()+" records");
		return result;
			
	}
	
	public Person getUser(String login, String password){
		Person result;
		Query q = etmn.createQuery("select p from Person p where "
				+ "p.login=:login and p.password=:pass");
		q.setParameter("login", login);
		q.setParameter("pass", password);
		try{
			result = (Person)q.getSingleResult();
			
			return result;
		}catch(NoResultException ex){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EventType> getEventTypes(){
		List<EventType> result;
		Query q = etmn.createQuery("select et from EventType et order by et.name");
		result = (List<EventType>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Advert> getAdverts(){
		List<Advert> result;
		Query q = etmn.createQuery("select et from Advert et order by et.name");
		result = (List<Advert>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(){
		List<Product> result;
		Query q = etmn.createQuery("select et from Product et order by et.name");
		result = (List<Product>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Institution> getDistributors() {
		List<Institution> result;
		Query q = etmn.createQuery("select et from Institution et WHERE et.distributor>0 order by et.name");
		result = (List<Institution>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutions(){
		List<Institution> result;
		Query q = etmn.createQuery("select et from Institution et order by et.name");
		result = (List<Institution>)q.getResultList();
		return result;
	}
	
	public Institution getInstitution(int id){
		Institution result;
		Query q = etmn.createQuery("select inst from Institution inst where inst.id=:id");
		q.setParameter("id", id);
		result = (Institution)q.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutionsByRep(int rep_id){
		List<Institution> result;
		Query q = etmn.createQuery("select et from Institution et  WHERE et.region.person.id=:repId order by et.name ");
		q.setParameter("repId", rep_id);
		result = (List<Institution>)q.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<InstitType> getInstitutionTypes(){
		List<InstitType> result;
		Query q = etmn.createQuery("select et from InstitType et order by et.name");
		result = (List<InstitType>)q.getResultList();
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<Specialty> getSpecialties(){
		List<Specialty> result;
		Query q = etmn.createQuery("select et from Specialty et order by et.name");
		result = (List<Specialty>)q.getResultList();
		return result;
	}

	public <T extends Entity> T findOrFail(Class<T> class1, Integer id) throws Exception {
		T e = etmn.find(class1, id);
		if(e==null) throw new Exception(class1.getClass().getSimpleName());
		return e;
	}
	
	public <T extends Entity> boolean removeEntity(T entity){
		if(entity==null||!entity.hasId()) return false;
		try{
			//em.getTransaction().begin();
			T toRemove = etmn.merge(entity);
			etmn.remove(toRemove);
			 
			//em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	public <T extends Entity> void persist(T entity){
		
		if(entity.hasId())
			{
			//System.out.println("From EAO: merge() "+entity.toString());
			etmn.merge(entity);
			}
		else{
			//System.out.println("From EAO: persist() "+entity.toString());
			etmn.persist(entity);
			if(!entity.hasId())
				{
				etmn.flush();
				}
		}
		
		//System.out.println("From Server: "+entity.getId());
		
	}

	@SuppressWarnings("unchecked")
	public List<Report> getReports(Date dFrom, Date dTo) {
		List<Report> result;
		Query q;
		q = etmn.createQuery("select re from Report re where "
					+ "re.perEnd >= :from "
					+ "and re.perStart <= :to");

		q.setParameter("from", dFrom, javax.persistence.TemporalType.DATE);
		q.setParameter("to",  dTo, TemporalType.DATE);
		result = (List<Report>)q.getResultList();
		return result;
	}

	public Product getProduct(int prId) {
		Product result;
		Query q = etmn.createQuery("select pr from Product pr where pr.id=:id");
		q.setParameter("id", prId);
		result = (Product)q.getSingleResult();
		return result;
	}

	public DistribInstit getDistributorInstitutionByName(InstitutionDTO distr,
			String name) {
		DistribInstit result;
		Query q = etmn.createQuery("select di from DistribInstit di where di.institution1.id=:id "
				+ "and di.institName=:name");
		q.setParameter("id", distr.getId());
		q.setParameter("name", name);
		try {
			result = (DistribInstit)q.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		return result;
	}
	public DistribInstit getDistributorInstitutionByCode(InstitutionDTO distr,
			String code) {
		DistribInstit result;
		Query q = etmn.createQuery("select di from DistribInstit di where di.institution1.id=:id "
				+ "and di.distribCod=:code");
		q.setParameter("id", distr.getId());
		q.setParameter("code", code);
		try {
			result = (DistribInstit)q.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		return result;
	}
	public DistribProd getDistributorProductByName(InstitutionDTO distr,
			String name) {
		DistribProd result;
		Query q = etmn.createQuery("select di from DistribProd di where di.institution.id=:id "
				+ "and di.prodName=:name");
		q.setParameter("id", distr.getId());
		q.setParameter("name", name);
		try {
			result = (DistribProd)q.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		return result;
	}
	public DistribProd getDistributorProductByCode(InstitutionDTO distr,
			String code) {
		DistribProd result;
		Query q = etmn.createQuery("select di from DistribProd di where di.institution.id=:id "
				+ "and di.distribCod=:code");
		q.setParameter("id", distr.getId());
		q.setParameter("code", code);
		try {
			result = (DistribProd)q.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
		return result;
	}

	

}
