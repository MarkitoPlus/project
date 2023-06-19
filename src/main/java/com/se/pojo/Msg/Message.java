package com.se.pojo.Msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message{
  private int type;  // 0:来自server, 1:来自其他用户
  private int state; // 1: success, 0: fail
  private Object data;
  private String content;
  static public Message getFaildMessage(){
    return new Message(0, 0, null, null);
  }
}
