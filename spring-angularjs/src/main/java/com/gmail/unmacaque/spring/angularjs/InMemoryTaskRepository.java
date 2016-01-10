package com.gmail.unmacaque.spring.angularjs;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private final Map<Integer, Task> taskMap = new HashMap<>();
	private int lastId = 1;

	public InMemoryTaskRepository() {
		addTask(new Task(lastId, "make breakfast", LocalDateTime.now(), "", false));
		addTask(new Task(lastId, "take out trash", LocalDateTime.now(), "", false));
		addTask(new Task(lastId, "do laundry", LocalDateTime.now(), "", false));
	}

	@Override
	public int addTask(Task task) {
		taskMap.put(lastId, task);
		lastId++;
		return lastId;
	}

	@Override
	public Task getTask(int taskId) {
		return taskMap.get(taskId);
	}

	@Override
	public Collection<Task> getTask() {
		return taskMap.values();
	}

	@Override
	public Task updateTask(int taskId, Task task) {
		if (!taskMap.containsKey(taskId) || taskId != task.getId()) {
			throw new IllegalArgumentException();
		}
		return taskMap.put(taskId, task);
	}

	@Override
	public Task deleteTask(int taskId) {
		return taskMap.remove(taskId);
	}
}
