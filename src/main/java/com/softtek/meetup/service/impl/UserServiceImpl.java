package com.softtek.meetup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

import  com.softtek.meetup.model.User;
import  com.softtek.meetup.command.Command;
import  com.softtek.meetup.service.UserService;
import  com.softtek.meetup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  public Mono<UserDetails> getByUsername(String username){
    return userRepository.findByUsername(username);      
  }

  public Mono<User> getByEmail(String email){
    return userRepository.findByEmail(email);
  }

  public void save(User user){
    userRepository.save(user);
  }

}
