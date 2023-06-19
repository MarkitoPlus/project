package com.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.se.pojo.SQL.ChatContentRes;
import com.se.pojo.SQL.ChatHistoryInsert;
import com.se.pojo.SQL.ChatRes;
import com.se.pojo.SQL.ChatTitleRes;
import com.se.pojo.SQL.ChatsInsert;

public interface ChatMapper {
  // select
  List<ChatRes> getChatList(@Param("id") int id);
  List<ChatRes> getChatListByPatientId(@Param("id") int id, @Param("search") String search, @Param("value") int value);
  List<ChatRes> getChatListByPatientName(@Param("id") int id, @Param("search") String search, @Param("value") String value);
  List<ChatRes> getChatListByStar(@Param("id") int id, @Param("search") String search, @Param("value") int value);
  List<ChatRes> getChatListByStartTimeEqual(@Param("id") int id, @Param("start") String start, @Param("end") String end);
  List<ChatRes> getChatListByStartTime(@Param("id") int id, @Param("search") String search, @Param("value") String value);
  List<ChatRes> getChatListByEndTimeEqual(@Param("id") int id, @Param("start") String start, @Param("end") String end);
  List<ChatRes> getChatListByEndTime(@Param("id") int id, @Param("search") String search, @Param("value") String value);
  List<ChatTitleRes> getUndoneChatList(@Param("id") int id);
  List<ChatTitleRes> getPatientChatList(@Param("id") int id);
  List<ChatTitleRes> getPatientUndoneChatList(@Param("id") int id);
  List<ChatContentRes> getChatContentListByChatId(@Param("id") int id);
  Integer getChatIdByServiceId(@Param("chat_id") int chat_id, @Param("service_id") int service_id);
  Integer getChatIdByPatientId(@Param("chat_id") int chat_id, @Param("patient_id") int patient_id);
  // insert
  Integer insertChatHistory(ChatHistoryInsert chat);
  Integer insertChats(ChatsInsert chat);
  // update
  Integer updateChats(ChatsInsert chat);
}
