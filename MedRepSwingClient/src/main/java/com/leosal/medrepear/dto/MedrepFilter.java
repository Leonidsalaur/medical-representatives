/**Contains filter data to extract statistics
 * 
 */
package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leonid
 *
 */
public class MedrepFilter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1764500301827478573L;
		//Filter fields
		private Date dateFrom, dateTo;
		private ContactDTO rep;
		private String category, contact, message;
		private String institution;
		private SpecialtyDTO specialty;
		
		public MedrepFilter() {
			this.dateFrom = null;
			this.dateTo = null;
			this.rep = null;
			this.category = null;
			this.contact = null;
			this.message = null;
			this.institution = null;
			this.specialty = null;
		}

		public Date getDateFrom() {
			return dateFrom;
		}

		public void setDateFrom(Date dateFrom) {
			this.dateFrom = dateFrom;
		}

		public Date getDateTo() {
			return dateTo;
		}

		public void setDateTo(Date dateTo) {
			this.dateTo = dateTo;
		}

		public ContactDTO getRep() {
			return rep;
		}

		public void setRep(ContactDTO rep) {
			this.rep = rep;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getInstitution() {
			return institution;
		}

		public void setInstitution(String institution) {
			this.institution = institution;
		}

		public SpecialtyDTO getSpecialty() {
			return specialty;
		}

		public void setSpecialty(SpecialtyDTO specialty) {
			this.specialty = specialty;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public boolean matches(EventDTO ev){
			
			if(this.category!=null&&!this.category.equals(ev.getFirstContact().getCategory()))
				return false;
			if(this.contact!=null && !ev.getFirstContact().toString().toLowerCase().contains(contact.toLowerCase()))
				return false;
			if(this.dateFrom!=null && ev.getDate().before(dateFrom))
				return false;
			if(this.dateTo!=null && ev.getDate().after(dateTo))
				return false;
			if(this.message!=null && ev.getMessage()!=null && !ev.getMessage().toLowerCase().contains(message.toLowerCase()))
				return false;
			if(this.rep!=null && !ev.getInitiator().getId().equals(rep.getId()))
				return false;
			if(this.specialty!=null && !ev.getFirstContact().getSpecialty1().getId().equals(specialty.getId()))
				return false;
			if(this.institution!=null && 
					!ev.getFirstContact().getInstitutions().toString().toLowerCase().contains(institution.toLowerCase()))
				return false;
			return true;
		}
	

}
