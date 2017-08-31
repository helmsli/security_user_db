package com.company.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.security.domain.SecurityAllaction;
import com.company.security.mapper.SecurityAllActionMapper;
import com.company.security.service.SecurityActionService;
@Service("securityActionService")
public class SecurityActionServiceImpl implements SecurityActionService {

	@Autowired
	SecurityAllActionMapper securityAllActionMapper;
	
	@Override
	public void insertSecurityaction(SecurityAllaction securityAllaction) {
		// TODO Auto-generated method stub
		 securityAllActionMapper.insertSecurityAllaction(securityAllaction);

	}

	@Override
	public List<SecurityAllaction> selectAllAction() {
		// TODO Auto-generated method stub
		return securityAllActionMapper.selectAllAction();
	}

	@Override
	public SecurityAllaction selectActionByid(long actionId) {
		// TODO Auto-generated method stub
	  List<SecurityAllaction> securityAllactions =securityAllActionMapper.selectActionByid(actionId);
	  if(securityAllactions!=null && securityAllactions.size()>0)
	  {
		  return securityAllactions.get(0);
	  }
	  return null;
	}

	@Override
	public List<SecurityAllaction> selectActionBySystem(String system) {
		// TODO Auto-generated method stub
		return securityAllActionMapper.selectActionBySystem(system);
	}

	@Override
	public int updateAction(SecurityAllaction securityAllaction) {
		// TODO Auto-generated method stub
		return securityAllActionMapper.updateAction(securityAllaction);
	}

	@Override
	public int deleteActionByid(long actionId) {
		// TODO Auto-generated method stub
		return securityAllActionMapper.deleteActionByid(actionId);
	}

}
