package com.promineotech.socialMediaApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.socialMediaApi.entity.User;
import com.promineotech.socialMediaApi.repository.UserRepository;
import com.promineotech.socialMediaApi.view.Following;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User login(User user) throws Exception {
		User foundUser = repo.findByusername(user.getUsername());
		
		if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
			return foundUser;
		}else {
			throw new Exception("Invalid username or password.");
		}
	}
	
	public Following follow(Long userId, Long followId) throws Exception {
		//User user = repo.findOne(userId);
		Optional<User> possibleUser;
		User foundUser;
		possibleUser = repo.findById(userId);
		foundUser = possibleUser.get();
		
		//User follow = repo.findOne(followId);		
		Optional<User> possibleFollow;
		User foundFollow;
		possibleFollow = repo.findById(followId);
		foundFollow = possibleFollow.get();
				
		if (foundUser == null) {
			throw new Exception("User does not esist.");
		}
		foundUser.getFollowing().add(foundFollow);
		repo.save(foundUser);
		return new Following(foundUser);
	}
	
	public Following getFollowedUsers(Long userId) throws Exception {
		//User user = repo.findOne(userId);
		Optional<User> possibleUser;
		User foundUser;
		possibleUser = repo.findById(userId);
		foundUser = possibleUser.get();
				
		if (foundUser == null) {
			throw new Exception("User does not exist.");
		}
		return new Following(foundUser);
	}
	
	public User updateProfilePicture(Long userId, String url) throws Exception {
		//User user = repo.findOne(userId);
		Optional<User> possibleUser;
		User foundUser;
		possibleUser = repo.findById(userId);
		foundUser = possibleUser.get();
				
		if (foundUser == null) {
			throw new Exception("User does not exist.");
		}
		foundUser.setProfilePictureUrl(url);
		return repo.save(foundUser);
	}
}
