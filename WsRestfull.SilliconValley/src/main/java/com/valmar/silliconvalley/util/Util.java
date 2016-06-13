package com.valmar.silliconvalley.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date getDateFromString(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return (Date) format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getDateFromStringSecondFormat(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (Date) format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
