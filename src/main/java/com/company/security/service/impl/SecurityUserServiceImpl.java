package com.company.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.security.domain.SecurityUser;
import com.company.security.mapper.SecurityUserMapper;
import com.company.security.service.SecurityUserService;
@Service("securityUserService")
public class SecurityUserServiceImpl implements SecurityUserService {
	@Autowired
	private SecurityUserMapper securityUserMapper;
	@Override
	public SecurityUser selectUserById(long userId) {
		// TODO Auto-generated method stub
		List<SecurityUser> securityUsers= securityUserMapper.selectSecurityUser(userId);
		if(securityUsers!=null&& securityUsers.size()>0)
		{
			return securityUsers.get(0);
		}
		return null;
	}

	@Override
	public SecurityUser selectUserByPhone(long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityUser selectUserByemail(long email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityUser selectUserByIdNo(int idType, String idNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPassword(long userId, String pasword, String algorithm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(long userId, String newpasword, String oldPassword, String algorithm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetPassword(long userId, String newpasword, String algorithm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateUserBasicInfo(SecurityUser securityUser) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmail(long userId, String email, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePhone(long userId, String countryCode, String phone, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateIdNo(long userId, String idType, String idNo, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatus(long userId, int status) {
		// TODO Auto-generated method stub
		return 0;
	}

}
