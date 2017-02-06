package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.system.base.getCodeReq;
import com.tianrui.service.bean.system.base.SystemCode;
import com.tianrui.service.mapper.system.base.SystemCodeMapper;
import com.tianrui.smartfactory.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DataDictServiceTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SystemCodeMapper systemCodeMapper;
	
	
	@Test
	public void testCode() throws Exception{
//		SystemCodeReq req = new SystemCodeReq();
//		req.setId("0001");
//		req.setCode("HH");
//		req.setCodeType((byte)0);
//		req.setCodeBegin((byte)0);
//		req.setInnerCodeBegin((byte)0);
//		req.setItemType(false);
		
//		SystemCode sc = systemCodeMapper.selectByCode("cs");
//		System.out.println(sc.getCode());
		
		getCodeReq req = new getCodeReq();
		req.setCode("cs");
		req.setCodeType(true);
		req.setUserid("sdfsd");
		
		Result result = systemCodeService.getCode(req);
		System.out.println(result.getCode());
		System.out.println(result.getData()+","+result.getError());
		
	}
	@Test
	public void test13() throws Exception{
		getCodeReq req = new getCodeReq();
		req.setCode("cs");
		req.setCodeType(true);
		req.setUserid("sdfsd");
		Result result = systemCodeService.updateCodeItem(req);
		System.out.println(result.getCode());
	}
	
//	@Autowired
//	private  IVehicleManageService  vehicleManageService;
//	@Autowired
//	private IAccessRecordService accessRecordService;
//	
//	@Autowired
//	private IOtherBdVehicleService otherBdVehicleService;
//	
//	@Autowired
//	private ISystemDataDictService systemDataDictService;
//	
//	@Test
//	public void findDictListTest()throws Exception{
//		VehicleManageSave bean = new  VehicleManageSave();
////		bean.setCode("CL01003611");
////		bean.setInternalcode("cl");
//		bean.setRfid("E152016050500000000000C3");
//		bean.setVehicleno("陕EM5082");
////		bean.setTransporttype("0");
////		bean.setVehicletype("车辆类型");
////		bean.setTransportunit("运输单位");
////		bean.setMaxweight(22.00);
////		bean.setTareweight(5.3);
////		bean.setOwnername("张三");
////		bean.setTelephone("15234567890");
////		bean.setAddress("银河系地球村");
//		bean.setOrgid("0");
//		bean.setOrgname("天瑞集团信息化部");
////		bean.setIsvalid("1");
////		bean.setIsblacklist("0");
////		bean.setRemarks("这里可以写备注");
//		bean.setCreator("我是创建人id");
////		bean.setCreatetime(System.currentTimeMillis());
////		bean.setModifier("我是修改人id");
////		bean.setModifytime(System.currentTimeMillis());
////		int n =vehicleManageService.addVehicle(bean);
////		System.out.println(n);
////		logger.info("{}",JSON.toJSON(n));
//	}
//	@Test
//	public void testOtherVehicle() throws Exception{
//		OtherBdVehicleReq bean=new OtherBdVehicleReq();
////		bean.setId("02");
////		bean.setCode("CL99001333");
////		bean.setInnercode("1");
//		bean.setNamelike("a");
////		bean.setInfo("Jack");
////		bean.setAddr("纽约");
////		bean.setTelphone("120");
////		bean.setIsvalid(Byte.valueOf("0"));
////		bean.setOrgid("36");
////		bean.setOrgname("天瑞集团");
////		bean.setRemark("这里可以写备注");
////		bean.setCreator("张三");
////		bean.setCreatetime(System.currentTimeMillis());
////		bean.setModifier("李四");
////		bean.setModifytime(System.currentTimeMillis());
//		bean.setStart(1);
//		bean.setLimit(5);
//		Result s=otherBdVehicleService.page(bean);
//		PaginationVO<OtherBdVehicleResp> page=(PaginationVO<OtherBdVehicleResp>) s.getData();
//		System.out.println(page.getTotal());
//		System.out.println(page.getList());
////		logger.info("{}",JSON.toJSON(n));
//
//	}
//	
//	@Test
//	public void testSystemDataDict(){
//		SystemDataDictReq bean=new SystemDataDictReq();
////		bean.setId("9c4c2e491d4a4856a42c2c9a0577a8a3");
//		bean.setCode("6");
//		bean.setName("职称");
//		bean.setType("1");
//		bean.setOrderBy(2);
//		bean.setInfo("职称分类");
////		bean.setCreator("YZF");
////		bean.setCreatetime(System.currentTimeMillis());
////		bean.setModifier("YZF");
////		bean.setModifytime(System.currentTimeMillis());
////		bean.setUtc(new Timestamp(System.currentTimeMillis()));
////		String id="cdcc7fa15a7c4d5e80ef5fee19653c8f";
//		Result n=systemDataDictService.addSystemDataDict(bean);
////		List<SystemDataDictResp> list=(List<SystemDataDictResp>) n.getData();
////		for (SystemDataDictResp systemDataDictResp : list) {
////			System.out.println(systemDataDictResp);
////		}
//		System.out.println(n.getData());
//		logger.info("{}",JSON.toJSON(n));
//	}
//	@Test
//	public void testSystemDataDictItem(){
//		SystemDataDictItemReq bean=new SystemDataDictItemReq();
////		bean.setId("5f42b1084f584be190c1abdc7c29c8c8");
//		bean.setDictid("9c4c2e491d4a4856a42c2c9a0577a8a3");
////		bean.setCode("4");
////		bean.setName("AAAA");
////		bean.setInfo("第四等级");
////		bean.setIsvalid(Byte.valueOf("1"));
////		bean.setCreator("YZF");
////		bean.setCreatetime(System.currentTimeMillis());
////		bean.setModifier("YZF");
////		bean.setUtc(new Timestamp(System.currentTimeMillis()));
//		Result n=systemDataDictService.findSystemDataDictItems(bean);
////		List<SystemDataDictItemResp> list=(List<SystemDataDictItemResp>) n.getData();
////		for (SystemDataDictItemResp systemDataDictItemResp : list) {
////			System.out.println(systemDataDictItemResp);
////		}
//		System.out.println(n.getData());
//		logger.info("{}",JSON.toJSON(n));
//	}
//	@Test
//	public void testAccessRecord(){
//		AccessRecordReq req = new AccessRecordReq();
//		req.setIcardid(UUIDUtil.getId());
//		req.setSalesarriveid(UUIDUtil.getId());
//		req.setSource("测试2");
//		Result result = accessRecordService.add(req);
//		System.out.println(result.getCode()+","+result.getError());
//	}
}
