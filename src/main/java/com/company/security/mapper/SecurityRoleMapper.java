package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.company.security.domain.SecurityRole;
@Mapper
public interface SecurityRoleMapper {
	/**
	 * 插入新的扣费记录
	 * 
	 * @param securityAllaction
	 */
	@Insert("INSERT INTO security_role(roleId,name,description,createTime,updateTime,createSource) VALUES(#{roleid},#{name},#{description},#{createtime},#{updatetime},#{createsource})")	   
	public void insertSecurityRole(SecurityRole securityRole);
	
	/**
	 * 选择所有记录
	 * @return
	 */
	@Select("SELECT * FROM security_role where roleId= #{roleid}")
	public List<SecurityRole> selectSecurityRole(long roleid);
	
	/**
	 * 更新制定的action
	 * @param securityAllaction
	 * @return
	 */
	@Update("update security_role set name=#{name},description=#{description},createTime=#{createtime},updateTime=#{updatetime},createSource=#{createsource} where roleid = #{roleid}")
	public int updateSecurityRole(SecurityRole securityRole);	
	
	/**
	 * 删除制定的action
	 * @param actionId
	 * @return
	 */
	@Delete("delete FROM security_role where roleId= #{roleid}")
	public int deleteSecurityRole(long roleid);

}
