package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.security.domain.SecurityUserEmail;
import com.company.security.domain.SecurityUserIdno;
@Mapper
public interface SecurityUserEmailMapper {
	/**
	 * 
	 * @param email
	 * @return
	 */
	@Select("SELECT * FROM security_user_email where email = #{email}")
	public List<SecurityUserEmail> selectUserId(String email);
	
	/**
	 * 
	 * @param securityUserEmail
	 */
	@Insert("insert into security_user_email(email,userId)values(#{email},#{userId})")
	public void insertUserEmail(SecurityUserEmail securityUserEmail);
	
	/**
	 * 
	 * @param securityUserEmail
	 * @return
	 */
	@Update("update security_user_email set userId = #{userId} where email = #{email}")
	public int updateUserEmail(SecurityUserEmail securityUserEmail);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	@Delete("delete from security_user_email where email = #{email}")
	public int deleteUserEmail(String email);

}
