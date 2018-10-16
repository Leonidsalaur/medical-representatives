package com.leosal.leotools.tools;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.jdesktop.beansbinding.Converter;

public class FloatToPercent extends Converter<Float, String> {
	private static final DecimalFormat df =new DecimalFormat("#0%");

	@Override
	public String convertForward(Float arg0) {
		
		return arg0!=null?df.format(arg0):"0%";
	}

	@Override
	public Float convertReverse(String arg0) {
		try {
			Float.parseFloat(arg0.replaceAll("%", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

}
