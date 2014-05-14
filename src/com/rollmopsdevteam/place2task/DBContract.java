package com.rollmopsdevteam.place2task;

import android.provider.BaseColumns;

public final class DBContract {
	public DBContract() {}

	public static final int DATABEASE_VERSION = 1;
	public static final String DATABASE_NAME = "TaskList.db";
	
	public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
	
	public static abstract class TaskEntryContract implements BaseColumns {
        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_NAME_TASK_ID = "TaskID";
        public static final String COLUMN_NAME_TASK_NAME = "TaskName";
        public static final String COLUMN_NAME_CREATION_DATE = "CreationDate";

	}
}
