package com.se.pojo;

import com.se.pojo.SQL.ServiceRes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Service extends Account{
  private ServiceRes data;
  public Service(ServiceRes data){
    super();
    super.setType(AccountType.ServiceTy);
    super.setTypeVal(AccountType.ServiceTy.ordinal());
    this.data = data;
  } 
}
