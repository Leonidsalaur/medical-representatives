package com.leosal.leotools.tools;


import java.text.DecimalFormat;

import org.jdesktop.beansbinding.Converter;

public class IntegerToString extends Converter<Integer, String> {

	private String format;
	
	public IntegerToString(){
		format = "#0";
	}
	

	@Override
	public String convertForward(Integer arg0) {
		return new DecimalFormat(format).format(arg0!=null?arg0:0);
	}

	@Override
	public Integer convertReverse(String arg0) {
		
		try {
			return Integer.parseInt(arg0!=null?arg0:"0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
