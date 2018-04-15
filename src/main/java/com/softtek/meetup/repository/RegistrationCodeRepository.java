package com.softtek.meetup.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.softtek.meetup.model.RegistrationCode;

import reactor.core.publisher.Mono;

public interface RegistrationCodeRepository extends ReactiveMongoRepository<RegistrationCode, Long> {

  Mono<RegistrationCode> findByToken(String token);

}
