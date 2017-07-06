package com.softtek.meetup.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.ModelAndView
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.WebDataBinder

import org.springframework.validation.Validator
import org.springframework.validation.Errors

import com.softtek.meetup.command.RecoveryPasswordCommand
import com.softtek.meetup.service.RecoveryService

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
@RequestMapping("/recovery")
class RecoveryController {

  @Autowired
  RecoveryService recoveryService

  Logger log = LoggerFactory.getLogger(this.class)
  
   @InitBinder('recoveryPasswordCommand')
	private void initGetEmailBinder(WebDataBinder binder) {
		binder.addValidators(generateRegistrationCodeForEmail)
    }

   @RequestMapping(method = GET, value = "/activate/{token}")
	String create(@PathVariable String token){
  	log.info "Calling activate token"
    recoveryService.confirmAccountForToken(token)
    'login/login'
	}

  @RequestMapping(method = GET, value = "/password")
	ModelAndView password(){
  	log.info "Asking for change password"
    ModelAndView modelAndView = new ModelAndView('recovery/recoveryPassword')
    modelAndView.addObject('recoveryPasswordCommand',  new RecoveryPasswordCommand())
    modelAndView
	}

   @RequestMapping(method = POST, value = "/email")
	ModelAndView getEmailForRecovery(@Valid RecoveryPasswordCommand command, BindingResult bindingResult){
	
	if(bindingResult.hasErrors()){
      return new ModelAndView('recovery/password')
    }
    ModelAndView modelAndView = new ModelAndView('home/home')
    recoveryService.generateRegistrationCodeForEmail(command.email)
    modelAndView
    }
}
