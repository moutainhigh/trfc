package com.tianrui.service.mongo.impl;

import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.system.base.SystemLoginLog;
import com.tianrui.service.mongo.SystemLoginLogDao;

@Repository("systemLoginLogDao")
public class SystemLoginLogDaoImpl extends BaseDaoImpl<SystemLoginLog,String> implements SystemLoginLogDao{

	@Override
	public void saveSystemLoginLog(SystemLoginLog systemLoginLog) {
		
	}

	
	
}
