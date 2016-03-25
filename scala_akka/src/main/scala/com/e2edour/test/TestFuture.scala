package com.e2edour.test

import akka.actor.{Actor, ActorSystem, Props, _}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future}
//import scala.language.postfixOps
import scala.concurrent.duration._


/**
  * 弄着玩
  *
  * @author King
  * @version 2016/3/23
  */
case object AskNameMessage

class MyTestActor extends Actor {
  override def receive = {
    case AskNameMessage =>
      println(sender.path)
      sender ! "Fred"
    case _ => println("that was unexcepted")
  }
}

object AskDemo extends App {
  val system = ActorSystem("AskDemoSystem")
  val myActor = system.actorOf(Props[MyTestActor], name = "myActor")



  implicit val timeout = Timeout(5 seconds)
  val future = myActor ? AskNameMessage
  val result = Await.result(future, timeout.duration).asInstanceOf[String]
  println(result)

  val future2: Future[String] = ask(myActor, AskNameMessage).mapTo[String]
  val result2 = Await.result(future2, 1 seconds)
  println(result2)
  system.shutdown()
}
