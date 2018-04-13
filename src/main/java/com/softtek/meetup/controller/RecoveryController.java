package com.softtek.meetup.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import com.softtek.meetup.command.RecoveryPasswordCommand;
import com.softtek.meetup.command.ChangePasswordCommand;
import com.softtek.meetup.service.RecoveryService;
import com.softtek.meetup.service.LocaleService;
import com.softtek.meetup.validator.RecoveryValidator;
import com.softtek.meetup.validator.ChangePasswordValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Controller
@RequestMapping("/recovery")
public class RecoveryController {

  @Autowired
  private RecoveryService recoveryService;
  @Autowired
  private RecoveryValidator recoveryValidator;
  @Autowired
  private ChangePasswordValidator changePasswordValidator;
  @Autowired
  private LocaleService localeService;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @InitBinder("recoveryPasswordCommand")
  private void initGetEmailBinder(WebDataBinder binder) {
    binder.addValidators(recoveryValidator);
  }

  @InitBinder("changePassword")
  private void initChangeBinder(WebDataBinder binder) {
    binder.addValidators(changePasswordValidator);
  }

  @RequestMapping(method = GET, value = "/activate/{token}")
  public String create(@PathVariable String token){
    log.info("Calling activate token");
    recoveryService.confirmAccountForToken(token);
    return "login/login";
  }

  @RequestMapping(method = GET, value = "/password")
  public String password(Model model){
    log.info("Asking for change password");
    model.addAttribute("recoveryPasswordCommand", new RecoveryPasswordCommand());
    return "recovery/recoveryPassword";
  }

  @RequestMapping(method = POST, value = "/email")
  public String  getEmailForRecovery(@Valid RecoveryPasswordCommand command, BindingResult bindingResult, Model model){
    if(bindingResult.hasErrors()){
      return "recovery/password";
    }
    recoveryService.generateRegistrationCodeForEmail(command.email);
    return "home/home";
  }

  @RequestMapping(method = GET, value = "/forgot/{token}")
  public String changePassword(@PathVariable String token, HttpServletRequest request, Model model){
    log.info("Calling change password");
    Boolean valid = recoveryService.validateToken(token);
    if(!valid){
      model.addAttriute("message", localeService.getMessage("recovery.token.error"));
    }
    Command changePasswordCommand = new ChangePasswordCommand();
    changePasswordCommand.setToken(token);
    modelAndView.addObject("changePasswordCommand", changePasswordCommand);
    return "recovery/changePassword";
  }
  
  @RequestMapping(method = POST, value = "/change")
  public String changePassword(@Valid ChangePasswordCommand command, BindingResult bindingResult, Model model){
  	log.info("Calling save and changing password");
    if(bindingResult.hasErrors()){
      return "recovery/changePassword";
    }
    model.addAttribute("message", localeService.getMessage("recovery.password.changed"));
    recoveryService.changePassword(command);
    return "login/login";
  }

}

