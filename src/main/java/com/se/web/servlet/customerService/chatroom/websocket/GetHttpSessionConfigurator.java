package com.se.web.servlet.customerService.chatroom.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
  @Override
  public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
    HttpSession httpSession = (HttpSession)request.getHttpSession();
    config.getUserProperties().put(HttpSession.class.getName(), httpSession);
  }
}
