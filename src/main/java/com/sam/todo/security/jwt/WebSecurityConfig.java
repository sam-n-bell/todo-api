package com.sam.todo.security.jwt;

import java.util.Arrays;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sam.todo.security.RestAuthFailureHandler;
import com.sam.todo.security.RestAuthSuccessHandler;


@Configuration
@EnableWebSecurity // primary spring security annotation used to enable web security in project
//@EnableGlobalMethodSecurity this would enable method level security based on annotations. https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private LogoutHandler logoutHandler;
	private RestAuthSuccessHandler restAuthSuccessHandler;
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .csrf()
        .disable()
        .cors();
//    	http
//        .formLogin()
//        .loginProcessingUrl("/login")
//        .usernameParameter("email")
//        .successHandler(this.restAuthSuccessHandler)
//        .failureHandler(customAuthenticationFailureHandler());
//    	http
//    	.logout().logoutUrl("/logout")
//    	.defaultLogoutSuccessHandlerFor(this.logoutHandler);
    	http
    	.authorizeRequests()
    	.antMatchers("/login").permitAll()
    	.antMatchers("/register").permitAll()
    	.antMatchers("/tasks/**").permitAll()
    	.anyRequest().authenticated();
    	http
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
    	return new RestAuthFailureHandler();
    }

}