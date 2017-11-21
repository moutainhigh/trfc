package com.tianrui.service.impl.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.resp.businessManage.cardManage.RfidTypeResp;
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
					if( db.getState()){
						//验证车牌号是否一直
						if( StringUtils.isNotBlank(req.getVehicleNo())  ){
							VehicleManage vehicle =vehicleManageMapper.getVehicleByNo(req.getVehicleNo());
							if(vehicle !=null && StringUtils.isNotBlank(vehicle.getRfid()) &&  vehicle.getRfid().equals(req.getRfid()) ){
								//修改车辆数据 重置rfid为null
								VehicleManage  update =new VehicleManage();
								update.setId(vehicle.getId());
								update.setCode(vehicle.getCode());
								update.setInternalcode(vehicle.getInternalcode());
								update.setVehicleno(vehicle.getVehicleno());
								update.setTransporttype(vehicle.getTransporttype());
								update.setVehicletype(vehicle.getVehicletype());
								update.setOrgid(vehicle.getOrgid());
								update.setOrgname(vehicle.getOrgname());
								update.setIsvalid(vehicle.getIsvalid());
								update.setIsblacklist(vehicle.getIsblacklist());
								update.setState(vehicle.getState());
								update.setCreator(vehicle.getCreator());
								update.setCreatetime(vehicle.getCreatetime());
								update.setModifier(req.getCurrUid());
								update.setModifytime(System.currentTimeMillis());
								vehicleManageMapper.updateByPrimaryKey(update);
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

	@Override
	public Result rfidTypeQuery(RFIDReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRfid())){
			//rfid存在 并且有车辆绑定关系时才返回
			RFID db =rfidMapper.selectByPrimaryKey(req.getRfid());
			if( db !=null ){
				//查询对应车辆信息
				VehicleManage vehicle =vehicleManageMapper.getVehicleByRfid(req.getRfid());
				if(vehicle !=null && "1".equals(vehicle.getState()) && StringUtils.isNotBlank(vehicle.getVehicleno())){
					//返回信息
					RfidTypeResp resp =new RfidTypeResp();
					resp.setRfidType(String.valueOf(db.getType()));
					resp.setVehicleNo(vehicle.getVehicleno());
					resp.setRfid(db.getRfid());
					rs.setData(resp);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.RFID_ERROR4);
				}
			}else{
				rs.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		}
		return rs;
	}
	
	
	
	
	
	
	
}
