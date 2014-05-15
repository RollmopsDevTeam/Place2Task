package com.rollmopsdevteam.place2task.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskListDBHelper extends SQLiteOpenHelper {

	private static final String SQL_CREATE_TASKS = "CREATE TABLE "
			+ DBContract.TaskEntryContract.TABLE_NAME + " ("
			+ DBContract.TaskEntryContract._ID + " INTEGER PRIMARY KEY,"
			+ DBContract.TaskEntryContract.COLUMN_NAME_TASK_ID
			+ DBContract.TEXT_TYPE + " NOT NULL UNIQUE" + DBContract.COMMA_SEP 
			+ DBContract.TaskEntryContract.COLUMN_NAME_TASK_NAME
			+ DBContract.TEXT_TYPE + DBContract.COMMA_SEP
			+ DBContract.TaskEntryContract.COLUMN_NAME_CREATION_DATE
			+ DBContract.TEXT_TYPE + " )";

	private static final String SQL_DELETE_TASKS = "DROP TABLE IF EXISTS "
			+ DBContract.TaskEntryContract.TABLE_NAME;

	public TaskListDBHelper(Context context) {
		super(context, DBContract.DATABASE_NAME, null,
				DBContract.DATABEASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(Constants.LOG_TAG, "Creating DB: " + SQL_CREATE_TASKS);
		db.execSQL(SQL_CREATE_TASKS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_TASKS);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
}
