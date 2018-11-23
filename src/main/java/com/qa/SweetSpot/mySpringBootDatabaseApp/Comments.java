package com.qa.SweetSpot.mySpringBootDatabaseApp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comments implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	private String message;

	private Integer upvotes = 0;
	
	@Column(nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "postId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Posts post;



	
	public long getId() {
		return this.id;
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
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	public void setPost(Posts post) {
		this.post = post;
	}
	
	public Posts getPost() {
		return this.post;
	}
}
