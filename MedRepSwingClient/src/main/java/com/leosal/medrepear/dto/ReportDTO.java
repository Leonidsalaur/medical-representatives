package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReportDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private Integer id;


	private String comment;

	
	private Date perEnd;

	
	private Date perStart;

	
	private InstitutionDTO distributor;

	
	private Set<SaleDTO> sales;

	public ReportDTO() {
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

	public Date getPerEnd() {
		return this.perEnd;
	}

	public void setPerEnd(Date perEnd) {
		this.perEnd = perEnd;
	}

	public Date getPerStart() {
		return this.perStart;
	}

	public void setPerStart(Date perStart) {
		this.perStart = perStart;
	}

	

	public Set<SaleDTO> getSales() {
		if(sales==null) sales = new HashSet<SaleDTO>();
		return this.sales;
	}

	public void setSales(Set<SaleDTO> sales) {
		this.sales = sales;
	}

	public SaleDTO addSale(SaleDTO sale) {
		getSales().add(sale);
		//sale.setReport(this);

		return sale;
	}

	public SaleDTO removeSale(SaleDTO sale) {
		getSales().remove(sale);
		//sale.setReport(null);

		return sale;
	}

	public InstitutionDTO getDistributor() {
		return distributor;
	}

	public void setDistributor(InstitutionDTO distributor) {
		this.distributor = distributor;
	}
}
