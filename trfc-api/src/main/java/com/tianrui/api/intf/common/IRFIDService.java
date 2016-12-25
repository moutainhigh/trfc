package com.tianrui.api.intf.common;

import java.util.List;

import com.tianrui.api.req.common.RFIDReq;

public interface IRFIDService {

	int save(RFIDReq req) throws Exception;

	int saveList(List<RFIDReq> list) throws Exception;
	
}