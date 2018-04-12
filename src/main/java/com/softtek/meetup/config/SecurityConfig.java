package com.softtek.meetup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.softtek.meetup.repository.UserRepository;

@EnableWebFluxSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserRepository userRepository;

  @Override
  void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    .antMatchers("/", "/assets/**","/home/**","/user/**","/recovery/**").permitAll()
    .anyRequest().authenticated()
    .and()
    .formLogin()
    .loginPage("/login")
    .usernameParameter("username")
    .permitAll()
    .and()
    .logout()
    .permitAll();
  }

  @Bean
  public ReactiveUserDetailsService  userDetailsService() {
    return (username) -> userRepository.findByUsername(username);
  }

}
