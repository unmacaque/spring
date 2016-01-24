package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private final Map<Integer, Task> taskMap = new HashMap<>();
	private int lastId = 1;

	@Override
	public void addTask(Task task) {
		task.setId(lastId);
		taskMap.put(lastId, task);
		lastId++;
	}

	@Override
	public Task getTask(int taskId) {
		return taskMap.get(taskId);
	}

	@Override
	public Collection<Task> getTasks() {
		return taskMap.values();
	}

	@Override
	public void updateTask(Task task) {
		int taskId = task.getId();
		if (!taskMap.containsKey(taskId)) {
			throw new IllegalArgumentException();
		}
		taskMap.put(taskId, task);
	}

	@Override
	public void deleteTask(Task task) {
		int taskId = task.getId();
		if (!taskMap.containsKey(taskId)) {
			throw new IllegalArgumentException();
		}
		taskMap.remove(taskId);
	}
}
