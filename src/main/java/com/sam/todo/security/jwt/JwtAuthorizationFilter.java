package com.sam.todo.security.jwt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sam.todo.services.JwtService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	private JwtService jwtService;

//	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//		// TODO Auto-generated constructor stub
//	}
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext) {
		super(authenticationManager);
		this.jwtService = applicationContext.getBean(JwtService.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		// because HEB didn't use this header name in their app im not using it here
		//		String header = request.getHeader("Authorization");
//		
//		if (header == null || !header.startsWith("Bearer ")) {
//			chain.doFilter(request, response);
//			return;
//		}
				
//		String header = request.getHeader("X-TODO-TOKEN");
		String header = getTokenHeader(request);
		
		if (header == null) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	
		chain.doFilter(request, response);
	}
	
	/**
	 * Reads the JWT from the header and authenticates it
	 * If good, the user is set in the SecurityContext and the request is allowed to procede
	 * @param request
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// because HEB didn't use this header name in their app im not using it here
//		String token = request.getHeader("X-TODO-TOKEN");
		String token = getTokenHeader(request);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512("javainuse".getBytes()))
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
	
	private String getTokenHeader(HttpServletRequest request) {
		return jwtService.getTokenFromHeader(request);
	}
	
	

}
