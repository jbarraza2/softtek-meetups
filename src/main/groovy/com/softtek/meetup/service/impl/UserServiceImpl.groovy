package com.softtek.meetup.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import  com.softtek.meetup.model.User
import  com.softtek.meetup.command.Command
import  com.softtek.meetup.binder.UserBinder
import  com.softtek.meetup.service.UserService
import  com.softtek.meetup.repository.UserRepository

@Service
class UserServiceImpl implements UserService {

  @Autowired
  UserBinder userBinder
  @Autowired
  UserRepository userRepository

  User getByUsername(String username){
    userRepository.findByUsername(username)
  }

  User getByEmail(String email){
    userRepository.findByEmail(email)
  }

  void save(Command command){
    User user = userBinder.bindUser(command)
    userRepository.save(user)
  }

}