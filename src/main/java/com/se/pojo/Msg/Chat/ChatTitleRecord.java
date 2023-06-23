package com.se.pojo.Msg.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatTitleRecord implements Comparable<ChatTitleRecord>{
  int chatId;
  String title;
  String startTime;
  @Override
  public int compareTo(ChatTitleRecord a){
    return a.startTime.compareTo(this.startTime);
  }
}
