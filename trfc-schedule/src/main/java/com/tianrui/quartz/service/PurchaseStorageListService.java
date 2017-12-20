package com.tianrui.quartz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageListItem;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListItemMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;

/**
 * 厂区到数据中心采购入库单数据同步接口
 * 
 * @author YangZhenFu
 *
 */
@Service
public class PurchaseStorageListService implements IPurchaseStorageListService {

	private Logger Logger = LoggerFactory.getLogger(PurchaseStorageListService.class);

	@Autowired
	private PurchaseStorageListMapper purchaseStorageListMapper;
	@Autowired
	private PurchaseStorageListItemMapper PurchaseStorageListItemMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private IPushSingleService pushSingleService;

	/**
	 * 获取采购入库单数据
	 */
	@Override
	public List<PurchaseStorageList> getPurchaseStorageList() {
		List<PurchaseStorageList> list = null;
		// 获取未推单的数据
		// 获取增量数据
		list = purchaseStorageListMapper.selectIncrementalData(null);
		if (CollectionUtils.isNotEmpty(list)) {
			List<String> ids = new ArrayList<String>();
			Map<String, PurchaseStorageList> map = new HashMap<String, PurchaseStorageList>();
			for (PurchaseStorageList item : list) {
				ids.add(item.getId());
				map.put(item.getId(), item);
			}
			// 获取所有的子表数据
			List<PurchaseStorageListItem> itemList = PurchaseStorageListItemMapper.selectByOrderIds(ids);
			// 遍历并赋值到主表
			if (CollectionUtils.isNotEmpty(itemList)) {
				for (PurchaseStorageListItem listItem : itemList) {
					PurchaseStorageList order = map.get(listItem.getPurchaseStorageListId());
					if (order.getList() != null) {
						order.getList().add(listItem);
					} else {
						List<PurchaseStorageListItem> itemList2 = new ArrayList<PurchaseStorageListItem>();
						itemList2.add(listItem);
						order.setList(itemList2);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 数据回传
	 */
	@Transactional
	@Override
	public void returnPurchaseStorageList() throws Exception {
		// 获取采购入库单数据 获取所有为推单的数据
		List<PurchaseStorageList> list = getPurchaseStorageList();
		List<SmUser> smUserList = null;
		if (CollectionUtils.isNotEmpty(list)) {
			List<PurchaseStorageList> subList = new ArrayList<PurchaseStorageList>();
			for (int i = 0; i < list.size(); i++) {
				PurchaseStorageList order = list.get(i);
				smUserList = getSmUser(order.getBillmaker());
				if (CollectionUtils.isNotEmpty(smUserList)) {
					order.setBillmaker(smUserList.get(0).getId());
				}
				subList.add(order);
				// 调用dc 接口成功 则推单状态为推单中 榜单展示为推单中
				ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(subList),
				        Constant.URL_DOMAIN + Constant.URL_RETURN_PURCHASESTORAGEATION);
				PoundNote pn = poundNoteMapper.selectByPrimaryKey(order.getPoundId());
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
				ps.setDesc2(Constant.ZERO_STRING);
				if (apiResult != null) {
					if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
						order.setStatus(Constant.PUSH_STATUS_ING);
						purchaseStorageListMapper.updateByPrimaryKeySelective(order);
						if (!StringUtils.equals(order.getType(), Constant.THREE_STRING)) {
							pn.setReturnstatus(Constant.POUND_PUSH_STATUS_ING);
							poundNoteMapper.updateByPrimaryKeySelective(pn);
						}
					} else {
						Logger.info(ErrorCode.OPERATE_ERROR.getMsg());
						ps.setPushStatus(Constant.THREE_STRING);
					}
					ps.setReasonFailure(apiResult.getError());
					ps.setDesc1(apiResult.getCode());
				} else {
					ps.setPushStatus(Constant.THREE_STRING);
					ps.setReasonFailure("FC-DC到货单推单失败，连接超时。");
					ps.setDesc1("-1");
				}
				pushSingleService.savePushSingle(ps);
				subList.clear();
			}
			Logger.info("同步完成!");
		}
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUserResp user = systemUserService.getUser(id);
		if (user != null) {
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}

}
