package com.softtek.meetup.config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.core.env.Environment

import com.softtek.meetup.model.User
import com.softtek.meetup.model.Role
import com.softtek.meetup.enums.CurrentEnvironment
import com.softtek.meetup.repository.UserRepository

@Component
class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  Environment environment
  @Autowired
  UserRepository userRepository

  @Override
  void onApplicationEvent(final ApplicationReadyEvent event) {
    if(environment.activeProfiles[0] == CurrentEnvironment.DEVELOPMENT.getDescription()){
      println "Loading development environment"
      createDefaultUsers()
    }
  }

  void createDefaultUsers(){
    createUserWithRole('softtekian', '12345678', 'jose.cruzm@softtek.com', Role.USER)
    createUserWithRole('admin', '12345678', 'admin@email.com', Role.ADMIN)
  }

  void createUserWithRole(String username, String password, String email, Role authority) {
    if(!userRepository.findByUsername(username)){
      User user = new User(
        username:username,
        password:new BCryptPasswordEncoder().encode(password),
        email:email,
        role:authority,
        firstname:username,
        lastname:username,
        enabled:true
      )
      userRepository.save(user)
    }
  }

}
