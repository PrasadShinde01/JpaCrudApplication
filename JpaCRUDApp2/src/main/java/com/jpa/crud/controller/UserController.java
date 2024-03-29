package com.jpa.crud.controller;

//package com.jpa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.crud.entity.Usernew;
import com.jpa.crud.repository.UserRepository;
import com.jpa.crud.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public List<Usernew>getAllUsers(){
		return this.userRepository.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public Usernew getUserById(@PathVariable(value="id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
	}
	
	//create user
	@PostMapping
	public Usernew createUser(@RequestBody Usernew user) {
		return this.userRepository.save(user);
	}
	
	//update user
	@PutMapping("/{id}")
	public Usernew updateUser(@RequestBody Usernew user,@PathVariable("id") long userId) {
		Usernew existingUser=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}
	
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Usernew>deleteUser(@PathVariable("id") long userId){
		Usernew existingUser=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with id:" +userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
	
}
