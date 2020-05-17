package com.leosal.medrep.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the institutions database table.
 * 
 */
@Entity
@Table(name="institutions")
@NamedQuery(name="Institution.findAll", query="SELECT i FROM Institution i")
public class Institution implements DBEntity, Serializable,
		Comparable<Institution>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private byte active;

	private Long code; //FIXME change filed type to string

	private byte distributor;

	private String name;

	//bi-directional many-to-one association to DistribInstit
	@OneToMany(mappedBy="institution1")
	private Set<DistribInstit> distribInstits1;

	//bi-directional many-to-one association to DistribInstit
	@OneToMany(mappedBy="institution2")
	private Set<DistribInstit> distribInstits2;

	//bi-directional many-to-one association to DistribProd
	@OneToMany(mappedBy="institution")
	private Set<DistribProd> distribProds;

	//bi-directional many-to-many association to Person
	@ManyToMany(mappedBy="institutions")
	private Set<Person> persons;

	//bi-directional many-to-one association to InstitType
	@ManyToOne
	@JoinColumn(name="inst_type_id")
	private InstitType institType;

	//bi-directional many-to-one association to Region
	@ManyToOne
	private Region region;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="institution")
	private Set<Report> reports;

	//bi-directional many-to-one association to Sale
	@OneToMany(mappedBy="institution")
	private Set<Sale> sales;

	public Institution() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Long getCode() {
		return this.code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public byte getDistributor() {
		return this.distributor;
	}

	public void setDistributor(byte distributor) {
		this.distributor = distributor;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DistribInstit> getDistribInstits1() {
		return this.distribInstits1;
	}

	public void setDistribInstits1(Set<DistribInstit> distribInstits1) {
		this.distribInstits1 = distribInstits1;
	}

	public DistribInstit addDistribInstits1(DistribInstit distribInstits1) {
		getDistribInstits1().add(distribInstits1);
		distribInstits1.setInstitution1(this);

		return distribInstits1;
	}

	public DistribInstit removeDistribInstits1(DistribInstit distribInstits1) {
		getDistribInstits1().remove(distribInstits1);
		distribInstits1.setInstitution1(null);

		return distribInstits1;
	}

	public Set<DistribInstit> getDistribInstits2() {
		return this.distribInstits2;
	}

	public void setDistribInstits2(Set<DistribInstit> distribInstits2) {
		this.distribInstits2 = distribInstits2;
	}

	public DistribInstit addDistribInstits2(DistribInstit distribInstits2) {
		getDistribInstits2().add(distribInstits2);
		distribInstits2.setInstitution2(this);

		return distribInstits2;
	}

	public DistribInstit removeDistribInstits2(DistribInstit distribInstits2) {
		getDistribInstits2().remove(distribInstits2);
		distribInstits2.setInstitution2(null);

		return distribInstits2;
	}

	public Set<DistribProd> getDistribProds() {
		return this.distribProds;
	}

	public void setDistribProds(Set<DistribProd> distribProds) {
		this.distribProds = distribProds;
	}

	public DistribProd addDistribProd(DistribProd distribProd) {
		getDistribProds().add(distribProd);
		distribProd.setInstitution(this);

		return distribProd;
	}

	public DistribProd removeDistribProd(DistribProd distribProd) {
		getDistribProds().remove(distribProd);
		distribProd.setInstitution(null);

		return distribProd;
	}

	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public InstitType getInstitType() {
		return this.institType;
	}

	public void setInstitType(InstitType institType) {
		this.institType = institType;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setInstitution(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setInstitution(null);

		return report;
	}

	public Set<Sale> getSales() {
		return this.sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Sale addSale(Sale sale) {
		getSales().add(sale);
		sale.setInstitution(this);

		return sale;
	}

	public Sale removeSale(Sale sale) {
		getSales().remove(sale);
		sale.setInstitution(null);

		return sale;
	}
	
	@Override
	public int compareTo(Institution o) {
		
		return this.getName().compareToIgnoreCase(o.getName());
	}
	
	@Override
	 public int hashCode()
	 {
		//System.out.println("Institution name: "+getName()+"\nInstitution id: "+getId());
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()==null?
	    		getName().hashCode()
	    		:getName().hashCode()+getId()));
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
        DBEntity e = (DBEntity) o;
        return (this.getId().equals(e.getId()));
	}

}