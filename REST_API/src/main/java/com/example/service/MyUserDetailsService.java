package com.example.service;

import com.example.model.User;
import com.example.model.UserPrincipal;
import com.example.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService{
  private final UserRepository userRepository;
  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
      .orElseThrow(()-> new UsernameNotFoundException("Not found user: "+username));
    
    // return org.springframework.security.core.userdetails.User.builder()
    //       .username(user.getUsername())
    //       .password(user.getPassword())
    //       .roles(user.getRole().name())
    //       .build();
    
    return new UserPrincipal(user);
  }
  
}
