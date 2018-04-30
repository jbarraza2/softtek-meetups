package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.enums.RegistrationCodeStatus

class RegistrationCodeSpec extends Specification {

  RegistrationCode registrationCode = new RegistrationCode()

	void "should create a registration code with creation date"(){
    given:"A Date"
      Date now = new Date()
    expect:"Date is not null and is in the past"
      now.getTime() - registrationCode.dateCreated.getTime()  < 1000
      registrationCode.token.size() == 36
      registrationCode.isValid()
  }

  void "should invalidate a token"(){
    when:"We validate a token"
      registrationCode.status = RegistrationCodeStatus.INVALID
    then:"Is no valid"
      registrationCode.isValid() == false
  }
	
}