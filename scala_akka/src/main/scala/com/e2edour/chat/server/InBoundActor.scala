package com.e2edour.chat.server

import akka.actor.Actor
import com.corundumstudio.socketio.listener.DataListener
import com.corundumstudio.socketio.{AckRequest, SocketIOClient, SocketIOServer}
import com.e2edour.chat.bean.Case.ForwardEvents
import com.e2edour.chat.bean.{Message, User}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by King on 2016/3/27.
  */
class InBoundActor(server: SocketIOServer) extends Actor {

  val mediator = DistributedPubSubExtension(context.system).mediator

  override def receive = {
    case ForwardEvents(events) =>
      events map {
        eventName =>
          server.addEventListener(eventName, classOf[Message], new DataListener[Message] {
            override def onData(client: SocketIOClient, data: Message, ackSender: AckRequest): Unit = {
              Future {
                client.get[User]("user")
              } onSuccess {
                case user =>

              }
            }
          })
      }
  }
}

