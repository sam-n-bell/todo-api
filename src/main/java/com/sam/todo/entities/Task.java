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

import com.fasterxml.jackson.core.sym.Name;

@Entity
@Table(name="tasks", schema = "todos" )
public class Task implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3584743544045043053L;
	/**
	 * 
	 */
	
	@ManyToOne //many tasks to one user
	@JoinColumn(name="user_id")
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false) //name is the name in the table itself
	private long id;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "due_date", nullable = false)
	private Date due_date;
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the due_date
	 */
	public Date getDue_date() {
		return due_date;
	}
	/**
	 * @param due_date the due_date to set
	 */
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
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
