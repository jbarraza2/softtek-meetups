package com.softtek.meetup.repository;

import com.softtek.meetup.model.User;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
  Mono<UserDetails> findByUsername(String username);
  Mono<UserDetails> findByEmail(String email);
}