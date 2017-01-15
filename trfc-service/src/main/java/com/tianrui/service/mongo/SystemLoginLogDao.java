package com.tianrui.service.mongo;

import com.tianrui.service.bean.system.base.SystemLoginLog;


/**
 * 
 * @author Administrator
 *
 */
public interface SystemLoginLogDao extends BaseDao<SystemLoginLog,String> {

	public void saveSystemLoginLog(SystemLoginLog systemLoginLog);
	
}
