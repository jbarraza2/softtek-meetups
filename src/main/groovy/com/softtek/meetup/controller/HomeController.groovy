package com.softtek.meetup.controller


import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import com.softtek.meetup.service.SofttekMeetupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Controller

import com.softtek.meetup.service.SofttekMeetupService

@Controller
class HomeController {
	Logger log = LoggerFactory.getLogger(this.class)

  @Autowired
  SofttekMeetupService service

  @Value('${message}')
  String message

  @RequestMapping("/")
  String index(){
  	log.info "Message is: ${message}"
    'home/index'
  }

}
