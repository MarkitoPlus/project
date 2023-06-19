package com.se.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Path("/*")
public class RootFilter implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // System.out.println("RootFilter init");
  }
  @Override
  public void destroy() {
    // System.out.println("RootFilter destroy");
  }
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
    // System.out.println("RootFilter doFilter");
    setting(response, request);
    solveCROS(response, request);
    chain.doFilter(request, response);
  }
  // 解决跨域问题
  private void solveCROS(ServletResponse response, ServletRequest request) throws IOException{
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpServletRequest req = (HttpServletRequest) request;
    resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
    resp.setHeader("Access-Control-Allow-Methods", "*");
    resp.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Depth,User-Agent, X-File-Size, X-Requested-With, X-Requested-By, If-Modified-Since, X-File-Name, X-File-Type, Cache-Control, Origin");
    resp.setHeader("Access-Control-Allow-Credentials", "true");
  }
  // 编码格式
  private void setting(ServletResponse response, ServletRequest request) throws IOException{
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpServletRequest req = (HttpServletRequest) request;
    resp.setContentType("application/json");
    resp.setCharacterEncoding("utf-8");
    req.setCharacterEncoding("utf-8");
  }
}
