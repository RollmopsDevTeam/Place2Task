package com.rollmopsdevteam.place2task;

import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.FragmentManager;
import android.util.Log;

import com.rollmopsdevteam.place2task.util.Constants;
import com.rollmopsdevteam.place2task.util.PlaceList;
import com.rollmopsdevteam.place2task.util.TaskList;
import com.rollmopsdevteam.place2task.util.Utility;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// load relevant data from DB
		TaskList.setContext(getApplicationContext());
		PlaceList.setContext(getApplicationContext());
		TaskList.getInstance().updateFromDB();
		PlaceList.getInstance().updateFromDB();

		Utility.setContext(getApplicationContext());

		Log.v(Constants.LOG_TAG,
				"@onCreate for " + MainActivity.class.getName());

		setContentView(R.layout.activity_main);

		MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(
				getFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
	}

	@Override
	protected void onStop() {
		super.onStop();
		// TODO check if this is the right place to store to the DB
		// this would mean opening e.g. NewTaskActivity to store to DB...is that
		// good?
		TaskList.getInstance().storeToDB();
		PlaceList.getInstance().storeToDB();
	}

	public static class MainFragmentPagerAdapter extends FragmentPagerAdapter {

		public MainFragmentPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new TaskListFragment();
			default:
				return new MapOverviewFragment();
			}
		}
	}

	public void setActionBarTitle(String title) {
		getActionBar().setTitle(title);
	}

}
