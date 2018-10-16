package com.leosal.leotools.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.jdesktop.beansbinding.Converter;

public class BigDecimalToPercent extends Converter<BigDecimal, String> {
	private static final DecimalFormat df =new DecimalFormat("#0.00%");
	@Override
	public String convertForward(BigDecimal arg0) {
		return arg0!=null?df.format(arg0):"0%";
	}

	@Override
	public BigDecimal convertReverse(String arg0) {
		
		try {
			return new BigDecimal(Float.parseFloat(arg0.replaceAll("%", ""))/100);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new BigDecimal(0);
	}

}
