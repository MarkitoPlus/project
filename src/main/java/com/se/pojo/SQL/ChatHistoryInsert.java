package com.se.pojo.SQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatHistoryInsert{
  private int id;
  private int chat_id;
  private int seq;
  private String content;
  private int from_type; // 0: service, 1: patient
  private int to_type;
  private String time;
  public ChatHistoryInsert(int chat_id, String content, int fromType, int toType, String time){
    this.chat_id = chat_id;
    this.content = content;
    this.seq = 1;
    this.time = time;
    this.from_type = fromType;
    this.to_type = toType;
  }
}
