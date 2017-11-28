package com.tianrui.web.action.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.Attributes.Name;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tianrui.api.intf.common.IFileService;

/**
 * 
  * @ClassName: CommonAction 
  * @Description: 通用action
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月24日 上午11:25:11 
  *
 */
@Controller
@Scope("prototype")   
public class ImageAction {

	
	public static Logger loger=org.slf4j.LoggerFactory.getLogger(ImageAction.class);
	@Autowired
	IFileService fileService;
	//用户实名认证页面
	@RequestMapping("/imgview/{filename}")
	public void imgview(@PathVariable String filename, HttpServletResponse response) throws IOException{
		
	    OutputStream os = null;  
	    try {  
	        os = response.getOutputStream();  
	        InputStream in =fileService.download(filename);
	        os.write(IOUtils.toByteArray(in));
	        os.flush();  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
        
	}
	
	
}
