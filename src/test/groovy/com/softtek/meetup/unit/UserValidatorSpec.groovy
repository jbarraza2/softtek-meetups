package com.softtek.meetup.unit

import spock.lang.Specification

import org.springframework.validation.Errors

import com.softtek.meetup.service.LocaleService
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
    def command = new UserCommand(username:'josdem',password:'password',passwordConfirmation:'p4ssword',email:'josdem@email.com',name:'name',lastname:'lastname')
    when: "We validate user"
    localeService.getMessage('user.validation.password.equals') >> 'The passwords are not equals'
    userValidator.validate(command, errors)
    then:"We expect an error"
    1 * errors.reject('password','The passwords are not equals')
  }

  void "should create an user"(){
    given: "An user command"
    def command = new UserCommand(username:'josdem',password:'password',passwordConfirmation:'password',email:'josdem@email.com',name:'name',lastname:'lastname')
    when: "We validate user"
    localeService.getMessage('user.validation.password.equals') >> 'The passwords are not equals'
    userValidator.validate(command, errors)
    then:"We expect an error"
    0 * errors.reject('password','The passwords are not equals')
  }

}