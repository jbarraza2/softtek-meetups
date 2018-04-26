package com.softtek.meetup.unit;

import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.meetup.repository.UserRepository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void shouldGetDefaultUser(){
    userRepository.findByUsername("josdem").subscribe(System.out::println);
    System.out.print("Hello World!");
  }
  

}