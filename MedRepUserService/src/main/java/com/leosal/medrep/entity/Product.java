package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements DBEntity, Serializable,
		Comparable<Product>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="bar_code")
	private Long barCode; //fixme change type to String

	private String name;

	private float price;

	//bi-directional many-to-one association to DistribProd
	@OneToMany(mappedBy="product")
	private Set<DistribProd> distribProds;

	//bi-directional many-to-one association to EventProduct
	@OneToMany(mappedBy="product")
	private Set<EventProduct> eventProducts;

	//bi-directional many-to-one association to Sale
	@OneToMany(mappedBy="product")
	private Set<Sale> sales;

	public Product() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getBarCode() {
		return this.barCode;
	}

	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<DistribProd> getDistribProds() {
		return this.distribProds;
	}

	public void setDistribProds(Set<DistribProd> distribProds) {
		this.distribProds = distribProds;
	}

	public DistribProd addDistribProd(DistribProd distribProd) {
		getDistribProds().add(distribProd);
		distribProd.setProduct(this);

		return distribProd;
	}

	public DistribProd removeDistribProd(DistribProd distribProd) {
		getDistribProds().remove(distribProd);
		distribProd.setProduct(null);

		return distribProd;
	}

	public Set<EventProduct> getEventProducts() {
		return this.eventProducts;
	}

	public void setEventProducts(Set<EventProduct> eventProducts) {
		this.eventProducts = eventProducts;
	}

	public EventProduct addEventProduct(EventProduct eventProduct) {
		getEventProducts().add(eventProduct);
		eventProduct.setProduct(this);

		return eventProduct;
	}

	public EventProduct removeEventProduct(EventProduct eventProduct) {
		getEventProducts().remove(eventProduct);
		eventProduct.setProduct(null);

		return eventProduct;
	}

	public Set<Sale> getSales() {
		return this.sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Sale addSale(Sale sale) {
		getSales().add(sale);
		sale.setProduct(this);

		return sale;
	}

	public Sale removeSale(Sale sale) {
		getSales().remove(sale);
		sale.setProduct(null);

		return sale;
	}
	
	@Override
	public int compareTo(Product o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId().intValue();
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