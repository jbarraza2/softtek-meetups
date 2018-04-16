package com.softtek.meetup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/")
  public String index(Principal principal){
    log.info("Calling home");
    String username = principal.getName();
    log.info("Authenticated user is: " + username);
    return "home/home";
  }

}
