package com.se.pojo;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import com.se.mapper.PatientMapper;
import com.se.util.Log;

/*
 * 医生
 * */
public class Doctor extends Account{

	private int 	doctorId; 					// 
	
	private String 	doctorName;    			    // 
	
	private int 	age;						// 
	
	private String 	phone;						// 
	
	private String 	mail;						// 
	
	private String 	deptName;						// 科室id

	private int 	status;						// 医生级别
	
	/*
	private static String patientNameReg  		// 姓名正则表达式
					= "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";	
	
	private static String phoneReg				// 手机号码正则表达式
					= "^1[3-9]\\d{9}$";			
	
	private static String mailReg				// 邮箱正则表达式
					= "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
	
	*/
	private static int invalidDoctorId = -1;	// 非法Id
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static int getInvalidDoctorId() {
		return invalidDoctorId;
	}
	
	// ctors
	public Doctor() {
		super();
		Log.LogToConsole(false, "Test::Invoke default ctr", "Doctor");
	}

	public Doctor(int account_id, String account, String password, String repassword,
			int doctorId, String doctorName, int age, String phone, String mail, String deptName, int status) {
		super(account_id, account, password, repassword, AccountType.DoctorTy);		
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
		this.deptName = deptName;
		this.status = status;
	}
	
	public Doctor(int doctorId, String doctorName, int age, String phone, String mail, 
			int accountId, String deptName, int status, String account, int typeVal) {
		super(accountId, account, typeVal);
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
		this.deptName = deptName;
		this.status = status;
	}
}