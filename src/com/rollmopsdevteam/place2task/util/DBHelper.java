package com.rollmopsdevteam.place2task.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String SQL_CREATE_TASKS = "CREATE TABLE IF NOT EXISTS "
			+ DBContract.TaskTableContract.TABLE_NAME + " ("
			+ DBContract.TaskTableContract._ID + " INTEGER PRIMARY KEY,"
			+ DBContract.TaskTableContract.COLUMN_NAME_TASK_ID
			+ DBContract.TEXT_TYPE + " NOT NULL UNIQUE" + DBContract.COMMA_SEP
			+ DBContract.TaskTableContract.COLUMN_NAME_TASK_NAME
			+ DBContract.TEXT_TYPE + DBContract.COMMA_SEP
			+ DBContract.TaskTableContract.COLUMN_NAME_CREATION_DATE
			+ DBContract.TEXT_TYPE + " )";
	
	// _ID, Name, AddressAsString, Country, Lat, Lng, Distance
	private static final String SQL_CREATE_PLACES = "CREATE TABLE IF NOT EXISTS "
			+ DBContract.PlaceTableContract.TABLE_NAME + " ("
			+ DBContract.PlaceTableContract._ID + " INTEGER PRIMARY KEY,"
			+ DBContract.PlaceTableContract.COLUMN_NAME_PLACE_NAME
			+ DBContract.TEXT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_ADDRESS_STRING
			+ DBContract.TEXT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_COUNTRY
			+ DBContract.TEXT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_ADDRESS_LAT
			+ DBContract.FLOAT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_ADDRESS_LNG
			+ DBContract.FLOAT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_DISTANCE
			+ DBContract.TEXT_TYPE + " NOT NULL" + DBContract.COMMA_SEP
			+ DBContract.PlaceTableContract.COLUMN_NAME_URL
			+ DBContract.TEXT_TYPE + " NOT NULL)";

	private static final String SQL_DELETE_TASKS = "DROP TABLE IF EXISTS "
			+ DBContract.TaskTableContract.TABLE_NAME;
	
	private static final String SQL_DELETE_PLACES = "DROP TABLE IF EXISTS "
			+ DBContract.PlaceTableContract.TABLE_NAME;

	public DBHelper(Context context) {
		super(context, DBContract.DATABASE_NAME, null,
				DBContract.DATABEASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TASKS);
		db.execSQL(SQL_CREATE_PLACES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_TASKS);
		db.execSQL(SQL_DELETE_PLACES);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
}
