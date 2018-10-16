package com.leosal.medrepear.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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
import com.leosal.medrepear.dto.PersonTypeDTO;
import com.leosal.medrepear.dto.ProductDTO;
import com.leosal.medrepear.dto.RegionDTO;
import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.dto.SpecialtyDTO;
import com.leosal.medrepear.service.RestManager;

public class RestTester {
	
	@BeforeClass
	public static void login(){
		RestManager manager = RestManager.getInstance();
		try {
			manager.login("diana.salaurd", "123456");
		} catch (UserNotAuthorisedException e) {
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void simpleAdd(){
		
		String result = RestManager.getInstance().simpleAdd(2, 5);
		assertEquals("7", result);
	}
	@Test
	public void getRepresentatives(){
		
		List<PersonDTO> result = RestManager.getInstance().getRepresentatives();
		
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getContacts(){
		List<PersonDTO> result = RestManager.getInstance().getContacts();
		
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getContactById(){
		PersonDTO result = RestManager.getInstance().getContactById(184);
		
		assertNotNull("Server returned NULL result",result);
	}
	@Test
	public void getRegions() {
		List<RegionDTO> result = RestManager.getInstance().getRegions();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getEvents() {
		List<EventDTO> result = RestManager.getInstance().getEvents(null,null);
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
		
	}
	@Test
	public void getEventTypes() {
		List<EventTypeDTO> result = RestManager.getInstance().getEventTypes();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getAdverts(){
		List<AdvertDTO> result = RestManager.getInstance().getAdverts();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getProducts(){
		List<ProductDTO> result = RestManager.getInstance().getProducts();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getInstitutions(){
		List<InstitutionDTO> result = RestManager.getInstance().getInstitutions();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getInstitutionTypes(){
		List<InstitTypeDTO> result = RestManager.getInstance().getInstitutionTypes();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getSpecialties(){
		List<SpecialtyDTO> result = RestManager.getInstance().getSpecialties();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getDistributors() {
		List<InstitutionDTO> result = RestManager.getInstance().getDistributors();
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getReports(){
		List<ReportDTO> result = RestManager.getInstance().getReports(null, null);
		assertNotNull("Server returned NULL result",result);
		assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getProduct(){
		List<EventTypeDTO> result = RestManager.getInstance().getEventTypes();
		assertNotNull("Server returned NULL result",result);
		
	}
	@Test
	public void getInstitution() {
		InstitutionDTO result = RestManager.getInstance().getInstitution(1923);
		assertNotNull("Server returned NULL result",result);
		//assertTrue("List size = :"+result.size(), result.size()>0);
	}
	@Test
	public void getDistributorInstitutionByName() {
		int id = 1385;
		String name = "Prodiafarm filial Vulcanesti";
		InstitutionDTO distrib = RestManager.getInstance().getInstitutionById(id);
		assertNotNull("Server returned NULL result for distributor with id "+id,distrib);
		assertEquals( 5, distrib.getInstitType().getId().longValue());
		
		DistribInstitDTO result = RestManager.getInstance().getDistributorInstitutionByName(distrib, name);
		assertNotNull("Server returned NULL result",result);
		//System.out.println(result.getInstitution().getName());
		
	}
	@Test
	public void getDistributorInstitutionByCode(){
		int id = 1383;
		String code = "F01";
		InstitutionDTO distrib = RestManager.getInstance().getInstitutionById(id);
		assertNotNull("Server returned NULL result for distributor with id "+id,distrib);
		assertEquals( 5, distrib.getInstitType().getId().longValue());
		
		DistribInstitDTO result = RestManager.getInstance().getDistributorInstitutionByCode(distrib, code);
		assertNotNull("Server returned NULL result",result);
		//System.out.println(result.getInstitution().getName());
	}
	@Test
	public void getDistributorProductByName(){
		int id = 1384;
		String name = "Bisogamma-10 comp.10mg N100";
		InstitutionDTO distrib = RestManager.getInstance().getInstitutionById(id);
		assertNotNull("Server returned NULL result for distributor with id "+id,distrib);
		assertEquals( 5, distrib.getInstitType().getId().longValue());
		
		DistribProdDTO result = RestManager.getInstance().getDistributorProductByName(distrib, name);
		assertNotNull("Server returned NULL result",result);
		//System.out.println(result.getProduct().getName());
	}
	@Test
	public void getDistributorProductByCode(){
		int id = 1384;
		String code = "123456789";
		InstitutionDTO distrib = RestManager.getInstance().getInstitutionById(id);
		assertNotNull("Server returned NULL result for distributor with id "+id,distrib);
		assertEquals( 5, distrib.getInstitType().getId().longValue());
		
		DistribProdDTO result = RestManager.getInstance().getDistributorProductByCode(distrib, code);
		assertNotNull("Server returned NULL result",result);
		//System.out.println(result.getProdName());
	}
	@Test
	public void storeRemoveEditRegions(){
		List<RegionDTO> regs0 = RestManager.getInstance().getRegions();
		RegionDTO testRegion = regs0.get(0);
		String name = testRegion.getName();
		int id = testRegion.getId();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<RegionDTO> testList = new ArrayList<RegionDTO>();
		
		//Edit test
		name+="*";
		testRegion.setName(name);
		testList.add(testRegion);
		List<RegionDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testRegion=null;
		for(RegionDTO r:resultList){
			if(r.getId().intValue()==id){
				testRegion=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testRegion);
		assertEquals("Entity name not edited", name, testRegion.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getRegions();
		for(RegionDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		
		//save test
		
		testRegion.setId(null);
		testList = new ArrayList<RegionDTO>();
		testList.add(testRegion);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testRegion=null;
		for(RegionDTO r:resultList){
			if(r.getName().equals(name)){
				assertTrue("Entity id remained the same", id!=r.getId());
				testRegion=r;
				break;
			}
		}
		assertNotNull("Edited region not added", testRegion);
		
		
		
	}
	@Test
	public void storeRemoveEditAdverts(){
		List<AdvertDTO> regs0 = RestManager.getInstance().getAdverts();
		AdvertDTO testAdvert = regs0.get(0);
		String name = testAdvert.getName();
		int id = testAdvert.getId();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<AdvertDTO> testList = new ArrayList<AdvertDTO>();
		
		//Edit test
		name+="*";
		testAdvert.setName(name);
		testList.add(testAdvert);
		List<AdvertDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testAdvert=null;
		for(AdvertDTO r:resultList){
			if(r.getId().intValue()==id){
				testAdvert=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testAdvert);
		assertEquals("Entity name not edited", name, testAdvert.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getAdverts();
		for(AdvertDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		//System.out.println("Removed entity: "+name+" ["+id+"]");
		
		//save test
		testAdvert.setId(null);
		testList = new ArrayList<AdvertDTO>();
		testList.add(testAdvert);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testAdvert=null;
		for(AdvertDTO r:resultList){
			if(r.getName().equals(name)){
				assertTrue("Entity id remained the same", id!=r.getId());
				testAdvert=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testAdvert);
		//System.out.println("Completed entity: "+name+" ["+id+"]");
	}
	@Test
	public void storeRemoveEditEvents(){
		List<EventDTO> regs0 = RestManager.getInstance().getEvents(null, null);
		EventDTO testEvent = regs0.get(0);
		String name = testEvent.getMessage();
		int id = testEvent.getId();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<EventDTO> testList = new ArrayList<EventDTO>();
		
		//Edit test
		name+="*";
		testEvent.setMessage(name);
		testList.add(testEvent);
		List<EventDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEvent=null;
		for(EventDTO r:resultList){
			if(r.getId().intValue()==id){
				testEvent=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEvent);
		assertEquals("Entity name not edited", name, testEvent.getMessage());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getEvents(null, null);
		for(EventDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		//System.out.println("Removed entity: "+name+" ["+id+"]");
		
		//save test
		testEvent.setId(null);
		testList = new ArrayList<EventDTO>();
		testList.add(testEvent);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEvent=null;
		for(EventDTO r:resultList){
			if(r.getMessage().equals(name)){
				assertTrue("Entity id remained the same", id!=r.getId());
				testEvent=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEvent);
		//System.out.println("Completed entity: "+name+" ["+id+"]");
	}
	@Test
	public void storeRemoveEditProducts(){
		ProductDTO testEntity = new ProductDTO();
		testEntity.setName("New Product");
		String name = testEntity.getName();
		
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<ProductDTO> testList = new ArrayList<ProductDTO>();
		Integer id = null;
		testList = new ArrayList<ProductDTO>();
		testList.add(testEntity);
		List<ProductDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(ProductDTO r:resultList){
			if(r.getName().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setName(name);
		testList = new ArrayList<ProductDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(ProductDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getProducts();
		for(ProductDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
	}
	@Test
	public void storeRemoveEditSpecialties(){
		//List<SpecialtyDTO> ents0 = RestManager.getInstance().getSpecialties();
		SpecialtyDTO testEntity = new SpecialtyDTO();
		testEntity.setName("New specialty");
		String name = testEntity.getName();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<SpecialtyDTO> testList = new ArrayList<SpecialtyDTO>();
		Integer id = null;
		testList = new ArrayList<SpecialtyDTO>();
		testList.add(testEntity);
		List<SpecialtyDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(SpecialtyDTO r:resultList){
			if(r.getName().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setName(name);
		testList = new ArrayList<SpecialtyDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(SpecialtyDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getSpecialties();
		for(SpecialtyDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		
	}
	@Test
	public void storeRemoveEditEductTypes(){
		//List<SpecialtyDTO> ents0 = RestManager.getInstance().getSpecialties();
		//Test Saving
		EducTypeDTO testEntity = new EducTypeDTO();
		testEntity.setType("New Type");
		String name = testEntity.getType();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<EducTypeDTO> testList = new ArrayList<EducTypeDTO>();
		Integer id = null;
		testList = new ArrayList<EducTypeDTO>();
		testList.add(testEntity);
		List<EducTypeDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(EducTypeDTO r:resultList){
			if(r.getType().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setType(name);
		testList = new ArrayList<EducTypeDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(EducTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getType());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
	}
	@Test
	public void storeRemoveEditEventTypes(){
		//List<SpecialtyDTO> ents0 = RestManager.getInstance().getSpecialties();
		EventTypeDTO testEntity = new EventTypeDTO();
		testEntity.setName("New event type");
		String name = testEntity.getName();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<EventTypeDTO> testList = new ArrayList<EventTypeDTO>();
		Integer id = null;
		testList.add(testEntity);
		List<EventTypeDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(EventTypeDTO r:resultList){
			if(r.getName().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setName(name);
		testList = new ArrayList<EventTypeDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(EventTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getEventTypes();
		for(EventTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		
	}
	@Test
	public void storeRemoveEditInstitutionTypes(){
		//List<SpecialtyDTO> ents0 = RestManager.getInstance().getSpecialties();
		InstitTypeDTO testEntity = new InstitTypeDTO();
		testEntity.setName("New institution type");
		String name = testEntity.getName();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<InstitTypeDTO> testList = new ArrayList<InstitTypeDTO>();
		Integer id = null;
		testList.add(testEntity);
		List<InstitTypeDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(InstitTypeDTO r:resultList){
			if(r.getName().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setName(name);
		testList = new ArrayList<InstitTypeDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(InstitTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getInstitutionTypes();
		for(InstitTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		
	}
	@Test
	public void storeRemoveEditInstitutions(){
		List<InstitutionDTO> regs0 = RestManager.getInstance().getInstitutions();
		InstitutionDTO testRegion = regs0.get(0);
		String name = testRegion.getName();
		int id = testRegion.getId();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<InstitutionDTO> testList = new ArrayList<InstitutionDTO>();
		
		//Edit test
		name+="*";
		testRegion.setName(name);
		testList.add(testRegion);
		List<InstitutionDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testRegion=null;
		for(InstitutionDTO r:resultList){
			if(r.getId().intValue()==id){
				testRegion=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testRegion);
		assertEquals("Entity name not edited", name, testRegion.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getInstitutions();
		for(InstitutionDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		
		//save test
		
		testRegion.setId(null);
		testList = new ArrayList<InstitutionDTO>();
		testList.add(testRegion);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testRegion=null;
		for(InstitutionDTO r:resultList){
			if(r.getName().equals(name)){
				assertTrue("Entity id remained the same", id!=r.getId());
				testRegion=r;
				break;
			}
		}
		assertNotNull("Edited region not added", testRegion);
		
	}
	@Test
	public void storeRemoveEditPersonTypes(){
		//Saving Test
		PersonTypeDTO testEntity = new PersonTypeDTO();
		testEntity.setName("New person type");
		String name = testEntity.getName();
		List<PersonTypeDTO> testList = new ArrayList<PersonTypeDTO>();
		Integer id = null;
		testList.add(testEntity);
		List<PersonTypeDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(PersonTypeDTO r:resultList){
			if(r.getName().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setName(name);
		testList = new ArrayList<PersonTypeDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(PersonTypeDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getName());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
	}
	@Test
	public void storeRemoveEditReports(){
		List<ReportDTO> ents0 = RestManager.getInstance().getReports(null, null);
		ReportDTO testEntity = ents0.get(0);
		String name = testEntity.getComment();
		int id = testEntity.getId();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<ReportDTO> testList = new ArrayList<ReportDTO>();
		
		//Edit test
		name+="*";
		testEntity.setComment(name);
		testList.add(testEntity);
		List<ReportDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(ReportDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getComment());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getReports(null, null);
		for(ReportDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
		//System.out.println("Removed entity: "+name+" ["+id+"]");
		
		//save test
		testEntity.setId(null);
		testList = new ArrayList<ReportDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(ReportDTO r:resultList){
			if(r.getComment().equals(name)){
				assertTrue("Entity id remained the same", id!=r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		//System.out.println("Completed entity: "+name+" ["+id+"]");
	}
	@Test
	public void storeRemoveEditPersons(){
		//List<SpecialtyDTO> ents0 = RestManager.getInstance().getSpecialties();
		PersonDTO testEntity = new PersonDTO();
		testEntity.setFirstname("Leonid");
		testEntity.setMidname("Vasile");
		testEntity.setLastname("Salaur");
		testEntity.setPersonType(new PersonTypeDTO(PersonTypeDTO.OTHER, "Other"));
		testEntity.setComment(testEntity.toString());
		String name = testEntity.getComment();
		//System.out.println("Tested entity: "+name+" ["+id+"]");
		List<PersonDTO> testList = new ArrayList<PersonDTO>();
		Integer id = null;
		testList.add(testEntity);
		List<PersonDTO> resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(PersonDTO r:resultList){
			if(r.getComment().equals(name)){
				assertNotNull("Entity id remained null", r.getId());
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not added", testEntity);
		id = testEntity.getId();
		
		//Edit test
		name+="*";
		testEntity.setComment(name);
		testList = new ArrayList<PersonDTO>();
		testList.add(testEntity);
		resultList = RestManager.getInstance().saveToDatabase(testList);
		testEntity=null;
		for(PersonDTO r:resultList){
			if(r.getId().intValue()==id){
				testEntity=r;
				break;
			}
		}
		assertNotNull("Edited entity not found", testEntity);
		assertEquals("Entity name not edited", name, testEntity.getComment());
		
		//remove test
		int removed = RestManager.getInstance().removeFromDatabase(testList);
		assertEquals("Removed records number differs",1, removed);
		resultList = RestManager.getInstance().getContacts();
		for(PersonDTO r:resultList){
			if(r.getId().intValue()==id){
				fail("Entity with id="+id+" not removed");
				break;
			}
		}
	}
	
	
	public static void main(String[] args){
		login();
		/*
		RegionDTO r= new RegionDTO();
		r.setName("A AAA AAA1");
		r.setRep_id(5683);
		List<RegionDTO> list = new ArrayList<RegionDTO>();
		list.add(r);
		List<RegionDTO> result = RestManager.getInstance().saveToDatabase(list);
		System.out.println(result.get(0).toString()+result.get(0).getRep_id());
		*/
		RestTester o = new RestTester();
		o.storeRemoveEditAdverts();
		
		
	}
	
	
	
	

}
