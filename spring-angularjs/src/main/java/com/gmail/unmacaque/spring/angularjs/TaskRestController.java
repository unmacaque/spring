package com.gmail.unmacaque.spring.angularjs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	@Autowired
	private TaskRepository taskRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getTasks() {
		return ResponseEntity.ok(taskRepository.getTask());
	}

	@RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<?> getTask(@PathVariable int noteId) {
		Task note = taskRepository.getNote(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(note);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addTask(@RequestBody String title, @RequestBody Task task) {
		int taskId = taskRepository.addTask(task);
		return ResponseEntity.ok(taskId);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody Task task) {
		Task returnedTask = taskRepository.updateTask(taskId, task);
		if (returnedTask == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(returnedTask);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable int taskId) {
		Task task = taskRepository.deleteTask(taskId);
		return ResponseEntity.ok(task);
	}
}
