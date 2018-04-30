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
    return UserCommand.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserCommand userCommand = (UserCommand) target;
    validatePasswords(errors, userCommand);
    validateUsername(errors, userCommand);
    validateEmail(errors, userCommand);
  }

  private void validatePasswords(Errors errors, UserCommand command){
    if (!command.getPassword().equals(command.getPasswordConfirmation())){
      errors.rejectValue("password", "user.validation.password.equals");
    }
  }

  private void validateUsername(Errors errors, UserCommand command){
    userService.getByUsername(command.getUsername()).subscribe(
      user -> errors.rejectValue("username", "user.validation.duplicated.username")
    );
  }

  private void validateEmail(Errors errors, UserCommand command){
    userService.getByEmail(command.getEmail()).subscribe(
      user -> errors.rejectValue("email", "user.validation.duplicated.email")
    );
  }

}
