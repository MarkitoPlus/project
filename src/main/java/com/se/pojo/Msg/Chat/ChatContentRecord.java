package com.se.pojo.Msg.Chat;

import com.se.pojo.SQL.ChatContentRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatContentRecord implements Comparable<ChatContentRecord>{
  private String content;
  private String from; // 0: service, 1: patient
  private String to;
  private String time;
  public ChatContentRecord(ChatContentRes chatContentRes){
    this.content = chatContentRes.getContent();
    this.time = chatContentRes.getTime();
    int from = chatContentRes.getFrom_type();
    int to = chatContentRes.getTo_type();
    if(from == 0) this.from = "service";
    else this.from = "patient";
    if(to == 0) this.to = "service";
    else this.to = "patient";
  }
  @Override
  public int compareTo(ChatContentRecord a){
    return this.time.compareTo(a.time);
  }
}
