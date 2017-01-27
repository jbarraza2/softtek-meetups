package com.softtek.meetup.service

import  com.softtek.meetup.model.User
import  com.softtek.meetup.command.Command

interface UserService{

  User getByUsername(String username)
  User getByEmail(String email)
  User save(Command command)

}