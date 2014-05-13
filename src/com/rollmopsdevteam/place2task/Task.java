package com.rollmopsdevteam.place2task;

import java.util.Date;

public class Task {
	private String _taskName;
	private Date _creationDate;
	
	public Task(String taskName, Date creationData) {
		_taskName = taskName;
		_creationDate = creationData;

	}
	public final String getTaskName() { return _taskName; }
	
	public final Date getCreationDate() { return _creationDate; }
}
