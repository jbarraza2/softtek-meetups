package com.softtek.meetup.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.Valid;

import com.softtek.meetup.model.User;
import com.softtek.meetup.binder.UserBinder;
import com.softtek.meetup.command.UserCommand;
import com.softtek.meetup.validator.UserValidator;
import com.softtek.meetup.service.UserService;
import com.softtek.meetup.repository.UserRepository;
import com.softtek.meetup.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserValidator userValidator;
  @Autowired
  private UserService userService;
  @Autowired
  private UserBinder userBinder;
  @Autowired
  private UserRepository userRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.addValidators(userValidator);
  }

  @RequestMapping(value = "/users/create")  
  public String create(Model model){
    model.addAttribute("userCommand", new UserCommand());
    return "users/create";
  }

  @RequestMapping(method = POST)
  public String save(UserCommand command) {
    log.info("Saving user:" + command.getUsername());
    User user = userBinder.bindUser(command);

    userRepository.findByUsername(command.getUsername())
      .subscribe(
        savedUser -> log.info("Saved user: " + savedUser),
        error -> { throw new BusinessException(error); },
        () -> userRepository.save(user).subscribe()
      );

    return "redirect:/";
  }

}
