package com.softtek.meetup.service.impl

import org.springframework.stereotype.Service
import org.springframework.context.MessageSource
import org.springframework.beans.factory.annotation.Autowired

import com.softtek.meetup.service.LocaleService

@Service
class LocaleServiceImpl implements LocaleService {

  @Autowired
  MessageSource messageSource

  String getMessage(String code){
    messageSource.getMessage(code, null, new Locale("en"))
  }

}