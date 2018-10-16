package com.leosal.medrepear.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

public class ApplicationManager {
	private static final Preferences prefs = 
			Preferences.userNodeForPackage(com.leosal.medrepear.service.ApplicationManager.class);
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final boolean TEST_MODE= true;
	private static final String LAST_EVENT_DATE = "LAST_EVENT_DATE";
	private static final String LAST_EVENT_TYPE = "LAST_EVENT_TYPE";

	public ApplicationManager() {
	}
	
	public static void printTest(Object ob){
		if(!TEST_MODE) return;
		System.out.println(ob);
	}
	
	public static boolean isTestMode(){
		return TEST_MODE;
	}
	
	public static Date getEventDate(){
		String c = prefs.get(LAST_EVENT_DATE, DATE_FORMAT.format(new Date()));
		try {
			return DATE_FORMAT.parse(c);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static void setEventDate(Date d){
		prefs.put(LAST_EVENT_DATE, DATE_FORMAT.format(d));
	}
	
	public static int getEventType(){
		String c = prefs.get(LAST_EVENT_TYPE, "0");
		return Integer.parseInt(c);
	}
	
	public static void setEventType(int index){
		prefs.put(LAST_EVENT_TYPE, ""+index);
	}

}
