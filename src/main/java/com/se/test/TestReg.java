package com.se.test;

public class TestReg {
	
	public static void main(String[] args) {
		String regString = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";

		String pwdString = "hUangshunhui123";
		
		if(pwdString.matches(regString))
			System.out.println("匹配");
	}
}
