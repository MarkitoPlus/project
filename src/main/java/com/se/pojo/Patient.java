package com.se.pojo;

import com.se.util.Log;

/*
 * 患者实体类
 * */
public class Patient extends Account{
	/*
	 * 患者类成员变量
	 * */
	private int 	patientId; 					// patientId唯一表示患者信息
	
	private String 	patientName;    			// 患者姓名
	
	private int 	age;						// 患者年龄
	
	private String 	phone;						// 患者手机号
	
	private String 	mail;						// 患者邮箱
	
	private String 	idCard;						// 患者身份证号

	private static String patientNameReg  		// 患者姓名正则表达式
					= "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";	
	
	private static String phoneReg				// 手机号码正则表达式
					= "^1[3-9]\\d{9}$";			
	
	private static String mailReg				// 邮箱正则表达式
					= "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
	
	private static String idCardReg				// 身份证号正则表达式
					= "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	
	private static int invalidPatientId = -1;	// 非法患者Id
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public static String getPatientNameReg() {
		return patientNameReg;
	}
	
	public static String getPhoneReg() {
		return phoneReg;
	}
	
	public static String getMailReg() {
		return mailReg;
	}
	
	public static String getIdCardReg() {
		return idCardReg;
	}
	
	public static int getInvalidPatientId() {
		return invalidPatientId;
	}
	
	public Patient() {
		super();
		Log.LogToConsole(false, "Test::Invoke default ctr", "Patient");
	}

	public Patient(int account_id, String account, String password, String repassword,
			int patientId, String patientName, int age, String phone, String mail, String idCard) {
		super(account_id, account, password, repassword, AccountType.PatientTy);		
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
		this.idCard = idCard;
	}
	
	public Patient(int patientId, String patientName, int age, String phone, String mail, int accountId, String idCard, String account, int typeVal) {
		super(accountId, account, typeVal);
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
		this.idCard = idCard;
	}
}