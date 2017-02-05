package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.service.RestService
import com.softtek.meetup.service.impl.RecoveryServiceImpl
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.command.Command

class RecoveryServiceSpec extends Specification {

  RecoveryService recoveryService = new RecoveryServiceImpl()

  RegistrationCodeRepository repository = Mock(RegistrationCodeRepository)
  RestService restService = Mock(RestService)

  def setup(){
    recoveryService.repository = repository
    recoveryService.restService = restService
  }

	void "should persist an registration code"(){
    given:"An email"
      String email = 'josdem@email.com'
    when:"We call regisgtration code"  
      recoveryService.sendConfirmationAccountToken(email)
    then:"We expect to call save from repository"
    1 * repository.save(_ as RegistrationCode)  
    1 * restService.sendCommand(_ as Command)  
  }
	
}