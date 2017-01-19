package com.softtek.meetup.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.servlet.ModelAndView
import org.springframework.validation.BindingResult
import org.springframework.stereotype.Controller
import javax.validation.Valid

import com.softtek.meetup.command.UserCommand
import com.softtek.meetup.validator.UserValidator

@Controller
@RequestMapping("/user")
class UserController {

  @Autowired
  UserValidator userValidator

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator)
	}

	@RequestMapping(method = GET, value = "/create")
	ModelAndView create(){
		def modelAndView = new ModelAndView('user/create')
		def userCommand = new UserCommand()
		modelAndView.addObject('userCommand', userCommand)
		modelAndView
	}

	@RequestMapping(method = POST, value = "/save")
	String save(@Valid UserCommand command, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return 'user/create'
    }
    'redirect:/'
  }

}