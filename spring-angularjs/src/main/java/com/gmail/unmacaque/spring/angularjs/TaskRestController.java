package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping
	public Collection<Task> getTasks() {
		return taskRepository.getTasks();
	}

	@GetMapping(value = "/{taskId}")
	public Task getTask(@PathVariable int taskId) {
		Task task = taskRepository.getTask(taskId);
		if (task == null) {
			throw new ResourceNotFoundException();
		}
		return task;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addTask(@RequestBody Task task) {
		taskRepository.addTask(task);
	}

	@PutMapping(value = "/{taskId}")
	public Task updateTask(@PathVariable int taskId, @RequestBody Task task) {
		if (task == null) {
			throw new IllegalArgumentException();
		}
		taskRepository.updateTask(task);
		return task;
	}

	@DeleteMapping(value = "/{taskId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask(@PathVariable int taskId) {
		Task task = taskRepository.getTask(taskId);
		if (task == null) {
			throw new ResourceNotFoundException();
		}
		taskRepository.deleteTask(task);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleResourceNotFoundException() {}
}
