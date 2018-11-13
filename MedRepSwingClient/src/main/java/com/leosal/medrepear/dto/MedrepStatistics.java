package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Leonid
 *
 */
public class MedrepStatistics implements Serializable{
	
	private MedrepFilter filter;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MedrepStatistics parent;
	private List<MedrepStatistics> nextLevel;
	private List<EventDTO> events;
	private boolean finalLevel=true;
	
	//Statistics fields
	private int totalDays, totalReps, totalContacts, totalOrganisations;
	private int totalGrowth, totalSafe, totalMonitor, totalIgnore;
	private int totalVisits;
	private int averagePerDay;
	private String name;
	
	public MedrepStatistics() {
		
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public int getTotalReps() {
		return totalReps;
	}
	public void setTotalReps(int totalReps) {
		this.totalReps = totalReps;
	}
	public int getTotalContacts() {
		return totalContacts;
	}
	public void setTotalContacts(int totalContacts) {
		this.totalContacts = totalContacts;
	}
	public int getTotalOrganisations() {
		return totalOrganisations;
	}
	public void setTotalOrganisations(int totalOrganisations) {
		this.totalOrganisations = totalOrganisations;
	}
	public int getTotalGrowth() {
		return totalGrowth;
	}
	public void setTotalGrowth(int totalGrowth) {
		this.totalGrowth = totalGrowth;
	}
	public int getTotalSafe() {
		return totalSafe;
	}
	public void setTotalSafe(int totalSafe) {
		this.totalSafe = totalSafe;
	}
	public int getTotalMonitor() {
		return totalMonitor;
	}
	public void setTotalMonitor(int totalMonitor) {
		this.totalMonitor = totalMonitor;
	}
	public int getTotalIgnore() {
		return totalIgnore;
	}
	public void setTotalIgnore(int totalIgnore) {
		this.totalIgnore = totalIgnore;
	}
	public int getTotalVisits() {
		return totalVisits;
	}
	public void setTotalVisits(int totalVisits) {
		this.totalVisits = totalVisits;
	}
	public int getAveragePerDay() {
		return averagePerDay;
	}
	public void setAveragePerDay(int averagePerDay) {
		this.averagePerDay = averagePerDay;
	}
	public List<MedrepStatistics> getNextLevel() {
		return nextLevel;
	}
	public void setNextLevel(List<MedrepStatistics> nextLevel) throws UnsupportedOperationException {
		if(events!=null) throw new UnsupportedOperationException("This is a final level statistics");
		this.nextLevel = nextLevel;
		for(MedrepStatistics ms:nextLevel)
			ms.setParent(this);
		finalLevel=false;
		if(nextLevel.size()>0) refreshStatistics();
	}
	public void addStatistics(MedrepStatistics st) throws UnsupportedOperationException{
		if(events!=null) throw new UnsupportedOperationException("This is a final level statistics");
		if(nextLevel==null) nextLevel=new ArrayList<MedrepStatistics>();
		nextLevel.add(st);
		finalLevel=false;
		refreshStatistics();
	}
	
	
	public List<EventDTO> getEvents() {
		if(!finalLevel) {
			List<EventDTO> result = new ArrayList<EventDTO>();
			if(nextLevel!=null){
				for(MedrepStatistics st:nextLevel)
					if(st!=null&&st.getEvents()!=null)result.addAll(st.getEvents());
			}
			return result;
		}
		if(this.filter==null || this.events == null)
			return events;
		else{
			List<EventDTO> result = new ArrayList<EventDTO>();
			for(EventDTO ev:events){
				if(filter.matches(ev)) result.add(ev);
			}
			return result;
		}
			
		
	}
	public void setEvents(List<EventDTO> events) throws UnsupportedOperationException {
		if(nextLevel!=null) throw new UnsupportedOperationException("This is not a final level statistics");
		this.events = events;
		if(events.size()>0) refreshStatistics();
	}
	
	/**
	 * @param ev
	 * @throws UnsupportedOperationException
	 */
	public void addEvent(EventDTO ev) throws UnsupportedOperationException{
		if(nextLevel!=null) throw new UnsupportedOperationException("This is not a final level statistics");
		if(events==null) events=new ArrayList<EventDTO>();
		events.add(ev);
		refreshStatistics();
	}
	public String getName() {
		if(name==null) name="";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private void refreshStatistics(){
		List<EventDTO> set = getEvents();
		
		if(set==null) return;
		
		HashSet<Object> dates = new HashSet<Object>();
		HashSet<Object> contacts = new HashSet<Object>();
		HashSet<Object> reps = new HashSet<Object>();
		HashSet<Object> orgs = new HashSet<Object>();
		//HashSet<Object> orgs = new HashSet<Object>();
		HashMap<String, Integer> gsmi = new HashMap<String, Integer>();
		totalVisits=0;
		for(EventDTO ev:set){
			dates.add(new SimpleDateFormat("dd/MM/yyyy").format(ev.getDate()));
			for(EventPersonDTO pers:ev.getParticipants()){
				totalVisits++;
				contacts.add(pers.getPerson().getId());
				if(pers.getPerson().getInstitutions()!=null)
					for(InstitutionDTO in:pers.getPerson().getInstitutions())
						orgs.add(in.getId());
				String cat = pers.getPerson().getCategory();
				int count = gsmi.get(cat)!=null?gsmi.get(cat):0;
				gsmi.put(cat, count+1);
			}
			reps.add(ev.getInitiator().getId());
			
		}
		totalContacts = contacts.size();
		totalDays = dates.size();
		totalReps = reps.size();
		totalOrganisations = orgs.size();
		
		if(totalDays>0&&totalReps>0)
			averagePerDay = totalVisits/(totalDays*totalReps);
		else
			averagePerDay = 0;
		totalGrowth = (gsmi.get("G")!=null?gsmi.get("G"):0);
		totalSafe = (gsmi.get("S")!=null?gsmi.get("S"):0);
		totalMonitor = (gsmi.get("M")!=null?gsmi.get("M"):0);
		totalIgnore = (gsmi.get("I")!=null?gsmi.get("I"):0);
		//this.printInfo();
		if(this.parent!=null){
			this.parent.refreshStatistics();
		}
	}
	
	public static List<MedrepStatistics> generate(List<EventDTO> set){
		if(set==null) return null;
		List<MedrepStatistics> general = new ArrayList<MedrepStatistics>();
		Collections.sort(set, new Comparator<EventDTO>() {

			@Override
			public int compare(EventDTO o1, EventDTO o2) {
				return -o1.getDate().compareTo(o2.getDate());
			}
		});
		int curDay=-1, curMonth=-1,curYear=-1;
		MedrepStatistics year=null,month=null,day=null;
		for(EventDTO ev:set){
			Calendar d = new GregorianCalendar();
			d.setTime(ev.getDate());
			if(curYear!=d.get(Calendar.YEAR)){
				year = new MedrepStatistics();
				year.setName(""+d.get(Calendar.YEAR)+" year");
				general.add(year);
				curYear = d.get(Calendar.YEAR);
				
			}
			if(curMonth!=d.get(Calendar.MONTH)){
				month = new MedrepStatistics();
				month.setName("");
				switch(d.get(Calendar.MONTH)){
				case Calendar.JANUARY:
					month.setName("January");break;
				case Calendar.FEBRUARY:
					month.setName("February");break;
				case Calendar.MARCH:
					month.setName("March");break;
				case Calendar.APRIL:
					month.setName("April");break;
				case Calendar.MAY:
					month.setName("May");break;
				case Calendar.JUNE:
					month.setName("June");break;
				case Calendar.JULY:
					month.setName("July");break;
				case Calendar.AUGUST:
					month.setName("August");break;
				case Calendar.SEPTEMBER:
					month.setName("September");break;
				case Calendar.OCTOBER:
					month.setName("October");break;
				case Calendar.NOVEMBER:
					month.setName("November");break;
				case Calendar.DECEMBER:
					month.setName("December");break;
				}
				try {
					year.addStatistics(month);
					month.setParent(year);
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
				}
				curMonth=d.get(Calendar.MONTH);
				
			}
			if(curDay!=d.get(Calendar.DAY_OF_MONTH)){
				day = new MedrepStatistics();
				
				String dow="";
				switch(d.get(Calendar.DAY_OF_WEEK)){
				case Calendar.MONDAY:
					dow="Mon"; break;
				case Calendar.TUESDAY:
					dow="Tue"; break;
				case Calendar.WEDNESDAY:
					dow="Wed"; break;
				case Calendar.THURSDAY:
					dow="Thu"; break;
				case Calendar.FRIDAY:
					dow="Fri"; break;
				case Calendar.SATURDAY:
					dow="Sat"; break;
				case Calendar.SUNDAY:
					dow="Sun"; break;
				}
				day.setName(""+d.get(Calendar.DAY_OF_MONTH)+" ("+dow+")");
				try {
					month.addStatistics(day);
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
				}
				curDay=d.get(Calendar.DAY_OF_MONTH);
			}
			try {
				day.addEvent(ev);
				day.setParent(month);
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
			
			
		}
		return general;
	}
	public MedrepStatistics getParent() {
		return parent;
	}
	public void setParent(MedrepStatistics parent) {
		this.parent = parent;
	}
	public MedrepFilter getFilter() {
		return filter;
	}
	public void setFilter(MedrepFilter filter) {
		if(!finalLevel && nextLevel!=null){
			for(MedrepStatistics st:nextLevel)
				st.setFilter(filter);
		}else{
			this.filter = filter;
			refreshStatistics();
		}
	}
	
	public void printInfo(){
		
		StringBuilder info = new StringBuilder(name+" :: ");
		info.append("Reps ["+totalReps+"] ");
		info.append("Avg ["+averagePerDay+"] ");
		info.append("Days ["+totalDays+"] ");
		info.append("Contacts ["+totalContacts+"] ");
		info.append("Visits ["+totalVisits+"] ");
		info.append("Orgs ["+totalOrganisations+"] ");
		info.append("G ["+totalGrowth+"] ");
		info.append("S ["+totalSafe+"] ");
		info.append("M ["+totalMonitor+"] ");
		info.append("I ["+totalIgnore+"] ");
		System.out.println(info);
	}
	

}
