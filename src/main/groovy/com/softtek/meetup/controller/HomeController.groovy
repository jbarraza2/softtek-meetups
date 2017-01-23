package com.softtek.meetup.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
class HomeController {
	Logger log = LoggerFactory.getLogger(this.class)

  @RequestMapping("/")
  String index(){
  	log.info "Message is: ${message}"
    'home/index'
  }

}
