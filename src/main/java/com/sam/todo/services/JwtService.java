package com.sam.todo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.entities.UserJWT;
import com.sam.todo.repositories.JwtRepository;
import com.sam.todo.repositories.UserRepository;

@Service
public class JwtService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtRepository jwtRepository;

	public void saveJwt(String username, String token, Date expirationDate) {
		
		
		ApplicationUser applicationUser = userRepository.findByUsername(username);
		UserJWT userJwt = new UserJWT();
		userJwt.setExpirationDate(expirationDate);
		userJwt.setJwt(token);
		userJwt.setApplicationUser(applicationUser);
		userJwt.setDateIssued(new Date());
		jwtRepository.save(userJwt);
		
	}
	
	

}
