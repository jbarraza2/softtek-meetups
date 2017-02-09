package com.softtek.meetup.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.service.RegistrationService
import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.model.RegistrationCode
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.exception.SofttekMeetupException

@Service
class RegistrationServiceImpl implements RegistrationService {

  @Autowired
  LocaleService localeService
  @Autowired
  RegistrationCodeRepository repository

  String findEmailByToken(String token){
    RegistrationCode registrationCode = repository.findByToken(token)
    if(!registrationCode) throw new SofttekMeetupException(localeService.getMessage('exception.token.not.found'))
    registrationCode.email
  }

}