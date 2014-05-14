package com.rollmopsdevteam.place2task;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TaskListFragment extends Fragment {
	TaskListAdapter _taskListAdapter;
	ListView _taskList;

	public TaskListFragment() {
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
}
