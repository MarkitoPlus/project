package com.se.mapper;

import com.se.pojo.Account;
import com.se.pojo.Patient;

import org.apache.ibatis.annotations.Param;

public interface PatientMapper {		
	/*
	 * 增加新患者
	 * */
	public void AddPatient(Patient patient);

	/*
	 * 获取患者信息
	 * */
	public Patient GetPatient(Account account);


	/*
	 * 获取患者信息 [Jsut for merging, may be deleted in the future]
	 * */
  Patient getOnePatient(@Param("account") String account, @Param("password") String password);
}
