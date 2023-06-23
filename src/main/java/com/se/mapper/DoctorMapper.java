package com.se.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.se.pojo.Account;
import com.se.pojo.Case;
import com.se.pojo.Doctor;
import com.se.pojo.DoctorInfo;
import com.se.pojo.Patient;

public interface DoctorMapper {	
	
	/*
	 * 获取医生账号
	 * */
	public Doctor GetDoctor(Account account);
	
	/**
	 * 获取医生个人描述
	 */
	public DoctorInfo GetDoctorInfo(int doctorId);
	
	
	/*
	 * 更新医生信息
	 */
	public void UpdateProfile(Doctor doctor);
	
	/*
	 * 更新医生介绍
	 */
	public void UpdateInfo(DoctorInfo doctorInfo);
	
	
	public Integer GetAllowance(@Param("id") int id, @Param("date") String date, @Param("time") int time);
	
	// 更新余量
	public void UpdateAllowance(@Param("timeslot_id")int timeslot_id, @Param("new_allow")int new_allow);

	// 所有病历
	public List<Case> GetAllCases(int doctorId);
}
