package com.tianrui.api.intf.system.auth;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.system.auth.BdPsndocReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IBdPsndocService {
	Result findMaxUtc(BdPsndocReq query) throws Exception;
	
	Result updateDataWithDC(List<JSONObject> list )throws Exception;
}
