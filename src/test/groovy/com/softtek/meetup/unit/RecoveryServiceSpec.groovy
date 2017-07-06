package com.softtek.meetup.unit

import spock.lang.Specification

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.service.RestService
import com.softtek.meetup.service.RegistrationService
import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.service.impl.RecoveryServiceImpl
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.repository.UserRepository
import com.softtek.meetup.model.User
import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.command.Command
import com.softtek.meetup.exception.UserNotFoundException
import com.softtek.meetup.exception.SofttekMeetupException

class RecoveryServiceSpec extends Specification {

  RecoveryService recoveryService = new RecoveryServiceImpl()

  RegistrationCodeRepository repository = Mock(RegistrationCodeRepository)
  RestService restService = Mock(RestService)
  RegistrationService registrationService = Mock(RegistrationService)
  UserRepository userRepository = Mock(UserRepository)
  LocaleService localeService = Mock(LocaleService)

  def setup(){
    recoveryService.repository = repository
    recoveryService.restService = restService
    recoveryService.registrationService = registrationService
    recoveryService.userRepository = userRepository
    recoveryService.localeService = localeService
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

  void "should not confirm account for token since user not found"(){
   given:"A token"
      String token = 'token'
    and:"An email"
      String email = 'josdem@email.com'
    when:"We call for account confirmation"
      localeService.getMessage('exception.user.not.found') >> 'User not found'
      registrationService.findEmailByToken(token) >> email
      User result = recoveryService.confirmAccountForToken(token)
    then:"We expect an exception"
      thrown UserNotFoundException
  }

   void "should not generate registration token for email since user not enabled"(){
     given:"An user"
       User user = new User()
     and:"An email"
       String email = 'josdem@email.com'
     when:"We generate registration token for email"
       userRepository.findByEmail(email) >> user
       localeService.getMessage('exception.account.not.activated') >> 'Account not activated'
       recoveryService.generateRegistrationCodeForEmail(email)
     then:"We expect Softtek exception"
       thrown SofttekMeetupException
   }

   void "should not generate registration token for email since user not found"(){
     given:"An email"
       String email = 'josdem@email.com'
     when:"We generate registration token for email"
       localeService.getMessage('exception.user.not.found') >> 'User not found'
       recoveryService.generateRegistrationCodeForEmail(email)
     then:"We expect user not found exception"
       thrown UserNotFoundException
   }

   void "should generate registration token for email"(){
     given:"An user"
       User user = new User(enabled:true)
     and:"An email"
       String email = 'josdem@email.com'
     and:"A token"
       String token = 'token'
     when:"We generate registration token for email"
       userRepository.findByEmail(email) >> user
       registrationService.generateToken(email) >> token
       recoveryService.generateRegistrationCodeForEmail(email)
     then:"We expect send command"
     1 * restService.sendCommand(_ as Command)
   }

}
