package com.rollmopsdevteam.place2task.util;

import android.provider.BaseColumns;

public final class DBContract {
	public DBContract() {
	}

	public static final int DATABEASE_VERSION = 1;
	public static final String DATABASE_NAME = "Place2Task.db";

	public static final String TEXT_TYPE = " TEXT";
	public static final String FLOAT_TYPE = " FLOAT";
	public static final String INTEGER_TYPE = " INTEGER";
	public static final String COMMA_SEP = ",";

	public static abstract class TaskTableContract implements BaseColumns {
		public static final String TABLE_NAME = "Tasks";
		public static final String COLUMN_NAME_TASK_ID = "TaskID";
		public static final String COLUMN_NAME_TASK_NAME = "TaskName";
		public static final String COLUMN_NAME_CREATION_DATE = "CreationDate";
		
		public static final String[] PROJECTION = { _ID,
				COLUMN_NAME_TASK_ID,
				COLUMN_NAME_TASK_NAME,
				COLUMN_NAME_CREATION_DATE };
	}
	
	public static abstract class PlaceTableContract implements BaseColumns {
		public static final String TABLE_NAME = "Places";
		public static final String COLUMN_NAME_PLACE_NAME = "Name";
		public static final String COLUMN_NAME_ADDRESS_STRING = "AddressAsString";
		public static final String COLUMN_NAME_COUNTRY = "Country";
		public static final String COLUMN_NAME_ADDRESS_LAT = "Lat";
		public static final String COLUMN_NAME_ADDRESS_LNG = "Lng";
		public static final String COLUMN_NAME_DISTANCE = "Distance";
		public static final String COLUMN_NAME_URL = "Url";
		
		public static final String[] PROJECTION = { _ID,
				COLUMN_NAME_PLACE_NAME,
				COLUMN_NAME_ADDRESS_STRING,
				COLUMN_NAME_COUNTRY,
				COLUMN_NAME_ADDRESS_LAT,
				COLUMN_NAME_ADDRESS_LNG,
				COLUMN_NAME_DISTANCE,
				COLUMN_NAME_URL
		};
	}
}
