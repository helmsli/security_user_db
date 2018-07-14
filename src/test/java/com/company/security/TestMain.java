package com.company.security;

import org.springframework.web.client.RestTemplate;

import com.company.security.domain.SecurityUser;
import com.xinwei.nnl.common.domain.ProcessResult;
import com.xinwei.nnl.common.util.JsonUtil;

public class TestMain {
	private RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMain testMain = new TestMain();
		testMain.modifyUser();
	}
	
	public void modifyUser()
	{
		//userid:110000000
		SecurityUser securityUser= new SecurityUser();
		securityUser.setUserId(110000000L);
		securityUser.setEmail("11113@qq.com");
		securityUser.setPhone("008618500788600");
		securityUser.setBusinessAddress("busizedd");
		securityUser.setIdType(0);
		securityUser.setIdNo("1211");
		ProcessResult process = restTemplate.postForObject("http://www.chunzeacademy.com:8080/user/0086/modifyUserInfo", securityUser, ProcessResult.class);
		System.out.print(process.toString());
	}

}
