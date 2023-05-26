package com.codingdojo.oriana.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.oriana.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	//SELECT * FROM users WHERE email = <EMAIL QUE RECIBIMOS>
	
	List<User> findAll();
	
	User findByEmail(String email); 
}
