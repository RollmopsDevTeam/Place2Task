package com.rollmopsdevteam.place2task.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlaceListDBHelper extends SQLiteOpenHelper {

	private static final String SQL_CREATE_PLACES = "CREATE TABLE "
			+ DBContract.PlaceEntryContract.TABLE_NAME + " ("
			+ DBContract.PlaceEntryContract._ID + " INTEGER PRIMARY KEY,"
			+ DBContract.PlaceEntryContract.COLUMN_NAME_PLACE_NAME
			+ DBContract.TEXT_TYPE + " NOT NULL UNIQUE" + DBContract.COMMA_SEP
			+ DBContract.PlaceEntryContract.COLUMN_NAME_ADDRESSES
			+ DBContract.TEXT_TYPE + " )";

	private static final String SQL_DELETE_PLACES = "DROP TABLE IF EXISTS "
			+ DBContract.PlaceEntryContract.TABLE_NAME;

	public PlaceListDBHelper(Context context) {
		super(context, DBContract.DATABASE_NAME, null,
				DBContract.DATABEASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(Constants.LOG_TAG, "Creating DB: " + SQL_CREATE_PLACES);
		db.execSQL(SQL_CREATE_PLACES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_PLACES);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
}
