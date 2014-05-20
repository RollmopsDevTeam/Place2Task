package com.rollmopsdevteam.place2task.util;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class PlaceList extends ArrayList<Place> {

	private static final long serialVersionUID = 6932570688740820271L;
	private static PlaceList _instance = null;
	private static Context _context;
	private static PlaceListDBHelper _placeListDBHelper;
	
	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private PlaceList() {
		if (_context == null) {
			Log.e(Constants.LOG_TAG,
					"Context is not set. Use PlaceList.setContext() before calling PlaceList.getInstance()!");
		} else {
			Log.v(Constants.LOG_TAG, "Creating " + TaskList.class.getName());
			_placeListDBHelper = new PlaceListDBHelper(_context);
		}
	}
	
	public static void setContext(Context context) {
		_context = context;
	}

	public static PlaceList getInstance() {
		if (_instance == null) {
			_instance = new PlaceList();
		}
		return _instance;
	}

	
}
