package com.rollmopsdevteam.place2task;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DatePickerFragment extends DialogFragment {

	private OnDateSetListener _listener;
	private Date _date = new Date();

	// since we need a default constructor we have to use set methods instead of
	// passing the arguments with the constructor
	final public void setOnDateSetListener(OnDateSetListener listener) {
		_listener = listener;
	}

	final public void setDate(Date date) {
		_date = date;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		Calendar c = Calendar.getInstance();
		c.setTime(_date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), _listener, year, month, day);
	}

}
