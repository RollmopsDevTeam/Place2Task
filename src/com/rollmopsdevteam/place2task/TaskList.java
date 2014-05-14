package com.rollmopsdevteam.place2task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskList extends ArrayList<Task> {
	
	private static final long serialVersionUID = 4105832240816454926L;
	private static TaskList _instance=null;
	private static Context _context = null;
	
	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private TaskList() {
		if(_context == null ) {
			Log.e(Constants.LOG_TAG, "Context is not set. Use TaskList.setContext() before calling TaskList.getInstance()!");
		} else {
			Log.v(Constants.LOG_TAG, "Creating " + TaskList.class.getName());
			try {
				__init();
			} catch (Exception e) {
				Log.e(Constants.LOG_TAG, e.getMessage() );
			}
		}
	}
	
	// here is where we actually read the TaskList DB and put it into the array
	private void __init()  {
		TaskListDBHelper taskListDBHelper = new TaskListDBHelper(_context);
		SQLiteDatabase db = taskListDBHelper.getReadableDatabase();
		
		String[] projection = { 
				DBContract.TaskEntryContract._ID,
				DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID,
				DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME,
				DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE
		};
		
		Cursor c = db.query(
				DBContract.TaskEntryContract.TABLE_NAME, 
				projection, 
				null,
				null,
				null, 
				null,
				null,
				null);
		
		if( c != null ) {
			while ( c.moveToNext() ) {
				try {
					// TODO check locale here
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
						.parse(c.getString(c.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE)));
					
					Task task = new Task(
							c.getInt(c.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID)),
							c.getString(c.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME)),
							date );
					
					add(task);
					
				} catch (java.text.ParseException e) {
					Log.e(Constants.LOG_TAG, e.getMessage());
				}
			}
		}
		c.close();
		
		
	}
	
	public static void setContext( Context context ) {
		_context = context;
	}
	
	public static TaskList getInstance() {
		if( _instance == null ) {
			_instance = new TaskList();
		} 
		return _instance;
	}
	
	
	

	

}
