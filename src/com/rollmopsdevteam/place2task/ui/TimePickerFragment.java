package com.rollmopsdevteam.place2task.ui;

import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;

public class TimePickerFragment extends DialogFragment {

	private OnTimeSetListener _listener;
	private Date _date = new Date();

	// since we need a default constructor we have to use set methods instead of
	// passing the arguments with the constructor
	final public void setOnTimeSetListener(OnTimeSetListener listener) {
		_listener = listener;
	}

	final public void setTime(Date date) {
		_date = date;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		Calendar c = Calendar.getInstance();
		c.setTime(_date);
		int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		// Create a new instance of DatePickerDialog and return it
		return new TimePickerDialog(getActivity(), _listener, hourOfDay,
				minute, true);
	}

}
