package com.e2edour.chat

import akka.actor.{ActorSystem, Props}
import com.e2edour.chat.server.{InBoundActor, OutBoundActor, SocketIOServerLauncher}
import com.e2edour.chat.bean.Case.ForwardEvents

/**
  * 闹着玩吧
  * 简单的聊天系统、可以收发消息
  *
  * @author King
  * @version 2016/3/25
  */
object StandaloneApp extends App {

  //客户端发消息，可以正常接收
  //如果匹配到问题则返回，否则要求别人重新输入
  //1对1 1对多
  //启动一个socket服务端吧
  //代码begin


  val server = new SocketIOServerLauncher
  server.start
  val commands = List("hello")
  val actorSystem = ActorSystem("inboundActor")
  val outboundActor = actorSystem.actorOf(Props(new OutBoundActor(server.getServer)), "outboundActor")
  val inboundActor = actorSystem.actorOf(Props(new InBoundActor(server.getServer,outboundActor)), "inboundActor")
  inboundActor ! ForwardEvents(commands)


}
