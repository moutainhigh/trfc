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
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.service.bean.basicFile.other.OtherBdCustomer;
import com.tianrui.service.bean.system.base.SystemDataDict;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
//import com.tianrui.service.bean.demo.Demo;
import com.tianrui.service.impl.basicFile.other.OtherBdCustomerService;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
//import com.tianrui.service.mapper.DemoMapper;
import com.tianrui.service.mapper.basicFile.other.OtherBdCustomerMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class DemoMapperTest2 {

	public static Logger logger =LoggerFactory.getLogger(DemoMapperTest2.class);
//	@Autowired
////	private  DemoMapper   demoMapper;
//	@Autowired
//	private OtherBdCustomerMapper otherBdCustomerMapper;
//	@Autowired
//	private OtherBdCustomerService otherBdCustomerService;
	@Autowired
	private SystemDataDictMapper systemDataDictMapper;
	@Autowired
	private SystemDataDictItemMapper systemDataDictItemMapper;
	
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	
	@Test
	public void test1(){
		Long Time = supplierManageMapper.findMaxUtc();
		System.out.println(Time);
	}
	
	
//	@Test
//	public void select1() throws Exception{
//		OtherBdCustomerReq oo = new OtherBdCustomerReq();
//		oo.setId("7");
//		oo.setCode("cd0007");
//		oo.setInnercode("0007");
//		oo.setName("test7");
//		oo.setInfo("hello7");
//		oo.setOrgid("00000007");
//		oo.setOrgname("woaijava7");
//		oo.setCreator("test7");
//		oo.setCreatetime(System.currentTimeMillis());
//		Boolean i = otherBdCustomerService.updateCustomer(oo);
//		System.out.println(i);
//	}
//	/*	@Test
//	public void selectPage(){
//		List<OtherBdCustomer> list = otherBdCustomerMapper.page(0, 1);
//		for(OtherBdCustomer o:list)
//		System.out.println(o.getId());
//	}*/
//	@Test
//	public void findCustomerBypage() throws Exception{
//		OtherBdCustomerReq oo = new OtherBdCustomerReq();
//		oo.setStart(0);
//		oo.setLimit(3);
//		oo.setInnercode("0");
//		//		System.out.println(otherBdCustomerMapper.count(oo));
//		PaginationVO<OtherBdCustomerResp> page = otherBdCustomerService.findCustomerByPage(oo);
//		System.out.println(page.getTotal());
//		for(OtherBdCustomerResp r : page.getList()){
//			System.out.println(r.getName());
//		}
//	}
//	@Test
//	public void count2(){
//		//System.out.println(otherBdCustomerMapper.count());
//	}
//
//
//	@Test
//	public void saveTest(){
//		Demo bean = new  Demo();
//		bean.setId(UUIDUtil.getId());
//		bean.setCreatetime(System.currentTimeMillis());
//		bean.setCreator("11");
//
//		demoMapper.insert(bean);
//	}
//
//	@Test
//	public void savequerylist(){
//		Demo bean = new  Demo();
//		bean.setCreator("11");
//		List<Demo>  list=demoMapper.selectByCondition(bean);
//		System.out.println(JSON.toJSONString(list));
//	}
//	
//	@Test
//	public void findCustomerById() throws Exception{
//		OtherBdCustomer o = otherBdCustomerMapper.selectByPrimaryKey("4");
//		OtherBdCustomerReq req = otherBdCustomerService.findByPrimaryKey("4");
//		System.out.println(req.getId());
//	}
//	@Test
//	public void findCustomerByName(){
//		int o = otherBdCustomerMapper.findCustomerByName("七娃");
//		System.out.println(o);
//	}
	@Test
	public void testSystemDataDictMapper(){
//		SystemDataDict dataDict=systemDataDictMapper.selectByPrimaryKey("9c4c2e491d4a4856a42c2c9a0577a8a3");
//		System.out.println(dataDict);
		List<SystemDataDictItem> list=this.systemDataDictItemMapper.selectByDictId("9c4c2e491d4a4856a42c2c9a0577a8a3");
		System.out.println(list);
	}
}