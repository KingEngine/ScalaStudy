package com.e2edour.chat.bean


import scala.beans.BeanProperty

/**
  * 弄着玩啊
  *
  * @author King
  * @version 2016/3/28
  */
class Message {

  @BeanProperty var user = User("")

  @BeanProperty var msg = ""

  @BeanProperty var room = Room("")

}
