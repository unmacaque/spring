package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	@Autowired
	private TaskRepository taskRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Task> getTasks() {
		return taskRepository.getTasks();
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
	public Task getTask(@PathVariable int taskId) {
		Task task = taskRepository.getTask(taskId);
		if (task == null) {
			throw new ResourceNotFoundException();
		}
		return task;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addTask(@RequestBody Task task) {
		taskRepository.addTask(task);
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
	public Task updateTask(@PathVariable int taskId, @RequestBody Task task) {
		if (task == null) {
			throw new IllegalArgumentException();
		}
		taskRepository.updateTask(task);
		return task;
	}

	@RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
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
	public void handleResourceNotFoundException() {
	}
}
