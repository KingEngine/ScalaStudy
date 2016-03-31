package com.e2edour.chat.bean

/**
  * 弄着玩啊
  * @author King
  * @version 2016/3/28
  */
class Message {

  var user=User("")

  var msg=""

  var room=Room("")

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
  def setRoom(room:Room)={
    this.room=room
  }

  def getRoom={
    room
  }

}
