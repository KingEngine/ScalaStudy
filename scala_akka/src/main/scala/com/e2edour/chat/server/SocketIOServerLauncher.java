package com.e2edour.chat.server;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

/**
 * @author King
 * @version 2016/3/25
 */
public class SocketIOServerLauncher {

    private SocketIOServer server;

    public SocketIOServerLauncher() {
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9979);
        config.setAckMode(AckMode.MANUAL);
        config.setTransports(Transport.WEBSOCKET);
        server = new SocketIOServer(config);
        System.out.println("初始化成功,你信吗？");
    }

    public void start() {
        server.start();
        server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient client) {
                System.out.println("好像是客户端连进来了啊");
            }
        });
    }


}


