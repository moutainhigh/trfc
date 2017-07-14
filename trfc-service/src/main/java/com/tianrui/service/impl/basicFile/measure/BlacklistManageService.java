package com.tianrui.service.impl.basicFile.measure;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.resp.basicFile.measure.BlacklistResp;
import com.tianrui.service.bean.basicFile.measure.BlacklistManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.mapper.basicFile.measure.BlacklistManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class BlacklistManageService implements IBlacklistManageService {
	
	@Autowired
	private BlacklistManageMapper blacklistManageMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;

    @Override
    public PaginationVO<BlacklistResp> page(BlacklistManageQuery query) throws Exception {
        PaginationVO<BlacklistResp> page = null;
        if (query != null) {
            page = new PaginationVO<BlacklistResp>();
            long count=blacklistManageMapper.countBlacklist(query);
            if (count > 0) {
                query.setStart((query.getPageNo()-1)*query.getPageSize());
                query.setLimit(query.getPageSize());
                List<BlacklistResp> list = blacklistManageMapper.listBlacklist(query);
                formatDate(list);
                page.setList(list);;
            }
            page.setPageNo(query.getPageNo());
            page.setPageSize(query.getPageSize());
            page.setTotal(count);
        }
        return page;
    }

    @Override
    public Result add(BlacklistManageSave save) throws Exception {
        Result result = Result.getParamErrorResult();
        if(save != null && StringUtils.isNotBlank(save.getVehicleid())){
            BlacklistManage blacklist = blacklistManageMapper.getBeanByVehicleId(save.getVehicleid());
            if (blacklist == null) {
                VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicleid());
                if (vehicle != null) {
                    BlacklistManage bean = new BlacklistManage();
                    bean.setId(UUIDUtil.getId());
                    bean.setVehicleid(vehicle.getId());
                    bean.setVehicleno(vehicle.getVehicleno());
                    bean.setCreator(save.getCurrId());
                    bean.setCreatetime(System.currentTimeMillis());
                    bean.setRemarks(save.getRemarks());
                    bean.setModifier(save.getCurrId());
                    bean.setModifytime(System.currentTimeMillis());
                    if (blacklistManageMapper.insertSelective(bean) == 1) {
                        VehicleManage vehicleManage = new VehicleManage();
                        vehicleManage.setId(save.getVehicleid());
                        vehicleManage.setIsblacklist(Constant.ONE_STRING);
                        vehicleManage.setModifier(save.getCurrId());
                        vehicleManage.setModifytime(System.currentTimeMillis());
                        vehicleManageMapper.updateByPrimaryKeySelective(vehicleManage);
                        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                    } else {
                        result.setErrorCode(ErrorCode.OPERATE_ERROR);
                    }
                } else {
                    result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
                }
            } else {
                result.setErrorCode(ErrorCode.BLACKLIST_REPEAT_ERROR);
            }
        }
        return result;
    }
    
    private void formatDate(List<BlacklistResp> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (BlacklistResp resp : list) {
                resp.setCreateTimeStr(DateUtil.parse(resp.getCreateTime(), DateUtil.Y_M_D_H_M_S));
            }
        }
    }
	
    @Transactional
	@Override
	public Result del(BlacklistManageQuery query) {
		Result result = Result.getParamErrorResult();
		if (query!=null && StringUtils.isNotBlank(query.getId())) {
		    BlacklistManage blacklist = blacklistManageMapper.selectByPrimaryKey(query.getId());
		    if (blacklist != null) {
	            if (blacklistManageMapper.deleteByPrimaryKey(query.getId()) == 1) {
	                VehicleManage vehicle = new VehicleManage();
	                vehicle.setId(blacklist.getVehicleid());
	                vehicle.setIsblacklist(Constant.ZERO_STRING);
	                vehicle.setModifier(query.getCurrId());
                    vehicle.setModifytime(System.currentTimeMillis());
	                vehicleManageMapper.updateByPrimaryKeySelective(vehicle);
	                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	            } else {
	                result.setErrorCode(ErrorCode.OPERATE_ERROR);
	            }
		    } else {
		        result.setErrorCode(ErrorCode.BLACKLIST_REMOVE_ERROR);
		    }
		}
		return result;
	}
    
    @Override
    public int deleteBlacklist(String id){
        int n = 0;
        if(StringUtils.isNotBlank(id)){
            n = blacklistManageMapper.deleteBlacklistByVid(id);
        }
        return n;
    }

}