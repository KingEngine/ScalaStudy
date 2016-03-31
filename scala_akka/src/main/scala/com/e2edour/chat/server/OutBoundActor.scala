package com.e2edour.chat.server

import akka.actor.Actor
import com.corundumstudio.socketio.SocketIOServer
import com.e2edour.chat.bean.Case.UserInput

/**
  * 闹着玩啊
  * @author King
  * @version 2016/3/29
  */
class OutBoundActor(server: SocketIOServer) extends Actor{
  override def receive={
    case msg@UserInput(event,data)=>
      server.getRoomOperations(data.getRoom.getId).sendEvent(event,data.getMsg)
  }
}
