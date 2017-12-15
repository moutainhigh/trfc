package com.tianrui.api.intf.system.auth;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.system.auth.SmUserReq;
import com.tianrui.api.resp.system.auth.SmUserResp;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISmUserService {
	Result findMaxUtc(SmUserReq query) throws Exception;
	
	Result updateDataWithDC(List<JSONObject> list )throws Exception;

	List<SmUserResp> listAll() throws Exception;

	List<SmUserResp> listByCache() throws Exception;
}
