package com.company.security.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.company.security.Const.SecurityUserConst;
import com.company.security.domain.SecurityAllaction;
import com.company.security.domain.SecurityRole;
import com.company.security.domain.SecurityRoleaction;
import com.company.security.mapper.SecurityRoleMapper;
import com.company.security.mapper.SecurityRoleactionMapper;
import com.company.security.service.SecurityRoleService;

public class SecurityRoleServiceImpl implements SecurityRoleService {

	@Autowired
	private SecurityRoleMapper securityRoleMapper; 
	@Autowired
	private SecurityRoleactionMapper securityRoleactionMapper;
	@Override
	public SecurityRole selectSecurityRole(long roleid) {
		// TODO Auto-generated method stub
		List<SecurityRole> securityRoles = securityRoleMapper.selectSecurityRole(roleid);
	    if(securityRoles!=null&& securityRoles.size()>0)
	    {
	    	return securityRoles.get(0);
	    }
	    return null;
	}

	
	@Override
	public int deleteRoleAllAction(long roleid) {
		// TODO Auto-generated method stub
		return securityRoleactionMapper.deleteRoleAllAction(roleid);
	}

	@Override
	@Transactional 
	public int updateSecurityRole(List<SecurityRoleaction> securityRoleactions) {
		// TODO Auto-generated method stub
		int retNumber = 0;
		
		for(SecurityRoleaction securityRoleaction:securityRoleactions)
		{
			
			List<SecurityRoleaction> queryRoleactions=securityRoleactionMapper.selectRoleOneAction(securityRoleaction.getRoleid(), securityRoleaction.getActionid());
			if(queryRoleactions!=null&&queryRoleactions.size()>0)
			{
				securityRoleactionMapper.insertSecurityRoleaction(securityRoleaction);
				retNumber++;
			}
			else
			{
				retNumber=retNumber+securityRoleactionMapper.updateSecurityRoleaction(securityRoleaction);
			}
		}
		return retNumber;
	}
    
	/**
	 *先删除该roleId的所有记录，然后执行插入；
	 */
	@Override
	@Transactional 
	public int refreshSecurityRole(int roleId, List<SecurityRoleaction> securityRoleactions) {
		// TODO Auto-generated method stub
		securityRoleactionMapper.deleteRoleAllAction(roleId);
		int retNumber = 0;
		
		for(SecurityRoleaction securityRoleaction:securityRoleactions)
		{
			
			securityRoleactionMapper.insertSecurityRoleaction(securityRoleaction);
				retNumber++;
			
		}
		return retNumber;
	}


	@Override
	public void insertRole(SecurityRole securityRole) {
		// TODO Auto-generated method stub
		securityRoleMapper.insertSecurityRole(securityRole);
	}


	@Override
	public int updateRole(SecurityRole securityRole) {
		// TODO Auto-generated method stub
		return securityRoleMapper.updateSecurityRole(securityRole);
	}


	@Override
	public int deleteRole(int roleid) {
		// TODO Auto-generated method stub
		return securityRoleMapper.deleteSecurityRole(roleid);
	}	

}
