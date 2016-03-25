package com.e2edour.test

import akka.actor.{Actor, ActorSystem, Props}

/**
  * 弄着玩
  *
  * @author King
  * @version 2016/3/22
  */
class HelloActor extends Actor {
  override def receive = {
    case "hello" => println("hello back to u")
    case _ => println("huh?")
  }
}

object Test1_HelloActor {

  def main(args: Array[String]) {

    val system = ActorSystem("HelloSystem")

    val helloActor = system.actorOf(Props[HelloActor])

    helloActor ! "hello"
    helloActor ! "what"
    system.shutdown
  }

}
