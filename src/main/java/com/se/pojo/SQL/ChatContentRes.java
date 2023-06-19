package com.se.pojo.SQL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatContentRes{
  private String content; 
  private int from_type; // 0: Service, 1: Patient
  private int to_type; // 0: Service, 1: Patient
  private String time;
}
