package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.service.impl.RecoveryServiceImpl
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.model.RegistrationCode

class RecoveryServiceSpec extends Specification {

  RecoveryService recoveryService = new RecoveryServiceImpl()

  RegistrationCodeRepository repository = Mock(RegistrationCodeRepository)

  def setup(){
    recoveryService.repository = repository
  }

	void "should persist an registration code"(){
    given:"An email"
      String email = 'josdem@email.com'
    when:"We call regisgtration code"  
      recoveryService.saveRegistrationCode(email)
    then:"We expect to call save from repository"
    1 * repository.save(_ as RegistrationCode)  
  }
	
}