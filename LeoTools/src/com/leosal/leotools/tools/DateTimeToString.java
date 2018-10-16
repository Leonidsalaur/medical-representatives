package com.leosal.leotools.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.beansbinding.Converter;

public class DateTimeToString extends Converter<Date, String> {
	private static SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	@Override
	public String convertForward(Date arg0) {
		return dtf.format(arg0);
	}

	@Override
	public Date convertReverse(String arg0) {
		try {
			return dtf.parse(arg0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
