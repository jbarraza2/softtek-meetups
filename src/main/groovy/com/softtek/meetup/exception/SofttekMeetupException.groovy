package com.softtek.meetup.exception

import java.lang.RuntimeException

class SofttekMeetupException extends RuntimeException {

  @Override
  String getMessage() {
    "SofttekMeetup exception"
  }

}