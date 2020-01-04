package com.sam.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sam.todo.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	
}
