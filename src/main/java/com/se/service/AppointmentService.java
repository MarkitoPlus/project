package com.se.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.mapper.AppointmentMapper;
import com.se.pojo.Appointment;
import com.se.util.Encrypt;
import com.se.util.SqlSessionFactoryUtils;

public class AppointmentService{
	/*
	 * 静态类
	 * */
	private AppointmentService() {
		
	}
	
	/*
	 * 借助SqlSessionFactoryUtils静态方法获取sqlSessionFactory对象，用于创建sqlSession
	 * */
	private static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
	
	// 获取医生对应的预约信息
	public static List<Appointment> GetAppointment(int doctorId){
		SqlSession sqlSession = factory.openSession();
		
		AppointmentMapper appointmentMapper = sqlSession.getMapper(AppointmentMapper.class);
		
		List<Appointment> result = appointmentMapper.SelectByDoctorId(doctorId);

		sqlSession.close();
				
		return result;
	}
	
	// 插入一条
	public static void AddAppoint(String pName, String dName, String date, int time, int status){
		SqlSession sqlSession = factory.openSession();
		
		AppointmentMapper appointmentMapper = sqlSession.getMapper(AppointmentMapper.class);
		
		appointmentMapper.InsertAppointment(pName, dName, date, time, status);

		sqlSession.commit();
		sqlSession.close();
	}


}
