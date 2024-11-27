package com.example.controller;

import java.util.List;

import com.example.model.User;
import com.example.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAll(){
    return userService.getAllUsers();
  }
  
}
