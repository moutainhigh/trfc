package com.tianrui.web.cache;

import java.util.List;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.system.auth.ISmUserService;
import com.tianrui.api.resp.system.auth.SmUserResp;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheModule;

public class CacheCommon extends ContextLoaderListener {
	
	private ISmUserService smUserService;
	private CacheClient cacheClient;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
	        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
	        //获取bean  
	        smUserService = applicationContext.getBean(ISmUserService.class); 
	        cacheClient = applicationContext.getBean(CacheClient.class); 
			List<SmUserResp> list = smUserService.listAll();
			cacheClient.remove(CacheModule.SM_USER.getCode());
			cacheClient.saveString(CacheModule.SM_USER.getCode(), JSON.toJSONString(list), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
