package com.softtek.meetup.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import javax.validation.Valid 

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.BindingResult
import org.springframework.stereotype.Controller

import com.softtek.meetup.service.LocaleService
import com.softtek.meetup.validator.UserValidator
import com.softtek.meetup.command.UserCommand

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
class LoginController {

  @Autowired
  UserValidator userValidator
  @Autowired
  LocaleService localeService

  Logger log = LoggerFactory.getLogger(this.class)

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.addValidators(userValidator)
  }

  @RequestMapping(method = GET, value = "/login")
  ModelAndView login(@RequestParam Optional<String> error){
    log.info "Calling login"
    ModelAndView modelAndView = new ModelAndView('login/login')
    def userCommand = new UserCommand()
    modelAndView.addObject('userCommand', userCommand)
    if(error.isPresent()){
      modelAndView.addObject('error', localeService.getMessage('login.error'))
    }
    modelAndView
  }

  @RequestMapping(method = POST, value = "/save")
  String save(@Valid UserCommand command, BindingResult bindingResult) {
    log.info "Saving user"
    if (bindingResult.hasErrors()) {
      return 'login'
    }
    'redirect:/'
  }

}


