package com.tianrui.api.req.android;

/**
 * @annotation 客商APP我的车辆查询条件
 * @author zhanggaohao
 *
 */
public class MyVehicleListParam extends AppBase {
	
	private static final long serialVersionUID = 3574816464825541063L;
	//页签类别（0：未入厂，1：入厂，2：熟车）
	private String type;
	private int start;
	private int limit;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MyVehicleListParam [type=" + type + ", start=" + start + ", limit=" + limit + "]";
	}
}