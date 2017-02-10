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
import com.tianrui.api.intf.common.IFileService;
import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.smartfactory.common.vo.Result;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class FileServiceTest {
	public static Logger logger =LoggerFactory.getLogger(FileServiceTest.class);
	@Autowired
	private  IFileService fileService ;
	
	@Test
	public void saveTest()throws Exception{
		FileUploadReq req = new FileUploadReq();
		req.setFileByte(getBytes("D:\\1.jpg"));
		req.setuId("111");
		
		Result rs =fileService.uploadImg(req);
		System.out.println(JSON.toJSONString(rs));
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
