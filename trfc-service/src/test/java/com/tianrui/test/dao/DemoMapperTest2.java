package com.tianrui.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.quality.file.IQualitySchemeService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.other.OtherBdSupplierReq;
import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.api.req.system.base.SystemCodeReq;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.quality.file.QualityColumn;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.other.OtherBdSupplierMapper;
import com.tianrui.service.mapper.quality.file.QualityColumnMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictMapper;

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
	@Autowired
	private OtherBdSupplierMapper otherBdSupplierMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@Autowired
	private IQualitySchemeService qualitySchemeService;
	@Autowired
	private QualitySchemeMapper qualitySchemeMapper;
	@Autowired
	private QualityColumnMapper qualityColumnMapper;
	@Autowired
	private SupplierManageMapper supplier;
	@Autowired
	CustomerManageMapper customerManageMapper;
	@Test
	public void test(){
		
			List<QualityColumn> list= qualityColumnMapper.autoCompleteSearch("1");
			System.out.println(list);
		
		
		
	}
	
	@Test
	public void test123() throws Exception{
		QualitySchemeReq req = new QualitySchemeReq();
		req.setId("000");
		QualityScheme qs= qualitySchemeMapper.selectByPrimaryKey(req.getId());
		System.out.println(qs.getName());
	}
	
	
	
	
	@Test
	public void test12() throws Exception{
		SystemCodeReq req = new SystemCodeReq();
		req.setId("00000");
		req.setCodeType((byte)1);
		req.setItemType(false);
		req.setUserName("sd");
		//Result rs = systemCodeService.updateCodeItem(req);
		
		//System.out.println(rs.getCode());
		
	}
	
	
	@Test
	public void test2(){
		OtherBdSupplierReq req = new OtherBdSupplierReq();
		req.setInnercode("2");
		int a = otherBdSupplierMapper.count(req);
//		List<OtherBdSupplier> list = otherBdSupplierMapper.page(req);
		System.out.println(a);
//		for(OtherBdSupplier o : list){
//			System.out.println(o.getId());
//		}
		
	}
	
	
	
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
	@Test
	public void test3(){
		List<CustomerManage>  list=customerManageMapper.selectSelective(null);
		System.out.println(list.size());
	}
}