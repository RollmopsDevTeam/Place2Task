package com.rollmopsdevteam.place2task.util;

import android.provider.BaseColumns;

public final class DBContract {
	public DBContract() {
	}

	public static final int DATABEASE_VERSION = 1;
	public static final String DATABASE_NAME = "TaskList.db";

	public static final String TEXT_TYPE = " TEXT";
	public static final String FLOAT_TYPE = " FLOAT";
	public static final String INTEGER_TYPE = " INTEGER";
	public static final String COMMA_SEP = ",";

	public static abstract class TaskEntryContract implements BaseColumns {
		public static final String TABLE_NAME = "Tasks";
		public static final String COLUMN_NAME_TASK_ID = "TaskID";
		public static final String COLUMN_NAME_TASK_NAME = "TaskName";
		public static final String COLUMN_NAME_CREATION_DATE = "CreationDate";
	}
	
	public static abstract class PlaceEntryContract implements BaseColumns {
		public static final String TABLE_NAME = "Places";
		public static final String COLUMN_NAME_PLACE_NAME = "Name";
		public static final String COLUMN_NAME_ADDRESS_STRING = "AddressAsString";
		public static final String COLUMN_NAME_COUNTRY = "Country";
		public static final String COLUMN_NAME_ADDRESS_LAT = "Lat";
		public static final String COLUMN_NAME_ADDRESS_LNG = "Lng";
		public static final String COLUMN_NAME_DISTANCE = "Distance";
	}
}
