package com.se.pojo.SQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatTitleRes{
  private int id;
  private int patient_id;
  private int service_id;
  private String title;
  private String start_time;
}
