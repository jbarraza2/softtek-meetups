package com.softtek.meetup.service

import com.softtek.meetup.command.Command

interface RestService {
  void sendCommand(Command message)
}
