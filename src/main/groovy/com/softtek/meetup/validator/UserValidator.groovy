package com.softtek.meetup.validator

import org.springframework.validation.Validator
import org.springframework.validation.Errors
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.command.UserCommand
import com.softtek.meetup.service.LocaleService

@Component
class UserValidator implements Validator {

	@Autowired
  LocaleService localeService

  @Override
  boolean supports(Class<?> clazz) {
    UserCommand.class.equals(clazz)
  }

  @Override
  void validate(Object target, Errors errors) {
    UserCommand UserCommand = (UserCommand) target
    validatePasswords(errors, UserCommand)
  }

  def validatePasswords(Errors errors, UserCommand command) {
    if (!command.password.equals(command.passwordConfirmation)){
      errors.reject('password', localeService.getMessage('user.validation.password.equals'))
    }
  }

}