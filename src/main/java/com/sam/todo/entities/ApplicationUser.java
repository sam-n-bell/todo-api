package com.sam.todo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="APP_USERS", schema="todos")
public class ApplicationUser implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7836886278022726618L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "username", nullable = false)
	private String username;
	
//	@OneToMany(mappedBy = "applicationUser") //one user to many tasks
//	private List<Task> tasks;
//	
//	@OneToMany(mappedBy = "applicationUser") //one user to many tokens
//	private List<UserJWT> jwts; 
	
	/**
	 * @return the jwts
	 */
//	public List<UserJWT> getJwts() {
//		return jwts;
//	}
//	/**
//	 * @param jwts the jwts to set
//	 */
//	public void setJwts(List<UserJWT> jwts) {
//		this.jwts = jwts;
//	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the tasks
	 */
//	public List<Task> getTasks() {
//		return tasks;
//	}
//	/**
//	 * @param tasks the tasks to set
//	 */
//	public void setTasks(List<Task> tasks) {
//		this.tasks = tasks;
//	}

	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the password
	 */
	@JsonIgnore //prevents password from being shown in HTTP Response
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
