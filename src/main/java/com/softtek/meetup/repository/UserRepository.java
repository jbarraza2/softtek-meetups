package com.softtek.meetup.repository;

import com.softtek.meetup.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
  Mono<UserDetails> findByUsername(String username);
  Mono<User> findByEmail(String email);
  Mono<User> save(User user);
}