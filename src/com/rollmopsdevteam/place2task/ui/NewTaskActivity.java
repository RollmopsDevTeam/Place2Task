package com.rollmopsdevteam.place2task.ui;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.rollmopsdevteam.place2task.R;
import com.rollmopsdevteam.place2task.util.Utility;

public class NewTaskActivity extends Activity {

	private Button _saveButton;

	private EditText _taskNameEditText;

	private LinearLayout _dueDateFrame;
	private Button _dueDateButton;
	private Button _dueTimeButton;

	private Date _dueDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);

		_saveButton = (Button) findViewById(R.id.save);

		_taskNameEditText = (EditText) findViewById(R.id.task_name);
		_dueDateFrame = (LinearLayout) findViewById(R.id.due_date_frame);

		_dueDateButton = (Button) findViewById(R.id.due_date_button);
		_dueTimeButton = (Button) findViewById(R.id.due_time_button);

		_saveButton.setEnabled(false);

		_dueDate = new Date();
		_dueDateButton.setText(Utility.getFormattedDate(_dueDate));
		_dueTimeButton.setText(Utility.getFormattedTime(_dueDate));

		_taskNameEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				_saveButton.setEnabled(s.length() > 0);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// click on calendar image
	public void onCalendarClick(View v) {
		final CalendarFragment calendarFragment = new CalendarFragment();
		calendarFragment.setDate(_dueDate);

		calendarFragment.setOnOkListener(new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				_dueDate = calendarFragment.getDate();
				_dueDateButton.setText(Utility.getFormattedDate(_dueDate));
			}
		});

		calendarFragment.show(getFragmentManager(), null);
	}

	// click on due date selector
	public void onDueDateClicked(View v) {
		DatePickerFragment datePicker = new DatePickerFragment();
		datePicker.setDate(_dueDate);
		datePicker.setOnDateSetListener(new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(_dueDate);
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, monthOfYear);
				cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				_dueDate = cal.getTime();
				_dueDateButton.setText(Utility.getFormattedDate(_dueDate));
			}
		});

		datePicker.show(getFragmentManager(), "datePicker");
	}

	// click on due time selector
	public void onDueTimeClicked(View v) {
		TimePickerFragment timePicker = new TimePickerFragment();
		timePicker.setTime(_dueDate);
		timePicker.setOnTimeSetListener(new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(_dueDate);
				cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
				cal.set(Calendar.MINUTE, minute);
				_dueDate = cal.getTime();
				_dueTimeButton.setText(Utility.getFormattedTime(_dueDate));
			}
		});

		timePicker.show(getFragmentManager(), "timePicker");
	}

	// click on checkbox due date
	public void onDueDateCheckBoxClicked(View v) {
		if (((CheckBox) v).isChecked()) {
			_dueDateFrame.setVisibility(View.VISIBLE);
			_dueDateFrame.requestFocus();
		} else {
			_dueDateFrame.setVisibility(View.GONE);
		}
	}

	// click on cancel
	public void onCancelClicked(View v) {
		finish();
	}

}
