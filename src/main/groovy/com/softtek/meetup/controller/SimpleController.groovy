package com.softtek.meetup.controller


import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import com.softtek.meetup.service.SofttekMeetupService

@Controller
class SimpleController {

  @Autowired
  SofttekMeetupService service

  @RequestMapping("/")
  String index(){
    'index'
  }
	
}