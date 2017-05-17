package com.tianrui.test.api.web;

import java.util.HashMap;
import java.util.Map;

public class testheihei {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("2", 12);
		map.put("1", 1);
		
		System.out.println(map.get("3"));
	}

}
