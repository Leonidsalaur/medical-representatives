package com.leosal.leotools.tools;


import java.text.DecimalFormat;
import java.text.ParseException;

import org.jdesktop.beansbinding.Converter;

public class NumberPrecision extends Converter<Number, String> {
	private int decimals;
	private String format;
	
	public NumberPrecision(){
		this(2);
	}
	public NumberPrecision(int decimals){
		this.decimals=Math.max(decimals, 0);
		format = "#0"+(this.decimals>0?".":"");
		for(int i=0;i<this.decimals;i++)
			format+="0";
	}

	@Override
	public String convertForward(Number arg0) {
		return new DecimalFormat(format).format(arg0!=null?arg0:0);
	}

	@Override
	public Number convertReverse(String arg0) {
		
		try {
			return new DecimalFormat(format).parse(arg0!=null?arg0:"0");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0f;
	}

}
