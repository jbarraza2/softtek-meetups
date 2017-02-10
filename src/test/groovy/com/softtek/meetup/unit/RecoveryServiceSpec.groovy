package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.service.RestService
import com.softtek.meetup.service.RegistrationService
import com.softtek.meetup.service.impl.RecoveryServiceImpl
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.repository.UserRepository
import com.softtek.meetup.model.User
import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.command.Command

class RecoveryServiceSpec extends Specification {

  RecoveryService recoveryService = new RecoveryServiceImpl()

  RegistrationCodeRepository repository = Mock(RegistrationCodeRepository)
  RestService restService = Mock(RestService)
  RegistrationService registrationService = Mock(RegistrationService)
  UserRepository userRepository = Mock(UserRepository)

  def setup(){
    recoveryService.repository = repository
    recoveryService.restService = restService
    recoveryService.registrationService = registrationService
    recoveryService.userRepository = userRepository
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
	
  void "should confirm account for token"(){
    given:"A token"
      String token = 'token'
    and:"An email"
      String email = 'josdem@email.com'  
    and:"A user"
      User user = new User()  
    when:"We call for account confirmation"
      userRepository.findByEmail(email) >> user
      registrationService.findEmailByToken(token) >> email 
      User result = recoveryService.confirmAccountForToken(token) 
    then:"We expect user is enabled"
      result.enabled
      1 * userRepository.save(user)
  }
}