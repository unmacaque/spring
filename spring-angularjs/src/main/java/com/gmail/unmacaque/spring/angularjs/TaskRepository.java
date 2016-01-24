package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

public interface TaskRepository {
	Collection<Task> getTasks();
	Task getTask(int taskId);
	void addTask(Task task);
	void updateTask(Task task);
	void deleteTask(Task task);
}
