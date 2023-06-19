package com.se.pojo.SQL;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatsInsert{
  private int id;
  private int service_id;
  private int patient_id;
  private String start_time;
  private String end_time;
  private int star;
  private String title;
  public ChatsInsert(int service_id, int patient_id, String start_time, String title){
    this.service_id = service_id;
    this.patient_id = patient_id;
    this.start_time = start_time;
    this.title = title;
  }
  public ChatsInsert(int chatId, String endTime, int star){
    this.id = chatId;
    this.end_time = endTime;
    this.star = star;
  }
}
