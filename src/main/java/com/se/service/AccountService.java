package com.se.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.se.exception.AccountExecption;
import com.se.exception.ErrorType;
import com.se.mapper.AccountMapper;
import com.se.pojo.Account;
import com.se.util.Encrypt;
import com.se.util.SqlSessionFactoryUtils;

public class AccountService{
	/*
	 * 静态类
	 * */
	private AccountService() {
		
	}
	
	/*
	 * 借助SqlSessionFactoryUtils静态方法获取sqlSessionFactory对象，用于创建sqlSession
	 * */
	private static SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
	
	/*
	 * 针对Account的数据检查
	 * */
	public static void Check(Account account, ErrorType errorType) throws AccountExecption{
		switch (errorType) {
		// 账号Id是否非法
		case InvalidAccountId:
			if(account.getAccountId() != Account.getInvalidAccountId()) 
				return; 
			break;
		
		// 账号名是否为空
		case EmptyAccountName:
			if(account.getAccount() != null)
				return;
			break;
		
		// 账号名是否重复
		case DuplicatedAccountName:
			SqlSession sqlSessionDA = factory.openSession();
			
			AccountMapper accountMapperDA = sqlSessionDA.getMapper(AccountMapper.class);
			
			Account aDA = accountMapperDA.SelectByAccountName(account.getAccount());

			sqlSessionDA.close();
			
			if(aDA == null) 
				return;
			break;
		
		// 账号名是否非法
		case InvalidAccountName:
			if(account.getAccount().matches(Account.getAccountReg()))
				return;
			break;
		
		// 密码是否为空
		case EmptyPassword:
			if(account.getPassword() != null)
				return;
			break;
		
		// 密码是否非法
		case InvalidPassword:
			if(account.getPassword().matches(Account.getPasswordReg()))
				return;
			break;
			
		// 两次密码是否一致
		case InconsistentPassword:
			if(account.getRepassword().equals(account.getPassword()))
				return;
			break;
			
		// 类型是否为空
		case EmptyType:
			if(account.getType() != null)
				return;
			break;
		
		// 类型值是否与类型不一致
		case InvalidTypeVal:
			if(account.getTypeVal() == account.getType().ordinal())
				return;
			break;
			
		// 不存在该账号名
		case NonexistentAccountName:
			SqlSession sqlSessionNA = factory.openSession();
			
			AccountMapper accountMapperNA = sqlSessionNA.getMapper(AccountMapper.class);
			
			Account aNA = accountMapperNA.SelectByAccountName(account.getAccount());

			sqlSessionNA.close();
			
			if(aNA != null) 
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
	 * 添加新的账号
	 * */
	private static void AddAccount(Account account) throws AccountExecption{
		// 1. 获取Sqlsession
		SqlSession sqlSession = factory.openSession();
		
		// 2. 获取AccountMapper
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
		
		// 3. 添加账号
		// 3.1 标注id用于校验是否成功添加
		account.setAccountId(Account.getInvalidAccountId());
		
		// 3.2 利用SHA算法取得密码哈希值
		account.setEcpassword(Encrypt.SHA256(account.getPassword()));
		
		// 3.3 添加账号
		accountMapper.AddAccount(account);
		
		// 3.4 提交事务
		sqlSession.commit();
		
		// 4. 释放资源 
		sqlSession.close();
		
		// 5. 检查是否成功
		Check(account, ErrorType.InvalidAccountId);
	
	}
	
	/*
	 * 获取账号信息
	 * */
	private static Account GetAccount(Account account) throws AccountExecption{
		SqlSession sqlSession = factory.openSession();
		
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
		
		Account result = accountMapper.SelectByNameAndPassword(account);

		sqlSession.close();
				
		return result;
	}
	
	/*
	 * 注册
	 * */
	public static void Register(Account account) throws AccountExecption{		
		// 1. 注册新的账号
		// 1.1 校验合法性
		Check(account, ErrorType.EmptyAccountName);
		Check(account, ErrorType.DuplicatedAccountName);
		Check(account, ErrorType.InvalidAccountName);
		Check(account, ErrorType.EmptyPassword);
		Check(account, ErrorType.InconsistentPassword);
		Check(account, ErrorType.InvalidPassword);
		Check(account, ErrorType.EmptyType);
		Check(account, ErrorType.InvalidTypeVal);
		
		// 1.2 向数据库中添加账号
		AddAccount(account);
	}

	/*
	 * 登陆
	 * */
	public static Account LogIn(Account account) throws AccountExecption{
		
		// 1.1 校验数据完整性
		Check(account, ErrorType.EmptyAccountName);
		Check(account, ErrorType.EmptyPassword);
		
		// 1.2 判断是否存在账号名
		Check(account, ErrorType.NonexistentAccountName); 
		
		// 1.3 尝试获取账号信息（验证密码哈希值是否一致)
		account.setEcpassword(Encrypt.SHA256(account.getPassword()));
		Account result = GetAccount(account);
		
		// 1.4 判断是否成功
		if(result == null) 
			throw new AccountExecption(ErrorType.ErrorPassword, "AccountService.GetAccount");
		
		return result;
	}

}
