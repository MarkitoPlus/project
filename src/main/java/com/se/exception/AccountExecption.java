package com.se.exception;

import com.se.util.Log;

public class AccountExecption extends SeException{
	public AccountExecption(ErrorType errorTy, String methodName) {
		super(errorTy);
		Log.LogToConsole(false, errorTy.toString(), methodName);
	}
}
