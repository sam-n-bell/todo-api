package com.sam.todo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.sam.todo.entities.User;
import org.springframework.security.core.userdetails.User;

import com.sam.todo.entities.ApplicationUser;
import com.sam.todo.repositories.UserRepository;

import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * When a user tries to authenticate, this method
     * receives the username, searches the DB, and if 
     * found, returns an instance of Spring User. The properties (username, password)
     * are checked against the credenetials passed by the user in the login request.
     * This is done outside this class by the Spring framework.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	ApplicationUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}