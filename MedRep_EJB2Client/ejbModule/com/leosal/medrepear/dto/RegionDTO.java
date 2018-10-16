package com.leosal.medrepear.dto;

import java.io.Serializable;


public class RegionDTO extends AbstractModelObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer rep_id;
	private String rep_name;

	public RegionDTO() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		
		this.firePropertyChange("id", this.id, id);
		this.id = id;
	}

	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		this.firePropertyChange("name", this.name, name);
		this.name = name;
	}

	public Integer getRep_id() {
		return rep_id;
	}

	public void setRep_id(Integer rep_id) {
		this.firePropertyChange("rep_id", this.rep_id, rep_id);
		this.rep_id = rep_id;
	}

	public String getRep_name() {
		return rep_name;
	}

	public void setRep_name(String rep_name) {
		this.firePropertyChange("rep_name", this.rep_name, rep_name);
		this.rep_name = rep_name;
	}
	@Override
	public String toString(){
		return getName()+" ("+getRep_name()+")";
	}

	
}
