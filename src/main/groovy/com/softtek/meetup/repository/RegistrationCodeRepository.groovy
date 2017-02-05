package com.softtek.meetup.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.softtek.meetup.model.RegistrationCode

interface RegistrationCodeRepository extends JpaRepository<RegistrationCode,Long> {

	RegistrationCode save(RegistrationCode registrationCode)
	
}