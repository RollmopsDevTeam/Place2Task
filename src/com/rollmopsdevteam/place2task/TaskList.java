package com.rollmopsdevteam.place2task;

import java.util.ArrayList;

import android.util.Log;

public class TaskList extends ArrayList<Task> {
	

	private static final long serialVersionUID = 4105832240816454926L;
	private static TaskList _instance=null; 
	
	public TaskList() {
		Log.v(Constants.LOG, "Creating " + TaskList.class.getName());
		//TODO load task list here
	}
	
	public static TaskList getInstance() {
		if( _instance == null ) {
			_instance = new TaskList();
		} 
		return _instance;
	}
	
	
	

	

}
