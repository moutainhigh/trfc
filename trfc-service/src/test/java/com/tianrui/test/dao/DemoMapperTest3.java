package com.tianrui.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.mapper.businessManage.financeManage.CustomerRemainderMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DemoMapperTest3 {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest3.class);
	@Autowired
	CustomerRemainderMapper customerRemainderMapper;
	
	@Test
	public void test() throws Exception{
		
		
	}
	
}