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
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DemoMapperTest {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest.class);
	@Autowired
	PurchaseArriveMapper purchaseArriveMapper;
	
	@Test
	public void test() throws Exception{
		NoticeListParam param = new NoticeListParam();
		param.setType("0");
		param.setNcId("1002P110000000ATUUTJ");
		param.setStart((param.getPageNo() - 1) * param.getPageSize());
		param.setLimit(param.getPageSize());
		List<NoticeListVo> list = purchaseArriveMapper.appNoticeList(param);
		System.out.println(JSON.toJSONString(list));
	}
	
}