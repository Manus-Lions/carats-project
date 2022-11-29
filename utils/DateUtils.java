package com.social.ProgettoFinaleSocial.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FinalConstants.DateTimeFormat);
	public static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(FinalConstants.DateTimeFormat2); 
	
	public static String convertToDateFormat (LocalDateTime time) throws Exception {
		
			return formatter2.format(time);			

	}

	public static String convertToDateFormat2 (LocalDateTime time) throws Exception {
		
		return formatter.format(time);			

}

}
