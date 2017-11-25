package com.tianrui.test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.bean.businessManage.report.ReportPurchase;
import com.tianrui.service.mapper.businessManage.report.ReportPurchaseMapper;
import com.tianrui.smartfactory.common.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class ReportPuchaseMapperTest {
	public static Logger logger =LoggerFactory.getLogger(ReportPuchaseMapperTest.class);
	@Resource
	private ReportPurchaseMapper reportPurchaseMapper;
	
	
	@Test
	public void testCount(){
		ReportPurchase query =new ReportPurchase();
		query.setReturnstatus("2");
		query.setBeginTimeLong(DateUtil.parse("2017-12-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		query.setMinemouthNameLike("建");
		Long count =reportPurchaseMapper.countByCondition(query);
		System.out.println(count);
	}
}
