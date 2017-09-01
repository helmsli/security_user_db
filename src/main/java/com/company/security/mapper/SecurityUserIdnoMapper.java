package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.security.domain.SecurityUserIdno;
import com.company.security.domain.SecurityUserPhone;
@Mapper
public interface SecurityUserIdnoMapper {
	/**
	 * 
	 * @param idtotalno
	 * @return
	 */
	@Select("SELECT * FROM security_user_idno where idTotalNo = #{idtotalno}")
	public List<SecurityUserIdno> selectUserId(String idtotalno);
	
	/**
	 * 
	 * @param securityUserIdno
	 */
	@Insert("insert into security_user_idno(idTotalNo,userId)values(#{idtotalno},#{userId})")
	public void insertUserIdNo(SecurityUserIdno securityUserIdno);
	
	/**
	 * 
	 * @param securityUserIdno
	 * @return
	 */
	@Update("update security_user_idno set userId = #{userId} where idTotalNo = #{idtotalno}")
	public int updateUserIdNo(SecurityUserIdno securityUserIdno);
	
	/**
	 * 
	 * @param idtotalno
	 * @return
	 */
	@Delete("delete from security_user_idno where idTotalNo = #{idtotalno}")
	public int deleteUserIdNo(String idtotalno);

}
