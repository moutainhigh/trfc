package com.tianrui.service.api.nc.imple;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.api.nc.imple.IOneBillOneCarService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.nc.oneBillOneCar;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OneBillOneCarService implements IOneBillOneCarService {

	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@Override
	public Result auditCallBack(oneBillOneCar req) {
		Result result = Result.getParamErrorResult();
//		if (req != null && StringUtils.isNotBlank(req.getBillId())
//				&& StringUtils.isNotBlank(req.getBillDetailId())) {
//			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(req.getBillId());
//			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(req.getBillDetailId());
//			SalesArrive bean = new SalesArrive();
//			bean.setId(UUIDUtil.getId());
//			bean.setCode(getCode("TH", sa.getMakerid(), true));
//			bean.setAuditstatus(Constant.ZERO_STRING);
//			bean.setSource(Constant.TWO_STRING);
//			bean.setStatus(Constant.ZERO_STRING);
//			bean.setVehicleid(vehicle.getId());
//			bean.setVehicleno(vehicle.getVehicleno());
//			bean.setVehiclerfid(vehicle.getRfid());
//			if (driver != null) {
//				bean.setDriverid(driver.getId());
//				bean.setDrivername(driver.getName());
//				bean.setDriveridentityno(driver.getIdentityno());
//				saveUserDriver(user.getId(), driver.getId());
//			}
//			bean.setBillid(sa.getId());
//			bean.setBillcode(sa.getCode());
//			bean.setBilldetailid(sad.getId());
//			bean.setUnit(sad.getUnit());
//			bean.setTakeamount(param.getNumber());
//			bean.setState(Constant.ONE_STRING);
//			bean.setMakerid(user.getId());
//			bean.setMakebillname(user.getName());
//			bean.setMakebilltime(System.currentTimeMillis());
//			bean.setCreator(user.getId());
//			bean.setCreatetime(System.currentTimeMillis());
//			salesArriveMapper.insertSelective(bean);
//			SalesApplicationArrive join = new SalesApplicationArrive();
//			join.setId(UUIDUtil.getId());
//			join.setBillId(sa.getId());
//			join.setBillDetailId(sad.getId());
//			join.setNoticeId(bean.getId());
//			join.setNumber(param.getNumber());
//			join.setSequence(1);
//			salesApplicationArriveMapper.insertSelective(join);
//			sad.setMargin(sad.getMargin() - param.getNumber());
//			sad.setPretendingtake(sad.getPretendingtake() + param.getNumber());
//			salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
//			updateCode("TH", param.getUserId());
//			saveUserVehicle(user.getId(), vehicle.getId());
//			
//		}
		return result;
	}
	
	private String getCode(String code, String userId, boolean flag) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(flag);
		codeReq.setUserid(userId);
		return systemCodeService.getCode(codeReq).getData().toString();
	}
	
	private void updateCode(String code, String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		systemCodeService.updateCodeItem(codeReq);
	}

	@Override
	public Result validCallBack(oneBillOneCar body) {
		
		return null;
	}

}
