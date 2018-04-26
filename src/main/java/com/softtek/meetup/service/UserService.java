package com.softtek.meetup.service;

import reactor.core.publisher.Mono;
import org.springframework.security.core.userdetails.UserDetails;

import  com.softtek.meetup.model.User;
import  com.softtek.meetup.command.Command;

public interface UserService {

  Mono<UserDetails> getByUsername(String username);
  Mono<UserDetails> getByEmail(String email);
  void save(User user);

}