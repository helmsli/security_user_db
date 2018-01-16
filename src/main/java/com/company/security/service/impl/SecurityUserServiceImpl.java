package com.company.security.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.company.security.Const.SecurityUserConst;
import com.company.security.domain.LoginUser;
import com.company.security.domain.SecurityUser;
import com.company.security.domain.SecurityUserEmail;
import com.company.security.domain.SecurityUserIdno;
import com.company.security.domain.SecurityUserPhone;
import com.company.security.mapper.SecurityUserEmailMapper;
import com.company.security.mapper.SecurityUserIdnoMapper;
import com.company.security.mapper.SecurityUserMapper;
import com.company.security.mapper.SecurityUserPhoneMapper;
import com.company.security.service.SecurityUserCacheService;
import com.company.security.service.SecurityUserService;
import com.company.security.utils.SecurityUserAlgorithm;
@Service("securityUserService")
public class SecurityUserServiceImpl implements SecurityUserService {
	@Autowired
	private SecurityUserMapper securityUserMapper;
	
	@Autowired
	private SecurityUserPhoneMapper securityUserPhoneMapper;
	@Autowired
	private SecurityUserEmailMapper securityUserEmailMapper;
	
	@Autowired
	private SecurityUserIdnoMapper securityUserIdnoMapper;
	
	@Autowired
	private SecurityUserCacheService securityUserCacheService;
	
	@Value("${db.dbUserkey}")  
	private String dbUserKey;
	
	@Value("${hessian.transferUserKey}")  
	private String transferUserKey;
	
	/**
	 * 比较数据库是否有人篡改过
	 * @param securityUser
	 * @return
	 */
	protected boolean checkDbCrc(SecurityUser securityUser)
	{
		String nowKey=getDbUserCrcKey(securityUser);
		if(!nowKey.equalsIgnoreCase(securityUser.getPasswordExt()))
		{
			return false;
		}
		return true;
	}
	/**
	 * 获取数据库签名
	 * @param securityUser
	 * @return
	 */
	protected String getDbUserCrcKey(SecurityUser securityUser)
	{
		try {
			StringBuilder str = new StringBuilder();
			str.append(securityUser.getUserId());
			str.append("*");
			if(!StringUtils.isEmpty(securityUser.getPassword()))
			{
				str.append(securityUser.getPassword());
			}
			return SecurityUserAlgorithm.EncoderByMd5(this.dbUserKey,str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public SecurityUser selectUserById(long userId) {
		// TODO Auto-generated method stub
		List<SecurityUser> securityUsers= securityUserMapper.selectSecurityUser(userId);
		if(securityUsers!=null&& securityUsers.size()>0)
		{
			
			if(this.checkDbCrc(securityUsers.get(0)))
			{
				return securityUsers.get(0);
			}
		}
		return null;
	}

	@Override
	public SecurityUser selectUserByPhone(String phone) {
		// TODO Auto-generated method stub
		List<SecurityUserPhone> userids = securityUserPhoneMapper.selectUserId(phone);
		if(userids!=null&&userids.size()>0)
		{
			SecurityUserPhone securityUserPhone=userids.get(0);
			SecurityUser securityUser=  selectUserById(securityUserPhone.getUserId());
			if(securityUser!=null)
			{
				if(phone.equalsIgnoreCase(securityUser.getPhone()))
				{
					return securityUser;
				}
			}
		}
		return null;
	}

	@Override
	public SecurityUser selectUserByemail(String email) {
	
		// TODO Auto-generated method stub
		List<SecurityUserEmail> userids = securityUserEmailMapper.selectUserId(email);
		if(userids!=null&&userids.size()>0)
		{
			SecurityUserEmail userid=userids.get(0);
			SecurityUser securityUser=  selectUserById(userid.getUserId());
			if(securityUser!=null)
			{
				if(email.equalsIgnoreCase(securityUser.getEmail()))
				{
					return securityUser;
				}
			}
		}
		return null;
	}

	@Override
	public SecurityUser selectUserByIdNo(int idType, String idNo) {
		// TODO Auto-generated method stub
		List<SecurityUserIdno> userids = securityUserIdnoMapper.selectUserId(SecurityUserIdno.getIdtotalno(idType, idNo));
		if(userids!=null&&userids.size()>0)
		{
			SecurityUserIdno userid=userids.get(0);
			SecurityUser securityUser= selectUserById(userid.getUserId());
			if(securityUser!=null)
			{
				if(idNo.equalsIgnoreCase(securityUser.getIdNo()))
				{
					return securityUser;
				}
			}
		}
		return null;
	}


	
	public boolean checkPassword(long userId, String pasword, String algorithm) {
		// TODO Auto-generated method stub
		if(!SecurityUserAlgorithm.checkByMd5(this.transferUserKey, pasword, algorithm))
		{
			return false;
		}
		List<SecurityUser> securityUsers= securityUserMapper.selectPasswordByid(userId);
		if(securityUsers!=null&& securityUsers.size()>0)
		{
			SecurityUser securityUser =  securityUsers.get(0);
			boolean crcOk = this.checkDbCrc(securityUser);
			if(crcOk&&pasword.equalsIgnoreCase(securityUser.getPassword()))
			{
				
				return true;
			}
			
		}
		return false;
		
		
		
	}

	@Override
	public boolean updatePassword(long userId, String newpasword, String oldPassword, String algorithm) {
		// TODO Auto-generated method stub
		//判断是否传输是合法的
		if(!SecurityUserAlgorithm.checkByMd5(this.transferUserKey, newpasword, algorithm))
		{
			return false;
		}
		//获取用户的密码信息，
		List<SecurityUser> securityUsers= securityUserMapper.selectPasswordByid(userId);
		if(securityUsers!=null&& securityUsers.size()>0)
		{
			SecurityUser securityUser =  securityUsers.get(0);
			boolean crcOk = this.checkDbCrc(securityUser);
			//比较老的密码是否是正确的。
			if(crcOk&&oldPassword.equalsIgnoreCase(securityUser.getPassword()))
			{
				securityUser.setOldPasswordExt(securityUser.getPasswordExt());
				securityUser.setPassword(newpasword);
				securityUser.setPasswordExt(this.getDbUserCrcKey(securityUser));
				securityUser.setUpdateTime(Calendar.getInstance().getTime());
				int updateNum = securityUserMapper.updatePassword(securityUser);
				boolean bRet = (updateNum==1); 
				//更新缓存中的密码
				if(bRet)
				{
					LoginUser loginUser = new LoginUser();
					loginUser.setUserId(securityUser.getUserId());
					loginUser.setPhone(securityUser.getPhone());
					this.securityUserCacheService.removeBasinInfo(securityUser.getUserId());
					this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
				}
				return bRet;
			}
			
		}		
		return false;
	}

	@Override
	public boolean resetPasswordByPhone(String phone, String newpasword, String algorithm) {
		// TODO Auto-generated method stub
		List<SecurityUserPhone> userids = securityUserPhoneMapper.selectUserId(phone);
		
		if(userids!=null&& userids.size()>0)
		{
			return this.resetPassword(userids.get(0).getUserId(), newpasword, algorithm);
		}
		return false;
	}
	@Override
	public boolean resetPassword(long userId, String newpasword, String algorithm) {
		if(!SecurityUserAlgorithm.checkByMd5(this.transferUserKey, newpasword, algorithm))
		{
			return false;
		}
		List<SecurityUser> securityUsers = this.securityUserMapper.selectPasswordByid(userId);
		//如果用户不存在
		if(securityUsers==null||securityUsers.size()==0)
		{
				return false;
		}	
		
		//重置系统密码
		SecurityUser securityUser =  securityUsers.get(0);
		securityUser.setOldPasswordExt(securityUser.getPasswordExt());
		securityUser.setPassword(newpasword);
		securityUser.setPasswordExt(this.getDbUserCrcKey(securityUser));
		securityUser.setUpdateTime(Calendar.getInstance().getTime());
		int updateNum = securityUserMapper.updatePassword(securityUser);
		//更新缓存中的密码
		boolean bRet = (updateNum==1);
		if(bRet)
		{
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.removeBasinInfo(securityUser.getUserId());
			this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
		}
		return bRet;		
	
	}

	@Override
	public int updateUserBasicInfo(SecurityUser securityUser) {
		// TODO Auto-generated method stub
		int bRet =  securityUserMapper.updateSecurityUser(securityUser);		
	    boolean isSuccess = (bRet ==1);
	    if(isSuccess)
	    {
	    	LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
	    	this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
	    }
	    	
	    return bRet;
	}

	/**
	 * 
	 * @param userId
	 * @param email
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@Transactional 
	public int updateNewEmail(long userId, String email, int status) throws Exception{
		// TODO Auto-generated method stub
		//更新用户表中的email信息，
		SecurityUser securityUser = this.selectUserByemail(email);
		if(securityUser==null)
		{
			return 0;
		}
		securityUser.setUserId(userId);
		securityUser.setEmail(email);
		securityUser.setEmailVerified((int)(status&0xff));
		//securityUser.setPasswordext(this.getDbUserCrcKey(securityUser));
		//not user;
		int verifyResult = securityUserMapper.verifyEmail(securityUser);
		//如果更新成功,并且验证状态成功
		if(verifyResult==1&& status == securityUser.verified_Success)
		{
			//查询是否有email和ID的对应关系
			List<SecurityUserEmail> userEmails = securityUserEmailMapper.selectUserId(email);
			//如果有对应关系，回滚
			if(userEmails!=null && userEmails.size()>0)
			{
				SecurityUserEmail  securityUserEmail=userEmails.get(0);
				securityUserEmail.setUserId(userId);
				throw new RuntimeException();
				//verfyResult = securityUserEmailMapper.updateUserEmail(securityUserEmail);
			}
			//否则修改
			else
			{
				SecurityUserEmail  securityUserEmail=new SecurityUserEmail();
				securityUserEmail.setUserId(userId);
				securityUserEmail.setEmail(email);
				securityUserEmailMapper.insertUserEmail(securityUserEmail);
				verifyResult = 1;
			}
		}
		if(verifyResult==1)
		{
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
		    
		}
		return verifyResult;
	}
		
	
	@Override
	public int bindEmail(long userId, String email, int status) {
		int verfyResult=0;
		try {
			verfyResult = updateNewEmail(userId,email,status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(verfyResult==1)
		{
			SecurityUser securityUser = this.selectUserById(userId);
			if(securityUser!=null)
			{
				LoginUser loginUser = new LoginUser();
				loginUser.setUserId(securityUser.getUserId());
				loginUser.setPhone(securityUser.getPhone());
				
				this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
			}
		}
		return verfyResult;
	}

	@Override
	@Transactional 
	public int unbindEmail(long userId, String email) {
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId(userId);
		securityUser.setEmail("");
		securityUser.setEmailVerified(SecurityUser.verified_Fail);
		int verfyResult = securityUserMapper.verifyEmail(securityUser);
		//如果更新成功,并且验证状态成功
		if(verfyResult==1)
		{
			//查询是否有email和ID的对应关系
			securityUserEmailMapper.deleteUserEmail(email);
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return verfyResult;
	
	}
	
	/**
	 * 
	 * @param userId
	 * @param countryCode
	 * @param phone 代国家吗的全号码；
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@Transactional 
	public int updatePhone(long userId , String countryCode, String phone, int status) throws Exception{
		// TODO Auto-generated method stub
		//更新用户表中的email信息，
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId(userId);
		securityUser.setPhone(phone);
		securityUser.setPhoneCode(countryCode);
		securityUser.setPhoneVerified((int)(status&0xff));
		int verifyResult = securityUserMapper.verifyPhone(securityUser);
		//如果更新成功,并且验证状态成功
		if(verifyResult==1&& status == securityUser.verified_Success)
		{
			//查询是否有email和ID的对应关系
			List<SecurityUserPhone> userIds = this.securityUserPhoneMapper.selectUserId(phone);
			//如果有对应关系，更新
			if(userIds!=null && userIds.size()>0)
			{
				
				throw new RuntimeException();
				//verfyResult = securityUserEmailMapper.updateUserEmail(securityUserEmail);
			}
			//否则修改
			else
			{
				SecurityUserPhone  userMaps=new SecurityUserPhone();
				userMaps.setUserId(userId);
				userMaps.setPhone(phone);
				securityUserPhoneMapper.insertUserPhone(userMaps);
				verifyResult = 1;
			}
		}
		if(verifyResult==1)
		{
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return verifyResult;
	}
	
	
	@Override
	public int bindPhone(long userId, String countryCode, String phone, int status) {
		// TODO Auto-generated method stub
		if(!phone.startsWith("00"))
		{
			return SecurityUserConst.RESULT_Error_PhoneError;
		}
		int ret = 0;
		try {
			ret = updatePhone(userId,countryCode,phone,status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ret==1)
		{
			SecurityUser securityUser = this.selectUserById(userId);
			if(securityUser!=null)
			{
				LoginUser loginUser = new LoginUser();
				loginUser.setUserId(securityUser.getUserId());
				loginUser.setPhone(securityUser.getPhone());
				
				this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
			}
		}
		return ret;
	}

	
	@Override
	public int unbindPhone(long userId, String countryCode, String phone) {
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId(userId);
		securityUser.setPhone("");
		securityUser.setPhoneCode("");
		securityUser.setPhoneVerified(SecurityUser.verified_Fail);
		int verifyResult = securityUserMapper.verifyPhone(securityUser);
		//如果更新成功,并且验证状态成功
		if(verifyResult==1)
		{
			//查询是否有email和ID的对应关系
			securityUserPhoneMapper.deleteUserPhone(phone);
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return verifyResult;
	
	}
	
	/**
	 * 
	 * @param userId
	 * @param idType
	 * @param idNo
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@Transactional 
	public int updateIdNo(long userId , int idType, String idNo, int status) throws Exception{
		// TODO Auto-generated method stub
		//更新用户表中的email信息，
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId(userId);
		securityUser.setIdType(idType);
		securityUser.setIdNo(idNo);
		securityUser.setIdVerified((int)(status&0xff));
		int verifyResult = securityUserMapper.verifyIdNo(securityUser);
		//如果更新成功,并且验证状态成功
		if(verifyResult==1&& status == securityUser.verified_Success)
		{
			//查询是否有email和ID的对应关系
			List<SecurityUserIdno> userIds = this.securityUserIdnoMapper.selectUserId(SecurityUserIdno.getIdtotalno(idType, idNo));
			//如果有对应关系，更新
			if(userIds!=null && userIds.size()>0)
			{
				
				throw new RuntimeException();
				//verfyResult = securityUserEmailMapper.updateUserEmail(securityUserEmail);
			}
			//否则修改
			else
			{
				SecurityUserIdno  userMaps=new SecurityUserIdno();
				userMaps.setUserId(userId);
				userMaps.setIdtotalno(idType, idNo);
				securityUserIdnoMapper.insertUserIdNo(userMaps);
				
				verifyResult = 1;
			}
		}
		if(verifyResult==1)
		{
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return verifyResult;
	}
	
	
	@Override
	public int bindIdNo(long userId, int idType, String idNo, int status) {
		// TODO Auto-generated method stub
		int ret  =0;
		try {
			ret = updateIdNo(userId,idType,idNo,status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ret==1)
		{
			SecurityUser securityUser = this.selectUserById(userId);
			if(securityUser!=null)
			{
				LoginUser loginUser = new LoginUser();
				loginUser.setUserId(securityUser.getUserId());
				loginUser.setPhone(securityUser.getPhone());
				
				this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
			}
		}
		return ret;
	}

	@Override
	public int unbindIdNo(long userId, int idType, String idNo) {
		SecurityUser securityUser = new SecurityUser();
		securityUser.setUserId(userId);
		securityUser.setIdType(0x00);
		securityUser.setIdNo("");
		securityUser.setIdVerified(SecurityUser.verified_Fail);
		int verfyResult = securityUserMapper.verifyIdNo(securityUser);
	//如果更新成功,并且验证状态成功
		if(verfyResult==1)
		{
			//查询是否有email和ID的对应关系
			this.securityUserIdnoMapper.deleteUserIdNo(SecurityUserIdno.getIdtotalno(idType, idNo));
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return verfyResult;
	
	}
	@Override
	public int updateStatus(long userId, int status) {
		// TODO Auto-generated method stub
		return this.securityUserMapper.updateStatus(userId, status);
	}
	@Override
	public int registerUserByPhone(SecurityUser securityUser)
	{
		int ret=SecurityUserConst.RESULT_Error_DbError;
		try {
			ret = innerregisterUserByPhone(securityUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = SecurityUserConst.RESULT_Error_DbError;
		}
		if(ret==SecurityUserConst.RESULT_SUCCESS)
		{
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(securityUser.getUserId());
			loginUser.setPhone(securityUser.getPhone());
			this.securityUserCacheService.putLastModifyTime(loginUser,System.currentTimeMillis());
			
		}
		return ret;
	}
	
	@Transactional 
	public int innerregisterUserByPhone(SecurityUser securityUser)
	{
		if(!securityUser.getPhone().startsWith("00"))
		{
			return SecurityUserConst.RESULT_Error_PhoneError;
		}
		if(securityUser.getPhoneVerified()!=SecurityUser.verified_Success)
		{
			return SecurityUserConst.RESULT_Error_PhoneNotVerified;
		}
		List<SecurityUserPhone> userids = this.securityUserPhoneMapper.selectUserId(securityUser.getPhone());
		if(userids!=null && userids.size()>0)
		{
			return SecurityUserConst.RESULT_Error_PhoneExist;
		}
		else
		{
			securityUser.setPasswordExt(this.getDbUserCrcKey(securityUser));
			this.securityUserMapper.insertSecurityUser(securityUser);
			SecurityUserPhone securityUserPhone = new SecurityUserPhone();
			securityUserPhone.setPhone(securityUser.getPhone());
			securityUserPhone.setUserId(securityUser.getUserId());
			this.securityUserPhoneMapper.insertUserPhone(securityUserPhone);
		}
		return SecurityUserConst.RESULT_SUCCESS;
	}
	@Override
	public int updateRoles(long userId,String roles)
	{
		
		int idS = this.securityUserMapper.updateRoles(userId, roles);
		if(idS==1)
		{
			SecurityUser securityUser = this.selectUserById(userId);
			if(securityUser!=null)
			{
				LoginUser loginUser = new LoginUser();
				loginUser.setUserId(securityUser.getUserId());
				loginUser.setPhone(securityUser.getPhone());
				
				this.securityUserCacheService.putLastModifyTime(loginUser, System.currentTimeMillis());
			}
		}
		return idS;
	}

	

}
