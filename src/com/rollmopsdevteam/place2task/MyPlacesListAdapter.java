package com.rollmopsdevteam.place2task;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rollmopsdevteam.place2task.util.Place;

public class MyPlacesListAdapter extends ArrayAdapter<Place> {

	private Activity _activity;

	public MyPlacesListAdapter(Activity activity) {
		super(activity.getApplicationContext(), R.layout.item_location_dropdown);
		_activity = activity;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (vi == null) {
			vi = _activity.getLayoutInflater().inflate(
					R.layout.item_location_dropdown, null);
		}
		return vi;
	}


}
