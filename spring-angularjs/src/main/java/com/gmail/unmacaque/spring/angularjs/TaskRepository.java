package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

public interface TaskRepository {
	int addTask(Task task);
	Task getTask(int taskId);
	Collection<Task> getTask();
	Task updateTask(int taskId, Task task);
	Task deleteTask(int taskId);
}
