package com.rollmopsdevteam.place2task;

import java.util.ArrayList;
import java.util.HashMap;

import com.rollmopsdevteam.place2task.R.id;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskListAdapter extends BaseAdapter {

	private Activity _activity;
	private ArrayList<HashMap<String, String> > _taskListInfo;
	private static LayoutInflater _inflater=null;
	
	public TaskListAdapter(Activity activity, ArrayList<HashMap<String, String> > taskList ) {
		_activity = activity;
		_taskListInfo = taskList;
		_inflater = _activity.getLayoutInflater();
	}

	@Override
	public int getCount() {
		return _taskListInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if( vi == null) {
			vi = _inflater.inflate(R.layout.list_row, null);
		}
		
		ImageView imageView = (ImageView)vi.findViewById(R.id.task_image);
		TextView taskName = (TextView)vi.findViewById(id.task_name_label);
		TextView taskDurationCreated = (TextView)vi.findViewById(id.task_duration_created_label);
		
		// TODO check task type
		imageView.setImageResource(R.drawable.ic_location_task);
		taskName.setText("Test Task");
		taskDurationCreated.setText("2 Days");
		
		return vi;
	}

}
