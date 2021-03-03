package com.promineotech.socialMediaApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.socialMediaApi.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByusername(String userName);	
}
