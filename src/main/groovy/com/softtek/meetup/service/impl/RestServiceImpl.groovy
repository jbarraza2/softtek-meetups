package com.softtek.meetup.service

import groovyx.net.http.RESTClient
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import groovyx.net.http.HttpResponseException

import com.softtek.meetup.command.Command
import com.softtek.meetup.service.RestService
import com.softtek.meetup.exception.RestException

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class RestServiceImpl implements RestService {

  Logger log = LoggerFactory.getLogger(this.class)

  @Value('${emailer.url}')
  String emailerUrl
  @Value('${emailer.path}')
  String emailerPath
  @Value('${token}')
  String token

  void sendCommand(Command message){
    try{
      def rest = new RESTClient(emailerUrl)
      def response = rest.post(
        path: emailerPath,
        headers: [Authorization:"${token}"],
        body: message,
        requestContentType: 'application/json' )
      response.responseData
    } catch(Exception ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }

}
