package com.softtek.meetup.repository

import com.jos.dem.vetlog.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User,Long> {

  User findByUsername(String username)

}