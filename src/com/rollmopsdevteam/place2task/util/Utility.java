package com.rollmopsdevteam.place2task.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

import com.rollmopsdevteam.place2task.R;

public class Utility {

	private static Context _context;

	public static void setContext(Context context) {
		_context = context;
	}

	static public String getFormattedDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				_context.getString(R.string.date_format), Locale.ENGLISH);
		return dateFormat.format(date);
	}

	static public String getFormattedTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				_context.getString(R.string.time_format), Locale.ENGLISH);
		return dateFormat.format(date);
	}
}
