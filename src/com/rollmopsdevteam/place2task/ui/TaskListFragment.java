package com.rollmopsdevteam.place2task.ui;

import com.rollmopsdevteam.place2task.R;
import com.rollmopsdevteam.place2task.util.TaskList;

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
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragment = inflater.inflate(R.layout.fragment_task_list,
				container, false);

		_taskList = (ListView) fragment.findViewById(R.id.task_list);

		// TODO make this a little more fancy (ViewStup)
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

}
