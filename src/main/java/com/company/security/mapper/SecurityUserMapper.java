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
			+ "passwordExt,email,emailVerified,phone,phoneCode,phoneVerified,sex,birthday,"
			+ "avatar,homeAddress,businessAddress,idNo,idType,idVerified,"
			+ "status,roles,extDate,createTime,createSource,updateTime) VALUES("
			+ "#{userId},#{loginName},#{lastName},#{firstName},#{displayName},#{password},"
			+ "#{passwordExt},#{email},#{emailVerified},#{phone},#{phoneCode},#{phoneVerified},#{sex},#{birthday},"
			+ "#{avatar},#{homeAddress},#{businessAddress},#{idNo},#{idType},#{idVerified},"
			+ "#{status},#{roles},#{extDate},#{createTime},#{createSource},#{updateTime})")	   
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
			+ "userId=#{userId},loginName=#{loginName},lastName=#{lastName},firstName=#{firstName},displayName=#{displayName},"
			+ "sex=#{sex},avatar=#{avatar},birthday=#{birthday},homeaddress=#{homeAddress},extDate=#{extDate},updateTime=#{updateTime} where userId = #{userId}")
	public int updateSecurityUser(SecurityUser securityUser);	
	
	/**
	 * 更新密码信息
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set password=#{password},passwordExt=#{passwordExt},updateTime=#{updateTime} where userId = #{userId} and passwordExt=#{oldPasswordExt}")
	public int updatePassword(SecurityUser securityUser);	
	/**
	 * 更新mail
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set email=#{email},emailVerified=#{emailVerified} where userId = #{userId}")
	public int verifyEmail(SecurityUser securityUser);
	/**
	 * 更新电话号码
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set phone=#{phone},phoneCCode=#{phoneCode},phoneVerified=#{phoneVerified} where userId = #{userId}")
	public int verifyPhone(SecurityUser securityUser);
	
	/**
	 * 更新身份信息
	 * @param securityUser
	 * @return
	 */
	@Update("update security_user set idNo=#{idNo},idType=#{idType},idVerified=#{idVerified} where userId = #{userId}")
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
