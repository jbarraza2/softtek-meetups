package com.softtek.meetup.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.softtek.meetup.model.RegistrationCode;

public interface RegistrationCodeRepository extends ReactiveMongoRepository<RegistrationCode, Long> {

  Mono<RegistrationCode> findByToken(String token);
  void save(RegistrationCode registrationCode);

}
