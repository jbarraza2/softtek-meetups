package com.softtek.meetup.service

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.softtek.meetup.exception.SofttekMeetupException

@Service
class SofttekMeetupService {

  Logger log = LoggerFactory.getLogger(this.class)

  def callService(){
    log.debug 'Calling a service'
    throw new SofttekMeetupException()
  }

}