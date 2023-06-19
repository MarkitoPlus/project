package com.se.pojo;

import com.se.web.servlet.customerService.chatroom.websocket.ServiceEndPoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceWebsocketSession implements Comparable<ServiceWebsocketSession>{
  private ServiceEndPoint endPoint;
  private int currentConnect;
  @Override
  public int compareTo(ServiceWebsocketSession s){
    return this.currentConnect - s.currentConnect;
  }
}
