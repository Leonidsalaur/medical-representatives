package com.leosal.medrepear.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the distrib_instit database table.
 * 
 */
@XmlRootElement(name="distrib_instit")
@XmlAccessorType(XmlAccessType.FIELD)
public class DistribInstitDTO  implements Serializable,
	Comparable<DistribInstitDTO>{
	private static final long serialVersionUID = 1L;

	@XmlAttribute(name="id")
	private Integer id;
	
	@XmlAttribute(name="distrib_cod")
	private String distribCod;
	
	@XmlAttribute(name="instit_name")
	private String institName;
	
	@XmlElement(name="distributor")
	private InstitutionDTO distributor;
	
	@XmlElement(name="institution")
	private InstitutionDTO institution;

	public DistribInstitDTO() {
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getDistribCod() {
		return this.distribCod;
	}

	public void setDistribCod(String distribCod) {
		this.distribCod = distribCod;
	}

	
	public String getInstitName() {
		return this.institName;
	}

	public void setInstitName(String institName) {
		this.institName = institName;
	}

	
	@Override
	public int compareTo(DistribInstitDTO o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
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
        DistribInstitDTO e = (DistribInstitDTO) o;
        return (this.getId().equals(e.getId()));
	}


	public InstitutionDTO getDistributor() {
		return distributor;
	}

	public void setDistributor(InstitutionDTO distributor) {
		this.distributor = distributor;
	}

	
	public InstitutionDTO getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionDTO institution) {
		this.institution = institution;
	}

}