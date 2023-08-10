package net.testproject.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.testproject.springboot.exception.ResourceNotFoundException;
import net.testproject.springboot.model.Task;
import net.testproject.springboot.repository.TaskRepository;
import net.testproject.springboot.model.User;
import net.testproject.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/")
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users/{user_id}/tasks")
	public List<Task> getAllTasks(@PathVariable Long user_id){
		logger.info("List all tasks for a user");

		User user = userRepository.findById(user_id)
			.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + user_id));

		return user.getTasks();
	}
	
	@GetMapping("/users/{user_id}/tasks/{task_id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long user_id, @PathVariable Long task_id) {
		logger.info("Get Task Info");

		Task task = taskRepository.findById(task_id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + task_id));
		return ResponseEntity.ok(task);
	}

	@PostMapping("/users/{user_id}/tasks")
	public Task createTask(@PathVariable Long user_id, @RequestBody Task task) {
		logger.info("Create Task");

		User user = userRepository.findById(user_id)
			.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + user_id));
		List<Task> tasks = user.getTasks();
		tasks.add(task);
		return taskRepository.save(task);	
	}
	
	@PutMapping("/users/{user_id}/tasks/{task_id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long user_id, @PathVariable Long task_id, @RequestBody Task taskDetails){
		logger.info("Update Task");

		Task task = taskRepository.findById(task_id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + task_id));
		
		task.setName(taskDetails.getName());
		
		Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/users/{user_id}/tasks/{task_id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long user_id, @PathVariable Long task_id){
		logger.info("Delete Task");

		Task task = taskRepository.findById(task_id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + task_id));
		
		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
