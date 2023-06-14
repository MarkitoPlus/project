package com.se.exception;

public enum ErrorType {	
	InvalidAccountId,		// 返回无效账户Id
	
	InvalidAccountName, 	// 非法账户名
	
	InvalidPassword,		// 非法密码
			
	InvalidTypeVal,			// 空类型值
	
	InvalidPatientName,		// 非法姓名
	
	InvalidAge,				// 非法年龄
	
	InvalidPhone,			// 非法电话
	
	InvalidMail,			// 非法邮箱
	
	InvalidIdCard,			// 非法身份证
		
	InvalidPatientId,		// 非法患者Id
	
	InconsistentType,		// 不一致的类型
	
	InconsistentPassword,	// 不一致的密码
	
	EmptyAccountName,		// 空账号名
	
	EmptyPassword,			// 空密码
	
	EmptyType,				// 空类型
	
	DuplicatedAccountName, 	// 重复账户名
	
	NonexistentAccountName,	// 不存在账号 
	
	NonesistentPatient,		// 不存在患者
	
	ErrorPassword,			// 错误密码

	NonexistentDeptName,	// 科室数据丢失
}
