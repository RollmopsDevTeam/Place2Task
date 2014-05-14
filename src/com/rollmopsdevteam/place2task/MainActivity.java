package com.rollmopsdevteam.place2task;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.v(Constants.LOG_TAG,
				"@onCreate for " + MainActivity.class.getName());

		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new TaskListFragment()).commit();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();

		//TODO check if this is the right place to store to the DB
		TaskList.getInstance().storeToDB();
	}
}
