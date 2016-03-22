import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  *弄着玩啊
  * @author King
  * @version 2016/3/22
  */

case object PingMessage

case object PongMessage

case object StartMessage

case object StopMessage

class Ping(pong: ActorRef) extends Actor {

  var count = 0

  val introduction: String = self.toString()

  def incrementAndPrint {
    count += 1
    println(s"$count:ping")
  }

  override def receive = {
    case StartMessage =>
      incrementAndPrint
      pong ! PongMessage
    case PingMessage =>
      println("-------------------------------------")
      incrementAndPrint
      if (count > 10) {
        sender ! StopMessage
        context.stop(self)
      } else {
        println(s"self: $introduction")
        sender ! PongMessage
      }
    case _ => println("Ping got unexcepted information")
  }
}

class Pong extends Actor {
  var count = 0

  val introduction: String = self.toString()

  override def receive = {
    case StopMessage =>
      println(s"pong stopped ")
      context.stop(self)
    case PongMessage =>
      count += 1
      println("-------------------------------------")
      println(s"$count:pong")
      println(s"self: $introduction")
      sender ! PingMessage
    case _ => println("Pong got unexcepted information")
  }


}

object PingPongTest extends App {
  val system = ActorSystem("PingPongTest")
  val pongActor = system.actorOf(Props[Pong], name = "pong")
  val pingActor = system.actorOf(Props(new Ping(pongActor)), name = "ping")
  pingActor ! StartMessage
}


