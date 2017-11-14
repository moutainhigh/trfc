package com.tianrui.service.impl.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 卡务管理Service
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:41:24
 * @classname CardService.java
 */
@Service
public class RFIDService implements IRFIDService {
	
	@Autowired
	private RFIDMapper rfidMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	@Transactional
	@Override
	public Result save(RFIDReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRfid())
		        && StringUtils.isNotBlank(req.getType())){
			RFID db =rfidMapper.selectByPrimaryKey(req.getRfid());
			if(db ==null){
				RFID save = new RFID();
				save.setRfid(req.getRfid());
				save.setState(true);
				save.setType(Integer.valueOf(req.getType()));
				save.setCreatetime(System.currentTimeMillis());
				save.setModifytime(System.currentTimeMillis());
				save.setModifier(req.getCurrUid());
				save.setCreator(req.getCurrUid());
				rfidMapper.insert(save);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result unbind(RFIDReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRfid())){
			RFID db =rfidMapper.selectByPrimaryKey(req.getRfid());
			//验证rfid是否存在    并且是临时卡  车牌号一致 解绑
			if(db !=null ){
				//验证rfid是否为临时卡
				if( db.getType()!=null && db.getType()==2 ){
					//验证是否为启用状态
					if( db.getState() !=true ){
						//验证车牌号是否一直
						if( StringUtils.isNotBlank(req.getVehicleNo())  ){
							VehicleManage vehicle =vehicleManageMapper.getVehicleByNo(req.getVehicleNo());
							if(vehicle !=null && StringUtils.isNotBlank(vehicle.getRfid()) &&  vehicle.getRfid().equals(req.getRfid()) ){
								vehicleManageMapper.deleteByPrimaryKey(vehicle.getId());
								rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								rs.setErrorCode(ErrorCode.RFID_ERROR3);
							}
						}else{
							rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						}
					}else{
						rs.setErrorCode(ErrorCode.RFID_ERROR2);
					}
				}else{
					rs.setErrorCode(ErrorCode.RFID_ERROR1);
				}
			}else{
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}
		}
		return rs;
	}
	
	
	
	
}
