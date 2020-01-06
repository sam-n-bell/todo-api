package com.sam.todo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="USER_JWT", schema = "todos" )
public class UserJWT implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774601895847758221L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "jwt", nullable = false)
	private String jwt;
	
	@Column(name = "date_issued", nullable = false)
	private Date date_issue;
	
	@Column(name = "expired", nullable = false)
	@Type(type="yes_no")
	private boolean expired;

	@ManyToOne //many tokens to one user
	@JoinColumn(name="user_id")
	private ApplicationUser applicationUser;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public ApplicationUser getUser() {
		return this.applicationUser;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(ApplicationUser user) {
		this.applicationUser = user;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	/**
	 * @return the date_issue
	 */
	public Date getDate_issue() {
		return date_issue;
	}

	/**
	 * @param date_issue the date_issue to set
	 */
	public void setDate_issue(Date date_issue) {
		this.date_issue = date_issue;
	}

	/**
	 * @return the expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
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
