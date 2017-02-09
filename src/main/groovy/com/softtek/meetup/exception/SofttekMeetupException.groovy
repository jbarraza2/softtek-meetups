package com.softtek.meetup.exception

import java.lang.RuntimeException
import java.lang.Throwable

class SofttekMeetupException extends RuntimeException {

  SofttekMeetupException(String message){
    super(message)
  }

  SofttekMeetupException(String message, Throwable cause){
  	super(message, cause)
  }

}