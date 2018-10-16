package com.leosal.leotools.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.beansbinding.Converter;

public class DateToString extends Converter<Date, String> {

	@Override
	public String convertForward(Date arg0) {
		if(arg0==null) return null;
		return new SimpleDateFormat("dd/MM/yyyy").format(arg0);
	}

	@Override
	public Date convertReverse(String arg0) {
		if(arg0==null) return null;
		try {
			return new SimpleDateFormat(arg0).parse(arg0);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
