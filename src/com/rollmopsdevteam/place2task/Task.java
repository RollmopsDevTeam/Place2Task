package com.rollmopsdevteam.place2task;

import java.util.Date;
import java.util.UUID;

public class Task {
	private UUID _taskID;
	private String _taskName;
	private Date _creationDate;

	public Task(UUID taskID, String taskName, Date creationData) {
		_taskID = taskID;
		_taskName = taskName;
		_creationDate = creationData;

	}

	public final String getTaskName() {
		return _taskName;
	}

	public final Date getCreationDate() {
		return _creationDate;
	}

	public final UUID getTaskID() {
		return _taskID;
	}
}
