package com.tianrui.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.basicFile.finance.ArGatherbillQuery;
import com.tianrui.service.bean.basicFile.finance.ArGatherbill;
import com.tianrui.service.mapper.basicFile.finance.ArGatherbillMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class ArGatherbillMapperTest {
	public static Logger logger =LoggerFactory.getLogger(ArGatherbillMapperTest.class);
	@Resource
	private ArGatherbillMapper arGatherbillMapper;
	
	
	/*@Test
	public void test1(){
		ArGatherbillQuery ar = new ArGatherbillQuery();
		ar.setCustomerName("河南省扬升建设工程有限公司");
		ar.setStart((ar.getPageNo()-1)*ar.getPageSize());
		ar.setLimit(ar.getPageSize());
		List<ArGatherbill> findpageArGatherbill = arGatherbillMapper.findpageArGatherbill(ar);
		System.out.println(JSON.toJSONString(findpageArGatherbill));
	}*/
	@Test
	public void test2(){
		ArGatherbillQuery ar = new ArGatherbillQuery();
		ar.setCustomerName("河南省扬升建设工程有限公司");
		ar.setStart((ar.getPageNo()-1)*ar.getPageSize());
		ar.setLimit(ar.getPageSize());
		Long findpageArGatherbill = arGatherbillMapper.findpagecountArGatherbill(ar);
		System.out.println(JSON.toJSONString(findpageArGatherbill));
		
		
	}
}
