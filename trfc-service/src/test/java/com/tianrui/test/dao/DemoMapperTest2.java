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
import com.tianrui.service.bean.demo.Demo;
import com.tianrui.service.mapper.DemoMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class DemoMapperTest2 {
	
	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest2.class);
	@Autowired
	private  DemoMapper   demoMapper;
	
	@Test
	public void saveTest(){
		Demo bean = new  Demo();
		bean.setId(UUIDUtil.getId());
		bean.setCreatetime(System.currentTimeMillis());
		bean.setCreator("11");

		demoMapper.insert(bean);
	}
	
	@Test
	public void savequerylist(){
		Demo bean = new  Demo();
		bean.setCreator("11");
		List<Demo>  list=demoMapper.selectByCondition(bean);
		System.out.println(JSON.toJSONString(list));
	}
	
}
