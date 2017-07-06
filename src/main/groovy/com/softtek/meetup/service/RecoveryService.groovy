package com.softtek.meetup.service

import com.softtek.meetup.model.User

interface RecoveryService {
	void sendConfirmationAccountToken(String email)
	User confirmAccountForToken(String token)
  void generateRegistrationCodeForEmail(String email)
}
