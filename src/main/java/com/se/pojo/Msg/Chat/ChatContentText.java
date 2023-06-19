package com.se.pojo.Msg.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatContentText {
  private int chatId;
  private String text;
  private String time;
}
