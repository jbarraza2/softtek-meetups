package com.softtek.meetup.model

import com.softtek.meetup.enums.RegistrationCodeStatus

class RegistrationCode {

  RegistrationCodeStatus status = RegistrationCodeStatus.VALID;
	Date dateCreated = new Date()
  String token = UUID.randomUUID().toString().replaceAll('-','')

  Boolean isValid(){
    status == RegistrationCodeStatus.VALID ? true : false
  }
	
}