package com.wipro.velocity.hypotheek.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wipro.velocity.hypotheek.model.User;

public interface UserRepository extends MongoRepository<User, String> {

public User findByEmailId(String emailId);
	

	public Optional<User> findById(String id);
	 
}
