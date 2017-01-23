package com.softtek.meetup.unit

import spock.lang.Specification

import org.springframework.validation.Errors

import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.command.Command
import com.softtek.meetup.command.UserCommand
import com.softtek.meetup.validator.UserValidator

class UserValidatorSpec extends Specification {

	UserValidator userValidator = new UserValidator()

	Errors errors = Mock(Errors)
  LocaleService localeService = Mock(LocaleService)

  void setup(){
    userValidator.localeService = localeService
  }

  void "should not create an user since theirs passwords are not equals"(){
    given: "An user command"
    Command command = new UserCommand(username:'josdem',password:'password',passwordConfirmation:'p4ssword',email:'josdem@email.com',name:'name',lastname:'lastname')
    when: "We validate user"
    localeService.getMessage('user.validation.password.equals') >> 'The passwords are not equals'
    userValidator.validate(command, errors)
    then:"We expect an error"
    1 * errors.reject('password','The passwords are not equals')
  }

  void "should create an user"(){
    given: "An user command"
    Command command = new UserCommand(username:'josdem',password:'password',passwordConfirmation:'password',email:'josdem@email.com',name:'name',lastname:'lastname')
    when: "We validate user"
    localeService.getMessage(_ as String) >> 'Passwords are bad formed'
    userValidator.validate(command, errors)
    then:"We expect an error"
    0 * errors.reject('password','The passwords are not equals')
  }

  void "should accept characters and numbers in password"(){
    given:"A user command"
    Command command = new UserCommand(username:'josdem',password:'p4ssword', passwordConfirmation:'p4ssword', name:'josdem',lastname:'lastname',email:'josdem@email.com')
    when:"We validate passwords"
    localeService.getMessage(_ as String) >> 'Passwords are bad formed'
    userValidator.validate(command, errors)
    then:"We expect everything is going to be all right"
    0 * errors.reject('password', _ as String)
  }

  void "should accept dash character in password"(){
    given:"A user command"
    Command command = new UserCommand(username:'josdem',password:'pa-4ssword', passwordConfirmation:'pa-4ssword', name:'josdem',lastname:'lastname',email:'josdem@email.com')
    when:"We validate passwords"
    localeService.getMessage(_ as String) >> 'Passwords are bad formed'
    userValidator.validate(command, errors)
    then:"We expect everything is going to be all right"
    0 * errors.reject('password', _ as String)
  }

  void "should accept underscore character in password"(){
    given:"A user command"
    Command command = new UserCommand(username:'josdem',password:'pa_4ssword', passwordConfirmation:'pa_4ssword', name:'josdem',lastname:'lastname',email:'josdem@email.com')
    when:"We validate passwords"
    localeService.getMessage(_ as String) >> 'Passwords are bad formed'
    userValidator.validate(command, errors)
    then:"We expect everything is going to be all right"
    0 * errors.reject('password', _ as String)
  }

  void "should accept dot character in password"(){
    given:"A user command"
    Command command = new UserCommand(username:'josdem',password:'pa.4ssword', passwordConfirmation:'pa.4ssword', name:'josdem',lastname:'lastname',email:'josdem@email.com')
    when:"We validate passwords"
    localeService.getMessage(_ as String) >> 'Passwords are bad formed'
    userValidator.validate(command, errors)
    then:"We expect everything is going to be all right"
    0 * errors.reject('password', _ as String)
  }

}