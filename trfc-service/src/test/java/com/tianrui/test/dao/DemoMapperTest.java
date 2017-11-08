package com.tianrui.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DemoMapperTest {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest.class);
	@Autowired
	PurchaseArriveMapper purchaseArriveMapper;
	
	@Test
	public void test() throws Exception{
		boolean flag = false;
		for (int i = 0; i < 200; i++) {
			PurchaseArrive pa = new PurchaseArrive();
			pa.setId("d21a8c4dde934acebd8caa891152d524");
			pa.setStatus("6");
			pa.setIcardid("14d19edad81a4203a7cdd01dd47ae96d");
			int count = purchaseArriveMapper.updateByPrimaryKeySelective(pa);
			if (count == 1) {
				flag = true;
			} else {
				break;
			}
		}
		if (flag) {
			System.out.println("成功");
		} else {
			System.err.println("失败");
		}
	}
	
}