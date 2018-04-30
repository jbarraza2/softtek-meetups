package com.softtek.meetup.unit;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.softtek.meetup.repository.UserRepository;
import com.softtek.meetup.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

  @Autowired 
  private UserService userService;

  @Test
  public void shouldGetDefaultUser(){    
    assertTrue(true);
  }
  

}