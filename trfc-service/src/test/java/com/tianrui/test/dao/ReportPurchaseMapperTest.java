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
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.service.bean.businessManage.report.ReportPurchase;
import com.tianrui.service.mapper.businessManage.report.ReportPurchaseMapper;
import com.tianrui.smartfactory.common.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class ReportPurchaseMapperTest {
	public static Logger logger =LoggerFactory.getLogger(ReportPurchaseMapperTest.class);
	@Resource
	private ReportPurchaseMapper reportPurchaseMapper;
/*	@Test
	public void test(){
		ReportPurchase re = new ReportPurchase();
		re.setDrivername("马献国");
		List<ReportPurchase> selectByConditionForMater = reportPurchaseMapper.selectByConditionForMater(re);
		System.out.println(JSON.toJSONString(selectByConditionForMater));
		
	}*/
	

	/*@Test
	public void testCount1(){
		ReportPurchase query =new ReportPurchase();
		query.setReturnstatus("2");
		query.setBeginTimeLong(DateUtil.parse("2017-12-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		query.setMinemouthNameLike("建");
		Long count =reportPurchaseMapper.countByConditionForMater(query);
		System.out.println(count);
	}*/

	/*@Test
	public void testCount2(){
		ReportPurchase query =new ReportPurchase();
		query.setReturnstatus("2");
		query.setBeginTimeLong(DateUtil.parse("2017-12-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		query.setMinemouthNameLike("建");
		Long count =reportPurchaseMapper.countByConditionForMatercg(query);
		System.out.println(count);
	}*/

	/*@Test
	public void testCount1(){
		ReportPurchase query =new ReportPurchase();
		query.setReturnstatus("2");
		query.setBeginTimeLong(DateUtil.parse("2017-12-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		query.setMinemouthNameLike("建");
		Long count =reportPurchaseMapper.countByConditionForMaterSignPersonName(query);
		System.out.println(count);
	}*/
	@Test
	public void testSelect(){
		ReportPurchase query =new ReportPurchase();
		query.setReturnstatus("2");
	//	query.setBeginTimeLong(DateUtil.parse("2017-12-12 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		query.setMinemouthNameLike("建");
		 List<ReportPurchase> selectByConditionForMaterSignPersonName = reportPurchaseMapper.selectByConditionForMaterSignPersonName(query);
		System.out.println(selectByConditionForMaterSignPersonName);
	}
}
