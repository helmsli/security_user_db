package com.company.security.service.impl;

public class SecurityUserCacheKeyService {
	
	public static final String Key_prefix_LoginUser= "luser:";
	public static final String Key_prefix_phone= "lphone:";
	public static final String Key_prefix_email= "lemail:";
	public static final String Key_prefix_lastModify= "lmodify:";
	public static final String Key_prefix_UserLock= "lulock:";
	
	
	/**
	 * 获取同步锁的key
	 * @param userId
	 * @return
	 */
	public String getLockkey(long userId)
	{
		StringBuilder retStr = new StringBuilder();
		retStr.append(Key_prefix_UserLock);
		retStr.append(userId);
		return retStr.toString();
	}
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public String getLoginUserkey(long userId)
	{
		StringBuilder retStr = new StringBuilder();
		retStr.append(Key_prefix_LoginUser);
		retStr.append(userId);
		return retStr.toString();
	}
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public String getPhoneKey(String phone)
	{
		StringBuilder retStr = new StringBuilder();
		retStr.append(Key_prefix_phone);
		retStr.append(phone);
		return retStr.toString();
	}
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public String getEmailKey(String email)
	{
		StringBuilder retStr = new StringBuilder();
		retStr.append(Key_prefix_email);
		retStr.append(email);
		return retStr.toString();
	}
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public String getLastModifyKey(long userId)
	{
		StringBuilder retStr = new StringBuilder();
		retStr.append(Key_prefix_lastModify);
		retStr.append(userId);
		return retStr.toString();
	}
	
}
