package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.ReportDTO;

@XmlRootElement(name="reports")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="report")
	private List<ReportDTO> list;
	
	public ReportListDTO(){
		
	}
	public ReportListDTO(List<ReportDTO> list){
		this.list=list;
	}
	public List<ReportDTO> getList() {
		return list;
	}
	public void setList(List<ReportDTO> list) {
		this.list = list;
	}
	

}
