package com.company.security.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Model class of security_user.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class SecurityUser implements Serializable {

	//正常
	public static final int Status_OK = 0;
	//需要认证
	public static final int Status_needVerified = 1;
	
	//禁用
	public static final int Status_suspend = 2;
	
	
	
	//phone,email,id认证成功
	public static final int verified_Success = 1;
	//认证失败
	public static final int verified_Fail = 0;
	//身份证
	public static final int IDTYPE_IdCard = 0;
	//护照
	public static final int IDTYPE_Passport = 1;
	
	//男
	public static final int SEX_Male=0;
	//女
	public static final int SEX_FeMale=1;
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 用户ID. */
	private long userId;

	/** 登录的名字. */
	private String loginName;

	/** 姓. */
	private String lastName;

	/** 名. */
	private String firstName;

	/** 显示名字. */
	private String displayName;

	/** 密码. */
	private String password;

	/** 密码扩展字段. */
	private String passwordExt;

	
	/** 密码扩展字段. */
	private String oldPasswordExt;

	/** 邮箱. */
	private String email;

	/** 邮箱认证状态. */
	private int emailVerified;

	/** 电话号码. 带国家码的号码，国家码开头是00*/
	private String phone;

	/** 电话号码国家码. */
	private String phoneCode;

	/** 电话号码认证状态. */
	private int phoneVerified;

	/** 性别. */
	private int sex=-1;

	/** 生日. */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date birthday;

	/** 头像地址. */
	private String avatar;

	/** 家庭住址. */
	private String homeAddress;

	/** 单位地址. */
	private String businessAddress;

	/** 证件号码. */
	private String idNo;

	/** 证件类型. */
	private int idType=-1;

	/** 证件是否审核过. */
	private int idVerified=-1;

	/** 账户状态. */
	private int status;

	/** 拥有的角色.   0--买家/普通用户，注册默认用户    1-- 卖家/讲师     255--超级管理员    10 --讲师待审核*/
	private String roles="0";

	/** 扩展数据. */
	private String extDate;

	/** 创建时间. */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 创建来源. */
	private String createSource;

	/** 更新时间. */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * Constructor.
	 */
	public SecurityUser() {
		this.setRoles("0");
	}

	/**
	 * Set the 用户ID.
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Get the 用户ID.
	 * 
	 * @return 用户ID
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Set the 登录的名字.
	 * 
	 * @param loginname
	 *            登录的名字
	 */
	public void setLoginName(String loginname) {
		this.loginName = loginname;
	}

	/**
	 * Get the 登录的名字.
	 * 
	 * @return 登录的名字
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 * Set the 姓.
	 * 
	 * @param lastname
	 *            姓
	 */
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * Get the 姓.
	 * 
	 * @return 姓
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Set the 名.
	 * 
	 * @param firstname
	 *            名
	 */
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * Get the 名.
	 * 
	 * @return 名
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Set the 显示名字.
	 * 
	 * @param displayname
	 *            显示名字
	 */
	public void setDisplayName(String displayname) {
		this.displayName = displayname;
	}

	/**
	 * Get the 显示名字.
	 * 
	 * @return 显示名字
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Set the 密码.
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the 密码.
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the 密码扩展字段.
	 * 
	 * @param passwordext
	 *            密码扩展字段
	 */
	public void setPasswordExt(String passwordext) {
		if(passwordext!=null)
		{
			
				this.passwordExt = passwordext;
		}			
		
	}

	/**
	 * Get the 密码扩展字段.
	 * 
	 * @return 密码扩展字段
	 */
	public String getPasswordExt() {
		return this.passwordExt;
	}

	/**
	 * Set the 邮箱.
	 * 
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the 邮箱.
	 * 
	 * @return 邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set the 邮箱认证状态.
	 * 
	 * @param emailverified
	 *            邮箱认证状态
	 */
	public void setEmailVerified(int emailverified) {
		this.emailVerified = emailverified;
	}

	/**
	 * Get the 邮箱认证状态.
	 * 
	 * @return 邮箱认证状态
	 */
	public int getEmailVerified() {
		return this.emailVerified;
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
	 * Set the 电话号码国家码.
	 * 
	 * @param phoneccode
	 *            电话号码国家码
	 */
	public void setPhoneCode(String phoneccode) {
		this.phoneCode = phoneccode;
	}

	/**
	 * Get the 电话号码国家码.
	 * 
	 * @return 电话号码国家码
	 */
	public String getPhoneCode() {
		return this.phoneCode;
	}

	/**
	 * Set the 电话号码认证状态.
	 * 
	 * @param phoneverified
	 *            电话号码认证状态
	 */
	public void setPhoneVerified(int phoneverified) {
		this.phoneVerified = phoneverified;
	}

	/**
	 * Get the 电话号码认证状态.
	 * 
	 * @return 电话号码认证状态
	 */
	public int getPhoneVerified() {
		return this.phoneVerified;
	}

	/**
	 * Set the 性别.
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * Get the 性别.
	 * 
	 * @return 性别
	 */
	public int getSex() {
		return this.sex;
	}

	/**
	 * Set the 生日.
	 * 
	 * @param birthday
	 *            生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Get the 生日.
	 * 
	 * @return 生日
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * Set the 头像地址.
	 * 
	 * @param avatar
	 *            头像地址
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Get the 头像地址.
	 * 
	 * @return 头像地址
	 */
	public String getAvatar() {
		return this.avatar;
	}

	/**
	 * Set the 家庭住址.
	 * 
	 * @param homeaddress
	 *            家庭住址
	 */
	public void setHomeAddress(String homeaddress) {
		this.homeAddress = homeaddress;
	}

	/**
	 * Get the 家庭住址.
	 * 
	 * @return 家庭住址
	 */
	public String getHomeAddress() {
		return this.homeAddress;
	}

	/**
	 * Set the 单位地址.
	 * 
	 * @param businessaddress
	 *            单位地址
	 */
	public void setBusinessAddress(String businessaddress) {
		this.businessAddress = businessaddress;
	}

	/**
	 * Get the 单位地址.
	 * 
	 * @return 单位地址
	 */
	public String getBusinessAddress() {
		return this.businessAddress;
	}

	/**
	 * Set the 证件号码.
	 * 
	 * @param idno
	 *            证件号码
	 */
	public void setIdNo(String idno) {
		this.idNo = idno;
	}

	/**
	 * Get the 证件号码.
	 * 
	 * @return 证件号码
	 */
	public String getIdNo() {
		return this.idNo;
	}

	/**
	 * Set the 证件类型.
	 * 
	 * @param idtype
	 *            证件类型
	 */
	public void setIdType(int idtype) {
		this.idType = idtype;
	}

	/**
	 * Get the 证件类型.
	 * 
	 * @return 证件类型
	 */
	public int getIdType() {
		return this.idType;
	}

	/**
	 * Set the 证件是否审核过.
	 * 
	 * @param idverified
	 *            证件是否审核过
	 */
	public void setIdVerified(int idverified) {
		this.idVerified = idverified;
	}

	/**
	 * Get the 证件是否审核过.
	 * 
	 * @return 证件是否审核过
	 */
	public int getIdVerified() {
		return this.idVerified;
	}

	/**
	 * Set the 账户状态.
	 * 
	 * @param status
	 *            账户状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Get the 账户状态.
	 * 
	 * @return 账户状态
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * Set the 拥有的角色.
	 * 
	 * @param roles
	 *            拥有的角色
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * Get the 拥有的角色.
	 * 
	 * @return 拥有的角色
	 */
	public String getRoles() {
		return this.roles;
	}

	/**
	 * Set the 扩展数据.
	 * 
	 * @param extdate
	 *            扩展数据
	 */
	public void setExtDate(String extdate) {
		this.extDate = extdate;
	}

	/**
	 * Get the 扩展数据.
	 * 
	 * @return 扩展数据
	 */
	public String getExtDate() {
		return this.extDate;
	}

	/**
	 * Set the 创建时间.
	 * 
	 * @param createtime
	 *            创建时间
	 */
	public void setCreateTime(Date createtime) {
		this.createTime = createtime;
	}

	/**
	 * Get the 创建时间.
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * Set the 创建来源.
	 * 
	 * @param createsource
	 *            创建来源
	 */
	public void setCreateSource(String createsource) {
		this.createSource = createsource;
	}

	/**
	 * Get the 创建来源.
	 * 
	 * @return 创建来源
	 */
	public String getCreateSource() {
		return this.createSource;
	}

	/**
	 * Set the 更新时间.
	 * 
	 * @param updatetime
	 *            更新时间
	 */
	public void setUpdateTime(Date updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * Get the 更新时间.
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	
	public String getOldPasswordExt() {
		return oldPasswordExt;
	}

	public void setOldPasswordExt(String oldPasswordext) {
		this.oldPasswordExt = oldPasswordext;
	}

	public LoginUser getLoginUser()
	{
		LoginUser loginUser = new LoginUser();
		loginUser.setAvatar(this.getAvatar());
		loginUser.setDisplayName(this.getDisplayName());
		loginUser.setEmail(this.getEmail());
		loginUser.setEmailVerified(this.getEmailVerified());
		loginUser.setLoginName(this.getLoginName());
		loginUser.setPassword(this.getPassword());
		loginUser.setPasswordExt(this.getPasswordExt());
		loginUser.setPhone(this.getPhone());
		loginUser.setPhoneCode(this.getPhoneCode());
		loginUser.setPhoneVerified(this.getPhoneVerified());
		loginUser.setRoles(this.getRoles());
		loginUser.setSex(this.getSex());
		loginUser.setStatus(this.getStatus());
		loginUser.setUserId(this.getUserId());
		return loginUser;
	}

	@Override
	public String toString() {
		return "SecurityUser [userId=" + userId + ", loginName=" + loginName + ", lastName=" + lastName + ", firstName="
				+ firstName + ", displayName=" + displayName + ", password=" + password + ", passwordExt=" + passwordExt
				+ ", oldPasswordExt=" + oldPasswordExt + ", email=" + email + ", emailVerified=" + emailVerified
				+ ", phone=" + phone + ", phoneCode=" + phoneCode + ", phoneVerified=" + phoneVerified + ", sex=" + sex
				+ ", birthday=" + birthday + ", avatar=" + avatar + ", homeAddress=" + homeAddress
				+ ", businessAddress=" + businessAddress + ", idNo=" + idNo + ", idType=" + idType + ", idVerified="
				+ idVerified + ", status=" + status + ", roles=" + roles + ", extDate=" + extDate + ", createTime="
				+ createTime + ", createSource=" + createSource + ", updateTime=" + updateTime + "]";
	}

	

}
