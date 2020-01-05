package com.sam.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sam.todo.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	//if there is an email fiend, you can use this
//	User findByEmail(String email);
	
	User findByUsername(String username);
	
	
}
