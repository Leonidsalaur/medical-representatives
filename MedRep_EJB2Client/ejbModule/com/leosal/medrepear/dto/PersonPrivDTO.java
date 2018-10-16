package com.leosal.medrepear.dto;

import java.io.Serializable;



public class PersonPrivDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int  READ_ALL_CONTACTS = 			10;
	public static final int READ_PERSONAL_CONTACTS = 		20;
	public static final int CHANGE_ALL_CONTACTS = 			30;
	public static final int CHANGE_PERSONAL_CONTACTS = 		40;
	public static final int ADD_NEW_CONTACTS = 				50;
	
	public static final int CHANGE_PERSONAL_EVENTS = 		60;
	public static final int READ_PERSONAL_EVENTS =			65;
	public static final int CHANGE_ALL_EVENTS = 			70;
	public static final int READ_ALL_EVENTS = 				80;
	
	public static final int READ_CATALOGS = 				90;
	public static final int CHANGE_CATALOGS = 				100;
	public static final int ADD_TO_CATALOGS = 				110;
	
	public static final int MODIFY_PRIVILEGIES = 			120;
	
	public static final int ADD_GROUP_EVENTS =				130;
	public static final int CHANGE_GROUP_EVENTS = 			140;
	
	public static final int READ_ALL_REGIONS = 				150;
	public static final int MODIFY_REGIONS = 				160;
	
	public static final int DATABASE_ADMIN = 				1013;
	
	
	public static final int[] STANDARD_USER_PRIVS = new int[]{
		READ_PERSONAL_CONTACTS,
		CHANGE_PERSONAL_CONTACTS,
		READ_PERSONAL_EVENTS,
		CHANGE_PERSONAL_EVENTS
	};
	

	private Integer id;
	private int privId;


	public PersonPrivDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPrivId() {
		return this.privId;
	}

	public void setPrivId(int privId) {
		this.privId = privId;
	}
	
	

	
}
