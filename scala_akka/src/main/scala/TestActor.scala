import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import akka.pattern.gracefulStop


/**
  * 弄着玩
  *
  * @author King
  * @version 2016/3/23
  */
case object TestActorStop

class TestActor extends Actor {
  override def receive = {
    case TestActorStop =>
      context.stop(self)
      print(self.toString+" have stopped")
    case _ =>
  }

  override def postStop() = print("TestActor:postStop")
}

object GracefulStopTest extends App {
  val system = ActorSystem("TestActor")
  val testActor = system.actorOf(Props[TestActor], name = "TestActor11")
  try {
    val stopped: Future[Boolean] = gracefulStop(testActor, 2 seconds, TestActorStop)

    Await.result(stopped, 3 seconds)
  } catch {
    case e: akka.pattern.AskTimeoutException => e.printStackTrace
  }finally {
    system.shutdown()
  }
}

