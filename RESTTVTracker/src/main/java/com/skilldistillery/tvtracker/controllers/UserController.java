package com.skilldistillery.tvtracker.controllers;

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

import com.skilldistillery.tvtracker.entities.User;
import com.skilldistillery.tvtracker.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService uS;
	@Autowired 

	@GetMapping(path = {"users", "users/"})
	public List<User> index() {
		return uS.findAllUsers();
	}

	@GetMapping("users/{userId}")
	public User show(@PathVariable("userId") int userId, HttpServletResponse rsp) {
		User user = uS.findById(userId);

		if (user == null) {
			rsp.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public User createUser(@RequestBody User user, HttpServletResponse rsp) {
		User createdUser = uS.createUser(user);
		if (createdUser == null) {
			rsp.setStatus(404);
		}
		return createdUser;
	}

	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User user, HttpServletResponse rsp) {
		try {
			user = uS.updateUser(userId, user);
			if (user == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@DeleteMapping("users/{userId}")
	public void deleteUser(@PathVariable("userId") int userId, HttpServletResponse rsp) {
		User user = uS.findById(userId);

		try {
			if (user != null) {
				uS.deleteUser(userId);
				rsp.setStatus(204);
			} else {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			e.printStackTrace();
		}
	}

}
