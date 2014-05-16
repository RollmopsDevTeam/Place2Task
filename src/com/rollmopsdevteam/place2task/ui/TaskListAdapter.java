package com.rollmopsdevteam.place2task.ui;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rollmopsdevteam.place2task.R;
import com.rollmopsdevteam.place2task.R.id;
import com.rollmopsdevteam.place2task.util.Constants;
import com.rollmopsdevteam.place2task.util.Task;
import com.rollmopsdevteam.place2task.util.TaskList;

public class TaskListAdapter extends BaseAdapter {

	private Activity _activity;
	private static LayoutInflater _inflater = null;

	public TaskListAdapter(Activity activity) {
		Log.v(Constants.LOG_TAG, "Constructing TaskListAdapter");
		_activity = activity;

		// our TaskList needs the context to create the DBHelper
		TaskList.setContext(activity.getApplicationContext());

		_inflater = _activity.getLayoutInflater();
	}

	@Override
	public int getCount() {
		return TaskList.getInstance().size();
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
		if (vi == null) {
			vi = _inflater.inflate(R.layout.task_list_element, null);
		}

		ImageView imageView = (ImageView) vi.findViewById(R.id.task_image);
		TextView taskName = (TextView) vi.findViewById(id.task_name_label);
		TextView taskDurationCreated = (TextView) vi
				.findViewById(id.task_duration_created_label);

		Task task = TaskList.getInstance().get(position);

		// TODO check task type
		imageView.setImageResource(R.drawable.ic_location_task);
		taskName.setText(task.getTaskName());
		taskDurationCreated.setText("2 Days");

		return vi;
	}

}
