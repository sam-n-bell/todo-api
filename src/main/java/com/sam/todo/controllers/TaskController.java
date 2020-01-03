package com.sam.todo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.todo.entities.Task;
import com.sam.todo.repositories.TaskRepository;




@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
	
	@Autowired 
	private TaskRepository taskRepository;
	
	@GetMapping()
	public List<Task> finalAll() {
		//findAll is from the JpaRepository
		//returns all the entities for this entity in the table
		return taskRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Task> findById(@PathVariable final Long id) {
		return taskRepository.findById(id);
	}
	
	@PostMapping
	public Task addTask(@RequestBody final Task task) {
		taskRepository.save(task);
//		return taskRepository.findAll();
		return taskRepository.findTopByOrderByIdDesc();
	}

}
