package com.softtek.meetup.unit

import spock.lang.Specification;

import  com.softtek.meetup.binder.UserBinder
import  com.softtek.meetup.service.UserService
import  com.softtek.meetup.service.impl.UserServiceImpl
import  com.softtek.meetup.service.RecoveryService
import  com.softtek.meetup.repository.UserRepository
import  com.softtek.meetup.command.Command
import  com.softtek.meetup.command.UserCommand
import  com.softtek.meetup.model.User
import  com.softtek.meetup.model.Role

class UserServiceSpec extends Specification {

	UserService userService = new UserServiceImpl();

  UserRepository userRepository = Mock(UserRepository)
  UserBinder userBinder = new UserBinder()
  RecoveryService recoveryService = Mock(RecoveryService)

  def setup(){
    userService.userRepository = userRepository
    userService.userBinder = userBinder
    userService.recoveryService = recoveryService
  }

	void "should find a user by username"(){
    given:"An username"
      String username = 'josdem'
    when:
      userService.getByUsername(username)
    then:
    1 * userRepository.findByUsername(username)
  }
	
  void "should find a user by email"(){
    given:"An email"
      String email = 'josdem@email.com'
    when:
      userService.getByEmail(email)
    then:
    1 * userRepository.findByEmail(email)
  }

  void "should save an user"(){
    given:"A command"
      Command command = new UserCommand(username:'josdem',password:'password',passwordConfirmation:'password',email:'josdem@email.com',name:'name',lastname:'lastname')
    when:"We save an user"
      User user = userService.save(command)
    then:"We expect user is saved"
    1 * userRepository.save(_ as User)
    1 *  recoveryService.saveRegistrationCode('josdem@email.com')
    user.username == 'josdem'
    user.firstName == 'name'
    user.lastName == 'lastname'
    user.email == 'josdem@email.com'
    user.role == Role.USER
    user.password.size() == 60
  }
}