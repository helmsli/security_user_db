package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.security.domain.SecurityRoleaction;
@Mapper
public interface SecurityRoleactionMapper {
	/**
	 * 插入新的扣费记录
	 * 
	 * @param securityAllaction
	 */
	@Insert("INSERT INTO security_roleaction(actionId,roleId,description,action,authority,createTime,updateTime,createSource) VALUES(#{actionid},#{roleid},#{description},#{action},#{authority},#{createtime},#{updatetime},#{createsource})")	   
	public void insertSecurityRoleaction(SecurityRoleaction securityRoleaction);
	
	/**
	 * 选择记录
	 * @return
	 */
	@Select("SELECT * FROM security_roleaction where roleId= #{roleid}")
	public List<SecurityRoleaction> selectSecurityRoleaction(long roleid);
	
	
	@Select("SELECT * FROM security_roleaction where roleId= #{roleid} and actionI#{actionid}")
	public List<SecurityRoleaction> selectSecurityRoleaction(long roleid,long actionid);
	
	/**
	 * 更新制定的action
	 * @param securityAllaction
	 * @return
	 */
	
	@Update("update security_roleaction set description=#{description},action=#{action},authority=#{authority},createTime=#{createtime},updateTime=#{updatetime},createSource=#{createsource} where roleId= #{roleid} and actionI#{actionid}")
	public int updateSecurityRoleaction(SecurityRoleaction securityRoleaction);	
	
	/**
	 * 删除制定的action
	 * @param actionId
	 * @return
	 */
	@Delete("delete FROM security_roleaction where roleId= #{roleid} and actionI#{actionid}")
	public int deleteSecurityRoleaction(long roleid,long actionid);

	@Delete("delete FROM security_roleaction where roleId= #{roleid}")
	public int deleteSecurityRoleaction(long roleid);

}
