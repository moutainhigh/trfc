package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.basicFile.finance.ICustomerRemainderService;
import com.tianrui.smartfactory.common.vo.Result;


@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CustomerRemaServiceTest {
	public static Logger logger =LoggerFactory.getLogger(CustomerRemaServiceTest.class);
	@Autowired
	private ICustomerRemainderService icustomerRemainderService;
		
	@Test
	public void test() throws Exception{
		//Result rs = icustomerRemainderService.findMaxUtc(baseReq);
		//System.out.println(rs.getData());
	}
	
	
}
