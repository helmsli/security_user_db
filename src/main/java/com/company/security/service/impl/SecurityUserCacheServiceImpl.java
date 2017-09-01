package com.company.security.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.company.security.domain.LoginUser;
import com.company.security.service.SecurityUserCacheService;
@Service("securityUserCacheService")
public class SecurityUserCacheServiceImpl extends SecurityUserCacheKeyService implements SecurityUserCacheService {
	
	@Resource (name = "redisTemplate")
	protected RedisTemplate<Object, Object> redisTemplate;
	
	@Resource(name="redisLockService")
	private RedisLockServiceImpl redisLockService;
	@Override
	public boolean putBasicInfo(LoginUser loginUser) {
		String lockKey = this.getLockkey(loginUser.getUserId());
		String transTime = String.valueOf(System.currentTimeMillis());
		boolean isLock = getLock(lockKey, transTime);
		
		try {
			// TODO Auto-generated method stub
			if(isLock)
			{
				this.cleanAllUserCache(loginUser.getUserId());
				ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
				String userKey = this.getLoginUserkey(loginUser.getUserId());
				opsForValue.set(userKey, loginUser);
				String emailkey = this.getEmailKey(loginUser.getEmail());
				opsForValue.set(emailkey, new Long(loginUser.getUserId()));
				String phoneKey = this.getPhoneKey(loginUser.getPhone());
				opsForValue.set(phoneKey, new Long(loginUser.getUserId()));
					
			}
		} finally {
			if (isLock) {
				try {
					this.redisLockService.releaseUserTransLock(lockKey, transTime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public LoginUser getBasicInfo(long userId) {
		// TODO Auto-generated method stub
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		
		String userKey = this.getLoginUserkey(userId);
		LoginUser loginUser = (LoginUser)opsForValue.get(userKey);
		return loginUser;
		
	}

	@Override
	public LoginUser getBInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		String phoneKey = this.getPhoneKey(phone);
		Long  userid = (Long)opsForValue.get(phoneKey);
		return getBasicInfo(userid.longValue());
	}

	@Override
	public LoginUser getBInfoByEmail(String email) {
		// TODO Auto-generated method stub
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		String emailkey = this.getEmailKey(email);
		Long  userid = (Long)opsForValue.get(emailkey);
		return getBasicInfo(userid.longValue());
	}

	@Override
	public boolean removeBasinInfo(long userId) {
		// TODO Auto-generated method stub
		String key = this.getLoginUserkey(userId);
		redisTemplate.delete(key);
		return true;
	}
	@Override
	public long getLastModifyTime(long userId) {
		// TODO Auto-generated method stub
		String lockKey = this.getLockkey(userId);
		ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
		String lastModifyTime = (String)opsForValue.get(lockKey);
		return Long.parseLong(lastModifyTime);
	}
	/**
	 * 清除所有缓存
	 * @param userId
	 */
	protected void cleanAllUserCache(long userId)
	{
		String key = this.getLoginUserkey(userId);
		LoginUser loginUser = getBasicInfo(userId);
		if(loginUser!=null)
		{
			String childKey = this.getEmailKey(loginUser.getEmail());
			redisTemplate.delete(childKey);
			
			childKey = this.getPhoneKey(loginUser.getPhone());
			redisTemplate.delete(childKey);
		}
		redisTemplate.delete(key);
	}
	@Override
	public boolean putLastModifyTime(long userId, long lastModifyTime) {
		// TODO Auto-generated method stub
		String lockKey = this.getLockkey(userId);
		String transTime = String.valueOf(System.currentTimeMillis());
		boolean isLock = getLock(lockKey,transTime);
		try {
			if (isLock) {
               String modifyKey = this.getLastModifyKey(userId);
               cleanAllUserCache(userId);
               ValueOperations<Object, Object> opsForValue = redisTemplate.opsForValue();
               opsForValue.set(modifyKey, String.valueOf(lastModifyTime));               
			} 
		} finally {
			if (isLock) {
				try {
					this.redisLockService.releaseUserTransLock(lockKey, transTime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param key
	 * @param accesskey
	 * @return
	 */
	protected boolean getLock(String key,String accesskey)
	{
		return redisLockService.getUserTransLock(key, accesskey, 10000, 1000, 100000);				 
	}

}
