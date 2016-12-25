package com.tianrui.service.impl.basicFile.measure;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.service.bean.basicFile.measure.BlacklistManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.mapper.basicFile.measure.BlacklistManageMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;

@Service
public class BlacklistManageService implements IBlacklistManageService {
	
	@Autowired
	private BlacklistManageMapper blacklistManageMapper;
	
	@Override
	public int deleteBlacklist(String id){
		int n = 0;
		if(StringUtils.isNotBlank(id)){
			n = blacklistManageMapper.deleteBlacklistByVid(id);
		}
		return n;
	}

	@Override
	public int addBlacklist(BlacklistManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			BlacklistManage record = new BlacklistManage();
			record.setVehicleno(req.getVehicleno());
			List<VehicleManage> list = this.blacklistManageMapper.selectSelective(record);
			if(list != null && list.size() > 0){
				return -1;
			}
			BlacklistManage blacklist = new BlacklistManage();
			PropertyUtils.copyProperties(blacklist, req);
			blacklist.setId(UUIDUtil.getId());
			blacklist.setCreatetime(System.currentTimeMillis());
			blacklist.setModifytime(System.currentTimeMillis());
			n = this.blacklistManageMapper.insert(blacklist);
		}
		return n;
	}
	
}