package com.promineotech.socialMediaApi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.socialMediaApi.entity.Post;
import com.promineotech.socialMediaApi.entity.User;
import com.promineotech.socialMediaApi.repository.PostRepository;
import com.promineotech.socialMediaApi.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Iterable<Post> getAllPosts() {
		return repo.findAll();
	}
	
	public Post getPost(Long id) {
		//return repo.findOne(id);
		Optional<Post> possible;
		Post foundPost;
		possible = repo.findById(id);
		foundPost = possible.get();
		return foundPost;
	}
	
	public Post updatePost(Post post, Long id) throws Exception {
		Optional<Post> possible;
		Post foundPost;
		possible = repo.findById(id);
		foundPost=possible.get();
		if(foundPost != null) {
			foundPost.setContent(post.getContent());
			repo.save(foundPost);
		}
		return foundPost;
		/*
		 * Post foundPost = repo.findOne(id); 
		 * if (foundPost == null) { 
		 * 		throw new Exception("Post not found."); 
		 * } 
		 * foundPost.setContent(post.getContent());
		 * return post.save(foundPost);
		 */
	}
	
	public Post createPost(Post post, Long userId) throws Exception {
		//User user = userRepo.findOne(id); 
		Optional<User> possibleUser;
		User foundUser;
		possibleUser = userRepo.findById(userId);
		foundUser = possibleUser.get();
		  
		if (foundUser == null) { 
			throw new Exception("User not found."); 
		} 
		post.setDate(new Date()); 
		post.setUser(foundUser);
		return repo.save(post);
		 
	}
}
