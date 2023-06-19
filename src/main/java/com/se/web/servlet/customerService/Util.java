package com.se.web.servlet.customerService;

import javax.servlet.http.*;

import com.alibaba.fastjson.*;

import java.io.*;

public class Util{
  public static JSONObject getJObject(HttpServletRequest req) throws IOException{
    String s = req.getReader().readLine();
    JSONObject jObject = JSON.parseObject(s);
    return jObject;
  }
}
