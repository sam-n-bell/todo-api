package com.sam.todo.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.repositories.UserRepository;
import com.sam.todo.services.JwtService;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//import com.sam.todo.entities.User;

//attempt 2 https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext) {
		this.authenticationManager = authenticationManager;
		this.jwtService = applicationContext.getBean(JwtService.class);
	}
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            ApplicationUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), ApplicationUser.class);

            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } 
    }
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication auth) throws IOException, ServletException {
		
//		Date expirationDate = (new Date(System.currentTimeMillis() + 846_000_000));
//		
//		String token = JWT.create()
//				.withSubject(((User) auth.getPrincipal()).getUsername())
//				.withExpiresAt(expirationDate)
//				.sign(HMAC512("javainuse".getBytes()));
		
		String token = jwtService.createJwt(auth);
		
		User user = (User) auth.getPrincipal();
		String username = user.getUsername();		
//		jwtService.saveJwt(username, token, expirationDate);
		
		Cookie cookie = jwtService.getTokenCookie(token);
		
		response.addHeader("Authorization", "Bearer " + token);
//		Cookie cookie = new Cookie("X-TODO-TOKEN", token);
//        cookie.setDomain("localhost");
//        cookie.setPath("/");
        response.addCookie(cookie);
				
	}

}
