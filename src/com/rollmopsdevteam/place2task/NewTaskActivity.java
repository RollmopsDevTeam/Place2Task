package com.rollmopsdevteam.place2task;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
		_dueDateButton.setText(Util.getFormattedDate(_dueDate));
		_dueTimeButton.setText(Util.getFormattedTime(_dueDate));

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
	
	public void onDueDateClicked(View v) {
		DatePickerFragment datePickerFragment = new DatePickerFragment() {
			
		    @Override
		    public Dialog onCreateDialog(Bundle savedInstanceState) {
		        // Use the current date as the default date in the picker
		        Calendar c = Calendar.getInstance();
		        c.setTime(_dueDate);
		        int year = c.get(Calendar.YEAR);
		        int month = c.get(Calendar.MONTH);
		        int day = c.get(Calendar.DAY_OF_MONTH);

		        // Create a new instance of DatePickerDialog and return it
		        return new DatePickerDialog(getActivity(), this, year, month, day);
		    }
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				Calendar cal = Calendar.getInstance();
		        cal.set(Calendar.YEAR, year);
		        cal.set(Calendar.MONTH, monthOfYear);
		        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        _dueDate = cal.getTime();
		        _dueDateButton.setText(Util.getFormattedDate(_dueDate));
			}
		};
	    datePickerFragment.show( getFragmentManager(), "datePicker");
	}
	
	public void onDueDateCheckBoxClicked(View v) {
		if ( ((CheckBox)v).isChecked() ) {
			_dueDateFrame.setVisibility(View.VISIBLE);
			_dueDateFrame.requestFocus();
		} else {
			_dueDateFrame.setVisibility(View.GONE);
		}
	}
	
	public void onCancelClicked(View v) {
		finish();
	}

}
