package com.company.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.security.domain.SecurityAllaction;

@Mapper
public interface SecurityAllActionMapper {
	/**
	 * 插入新的扣费记录
	 * 
	 * @param securityAllaction
	 */
	@Insert("INSERT INTO security_allaction(actionId,description,action,system) VALUES(#{actionid},#{description},#{action},#{system})")	   
	public void insertSecurityAllaction(SecurityAllaction securityAllaction);
	
	/**
	 * 选择所有记录
	 * @return
	 */
	@Select("SELECT * FROM security_allaction ")
	public List<SecurityAllaction> selectAllAction();
	
	/**
	 * 根据anctionID选择制定的action
	 * @param actionId
	 * @return
	 */
	@Select("SELECT * FROM security_allaction where actionId = #{actionid}")
	public List<SecurityAllaction> selectActionByid(long actionId);
	
	/**
	 * 根据系统选择所有的action
	 * @param system
	 * @return
	 */
	@Select("SELECT * FROM security_allaction where system = #{system}")
	public List<SecurityAllaction> selectActionBySystem(String system);	
	
	/**
	 * 更新制定的action
	 * @param securityAllaction
	 * @return
	 */
	@Update("update security_allaction set description=#{description},action=#{action},system=#{system} where actionId = #{actionid}")
	public int updateAction(SecurityAllaction securityAllaction);	
	
	/**
	 * 删除制定的action
	 * @param actionId
	 * @return
	 */
	@Delete("delete FROM security_allaction where actionId = #{actionid}")
	public int deleteActionByid(long actionId);
	
}
