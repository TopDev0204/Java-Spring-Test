package net.testproject.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Iterable;

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
import org.springframework.web.bind.annotation.RequestParam;

import net.testproject.springboot.exception.ResourceNotFoundException;
import net.testproject.springboot.model.User;
import net.testproject.springboot.repository.UserRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public Iterable<User> getAllUsers(               
		@RequestParam(defaultValue = "0") Integer pageNo,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "id") String sortBy
		){
			logger.info("List all users");

			Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

			return userRepository.findAll(pageable);
	}		
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		logger.info("Create user");

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new ResourceNotFoundException("Username '" + user.getUsername() + "' already exists");
		}

		return userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		logger.info("Get User info");

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
		logger.info("Update user");

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		logger.info("Delete user");

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
				userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
