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
public class SecurityConfig {

  @Autowired
  private UserRepository userRepository;

  @Bean
  SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
    http
      .authorizeExchange()
      .pathMatchers("/", "/assets/**","/home/**","/recovery/**","/login/**","/users/**").permitAll()
      .anyExchange()
      .authenticated()
      .and()
      .httpBasic()
      .and()
      .formLogin()
      .loginPage("/login")
      .and()
      .csrf().disable();
    return http.build();
  }

  @Bean
  public ReactiveUserDetailsService  userDetailsService() {
    return (username) -> userRepository.findByUsername(username);
  }

}
