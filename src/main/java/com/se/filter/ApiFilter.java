package com.se.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.se.pojo.Account;

// Path("/api/*")
public class ApiFilter implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // System.out.println("RootFilter init");
  }
  @Override
  public void destroy() {
    // System.out.println("RootFilter destroy");
  }
  @Override
  // 验证session是否规范
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
    // System.out.println("ApiFilter doFilter");
    Account user = Util.getUser(request);
    if(user != null){
      // System.out.println(user.toString());
      chain.doFilter(request, response);
    }
    else{
      // 使session失效
      Util.seesionInvalid(request);
      Util.sendFaildResponse(response);
    }
  }
}
