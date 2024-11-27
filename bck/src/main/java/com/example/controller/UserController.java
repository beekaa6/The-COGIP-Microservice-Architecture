package com.example.controller;

import java.util.List;
import java.util.Optional;

import com.example.model.User;
import com.example.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAll(){
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getById(@PathVariable int id){
    return userService.getUsersById(id);
  }

  @PostMapping()
  public void addNewUser(@RequestBody User user){
    user.setPassword(encoder.encode(user.getPassword()));
    userService.addUser(user);
  }

  @PutMapping()
  public void updateNewUser(@RequestBody User user){
    user.setPassword(encoder.encode(user.getPassword()));
    userService.updateUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id){
    userService.deleteUserById(id);
  }
}
