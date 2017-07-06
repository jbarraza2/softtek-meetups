package com.softtek.meetup.validator

import org.springframework.validation.Validator
import org.springframework.validation.Errors
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.command.RecoveryPasswordCommand
import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.service.RecoveryService

@Component
class RecoveryValidator implements Validator {

  @Autowired
  LocaleService localeService
  @Autowired
  RecoveryService recoveryService

 @Override
  boolean supports(Class<?> clazz) {
    RecoveryPasswordCommand.class.equals(clazz)
  }

  @Override
  void validate(Object target, Errors errors) {
    RecoveryPasswordCommand command = (RecoveryPasswordCommand) target
  }


}