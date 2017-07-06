package com.softtek.meetup.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.service.RegistrationService
import com.softtek.meetup.service.RestService
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.repository.UserRepository
import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.model.User
import com.softtek.meetup.command.Command
import com.softtek.meetup.command.MessageCommand
import com.softtek.meetup.exception.UserNotFoundException
import com.softtek.meetup.exception.SofttekMeetupException

@Service
class RecoveryServiceImpl implements RecoveryService {

  @Autowired
  RegistrationCodeRepository repository
  @Autowired
  UserRepository userRepository
  @Autowired
  RegistrationService registrationService
  @Autowired
  RestService restService
  @Autowired
  LocaleService localeService

  @Value('${server}')
  String server
  @Value('${template.register.name}')
  String template
  @Value('${template.register.path}')
  String path
  @Value('${template.forgot.name}')
  String forgotTemplate
  @Value('${template.forgot.path}')
  String forgotPath

  void sendConfirmationAccountToken(String email){
    RegistrationCode registrationCode = new RegistrationCode(email:email)
    repository.save(registrationCode)
    Command command = new MessageCommand(email:email, template:template, url:"${server}${path}${registrationCode.token}")
    restService.sendCommand(command)
  }

   User confirmAccountForToken(String token){
    User user = getUserByToken(token)
    if(!user) throw new UserNotFoundException(localeService.getMessage('exception.user.not.found'))
    user.enabled = true
    userRepository.save(user)
    user
  }

  User getUserByToken(String token){
    String email = registrationService.findEmailByToken(token)
    if(!email) throw new SofttekMeetupException(localeService.getMessage('exception.token.not.found'))
    User user = userRepository.findByEmail(email)
    user
  }

  void generateRegistrationCodeForEmail(String email){
    User user = userRepository.findByEmail(email)
    if(!user) throw new UserNotFoundException(localeService.getMessage('exception.user.not.found'))
    if(!user.enabled) throw new SofttekMeetupException(localeService.getMessage('exception.account.not.activated'))
    String token = registrationService.generateToken(email)
    Command command = new MessageCommand(email:email, template:forgotTemplate, url:"${server}${forgotPath}${token}")
    restService.sendCommand(command)
  }

}
