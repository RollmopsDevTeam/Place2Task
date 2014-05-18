package com.rollmopsdevteam.place2task;

import com.rollmopsdevteam.place2task.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.rollmopsdevteam.place2task.util.Constants;
import com.rollmopsdevteam.place2task.util.TaskList;
import com.rollmopsdevteam.place2task.util.Utility;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Utility.setContext(getApplicationContext());

		Log.v(Constants.LOG_TAG,
				"@onCreate for " + MainActivity.class.getName());

		setContentView(R.layout.activity_main);

		MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(
				getSupportFragmentManager());
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
				return new MyPlacesFragment();
			}
		}
	}

	public void setActionBarTitle(String title) {
		getActionBar().setTitle(title);
	}
}
