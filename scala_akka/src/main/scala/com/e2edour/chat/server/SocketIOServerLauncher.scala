package com.e2edour.chat.server

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.corundumstudio.socketio._
import com.corundumstudio.socketio.listener.ConnectListener
import com.e2edour.chat.bean.{CustomerList, Room, User}

/**
  * 弄着玩
  *
  * @author King
  * @version 2016/3/28
  */
class SocketIOServerLauncher {

  val socketServer: SocketIOServer = new SocketIOServer(config);

  def config: Configuration = {
    val config: Configuration = new Configuration
    config.setHostname("0.0.0.0")
    config.setPort(8888)
    config.setAckMode(AckMode.MANUAL)
    config.setTransports(Transport.WEBSOCKET)
    config
  }

  def start = {
    socketServer.start
    socketServer.addConnectListener(new ConnectListener() {
      def onConnect(client: SocketIOClient) {
        val id = client.getHandshakeData.getSingleUrlParam("id")
        val user =User(id)
        val roomPath = "/user/private/"+user.id
        val room =Room(roomPath)
        client.set("room", room)
        client.set("user", user)
        client.joinRoom(roomPath)
      }
    })
  }

  def getServer: SocketIOServer = {
    socketServer
  }
}
