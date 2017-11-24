package com.tianrui.test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.basicFile.finance.IArGatherbillService;
import com.tianrui.api.req.basicFile.finance.ArGatherbillQuery;
import com.tianrui.smartfactory.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/appliactionContext-service.xml"})
public class ArGatherbillServiceTest {
	public static Logger logger =LoggerFactory.getLogger(ArGatherbillServiceTest.class);
	@Resource
	private IArGatherbillService arGatherbillService;
	@Test
	public void testpage() throws Exception{
		ArGatherbillQuery ar = new ArGatherbillQuery();
	//	ar.setCustomerName("河南省扬升建设工程有限公司");
		ar.setPageNo(1);
		ar.setPageSize(10);
	//	ar.setStart((ar.getPageNo()-1)*ar.getPageSize());
	//	ar.setLimit(ar.getPageSize());
		Result page = arGatherbillService.page(ar);
		System.out.println(page.getData());
		
	}
}