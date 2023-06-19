package com.se.pojo.SQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatNameInsert{
  private int id;
  private int chat_id;
  private String title;
  public ChatNameInsert(int chatId, String title){
    this.chat_id = chatId;
    this.title = title;
  }
}
