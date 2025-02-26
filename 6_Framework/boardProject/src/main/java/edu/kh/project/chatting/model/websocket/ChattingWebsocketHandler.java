package edu.kh.project.chatting.model.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChattingWebsocketHandler extends TextWebSocketHandler{
   
   private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      
      sessions.add(session);
   }

   @Override
   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
   }

   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      
      sessions.remove(session);
   }
   
   

}
