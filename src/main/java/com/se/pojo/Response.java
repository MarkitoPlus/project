package com.se.pojo;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response{
  private int state; // 1: success, 0: fail
  private Object content; 
  // 构建response的JSON字符串
  static public String getResponseJSONstring(Response res){
    return JSON.toJSONString(res);
  }
  // 构建成功的response的JSON字符串
  static public String getSuccessResponse(Object obj){
    return Response.getResponseJSONstring(new Response(1, obj));
  }
  // 构建失败的response的JSON字符串
  static public String getFaildResponse(){
    return Response.getResponseJSONstring(new Response(0, null));
  }
}
