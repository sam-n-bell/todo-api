package com.sam.todo.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.repositories.UserRepository;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping({ "/hello" })
	public String login() {
		return "Login";
	}
	
	@GetMapping({ "/register" })
	public String register() {
		return "registration route";
	}
	
	@PostMapping({"/register"})
	public ResponseEntity<?> createUser(@RequestBody final ApplicationUser user) {
		ApplicationUser existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		}
	}
	
}
