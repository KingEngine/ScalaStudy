package com.e2edour.chat.bean

/**
  *
  *
  * @author King
  * @version 2016/3/28
  */
object Case {

  case class ForwardEvents(events: List[String])

  case class UserInput(event:String,data:Message)

}
