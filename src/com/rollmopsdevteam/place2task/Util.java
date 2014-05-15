package com.rollmopsdevteam.place2task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

public class Util {
	
	static Context context;
	
	static public String getFormattedDate( Date date ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				context.getString(R.string.date_format), Locale.ENGLISH);
		return dateFormat.format(date);
	}
	
	static public String getFormattedTime( Date date ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				context.getString(R.string.time_format), Locale.ENGLISH);
		return dateFormat.format(date);
	}

}
