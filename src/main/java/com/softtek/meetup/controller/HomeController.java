package com.softtek.meetup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/")
  public String index(){
  	log.info("Calling home");
    return "home/home";
  }

}
