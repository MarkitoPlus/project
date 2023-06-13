package com.se.exception;

import com.se.pojo.Account;
import com.se.util.Log;

public class AccountExecption extends Exception{
	private ErrorType errorTy;
		
	public ErrorType getErrorTy() {
		return errorTy;
	}

	public AccountExecption(ErrorType errorTy, String methodName) {
		super();
		this.errorTy = errorTy;
		Log.LogToConsole(false, errorTy.toString(), methodName);
	}
}
