package com.softtek.meetup.service

interface RegistrationService {
  String findEmailByToken(String token)
  String generateToken(String email)
}
