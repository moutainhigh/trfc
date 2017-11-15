package com.tianrui.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DemoMapperTest {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest.class);
	@Autowired
	SalesApplicationMapper salesApplicationMapper;
	@Autowired
	SalesArriveMapper salesArriveMapper;
	
	@Test
	public void test() throws Exception{
		HomePageParam param = new HomePageParam();
		param.setUserId("1002P11000000000RJ51");
		param.setSalesOrg("0001PP1000000000BSIU");
		List<String> list = salesArriveMapper.appHomeVehicle(param);
		System.err.println(JSON.toJSONString(list));
	}
	
}