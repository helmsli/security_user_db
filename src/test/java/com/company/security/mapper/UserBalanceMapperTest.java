/**
 * 
 */
package com.company.security.mapper;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.security.domain.UserBalance;
import com.company.security.mapper.UserBalanceMapper;

/**
 * @author helmsli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBalanceMapperTest {

	@Autowired
    private UserBalanceMapper userBalanceMapper;
	/**
	 * Test method for {@link com.company.security.mapper.UserBalanceMapper#insertUserBalance(com.company.security.domain.UserBalance)}.
     */	 
	@Test
	public void testInsertUserBalance() {
		int i=0;
		
		
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000000L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("insertUserBalance exceptions;");
			return;
		}
		
		List<UserBalance> queryBalances=null;
		try {
			queryBalances = userBalanceMapper.selectUserBalance(userBalance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("insertUserBalance query  result exception");
		}
		if(queryBalances.size()==0)
		{
			fail("insertUserBalance query  result size =0;");
			return;
		}
		else
		{
			UserBalance queryBalance = queryBalances.get(0);
			queryBalance.setAmount(100.1032d);
			queryBalance.setOldBalanceext("oldbalanceext");
			
			assertEquals("testInsertUserBalance result:",userBalance.toString(),queryBalance.toString());
			
		
		}
		
		
		
	}

	
	/**
	 * Test for success update
	 * Test method for {@link com.company.security.mapper.UserBalanceMapper#updateUserBalance(com.company.security.domain.UserBalance)}.
	 */
	@Test
	public void testUpdateUserBalanceSuccess() {
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000001L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance exceptions;");
			return;
		}
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalance.getUserId());
		newUserBalance.setBalance(userBalance.getBalance());
		newUserBalance.setBalanceext(userBalance.getBalanceext()+"*");
		
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		
		newUserBalance.setExpiredata(nowDate.getTime());
		newUserBalance.setAmount(userBalance.getBalance()/2);
		newUserBalance.setOldBalanceext(userBalance.getBalanceext());
		newUserBalance.setTransaction(userBalance.getTransaction()+"*");
		newUserBalance.setUpdatesource(userBalance.getUpdatesource()+"*");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		newUserBalance.setUpdatetime(nowDate.getTime());
		int updateAmount = userBalanceMapper.updateUserBalance(newUserBalance);
		//更新记录为1
		assertEquals(1,updateAmount);
		
		List<UserBalance> queryBalances=null;
		try {
			queryBalances = userBalanceMapper.selectUserBalance(userBalance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance query  result exception");
		}
		if(queryBalances.size()==0)
		{
			fail("testUpdateUserBalance query  result size =0;");
			return;
		}
		else
		{
			UserBalance queryBalance=queryBalances.get(0);
			assertEquals("testUpdateUserBalance result :userid",newUserBalance.getUserId(),queryBalance.getUserId());
			
			assertEquals("testUpdateUserBalance result :balance",newUserBalance.getBalance(),(queryBalance.getBalance() + newUserBalance.getAmount()),0);
			
			assertEquals("testUpdateUserBalance result :balanceext",newUserBalance.getBalanceext(),queryBalance.getBalanceext());
			
			assertEquals("testUpdateUserBalance result :expiredate",newUserBalance.getExpiredata().toString(),queryBalance.getExpiredata().toString());
			assertEquals("testUpdateUserBalance result :transaction",newUserBalance.getTransaction(),queryBalance.getTransaction());
			assertEquals("testUpdateUserBalance result :updatetime",newUserBalance.getUpdatetime().toString(),queryBalance.getUpdatetime().toString());
			assertEquals("testUpdateUserBalance result :updatedatesource",newUserBalance.getUpdatesource(),queryBalance.getUpdatesource());
		}
		
	}
	
	/**
	 * Test for balance reduce to zero
	 * Test method for {@link com.company.security.mapper.UserBalanceMapper#updateUserBalance(com.company.security.domain.UserBalance)}.
	 */
	@Test
	public void testUpdateUserBalanceToZero() {
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000001L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance exceptions;");
			return;
		}
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalance.getUserId());
		newUserBalance.setBalance(userBalance.getBalance());
		newUserBalance.setBalanceext(userBalance.getBalanceext()+"*");
		
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		
		newUserBalance.setExpiredata(nowDate.getTime());
		newUserBalance.setAmount(userBalance.getBalance());
		newUserBalance.setOldBalanceext(userBalance.getBalanceext());
		newUserBalance.setTransaction(userBalance.getTransaction()+"*");
		newUserBalance.setUpdatesource(userBalance.getUpdatesource()+"*");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		newUserBalance.setUpdatetime(nowDate.getTime());
		int updateAmount = userBalanceMapper.updateUserBalance(newUserBalance);
		//更新记录为1
		assertEquals(1,updateAmount);
		
		List<UserBalance> queryBalances=null;
		try {
			queryBalances = userBalanceMapper.selectUserBalance(userBalance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance query  result exception");
		}
		if(queryBalances.size()==0)
		{
			fail("testUpdateUserBalance query  result size =0;");
			return;
		}
		else
		{
			UserBalance queryBalance=queryBalances.get(0);
			assertEquals("testUpdateUserBalance result :userid",newUserBalance.getUserId(),queryBalance.getUserId());
			
			assertEquals("testUpdateUserBalance result :balance",newUserBalance.getBalance(),(queryBalance.getBalance() + newUserBalance.getAmount()),0);
			assertEquals("testUpdateUserBalance result :balanceext",newUserBalance.getBalanceext(),queryBalance.getBalanceext());
			assertEquals("testUpdateUserBalance result :expiredate",newUserBalance.getExpiredata().toString(),queryBalance.getExpiredata().toString());
			assertEquals("testUpdateUserBalance result :transaction",newUserBalance.getTransaction(),queryBalance.getTransaction());
			assertEquals("testUpdateUserBalance result :updatetime",newUserBalance.getUpdatetime().toString(),queryBalance.getUpdatetime().toString());
			assertEquals("testUpdateUserBalance result :updatedatesource",newUserBalance.getUpdatesource(),queryBalance.getUpdatesource());
			assertEquals("testUpdateUserBalance result :balance is not zero",0d,queryBalance.getBalance(),0d);
		
		}
		
	}
	/**
	 * 余额不相等的更新失败的测试用例
	 */
	@Test
	public void testUpdateUserBalanceNotEqual() {
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000001L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance exceptions;");
			return;
		}
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalance.getUserId());
		newUserBalance.setBalance(userBalance.getBalance()/2);
		newUserBalance.setBalanceext(userBalance.getBalanceext()+"*");
		
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		
		newUserBalance.setExpiredata(nowDate.getTime());
		newUserBalance.setAmount(userBalance.getBalance()/2);
		newUserBalance.setOldBalanceext(userBalance.getBalanceext());
		newUserBalance.setTransaction(userBalance.getTransaction()+"*");
		newUserBalance.setUpdatesource(userBalance.getUpdatesource()+"*");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		newUserBalance.setUpdatetime(nowDate.getTime());
		int updateAmount = userBalanceMapper.updateUserBalance(newUserBalance);
		//更新记录为1
		assertEquals("testUpdateUser BalanceNot Equal result :balance",0,updateAmount);
			
	}

	/**
	 * 校验和不相等的更新失败的测试用例
	 */
	@Test
	public void testUpdateUserBalanceCheckNotEqual() {
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000001L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance exceptions;");
			return;
		}
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalance.getUserId());
		newUserBalance.setBalance(userBalance.getBalance()/2);
		newUserBalance.setBalanceext(userBalance.getBalanceext()+"*");
		
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		
		newUserBalance.setExpiredata(nowDate.getTime());
		newUserBalance.setAmount(userBalance.getBalance());
		newUserBalance.setOldBalanceext(userBalance.getBalanceext()+"*");
		newUserBalance.setTransaction(userBalance.getTransaction()+"*");
		newUserBalance.setUpdatesource(userBalance.getUpdatesource()+"*");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		newUserBalance.setUpdatetime(nowDate.getTime());
		int updateAmount = userBalanceMapper.updateUserBalance(newUserBalance);
		//更新记录为1
		assertEquals("testUpdateUser BalanceNot Equal result :balance",0,updateAmount);
			
	}
	/**
	 * 余额不够的更新失败的测试用例
	 */
	@Test
	public void testUpdateUserBalanceNotEnough() {
		Calendar nowDate = Calendar.getInstance();
		UserBalance userBalance = new UserBalance();
		userBalance.setUserId(1000001L);
		userBalance.setBalance(0.1234d);
		userBalance.setBalanceext("balanceext");
		userBalance.setExpiredata(nowDate.getTime());
		
		userBalance.setAmount(100.1032d);
		userBalance.setOldBalanceext("oldbalanceext");
		
		userBalance.setTelphonenum("01234567890");
		userBalance.setTransaction("trans:123456789");
		userBalance.setUpdatesource("updatesource");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		userBalance.setUpdatetime(nowDate.getTime());
		try {
			System.out.println(userBalance.toString());
			userBalanceMapper.deleteUserBalance(userBalance);
			userBalanceMapper.insertUserBalance(userBalance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testUpdateUserBalance exceptions;");
			return;
		}
		UserBalance newUserBalance = new UserBalance();
		newUserBalance.setUserId(userBalance.getUserId());
		newUserBalance.setBalance(userBalance.getBalance()+0.0001);
		newUserBalance.setBalanceext(userBalance.getBalanceext()+"*");
		
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		
		newUserBalance.setExpiredata(nowDate.getTime());
		newUserBalance.setAmount(userBalance.getBalance());
		newUserBalance.setOldBalanceext(userBalance.getBalanceext());
		newUserBalance.setTransaction(userBalance.getTransaction()+"*");
		newUserBalance.setUpdatesource(userBalance.getUpdatesource()+"*");
		nowDate.add(Calendar.DAY_OF_YEAR, 30);
		newUserBalance.setUpdatetime(nowDate.getTime());
		int updateAmount = userBalanceMapper.updateUserBalance(newUserBalance);
		//更新记录为1
		assertEquals("testUpdateUser BalanceNot Equal result :balance",0,updateAmount);
			
	}
}
