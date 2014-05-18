package com.rollmopsdevteam.place2task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rollmopsdevteam.place2task.util.TaskList;

public class TaskListFragment extends Fragment {
	TaskListAdapter _taskListAdapter;
	ListView _taskList;

	public TaskListFragment() {
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragment = inflater.inflate(R.layout.fragment_task_list,
				container, false);

		_taskList = (ListView) fragment.findViewById(R.id.task_list);

		_taskList.setEmptyView(fragment.findViewById(R.id.empty_task_list));

		_taskListAdapter = new TaskListAdapter(getActivity());
		_taskList.setAdapter(_taskListAdapter);

		return fragment;
	}

	@Override
	public void onStart() {
		super.onStart();
		TaskList.getInstance().updateFromDB();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.task_list_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_new_task:
			Intent newTaskIntent = new Intent(getActivity(),
					NewTaskActivity.class);
			startActivity(newTaskIntent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
