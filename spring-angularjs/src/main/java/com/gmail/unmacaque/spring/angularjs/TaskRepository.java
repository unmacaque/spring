package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

public interface TaskRepository {
	int addTask(Task note);
	Task getNote(int noteId);
	Collection<Task> getTask();
	Task updateTask(int noteId, Task note);
	Task deleteTask(int noteId);
}
