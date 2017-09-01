package com.company.security.service;

import java.util.List;

import com.company.security.domain.LoginUser;
import com.company.security.domain.SecurityUser;

public interface SecurityUserCacheService {
	/**
	 * 
	 * @param loginUser
	 * @return
	 */
	public boolean putBasicInfo(LoginUser loginUser);
	/**
	 * 获取基本信息
	 * @param userId
	 * @return
	 */
	public LoginUser getBasicInfo(long userId);
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public LoginUser getBInfoByPhone(String phone);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public LoginUser getBInfoByEmail(String email);
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public boolean removeBasinInfo(long userId);
	
	
	
	
	
	/**
	 * 获取用户基本信息最后更新时间
	 * @param securityUser
	 */
	public long getLastModifyTime(long userId);
	
	/**
	 * 设置用户基本信息最后更新时间
	 * @param userId
	 * @param lastModifyTime
	 * @return
	 */
	public boolean putLastModifyTime(long userId,long lastModifyTime);
		
}
