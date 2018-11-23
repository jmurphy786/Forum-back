package com.qa.SweetSpot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.SweetSpot.exception.ResourceNotFoundException;
import com.qa.SweetSpot.mySpringBootDatabaseApp.Users;
import com.qa.SweetSpot.repository.*;
import com.qa.SweetSpot.mySpringBootDatabaseApp.*;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class SpringBootController {

	private static final ResponseEntity<?> ResponseEntitiy = null;

	@Autowired
	personRepository personRepo;
	
	@Autowired
	postRepository postRepo;
	
	@Autowired
	commentRepository commentRepo; 	
	
	
	//Method to create a person
	@PostMapping("/person")
	public Users createPerson(@Valid @RequestBody Users mSDM) {
		return personRepo.save(mSDM);
	}
	
	//Get a person by ID
	@GetMapping("/person/{id}")
	public Users getPersonByID(@PathVariable(value = "id")Long personID) {
		return personRepo.findById(personID).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
	}
	
	@RequestMapping("/person")
	public List<Users> getAllUsers() {
		return personRepo.findAll();
	}
	
	
	//Method to create a post
	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public Posts createPost(@Valid @RequestBody Posts pSDM) {
		pSDM.setUpvotes(0);
		return postRepo.save(pSDM);
	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public List<Posts> getAllPosts() {
		return postRepo.findAll();
	}
	
	@RequestMapping(value="/posts/{id}", method=RequestMethod.DELETE)
	public Posts deletePerson(@PathVariable(value = "id")Long id) 
	{
		Posts pSDM = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", id));
		postRepo.delete(pSDM);
		return pSDM;
	}
	
	@RequestMapping(value="/posts/{id}/upvote", method=RequestMethod.PUT)
	public Posts upvote(@PathVariable(value = "id")Long id) 
	{
		Posts pSDM = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", id));
		pSDM.upvote();
		return postRepo.save(pSDM);
	}	
	
	@RequestMapping(value="/posts/{id}/downvote", method=RequestMethod.PUT)
	public Posts downvote(@PathVariable(value = "id")Long id) 
	{
		Posts pSDM = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", id));
		pSDM.downvote();
		return postRepo.save(pSDM);
	}	
	
	@RequestMapping(value="/posts/{id}/upvote", method=RequestMethod.GET)
	public Posts getUpvote(@PathVariable(value = "id")Long id) 
	{
		Posts pSDM = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", id));;
		return pSDM;
	}
	
	//Creates a comment linked to a specific post
	@RequestMapping(value="/posts/{postId}/comments", method=RequestMethod.POST)
	public Comments createComment(@PathVariable (value="postId") Long postId, @Valid @RequestBody Comments comment) {
		return postRepo.findById(postId).map(posts -> {
			comment.setPost(posts);
			return commentRepo.save(comment);}).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", postId));
	}
	

	@RequestMapping(value="/posts/{postId}/comments", method=RequestMethod.GET)
	public Page<Comments> getAllCommentsByPostId(@PathVariable (value = "postId")Long postId, Pageable pageable){
		return commentRepo.findByPostId(postId,pageable);
	}
	
	@RequestMapping(value="/posts/{postId}/comments/{commentId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") long postId, @PathVariable (value = "commentId") long commentId)
	{
		if(!postRepo.existsById(postId)) {
			throw new ResourceNotFoundException("Person", "id", postId);
			}
		return commentRepo.findById(commentId).map(comment -> {
			commentRepo.delete(comment);
			return ResponseEntitiy.ok().build();
		}).orElseThrow(()-> new ResourceNotFoundException("mySpringBootDataModel", "id", commentId));
		}
	}
	

