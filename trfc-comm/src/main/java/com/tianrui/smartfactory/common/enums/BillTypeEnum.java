package com.tianrui.smartfactory.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum BillTypeEnum {
	
	BILL_TYPE_ONE_CAR("0","一单一车"),
	BILL_TYPE_MORE_CAR("1","一单多车");
	
	private String code;
	private String name;
	private static Map<String, String> map = null;
	
	static {
		map = new HashMap<String, String>();
		BillTypeEnum[] enums = BillTypeEnum.values();
		if (enums != null && enums.length > 0) {
			for (BillTypeEnum e : enums) {
				map.put(e.getCode(), e.getName());
			}
		}
	}
	
	private BillTypeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(String code) {
		return map.get(code);
	}
}
