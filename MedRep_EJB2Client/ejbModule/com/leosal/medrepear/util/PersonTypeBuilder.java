package com.leosal.medrepear.util;

import java.util.HashMap;
import java.util.Map;

import com.leosal.medrepear.dto.PersonTypeDTO;

public class PersonTypeBuilder {
	
	private static Map<Integer, PersonTypeDTO> types;
	
	public static PersonTypeDTO build(int personType){
		if (types==null) 
			types = new HashMap<Integer, PersonTypeDTO>();
		
		PersonTypeDTO pers = types.get(personType);
		if(pers!=null) return pers;
		switch(personType){
		case PersonTypeDTO.ADMINISTRATOR:
			pers = new PersonTypeDTO(1, "Administrator");
			break;
		case PersonTypeDTO.ADMINISTRATION:
			pers = new PersonTypeDTO(2, "Administratie");
			break;
		case PersonTypeDTO.REPRESENTATIVE:
			pers = new PersonTypeDTO(3, "Reprezentant");
			break;
		case PersonTypeDTO.EMPLOYEE:
			pers = new PersonTypeDTO(4, "Lucrator");
		case PersonTypeDTO.HOSPITAL_WORKER:
			pers = new PersonTypeDTO(5, "Lucrator in spital");
			break;
		case PersonTypeDTO.PHARMACY_WORKER:
			pers = new PersonTypeDTO(6, "Lucrator in farmacie");
		default:
			pers = new PersonTypeDTO(7, "Altele");
			personType=7;
		}
		
		
		types.put(personType, pers);
		return pers;
	}

}
