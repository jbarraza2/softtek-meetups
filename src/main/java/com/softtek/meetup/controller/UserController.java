package com.softtek.meetup.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;

import com.softtek.meetup.model.User;
import com.softtek.meetup.binder.UserBinder;
import com.softtek.meetup.command.UserCommand;
import com.softtek.meetup.validator.UserValidator;
import com.softtek.meetup.service.UserService;

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

	private Logger log = LoggerFactory.getLogger(this.getClass());

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.addValidators(userValidator);
  }

  @RequestMapping(method = GET, value = "/create")  
  public String create(Model model){
    model.addAttribute("userCommand", new UserCommand());
    return "users/create";
  }

  @RequestMapping(method = POST)
  public String save(@Valid UserCommand command, BindingResult bindingResult) {
    log.info("Saving user:" + command.getUsername());

    if (bindingResult.hasErrors()) {
	    return "users/create";
    }

    User user = userBinder.bindUser(command);
    userService.save(user);
    return "redirect:/";
  }

}
