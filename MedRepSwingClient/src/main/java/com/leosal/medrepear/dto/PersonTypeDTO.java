package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.util.ArrayList;



public class PersonTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int ADMINISTRATOR = 1;
	public static final int ADMINISTRATION = 2;
	public static final int REPRESENTATIVE = 3;
	public static final int EMPLOYEE = 4;
	public static final int HOSPITAL_WORKER = 5;
	public static final int PHARMACY_WORKER = 6;
	public static final int OTHER = 7;
	
	
	
	private static ArrayList<PersonTypeDTO> list=null;
	
	private Integer id;

	private String name;


	public PersonTypeDTO() {
	}
	public PersonTypeDTO(int id, String name){
		this.id = id;
		this.name=name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
