package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.ProductDTO;

@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="product")
	private List<ProductDTO> list;
	
	public ProductListDTO(){
		
	}
	public ProductListDTO(List<ProductDTO> list){
		this.list=list;
	}
	public List<ProductDTO> getList() {
		return list;
	}
	public void setList(List<ProductDTO> list) {
		this.list = list;
	}
	

}
