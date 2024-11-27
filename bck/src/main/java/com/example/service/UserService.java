package com.example.service;

import java.util.List;
import java.util.Optional;

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

  public Optional<User> getUsersById(int id){
    return repo.findById(id);
  }

  public void addUser(User user){
    repo.save(user);
  }

  public void updateUser(User user){
    repo.save(user);
  }
  
  public void deleteUserById(int id){
    repo.deleteById(id);
  }

}
