package com.sam.todo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@RequestMapping({ "/hello" })
	public String login() {
		return "Login";
	}
	
}
