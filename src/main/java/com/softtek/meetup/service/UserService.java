package com.softtek.meetup.service;

import  com.softtek.meetup.model.User;
import  com.softtek.meetup.command.Command;

public interface UserService {

  User getByUsername(String username);
  User getByEmail(String email);

}