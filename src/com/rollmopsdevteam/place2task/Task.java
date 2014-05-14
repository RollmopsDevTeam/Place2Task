package com.rollmopsdevteam.place2task;

import java.util.Date;

public class Task {
	private int _taskID;
	private String _taskName;
	private Date _creationDate;
	
	public Task(int taskID, String taskName, Date creationData) {
		_taskID = taskID;
		_taskName = taskName;
		_creationDate = creationData;

	}
	public final String getTaskName() { return _taskName; }
	
	public final Date getCreationDate() { return _creationDate; }
	
	public final int getTaskID() { return _taskID; }
}
