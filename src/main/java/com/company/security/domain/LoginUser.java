package com.company.security.domain;

public class LoginUser {
	/** 用户ID. */
	private long userId;
	/** 登录的名字. */
	private String loginname;
	/** 显示名字. */
	private String displayname;
	
	/** 电话号码. 带国家码的号码，国家码开头是00*/
	private String phone;

	/** 电话号码国家码. */
	private String phoneccode;
	/** 电话号码认证状态. */
	private int phoneverified;
	/** 头像地址. */
	private String avatar;

	
	/** 性别. */
	private int sex;

}
