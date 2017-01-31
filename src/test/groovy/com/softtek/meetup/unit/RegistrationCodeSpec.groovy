package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.model.RegistrationCode

class RegistrationCodeSpec extends Specification {

  RegistrationCode registrationCode = new RegistrationCode()

	void "should create a registration code with creation date"(){
    given:"A Date"
      Date now = new Date()
    expect:"Date is not null and is in the past"
      now.getTime() - registrationCode.dateCreated.getTime()  < 1000
  }
	
}