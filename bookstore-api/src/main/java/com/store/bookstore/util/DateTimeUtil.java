package com.store.bookstore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
	
	public static String dateToString(Date date, String dest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dest, Locale.ENGLISH);
        return convertDateToString(date, dateFormat);
    }
	
	public static String convertDateToString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }
	
	public static Date convertToDate(String dateStr, String sourceFormatDate) {
		return convertToDate(dateStr, sourceFormatDate, Locale.ENGLISH);
	}
	
	public static Date convertToDate(String dateStr, String sourceFormatDate, Locale locale) {
		SimpleDateFormat fromFormat = new SimpleDateFormat(sourceFormatDate, locale);
		try {
			return fromFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
