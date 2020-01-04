package com.sam.todo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Component;

import com.sam.todo.entities.Task;


@Component
public interface TaskRepository extends JpaRepository<Task, Long> {
	//Task is the model, Long is the ID
	
	Task findTopByOrderByIdDesc();
	
	public List<Task>findByUserId(long id);
	
//	public List<Task> findTaskByUserId(long id);
}
