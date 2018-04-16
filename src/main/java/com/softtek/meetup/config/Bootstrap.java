package com.softtek.meetup.config;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import com.softtek.meetup.model.User;
import com.softtek.meetup.model.Role;
import com.softtek.meetup.enums.CurrentEnvironment;
import com.softtek.meetup.repository.UserRepository;

import reactor.core.publisher.Mono;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

  private final static String uuid = "39ed0e7c-590b-48f3-9d72-19e3c667a827";

  @Autowired
  private Environment environment;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    if(environment.getActiveProfiles()[0].equals(CurrentEnvironment.DEVELOPMENT.getDescription())){
      System.out.println("Loading development environment");
      createDefaultUsers();
    }
  }

  private void createDefaultUsers(){
    createUserWithRole("softtekian", "12345678", "jose.cruzm@softtek.com", Role.USER);
  }

  private void createUserWithRole(String username, String password, String email, Role authority) {
    User user = new User(
      uuid,
      username,
      new passwordEncoder().encode(password)
    );

    userRepository.findByUsername(username)
      .subscribe(
        defaultUser -> System.out.println("Default user: " + defaultUser),
        error -> error.printStackTrace(),
        () -> userRepository.save(user).subscribe()
      );

  }

}
