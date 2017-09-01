package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.security.domain.SecurityUser;
import com.company.security.domain.SecurityUserPhone;
@Mapper
public interface SecurityUserPhoneMapper {
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	@Select("SELECT * FROM security_user_phone where phone = #{phone}")
	public List<SecurityUserPhone> selectUserId(String phone);
	
	/**
	 * 
	 * @param securityUserPhone
	 */
	@Insert("insert into security_user_phone(phone,userId)values(#{phone},#{userId})")
	public void insertUserPhone(SecurityUserPhone securityUserPhone);
	
	/**
	 * 
	 * @param securityUserPhone
	 * @return
	 */
	@Update("update security_user_phone set userId = #{userId} where phone = #{phone}")
	public int updateUserPhone(SecurityUserPhone securityUserPhone);
	
	/**
	 * 
	 * @param phone
	 * @return
	 */
	@Delete("delete from security_user_phone where phone = #{phone}")
	public int deleteUserPhone(String phone);
}
