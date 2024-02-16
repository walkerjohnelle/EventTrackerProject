package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.User;

public interface UserService {
List<User> findAllUsers();
}
