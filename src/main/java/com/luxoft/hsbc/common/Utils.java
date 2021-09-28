package com.luxoft.hsbc.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for common useful methods to the whole application.
 * 
 * @author Carlos Santos
 *
 */
public class Utils {
	
	public static final String DATE_FORMAT_US = "yyyy-MM-dd";
	
	private Utils() {}
	
	/**
	 * Convert date text to local date.
	 * 
	 * @param date the text date to be converted.
	 * @param format the format that the text date has.
	 * @return the date converted.
	 */
	public static LocalDate convertToLocalDate(String date, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDate.parse(date, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HsbcException("The date informed could not be searched");
		}
	}

}
