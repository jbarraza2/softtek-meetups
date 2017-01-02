package com.softtek.meetup.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.service.SofttekMeetupService

@RestController
class SimpleController {

  @Autowired
  SofttekMeetupService service

  @RequestMapping("/")
  String index(){
    service.callService()
  }
	
}