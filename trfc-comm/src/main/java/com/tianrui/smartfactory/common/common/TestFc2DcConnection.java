package com.tianrui.smartfactory.common.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;

public class TestFc2DcConnection {
	
	private static Logger logger = LoggerFactory.getLogger(TestFc2DcConnection.class);
	
	public static ApiResult post(ApiParam<?> apiParam, String path){
		ApiResult apiResult = null;
		logger.info("post url:{},ApiParam:{}",new Object[]{path,JSON.toJSONString(apiParam)});
		try {
			URL url = new URL(path);
			//打开链接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//连接超时设置
			connection.setConnectTimeout(5000);
			//读取超时设置
			connection.setReadTimeout(15000);
			//设置请求类型
			connection.setRequestMethod("POST");
			//设置是否带参数
			connection.setDoOutput(true);
			//拼接参数
			StringBuffer params = new StringBuffer();
			params.append("p").append("=").append(JSON.toJSONString(apiParam));
			byte[] bytes = params.toString().getBytes("utf-8");
			connection.getOutputStream().write(bytes);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			//发送请求
			String result = reader.readLine();
			apiResult = JSON.parseObject(result, ApiResult.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return apiResult;
	}
}
