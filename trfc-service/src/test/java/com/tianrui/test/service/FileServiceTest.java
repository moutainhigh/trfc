package com.tianrui.test.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.businessManage.financeManage.ISalesChargeService;
import com.tianrui.api.intf.businessManage.financeManage.ISalesLedgerService;
import com.tianrui.api.intf.common.IFileService;
import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesChargeQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesLedgerSave;
import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.service.bean.businessManage.financeManage.SalesCharge;
import com.tianrui.service.impl.basicFile.measure.YardManageService;
import com.tianrui.service.impl.businessManage.financeManage.SalesLedgerService;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.vo.Result;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class FileServiceTest {
	public static Logger logger =LoggerFactory.getLogger(FileServiceTest.class);
	@Autowired
	private  IFileService fileService ;
	@Autowired
	private IYardManageService yardManageService;
	@Autowired
	private ISalesChargeService salesChargeService;
	@Autowired
	private ISalesLedgerService salesLedgerService;
	@Test
	public void saveTest()throws Exception{
		FileUploadReq req = new FileUploadReq();
		req.setFileByte(getBytes("D:\\1.jpg"));
		req.setuId("111");
		
		Result rs =fileService.uploadImg(req);
		System.out.println(JSON.toJSONString(rs));
	}
	
	@Test
	public void test1() throws Exception{
		YardManageQuery query=new YardManageQuery();
		query.setIsvalid("1");
		query.setPageNo(1);
		query.setPageSize(10);
		Result result=yardManageService.page(query);
		System.out.println(result);
	}
	@Test
	public void test2() throws Exception{
		SalesChargeQuery  query=new SalesChargeQuery();
		query.setPageNo(1);
		query.setPageSize(10);
		Result result=salesChargeService.page(query);
		System.out.println(result.getData());
	}
	@Test
	public void test3() throws Exception{
		SalesLedgerSave bean=new SalesLedgerSave();
		bean.setCode("D22006201703100003");
		bean.setAuditstatus("3");
		bean.setCustomerid("1002P110000000HS7G71");
		bean.setCustomername("大石桥市旗口镇长屯村");
		bean.setChargetype("1");
		bean.setPayer("反倒是");
		bean.setBilldate(1489133480399l);;
		bean.setMoney(55.46);
		bean.setMoneybig("伍拾伍元肆角陆分");
		bean.setChargeunit(Constant.ORG_NAME);
		bean.setMakeid("f36941e550664546ba9739053e935a20");
		bean.setMakebillname("金鑫测试");
		bean.setMakebilltime(1488963363096l);
//		bean.setAuditid("f36941e550664546ba9739053e935a20");
//		bean.setAuditname("金鑫测试");
//		bean.setAudittime(1488963363096l);
		bean.setUser("f36941e550664546ba9739053e935a20");
		bean.setRemark("都佛挡杀佛");
		Result result=salesLedgerService.add(bean);
		System.out.println(result.getData());
		
	}
	private byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    } 
}
