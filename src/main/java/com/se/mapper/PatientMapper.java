package com.se.mapper;

import com.se.pojo.Account;
import com.se.pojo.Patient;

public interface PatientMapper {		
	/*
	 * 增加新患者
	 * */
	public void AddPatient(Patient patient);

	/*
	 * 获取患者信息
	 * */
	public Patient GetPatient(Account account);
}
