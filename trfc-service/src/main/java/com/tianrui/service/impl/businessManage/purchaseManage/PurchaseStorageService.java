package com.tianrui.service.impl.businessManage.purchaseManage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseStorageService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseStorageUpReq;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseStorageService implements IPurchaseStorageService {
	@Autowired
	PurchaseStorageListMapper purchaseStorageMapper;
	@Autowired
	PoundNoteMapper poundNoteMapper;
	@Autowired
	IPushSingleService pushSingleService;

	@Override
	public Result poundPushUp(PurchaseStorageUpReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if(req !=null && StringUtils.isNotBlank(req.getId())){
			PurchaseStorageList db =purchaseStorageMapper.selectByPrimaryKey(req.getId());
			if(db !=null ){
				PoundNote pn = poundNoteMapper.selectByPrimaryKey(db.getPoundId());
				PushSingleReq ps = new PushSingleReq();
				ps.setId(UUIDUtil.getId());
				ps.setRequisitionNum(pn.getBillcode());
				ps.setNoticeNum(pn.getNoticecode());
				ps.setRequisitionType(Constant.ONE_STRING);
				ps.setLightCarTime(pn.getLighttime());
				ps.setHeavyCarTime(pn.getWeighttime());
				ps.setNetWeight(pn.getNetweight().toString());
				ps.setCreatetime(System.currentTimeMillis());
				ps.setModifytime(System.currentTimeMillis());
				ps.setDesc1(req.getStatus());
				ps.setReasonFailure(req.getMsg());
				if(StringUtils.equals("1", req.getStatus())){
					ps.setPushStatus(Constant.TWO_STRING);
					//入库单状态修改为 已推单
					PurchaseStorageList update = new PurchaseStorageList();
					update.setId(req.getId());
					update.setRkdNcId(req.getNcId());
					update.setTs(req.getTs());
					update.setStatus(Constant.PUSH_STATUS_END);
					purchaseStorageMapper.updateByPrimaryKeySelective(update);
					//榜单状态修改为 已推单(红冲修改为已红冲)
					if (StringUtils.equals(db.getType(), Constant.THREE_STRING)) {
						pn.setRedColStatus(Constant.TWO_STRING);
					} else {
						pn.setReturnstatus(Constant.POUND_PUSH_STATUS_END);
					}
					pn.setModifytime(System.currentTimeMillis());
					poundNoteMapper.updateByPrimaryKeySelective(pn);
				}else{
					//DC推动失败 处理原因
					ps.setPushStatus(Constant.THREE_STRING);
				}
				pushSingleService.savePushSingle(ps);
			}
		}
		return rs;
	}

	

}
