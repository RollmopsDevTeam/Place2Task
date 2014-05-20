package com.rollmopsdevteam.place2task.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskList extends ArrayList<Task> {

	private static final long serialVersionUID = 4105832240816454926L;
	private static TaskList _instance = null;
	private static Context _context = null;
	private static DBHelper _taskListDBHelper;

	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private TaskList() {
		if (_context == null) {
			Log.e(Constants.LOG_TAG,
					"Context is not set. Use TaskList.setContext() before calling TaskList.getInstance()!");
		} else {
			Log.v(Constants.LOG_TAG, "Creating " + TaskList.class.getName());
			_taskListDBHelper = new DBHelper(_context);
		}
	}

	public void updateFromDB() {
		Log.v(Constants.LOG_TAG, "Updating TaskList from DB.");
		clear();
		SQLiteDatabase db = _taskListDBHelper.getReadableDatabase();

		Cursor c = db.query(DBContract.TaskTableContract.TABLE_NAME,
				DBContract.TaskTableContract.PROJECTION, null, null, null, null, null, null);

		if (c != null) {
			while (c.moveToNext()) {
				try {
					// TODO check locale here
					Date date = new SimpleDateFormat(
							Constants.DATE_TIME_FORMAT, Locale.ENGLISH)
							.parse(c.getString(c
									.getColumnIndex(DBContract.TaskTableContract.COLUMN_NAME_CREATION_DATE)));

					Task task = new Task();
					// set taskID
					task.setTaskID(UUID.fromString(c.getString(c
							.getColumnIndex(DBContract.TaskTableContract.COLUMN_NAME_TASK_ID))));
					// set task name
					task.setTaskName(c.getString(c
							.getColumnIndex(DBContract.TaskTableContract.COLUMN_NAME_TASK_NAME)));
					// set creation date
					task.setCreationDate(date);
					add(task);

				} catch (java.text.ParseException e) {
					Log.e(Constants.LOG_TAG, e.getMessage());
				}
			}
		}
		c.close();
	}

	public void storeToDB() {
		Log.v(Constants.LOG_TAG, "Storing TaskList to DB.");
		SQLiteDatabase db = _taskListDBHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		for (Task task : this) {
			values.clear();
			values.put(DBContract.TaskTableContract.COLUMN_NAME_TASK_ID, task
					.getTaskID().toString());
			values.put(DBContract.TaskTableContract.COLUMN_NAME_TASK_NAME,
					task.getTaskName());

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					Constants.DATE_TIME_FORMAT, Locale.ENGLISH);
			values.put(DBContract.TaskTableContract.COLUMN_NAME_CREATION_DATE,
					dateFormat.format(task.getCreationDate()));

			// TODO check to rather use UPDATE instead of INSERT here
			// we use CONFLICT_IGNORE here since this is just meant to be an
			// update
			db.insertWithOnConflict(DBContract.TaskTableContract.TABLE_NAME,
					null, values, SQLiteDatabase.CONFLICT_IGNORE);
		}
	}

	public static void setContext(Context context) {
		_context = context;
	}

	public static TaskList getInstance() {
		if (_instance == null) {
			_instance = new TaskList();
		}
		return _instance;
	}

}
