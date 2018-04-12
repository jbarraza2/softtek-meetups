package com.softtek.meetup.controller;

import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.softtek.meetup.service.LocaleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

  @Autowired
  private LocaleService localeService;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/login")
  public String login(@RequestParam Optional<String> error, Model model){
    log.info("Calling login");
    if(error.isPresent()){
      model.addAttribute("error", localeService.getMessage("login.error"));
    }
    return "login/login";
  }

}


