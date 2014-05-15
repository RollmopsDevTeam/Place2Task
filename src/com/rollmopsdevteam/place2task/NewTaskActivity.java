package com.rollmopsdevteam.place2task;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NewTaskActivity extends Activity {
	
	private Button _saveButton;
	
	private EditText _taskNameEditText;

	private CheckBox _dueDateCheckBox;
	private LinearLayout _dueDateFrame;
	private Button _dueDateButton;
	private Button _dueTimeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);

		_saveButton = (Button) findViewById(R.id.save);
				
		_taskNameEditText = (EditText) findViewById(R.id.task_name);
		_dueDateCheckBox = (CheckBox) findViewById(R.id.due_date_check_box);
		_dueDateFrame = (LinearLayout) findViewById(R.id.due_date_frame);


		_dueDateButton = (Button) findViewById(R.id.due_date_button);
		_dueTimeButton = (Button) findViewById(R.id.due_time_button);

		// cancel add new task
		findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		_saveButton.setEnabled(false);
		
		Date now = new Date();
		_dueDateButton.setText(Util.getFormattedDate(now));
		_dueTimeButton.setText(Util.getFormattedTime(now));

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

		_dueDateCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							_dueDateFrame.setVisibility(View.VISIBLE);
						} else {
							_dueDateFrame.setVisibility(View.GONE);
						}
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

}
