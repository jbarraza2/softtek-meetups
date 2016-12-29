package com.softtek.meetup.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
class SimpleController {

  @RequestMapping("/")
  String index(){
    "Hello Softtek Meetups :)"
  }
	
}