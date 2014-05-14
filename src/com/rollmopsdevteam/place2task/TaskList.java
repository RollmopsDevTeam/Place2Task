package com.rollmopsdevteam.place2task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskList extends ArrayList<Task> {

	private static final long serialVersionUID = 4105832240816454926L;
	private static TaskList _instance = null;
	private static Context _context = null;
	private static TaskListDBHelper _taskListDBHelper;

	// do not create TaskList by yourself. Instead use TaskList.getInstance()
	private TaskList() {
		if (_context == null) {
			Log.e(Constants.LOG_TAG,
					"Context is not set. Use TaskList.setContext() before calling TaskList.getInstance()!");
		} else {
			Log.v(Constants.LOG_TAG, "Creating " + TaskList.class.getName());
			_taskListDBHelper = new TaskListDBHelper(_context);
			updateFromDB();
		}
	}

	public void updateFromDB() {
		clear();
		SQLiteDatabase db = _taskListDBHelper.getReadableDatabase();

		String[] projection = { DBContract.TaskEntryContract._ID,
				DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID,
				DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME,
				DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE };

		Cursor c = db.query(DBContract.TaskEntryContract.TABLE_NAME,
				projection, null, null, null, null, null, null);

		if (c != null) {
			while (c.moveToNext()) {
				try {
					// TODO check locale here
					Date date = new SimpleDateFormat(
							Constants.DATE_TIME_FORMAT, Locale.ENGLISH)
							.parse(c.getString(c
									.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE)));

					Task task = new Task(
							c.getInt(c
									.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID)),
							c.getString(c
									.getColumnIndex(DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME)),
							date);

					add(task);

				} catch (java.text.ParseException e) {
					Log.e(Constants.LOG_TAG, e.getMessage());
				}
			}
		}
		c.close();
	}

	public void storeToDB() {
		SQLiteDatabase db = _taskListDBHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		for (Task task : this) {
			values.clear();
			values.put(DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID,
					task.getTaskID());
			values.put(DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME,
					task.getTaskName());

			SimpleDateFormat df = new SimpleDateFormat(
					Constants.DATE_TIME_FORMAT, Locale.ENGLISH);
			values.put(DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE,
					df.format(task.getCreationDate()));

			// we use CONFLICT_IGNORE here cause this is just meant to be an update
			db.insertWithOnConflict(DBContract.TaskEntryContract.TABLE_NAME,
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
