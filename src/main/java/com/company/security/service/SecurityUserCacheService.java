package com.company.security.service;

import java.util.List;

import com.company.security.domain.SecurityUser;

public interface SecurityUserCacheService {
	
	/**
	 * 更新内存中基本数据的数据，除了密码数据，过期时间由实现类决定
	 * @param securityUser
	 */
	public void putBasicInfo(SecurityUser securityUser);
	/**
	 * 获取基本信息
	 * @param userId
	 * @return
	 */
	public SecurityUser getBasicInfo(long userId);
	//
	public int updatePassword(SecurityUser securityUser);
	public SecurityUser getLoginInfo(long userId);
	
	
}
