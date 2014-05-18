package com.rollmopsdevteam.place2task;

import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

public class CalendarFragment extends DialogFragment {

	private DialogInterface.OnClickListener _onCancelListener;
	private DialogInterface.OnClickListener _onOkListener;

	private CalendarView _calendarView;
	private Date _date;

	public final void setDate(Date date) {
		_date = date;
	}

	public Date getDate() {
		return _date;
	}

	public final void setOnCancelListener(
			DialogInterface.OnClickListener onCancelListener) {
		_onCancelListener = onCancelListener;
	}

	public final void setOnOkListener(
			DialogInterface.OnClickListener onOkListener) {
		_onOkListener = onOkListener;
	}

	public CalendarView getCalendarView() {
		return _calendarView;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View calendarDialog = inflater.inflate(R.layout.calendar_dialog, null);

		builder.setPositiveButton(android.R.string.ok, _onOkListener)
				.setNegativeButton(android.R.string.cancel, _onCancelListener);

		_calendarView = (CalendarView) calendarDialog
				.findViewById(R.id.calendar);
		_calendarView.setDate(_date.getTime());

		_calendarView.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				_date = cal.getTime();
			}
		});

		builder.setView(calendarDialog);
		return builder.create();
	}

}
