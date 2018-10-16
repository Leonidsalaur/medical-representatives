package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import com.leosal.medrepear.service.RestManager;




public class EventDTO implements Serializable,
		Comparable<EventDTO>{
	private static final long serialVersionUID = 1L;
	private static final Preferences prefs = Preferences.userNodeForPackage(com.leosal.medrepear.dto.EventDTO.class);
	private static final String DEFAULT_DATE = "DEFAULT_DATE";
	@SuppressWarnings("unused")
	private static final String DEFAULT_SINGLE_TYPE = "DEFAULT_SINGLE_TYPE";
	@SuppressWarnings("unused")
	private static final String DEFAULT_GROUP_TYPE = "DEFAULT_GROUP_TYPE";
	

	private Integer id;


	private String comment;


	private Date date;


	private String message;


	//private Set<EventAdvert> eventAdverts;
	
	//private PersonDTO firstContact;
	
	
	

	private List<EventGiftDTO> eventGifts;
	private List<EventPersonDTO> participants;
	private List<EventProductDTO> eventProducts;
	private PersonDTO initiator;
	private EventTypeDTO eventType;

	public EventDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<EventGiftDTO> getGifts() {
		return eventGifts;
	}

	public void setGifts(List<EventGiftDTO> eventGifts) {
		this.eventGifts = eventGifts;
	}
	
	public boolean addGift(EventGiftDTO eg){
		if(eventGifts==null)eventGifts=new ArrayList<EventGiftDTO>();
		return eventGifts.add(eg);
	}
	
	public boolean removeGift(EventGiftDTO adv){
		if(eventGifts==null) return true;
		return eventGifts.remove(adv);
	}
	
	public boolean containsGift(AdvertDTO adv){
		if(eventGifts==null) return false;
		for(EventGiftDTO eg:eventGifts)
			if(eg.getAdvert().equals(adv)) return true;
		return false;
	}

	public List<EventPersonDTO> getParticipants() {
		return participants;
	}

	public void setParticipants(List<EventPersonDTO> participants) {
		this.participants = participants;
	}
	
	public boolean addParticipant(EventPersonDTO p){
		if(participants==null) participants=new ArrayList<EventPersonDTO>();
		return participants.add(p);
	}
	
	public boolean removeParticipant(EventPersonDTO p){
		if(participants==null) return true;
		return participants.remove(p);
	}
	
	public boolean containsParticipant(PersonDTO pers){
		if(participants==null) return false;
		for(EventPersonDTO ep: participants)
			if(ep.getPerson().equals(pers)) return true;
		return false;
	}

	public List<EventProductDTO> getProducts() {
		return eventProducts;
	}

	public void setProducts(List<EventProductDTO> eventProducts) {
		this.eventProducts = eventProducts;
	}
	
	public boolean addProduct(EventProductDTO pr){
		if(eventProducts==null) eventProducts=new ArrayList<EventProductDTO>();
		return eventProducts.add(pr);
	}
	
	public boolean removeProduct(EventProductDTO pr){
		if(eventProducts==null) return true;
		return eventProducts.remove(pr);
	}
	
	public boolean containsProduct(ProductDTO pr){
		if(eventProducts==null) return false;
		for(EventProductDTO ep:eventProducts)
			if(ep.getProduct().equals(pr)) return true;
		return false;
	}

	public PersonDTO getInitiator() {
		return initiator;
	}

	public void setInitiator(PersonDTO initiator) {
		this.initiator = initiator;
	}

	public EventTypeDTO getEventType() {
		return eventType;
	}

	public void setEventType(EventTypeDTO eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public int compareTo(EventDTO o) {
		if(this.getId()==null && o.getId()==null){
			return 0;
		}
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + (getId()==null?0:getId());
	    return result;
	 }
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        EventDTO e = (EventDTO) o;
        if(this.getId()!=null)
        	return (this.getId().equals(e.getId()));
        
        return this==e;
	}

	/**Used for table binding
	 * @return first contact in participants list
	 */
	public PersonDTO getFirstContact() {
		if(participants!=null && participants.size()>0)
			return participants.iterator().next().getPerson();
		return null;
	}
	
	public boolean save(){
		List<EventDTO>  list=new ArrayList<EventDTO>();
		if(!this.getEventType().isGroupEvent()){
			for(EventPersonDTO pp:this.getParticipants()){
				EventDTO ee = new EventDTO();
				ee.setComment(this.getComment());
				ee.setDate(this.getDate());
				ee.setEventType(this.getEventType());
				ee.setGifts(this.getGifts());
				//System.out.println("Individual event: "+ee.getGifts().get(0).getAdvert().getName());
				ee.setId(this.getId());
				ee.setInitiator(this.getInitiator());
				ee.setMessage(this.getMessage());
				ee.setProducts(this.getProducts());
				
				List<EventPersonDTO> l = new ArrayList<EventPersonDTO>();
				l.add(pp);
				ee.setParticipants(l);
				list.add(ee);
			}
		}else{
			//System.out.println("Group event: "+event.getGifts().get(0).getId());
			list.add(this);
		}
		//ConnectionManager.getInstance().storeDTOs(list);
		RestManager.getInstance().saveToDatabase(list);
		return true;
	}
	
	public static EventDTO getDefaultEvent(){
		EventDTO ev = new EventDTO();
		ev.setInitiator(RestManager.getInstance().currentUser());
		ev.setDate(new Date(prefs.getLong(DEFAULT_DATE, new Date().getTime())));
		
		return ev;
	}

}
