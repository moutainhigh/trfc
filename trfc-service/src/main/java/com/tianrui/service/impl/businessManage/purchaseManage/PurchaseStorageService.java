package com.tianrui.service.impl.businessManage.purchaseManage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseStorageService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseStorageUpReq;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseStorageService implements IPurchaseStorageService {
	@Autowired
	PurchaseStorageListMapper purchaseStorageMapper;
	@Autowired
	PoundNoteMapper poundNoteMapper;

	@Override
	public Result poundPushUp(PurchaseStorageUpReq req) {
		Result rs =Result.getSuccessResult();
		if(req !=null && StringUtils.isNotBlank(req.getId())){
			if(StringUtils.equals("1", req.getStatus())){
				PurchaseStorageList db =purchaseStorageMapper.selectByPrimaryKey(req.getId());
				if(db !=null ){
					//入库单状态修改为 已退单
					PurchaseStorageList update = new PurchaseStorageList();
					update.setId(req.getId());
					update.setNcId(req.getNcId());
					update.setTs(req.getTs());
					update.setStatus(Constant.PUSH_STATUS_END);
					purchaseStorageMapper.updateByPrimaryKeySelective(update);
					//榜单状态修改为 已退单
					PoundNote updatePound = new PoundNote();
					updatePound.setId(db.getPoundId());
					updatePound.setReturnstatus(Constant.POUND_PUSH_STATUS_END);
					updatePound.setModifytime(System.currentTimeMillis());
					poundNoteMapper.updateByPrimaryKeySelective(updatePound);
				}
			}else{
				//DC推动失败 处理原因
			}
		}
		return rs;
	}

	

}
