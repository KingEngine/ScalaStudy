package com.e2edour.chat.bean

/**
  *
  *
  * @author King
  * @version 2016/3/28
  */
abstract class Message {

  var user: User

  var msg: String

  def setUser(user: User): Unit = {
    this.user = user
  }

  def getUser = {
    user
  }

  def setMsg(msg: String): Unit = {
    this.msg = msg
  }

  def getMsg = {
    msg
  }

}
