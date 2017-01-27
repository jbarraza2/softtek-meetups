package com.softtek.meetup.unit

import spock.lang.Specification;

import  com.softtek.meetup.service.UserService
import  com.softtek.meetup.service.impl.UserServiceImpl
import  com.softtek.meetup.repository.UserRepository

class UserServiceSpec extends Specification {

	UserService userService = new UserServiceImpl();

  UserRepository userRepository = Mock(UserRepository)

  def setup(){
    userService.userRepository = userRepository
  }

	void "should find a user by username"(){
    given:"An username"
      String username = 'josdem'
    when:
      userService.getByUsername(username)
    then:
    1 * userRepository.findByUsername(username)
  }
	
}