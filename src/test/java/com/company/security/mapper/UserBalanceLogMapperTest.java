/**
 * 
 */
package com.company.security.mapper;

import static org.junit.Assert.*;


import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.security.domain.UserBalanceLog;
import com.company.security.mapper.UserBalanceLogMapper;

/**
 * @author helmsli
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBalanceLogMapperTest {

	@Autowired
    private UserBalanceLogMapper userBalanceLogMapper;
	/**
	 * Test method for {@link com.company.security.mapper.UserBalanceLogMapper#insertUserBalanceLog(com.company.security.domain.UserBalanceLog)}.
	 */
	@Test
	public void testInsertUserBalanceLog() {
		UserBalanceLog userBalanceLog = new UserBalanceLog();
		userBalanceLog.setAmount(123.4567d);
        
		userBalanceLog.setBeginningbalance(0.012d);
		
		Calendar nowCal = Calendar.getInstance();
        nowCal.set(Calendar.MILLISECOND, 0);
        userBalanceLog.setBeginningexpiretimes(nowCal.getTime());
        userBalanceLog.setRemark("remark");
        userBalanceLog.setTransaction("transaction");
        nowCal.add(Calendar.DAY_OF_YEAR, 30);
        userBalanceLog.setTransactionTime(nowCal.getTime());
        userBalanceLog.setUpdatesource("setUpdatesource");
        nowCal.add(Calendar.DAY_OF_YEAR, 30);
        userBalanceLog.setUpdatetime(nowCal.getTime());
        userBalanceLog.setUserId(100000);
        int affectedRowNum =0;
        try {
        	System.out.println(userBalanceLog.toString());
        	//测试删除
        	affectedRowNum = userBalanceLogMapper.deleteUserBalanceLog(userBalanceLog);
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testInsertUserBalanceLog delete error");
		}
        //测试插入
        try {
			userBalanceLogMapper.insertUserBalanceLog(userBalanceLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("testInsertUserBalanceLog insert error");
		}
        //测试重复插入，重复插入应该抛出异常
        try {
			userBalanceLogMapper.insertUserBalanceLog(userBalanceLog);
			fail("testInsertUserBalanceLog double insert error;");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//fail("testInsertUserBalanceLog insert error");
		}
        List<UserBalanceLog> userBalanceLogs = userBalanceLogMapper.selectUserBalanceLog(userBalanceLog);
        if(userBalanceLogs==null || userBalanceLogs.size()==0)
        {
        	fail("testInsertUserBalanceLog select  error,not found insert record");
        	return;
        }
        else
        {
        	UserBalanceLog queryUserBalanceLog=userBalanceLogs.get(0);
        	assertEquals("testInsertUserBalanceLog not equal:",userBalanceLog.toString(),queryUserBalanceLog.toString());
    			
        }
		
	}

	
}
