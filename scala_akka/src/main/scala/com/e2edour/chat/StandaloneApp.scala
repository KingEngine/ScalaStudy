package com.e2edour.chat

import akka.actor.{ActorSystem, Props}
import com.e2edour.chat.server.{InBoundActor, OutBoundActor, SocketIOServerLauncher}
import com.e2edour.chat.bean.Case.ForwardEvents
import com.e2edour.chat.bean.Constants

/**
  * 闹着玩吧
  * 简单的聊天系统、可以收发消息
  *
  * @author King
  * @version 2016/3/25
  */
object StandaloneApp extends App {

  //消息广播

  //消息单播

  //添加简单知识库

  //添加机器人

  //添加机器人对知识库的检索


  val server = new SocketIOServerLauncher
  server.start
  /*
   * chat:message     聊天
   * modify:nickName  修改昵称
   */
  val commands = List(Constants.chatMsg, Constants.modifyNickName)
  val actorSystem = ActorSystem("inboundActor")
  val outboundActor = actorSystem.actorOf(Props(new OutBoundActor(server.getServer)), "outboundActor")
  val inboundActor = actorSystem.actorOf(Props(new InBoundActor(server.getServer, outboundActor)), "inboundActor")
  inboundActor ! ForwardEvents(commands)
}
