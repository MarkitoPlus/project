package com.se.pojo;

import com.se.util.Encrypt;
/*
 * 账户实体类
 * */
public class Account {
	/*
	 * 账户类成员变量
	 * */
	protected int	 	  	accountId;			// 账号ID,唯一表示账户
	
	protected String 	 	account;	 		// 账号名
	
	protected String 	  	password;	 		// 密码
	
	protected String		repassword;			// 确认密码
	
	protected String		ecpassword;			// 加密后密码
	
	protected AccountType 	type;		 		// 账号类型
	
	protected int			typeVal;			// 账号类型值

	private static String 	accountReg			// 账号正则表达式: 字母开头，允许5-16字节，允许字母数字下划线
		= "[a-zA-Z][a-zA-Z0-9_]{4,15}";		 

	private static String 	passwordReg			// 密码正则表达式: 必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-16之间
		= "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$"; 

	static private int invalidAccountId = -1; 	//非法id

	/*
	 * 账户类Getter & Setter方法
	 * */
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepassword() {
		return repassword;
	}
	
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}	
	
	public AccountType getType() {
		return type;
	}
	
	public void setType(AccountType type) {
		this.type = type;
	}
	
	public static String getAccountReg() {
		return accountReg;
	}
	
	public static String getPasswordReg() {
		return passwordReg;
	}
	
	public String getEcpassword() {
		return ecpassword;
	}
	
	public void setEcpassword(String ecPassword) {
		this.ecpassword = ecPassword;
	}
		
	public int getTypeVal() {
		return typeVal;
	}
	
	public void setTypeVal(int typeVal) {
		this.typeVal = typeVal;
	}
	
	public static int getInvalidAccountId() {
		return invalidAccountId;
	}
	
	/*
	 * 构造方法
	 * */
	public Account() {
		this.type = AccountType.AccountTy;
	}
	
	public Account(int accountId, String account, String password, String repassword, AccountType type) {
		super();
		this.accountId = accountId;
		this.account = account;
		this.password = password;
		this.repassword = repassword;
		this.type = type;
		this.typeVal = this.type.ordinal();
	}
	
	public Account(int accountId, String account, String password, String repassword, int typeVal) {
		super();
		this.accountId = accountId;
		this.account = account;
		this.password = password;
		this.repassword = repassword;
		this.typeVal = typeVal;
		this.type = AccountType.values()[typeVal];
	}
	
	public Account(int accountId, String account, String ecpassword, int typeVal) {
		super();
		this.accountId = accountId;
		this.account = account;
		this.ecpassword = ecpassword;
		this.typeVal = typeVal;
		this.type = AccountType.values()[typeVal];
	}
	
	public Account(int accountId, String account, int typeVal) {
		super();
		this.accountId = accountId;
		this.account = account;
		this.typeVal = typeVal;
		this.type = AccountType.values()[typeVal];
	}
	
	public Account(String accountName, String password) {
		super();
		this.accountId = Account.getInvalidAccountId();
		this.account = accountName;
		this.password = password;
	}

}
