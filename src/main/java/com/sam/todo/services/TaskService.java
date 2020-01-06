package com.sam.todo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.entities.Task;

@Service
public class TaskService {
	

	/**
	 * Checks if a given task exists and if so, does it belong to the given applicationUser 
	 * (comparing id of user to id of user who made task)
	 * @param optionalTask
	 * @param applicationUser
	 * @return
	 */
	public boolean taskBelongsToCurrentUser(Optional<Task> optionalTask, ApplicationUser applicationUser) {
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			if (task.getApplicationUser().getId() == applicationUser.getId()) {
				return true;
			}
		}
		return false;
	}
	
}
