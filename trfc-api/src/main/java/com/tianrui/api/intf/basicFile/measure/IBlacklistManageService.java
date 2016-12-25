package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;

public interface IBlacklistManageService {

	int deleteBlacklist(String id);

	int addBlacklist(BlacklistManageReq req) throws Exception;
}