package com.company.security.service.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.security.Const.SecurityUserConst;
import com.company.security.domain.UserBalance;
import com.company.security.domain.UserBalanceApply;
import com.company.security.domain.UserBalanceApplyResult;
import com.company.security.mapper.UserBalanceMapper;
import com.company.security.service.ServiceUserBlance;
import com.company.security.service.impl.ServiceUserBalanceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceUserBalanceImplTest extends ServiceUserBalanceImpl {

	@Autowired 
	ServiceUserBlance serviceUserBalanceImpl;
	@Autowired
    private UserBalanceMapper userBalanceMapper;
	
	protected UserBalanceApply createUserBalanceApply()
	{
		Calendar nowDate = Calendar.getInstance();		
		UserBalanceApply userBalanceApply = new UserBalanceApply();
		userBalanceApply.setAmount(1.0001);
		userBalanceApply.setBizType("bizType");
		userBalanceApply.setExpireDays(0);
		userBalanceApply.setOperType("operType");
		userBalanceApply.setOrderId("orderId");
		userBalanceApply.setRemark("remark");
		userBalanceApply.setTelphonenum("telphone");
		userBalanceApply.setTransaction("transaction134");
		userBalanceApply.setTransactionTime(nowDate.getTime());
		userBalanceApply.setUpdatesource("updateSrc");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalanceApply.setUpdatetime(nowDate.getTime());
		userBalanceApply.setUserId(30000);
		return userBalanceApply;
	}
	
	@Test
	public void testUpdateUserBalance() {
		//fail("Not yet implemented");
		//1.插入测试用例
		//1.1创建初始化对象
		UserBalanceApply userBalanceApply = createUserBalanceApply();
		ServiceUserBalanceImpl serviceBalanceInstance = (ServiceUserBalanceImpl)serviceUserBalanceImpl;
		Calendar nowDate = Calendar.getInstance();
		UserBalance initUserBalance = new UserBalance();
		//删除初始化的账号余额
		initUserBalance.setUserId(userBalanceApply.getUserId());
		userBalanceMapper.deleteUserBalance(initUserBalance);
		
		//userBalance.setUserId(userBalanceApply.getUserId());
		initUserBalance.setBalance(0d);
		initUserBalance.setUpdatetime(nowDate.getTime());
		initUserBalance.setTransaction("00200009121212129999999");
		
		initUserBalance.setExpiredata(nowDate.getTime());	
		//获取初始化的用户余额
		initUserBalance = serviceBalanceInstance.initUserBalance(initUserBalance,userBalanceApply);
	
		//发送充值请求
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(-1);		
		UserBalanceApplyResult userBalanceApplyResult = serviceUserBalanceImpl.updateUserBalance(userBalance, userBalanceApply);
		assertEquals("testUpdateUserBalance result:",SecurityUserConst.RESULT_SUCCESS_init,userBalanceApplyResult.getResult());
		assertEquals("testInsertUserBalance result balance:",initUserBalance.getBalance(),userBalanceApplyResult.getBalance(),0);
		assertEquals("testUpdateUserBalance result balanceCrc",initUserBalance.getBalanceext(),userBalanceApplyResult.getBalanceext());
		assertEquals("testUpdateUserBalance result expireDate",initUserBalance.getExpiredata(),userBalanceApplyResult.getExpiredata());
		assertEquals("testUpdateUserBalance result transaction",initUserBalance.getTransaction(),userBalanceApplyResult.getTransaction());
		assertEquals("testUpdateUserBalance result userid",initUserBalance.getUserId(),userBalanceApplyResult.getUserId());
		
		//从数据库中获取余额信息，进行比较
		userBalance.setUserId(userBalanceApply.getUserId());
		List<UserBalance> userBalances = userBalanceMapper.selectUserBalance(userBalance);
		if(userBalances==null || userBalances.size()==0)
		{
			fail("testUpdateUserBalance result db result error;");
		}
		userBalance=userBalances.get(0);
		assertEquals("testInsertUserBalance dbresult balance:",initUserBalance.getBalance(),userBalance.getBalance(),0);
		assertEquals("testUpdateUserBalance dbresult balanceCrc",initUserBalance.getBalanceext(),userBalance.getBalanceext());
		assertEquals("testUpdateUserBalance dbresult expireDate",initUserBalance.getExpiredata(),userBalance.getExpiredata());
		assertEquals("testUpdateUserBalance dbresult transaction",initUserBalance.getTransaction(),userBalance.getTransaction());
		assertEquals("testUpdateUserBalance dbresult userid",initUserBalance.getUserId(),userBalance.getUserId());
		
		
		//end 1.插入测试用例
		//2.测试用例，测试更新余额
		double amount = -100.1234d;
		userBalanceApply.setAmount(amount);
		
		userBalanceApplyResult = serviceUserBalanceImpl.updateUserBalance(initUserBalance, userBalanceApply);
		
		//从数据库中获取余额信息，进行比较
		userBalance.setUserId(userBalanceApply.getUserId());
		userBalances = userBalanceMapper.selectUserBalance(userBalance);
		if(userBalances==null || userBalances.size()==0)
		{
			fail("testUpdateUserBalance 更新数据库余额 查询结果错误;");
		}
		userBalance=userBalances.get(0);
		assertEquals("testUpdateUserBalance 增加余额 error",SecurityUserConst.RESULT_SUCCESS,userBalanceApplyResult.getError());
		
		assertEquals("testUpdateUserBalance 增加余额 result",SecurityUserConst.RESULT_SUCCESS,userBalanceApplyResult.getResult());
		
		assertEquals("testInsertUserBalance 增加余额db  balance:",initUserBalance.getBalance() - userBalanceApply.getAmount()  ,userBalance.getBalance(),0);
		assertEquals("testUpdateUserBalance 增加余额db balanceCrc",userBalanceApplyResult.getBalanceext(),userBalance.getBalanceext());
		assertEquals("testUpdateUserBalance 增加余额db expireDate",userBalanceApplyResult.getExpiredata(),userBalance.getExpiredata());
		assertEquals("testUpdateUserBalance 增加余额db transaction",userBalanceApplyResult.getTransaction(),userBalance.getTransaction());
		assertEquals("testUpdateUserBalance 增加余额db userid",userBalanceApplyResult.getUserId(),userBalance.getUserId());
		
		assertEquals("testInsertUserBalance 增加余额返回值  balance:",0-amount,userBalance.getBalance(),0);
		//end of 测试用例
		
		//测试校验码不正确，不能更新,initUserBalance--数据库初始余额， userbalance 操作后的数据库余额
		//查询初始化的余额
		initUserBalance.setUserId(userBalanceApply.getUserId());
		userBalances = userBalanceMapper.selectUserBalance(initUserBalance);
		if(userBalances==null || userBalances.size()==0)
		{
			fail("testUpdateUserBalance 更新数据库余额 查询结果错误;");
		}
		initUserBalance=userBalances.get(0);
		//设置校验和为错误的数值
		String initBalanceCrc = initUserBalance.getBalanceext();
		initUserBalance.setBalanceext("000000000000000");
		
		amount = -100.1234d;
		userBalanceApply.setAmount(amount);
		
		userBalanceApplyResult = serviceUserBalanceImpl.updateUserBalance(initUserBalance, userBalanceApply);
		
		//从数据库中获取余额信息，进行比较
		userBalance.setUserId(userBalanceApply.getUserId());
		userBalances = userBalanceMapper.selectUserBalance(userBalance);
		if(userBalances==null || userBalances.size()==0)
		{
			fail("testUpdateUserBalance 更新数据库余额 查询结果错误;");
		}
		userBalance=userBalances.get(0);
		
		assertEquals("testUpdateUserBalance 增加余额 error",SecurityUserConst.ERROR_CHECKSUM_NOTEQUAL,userBalanceApplyResult.getError());
		
		assertEquals("testUpdateUserBalance 增加余额 result",SecurityUserConst.RESULT_FAILURE,userBalanceApplyResult.getResult());
		
		assertEquals("testInsertUserBalance 增加余额db  balance:",initUserBalance.getBalance() ,userBalance.getBalance(),0);
		assertEquals("testUpdateUserBalance 增加余额db balanceCrc",initBalanceCrc,userBalance.getBalanceext());
		assertEquals("testUpdateUserBalance 增加余额db expireDate",initUserBalance.getExpiredata(),userBalance.getExpiredata());
		assertEquals("testUpdateUserBalance 增加余额db transaction",initUserBalance.getTransaction(),userBalance.getTransaction());
		assertEquals("testUpdateUserBalance 增加余额db userid",initUserBalance.getUserId(),userBalance.getUserId());
		
		//end of crc 不合法
		
		//测试 transaction 不正确，不能更新,initUserBalance--数据库初始余额， userbalance 操作后的数据库余额
				//查询初始化的余额
				initUserBalance.setUserId(userBalanceApply.getUserId());
				userBalances = userBalanceMapper.selectUserBalance(initUserBalance);
				if(userBalances==null || userBalances.size()==0)
				{
					fail("testUpdateUserBalance 更新数据库余额 查询结果错误;");
				}
				initUserBalance=userBalances.get(0);
				//设置校验和为错误的数值
				 initBalanceCrc = initUserBalance.getTransaction();
				initUserBalance.setTransaction("000000000000000");
				
				amount = -100.1234d;
				userBalanceApply.setAmount(amount);
				
				userBalanceApplyResult = serviceUserBalanceImpl.updateUserBalance(initUserBalance, userBalanceApply);
				
				//从数据库中获取余额信息，进行比较
				userBalance.setUserId(userBalanceApply.getUserId());
				userBalances = userBalanceMapper.selectUserBalance(userBalance);
				if(userBalances==null || userBalances.size()==0)
				{
					fail("testUpdateUserBalance 更新数据库余额 查询结果错误;");
				}
				userBalance=userBalances.get(0);
				
				assertEquals("testUpdateUserBalance 增加余额 error",SecurityUserConst.ERROR_TRANSACTION_NOTEQUAL,userBalanceApplyResult.getError());
				
				assertEquals("testUpdateUserBalance 增加余额 result",SecurityUserConst.RESULT_FAILURE,userBalanceApplyResult.getResult());
				
				assertEquals("testInsertUserBalance 增加余额db  balance:",initUserBalance.getBalance() ,userBalance.getBalance(),0);
				assertEquals("testUpdateUserBalance 增加余额db balanceCrc",initUserBalance.getBalanceext(),userBalance.getBalanceext());
				assertEquals("testUpdateUserBalance 增加余额db expireDate",initUserBalance.getExpiredata(),userBalance.getExpiredata());
				assertEquals("testUpdateUserBalance 增加余额db transaction",initBalanceCrc,userBalance.getTransaction());
				assertEquals("testUpdateUserBalance 增加余额db userid",initUserBalance.getUserId(),userBalance.getUserId());
				//end of transaction error
		
		
		
				
	}

}
