package com.gmail.unmacaque.spring.angularjs;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private final Map<Integer, Task> noteMap = new HashMap<>();
	private int lastId = 1;

	public InMemoryTaskRepository() {
		addTask(new Task(lastId, "make breakfast", LocalDateTime.now(), "", false));
		addTask(new Task(lastId, "take out trash", LocalDateTime.now(), "", false));
		addTask(new Task(lastId, "do laundry", LocalDateTime.now(), "", false));
	}

	@Override
	public int addTask(Task note) {
		noteMap.put(lastId, note);
		lastId++;
		return lastId;
	}

	@Override
	public Task getNote(int noteId) {
		return noteMap.get(noteId);
	}

	@Override
	public Collection<Task> getTask() {
		return noteMap.values();
	}

	@Override
	public Task updateTask(int noteId, Task note) {
		if (!noteMap.containsKey(noteId) || noteId != note.getId()) {
			throw new IllegalArgumentException();
		}
		return noteMap.put(noteId, note);
	}

	@Override
	public Task deleteTask(int noteId) {
		return noteMap.remove(noteId);
	}
}
