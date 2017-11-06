package com.tianrui.service.impl.other.selfsystem;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.other.selfsystem.ISelfSystemService;
import com.tianrui.api.req.other.selfsystem.ApiCheckRfidReq;
import com.tianrui.api.req.other.selfsystem.ApiInspectionReportQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiPoundQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardReq;
import com.tianrui.api.resp.other.selfsystem.ApiInspectionReportQueryResp;
import com.tianrui.api.resp.other.selfsystem.ApiPoundQueryResp;
import com.tianrui.api.resp.other.selfsystem.ApiSendCardQueryResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SelfSystemService implements ISelfSystemService {

	@Override
	public Result purchaseSendCardQuery(ApiSendCardQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getIcno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			List<ApiSendCardQueryResp> list =new  ArrayList<ApiSendCardQueryResp>();
			ApiSendCardQueryResp item1 =new ApiSendCardQueryResp("id1","no1","materl","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"供应商1");
			ApiSendCardQueryResp item2 =new ApiSendCardQueryResp("id2","no2","mater2","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"供应商2");
			list.add(item1);
			list.add(item2);
			result.setData(list);
		}
		return result;
	}

	@Override
	public Result saleSendCardQuery(ApiSendCardQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getIcno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			List<ApiSendCardQueryResp> list =new  ArrayList<ApiSendCardQueryResp>();
			ApiSendCardQueryResp item1 =new ApiSendCardQueryResp("id3","no3","mater3","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"客户3");
			ApiSendCardQueryResp item2 =new ApiSendCardQueryResp("id4","no4","mater4","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"客户4");
			list.add(item1);
			list.add(item2);
			result.setData(list);
		}
		return result;
	}

	@Override
	public Result otherEntrySendCardQuery(ApiSendCardQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getIcno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			List<ApiSendCardQueryResp> list =new  ArrayList<ApiSendCardQueryResp>();
			ApiSendCardQueryResp item1 =new ApiSendCardQueryResp("id5","no5","mater5","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"张三");
			ApiSendCardQueryResp item2 =new ApiSendCardQueryResp("id6","no6","mater6","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"李四");
			list.add(item1);
			list.add(item2);
			result.setData(list);
		}
		return result;
	}

	@Override
	public Result otherExitSendCardQuery(ApiSendCardQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getIcno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			List<ApiSendCardQueryResp> list =new  ArrayList<ApiSendCardQueryResp>();
			ApiSendCardQueryResp item1 =new ApiSendCardQueryResp("id7","no7","mater7","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"王五");
			ApiSendCardQueryResp item2 =new ApiSendCardQueryResp("id8","no8","mater8","未入厂",DateUtil.getNowDateString(DateUtil.Y_M_D),DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S),"马六");
			list.add(item1);
			list.add(item2);
			result.setData(list);
		}
		return result;
	}

	@Override
	public Result sendCard(ApiSendCardReq paran) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( paran !=null && StringUtils.isNotBlank(paran.getRfid()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			//List<>
		}
		return result;
	}

	@Override
	public Result poundQuery(ApiPoundQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getPoundno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			
			ApiPoundQueryResp resp =new ApiPoundQueryResp();
			resp.setPoundId("id09");
			resp.setPoundNo("PD001");
			resp.setVehicleNo("豫DA3B17");
			resp.setTareweight("12");
			resp.setLighttime("2017-11-01 09:14:00");
			resp.setGrossweight("37");
			resp.setWeighttime("2017-11-01 09:24:00");
			resp.setNetweight("25");
			resp.setCustomer("宋太祖");
			resp.setNoticeid("TZD01");
			resp.setBillid("DD001");
			result.setData(resp);
		}
		return result;
	}

	@Override
	public Result inspectionReportQuery(ApiInspectionReportQueryReq query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result inspectionReportPrint(ApiInspectionReportQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getPoundno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result inspectionReportPrintQuery(ApiInspectionReportQueryReq query) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( query !=null && StringUtils.isNotBlank(query.getPoundno()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			ApiInspectionReportQueryResp resp =new ApiInspectionReportQueryResp();
			resp.setCount(0);
			resp.setPoundno("PD001");
			result.setData(resp);
		}
		return result;
	}

	@Override
	public Result checkCompositecard(ApiCheckRfidReq apiCheckRfidReq) {
		//TODO 需要实现
		Result result = Result.getParamErrorResult();
		if( apiCheckRfidReq !=null && StringUtils.isNotBlank(apiCheckRfidReq.getRfid()) ){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
    
   
    
}
