package com.softtek.meetup.service

import  com.softtek.meetup.model.User
import  com.softtek.meetup.command.Command

interface UserService{

  User getUserByUsername(String username)
  void save(Command command)

}