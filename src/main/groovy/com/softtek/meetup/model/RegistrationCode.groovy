package com.softtek.meetup.model

class RegistrationCode {

	Date dateCreated = new Date()
  String token = UUID.randomUUID().toString().replaceAll('-','')

  Boolean isValid(){
    true
  }
	
}