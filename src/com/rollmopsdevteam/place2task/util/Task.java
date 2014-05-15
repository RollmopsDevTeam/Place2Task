package com.rollmopsdevteam.place2task.util;

import java.util.Date;
import java.util.UUID;

public class Task {
	private UUID _taskID;
	private String _taskName;
	private Date _creationDate;
	private boolean _hasDueDate = false;
	private Date _dueDate;

	static final Task createTask(String taskName ) {
		Task task = new Task();
		task.setCreationDate(new Date());
		task.setTaskID(UUID.randomUUID());
		task.setTaskName(taskName);
		return task;
	}
	
	public final void setTaskName(String taskName) {
		_taskName = taskName;
	}

	public final void setDueDate(Date dueDate) {
		_dueDate = dueDate;
		_hasDueDate = true;
	}
	
	public final void setCreationDate(Date creationDate ) {
		_creationDate = creationDate;
	}

	public final void setTaskID(UUID taskID) {
		_taskID = taskID;
	}
	
	// getter
	
	public final String getTaskName() {
		return _taskName;
	}

	public final Date getCreationDate() {
		return _creationDate;
	}

	public final UUID getTaskID() {
		return _taskID;
	}

	public final Date getDueDate() {
		return _dueDate;
	}

	public final boolean hasDueDate() {
		return _hasDueDate;

	}
}
