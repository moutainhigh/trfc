package com.tianrui.test.mongo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.util.Base64;
import com.mongodb.gridfs.GridFSDBFile;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class FileUploadTest {
	
	public static Logger logger =LoggerFactory.getLogger(FileUploadTest.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@Test
	public void uploadTest() throws FileNotFoundException{
		
		File file= new File("d://1.jpg");
		
		InputStream input = new FileInputStream(file);
		gridFsTemplate.store(input, "3");
	}
	
	
	@Test
	public void findTest() throws FileNotFoundException{
		Query query = new Query();
		query.addCriteria(Criteria.where("filename").is("2.jpg"));
		
		 List<GridFSDBFile> list =gridFsTemplate.find(query);
		 for( GridFSDBFile gridFSDBFile:list ){
			 System.out.println(gridFSDBFile.getFilename());
			 System.out.println(gridFSDBFile.getMD5());
			 System.out.println(gridFSDBFile.getChunkSize());
		 }
	}

	
	 void getImgString(){
	
	 }
	
}
