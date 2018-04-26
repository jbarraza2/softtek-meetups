package com.softtek.meetup.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import  com.softtek.meetup.model.User;
import  com.softtek.meetup.command.Command;
import  com.softtek.meetup.binder.UserBinder;
import  com.softtek.meetup.service.UserService;
import  com.softtek.meetup.service.RecoveryService;
import  com.softtek.meetup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserBinder userBinder;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RecoveryService recoveryService;

  public Mono<User> getByUsername(String username){
    return userRepository.findByUsername(username);      
  }

  public Mono<User> getByEmail(String email){
    return userRepository.findByEmail(email);
  }


}
