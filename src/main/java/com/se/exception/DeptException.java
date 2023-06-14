package com.se.exception;

import com.se.util.Log;

public class DeptException extends SeException{
	public DeptException(ErrorType errorType, String methodName) {
		super(errorType);
		Log.LogToConsole(false, errorTy.toString(), methodName);
	}
}
