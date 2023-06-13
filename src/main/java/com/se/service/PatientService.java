package com.se.service;

import javax.security.auth.login.AccountException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.mapper.AccountMapper;
import com.se.mapper.PatientMapper;
import com.se.pojo.Account;
import com.se.pojo.AccountType;
import com.se.pojo.Patient;
import com.se.util.SqlSessionFactoryUtils;

public class PatientService{
	/*
	 * 借助SqlSessionFactoryUtils静态方法获取sqlSessionFactory对象，用于创建sqlSession
	 * */
	private static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
	
	
	/*
	 * 静态类
	 * */
	private PatientService() {
		
	}
	
	/*
	 * 针对Patient的数据检查
	 * */
	public static void Check(Patient patient, ErrorType errorType) throws AccountExecption{
		switch (errorType) {
		case InconsistentType:
			if(patient.getType() == AccountType.PatientTy)
				return;
			break;
		
		case InvalidPatientName:
			if(patient.getPatientName()!=null && patient.getPatientName().matches(Patient.getPatientNameReg()))
				return;
			break;
		
		case InvalidAge:
			if(patient.getAge() > 0 && patient.getAge()<120)
				return;
			break;
			
		case InvalidPhone:
			if(patient.getPhone()!=null && patient.getPhone().matches(Patient.getPhoneReg()))
				return;
			break;
			
		case InvalidMail:
			if(patient.getMail()!=null && patient.getMail().matches(Patient.getMailReg()))
				return;
			break;
			
		case InvalidIdCard:
			if(patient.getIdCard()!=null && patient.getIdCard().matches(Patient.getIdCardReg()))
				return;
			break;
		
		case InvalidPatientId:
			if(patient.getPatientId()!=Patient.getInvalidPatientId())
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
	
	/*
	 * 添加患者信息
	 * */
	private static void AddPatient(Patient patient) throws AccountExecption{		
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 获取PatientMapper
		PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
		
		// 3. 添加患者信息
		// 3.1 标注id用于校验是否添加成功
		patient.setPatientId(Patient.getInvalidPatientId());
		
		// 3.2 添加患者
		patientMapper.AddPatient(patient);
		
		// 3.3 提交事务
		sqlSession.commit();
		
		// 4. 释放资源
		sqlSession.close();
		
		// 5. 检查是否成功
		Check(patient, ErrorType.InvalidPatientId);
	}
	
	/*
	 * 获取患者信息
	 * */
	private static Patient GetPatient(Account account) throws AccountExecption{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 获取PatientMapper
		PatientMapper patientMapper = sqlSession.getMapper(PatientMapper.class);
		
		// 3. 获取患者信息
		Patient result = patientMapper.GetPatient(account);
		
		return result;
	}
	
	/*
	 * 注册患者
	 * 	1. 检查数据
	 * 
	 * 	2. 添加患者信息
	 * */
	public static void Register(Patient patient) throws AccountExecption{
		// 1. 注册新的患者
		// 1.1 调用AccountService注册方法
		AccountService.Register(patient);
		
		// 1.2 校验合法性
		Check(patient, ErrorType.InconsistentType);
		Check(patient, ErrorType.InvalidPatientName);
		Check(patient, ErrorType.InvalidAge);
		Check(patient, ErrorType.InvalidPhone);
		Check(patient, ErrorType.InvalidMail);
		Check(patient, ErrorType.InvalidIdCard);
		
		// 1.3 向数据库中添加账号
		AddPatient(patient);
		
	}

	/*
	 * 患者登陆 传入Account实例， 携带需要的信息account 和 password
	 * */
	public static Patient LogIn(Account account) throws AccountExecption{
		// 校验登陆
		// 1. 调用AccountService登陆方法，若成功，会返回携带accountId与type的Account实例
		Account result= AccountService.LogIn(account);
		
		
		// . 获取患者数据
		Patient patient = GetPatient(result);
		
		// 3. 判断是否成功
		if(patient == null)
			throw new AccountExecption(ErrorType.NonesistentPatient, "PatientService.LogIn");
		
		return patient;
	}
}
