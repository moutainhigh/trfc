package com.tianrui.test.dao;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;

public class testDemo {
	public static void main(String[] args) {
//		Calendar c = Calendar.getInstance();
//		Date now = new Date(System.currentTimeMillis());
//		Date last = new Date(1484270339346l);
//		c.setTime(last);
//		System.out.println(c.get(Calendar.YEAR));
//		System.out.println(c.get(Calendar.DATE));
//		System.out.println(c.get(Calendar.MONTH));
//		System.out.println(last);
		String userStr = "[{\"material\":\"PS42.5水泥散装\",\"factorycode\":\"123345\",\"count\":\"5000\",\"producedtime\":1486512000000,\"testtime\":1486512000000,\"remark\":\"搜索\"},{\"material\":\"PS42.5水泥散装\",\"factorycode\":\"12345345\",\"count\":\"5000\",\"producedtime\":1486512000000,\"testtime\":1486512000000,\"remark\":\"尾气\"}]";
		JSONArray json = JSONArray.parseArray(userStr);//userStr是json字符串
		SalesBatchnumReq h = JSONArray.toJavaObject(json.getJSONObject(0), SalesBatchnumReq.class);
		System.out.println(h.getMaterial());
		
		
	}
}
