package com.example.service;

import java.util.List;

import com.example.model.User;
import com.example.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository repo;
  public UserService(UserRepository repo){
    this.repo = repo;
  }

  public List<User> getAllUsers(){
    return repo.findAll();
  }
}
