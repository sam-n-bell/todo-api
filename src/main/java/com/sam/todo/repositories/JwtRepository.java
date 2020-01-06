package com.sam.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.todo.entities.UserJWT;

public interface JwtRepository extends JpaRepository<UserJWT, Long> {
	
	

}
