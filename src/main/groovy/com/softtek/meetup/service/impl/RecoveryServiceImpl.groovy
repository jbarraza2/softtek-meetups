package com.softtek.meetup.service.impl

import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.service.RecoveryService
import com.softtek.meetup.repository.RegistrationCodeRepository
import com.softtek.meetup.model.RegistrationCode

class RecoveryServiceImpl implements RecoveryService {

  @Autowired
  RegistrationCodeRepository repository

	void sendConfirmationAccountToken(String email){
    RegistrationCode registrationCode = new RegistrationCode(email:email)  
    repository.save(registrationCode)
	}
	
}