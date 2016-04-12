package com.e2edour.chat.server

import akka.actor.Actor
import com.corundumstudio.socketio.SocketIOServer
import com.e2edour.chat.bean.Case.UserInput
import com.e2edour.chat.bean.Constants

/**
  * 闹着玩啊
  * @author King
  * @version 2016/3/29
  */
class OutBoundActor(server: SocketIOServer) extends Actor{
  override def receive={
    case msg@UserInput(Constants.chatMsg,data)=>
      //server.getRoomOperations(data.getRoom.getId).sendEvent(msg.event,data)
      server.getBroadcastOperations.sendEvent(msg.event,data)
    case msg@UserInput(Constants.modifyNickName,data)=>
  }
}
