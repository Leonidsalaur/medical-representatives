package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the reports database table.
 * 
 */
@Entity
@Table(name="reports")
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<Report>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Lob
	private String comment;

	@Temporal(TemporalType.DATE)
	@Column(name="per_end")
	private Date perEnd;

	@Temporal(TemporalType.DATE)
	@Column(name="per_start")
	private Date perStart;

	//bi-directional many-to-one association to Institution
	@ManyToOne
	@JoinColumn(name="distr_id")
	private Institution institution;

	//bi-directional many-to-one association to Sale
	@OneToMany(mappedBy="report", cascade=CascadeType.ALL)
	private Set<Sale> sales;

	public Report() {
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

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Set<Sale> getSales() {
		return this.sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Sale addSale(Sale sale) {
		if(getSales()==null)
			setSales(new HashSet<Sale>());
		getSales().add(sale);
		sale.setReport(this);

		return sale;
	}

	public Sale removeSale(Sale sale) {
		getSales().remove(sale);
		sale.setReport(null);

		return sale;
	}
	
	@Override
	public int compareTo(Report o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + (getId()==null?0:getId())+this.getInstitution().hashCode()+this.getPerEnd().hashCode()+this.getPerStart().hashCode();
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
        com.leosal.util.jpa.Entity e = (com.leosal.util.jpa.Entity) o;
        return (this.getId().equals(e.getId()));
	}

}