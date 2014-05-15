package com.rollmopsdevteam.place2task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Util.context = getApplicationContext();

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

		// TODO check if this is the right place to store to the DB
		TaskList.getInstance().storeToDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.tasks_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_new_task:
			Intent newTaskIntent = new Intent(this, NewTaskActivity.class);
			startActivity(newTaskIntent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}
}
