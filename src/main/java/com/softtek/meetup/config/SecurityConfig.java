package com.softtek.meetup.config;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.softtek.meetup.repository.UserRepository;

@EnableWebFluxSecurity
class SecurityConfig {

  @Autowired
  UserRepository userRepository;

  @Bean
  SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
    http
      .authorizeExchange()
      .pathMatchers(HttpMethod.GET, "/", "/assets/**","/home/**","/user/**","/recovery/**").permitAll()
      .anyExchange().authenticated()
      .and()
      .httpBasic()
      .and()
      .formLogin()
      .loginPage("/login");
    return http.build();      
  }

  @Bean
  public ReactiveUserDetailsService  userDetailsService() {
    return (username) -> userRepository.findByUsername(username);
  }

}
