package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.company.security.domain.SecurityUser;

@Mapper
public interface SecurityUserMapper {
	/**
	 * 
	 * @param securityUser
	 */
	@Insert("INSERT INTO security_user(userId,loginName,lastName,firstName,displayName,password,"
			+ "passwordExt,email,emailVerified,phone,phoneCCode,phoneVerified,sex,birthday,"
			+ "avatar,homeAddress,businessAddress,idNo,idType,idVerified,"
			+ "status,roles,extDate,createTime,createSource,updateTime) VALUES("
			+ "#{userId},#{loginname},#{lastname},#{firstname},#{displayname},#{password},"
			+ "#{passwordext},#{email},#{emailverified},#{phone},#{phoneccode},#{phoneverified},#{sex},#{birthday},"
			+ "#{avatar},#{homeaddress},#{businessaddress},#{idno},#{idtype},#{idverified},"
			+ "#{status},#{roles},#{extdate},#{createtime},#{createsource},#{updatetime})")	   
	public void insertSecurityUser(SecurityUser securityUser);
	
	
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM security_user where userId = #{userId} ")
	public List<SecurityUser> selectSecurityUser(long userId);
	
	/**
	 * 获取密码信息
	 * @param userId
	 * @return
	 */
	@Select("SELECT userId,password,passwordExt,email,phone,idNo,status FROM security_user where userId = #{userId}")
	public List<SecurityUser> selectPasswordByid(long userId);
	
	/**
	 * 更新信息，除了电话号码，密码，邮箱，ID，状态等信息
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set "
			+ "userId=#{userId},loginName=#{loginname},lastName=#{lastname},firstName=#{firstname},displayName=#{displayname},"
			+ "sex=#{sex},birthday=#{birthday},extDate=#{extdate},updateTime=#{updateTime} where userId = #{userId}")
	public int updateSecurityUser(SecurityUser securityUser);	
	
	/**
	 * 更新密码信息
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set password=#{password},passwordExt=#{passwordext} where userId = #{userId} and passwordExt=#{oldPasswordext}")
	public int updatePassword(SecurityUser securityUser);	
	/**
	 * 更新mail
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set email=#{email},emailVerified=#{emailverified} where userId = #{userId}")
	public int verifyEmail(SecurityUser securityUser);
	/**
	 * 更新电话号码
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set phone=#{phone},phoneCCode=#{phoneccode},phoneVerified=#{phoneverified} where userId = #{userId}")
	public int verifyPhone(SecurityUser securityUser);
	
	/**
	 * 更新身份信息
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set idNo=#{idno},idType=#{idtype},idVerified=#{idverified} where userId = #{userId}")
	public int verifyIdNo(SecurityUser securityUser);
	/**
	 * 更新状态
	 * @param userId
	 * @param status
	 * @return
	 */
	@Update("update security_user set status=#{status} where userId = #{userId}")
	public int updateStatus(@Param(value="userId")long userId,@Param(value="status")int status);
	
	@Update("update security_user set roles=#{roles} where userId = #{userId}")
	public int updateRoles(@Param(value="userId")long userId,@Param(value="roles")String roles);
	
	
	/**
	 * 删除用户信息
	 * @param userId
	 * @return
	 */
	@Delete("delete FROM security_user where userId = #{userId}")
	public int deleteSecurityUser(long userId);

}
