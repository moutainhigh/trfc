package com.tianrui.smartfactory.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionAuditEnum {
	
	EMPTY_CAR_OUT_FACTORY(1,"空车出厂"),
	FILL_BAG(2,"补包"),
	BACK_BAG(3,"回包"),
	DONT_FILL_BAG(4,"无需补包"),
	INFRARED_BLOCK(5,"红外被挡");
	
	private Integer code;
	private String name;
	private static Map<Integer, String> map = null;
	
	static {
		map = new HashMap<Integer, String>();
		ExceptionAuditEnum[] enums = ExceptionAuditEnum.values();
		if (enums != null && enums.length > 0) {
			for (ExceptionAuditEnum e : enums) {
				map.put(e.getCode(), e.getName());
			}
		}
	}
	
	private ExceptionAuditEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(Integer code) {
		return map.get(code);
	}
}
