package com.se.pojo.Msg.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatTitleRecord{
  int chatId;
  String title;
  String startTime;
}
