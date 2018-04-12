package com.softtek.meetup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;

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
