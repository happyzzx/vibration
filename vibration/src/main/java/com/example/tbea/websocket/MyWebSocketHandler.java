package com.example.tbea.websocket;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
        import org.springframework.web.socket.handler.TextWebSocketHandler;
        import org.springframework.web.socket.TextMessage;

import java.io.IOException;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接建立后发送初始消息
        session.sendMessage(new TextMessage("Welcome to WebSocket!"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理收到的消息并发送响应
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // 根据收到的消息处理逻辑
        String response = "Server received: " + payload;

        // 发送响应消息给前端
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 连接关闭后处理
        System.out.println("Disconnected: " + session.getId());
    }



    // 向指定会话发送消息的方法
    public void sendMessageToSession(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));



    }

}
