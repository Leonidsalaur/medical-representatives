/**
 * 
 */
package com.leosal.medrepear.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.leosal.medrepear.dto.ReportDTO;
import com.leosal.medrepear.dto.SaleDTO;

/**
 * @author Leonid
 *
 */
public class SalesReportDataItem {
	public static final String DATE="Date", 
			INSTITUTION="Institution", 
			PRODUCT="Product", 
			DISTRIBUTOR="Distributor",
			COMMENT="Comment", 
			REPRESENTATIVE="Representative", 
			REGION="Region", 
			QUANTITY="Quantity", 
			PRICE="Price", 
			COST="Cost";
	private static final String[] groupFields = new String[]{
		REPRESENTATIVE, 
		REGION,
		DISTRIBUTOR,
		INSTITUTION, 
		PRODUCT, 
		PRICE,
		COMMENT
	};
	private Date date;
	private String institution, product, distributor, comment, rep, region;
	private float quantity, price;

	/**
	 * 
	 */
	public SalesReportDataItem() {
		
	}
	public static String[] getAvailableGroupFields(){
		return groupFields;
	}
	public Object getField(String fieldName){

		if(fieldName.equals(DATE)) return getDate();
		else if(fieldName.equals(INSTITUTION))return getInstitution();
		else if(fieldName.equals(PRODUCT))return getProduct();
		else if(fieldName.equals(DISTRIBUTOR)) return getDistributor();
		else if(fieldName.equals(COMMENT)) return getComment();
		else if(fieldName.equals(REPRESENTATIVE)) return getRep();
		else if(fieldName.equals(REGION)) return getRegion();
		else if(fieldName.equals(QUANTITY)) return getQuantity();
		else if(fieldName.equals(PRICE)) return getPrice();
		else if(fieldName.equals(COST)) return getCost();
		else return null;
		
	}
	/*
	public static List<SalesReportDataItem> union(Set<ReportDTO> ... sales){
		if(sales==null) return null;
		List<SalesReportDataItem> list = new ArrayList<SalesReportDataItem>();
		for(Set<ReportDTO> ss:sales){
			List<SalesReportDataItem> l = build(ss);
			list.addAll(l);
		}
		
		return list;
	}
	*/
	public static List<SalesReportDataItem> union(List<ReportDTO> reps, Date from, Date to){
		if(reps==null) return null;
		List<SalesReportDataItem> list = new ArrayList<SalesReportDataItem>();
		for(ReportDTO rr:reps){
			list.addAll(build(rr, from, to));
		}
		return list;
	}
	public static List<SalesReportDataItem> build(Set<SaleDTO> sales, Date from, Date to){
		if(sales==null) return null;
		List<SalesReportDataItem> list = new ArrayList<SalesReportDataItem>();
		for(SaleDTO s:sales){
			float coef = 0;
			Date cc = s.getReport().getPerStart();
			if(!from.before(s.getReport().getPerStart())
					&&!to.after(s.getReport().getPerEnd())){
				coef=-(to.getTime()-from.getTime())*1f
					/ (s.getReport().getPerEnd().getTime()-s.getReport().getPerStart().getTime());
				cc = from;
			}
			else if(!from.before(s.getReport().getPerStart())
					&&!from.after(s.getReport().getPerEnd())){
				coef=-(from.getTime()-s.getReport().getPerEnd().getTime())*1f
						/ (s.getReport().getPerEnd().getTime()-s.getReport().getPerStart().getTime());
				cc = from;
			}
			else if(!to.before(s.getReport().getPerStart())
					&&!to.after(s.getReport().getPerEnd())){
				coef=-(to.getTime()-s.getReport().getPerStart().getTime())*1f
					/ (s.getReport().getPerEnd().getTime()-s.getReport().getPerStart().getTime());
				cc=to;
			}
			SalesReportDataItem srd = new SalesReportDataItem();
			srd.setComment(s.getReport().getComment());
			srd.setDate(cc);
			srd.setDistributor(s.getReport().getDistributor().getName());
			srd.setInstitution(s.getInstitution().getName());
			srd.setProduct(s.getProduct().getName());
			srd.setQuantity(s.getQuant()*coef);
			srd.setPrice(s.getProduct().getPrice()); 
			srd.setRegion(s.getInstitution().getRegion().getName());
			srd.setRep(s.getInstitution().getRegion().getRep_name());
			list.add(srd);
		}
		return list;
	}
	public static List<SalesReportDataItem> build(ReportDTO rep, Date from, Date to){
		if(rep==null) return null;
		return build(rep.getSales(), from, to);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString(){
		return "["+new SimpleDateFormat("dd/MM/yyyy").format(date)
				+"\t"+this.distributor+":\t"
				+this.institution + "\t"
				+this.product +"\t"+this.quantity+"\t"
				+(this.region!=null?this.region:"")+"\t"+(this.rep!=null?this.rep:"")
				+"]\n";
				
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getCost(){
		return price*quantity;
	}

}
