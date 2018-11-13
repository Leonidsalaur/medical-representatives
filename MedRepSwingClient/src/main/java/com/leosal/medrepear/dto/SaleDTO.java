package com.leosal.medrepear.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlAttribute(name="id")
	private Integer id;

	@XmlElement(name="quantity")
	private float quant;

	@XmlElement
	private InstitutionDTO institution;

	@XmlElement
	private ProductDTO product;
	
	@XmlTransient
	private ReportDTO report;

	
	//private ReportDTO report;

	public SaleDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getQuant() {
		return this.quant;
	}

	public void setQuant(float quant) {
		this.quant = quant;
	}

	public InstitutionDTO getInstitution() {
		return this.institution;
	}

	public void setInstitution(InstitutionDTO institution) {
		this.institution = institution;
	}

	public ProductDTO getProduct() {
		return this.product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
/*
	public ReportDTO getReport() {
		return this.report;
	}

	public void setReport(ReportDTO report) {
		this.report = report;
	}
*/

	public ReportDTO getReport() {
		return report;
	}

	public void setReport(ReportDTO report) {
		this.report = report;
	}
}
