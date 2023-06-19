package com.se.pojo.SQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatHistoryInfoInsert {
  private int id;
  private int chat_history_id;
  private int from_type; // 0: service, 1: patient
  private int to_type;
  private String time;
  public ChatHistoryInfoInsert(int chat_history_id, int from, int to, String time){
    this.chat_history_id = chat_history_id;
    this.from_type = from;
    this.to_type = to;
    this.time = time;
  } 
}
