package com.tianrui.api.req.basicFile.nc;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.BaseReq;

public class NcBatchUpdateReq extends BaseReq {
	
	private static final long serialVersionUID = 8209305071357833825L;

	private List<JSONObject> list;

	public List<JSONObject> getList() {
		return list;
	}

	public void setList(List<JSONObject> list) {
		this.list = list;
	}


	
	

}
