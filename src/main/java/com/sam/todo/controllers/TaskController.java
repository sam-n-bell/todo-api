package com.sam.todo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.entities.Task;
import com.sam.todo.repositories.TaskRepository;
import com.sam.todo.repositories.UserRepository;
import com.sam.todo.services.TaskService;




@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
	
	@Autowired 
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping()
	@PostFilter("filterObject.applicationUser.username == authentication.name")
	public List<Task> finalAll() {
		return taskRepository.findAll();
		/**
		 * 	@PostFilter("filterObject.applicationUser.username == authentication.name")
		 *  When above is included above method name, the below lines are not needed
		 *  ApplicationUser applicationUser = getCurrentUser();
		 *  return taskRepository.findByApplicationUserId(applicationUser.getId());
		 */
		
	}
	
	@GetMapping(value = "/{id}")
	@PostAuthorize("returnObject != null && returnObject.isPresent() ? returnObject.get().applicationUser.username == authentication.name : true")
	public Optional<Task> findById(@PathVariable final Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		return optionalTask;
	}
	
	@PostMapping
	public Task addTask(@RequestBody final Task task) {
		ApplicationUser applicationUser = getCurrentUser();
		task.setApplicationUser(applicationUser);
		taskRepository.save(task);
		return taskRepository.findTopByOrderByIdDesc();
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable final Long id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent() && taskService.taskBelongsToCurrentUser(task, getCurrentUser())) {
			taskRepository.delete(task.get());
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateTask(@PathVariable final Long id, @RequestBody final Task task) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		
		if (taskService.taskBelongsToCurrentUser(optionalTask, getCurrentUser())) {
			task.setApplicationUser(optionalTask.get().getApplicationUser()); // in case task does not come in with a nonnull user
			taskRepository.save(task);
			return new ResponseEntity<>(task, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
	private ApplicationUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByUsername(authentication.getName());		
	}
	
	
	
	

}
