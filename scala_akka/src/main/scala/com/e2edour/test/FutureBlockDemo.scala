package com.e2edour.test

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
/**
  *
  *
  * @author King
  * @version 2016/3/23
  */
object FutureBlockDemo extends App {

  implicit val baseTime = System.currentTimeMillis
  val f = Future {
    Thread.sleep(500)
    1 + 1
  }
  val result = Await.result(f, 1 second)
  print(result)
  Thread.sleep(1000)
}
