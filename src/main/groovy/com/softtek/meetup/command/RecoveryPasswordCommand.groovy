package com.softtek.meetup.command

import javax.validation.constraints.Size
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Email

class RecoveryPasswordCommand implements Command {

  @Email
  @NotNull
  @Size(min=6, max=200)
  String email
  
  }
