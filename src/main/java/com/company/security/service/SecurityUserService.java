package com.company.security.service;

import java.util.List;

import com.company.security.domain.SecurityUser;

public interface SecurityUserService {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public SecurityUser selectUserById(long userId);
	/**
	 * 
	 * @param phone
	 * @return
	 */
	public SecurityUser selectUserByPhone(long phone);
	/**
	 * 
	 * @param email
	 * @return
	 */
	public SecurityUser selectUserByemail(long email);
	/**
	 * 
	 * @param idType
	 * @param idNo
	 * @return
	 */
	public SecurityUser selectUserByIdNo(int idType,String idNo);
	/**
	 * 
	 * @param userId
	 * @param pasword
	 * @param algorithm
	 * @return
	 */
	public boolean  checkPassword(long userId,String pasword,String algorithm);
	/**
	 * 
	 * @param userId
	 * @param newpasword
	 * @param oldPassword
	 * @param algorithm
	 * @return
	 */
	public boolean  updatePassword(long userId,String newpasword,String oldPassword,String algorithm);
	/**
	 * 
	 * @param userId
	 * @param newpasword
	 * @param algorithm
	 * @return
	 */
	public boolean  resetPassword(long userId,String newpasword,String algorithm);
	/**
	 * 
	 * @param securityUser
	 * @return
	 */
	public int updateUserBasicInfo(SecurityUser securityUser);
	/**
	 * 
	 * @param userId
	 * @param email
	 * @param status
	 * @return
	 */
	public int updateEmail(long userId,String email,int status);
	/**
	 * 
	 * @param userId
	 * @param countryCode
	 * @param phone
	 * @param status
	 * @return
	 */
	public int updatePhone(long userId,String countryCode,String phone,int status);
	/**
	 * 
	 * @param userId
	 * @param idType
	 * @param idNo
	 * @param status
	 * @return
	 */
	public int updateIdNo(long userId,String idType,String idNo,int status);
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public int updateStatus(long userId,int status);
	
}
