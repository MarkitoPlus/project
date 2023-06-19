package com.se.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.se.pojo.Account;

// Path("/api/service/chatroom")
// Path("/api/patient/chatroom")
public class ChatRoomFilter implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // System.out.println("RootFilter init");
  }
  @Override
  public void destroy() {
    // System.out.println("RootFilter destroy");
  }
  @Override
  // 对携带chatId的请求验证是否可以查询
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
    // System.out.println("ChatRoomFilter doFilter");
    // long start = System.currentTimeMillis();
    Account account = Util.getUser(request);
    HttpServletRequest req = (HttpServletRequest) request;    
    String chatId = req.getParameter("id"); 
    if(chatId != null && !Util.isChatIdOk(Integer.parseInt(chatId), account)){
      req.setAttribute("user", null);
      Util.sendFaildResponse(response);
      return;
    }
    // System.out.println("CHR Filter : "+(System.currentTimeMillis()-start));
    chain.doFilter(request, response);
  }
}
