package com.softtek.meetup.exception

import java.lang.RuntimeException
import java.lang.Throwable

class UserNotFoundException extends RuntimeException {

  UserNotFoundException(String message){
    super(message)
  }

  UserNotFoundException(String message, Throwable cause){
  	super(message, cause)
  }

}