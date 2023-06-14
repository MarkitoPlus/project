package com.se.exception;

public class SeException extends Exception{
	protected ErrorType errorTy;
	
	public ErrorType getErrorTy() {
		return errorTy;
	}
	
	public SeException(ErrorType errorTy) {
		this.errorTy = errorTy;
	}
}
