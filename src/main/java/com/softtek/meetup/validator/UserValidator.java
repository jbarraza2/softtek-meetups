package com.softtek.meetup.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.meetup.command.UserCommand;
import com.softtek.meetup.service.LocaleService;
import com.softtek.meetup.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
  private LocaleService localeService;
  @Autowired
  private UserService userService;

  @Override
  public boolean supports(Class<?> clazz) {
    UserCommand.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserCommand userCommand = (UserCommand) target;
    validatePasswords(errors, userCommand);
    validateUsername(errors, userCommand);
    validateEmail(errors, userCommand);
  }

  private void validatePasswords(Errors errors, UserCommand command) {
    if (!command.password.equals(command.passwordConfirmation)){
      errors.rejectValue("password", "user.validation.password.equals");
    }
  }

  private void validateUsername(Errors errors, UserCommand command){
    if(userService.getByUsername(command.username)){
      errors.rejectValue("username", "user.validation.duplicated.username");
    }
  }

  private void validateEmail(Errors errors, UserCommand command){
    if(userService.getByEmail(command.email)){
      errors.rejectValue("email", "user.validation.duplicated.email");
    }
  }

}
