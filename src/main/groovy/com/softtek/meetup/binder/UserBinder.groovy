package com.softtek.meetup.binder

import org.springframework.stereotype.Component
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import com.softtek.meetup.model.User
import com.softtek.meetup.model.Role
import com.softtek.meetup.command.Command

@Component
class UserBinder {

  User bindUser(Command command){
    User user = new User()
    user.username = command.username
    user.password = new BCryptPasswordEncoder().encode(command.password)
    user.role = Role.USER
    user.firstname = command.firstname
    user.lastname = command.lastname
    user.email = command.email
    user
  }

}