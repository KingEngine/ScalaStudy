package com.e2edour.chat

import akka.actor.Actor
import com.corundumstudio.socketio.{AckRequest, SocketIOClient, SocketIOServer}
import com.corundumstudio.socketio.listener.DataListener
import com.e2edour.chat.Bean.ForwardEvents

/**
  * Created by King on 2016/3/27.
  */
class InBoundActor(server: SocketIOServer) extends Actor {
  override def receive = {
    case ForwardEvents(events) =>
      events map {
        eventName =>
          server.addEventListener(eventName, classOf[Message], new DataListener[Message] {
            override def onData(client: SocketIOClient, data: Message, ackSender: AckRequest): Unit = {
              //client.getSessionId;
              print(eventName)
              print(data.getMsg)
              //ackSender.
              ackSender.sendAckData()
            }
          })
      }
  }
}

