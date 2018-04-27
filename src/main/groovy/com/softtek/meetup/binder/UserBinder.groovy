package com.softtek.meetup.binder

import java.util.UUID

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

import com.softtek.meetup.model.User
import com.softtek.meetup.model.Role
import com.softtek.meetup.command.Command

@Component
class UserBinder {

  @Autowired
  PasswordEncoder passwordEncoder

  User bindUser(Command command){
    User user = new User(UUID.randomUUID().toString(), command.username, passwordEncoder.encode(command.password))
    user.firstname = command.firstname
    user.lastname = command.lastname
    user.email = command.email
    user
  }

}