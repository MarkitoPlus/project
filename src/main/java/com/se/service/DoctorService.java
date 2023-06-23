package com.se.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.mapper.AccountMapper;
import com.se.mapper.DoctorMapper;
import com.se.mapper.PatientMapper;
import com.se.pojo.Account;
import com.se.pojo.AccountType;
import com.se.pojo.Case;
import com.se.pojo.Doctor;
import com.se.pojo.DoctorInfo;
import com.se.pojo.Patient;
import com.se.util.SqlSessionFactoryUtils;

public class DoctorService{
	/*
	 * 借助SqlSessionFactoryUtils静态方法获取sqlSessionFactory对象，用于创建sqlSession
	 * */
	private static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
	
	
	/*
	 * 静态类
	 * */
	private DoctorService() {
		
	}
	
	/*
	 * 数据检查
	 * */
	/*
	public static void Check(Doctor doctor, ErrorType errorType) throws AccountExecption{
		switch (errorType) {
		case InconsistentType:
			if(doctor.getType() == AccountType.DoctorTy)
				return;
			break;
		
		case InvalidPatientName:
			if(doctor.getDoctorName()!=null && doctor.getDoctorName().matches(doctor.getDoctorNameReg()))
				return;
			break;
		
		case InvalidAge:
			if(doctor.getAge() > 0 && doctor.getAge()<120)
				return;
			break;
			
		case InvalidPhone:
			if(doctor.getPhone()!=null && doctor.getPhone().matches(Patient.getPhoneReg()))
				return;
			break;
			
		case InvalidMail:
			if(doctor.getMail()!=null && doctor.getMail().matches(Patient.getMailReg()))
				return;
			break;
			
		case InvalidIdCard:
			if(doctor.getIdCard()!=null && doctor.getIdCard().matches(Patient.getIdCardReg()))
				return;
			break;
		
		case InvalidPatientId:
			if(doctor.getPatientId()!=Patient.getInvalidPatientId())
				return;
			break;
		
		default:
			return;
		}
		
		// 日志报错
		// Log.LogToFile(false, errorType.toString(), "Account.Check");
		
		// 抛出异常
		throw new AccountExecption(errorType, "Account.Check");
	}
	*/
	
	/*
	 * 获取医生
	 * */
	private static Doctor GetDoctor(Account account) throws AccountExecption{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 获取PatientMapper
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		// 3. 获取医生信息
		Doctor result = doctorMapper.GetDoctor(account);
		
		return result;
	}
	

	/*
	 * 医生登陆 传入Account实例， 携带需要的信息account 和 password
	 * */
	public static Doctor LogIn(Account account) throws AccountExecption{
		// 校验登陆
		// 1. 调用AccountService登陆方法，若成功，会返回携带accountId与type的Account实例
		Account result= AccountService.LogIn(account);
		
		// 2. 获取医生数据
		Doctor doctor = GetDoctor(result);
		
		// 3. 判断是否成功
		if(doctor == null)
			throw new AccountExecption(ErrorType.NonesistentDoctor, "DoctorService.LogIn");
		
		return doctor;
	}
	
	/**
	 * 根据医生ID获取对应信息
	 * @param doctorId
	 * @return
	 */
	public static DoctorInfo GetDoctorInfo(int doctorId)
	{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		// 3. 获取医生信息
		DoctorInfo res = doctorMapper.GetDoctorInfo(doctorId);
				
		return res;
	}
	
	/**
	 * 更新医生资料
	 */
	public static void UpdateDoctorProfile(Doctor doctor)
	{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		// 3. 获取医生信息
		doctorMapper.UpdateProfile(doctor);
		
		// 3.4 提交事务
		sqlSession.commit();
		
	}
	
	/**
	 * 更新医生资料
	 */
	public static void UpdateDoctorInfo(DoctorInfo doctorInfo)
	{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		// 3. 获取医生信息
		doctorMapper.UpdateInfo(doctorInfo);
		
		// 3.4 提交事务
		sqlSession.commit();
	}
	
	// 获得当天的剩余可挂号数量
	public static Integer GetAllowance(int id, String date, int time)
	{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		return doctorMapper.GetAllowance(id, date, time);
	}
	
	// 更新余量
	public void UpdateAllowance(int timeslot_id, int new_allow)
	{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		// 3. 获取医生信息
		doctorMapper.UpdateAllowance(timeslot_id, new_allow);
		
		// 3.4 提交事务
		sqlSession.commit();
	}

	// 获得所有接诊记录
	public static List<Case> GetAllCases(int doctorId) {
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 
		DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);
		
		return doctorMapper.GetAllCases(doctorId);
		
	}
}
