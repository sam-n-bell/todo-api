package com.sam.todo.services;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
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
	
	@Value("${jwt.secret}")
	private String encryptionKey;
	
	@Value("${jwt.cookie.name}")
	private String cookieName;
	
	@Value("${jwt.request.header.name}")
	private String requestHeader;
	
	public String createJwt(Authentication auth) {
		Date expirationDate = (new Date(System.currentTimeMillis() + 846_000_000));
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(expirationDate)
				.sign(HMAC512(encryptionKey.getBytes()));
		return token;
	}

	public void saveJwt(String username, String token, Date expirationDate) {
		
		
		ApplicationUser applicationUser = userRepository.findByUsername(username);
		UserJWT userJwt = new UserJWT();
		userJwt.setExpirationDate(expirationDate);
		userJwt.setJwt(token);
		userJwt.setApplicationUser(applicationUser);
		userJwt.setDateIssued(new Date());
		jwtRepository.save(userJwt);
		
	}

	public Cookie getTokenCookie(String token) {
		Cookie cookie = new Cookie(cookieName, token);
		cookie.setDomain("localhost");
		cookie.setPath("/");
		return cookie;
//		return new Cookie(cookieName, token);
	}
	

	public String getTokenFromHeader(HttpServletRequest request) {
		return request.getHeader(requestHeader);

	}
	
	

}
