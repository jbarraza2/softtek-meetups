package com.softtek.meetup.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException

import  com.softtek.meetup.model.User
import  com.softtek.meetup.model.Role
import  com.softtek.meetup.service.UserService

@Service
class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  UserService userService

  @Override
  org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getByUsername(username)
    Set<GrantedAuthority> grantedAuthorities = [] as Set
    grantedAuthorities << new SimpleGrantedAuthority(user.role.toString())
    new org.springframework.security.core.userdetails.User(user.username, user.password, user.enabled, user.accountNonExpired, user.credentialsNonExpired, user.accountNonLocked, grantedAuthorities)
  }

}
