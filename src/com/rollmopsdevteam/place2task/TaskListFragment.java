package com.rollmopsdevteam.place2task;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
    }
	
}
