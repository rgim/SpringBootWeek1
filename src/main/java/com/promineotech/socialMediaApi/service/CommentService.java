package com.promineotech.socialMediaApi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.socialMediaApi.entity.Comment;
import com.promineotech.socialMediaApi.entity.Post;
import com.promineotech.socialMediaApi.entity.User;
import com.promineotech.socialMediaApi.repository.CommentRepository;
import com.promineotech.socialMediaApi.repository.PostRepository;
import com.promineotech.socialMediaApi.repository.UserRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository repo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Comment createComment(Comment comment, Long userId, Long postId) throws Exception {
		//User user = userRepo.findOne(userId);
		Optional<User> possibleUser;
		User foundUser;
		possibleUser = userRepo.findById(userId);
		foundUser = possibleUser.get();
		
		//Post post = postRepo.findOne(postId);
		Optional<Post> possiblePost;
		Post foundPost;
		possiblePost = postRepo.findById(postId);
		foundPost = possiblePost.get();
		
		if (foundUser == null || foundPost == null) {
			throw new Exception("User or Post does not exist.");
		}
		comment.setDate(new Date());
		comment.setUser(foundUser);
		comment.setPost(foundPost);
		return repo.save(comment); //?????
	}
	
	public void deleteComment(Long commentId) {
		repo.deleteById(commentId);
	}
}
