package com.sam.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sam.todo.entities.ApplicationUser;

@Component
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

	//if there is an email fiend, you can use this
//	User findByEmail(String email);
	
	ApplicationUser findByUsername(String username);
	
	
}
