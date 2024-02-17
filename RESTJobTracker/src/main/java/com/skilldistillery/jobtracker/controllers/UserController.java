package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.services.UserService;

@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService uS;

	@GetMapping("users")
	public List<User> index() {
		return uS.findAllUsers();
	}

	@GetMapping("users/{userId}")
	public User getUserById(@PathVariable int userId) {
		return uS.findUserById(userId);
	}

	@PostMapping("users")
	public User createUser(@RequestBody User user) {
		return uS.createUser(user);
	}

	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable int userId, @RequestBody User user) {
		return uS.updateUser(userId, user);
	}

	@DeleteMapping("users/{userId}")
	public boolean deleteUser(@PathVariable int userId) {
		return uS.deleteUser(userId);
	}
}
