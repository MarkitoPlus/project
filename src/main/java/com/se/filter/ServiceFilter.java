package com.se.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.se.pojo.Service;
import com.se.pojo.SQL.ServiceRes;

// Path("/api/service/*")
public class ServiceFilter implements Filter{
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // System.out.println("RootFilter init");
  }
  @Override
  public void destroy() {
    // System.out.println("RootFilter destroy");
  }
  @Override
  // 检查客服数据是否存在
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
    // System.out.println("ServiceFilter doFilter");
    // 更新客服数据
    Service service = (Service) Util.getUser(request);
    ServiceRes newSreviceRes = Util.getServiceData(service);
    if(newSreviceRes != null){
      service.setData(newSreviceRes);
      chain.doFilter(request, response);
    }
    else{
      HttpServletRequest req = (HttpServletRequest) request;
      req.setAttribute("user", null);
      Util.sendFaildResponse(response);
    }
  }
}
