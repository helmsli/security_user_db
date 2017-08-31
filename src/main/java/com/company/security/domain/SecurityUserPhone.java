package com.company.security.domain;

import java.io.Serializable;

/**
 * Model class of security_user_phone.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class SecurityUserPhone implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 电话号码. */
	private String phone;

	/** 用户ID. */
	private Long userId;

	/**
	 * Constructor.
	 */
	public SecurityUserPhone() {
	}

	/**
	 * Set the 电话号码.
	 * 
	 * @param phone
	 *            电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the 电话号码.
	 * 
	 * @return 电话号码
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Set the 用户ID.
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Get the 用户ID.
	 * 
	 * @return 用户ID
	 */
	public Long getUserId() {
		return this.userId;
	}


}