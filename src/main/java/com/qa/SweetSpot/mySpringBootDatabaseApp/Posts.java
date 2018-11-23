package com.qa.SweetSpot.mySpringBootDatabaseApp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qa.SweetSpot.mySpringBootDatabaseApp.*;

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Posts implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String title;
	
	private String username;
	
	@NotBlank
	private String message;
	
	private Integer upvotes;
	

	private Integer numComments;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	public long getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getUpvotes() {
		return this.upvotes;
	}
	
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}
	
	public void upvote() {
		this.upvotes = this.upvotes + 1;
	}
	
	public void downvote() {
		this.upvotes = this.upvotes - 1;
	}
	
	public Date getDate() {
		return this.creationDate;
	}

	
	public Integer getNumComments() {
		return this.numComments;
	}
}
