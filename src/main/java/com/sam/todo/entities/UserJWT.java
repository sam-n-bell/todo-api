package com.sam.todo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "JWT_ID", nullable = false)
	private Long jwt_id;
	
	@Column(name = "JWT", nullable = false)
	private String jwt;
	
	@Column(name = "USER_ID", nullable = false)
	private Long user_id;
	
	@Column(name = "DATE_ISSUED", nullable = false)
	private Date date_issue;
	
	@Column(name = "EXPIRED", nullable = false)
	@Type(type="yes_no")
	private boolean expired;

	/**
	 * @return the jwt_id
	 */
	public Long getJwt_id() {
		return jwt_id;
	}

	/**
	 * @param jwt_id the jwt_id to set
	 */
	public void setJwt_id(Long jwt_id) {
		this.jwt_id = jwt_id;
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
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
