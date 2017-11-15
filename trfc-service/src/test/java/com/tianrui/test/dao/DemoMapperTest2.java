package com.tianrui.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DemoMapperTest2 {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest2.class);
	@Autowired
	SalesArriveMapper salesArriveMapper;
	
	@Test
	public void hasPurchaseArriveTest() throws Exception{

		salesArriveMapper.hasPurchaseArrive("豫D55555");
	}
	
}