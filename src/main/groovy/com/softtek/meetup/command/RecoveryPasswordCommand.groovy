package com.softtek.meetup.command

class RecoveryPasswordCommand implements Command {

  @Email
  @NotNull
  @Size(min=6, max=200)
  String email
  
  }
