package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.services.UserService;

@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> index() {
		return userService.findAllUsers();
	}
}
