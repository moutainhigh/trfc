package com.tianrui.test.service;

import java.security.PublicKey;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.basicFile.other.IOtherBdVehicleService;
import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DataDictServiceTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);
	@Autowired
	private  IVehicleManageService  vehicleManageService;
	
	@Autowired
	private IOtherBdVehicleService otherBdVehicleService;
	
	@Test
	public void findDictListTest()throws Exception{
		VehicleManageReq bean = new  VehicleManageReq();
//		bean.setCode("CL01003611");
//		bean.setInternalcode("cl");
		bean.setRfid("E152016050500000000000C3");
		bean.setVehicleno("陕EM5082");
//		bean.setTransporttype("0");
//		bean.setVehicletype("车辆类型");
//		bean.setTransportunit("运输单位");
//		bean.setMaxweight(22.00);
//		bean.setTareweight(5.3);
//		bean.setOwnername("张三");
//		bean.setTelephone("15234567890");
//		bean.setAddress("银河系地球村");
		bean.setOrgid("0");
		bean.setOrgname("天瑞集团信息化部");
//		bean.setIsvalid("1");
//		bean.setIsblacklist("0");
//		bean.setRemarks("这里可以写备注");
		bean.setCreator("我是创建人id");
//		bean.setCreatetime(System.currentTimeMillis());
//		bean.setModifier("我是修改人id");
//		bean.setModifytime(System.currentTimeMillis());
//		int n =vehicleManageService.addVehicle(bean);
//		System.out.println(n);
//		logger.info("{}",JSON.toJSON(n));
	}
	@Test
	public void testOtherVehicle() throws Exception{
//		OtherBdVehicleReq bean=new OtherBdVehicleReq();
//		bean.setId("02");
//		bean.setCode("CL99001333");
//		bean.setInnercode("1");
//		bean.setName("adf");
//		bean.setInfo("Jack");
//		bean.setAddr("纽约");
//		bean.setTelphone("120");
//		bean.setIsvalid(Byte.valueOf("0"));
//		bean.setOrgid("36");
//		bean.setOrgname("天瑞集团");
//		bean.setRemark("这里可以写备注");
//		bean.setCreator("张三");
//		bean.setCreatetime(System.currentTimeMillis());
//		bean.setModifier("李四");
//		bean.setModifytime(System.currentTimeMillis());
//		bean.setStart(1);
//		bean.setLimit(5);
		int n=otherBdVehicleService.deleteVehicle("061da67a53f947d590766cf72aadd88e");
		System.out.println(n);
		logger.info("{}",JSON.toJSON(n));

	}

}
