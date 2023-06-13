package com.se.mapper;

import com.se.pojo.Account;

public interface AccountMapper {
	/*
	 * 检查用户名是否存在
	 * */
	public Account SelectByAccountName(String account);
	
	/*
	 * 增加新用户
	 * */
	public void AddAccount(Account account);
	
	/*
	 * 检查用户名和密码是否存在
	 * */
	public Account SelectByNameAndPassword(Account account);
}
