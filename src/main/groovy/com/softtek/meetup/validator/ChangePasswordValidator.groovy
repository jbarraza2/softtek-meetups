package com.softtek.meetup.validator

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.validation.Validator
import org.springframework.validation.Errors
import org.springframework.stereotype.Component

@Component
class ChangePasswordValidator implements Validator {

  @Autowired
  LocaleService localeService

  @Override
  boolean supports(Class<?> clazz) {
    ChangePasswordCommand.class.equals(clazz)
  }

  @Override
  void validate(Object target, Errors errors) {
    ChangePasswordCommand command = (ChangePasswordCommand) target
    validatePasswords(errors, command)
  }

  def validatePasswords(Errors errors, ChangePasswordCommand command) {
    if (!command.password.equals(command.passwordConfirmation)){
      errors.rejectValue('password', 'error.password', localeService.getMessage('user.validation.password.equals'))
    }
  }

}
