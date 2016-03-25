package com.e2edour.test

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}

/**
  * 弄着玩啊
  *
  * @author King
  * @version 2016/3/22
  *          参考:http://www.jianshu.com/p/347db2de7103
  */

case class CreateChild(name: String)

case class Name(name: String)

class Child extends Actor {

  var name = "No name"

  override def receive = {
    case Name(name) =>
      this.name = name
      println(s"name:$name"+self.path)
    case _ => println(s"Child $name got message")
  }
}

class Parent extends Actor {
  override def receive = {
    case CreateChild(name) =>
      println(s"Parent about to create child [$name] ...")
      val child = context.actorOf(Props[Child], name = s"$name")
      child ! Name(name)
    case _ => println(s"Parent got some other message")
  }
}

object ParentChildDemo extends App {
  val actorSystem = ActorSystem("ParentChildTest")
  val parent = actorSystem.actorOf(Props[Parent], name = "Parent")
  parent ! CreateChild("XiaoMing")
  parent ! CreateChild("XiaoLiang")
  Thread.sleep(5000)
  println("sending XiaoMing a PoisonPill .... ")
  val xiaoming = actorSystem.actorSelection("/user/Parent/XiaoMing")
  xiaoming ! PoisonPill
  println("XiaoMing was Killed")
  Thread.sleep(5000)
  actorSystem.shutdown()
}
