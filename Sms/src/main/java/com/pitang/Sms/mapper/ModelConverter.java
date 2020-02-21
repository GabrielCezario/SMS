package com.pitang.Sms.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelConverter {

	private static final String FORMAT_DATE_HOUR = "dd/MM/yyyy";
	
	public static final Converter<Boolean, String> convertStatusToString = new AbstractConverter<Boolean, String>(){

		@Override
		protected String convert(Boolean source) {
			
			if (source == true) {
				return "Ativo";
			} else {
				return "Inativo";
			}
			
		}
		
	};
	
	public static final Converter<String, Boolean> convertStatusToBoolean = new AbstractConverter<String, Boolean>(){

		@Override
		protected Boolean convert(String source) {
			
			if (source.equalsIgnoreCase("Ativo")) {
				return true;
			} else {
				return false;
			}
			
		}
			
	};
	
	public static final Converter<Date, String> convertDatetoString = new AbstractConverter<Date, String>(){

		@Override
		protected String convert(Date source) {
			
			if (source == null) {
				return null;
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_HOUR);
			return simpleDateFormat.format(source.getTime());
		}
		
	};
	
	public static final Converter<String, Date> converterStringToDate = new AbstractConverter<String, Date>(){

		@Override
		protected Date convert(String source) {
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_HOUR);
			simpleDateFormat.setLenient(false);
			
			Date date = new Date();
			
			try {
				date = simpleDateFormat.parse(source);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}			
			
		}
		
	};
	
	
	
}
