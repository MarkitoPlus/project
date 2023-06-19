package com.se.mapper;

import com.se.pojo.SQL.ServiceRes;

import org.apache.ibatis.annotations.Param;

public interface ServiceMapper  {
  ServiceRes getOneService(@Param("account") String account, @Param("password") String password);
  ServiceRes getOneServiceById(@Param("id") int id);
}
