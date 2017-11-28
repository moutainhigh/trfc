package com.tianrui.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.mongo.CodeGenDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class ReidsTest {

	@Autowired
	CacheClient cacheClient; 
	@Test
	public void testCode(){
		cacheClient.saveString("key2","value1",0);
		System.out.println(cacheClient.getString("key2"));
	}
}