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
    validateUsername(errors, userCommand);
  }

  private void validateUsername(Errors errors, UserCommand command){
    userService.getByUsername(command.getUsername()).subscribe(
      username -> errors.rejectValue("username", "user.validation.duplicated.username")
    );
  }

}
